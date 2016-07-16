package com.teamtoriden.photome.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.teamtoriden.photome.Activity.IntroduceActivity;
import com.teamtoriden.photome.Activity.MainActivity;

import com.teamtoriden.photome.Adapter.PlaceAdapter;
import com.teamtoriden.photome.Class.Place;
import com.teamtoriden.photome.Class.RecyclerItemClickListener;
import com.teamtoriden.photome.R;

import java.util.ArrayList;
import java.util.List;


public class MainFragment extends Fragment implements OnMapReadyCallback {
    private GoogleMap mMap;
    private View view;
    private LatLng myLocation;
    private RecyclerView recyclerView;
    private List<Place> placeList = new ArrayList<>();
    private PlaceAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private markOnMap sample[]; //샘플마커

    private Location location; //mylocation

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private Context context;
    private boolean flag;

    public MainFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_main, container, false);

        context = view.getContext();


        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.main_recycler_view);

        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);
        // specify an adapter (see also next example)
        mAdapter = new PlaceAdapter(placeList);
        mRecyclerView.setAdapter(mAdapter);


        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(context, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        TextView nameTemp = (TextView) view.findViewById(R.id.name);
                        TextView descriptionTemp = (TextView) view.findViewById(R.id.description);
                        //ImageView imageViewtemp = (ImageView) view.findViewById(R.id.content_image);


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
        // specify an adapter (see also next example)
        
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("places");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                
                placeList.clear();
                for (DataSnapshot child : dataSnapshot.getChildren()) {

                    Log.d("work", "wow");
                    Place place = child.getValue(Place.class);
                    int id = context.getResources().getIdentifier(place.getImage(), "drawable", context.getPackageName());
                    place.setId(id);
                    placeList.add(place);
                    place.setDistnace(calDistance(location.getLatitude(),location.getLongitude(),place.getX(),place.getY()));
                    placeList.add(place);
                    mMap.addMarker(new MarkerOptions() //예제
                            .position(new LatLng(place.getX(),place.getY()))
                            .title((place.getName())));
                    mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                        @Override
                        public boolean onMarkerClick(Marker marker) {

                            return false;
                        }

                    });
                }
            }


            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("", "Failed to read value.", error.toException());
            }
        }

        );
        SupportMapFragment mapFragment = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map));
        mapFragment.getMapAsync(this);
        mAdapter = new PlaceAdapter(placeList);
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        if(mMap != null)
//        {
//            getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.map)).commit();
//            mMap = null;
//        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
        }


        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        double lat = location.getLatitude();
        double lng = location.getLongitude();
        LatLng cameraLatlng = new LatLng(lat, lng);
        mMap.setMyLocationEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cameraLatlng, 12));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 1500, null);
        // Add a marker in Sydney and move the camera
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    // 상태가 변경되었을 시 ( ex. gps->3g )
    public double calDistance(double lat1, double lon1, double lat2, double lon2){

        double theta, dist;
        theta = lon1 - lon2;
        dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);

        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;    // 단위 mile 에서 km 변환.
        dist = dist * 1000.0;      // 단위  km 에서 m 로 변환

        return dist;
    }

        // 주어진 도(degree) 값을 라디언으로 변환
        private double deg2rad(double deg){
            return (double)(deg * Math.PI / (double)180d);
        }

        // 주어진 라디언(radian) 값을 도(degree) 값으로 변환
        private double rad2deg(double rad){
            return (double)(rad * (double)180d / Math.PI);
        }

}

class markOnMap {//맵에 마크 객체
    private double markLat; //위도
    private double markLng; //경도
    private String markTitle; //타이틀

    public double getMarkLat() {
        return markLat;
    }

    public void setMarkLat(double markLat) {
        this.markLat = markLat;
    }

    public double getMarkLng() {
        return markLng;
    }

    public void setMarkLng(double markLng) {
        this.markLng = markLng;
    }

    public LatLng getLatlng() {
        return new LatLng(markLat, markLng);
    }

    public String getMarkTitle() {
        return markTitle;
    }

    public void setMarkTitle(String markTitle) {
        this.markTitle = markTitle;
    }

    public markOnMap(double markLat, double markLng, String markTitle) {
        this.markLat = markLat;
        this.markLng = markLng;
        this.markTitle = markTitle;
    }

}