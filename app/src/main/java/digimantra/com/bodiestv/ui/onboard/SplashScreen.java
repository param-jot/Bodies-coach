package digimantra.com.bodiestv.ui.onboard;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import digimantra.com.bodiestv.models.User;
import digimantra.com.bodiestv.ui.dashboard.DashboardActivity;
import digimantra.com.bodiestv.utilities.Preferences;

import digimantra.com.bodiestv.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //getActionBar().hide();

        int SPLASH_DISPLAY_LENGTH = 1000;


//        try {
//            PackageInfo info = getPackageManager().getPackageInfo("digimantra.com.bodiestv", PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.e("MY KEY HASH:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//
//        } catch (NoSuchAlgorithmException e) {
//
//        }


        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
//
                if(User.isUserLoggedIn()){
                    startMain();

                }

                else{
                    if(Preferences.getInstance().isFirstTime()){
                        Preferences.getInstance().setFirstTime();
                        goToGetStarted();
                    }
                    else{
                        startMain();
                    }
                }


            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    private void goToGetStarted() {
        Intent mainIntent = new Intent(SplashScreen.this,LetsGetStarted.class);
        SplashScreen.this.startActivity(mainIntent);
        SplashScreen.this.finish();
    }



    private void startMain(){
        Intent mainIntent = new Intent(SplashScreen.this,DashboardActivity.class);
        SplashScreen.this.startActivity(mainIntent);
        SplashScreen.this.finish();
    }
}
