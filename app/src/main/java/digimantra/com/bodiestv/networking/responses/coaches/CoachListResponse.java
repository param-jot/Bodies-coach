
package digimantra.com.bodiestv.networking.responses.coaches;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CoachListResponse {

    @SerializedName("is_success")
    @Expose
    public Boolean isSuccess;
    @SerializedName("userImagePathUrl")
    @Expose
    public String userImagePathUrl;
    @SerializedName("coaches_list")
    @Expose
    public List<Coach> coach = new ArrayList<Coach>();

    public Boolean getSuccess() {
        return isSuccess;
    }

    public String getUserImagePathUrl() {
        return userImagePathUrl;
    }

    public List<Coach> getCoach() {
        return coach;
    }
}
