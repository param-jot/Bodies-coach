package digimantra.com.bodiestv.ui.onboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.viewpagerindicator.CirclePageIndicator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import digimantra.com.bodiestv.CommonClasses.BaseActivity;
import digimantra.com.bodiestv.R;
import digimantra.com.bodiestv.ui.authentication.LoginActivity;
import digimantra.com.bodiestv.ui.dashboard.DashboardActivity;

public class LetsGetStarted extends BaseActivity {


    @BindView(R.id.pager)
    ViewPager pager;

    @BindView(R.id.pager_indicator)
    CirclePageIndicator indicator;


    @OnClick(R.id.sign_in_now)
    void openSignIn(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


    @OnClick(R.id.alternate)
    void openAlternateEditionScreen(){
        Intent intent = new Intent(this, AlternateEditionActivty.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slidedown_enter,R.anim.slidedown_exit);
    }

    @OnClick(R.id.ready_for_workout_button)
    void ready(){
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lets_get_started);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        setUpViewPager();
    }

    private void setUpViewPager() {
        pager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return SliderFragment.newInstance(position);
            }

            @Override
            public int getCount() {
                return 3;
            }
        });
        indicator.setViewPager(pager);
    }

}
