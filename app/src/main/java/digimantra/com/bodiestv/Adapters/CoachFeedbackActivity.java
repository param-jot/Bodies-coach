package digimantra.com.bodiestv.Adapters;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.malinskiy.superrecyclerview.SuperRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import digimantra.com.bodiestv.CommonClasses.BaseActivity;
import digimantra.com.bodiestv.ui.coachdetails.LeaveFeedbackDialogFragment;
import digimantra.com.bodiestv.R;

public class CoachFeedbackActivity extends BaseActivity {


    @BindView(R.id.feed_back_list)
    SuperRecyclerView feedbackList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_sessions_history);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        TextView heading = (TextView) toolbar.findViewById(R.id.text_view_toolbar);
        heading.setText("Coach Name | Feedback");

        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_keyboard_arrow_left_black_24dp);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        showDummydata();
    }


// TODO    DUMMY DATA WILL CHANGE IN FUTURE

    private void showDummydata() {
        feedbackList.setLayoutManager(new LinearLayoutManager(this));
        feedbackList.setAdapter(new CoachFeedbackAdapter());
    }

    @OnClick(R.id.see_reviews_dialog)
    void seeReviews(){
        LeaveFeedbackDialogFragment dialogFragment = new LeaveFeedbackDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), LeaveFeedbackDialogFragment.class.getCanonicalName());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_coach_feedback,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.refine_results:
                return true;
        }
        return false;
    }

//    @OnClick(R.id.leave_feedback_button)
//    void leaveFeedbackScreen(){
//        Intent leavefeedback = new Intent(this, LeaveFeedbackCoach.class);
//        startActivity(leavefeedback);
//    }

}
