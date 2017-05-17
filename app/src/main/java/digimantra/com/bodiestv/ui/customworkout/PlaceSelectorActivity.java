package digimantra.com.bodiestv.ui.customworkout;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.ButterKnife;
import butterknife.OnClick;
import digimantra.com.bodiestv.CommonClasses.BaseActivity;
import digimantra.com.bodiestv.R;

public class PlaceSelectorActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_session_where);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_keyboard_arrow_left_black_24dp);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ButterKnife.bind(this);

    }


    @OnClick(R.id.coach_location)
    void openModal(){
        DistanceModal modal = new DistanceModal();
        modal.show(getSupportFragmentManager(),"TAG");
    }

    @OnClick(R.id.coach_available_count)
    void showDialog(){
        CoachDialogFragment dialogFragment = new CoachDialogFragment();
        dialogFragment.show(getSupportFragmentManager(),"shfs");
    }

    @OnClick(R.id.next_button)
    void openAlmostDone(){
        Intent goTONext = new Intent(this, ReviewDetailsActivity.class);
        startActivity(goTONext);
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
