package digimantra.com.bodiestv.CommonClasses;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.facebook.FacebookSdk;

import com.facebook.stetho.Stetho;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import digimantra.com.bodiestv.R;
import io.fabric.sdk.android.Fabric;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by mridul on 7/7/16.
 */
public class BodiesTv extends Application{

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "eI1JcNyPT37EaUfZSW4OSeU13";
    private static final String TWITTER_SECRET = "yZ0FjDbWC7nczhtFmK91sKv6on4UoycHzGIjtGpOQtjtClphsZ";

    private static BodiesTv instance;

    @Override
    public void onCreate() {
        super.onCreate();
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Crashlytics(), new Twitter(authConfig));
        instance=this;
        FacebookSdk.sdkInitialize(this);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/System_San_Francisco_Display_Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        Stetho.initializeWithDefaults(this);
    }

    public static BodiesTv getInstance() {
        return instance;
    }
}
