package digimantra.com.bodiestv.ui.customworkout;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.malinskiy.superrecyclerview.SuperRecyclerView;

import butterknife.ButterKnife;
import butterknife.OnClick;
import digimantra.com.bodiestv.Adapters.ActivitySelectorAdapter;
import digimantra.com.bodiestv.Adapters.MostPopularAdapter;
import digimantra.com.bodiestv.CommonClasses.BaseActivity;
import digimantra.com.bodiestv.R;
import digimantra.com.bodiestv.ui.customworkout.DifficultyChooseActivity;

public class ChooseActivity extends BaseActivity {

    SuperRecyclerView mostPopular, allActivities;
    ActivitySelectorAdapter activitySelectorAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_session_activity);

        activitySelectorAdapter = new ActivitySelectorAdapter();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_keyboard_arrow_left_black_24dp);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
//
//        mostPopular = (SuperRecyclerView) findViewById(R.id.activity_list);
////        allActivities = (SuperRecyclerView) findViewById(R.id.activity_list_all);
////
//        mostPopular.setLayoutManager(new LinearLayoutManager(this){
//            @Override
//            public boolean canScrollVertically() {
//                return false;
//            }
//        });

//        mostPopular.setAdapter(new MostPopularAdapter());
//        allActivities.setLayoutManager(new LinearLayoutManager(this){
//            @Override
//            public boolean canScrollVertically() {
//                return false;
//            }
//        });
//
//        mostPopular.setAdapter(activitySelectorAdapter);
//        allActivities.setAdapter(activitySelectorAdapter);


        TextView heading = (TextView) toolbar.findViewById(R.id.text_view_toolbar);
        heading.setText("");

        ButterKnife.bind(this);
    }

    @OnClick(R.id.next_button)
    void goToNextStep(){
        Intent seeDifficultySelector = new Intent(this, DifficultyChooseActivity.class);
        startActivity(seeDifficultySelector);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_choose_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.cancel_menu_item) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
