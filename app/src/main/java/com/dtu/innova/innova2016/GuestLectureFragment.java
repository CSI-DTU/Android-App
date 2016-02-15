package com.dtu.innova.innova2016;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

public class GuestLectureFragment extends Fragment {
    public static GuestLectureFragment newInstance(CharSequence description, int poster) {
        GuestLectureFragment fragment = new GuestLectureFragment();
        Bundle bundle = new Bundle();
        bundle.putCharSequence("description", description);
        bundle.putInt("poster", poster);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.lectures_fragment, null);
        CharSequence description = getArguments().getCharSequence("description");
        TextView desc = (TextView) root.findViewById(R.id.description);
        desc.setText(description);
        ImageView poster = (ImageView) root.findViewById(R.id.poster);
        Picasso.with(getContext()).load(getArguments().getInt("poster")).fit().into(poster);
        return root;
    }
}
