package com.dtu.csi;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dexafree.materialList.card.Card;
import com.dexafree.materialList.card.CardProvider;
import com.dexafree.materialList.card.OnActionClickListener;
import com.dexafree.materialList.card.action.TextViewAction;
import com.dexafree.materialList.view.MaterialListView;
import com.victor.loading.rotate.RotateLoading;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EventsFragment2 extends Fragment {
    RotateLoading spinner;
    MaterialListView event_list;

    public EventsFragment2() {}

    public static EventsFragment2 newInstance() {
        EventsFragment2 fragment = new EventsFragment2();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_events_fragment2, container, false);
        spinner = (RotateLoading) layout.findViewById(R.id.spinner);
        event_list = (MaterialListView) layout.findViewById(R.id.event_list);

        RequestQueue rq = Volley.newRequestQueue(this.getContext());
        String url = getString(R.string.endpoint) + "events";

        JsonArrayRequest request = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("", response.toString());
                        for(int i = 0 ; i < response.length() ; i++) {
                            try {
                                JSONObject event = response.getJSONObject(i);
                                Card card = new Card.Builder(getContext())
                                        .setTag("Event")
                                        .withProvider(new CardProvider<>())
                                        .setLayout(R.layout.material_big_image_card_layout)
                                        .setTitle(event.getString("name"))
                                        .setDescription(event.getString("description"))
                                        .setDrawable(event.getString("thumbnail"))
                                        .setSubtitle(event.getString("location_time"))
                                        .addAction(R.id.left_text_button, new TextViewAction(getContext())
                                                .setText("Register")
                                                .setTextResourceColor(R.color.navy)
                                                .setListener(new OnActionClickListener() {
                                                    @Override
                                                    public void onActionClicked(View view, Card card) {
                                                        /**
                                                         * TO-DO Complete Registration
                                                         */
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