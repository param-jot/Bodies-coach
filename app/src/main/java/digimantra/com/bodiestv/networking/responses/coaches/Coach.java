
package digimantra.com.bodiestv.networking.responses.coaches;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import digimantra.com.bodiestv.models.User;

public class Coach implements Parcelable {

    @SerializedName("user_profile_id")
    @Expose
    public String userProfileId;
    @SerializedName("user_id")
    @Expose
    public String userId;
    @SerializedName("role_id")
    @Expose
    public String roleId;
    @SerializedName("job_title")
    @Expose
    public String jobTitle;
    @SerializedName("business_name1")
    @Expose
    public String businessName1;
    @SerializedName("business_name2")
    @Expose
    public String businessName2;
    @SerializedName("languages_spoken")
    @Expose
    public String languagesSpoken;
    @SerializedName("pricing_level")
    @Expose
    public String pricingLevel;
    @SerializedName("branding_desc")
    @Expose
    public String brandingDesc;
    @SerializedName("overview_info")
    @Expose
    public String overviewInfo;
    @SerializedName("specialization_info")
    @Expose
    public String specializationInfo;
    @SerializedName("reward_info")
    @Expose
    public String rewardInfo;
    @SerializedName("isactive")
    @Expose
    public String isactive;
    @SerializedName("createdby")
    @Expose
    public String createdby;
    @SerializedName("createddate")
    @Expose
    public String createddate;
    @SerializedName("updatedby")
    @Expose
    public String updatedby;
    @SerializedName("updateddate")
    @Expose
    public String updateddate;
    @SerializedName("user")
    @Expose
    public User userDetails;
    @SerializedName("userImages")
    @Expose
    public List<UserImage> userImages = new ArrayList<UserImage>();
    @SerializedName("userAddressList")
    @Expose
    public List<UserAddressList> userAddressList = new ArrayList<UserAddressList>();

    public String getUserProfileId() {
        return userProfileId;
    }

    public String getUserId() {
        return userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getBusinessName1() {
        return businessName1;
    }

    public String getBusinessName2() {
        return businessName2;
    }

    public String getLanguagesSpoken() {
        return languagesSpoken;
    }

    public String getPricingLevel() {
        return pricingLevel;
    }

    public String getBrandingDesc() {
        return brandingDesc;
    }

    public String getOverviewInfo() {
        return overviewInfo;
    }

    public String getSpecializationInfo() {
        return specializationInfo;
    }

    public String getRewardInfo() {
        return rewardInfo;
    }

    public String getIsactive() {
        return isactive;
    }

    public String getCreatedby() {
        return createdby;
    }

    public String getCreateddate() {
        return createddate;
    }

    public String getUpdatedby() {
        return updatedby;
    }

    public String getUpdateddate() {
        return updateddate;
    }

    public User getUserDetails() {
        return userDetails;
    }

    public List<UserImage> getUserImages() {
        return userImages;
    }

    public List<UserAddressList> getUserAddressList() {
        return userAddressList;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userProfileId);
        dest.writeString(this.userId);
        dest.writeString(this.roleId);
        dest.writeString(this.jobTitle);
        dest.writeString(this.businessName1);
        dest.writeString(this.businessName2);
        dest.writeString(this.languagesSpoken);
        dest.writeString(this.pricingLevel);
        dest.writeString(this.brandingDesc);
        dest.writeString(this.overviewInfo);
        dest.writeString(this.specializationInfo);
        dest.writeString(this.rewardInfo);
        dest.writeString(this.isactive);
        dest.writeString(this.createdby);
        dest.writeString(this.createddate);
        dest.writeString(this.updatedby);
        dest.writeString(this.updateddate);
        dest.writeParcelable(this.userDetails, flags);
        dest.writeList(this.userImages);
        dest.writeList(this.userAddressList);
    }

    public Coach() {
    }

    protected Coach(Parcel in) {
        this.userProfileId = in.readString();
        this.userId = in.readString();
        this.roleId = in.readString();
        this.jobTitle = in.readString();
        this.businessName1 = in.readString();
        this.businessName2 = in.readString();
        this.languagesSpoken = in.readString();
        this.pricingLevel = in.readString();
        this.brandingDesc = in.readString();
        this.overviewInfo = in.readString();
        this.specializationInfo = in.readString();
        this.rewardInfo = in.readString();
        this.isactive = in.readString();
        this.createdby = in.readString();
        this.createddate = in.readString();
        this.updatedby = in.readString();
        this.updateddate = in.readString();
        this.userDetails = in.readParcelable(User.class.getClassLoader());
        this.userImages = new ArrayList<UserImage>();
        in.readList(this.userImages, UserImage.class.getClassLoader());
        this.userAddressList = new ArrayList<UserAddressList>();
        in.readList(this.userAddressList, UserAddressList.class.getClassLoader());
    }

    public static final Parcelable.Creator<Coach> CREATOR = new Parcelable.Creator<Coach>() {
        @Override
        public Coach createFromParcel(Parcel source) {
            return new Coach(source);
        }

        @Override
        public Coach[] newArray(int size) {
            return new Coach[size];
        }
    };
}
