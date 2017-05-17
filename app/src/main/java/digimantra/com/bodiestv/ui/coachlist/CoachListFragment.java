package digimantra.com.bodiestv.ui.coachlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;
import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.poliveira.parallaxrecyclerview.ParallaxRecyclerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import digimantra.com.bodiestv.Adapters.CoachListAdapter;
import digimantra.com.bodiestv.R;
import digimantra.com.bodiestv.customview.SelectorWidget;
import digimantra.com.bodiestv.networking.RestClient;
import digimantra.com.bodiestv.networking.responses.coaches.Coach;
import digimantra.com.bodiestv.networking.responses.coaches.CoachListResponse;
import digimantra.com.bodiestv.ui.coachdetails.CoachDetailsActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by shivam on 2/9/16.
 */
public class CoachListFragment extends Fragment implements OnMapReadyCallback, ParallaxRecyclerAdapter.OnClickEvent, ParallaxRecyclerAdapter.OnParallaxScroll, View.OnTouchListener {

    @BindView(R.id.list)
    SuperRecyclerView recyclerView;

    private GoogleMap mMap;

    RelativeLayout mapsLayout;

    private ClusterManager<MyItem> mClusterManager;
    private CoachListAdapter adapter;
    private CoachListResponse coaches;
    private View headerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.coach_list_fragment,container,false);
         ButterKnife.bind(this,view);
         return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.getRecyclerView().setOnTouchListener(CoachListFragment.this);

        headerView = LayoutInflater.from(getActivity()).inflate(R.layout.maps_view_parallax_part,recyclerView, false);

        mapsLayout = (RelativeLayout)headerView.findViewById(R.id.maps_layout);
        ImageView imageView = (ImageView)headerView.findViewById(R.id.transparent_imageview);
        fixMapScroll(imageView);



//        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
        fetchCoaches();


    }

    private void setUpMap() {
        SupportMapFragment mapFragment = new SupportMapFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.map,mapFragment);
        transaction.commit();
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onClick(View view, int i) {
        Coach coach = coaches.getCoach().get(i);
        Intent intent = new Intent(getActivity(), CoachDetailsActivity.class);
        intent.putExtra(CoachDetailsActivity.COACH_DATA,coach);
        startActivity(intent);
    }

    @Override
    public void onParallaxScroll(float percentage, float offset, View parallax) {

//        Log.d("TAG", String.valueOf(percentage));

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }

    public class MyItem implements ClusterItem {
        private final LatLng mPosition;

        public MyItem(double lat, double lng) {
            mPosition = new LatLng(lat, lng);
        }

        @Override
        public LatLng getPosition() {
            return mPosition;
        }
    }


    private void fixMapScroll(ImageView imageView) {
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
//                Toast.makeText(getApplicationContext(), "TOUCHED", Toast.LENGTH_LONG).show();
                int action = motionEvent.getAction();
                switch (action){
                    case MotionEvent.ACTION_DOWN:
                        recyclerView.getRecyclerView().requestDisallowInterceptTouchEvent(true);
                        return false;
                    case MotionEvent.ACTION_UP:
                        recyclerView.getRecyclerView().requestDisallowInterceptTouchEvent(false);
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        recyclerView.getRecyclerView().requestDisallowInterceptTouchEvent(true);
                        return false;
                    default:
                        return true;
                }
            }
        });
    }


    private void fetchCoaches() {
        RestClient.get().getCoachList().enqueue(new Callback<CoachListResponse>() {
            @Override
            public void onResponse(Call<CoachListResponse> call, Response<CoachListResponse> response) {

                CoachListFragment.this.coaches = response.body();

                adapter = new CoachListAdapter(response.body().coach);
                recyclerView.setAdapter(adapter);
                adapter.setParallaxHeader(headerView , recyclerView.getRecyclerView());
                adapter.setOnParallaxScroll(CoachListFragment.this);
                adapter.setOnClickEvent(CoachListFragment.this);
                adapter.setScrollMultiplier(0.6f);
                setUpMap();

            }

            @Override
            public void onFailure(Call<CoachListResponse> call, Throwable t) {

            }
        });
    }


    private void addItems() {

        // Set some lat/lng coordinates to start with.
        for(Coach coach : coaches.getCoach()){
            Log.d("TAG",coaches.toString());
            if(coach!=null)
            mClusterManager.addItem(new MyItem(Double.parseDouble(coach.getUserAddressList().get(0).getLatitude()),
                   Double.parseDouble(coach.getUserAddressList().get(0).getLongitude())));
        }


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        setUpClusterer();
    }

    private void setUpClusterer() {
        // Declare a variable for the cluster manager.


        // Position the map.
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(51.503186, -0.126446), 10));

        // Initialize the manager with the context and the map.
        // (Activity extends context, so we can pass 'this' in the constructor.)
        mClusterManager = new ClusterManager<MyItem>(getActivity(), mMap);

        // Point the map's listeners at the listeners implemented by the cluster
        // manager.

        mMap.setOnCameraChangeListener(mClusterManager);

        mMap.setOnMarkerClickListener(mClusterManager);
        addItems();

    }

}
