package com.dtu.innova.innova2016;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;


public class GalleryActivity extends AppCompatActivity {
    RecyclerView galleryView;
    int[] images = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d,
            R.drawable.e, R.drawable.f, R.drawable.g, R.drawable.h, R.drawable.i,
            R.drawable.j, R.drawable.k, R.drawable.l, R.drawable.m, R.drawable.n, R.drawable.o};
    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            GalleryRecyclerAdapter.GalleryViewHolder holder = (GalleryRecyclerAdapter.GalleryViewHolder) view.getTag();
            int position = holder.getAdapterPosition();
            Intent intent = new Intent(getApplicationContext(), FullscreenGallery.class);
            intent.putExtra("images", images);
            intent.putExtra("index", position);
            startActivity(intent);
            //Snackbar.make(view, "Clicked at : " + position, Snackbar.LENGTH_LONG).show();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        galleryView = (RecyclerView) findViewById(R.id.gallery_recycler);
        galleryView.setHasFixedSize(true);
        galleryView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        galleryView.setAdapter(new GalleryRecyclerAdapter());
    }
    public class GalleryRecyclerAdapter extends RecyclerView.Adapter<GalleryRecyclerAdapter.GalleryViewHolder>{

        @Override
        public GalleryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.gallery_recycler_item, null);
            return new GalleryViewHolder(view);
        }

        @Override
        public void onBindViewHolder(GalleryViewHolder holder, int position) {
            if(holder.image != null) {
                Picasso.with(getApplicationContext()).load(images[position]).fit().into(holder.image);
                holder.image.setOnClickListener(clickListener);
                holder.image.setTag(holder);
            }
            else
                Log.v(this.getClass().getSimpleName(), "Holder Image is null");
            //holder.image.setImageResource(images[position]);
        }

        @Override
        public int getItemCount() {
            return images.length;
        }

        public class GalleryViewHolder extends RecyclerView.ViewHolder {
            ImageView image;
            public GalleryViewHolder(View itemView) {
                super(itemView);
                image = (ImageView) itemView.findViewById(R.id.imageView);
            }
        }
    }
}
