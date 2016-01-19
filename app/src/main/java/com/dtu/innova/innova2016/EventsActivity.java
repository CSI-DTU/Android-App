package com.dtu.innova.innova2016;

import android.graphics.Rect;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import worldline.com.foldablelayout.FoldableLayout;

public class EventsActivity extends AppCompatActivity {
    RecyclerView eventsRecycler;
    public String[] headers = {"Structural Design Event", "Junk-ineers", "Bridge making Event", "Disaster Management",
                                "CAD Craft", "Robotics", "Dream City", "Contraption", "Find D Bug", "Panorama", "Guest Lectures",
                                "Air Gliding Challenge", "Bizzeal"};
    public int[] headersImages = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d,
            R.drawable.e, R.drawable.f, R.drawable.g, R.drawable.h, R.drawable.i,
            R.drawable.j, R.drawable.k, R.drawable.l, R.drawable.m};
    public String[] details = {"The Structural Design event where the students are given an \n" +
            "opportunity of onsite architectural designing. Participants have \n" +
            "to tackle every problem which an architect would face.\n",
    "The all-time favourite of the technical/creative minds of our\n" +
            "junk-ineers. This time junk-ineers are provided with a virtual\n" +
            "budget in the beginning, which is followed by a bidding\n" +
            "event for different junks they will use in their model.\n",
    "This is a dream-come-true event for a structural engineer.\n" +
            "Before bridges are actually constructed, their models are to\n" +
            "be made from paper and glue. The strength and aesthetic\n" +
            "appeal of these models adjudge the design of the actual\n" +
            "structure.",
    "Any structure should be able to hold itself in disastrous\n" +
            "conditions. The objective is to examine their techniques\n" +
            "of building prototypes which resist disastrous conditions.",
    "It’s all about creating designs using computerised\n" +
            "techniques on a given problem statement. This event\n" +
            "aims at testing the imagination and spontaneity of the\n" +
            "participants using mind-boggling questions and their\n" +
            "equally innovative results.",
    "The robotics fiesta based on different design challenges in land\n" +
            "and water, wherein teams from various colleges compete with\n" +
            "their innovative bots. This mega event includes four varied\n" +
            "categories viz. Robotron, Robocruz, Robochase and Robomaze. \n",
    "Today every city in the world is facing a traffic and infrastructural\n" +
            "problem; this event’s main aim is to provide an opportunity for\n" +
            "the best designers/planners to prove their mettle and build their\n" +
            "own city.",
    "It is based on the genesis of energy; it entails the understanding of\n" +
            "complex machinery and the ways they can meditate energy via the\n" +
            "primate resource.\n",
    "The participant will be asked to point out the anomalies in a given\n" +
            "C++ code in fifteen minutes. The winners of Round A will be asked\n" +
            "to find the bugs in a Java code.",
    "A symposium of industry pioneers, academics and students.\n" +
            "It provides the students with an opportunity to appreciate\n" +
            "the achievements of those in the industry who are leaders\n" +
            "in their own right.",
    "Innova brings to you an opportunity to interact with personalities\n" +
            "sans pareil from across the world. We invite you to witness\n" +
            "sheer awesomeness - the pioneers of revolution are here, at DTU.",
    "The biggest news-maker of Innova ‘15 is back where the students\n" +
            "are set on a time-bound challenge to make hand-launched gliders.\n" +
            "These crafts are then launched from a high-rise and the teams\n" +
            "score on the basis of glide time and range.",
    "A magnificient platform for budding entrepreneurs to present\n" +
            "their B-plans corresponding to a given theme. The participating\n" +
            "teams need to pass through a series of hurdles in order to\n" +
            "establish a company."};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        eventsRecycler = (RecyclerView) findViewById(R.id.events_recycler);
        CustomAdapter customAdapter = new CustomAdapter(headers, details, headersImages);
        eventsRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        eventsRecycler.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = getResources().getDimensionPixelSize(R.dimen.activity_vertical_margin);
            }
        });
        eventsRecycler.setAdapter(customAdapter);
    }
    public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder>{
        private Map<Integer, Boolean> mFoldStates = new HashMap<>();
        public String[] dataset;
        public String[] detailset;
        public int[] headerImages;

        public CustomAdapter(String[] headers, String[] detailset, int[] headerImages) {
            this.dataset = headers;
            this.detailset = detailset;
            this.headerImages = headerImages;
        }

        @Override
        public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new CustomViewHolder(new FoldableLayout(getApplicationContext()));
        }

        @Override
        public void onBindViewHolder(final CustomViewHolder holder, int position) {
            holder.eventHeader.setText(dataset[position]);
            holder.eventDetail.setText(detailset[position]);
            Picasso.with(getApplicationContext()).load(headerImages[position]).fit().into(holder.imageHeader);
            if(mFoldStates.containsKey(position)) {
                if(mFoldStates.get(position) == Boolean.TRUE) {
                    if(!holder.foldableLayout.isFolded())
                        holder.foldableLayout.foldWithoutAnimation();
                } else if(mFoldStates.get(position) == Boolean.FALSE) {
                    if(holder.foldableLayout.isFolded())
                        holder.foldableLayout.unfoldWithoutAnimation();
                }
            } else
                holder.foldableLayout.foldWithoutAnimation();
            holder.foldableLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.foldableLayout.isFolded()) {
                        holder.foldableLayout.unfoldWithAnimation();
                    } else {
                        holder.foldableLayout.foldWithAnimation();
                    }
                }
            });
            holder.foldableLayout.setFoldListener(new FoldableLayout.FoldListener() {
                @Override
                public void onUnFoldStart() {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        holder.foldableLayout.setElevation(5);
                    }
                }

                @Override
                public void onUnFoldEnd() {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        holder.foldableLayout.setElevation(0);
                    }
                    mFoldStates.put(holder.getAdapterPosition(), false);
                }

                @Override
                public void onFoldStart() {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        holder.foldableLayout.setElevation(5);
                    }
                }

                @Override
                public void onFoldEnd() {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        holder.foldableLayout.setElevation(0);
                    }
                    mFoldStates.put(holder.getAdapterPosition(), true);
                }
            });
        }

        @Override
        public int getItemCount() {
            return dataset.length;
        }

        public class CustomViewHolder extends RecyclerView.ViewHolder {
            protected FoldableLayout foldableLayout;
            @Bind(R.id.event_item_header)
            protected ImageView imageHeader;
            @Bind(R.id.event_headline)
            protected TextView eventHeader;
            @Bind(R.id.event_detail)
            protected TextView eventDetail;
            public CustomViewHolder(FoldableLayout foldableLayout) {
                super(foldableLayout);
                this.foldableLayout = foldableLayout;
                this.foldableLayout.setupViews(R.layout.event_item, R.layout.event_detail, R.dimen.height, getApplicationContext());
                ButterKnife.bind(this, foldableLayout);
            }
        }
    }
}
