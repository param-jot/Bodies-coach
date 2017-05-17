package digimantra.com.bodiestv.ui.coachdetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import digimantra.com.bodiestv.Adapters.CoachFeedbackActivity;
import digimantra.com.bodiestv.CommonClasses.BaseActivity;
import digimantra.com.bodiestv.CommonClasses.BodiesTv;
import digimantra.com.bodiestv.R;
import digimantra.com.bodiestv.networking.BodiesTvService;
import digimantra.com.bodiestv.networking.responses.coaches.Coach;


public class CoachDetailsActivity extends BaseActivity {


    @BindView(R.id.coach_details_name)
    TextView coachName;

    @BindView(R.id.coach_city_text)
    TextView coachCity;

    @BindView(R.id.coach_description)
    TextView coachDescription;

    @BindView(R.id.coach_long_description)
    TextView coachLongDescription;

    @BindView(R.id.coach_fees)
    TextView coachFees;

    @BindView(R.id.coach_image)
    ImageView coachImage;

    @BindView(R.id.coach_languages)
    TextView coachLanguages;


    @BindView(R.id.coach_qualifications)
    TextView coachQualifications;


    @BindView(R.id.coach_awards)
    TextView coachAwards;






    public static final String COACH_DATA = "coach_details";
    private Coach coach;
    private TextView heading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coach_profile_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        heading = (TextView) toolbar.findViewById(R.id.text_view_toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);





        ButterKnife.bind(this);

        coach = (Coach)getIntent().getParcelableExtra(COACH_DATA);

        showData(coach);


    }

    private void showData(Coach coach) {
        heading.setText(coach.getUserDetails().getFirstname() +" "+  coach.getUserDetails().getLastname());
        coachName.setText(coach.getUserDetails().getFirstname()+ " "+ coach.getUserDetails().getLastname());
        coachCity.setText(coach.getUserAddressList().get(0).getCity());
        coachDescription.setText(coach.getBrandingDesc());
        coachLanguages.setText(coach.getLanguagesSpoken());
        coachFees.setText(coach.getPricingLevel());
        coachQualifications.setText(coach.getSpecializationInfo());
        coachLongDescription.setText(coach.getOverviewInfo());
        coachAwards.setText(coach.getRewardInfo());

//        coachFees.setText("");
        Picasso.with(this).load(getString(R.string.image_url)+coach.getUserDetails().getUserphotoPath()).into(coachImage);

    }


    @OnClick(R.id.see_reviews)
    void seeReviews(){
        Intent seeFeedback = new Intent(this, CoachFeedbackActivity.class);
        startActivity(seeFeedback);
    }

}
