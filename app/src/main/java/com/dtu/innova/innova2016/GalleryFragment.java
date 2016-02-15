package com.dtu.innova.innova2016;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jp.wasabeef.recyclerview.adapters.SlideInRightAnimationAdapter;

/**
 * Created by Arpit on 2/14/2016.
 */
public class GalleryFragment extends Fragment {
    RecyclerView galleryView;
    int[] images = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d,
            R.drawable.e, R.drawable.f, R.drawable.g, R.drawable.h, R.drawable.i,
            R.drawable.j, R.drawable.k, R.drawable.l, R.drawable.m, R.drawable.n, R.drawable.o};
    GalleryRecyclerAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_gallery, null);
        galleryView = (RecyclerView) view.findViewById(R.id.gallery_recycler);
        galleryView.setHasFixedSize(true);
        galleryView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapter = new GalleryRecyclerAdapter(getContext(), images);
        SlideInRightAnimationAdapter animationAdapter = new SlideInRightAnimationAdapter(adapter);
        animationAdapter.setDuration(1000);
        galleryView.setAdapter(animationAdapter);
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        adapter.setOnItemClickListener(new GalleryRecyclerAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent intent = new Intent(getContext(), FullscreenGallery.class);
                intent.putExtra("images", images);
                intent.putExtra("index", position);
                startActivity(intent);
            }
        });
    }
}
