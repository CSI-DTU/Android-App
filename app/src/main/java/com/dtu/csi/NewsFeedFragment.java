package com.dtu.csi;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

public class NewsFeedFragment extends Fragment {
    RotateLoading spinner;
    MaterialListView feed_list;

    public NewsFeedFragment() {
    }

    public static NewsFeedFragment newInstance() {
        return new NewsFeedFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_events_fragment2, container, false);
        spinner = (RotateLoading) layout.findViewById(R.id.spinner);
        spinner.start();
        feed_list = (MaterialListView) layout.findViewById(R.id.event_list);
        SharedPreferences prefs = getActivity().getSharedPreferences("creds", 0);
        RequestQueue rq = Volley.newRequestQueue(this.getContext());
        String url = getString(R.string.endpoint) + "/news/0";
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
                                        .setLayout(R.layout.material_basic_buttons_card)
                                        .setTitle(event.getString("title"))
                                        .setTitleResourceColor(R.color.black)
                                        .setDescription(event.getString("description"))
                                        .setDescriptionResourceColor(R.color.black)
//                                        .setDrawable(R.drawable.dog)
//                                        .setDrawableConfiguration(new CardProvider.OnImageConfigListener() {
//                                            @Override
//                                            public void onImageConfigure(@NonNull RequestCreator requestCreator) {
//                                                requestCreator.fit();
//                                            }
//                                        })
                                        .endConfig()
                                        .build();
                                feed_list.getAdapter().add(card);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        spinner.stop();
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
