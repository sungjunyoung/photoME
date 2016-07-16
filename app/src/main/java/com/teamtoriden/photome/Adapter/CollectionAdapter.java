package com.teamtoriden.photome.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.teamtoriden.photome.Class.Place;
import com.teamtoriden.photome.R;

import java.util.List;

/**
 * Created by Junyoung on 16. 7. 16..
 */
public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.ViewHolder> {

    private List<Place> collectionList;



    public CollectionAdapter(List<Place> collectionList) {
        this.collectionList = collectionList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name, description;
        public ImageView image;

        public ViewHolder(final View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            description = (TextView) itemView.findViewById(R.id.description);
            image = (ImageView) itemView.findViewById(R.id.image);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.mycollection_row,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Place collection = collectionList.get(position);
        holder.name.setText(collection.getName());
        holder.description.setText(collection.getDescription());
        holder.image.setImageResource(collection.getId());

    }

    @Override
    public int getItemCount() {
        return collectionList.size();
    }

}
