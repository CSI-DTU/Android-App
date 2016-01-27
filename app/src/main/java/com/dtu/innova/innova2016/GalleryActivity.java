package com.dtu.innova.innova2016;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import andy.ayaseruri.lib.CircularRevealActivity;
import jp.wasabeef.recyclerview.adapters.SlideInRightAnimationAdapter;

public class GalleryActivity extends AppCompatActivity {
    RecyclerView galleryView;
    int[] images = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d,
            R.drawable.e, R.drawable.f, R.drawable.g, R.drawable.h, R.drawable.i,
            R.drawable.j, R.drawable.k, R.drawable.l, R.drawable.m, R.drawable.n, R.drawable.o};
    GalleryRecyclerAdapter adapter;
    @Override
    protected void onResume() {
        super.onResume();
        adapter.setOnItemClickListener(new GalleryRecyclerAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent intent = new Intent(getApplicationContext(), FullscreenGallery.class);
                intent.putExtra("images", images);
                intent.putExtra("index", position);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        galleryView = (RecyclerView) findViewById(R.id.gallery_recycler);
        galleryView.setHasFixedSize(true);
        galleryView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        adapter = new GalleryRecyclerAdapter(getApplicationContext(), images);
        SlideInRightAnimationAdapter animationAdapter = new SlideInRightAnimationAdapter(adapter);
        animationAdapter.setDuration(1000);
        galleryView.setAdapter(animationAdapter);
    }
}
