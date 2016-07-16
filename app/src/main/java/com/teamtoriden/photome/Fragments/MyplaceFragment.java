package com.teamtoriden.photome.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DrawableUtils;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.teamtoriden.photome.Activity.IntroduceActivity;
import com.teamtoriden.photome.Adapter.CollectionAdapter;
import com.teamtoriden.photome.Class.Place;
import com.teamtoriden.photome.Class.RecyclerItemClickListener;
import com.teamtoriden.photome.R;

import java.util.ArrayList;
import java.util.List;

public class MyplaceFragment extends Fragment {

    private List<Place> collectionList = new ArrayList<>();

    private RecyclerView recyclerView;
    private CollectionAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    private Context context;

    public MyplaceFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_myplace, container, false);
        context = view.getContext();


        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);
        // specify an adapter (see also next example)

        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(context, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        TextView nameTemp = (TextView) view.findViewById(R.id.name);
                        TextView descriptionTemp = (TextView) view.findViewById(R.id.description);
                        ImageView imageViewtemp = (ImageView) view.findViewById(R.id.content_image);


                        Intent intent = new Intent(getActivity(), IntroduceActivity.class);

                        intent.putExtra("name", nameTemp.getText().toString());
                        intent.putExtra("description", descriptionTemp.getText().toString());


                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

        mAdapter = new CollectionAdapter(collectionList);
        mRecyclerView.setAdapter(mAdapter);


        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("places");
        myRef.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            // This method is called once with the initial value and again
                                            // whenever data at this location is updated.

                                            collectionList.clear();
                                            for (DataSnapshot child : dataSnapshot.getChildren()) {

                                                Log.d("work", "wow");
                                                Place place = child.getValue(Place.class);
                                                int id = context.getResources().getIdentifier(place.getImage(), "drawable", context.getPackageName());
                                                place.setId(id);

                                                collectionList.add(place);

                                            }
                                        }


                                        @Override
                                        public void onCancelled(DatabaseError error) {
                                            // Failed to read value
                                            Log.w("", "Failed to read value.", error.toException());
                                        }
                                    }

        );

        return view;
    }


}
