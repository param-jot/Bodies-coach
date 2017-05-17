
package digimantra.com.bodiestv.networking.responses.coaches;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class UserAddressList implements Parcelable {

    @SerializedName("user_address_id")
    @Expose
    public String userAddressId;
    @SerializedName("userid")
    @Expose
    public String userid;
    @SerializedName("address_type")
    @Expose
    public String addressType;
    @SerializedName("address_detail")
    @Expose
    public String addressDetail;
    @SerializedName("city")
    @Expose
    public String city;
    @SerializedName("state")
    @Expose
    public String state;
    @SerializedName("country")
    @Expose
    public String country;
    @SerializedName("zipcode")
    @Expose
    public String zipcode;
    @SerializedName("latitude")
    @Expose
    public String latitude;
    @SerializedName("longitude")
    @Expose
    public String longitude;
    @SerializedName("phone_landline")
    @Expose
    public String phoneLandline;
    @SerializedName("default_address")
    @Expose
    public String defaultAddress;
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

    public String getUserAddressId() {
        return userAddressId;
    }

    public String getUserid() {
        return userid;
    }

    public String getAddressType() {
        return addressType;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getPhoneLandline() {
        return phoneLandline;
    }

    public String getDefaultAddress() {
        return defaultAddress;
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
        dest.writeString(this.userAddressId);
        dest.writeString(this.userid);
        dest.writeString(this.addressType);
        dest.writeString(this.addressDetail);
        dest.writeString(this.city);
        dest.writeString(this.state);
        dest.writeString(this.country);
        dest.writeString(this.zipcode);
        dest.writeString(this.latitude);
        dest.writeString(this.longitude);
        dest.writeString(this.phoneLandline);
        dest.writeString(this.defaultAddress);
        dest.writeString(this.createdby);
        dest.writeString(this.createddate);
        dest.writeString(this.updatedby);
        dest.writeString(this.updateddate);
    }

    public UserAddressList() {
    }

    protected UserAddressList(Parcel in) {
        this.userAddressId = in.readString();
        this.userid = in.readString();
        this.addressType = in.readString();
        this.addressDetail = in.readString();
        this.city = in.readString();
        this.state = in.readString();
        this.country = in.readString();
        this.zipcode = in.readString();
        this.latitude = in.readString();
        this.longitude = in.readString();
        this.phoneLandline = in.readString();
        this.defaultAddress = in.readString();
        this.createdby = in.readString();
        this.createddate = in.readString();
        this.updatedby = in.readString();
        this.updateddate = in.readString();
    }

    public static final Parcelable.Creator<UserAddressList> CREATOR = new Parcelable.Creator<UserAddressList>() {
        @Override
        public UserAddressList createFromParcel(Parcel source) {
            return new UserAddressList(source);
        }

        @Override
        public UserAddressList[] newArray(int size) {
            return new UserAddressList[size];
        }
    };
}
