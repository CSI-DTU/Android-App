package com.dtu.csi;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfileFragment extends Fragment {
    EditText name, phone, email, college;
    Button save;
    public ProfileFragment() {
    }
    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        final SharedPreferences prefs = getActivity().getSharedPreferences("creds", 0);
        name = (EditText) v.findViewById(R.id.input_name);
        name.setText(prefs.getString("name", ""));
        email = (EditText) v.findViewById(R.id.input_email);
        email.setText(prefs.getString("email", ""));
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
                    email.setError("Not an Email");
                    if(email.requestFocus())
                        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }
        });
        college = (EditText) v.findViewById(R.id.input_college);
        college.setText(prefs.getString("college", ""));
        phone = (EditText) v.findViewById(R.id.input_phone);
        phone.setText(prefs.getString("phone", ""));
        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(phone.length() != 10) {
                    phone.setError("Invalid phone number");
                    if(phone.requestFocus())
                        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }
        });
        save = (Button) v.findViewById(R.id.btn_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.length() == 0)
                    name.setError("This field can't be left blank");
                else if(email.length() == 0)
                    email.setError("This field can't be left blank");
                else if(phone.length() == 0)
                    phone.setError("This field can't be left blank");
                else if(college.length() == 0)
                    college.setError("This field can't be left blank");
                else if(phone.length() != 10)
                    phone.setError("Invalid phone number");
                else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
                    email.setError("Not an Email");
                    if(email.requestFocus())
                        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);;
                }
                else {
                    prefs.edit()
                            .putString("name", name.getText().toString())
                            .putString("email", email.getText().toString())
                            .putString("phone", phone.getText().toString())
                            .putString("college", college.getText().toString())
                            .putBoolean("all_fields", true)
                            .apply();
                    Snackbar.make(v, "Profile Saved", BaseTransientBottomBar.LENGTH_SHORT).show();
                }
            }
        });
        return v;
    }
}
