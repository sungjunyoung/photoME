package com.teamtoriden.photome.Fragments;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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

    public MyplaceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        collectionList.add(new Place("올림픽경기장","올림픽 경기를 합니다"));
        collectionList.add(new Place("올림픽경기장1","올림픽 경기를 합니다3"));
        collectionList.add(new Place("올림픽경기장2","올림픽 경기를 합니다3"));
        collectionList.add(new Place("올림픽경기장3","올림픽 경기를 합니다3"));
        collectionList.add(new Place("올림픽경기장4","올림픽 경기를 합니다3"));

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_myplace, container, false);
        Context context = view.getContext();
        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);
        // specify an adapter (see also next example)

        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(context, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        TextView nameTemp = (TextView) view.findViewById(R.id.name);
                        TextView descriptionTemp = (TextView) view.findViewById(R.id.description);


                        Intent intent = new Intent(getActivity(), IntroduceActivity.class);

                        intent.putExtra("name",nameTemp.getText().toString());
                        intent.putExtra("description",descriptionTemp.getText().toString());

                        startActivity(intent);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

        mAdapter = new CollectionAdapter(collectionList);
        mRecyclerView.setAdapter(mAdapter);


        return view;
    }


}
