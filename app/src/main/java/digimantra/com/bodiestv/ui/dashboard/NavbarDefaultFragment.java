package digimantra.com.bodiestv.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import digimantra.com.bodiestv.R;
import digimantra.com.bodiestv.ui.authentication.LoginActivity;
import digimantra.com.bodiestv.ui.signup.SignupActivity;

/**
 * Created by shivam on 21/8/16.
 */
public class NavbarDefaultFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.nav_view)
    NavigationView navigationView;
    private NavBarDefaultCallbacks callback;

//    @BindView(R.id.sign_up_button)




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.navbar_default,container,false);
        ButterKnife.bind(this,view);
        return view;


    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        Button signUpButton = (Button) navigationView.getHeaderView(0).findViewById(R.id.sign_up_button);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUp = new Intent(getActivity(), SignupActivity.class);
                startActivity(signUp);
            }
        });

    }

//    @OnClick(R.id.sign_up_button)
//    void signUp(){
//        Intent signUp = new Intent(getActivity(), SignupActivity.class);
//        startActivity(signUp);
//    }

    @OnClick(R.id.sign_in_now)
    void signIn() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        callback = (NavBarDefaultCallbacks)getActivity();
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

    public interface NavBarDefaultCallbacks{
        void onItemSelected();
    }
}
