package digimantra.com.bodiestv.ui.onboard;

import android.content.Intent;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import digimantra.com.bodiestv.CommonClasses.BaseActivity;
import digimantra.com.bodiestv.R;
import digimantra.com.bodiestv.ui.authentication.LoginActivity;
import digimantra.com.bodiestv.utilities.ActivityTransitUtils;


public class AlternateEditionActivty extends BaseActivity {

    @OnClick(R.id.close_alternate_edition)
    void close(){
        ActivityTransitUtils.SlideUpAndExit(this,true);
    }
    @OnClick(R.id.sign_in_now)
    void openSignIn(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.contact_us)
    void openContact(){
        Intent intent = new Intent(this,ContactUsActivity.class);
        startActivity(intent);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alternate_edition);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityTransitUtils.SlideUpAndExit(this,false);
    }
}
