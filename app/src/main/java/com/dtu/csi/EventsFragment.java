package com.dtu.csi;

import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter;
import worldline.com.foldablelayout.FoldableLayout;

/**
 * Created by Arpit on 2/14/2016.
 */
public class EventsFragment extends Fragment {
    RecyclerView eventsRecycler;
    public String[] headers = {"Structure-D", "Junk-e-ard", "Sealink", "Disaster Management",
            "CADD Craft", "Robotics", "Dream City", "Contraption", "Find D Bug",
            "Panorama", "Guest Lectures", "Air Glider", "Bizzeal", "Deltech MUN",
            "Boomerang", "Electrified", "Clay Play", "Robo Race", "Robo Maze",
            "RoboWars", "Sydney Coathanger", "Consulting Knights", "Bid Masters",
            "Pantheon Cube", "Matrix Reinforcement"
    };
    public int[] headersImages = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d,
            R.drawable.e, R.drawable.f, R.drawable.g, R.drawable.h, R.drawable.i,
            R.drawable.j, R.drawable.k, R.drawable.l, R.drawable.m, R.drawable.n, R.drawable.o,
            R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d,
            R.drawable.e, R.drawable.f, R.drawable.g, R.drawable.h, R.drawable.i,
            R.drawable.j};
    public String[] details = {
                "The Structural Design event where the students are given an " +
                        "opportunity of onsite architectural designing. Participants have " +
                        "to tackle every problem which an architect would face.\n\n\nRitesh Kurar-9910057669\n" +
                        "Pankaj meena-9911262908",
                "The all-time favourite of the technical/creative minds of our " +
                        "junk-ineers. This time junk-ineers are provided with a virtual " +
                        "budget in the beginning, which is followed by a bidding " +
                        "event for different junks they will use in their model. ",
                "This is a dream-come-true event for a structural engineer. " +
                        "Before bridges are actually constructed, their models are to " +
                        "be made from paper and glue. The strength and aesthetic " +
                        "appeal of these models adjudge the design of the actual " +
                        "structure.",
                "Any structure should be able to hold itself in disastrous " +
                        "conditions. The objective is to examine their techniques " +
                        "of building prototypes which resist disastrous conditions.\n\n\nNitesh kumar-9899598727\n" +
                        "Atish kumar-9811911637",
                "It’s all about creating designs using computerised " +
                        "techniques on a given problem statement. This event " +
                        "aims at testing the imagination and spontaneity of the " +
                        "participants using mind-boggling questions and their " +
                        "equally innovative results.",
                "The robotics fiesta based on different design challenges in land " +
                        "and water, wherein teams from various colleges compete with " +
                        "their innovative bots. This mega event includes four varied " +
                        "categories viz. Robotron, Robocruz, Robochase and Robomaze.  ",
                "Today every city in the world is facing a traffic and infrastructural " +
                        "problem; this event’s main aim is to provide an opportunity for " +
                        "the best designers/planners to prove their mettle and build their " +
                        "own city.",
                "It is based on the genesis of energy; it entails the understanding of " +
                        "complex machinery and the ways they can meditate energy via the " +
                        "primate resource. ",
                "The participant will be asked to point out the anomalies in a given " +
                        "C++ code in fifteen minutes. The winners of Round A will be asked " +
                        "to find the bugs in a Java code.",
                "A symposium of industry pioneers, academics and students. " +
                        "It provides the students with an opportunity to appreciate " +
                        "the achievements of those in the industry who are leaders " +
                        "in their own right.",
                "Innova brings to you an opportunity to interact with personalities " +
                        "sans pareil from across the world. We invite you to witness " +
                        "sheer awesomeness - the pioneers of revolution are here, at DTU.",
                "The biggest news-maker of Innova ‘15 is back where the students " +
                        "are set on a time-bound challenge to make hand-launched gliders. " +
                        "These crafts are then launched from a high-rise and the teams " +
                        "score on the basis of glide time and range.",
                "A magnificient platform for budding entrepreneurs to present " +
                        "their B-plans corresponding to a given theme. The participating " +
                        "teams need to pass through a series of hurdles in order to " +
                        "establish a company.",
                "An academic simulation of United Nations committees where country delegations are " +
                        "reperesented by student individuals or groups. It involves intense " +
                        "discussion and critical analysis on international agendas and issues.",
                "The event where you have to show your aerodynamic skills and make a boomerang using " +
                        "wood.",
                "Use your brain and solve various electronics and logical puzzles.",
                "Show your skills and use clay to make an aerodynamic car to get through all the stages.",
                "The event where you show the speed of your robots. Got a robot? Get on and win the race.",
                "Its not just about speed, get through the hurdles and get out of the maze.",
                "It is the event where you get your robots in the ring and show whose robot is the best.",
                "This is a dream-come-true event for a structural engineer. Before bridges are actually constructed, " +
                        "their models are to be made from paper and glue. The strength and aesthetic appeal of these " +
                        "models adjudge the design of the actual structure.\n\n\nDinesh -9015840116\n" +
                        "Kabir -9871840684",
                "A magnificient platform for budding entrepreneurs to present their B-plans corresponding to a given theme. " +
                        "The participating teams need to pass through a series of hurdles in order to establish a company.",
                "Show your bidding skills and how much you know cricket.",
                "You'll make the best cube out of cement and be tested on your stability\n\n\nKartik bhola - 9582457688\n" +
                        "Kunal bhatia - 9811197761",
                "Build columns to support your matrix and build the strongest base.\n\n\nChit simran -9891879108\n" +
                        "Diksha-8377032008"
            };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_events, null);
        eventsRecycler = (RecyclerView) view.findViewById(R.id.events_recycler);
        EventsFragment.CustomAdapter customAdapter = new EventsFragment.CustomAdapter(headers, details, headersImages);
        SlideInBottomAnimationAdapter adapter = new SlideInBottomAnimationAdapter(customAdapter);
        adapter.setDuration(500);
        ScaleInAnimationAdapter adapter1 = new ScaleInAnimationAdapter(adapter);
        adapter1.setDuration(500);
        eventsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        eventsRecycler.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = getResources().getDimensionPixelSize(R.dimen.dim);
            }
        });
        eventsRecycler.setAdapter(adapter1);
        return view;
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
            return new CustomViewHolder(new FoldableLayout(getContext()));
        }

        @Override
        public void onBindViewHolder(final CustomViewHolder holder, int position) {
            holder.eventHeader.setText(dataset[position]);
            holder.eventDetail.setText(detailset[position]);
            Glide.with(getContext()).load(headerImages[position]).centerCrop().crossFade().into(holder.imageHeader);
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
                this.foldableLayout.setupViews(R.layout.event_item, R.layout.event_detail, R.dimen.height, getContext());
                ButterKnife.bind(this, foldableLayout);
            }
        }
    }
}
