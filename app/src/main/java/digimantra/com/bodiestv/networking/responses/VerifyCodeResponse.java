package digimantra.com.bodiestv.networking.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by shivam on 8/8/16.
 */
public class VerifyCodeResponse {

    @SerializedName("is_verify_send")
    @Expose
    private Boolean isVerifySend;
    @SerializedName("mobile")
    @Expose
    private String mobile;

    public Boolean getVerifySend() {
        return isVerifySend;
    }

    public String getMobile() {
        return mobile;
    }
}
