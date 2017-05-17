package digimantra.com.bodiestv.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import digimantra.com.bodiestv.R;
import digimantra.com.bodiestv.ui.onboard.LetsGetStarted;
import digimantra.com.bodiestv.utilities.MD5Util;
import digimantra.com.bodiestv.utilities.Preferences;

/**
 * Created by shivam on 21/8/16.
 */
public class NavbarSignedInFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.nav_view)
    NavigationView navigationView;
    private NavbarSignedInCallbacks callback;
    private TextView locationString;

//    @BindView(R.id.sign_up_button)




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.navbar_signed_in,container,false);
        ButterKnife.bind(this,view);
        return view;


    }



    @OnClick(R.id.log_out)
    void logout(){
        Preferences.getInstance().clearUserDetails();
        Intent goToGetStartedScreen = new Intent(getActivity(), LetsGetStarted.class);
        goToGetStartedScreen.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(goToGetStartedScreen);
        getActivity().finish();
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        View headerView = navigationView.getHeaderView(0);
        fillDetailsinHeader(headerView);


    }

    private void fillDetailsinHeader(View headerView) {
        TextView name = ButterKnife.findById(headerView,R.id.user_nav_name);
        name.setText(Preferences.getInstance().getName());
        CircularImageView imageView = ButterKnife.findById(headerView, R.id.user_image);
        if(Preferences.getInstance().getPic() == null || Preferences.getInstance().getPic().length() == 0){
            Log.d("Tag","https://www.gravatar.com/avatar/"+ MD5Util.md5Hex(Preferences.getInstance().getEmail()));
            Picasso.with(getActivity()).load("https://www.gravatar.com/avatar/"+ MD5Util.md5Hex(Preferences.getInstance().getEmail())+"?d=identicon" ).into(imageView);
        }
        else{
            Picasso.with(getActivity()).load(getString(R.string.image_url)+Preferences.getInstance().getPic()).into(imageView);
        }

        locationString =  ButterKnife.findById(headerView,R.id.location_string);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        callback = (NavbarSignedInCallbacks)getActivity();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        }
//        else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        callback.onItemSelected();
        return true;
    }

    public void setLocation(String location) {
        locationString.setText(location);
    }

    public interface NavbarSignedInCallbacks {
        void onItemSelected();
    }
}
