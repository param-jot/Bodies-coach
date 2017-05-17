package digimantra.com.bodiestv.ui.coachlist;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
import butterknife.OnClick;
import digimantra.com.bodiestv.Adapters.CoachListAdapter;
import digimantra.com.bodiestv.customview.SelectorWidget;
import digimantra.com.bodiestv.networking.RestClient;
import digimantra.com.bodiestv.networking.responses.coaches.Coach;
import digimantra.com.bodiestv.networking.responses.coaches.CoachListResponse;
import digimantra.com.bodiestv.CommonClasses.BaseActivity;
import digimantra.com.bodiestv.R;
import digimantra.com.bodiestv.ui.coachdetails.CoachDetailsActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListMapsActivity extends BaseActivity implements RefineSortFragment.RefineSortFragmentCallbacks, RefineFiltersFragment.RefineFilterCallbacks{





    private GoogleMap mMap;


    ImageView collapseButton;
    RelativeLayout mapsLayout;



//    @BindView(R.id.open_segmented_view)
//    ImageView segmentImageViewArrow;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;


    @BindView(R.id.selectorWidget)
    SelectorWidget selectionWidget;


    private CoachListAdapter adapter;
    private CoachListResponse coaches;

//    @BindView(R.id.refine_fragment)
//    RelativeLayout refineFragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_list_maps);
        ButterKnife.bind(this);

        //findViewById(R.id.scroll_holder).setVisibility(View.GONE);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_maps);
        setSupportActionBar(toolbar);
        TextView heading = (TextView) toolbar.findViewById(R.id.text_view_toolbar);
        heading.setText("Yoga");
        TextView headingBold = (TextView) toolbar.findViewById(R.id.text_view_toolbar_bold);
        headingBold.setText("Coaches");

        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_keyboard_arrow_left_black_24dp);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        onSortRequested();


        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);



        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        selectionWidget.setListener(new SelectorWidget.OnSelectedListener() {
            @Override
            public void onSelect(String value) {

            }
        });

        showCoachFragment();
    }

    private void showCoachFragment() {
        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.listing_fragment_container,new CoachListFragment()).commit();

    }


    private void setUpAdpater() {
//         adapter = new CoachListAdapter(new ArrayList<String>(), new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent seeCoach = new Intent(CoachListMapsActivity.this, CoachDetailsActivity.class);
//                startActivity(seeCoach);
//            }
//        });




    }




    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */








    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         getMenuInflater().inflate(R.menu.menu_coach_refine,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.refine_results:
                openRefineView();
                return true;
        }
        return false;
    }

    private void openRefineView() {

        drawerLayout.openDrawer(Gravity.RIGHT);

    }

    void replaceRefineFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.refine_fragment,fragment)
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .commit();
    }

    @Override
    public void onClosePressed() {
        drawerLayout.closeDrawer(Gravity.RIGHT);
    }

    @Override
    public void onSortRequested() {
        replaceRefineFragment(new RefineSortFragment());
    }

    @Override
    public void onFiltersRequested() {
        replaceRefineFragment(new RefineFiltersFragment());
    }






//    @OnClick(R.id.back_arrow)
//    void close(){
//        if(drawerLayout.isDrawerOpen(Gravity.RIGHT)){
//            drawerLayout.closeDrawer(Gravity.RIGHT);
//        }
//    }

//    @OnClick(R.id.open_segmented_view)
//    void openSegmentedView(){
//
//        if(fragSegment.getVisibility() == View.GONE){
//            segmentImageViewArrow.setImageResource(R.drawable.ic_expand_less_black_24dp);
//            fragSegment.setVisibility(View.VISIBLE);
//        }
//        else{
//            fragSegment.setVisibility(View.GONE);
//            segmentImageViewArrow.setImageResource(R.drawable.ic_expand_more_white_24dp);
//        }
//    }

}
