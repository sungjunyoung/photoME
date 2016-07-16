package com.teamtoriden.photome.Fragments;

import android.content.Context;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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
import com.teamtoriden.photome.Activity.MainActivity;
import com.teamtoriden.photome.Adapter.CollectionAdapter;
import com.teamtoriden.photome.Class.Place;
import com.teamtoriden.photome.R;

import java.util.ArrayList;
import java.util.List;


public class MainFragment extends Fragment implements OnMapReadyCallback {
    private GoogleMap mMap;
    private View view;
    private LatLng myLocation;

    private List<Place> placeList = new ArrayList<>();
    private CollectionAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private markOnMap sample[]; //샘플마커

    public MainFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_main, container, false);
        Context context = view.getContext();


        Drawable image1 = getResources().getDrawable(R.drawable.cotdeung);
        Drawable image2 = getResources().getDrawable(R.drawable.ganmonbeach);
        Drawable image3 = getResources().getDrawable(R.drawable.hanbandow);
        Drawable image4 = getResources().getDrawable(R.drawable.ongsim);
        Drawable image5 = getResources().getDrawable(R.drawable.skijump);

        placeList.add(new Place("올림픽경기장","올림픽 경기를 합니다",image1));
        placeList.add(new Place("올림픽경기장1","올림픽 경기를 합니다3", image2));
        placeList.add(new Place("올림픽경기장2","올림픽 경기를 합니다3", image3));
        placeList.add(new Place("올림픽경기장3","올림픽 경기를 합니다3", image4));
        placeList.add(new Place("올림픽경기장4","올림픽 경기를 합니다3", image5));

        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.main_recycler_view);

        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);
        // specify an adapter (see also next example)
        mAdapter = new CollectionAdapter(placeList);
        mRecyclerView.setAdapter(mAdapter);

        SupportMapFragment mapFragment =((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map));
        mapFragment.getMapAsync(this);
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
            ActivityCompat.requestPermissions(getActivity(),new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
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
        Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
        double lat = location.getLatitude();
        double lng = location.getLongitude();
        LatLng cameraLatlng = new LatLng(lat,lng);
        mMap.setMyLocationEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cameraLatlng, 12));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 1500, null);

        setMarker();
        // Add a marker in Sydney and move the camera
    }
    public void setMarker(){ //여기에 마커 하나씩 추가
        sample = new markOnMap[3];
        sample[0] = new markOnMap(37.508498, 127.045882,"선릉");
        sample[1] = new markOnMap(37.51013,127.0438243,"지하철역");
        sample[2] = new markOnMap(37.508632, 127.049052,"선정릉");
        for(int i=0;i<3;i++){
            mMap.addMarker(new MarkerOptions() //예제
                    .position(sample[i].getLatlng())
                    .title(sample[i].getMarkTitle()));
        }
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                return false;
            }

        });
    };
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


}
class markOnMap{//맵에 마크 객체
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
    public LatLng getLatlng(){
        return new LatLng(markLat,markLng);
    }
    public String getMarkTitle() {
        return markTitle;
    }

    public void setMarkTitle(String markTitle) {
        this.markTitle = markTitle;
    }
    public markOnMap(double markLat, double markLng, String markTitle){
        this.markLat = markLat;
        this.markLng = markLng;
        this.markTitle = markTitle;
    }

}