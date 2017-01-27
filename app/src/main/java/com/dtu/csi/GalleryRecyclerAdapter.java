package com.dtu.csi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

class GalleryRecyclerAdapter extends RecyclerView.Adapter<GalleryRecyclerAdapter.GalleryViewHolder> {
    private Context context;
    private String[] images;
    private MyClickListener myClickListener;
    GalleryRecyclerAdapter(Context context, String[] images) {
        this.context = context;
        this.images = images;
    }
    interface MyClickListener {
        void onItemClick(int position, View v);
    }
    void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }
    @Override
    public GalleryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.gallery_recycler_item, null);
        return new GalleryViewHolder(view);
    }
    @Override
    public void onBindViewHolder(GalleryViewHolder holder, int position) {
        if(holder.image != null) {
            Glide.with(context).load(context.getString(R.string.endpoint) + "/static/cogenesis/" + images[position] + ".jpeg").diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.image);
        }
        else
            Log.v(this.getClass().getSimpleName(), "Holder Image is null");
    }
    @Override
    public int getItemCount() {
        return images.length;
    }
    class GalleryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView image;
        GalleryViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }
}
