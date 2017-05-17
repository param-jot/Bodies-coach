package digimantra.com.bodiestv.networking.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by shivam on 28/7/16.
 */
public class ContactUsResponse {

    @SerializedName("is_success")
    @Expose
    boolean isSuccess;

    public boolean isSuccess() {
        return isSuccess;
    }
}
