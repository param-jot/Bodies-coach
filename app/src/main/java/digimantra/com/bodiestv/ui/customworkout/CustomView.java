package digimantra.com.bodiestv.ui.customworkout;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import butterknife.ButterKnife;
import butterknife.OnClick;
import digimantra.com.bodiestv.CommonClasses.BaseActivity;
import digimantra.com.bodiestv.R;
import digimantra.com.bodiestv.ui.customworkout.ChooseActivity;

public class CustomView extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_view_create);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_keyboard_arrow_left_black_24dp);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        findViewById(R.id.get_started_button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(CustomView.this,ChooseActivityNew.class));
//            }
//        });

//        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.create_custom_session_button)
    void chooseActivity(){
        startActivity(new Intent(this,ChooseActivity.class));
    }
}
