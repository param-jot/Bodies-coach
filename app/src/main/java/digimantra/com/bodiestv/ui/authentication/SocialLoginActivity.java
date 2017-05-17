package digimantra.com.bodiestv.ui.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import butterknife.ButterKnife;
import butterknife.OnClick;
import digimantra.com.bodiestv.CommonClasses.BaseActivity;
import digimantra.com.bodiestv.R;
import digimantra.com.bodiestv.ScrollingActivity;
import digimantra.com.bodiestv.utilities.ActivityTransitUtils;


public class SocialLoginActivity extends BaseActivity {






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.social_login);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        configureFragments();
    }

    private void configureFragments() {
        getSupportFragmentManager().beginTransaction().replace(R.id.facebook_fragment_container,FacebookLoginFragment.newInstance(false),"facebook_login_frag")
                .commit();
    }


    @OnClick(R.id.close_social_login)
    void close(){
       ActivityTransitUtils.SlideUpAndExit(this,true);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityTransitUtils.SlideUpAndExit(this,false);
    }
//
//    @OnClick(R.id.twitter_login)
//    void gotoMain(){
//        Intent intent = new Intent(this,ScrollingActivity.class);
//        startActivity(intent);
//        finish();
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment loginFragment = getSupportFragmentManager().findFragmentByTag("facebook_login_frag");
        if (loginFragment!=null)
            loginFragment.onActivityResult(requestCode,resultCode,data);

        Fragment twitter_login_frag = getSupportFragmentManager().findFragmentByTag("twitter_login_frag");
        if (twitter_login_frag!=null)
            twitter_login_frag.onActivityResult(requestCode,resultCode,data);

    }

    

}
