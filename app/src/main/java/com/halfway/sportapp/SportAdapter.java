package com.halfway.sportapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class SportAdapter extends RecyclerView.Adapter<SportAdapter.CategoryViewHolder>{

    private List<CategoryItem> list;
    Context context;

    public SportAdapter(Context context, List<CategoryItem> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public SportAdapter.CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CategoryViewHolder vh;
        View v1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.sport_item, parent, false);
        vh = new CategoryViewHolder(v1);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull SportAdapter.CategoryViewHolder holder, final int position) {
                final CategoryItem category = (CategoryItem) list.get(position);
                holder.tvTitle.setText(category.getEvents().get(position).getTitle());
                holder.tvCoefficient.setText(category.getEvents().get(position).getCoefficient());
                holder.tvTime.setText(category.getEvents().get(position).getTime());
                String strPlace = category.getEvents().get(position).getPlace();
                String []splitArray = strPlace.split(":");
                if (splitArray[1].equals(" ")) {
                    holder.tvPlace.setVisibility(View.INVISIBLE);
                } else {
                    holder.tvPlace.setText(category.getEvents().get(position).getPlace());
                }
                holder.tvPreview.setText(category.getEvents().get(position).getPreview());
                holder.llCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, ArticleActivity.class);
                        intent.putExtra("article", category.getEvents().get(position).getArticle());
                        intent.putExtra("title", category.getEvents().get(position).getTitle());
                        context.startActivity(intent);
                    }
                });
        }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvCoefficient;
        TextView tvTime;
        TextView tvPlace;
        TextView tvPreview;
        LinearLayout llCard;

        public CategoryViewHolder(View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvCoefficient = (TextView) itemView.findViewById(R.id.tv_coefficient);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
            tvPlace = (TextView) itemView.findViewById(R.id.tv_place);
            tvPreview = (TextView) itemView.findViewById(R.id.tv_preview);
            llCard = (LinearLayout) itemView.findViewById(R.id.ll_card);
        }
    }
}
