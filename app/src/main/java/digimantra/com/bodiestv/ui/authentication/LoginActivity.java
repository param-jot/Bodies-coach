package digimantra.com.bodiestv.ui.authentication;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import digimantra.com.bodiestv.CommonClasses.BaseActivity;
import digimantra.com.bodiestv.R;
import digimantra.com.bodiestv.networking.responses.VerifyCodeResponse;
import digimantra.com.bodiestv.networking.RestClient;
import digimantra.com.bodiestv.utilities.ActivityTransitUtils;
import digimantra.com.bodiestv.utilities.ValidationUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends BaseActivity {


    @BindView(R.id.email_field)
    EditText emailField;

    @OnClick(R.id.social_login)
    void goToSocialLogin(){
        ActivityTransitUtils.SlideDownAndOpen(new Intent(this,SocialLoginActivity.class),this);
    }

//    @OnClick(R.id.request_verification_code_button)
//    public void requestVerification(){
//        startActivity(new Intent(this, ActivationCodeVerificationActivity.class));
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_first_use);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_keyboard_arrow_left_black_24dp);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.request_verification_code_button)
    void requestCode(){
        if(validate()){
            showProgressbar(getString(R.string.loading),getString(R.string.request_verification_code));
//            RestClient.get().verifyCode(emailField.getText().toString(),"mobile")
//                    .enqueue(new Callback<UserActivationResponse>() {
//                        @Override
//                        public void onResponse(Call<UserActivationResponse> call, Response<UserActivationResponse> response) {
//                            Intent intent = new Intent(LoginActivity.this, ActivationCodeVerificationActivity.class);
//                            intent.putExtra(ActivationCodeVerificationActivity.EMAIL,emailField.getText().toString());
//                            startActivity(intent);
//                        }
//
//                        @Override
//                        public void onFailure(Call<UserActivationResponse> call, Throwable t) {
//
//                        }
//                    });

            RestClient.get().generateLoginOTP(emailField.getText().toString(),"mobile")
                    .enqueue(new Callback<VerifyCodeResponse>() {
                        @Override
                        public void onResponse(Call<VerifyCodeResponse> call, Response<VerifyCodeResponse> response) {
                            hideProgressBar();
                            if(response.body().getVerifySend()){
                               Intent intent = new Intent(LoginActivity.this, ActivationCodeVerificationActivity.class);
                               intent.putExtra(ActivationCodeVerificationActivity.EMAIL,emailField.getText().toString());
                               intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                               LoginActivity.this.finish();
                               startActivity(intent);
                           }
                            else{
                               Toast.makeText(LoginActivity.this, "Email address does not exists.", Toast.LENGTH_SHORT).show();
                           }
                        }

                        @Override
                        public void onFailure(Call<VerifyCodeResponse> call, Throwable t) {
                            hideProgressBar();
                        }
                    });
        }


    }



    private boolean validate() {
        if(!ValidationUtils.isValidEmail(emailField.getText().toString())){
            emailField.setError("Enter a valid emial");
            return false;
        }
        return true;
    }

}
