package digimantra.com.bodiestv.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import digimantra.com.bodiestv.networking.responses.SignupResponse;
import digimantra.com.bodiestv.networking.RestClient;
import digimantra.com.bodiestv.utilities.Preferences;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by shivam on 8/8/16.
 */
public class User implements Parcelable {


    @SerializedName("userid")
    @Expose
    private Integer userid;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("middlename")
    @Expose
    private String middlename;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("mobile")
    @Expose
    private String mobile;


    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("userphoto_path")
    @Expose
    private String userphotoPath;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("verificationcode")
    @Expose
    private String verificationcode;
    @SerializedName("last_login_date")
    @Expose
    private String lastLoginDate;
    @SerializedName("auth_key")
    @Expose
    private String authKey;
    @SerializedName("password_reset_token")
    @Expose
    private String passwordResetToken;
    @SerializedName("isactive")
    @Expose
    private Boolean isactive;
    @SerializedName("verifystatus")
    @Expose
    private Integer verifystatus;
    @SerializedName("createdby")
    @Expose
    private Integer createdby;
    @SerializedName("createddate")
    @Expose
    private String createddate;
    @SerializedName("updatedby")
    @Expose
    private String updatedby;
    @SerializedName("updateddate")
    @Expose
    private String updateddate;



    public static void createUser(String email,String password, String mobile, String firstname, String address, String dob, String image, final onUserCreated callback){
        RestClient.get().signUp(email,password, mobile, firstname, dob ,address, image).enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                if(response.body().isSignUpSuccessful())
                    callback.onSucesss(response.body().getMobile());
                else
                    callback.onFailure(response.body().getMsg());

            }

            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {

            }
        });
    }

    public static boolean isUserLoggedIn(){
        return Preferences.getInstance().getAuthCode() != null;
    }

    public void persistUserDetails(){
        Preferences.getInstance().setAuthCode(getAuthKey());
        Preferences.getInstance().setName(getFirstname());
        Preferences.getInstance().setPic(getUserphotoPath());
        Preferences.getInstance().setEmail(getEmail());
    }



    public interface onUserCreated{
        void onSucesss(String mobile);
        void onFailure(String msg);
    }

    public interface UserLoggedInCallback{
        void onSuccess(User user);
        void onFailure(String msg);
    }

    public Integer getUserid() {
        return userid;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getGender() {
        return gender;
    }

    public String getUserphotoPath() {
        return userphotoPath;
    }

    public String getDob() {
        return dob;
    }

    public String getVerificationcode() {
        return verificationcode;
    }

    public Object getLastLoginDate() {
        return lastLoginDate;
    }

    public String getAuthKey() {
        return authKey;
    }

    public String getPasswordResetToken() {
        return passwordResetToken;
    }

    public Boolean getIsactive() {
        return isactive;
    }

    public Integer getVerifystatus() {
        return verifystatus;
    }

    public Integer getCreatedby() {
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
        dest.writeValue(this.userid);
        dest.writeString(this.username);
        dest.writeString(this.firstname);
        dest.writeString(this.middlename);
        dest.writeString(this.lastname);
        dest.writeString(this.email);
        dest.writeString(this.mobile);
        dest.writeString(this.gender);
        dest.writeString(this.userphotoPath);
        dest.writeString(this.dob);
        dest.writeString(this.verificationcode);
        dest.writeString(this.lastLoginDate);
        dest.writeString(this.authKey);
        dest.writeString(this.passwordResetToken);
        dest.writeValue(this.isactive);
        dest.writeValue(this.verifystatus);
        dest.writeValue(this.createdby);
        dest.writeString(this.createddate);
        dest.writeString(this.updatedby);
        dest.writeString(this.updateddate);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.userid = (Integer) in.readValue(Integer.class.getClassLoader());
        this.username = in.readString();
        this.firstname = in.readString();
        this.middlename = in.readString();
        this.lastname = in.readString();
        this.email = in.readString();
        this.mobile = in.readString();
        this.gender = in.readString();
        this.userphotoPath = in.readString();
        this.dob = in.readString();
        this.verificationcode = in.readString();
        this.lastLoginDate = in.readString();
        this.authKey = in.readString();
        this.passwordResetToken = in.readString();
        this.isactive = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.verifystatus = (Integer) in.readValue(Integer.class.getClassLoader());
        this.createdby = (Integer) in.readValue(Integer.class.getClassLoader());
        this.createddate = in.readString();
        this.updatedby = in.readString();
        this.updateddate = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
