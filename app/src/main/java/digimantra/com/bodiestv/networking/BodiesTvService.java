package digimantra.com.bodiestv.networking;

import digimantra.com.bodiestv.models.User;
import digimantra.com.bodiestv.networking.responses.ContactUsResponse;
import digimantra.com.bodiestv.networking.responses.SignupResponse;
import digimantra.com.bodiestv.networking.responses.TokenResponse;
import digimantra.com.bodiestv.networking.responses.UserActivationResponse;
import digimantra.com.bodiestv.networking.responses.VerifyCodeResponse;
import digimantra.com.bodiestv.networking.responses.coaches.CoachListResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by shivam on 26/7/16.
 */
public interface BodiesTvService {


    String CLIENT_ID = "androidclient";
    String CLIENT_SECRET = "2b793351-be4e-52a1-86b6-307c07276466";
    String SCOPE = "custom";
    String GRANT_TYPE = "client_credentials";


    @FormUrlEncoded
    @POST("http://198.199.121.44/oauth2/token.php")
    Call<TokenResponse> getAccessToken(@Field("grant_type") String grantType,
                                       @Field("client_id") String clientId,
                                       @Field("client_secret") String clientString,
                                       @Field("scope") String scope);


    @FormUrlEncoded
    @POST("users/signup")
    Call<SignupResponse> signUp(@Field("SignupForm[email]") String email,
                                @Field("SignupForm[password]") String password,
                                @Field("SignupForm[mobile]") String mobile,
                                @Field("SignupForm[first_name]") String firstName,
                                @Field("SignupForm[dob]") String dob,
                                @Field("SignupForm[address]") String address,
                                @Field("SignupForm[image_data]")String image);


    @FormUrlEncoded
    @POST("users/login")
    Call<User> socialLogin (@Field("SignupForm[id]") String socialLoginId,
                            @Field("SignupForm[access_token]") String accessToken,
                            @Field("LoginForm[email]") String email,
                            @Field("LoginForm[type]")String type);



    @FormUrlEncoded
    @POST("contact/sendemail")
    Call<ContactUsResponse> sendEmail (@Field("Contactus[contactname]") String name,
                                       @Field("Contactus[contactemail]") String email,
                                       @Field("Contactus[contactmobile]") String mobile,
                                       @Field("Contactus[timepreffered]") String timePreffered
                                     );


    @FormUrlEncoded
    @POST("users/verifymobile")
    Call<UserActivationResponse> verifyCode(@Field("UserVerify[email]") String email,
                                            @Field("UserVerify[verficationcode]") String code);


    @FormUrlEncoded
    @POST("users/verifyresend")
    Call<VerifyCodeResponse> generateLoginOTP(@Field("UserVerify[email]") String email,
                                              @Field("UserVerify[type]") String type);


    @GET("coach/coachlisting")
    Call<CoachListResponse> getCoachList ();

//    @POST("coach/coachprofile")
//    Call<CoachDetails> getCoachDetails();


}
