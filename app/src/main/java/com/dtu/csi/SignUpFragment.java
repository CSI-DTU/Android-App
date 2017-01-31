package com.dtu.csi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;

public class SignUpFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    public static GoogleApiClient googleApiClient;
    public static int RC_SIGN_IN;
    public SignUpFragment() {}

    public static SignUpFragment newInstance(GoogleApiClient googleApiClient1) {
        SignUpFragment signUpFragment = new SignUpFragment();
        googleApiClient = googleApiClient1;
        return signUpFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sign_up, container, false);
        v.findViewById(R.id.sign_in_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == SignUpFragment.RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if(result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();
            Snackbar.make(this.getLayoutInflater(null).inflate(R.layout.fragment_sign_up, null).findViewById(R.id.sign_in_button), "Signed as " + account.getEmail(), Snackbar.LENGTH_SHORT).show();
            SharedPreferences prefs = getActivity().getSharedPreferences("creds", 0);
            prefs
                    .edit()
                    .putString("id", account.getId())
                    .putString("email", account.getEmail())
                    .putString("name", account.getGivenName())
                    .putBoolean("intro_done", true)
                    .apply();
            startActivity(new Intent(this.getContext(), MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
        } else {
            Snackbar.make(this.getLayoutInflater(null).inflate(R.layout.fragment_sign_up, null).findViewById(R.id.sign_in_button), "Sign In Failed", Snackbar.LENGTH_SHORT).show();
        }
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
