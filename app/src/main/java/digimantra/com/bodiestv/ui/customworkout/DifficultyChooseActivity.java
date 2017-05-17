package digimantra.com.bodiestv.ui.customworkout;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.OnClick;
import digimantra.com.bodiestv.CommonClasses.BaseActivity;
import digimantra.com.bodiestv.R;

public class DifficultyChooseActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_session_difficulty_level);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView heading = (TextView) toolbar.findViewById(R.id.text_view_toolbar);

        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_keyboard_arrow_left_black_24dp);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
//

//        heading.setText();
        ButterKnife.bind(this);

    }

    @OnClick(R.id.coach_available_count)
    void showDialog(){
        CoachDialogFragment dialogFragment = new CoachDialogFragment();
        dialogFragment.show(getSupportFragmentManager(),"shfs");
    }

    @OnClick({R.id.inter,R.id.beginner,R.id.advanced})
    void openSelectTime(View v){

//        Switch statement can be used later on
//        switch (v.getId()){
//
//
//        }
        Intent seeTime = new Intent(this, WhenStart.class);
        startActivity(seeTime);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_general, menu);
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
