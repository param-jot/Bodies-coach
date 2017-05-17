package digimantra.com.bodiestv.utilities;

import android.content.Context;
import android.content.SharedPreferences;
import digimantra.com.bodiestv.CommonClasses.BodiesTv;

/**
 * Created by shivam on 23/6/16.
 */
public class Preferences {

    private static final String NAME = "shared_prefernces_bodies";
    private static final String ACCESS_TOKEN = "access_token";
    private static final String AUTH_KEY = "AUTH_KEY";
    private static final String MPIN = "mpin";
    private static final String PIC = "pic";
    private static final String USER_DETAILS = "user_details";
    private static final String FIRST_TIME = "first_time";
    private static final String EMAIL = "email";
    private static Preferences instance;
    private SharedPreferences prefs;
    private SharedPreferences userPrefs;


    public static Preferences getInstance() {
        if(instance == null) {
            instance = new Preferences();
        }
        return instance;
    }

    private Preferences() {
        initPrefs();
    }

    private void initPrefs() {
        if(null != prefs) return;
        prefs = BodiesTv.getInstance().getSharedPreferences(NAME, Context.MODE_PRIVATE);
        userPrefs = BodiesTv.getInstance().getSharedPreferences(USER_DETAILS, Context.MODE_PRIVATE);
    }


    public void setAccessToken(String accessToken) {
        prefs.edit().putString(ACCESS_TOKEN, accessToken).commit();
    }

    public String getAccessToken(){
        return prefs.getString(ACCESS_TOKEN,null);
    }

    public void setAuthCode(String authCode) {
        userPrefs.edit().putString(AUTH_KEY,authCode).commit();
    }

    public String getAuthCode(){
        return  userPrefs.getString(AUTH_KEY,null);
    }

    public void setMpin(String mpin) {
        userPrefs.edit().putString(MPIN, mpin).commit();

    }

    public void setName(String name) {
        userPrefs.edit().putString(NAME, name).commit();
    }

    public void setPic(String pic) {
        userPrefs.edit().putString(PIC, pic).commit();
    }

    public String getName() {
       return userPrefs.getString(NAME, null);
    }

    public String getPic() {
        return userPrefs.getString(PIC,null);
    }

    public void clearUserDetails() {
        userPrefs.edit().clear().commit();
    }

    public void setFirstTime(){
        prefs.edit().putBoolean(FIRST_TIME,false).commit();
    }

    public boolean isFirstTime(){
        return prefs.getBoolean(FIRST_TIME,true);
    }

    public void setEmail(String email) {

            userPrefs.edit().putString(EMAIL, email).commit();
    }

    public String getEmail(){
       return userPrefs.getString(EMAIL,null);
    }
}
