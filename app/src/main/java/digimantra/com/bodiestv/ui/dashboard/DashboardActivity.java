package digimantra.com.bodiestv.ui.dashboard;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.Menu;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import digimantra.com.bodiestv.ui.customworkout.ChooseActivityNew;
import digimantra.com.bodiestv.ui.coachlist.ListMapsActivity;
import digimantra.com.bodiestv.ui.customworkout.CustomView;
import digimantra.com.bodiestv.R;
import digimantra.com.bodiestv.models.User;
import digimantra.com.bodiestv.ui.LocationActivity;
import digimantra.com.bodiestv.utilities.GeoLocationUtils;

public class DashboardActivity extends LocationActivity implements NavbarDefaultFragment.NavBarDefaultCallbacks,NavbarSignedInFragment.NavbarSignedInCallbacks {

    @BindView(R.id.main_layout)
    RelativeLayout mainLayout;

    @BindView(R.id.current_location)
    TextView currentLoctaion;

    NavbarSignedInFragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView title = (TextView) toolbar.findViewById(R.id.text_view_toolbar);
        title.setText("Bodies");

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                mainLayout.setTranslationX(slideOffset * drawerView.getWidth());
                drawer.bringChildToFront(drawerView);
                drawer.requestLayout();
            }


        };

        toggle.setDrawerIndicatorEnabled(false);

        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.menu, this.getTheme());

        toggle.setHomeAsUpIndicator(drawable);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerVisible(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });

        drawer.setDrawerListener(toggle);
        toggle.syncState();


        ButterKnife.bind(this);
        setUpDrawer();
    }

    private void setUpDrawer() {
        if(!User.isUserLoggedIn()){
            showDrawerFragment(new NavbarDefaultFragment());
        }
        else{
            fragment = new NavbarSignedInFragment();
            showDrawerFragment(fragment);
        }
    }


    public void showDrawerFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.navigation_fragment,fragment).commit();
    }




    @Override
    public void onCurrentLocationAvailaible(LatLng latLng) {
        GeoLocationUtils.Location location = GeoLocationUtils.getGeoLocation(latLng, this);
        if(location!=null){
            currentLoctaion.setText(String.format("%s %s", location.getAddressLine(), location.getCity()));
            if(fragment!=null){
                fragment.setLocation(String.format("%s %s", location.getAddressLine(), location.getCity()));
            }
        }

    }


    @OnClick({R.id.yoga, R.id.pilates, R.id.zumba, R.id.cycling})
    void seeCoach() {
        startActivity(new Intent(this, ListMapsActivity.class));
    }

    @OnClick(R.id.more_activity)
    void seeMoreActivities() {
        startActivity(new Intent(this, ChooseActivityNew.class));
    }

    @OnClick(R.id.create_own_workout_button)
    void create() {
        startActivity(new Intent(this, CustomView.class));
    }

//    @OnClick(R.id.location_viewer_dashboard)
//    public void showLocationFragment(){
//        SelectLocationFragment dialogFragment = new SelectLocationFragment();
//        dialogFragment.show(getSupportFragmentManager(), SelectLocationFragment.class.getCanonicalName());
//    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
//        return true;
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dashboard,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemSelected() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
}


