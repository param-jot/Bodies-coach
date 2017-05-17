package digimantra.com.bodiestv.utilities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import digimantra.com.bodiestv.R;

/**
 * Created by shivam on 28/7/16.
 */
public class ActivityTransitUtils {

    public static void SlideDownAndOpen(Intent intent,AppCompatActivity activity){
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.slidedown_enter,R.anim.slidedown_exit);
    }

    public static void SlideUpAndExit(AppCompatActivity activity,boolean finish){
        if(finish)
        activity.finish();
        activity.overridePendingTransition(R.anim.slideup_enter,R.anim.slideup_exit);
    }
}
