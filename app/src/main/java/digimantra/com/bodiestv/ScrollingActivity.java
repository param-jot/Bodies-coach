package digimantra.com.bodiestv;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import butterknife.ButterKnife;
import butterknife.OnClick;
import digimantra.com.bodiestv.CommonClasses.BaseActivity;
import digimantra.com.bodiestv.ui.coachlist.ListMapsActivity;
import digimantra.com.bodiestv.ui.customworkout.ChooseActivityNew;
import digimantra.com.bodiestv.ui.customworkout.CustomView;

public class ScrollingActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.menu);

        View v1 = findViewById(R.id.scrolled_part);
        final LinearLayout linearLayout = (LinearLayout) v1.findViewById(R.id.layout_linear);

        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);

        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                Log.d("STATE", state.name());

                if(state.name().equals("COLLAPSED")){
                    //toolbar.setBackgroundResource(R.drawable.gradient_dashboard);
                    linearLayout.setBackgroundResource(R.color.colorGradientLightDashboard);
                }

                else {
                    //toolbar.setBackgroundColor(Color.TRANSPARENT);
                    linearLayout.setBackgroundResource(R.drawable.gradient_dashboard_inverted);
                }
            }
        });

        ButterKnife.bind(this);

    }

    @OnClick({R.id.yoga,R.id.pilates,R.id.zumba,R.id.cycling})
    void seeCoach(){
        startActivity(new Intent(this,ListMapsActivity.class));
    }

    @OnClick(R.id.more_activity)
    void seeMoreActivities(){
        startActivity(new Intent(this, ChooseActivityNew.class));
    }

    @OnClick(R.id.create_own_workout_button)
    void create(){
        startActivity(new Intent(this, CustomView.class));
    }

}
