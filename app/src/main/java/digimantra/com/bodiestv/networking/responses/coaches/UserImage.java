
package digimantra.com.bodiestv.networking.responses.coaches;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserImage implements Parcelable {

    @SerializedName("photo_id")
    @Expose
    public String photoId;
    @SerializedName("user_id")
    @Expose
    public String userId;
    @SerializedName("session_id")
    @Expose
    public String sessionId;
    @SerializedName("photo_type")
    @Expose
    public String photoType;
    @SerializedName("photo_title")
    @Expose
    public String photoTitle;
    @SerializedName("photo_path")
    @Expose
    public String photoPath;
    @SerializedName("photo_details")
    @Expose
    public String photoDetails;
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


    public String getPhotoId() {
        return photoId;
    }

    public String getUserId() {
        return userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getPhotoType() {
        return photoType;
    }

    public String getPhotoTitle() {
        return photoTitle;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public String  getPhotoDetails() {
        return photoDetails;
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.photoId);
        dest.writeString(this.userId);
        dest.writeString(this.sessionId);
        dest.writeString(this.photoType);
        dest.writeString(this.photoTitle);
        dest.writeString(this.photoPath);
        dest.writeString(this.photoDetails);
        dest.writeString(this.isactive);
        dest.writeString(this.createdby);
        dest.writeString(this.createddate);
        dest.writeString(this.updatedby);
        dest.writeString(this.updateddate);
    }

    public UserImage() {
    }

    protected UserImage(Parcel in) {
        this.photoId = in.readString();
        this.userId = in.readString();
        this.sessionId = in.readString();
        this.photoType = in.readString();
        this.photoTitle = in.readString();
        this.photoPath = in.readString();
        this.photoDetails = in.readString();
        this.isactive = in.readString();
        this.createdby = in.readString();
        this.createddate = in.readString();
        this.updatedby = in.readString();
        this.updateddate = in.readString();
    }

    public static final Parcelable.Creator<UserImage> CREATOR = new Parcelable.Creator<UserImage>() {
        @Override
        public UserImage createFromParcel(Parcel source) {
            return new UserImage(source);
        }

        @Override
        public UserImage[] newArray(int size) {
            return new UserImage[size];
        }
    };
}
