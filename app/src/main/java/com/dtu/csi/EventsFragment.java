package com.dtu.csi;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

    boolean isConnected() {
        ConnectivityManager cm =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.fragment_events_fragment2, container, false);
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
                        for (int i = 0; i < response.length(); i++) {
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
                                                .setTextResourceColor(R.color.black)
                                                .setListener(new OnActionClickListener() {
                                                    @Override
                                                    public void onActionClicked(final View view, final Card card) {
                                                        String event_id = "";
                                                        final SharedPreferences prefs = getActivity().getSharedPreferences("creds", 0);
                                                        final HashSet registered_events = (HashSet) prefs.getStringSet("events", new HashSet<String>());
                                                        try {
                                                            event_id = event.getString("id");
                                                        } catch (JSONException e) {
                                                            e.printStackTrace();
                                                        }
                                                        if(registered_events.contains(event_id))
                                                            Snackbar.make(view, "Already Registered", Snackbar.LENGTH_SHORT).show();
                                                        else {
                                                            if (!prefs.getBoolean("all_fields", false))
                                                                Snackbar.make(view, "Please complete your profile before registering for an event", Snackbar.LENGTH_SHORT).show();
                                                            else if (!isConnected()) {
                                                                Snackbar.make(layout, "Not connected to the internet", Snackbar.LENGTH_LONG)
                                                                        .setAction("Settings", new View.OnClickListener() {
                                                                            @Override
                                                                            public void onClick(View v) {
                                                                                startActivity(new Intent(Settings.ACTION_SETTINGS));
                                                                            }
                                                                        }).show();
                                                            } else {
                                                                try {
                                                                    final String get_url = getString(R.string.endpoint) +
                                                                            "/api_register?" + "full_name=" +
                                                                            URLEncoder.encode(prefs.getString("name", ""), "utf-8") +
                                                                            "&contact=" +
                                                                            URLEncoder.encode(prefs.getString("phone", ""), "utf-8") +
                                                                            "&email_id=" +
                                                                            URLEncoder.encode(prefs.getString("email", ""), "utf-8") +
                                                                            "&college=" +
                                                                            URLEncoder.encode(prefs.getString("college", ""), "utf-8") +
                                                                            "&events=" +
                                                                            URLEncoder.encode(Integer.toString(event.getInt("id")), "utf-8");
                                                                    Log.v("", get_url);
                                                                    final String finalEvent_id = event_id;
                                                                    new AsyncTask<JSONObject, Void, String>() {
                                                                        final Snackbar snackbar = Snackbar.make(view, "Registering", BaseTransientBottomBar.LENGTH_INDEFINITE);
                                                                        URL url = new URL(get_url);

                                                                        @Override
                                                                        protected void onPreExecute() {
                                                                            snackbar.show();
                                                                        }

                                                                        @Override
                                                                        protected String doInBackground(JSONObject... params) {
                                                                            try {
                                                                                Log.v("", "doInBackground");
                                                                                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                                                                                connection.setDoInput(true);
                                                                                connection.setDoOutput(true);
                                                                                int responseCode = connection.getResponseCode();
                                                                                if (responseCode == HttpURLConnection.HTTP_OK) {
                                                                                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                                                                                    StringBuilder sb = new StringBuilder();
                                                                                    String line;
                                                                                    while ((line = in.readLine()) != null) {
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
                                                                            if (result.contains("Successful!")) {
                                                                                Snackbar.make(view, "Successful", Snackbar.LENGTH_SHORT).show();
                                                                                registered_events.add(finalEvent_id);
                                                                                prefs.edit().putStringSet("events", registered_events).apply();
                                                                            } else
                                                                                Snackbar.make(view, "Failed", Snackbar.LENGTH_SHORT).show();
                                                                        }
                                                                    }.execute();
                                                                } catch (Exception e) {
                                                                    e.printStackTrace();
                                                                }
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
                                event_list.smoothScrollToPosition(0);
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