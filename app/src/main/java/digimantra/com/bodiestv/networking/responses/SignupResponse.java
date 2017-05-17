package digimantra.com.bodiestv.networking.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by shivam on 10/8/16.
 */
public class SignupResponse {

    @SerializedName("is_signup")
    @Expose
    boolean signUpSuccessful;


    @SerializedName("mobile")
    @Expose
    String mobile;

    @SerializedName("msg")
    @Expose
    String msg;


    public boolean isSignUpSuccessful() {
        return signUpSuccessful;
    }

    public String getMobile() {
        return mobile;
    }

    public String getMsg() {
        return msg;
    }
}


