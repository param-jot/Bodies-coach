package digimantra.com.bodiestv.networking.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import digimantra.com.bodiestv.models.User;

/**
 * Created by shivam on 15/8/16.
 */
public class UserActivationResponse {
    @SerializedName("is_verify")
    @Expose
    boolean verfied;


    @SerializedName("user")
    @Expose
    User user;

    public boolean isVerfied() {
        return verfied;
    }

    public User getUser() {
        return user;
    }
}
