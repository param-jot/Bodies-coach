package digimantra.com.bodiestv.ui.customworkout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.ButterKnife;
import butterknife.OnClick;
import digimantra.com.bodiestv.CommonClasses.BaseActivity;
import digimantra.com.bodiestv.R;

public class WhenStart extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_session_timing);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        TextView heading = (TextView) toolbar.findViewById(R.id.text_view_toolbar);
//        heading.setText("");
//        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_white_36dp);
        ButterKnife.bind(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_general, menu);
        return true;
    }

    @OnClick(R.id.coach_available_count)
    void showDialog(){
        CoachDialogFragment dialogFragment = new CoachDialogFragment();
        dialogFragment.show(getSupportFragmentManager(),"shfs");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.cancel_menu_item) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.next_button)
    void onClick(){
        Intent intent = new Intent(this, FrequencySelectorActivity.class);
        startActivity(intent);
    }
}