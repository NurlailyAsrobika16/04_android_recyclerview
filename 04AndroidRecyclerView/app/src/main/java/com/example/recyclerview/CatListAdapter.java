package com.example.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CatListAdapter extends RecyclerView.Adapter<CatListAdapter.CatViewHolder>{
    private ArrayList<CatData> CatList;
    private LayoutInflater mInflater;
    private View.OnClickListener mOnItemClickListener;

    public CatListAdapter(Context context, ArrayList<CatData> CatList){
        mInflater = LayoutInflater.from(context);
        this.CatList = CatList;
    }

    @NonNull
    @Override
    public CatListAdapter.CatViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mItemView = mInflater.inflate(R.layout.item_list, viewGroup, false);
        return new CatViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CatListAdapter.CatViewHolder CatViewHolder, int position) {
        CatViewHolder.name.setText((CatList.get(position)).getName());
        CatViewHolder.description.setText((CatList.get(position)).getDescription());
        Glide.with(CatViewHolder.itemView)
                .load(CatList.get(position).getImage())
                .override(100, 150)
                .into(CatViewHolder.image);
    }


    @Override
    public int getItemCount() {
        return CatList.size();
    }

    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    class CatViewHolder extends RecyclerView.ViewHolder {
        TextView name, description;
        ImageView image;


        public CatViewHolder(View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.Cat_name);
            description = itemView.findViewById(R.id.cat_description);
            image = itemView.findViewById(R.id.cat_image);
            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }
    }
}
