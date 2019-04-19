package com.python.cat.splash;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.Locale;

public class PagerAdapter extends RecyclerView.Adapter<PagerAdapter.VH> {

    private final Splash bean;

    PagerAdapter(Splash bean) {
        this.bean = bean;
        if (bean.error) {
            bean.results = Collections.emptyList();
        }
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemV = inflater.inflate(R.layout.item_splash, parent, false);
        return new VH(itemV);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {

        if (bean.error) {
            holder.img.setImageResource(R.drawable.ic_sentiment_dissatisfied_black_24dp);
            holder.tv.setText(String.format(Locale.getDefault(), "%s/%s", 1, 1));
        } else {
            Glide.with(holder.img.getContext())
                    .load(bean.results.get(position).url)
                    .centerCrop()
                    .dontAnimate()
                    .into(holder.img);

            String text = String.format(Locale.getDefault(), "%s/%s",
                    position + 1, bean.results.size());
            holder.tv.setText(text);
        }
    }

    @Override
    public int getItemCount() {
        return bean.results.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        ImageView img;
        TextView tv;

        VH(@NonNull View itemView) {
            super(itemView);
            this.img = itemView.findViewById(R.id.splash_image);
            this.tv = itemView.findViewById(R.id.pager_indicator);
        }
    }
}
