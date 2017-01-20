package com.dtu.csi;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.dexafree.materialList.card.Card;
import com.dexafree.materialList.card.CardProvider;
import com.dexafree.materialList.card.OnActionClickListener;
import com.dexafree.materialList.card.action.TextViewAction;
import com.dexafree.materialList.view.MaterialListView;
import com.squareup.picasso.RequestCreator;
import com.victor.loading.rotate.RotateLoading;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class EventsFragment extends Fragment {
    RotateLoading spinner;
    MaterialListView event_list;

    public EventsFragment() {}

    public static EventsFragment newInstance() {
        return new EventsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_events_fragment2, container, false);
        spinner = (RotateLoading) layout.findViewById(R.id.spinner);
        spinner.start();
        event_list = (MaterialListView) layout.findViewById(R.id.event_list);

        final RequestQueue rq = Volley.newRequestQueue(this.getContext());
        final String url = getString(R.string.endpoint) + "/events";
        final JsonArrayRequest request = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("", response.toString());
                        spinner.stop();
                        for(int i = 0 ; i < response.length() ; i++) {
                            try {
                                final JSONObject event = response.getJSONObject(i);
                                Card card = new Card.Builder(getContext())
                                        .setTag("Event")
                                        .withProvider(new CardProvider<>())
                                        .setLayout(R.layout.material_basic_image_buttons_card_layout)
                                        .setTitle(event.getString("name"))
                                        .setTitleResourceColor(R.color.black)
                                        .setDescription(event.getString("description"))
                                        .setDescriptionResourceColor(R.color.black)
                                        .setDrawable(getString(R.string.endpoint) + event.getString("thumbnail"))
                                        .setDrawableConfiguration(new CardProvider.OnImageConfigListener() {
                                            @Override
                                            public void onImageConfigure(@NonNull RequestCreator requestCreator) {
                                                requestCreator.fit().centerInside();
                                            }
                                        })
                                        .addAction(R.id.left_text_button, new TextViewAction(getContext())
                                                .setText("Register")
                                                .setTextResourceColor(R.color.navy)
                                                .setListener(new OnActionClickListener() {
                                                    @Override
                                                    public void onActionClicked(final View view, final Card card) {
                                                        final SharedPreferences prefs = getActivity().getSharedPreferences("creds", 0);
                                                        if(!prefs.getBoolean("all_fields", false))
                                                            Snackbar.make(view, "Please complete your profile before registering for an event", Snackbar.LENGTH_SHORT).show();
                                                        else  {
                                                            try {
                                                                new AsyncTask<JSONObject, Void, String>() {
                                                                    final Snackbar snackbar = Snackbar.make(view, "Registering", BaseTransientBottomBar.LENGTH_INDEFINITE);
                                                                    URL url = new URL(getString(R.string.endpoint) + "/register");
                                                                    @Override
                                                                    protected void onPreExecute() {
                                                                        snackbar.show();
                                                                    }
                                                                    @Override
                                                                    protected String doInBackground(JSONObject... params) {
                                                                        try {
                                                                            Log.v("", "doInBackground");
                                                                            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                                                                            connection.setRequestMethod("POST");
                                                                            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                                                                            connection.setDoInput(true);
                                                                            connection.setDoOutput(true);
                                                                            JSONObject details = new JSONObject();
                                                                            details.put("full_name", prefs.getString("name", null));
                                                                            details.put("contact", prefs.getString("phone", null));
                                                                            details.put("college", prefs.getString("college", null));
                                                                            details.put("email_id", prefs.getString("email", null));
                                                                            details.put("events", event.getInt("id"));
                                                                            OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
                                                                            osw.write(details.toString());
                                                                            osw.close();
                                                                            int responseCode = connection.getResponseCode();
                                                                            if (responseCode == HttpURLConnection.HTTP_OK) {
                                                                                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                                                                                StringBuilder sb = new StringBuilder();
                                                                                String line;
                                                                                while((line = in.readLine()) != null) {
                                                                                    sb.append(line);
                                                                                }
                                                                                in.close();
                                                                                return sb.toString();

                                                                            } else {
                                                                                return "Error!";
                                                                            }
                                                                        } catch (Exception e) {
                                                                            e.printStackTrace();
                                                                            return null;
                                                                        }
                                                                    }
                                                                    @Override
                                                                    protected void onPostExecute(String result) {
                                                                        Log.v("TAG", "complete");
                                                                        snackbar.dismiss();
                                                                        Log.v("TAG", result);
                                                                        if(result.contains("successfully registered"))
                                                                            Snackbar.make(view, "Successful", Snackbar.LENGTH_SHORT);
                                                                        else
                                                                            Snackbar.make(view, "Failed", Snackbar.LENGTH_SHORT);
                                                                    }
                                                                }.execute();
                                                            } catch (Exception e) {
                                                                e.printStackTrace();
                                                            }
                                                        }
                                                    }
                                                })
                                            )
                                        .addAction(R.id.right_text_button, new TextViewAction(getContext())
                                                .setText("Share")
                                                .setTextResourceColor(R.color.navy)
                                                .setListener(new OnActionClickListener() {
                                                    @Override
                                                    public void onActionClicked(View view, Card card) {
                                                        Intent sendIntent = new Intent();
                                                        sendIntent.setAction(Intent.ACTION_SEND);
                                                        try {
                                                            sendIntent.putExtra(Intent.EXTRA_TEXT, event.getString("name") + " : " + event.getString("description"));
                                                        } catch (JSONException e) {
                                                            e.printStackTrace();
                                                        }
                                                        sendIntent.setType("text/plain");
                                                        startActivity(sendIntent);
                                                    }
                                                })
                                        )
                                        .endConfig()
                                        .build();
                                event_list.getAdapter().add(card);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("", error.toString());
                    }
        });

        rq.add(request);

        return layout;
    }



}
