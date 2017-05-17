package digimantra.com.bodiestv.ui.authentication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import digimantra.com.bodiestv.CommonClasses.BaseActivity;
import digimantra.com.bodiestv.ui.setpin.LoginModel;
import digimantra.com.bodiestv.R;
import digimantra.com.bodiestv.models.User;
import digimantra.com.bodiestv.networking.responses.UserActivationResponse;
import digimantra.com.bodiestv.networking.responses.VerifyCodeResponse;
import digimantra.com.bodiestv.networking.RestClient;
import me.philio.pinentry.PinEntryView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivationCodeVerificationActivity extends BaseActivity {

    public static final String MOBILE = "mobile";
    public static String EMAIL ="EMAIL";

    @BindView(R.id.pin_activate)
    PinEntryView pinEntryView;


    @BindView(R.id.login_button)
    Button loginButton;

    @BindView(R.id.mobile_number)
    TextView mobileNumber;
    private String code;

    @OnClick(R.id.login_button)
    public void login() {
        if(validate()){
            showProgressbar(getString(R.string.loading),getString(R.string.verifying_code));
            RestClient.get().verifyCode(getIntent().getStringExtra(EMAIL), getCode()).
                    enqueue(new Callback<UserActivationResponse>() {
                        @Override
                        public void onResponse(Call<UserActivationResponse> call, Response<UserActivationResponse> response) {
                            if(response.body().isVerfied()){
                                User user = response.body().getUser();
                                user.persistUserDetails();
                                setpin();
                            }
                            else{

                                hideProgressBar();
                            }

                        }

                        @Override
                        public void onFailure(Call<UserActivationResponse> call, Throwable t) {

                        }
                    });

        }

    }

    private void setpin() {
        Intent intent = new Intent(this,LoginModel.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }



    private void goToMain() {

    }

    private boolean validate() {
        if(pinEntryView.getText().length() == 6){
            return true;
        }
        else{
            Toast.makeText(getApplicationContext(),"Enter code",Toast.LENGTH_LONG).show();
            return false;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_activation_code);

        ButterKnife.bind(this);



        mobileNumber.setText(String.format(getString(R.string.text_message_login),getIntent().getStringExtra(MOBILE)));
    }

//    public void startEditTextTransitions(){
//        ed1.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if(ed1.getText().toString().length()==1)
//                {
//                    ed2.requestFocus();
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
//
//        ed2.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if(ed2.getText().toString().length()==1)
//                    ed3.requestFocus();
//                else if(ed2.getText().toString().length() == 0)
//                    ed1.requestFocus();
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
//
//        ed3.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if(ed3.getText().toString().length()==1)
//                {
//                    ed4.requestFocus();
//                }
//                else if(ed3.getText().toString().length() == 0)
//                    ed2.requestFocus();
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
//
//        ed4.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if(ed4.getText().toString().length()==1)
//                {
//                    ed5.requestFocus();
//                }
//                else if(ed4.getText().toString().length() == 0)
//                    ed3.requestFocus();
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
//
//        ed5.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if(ed5.getText().toString().length()==1)
//                {
//                    ed6.requestFocus();
//                }
//                else if(ed5.getText().toString().length() == 0)
//                    ed4.requestFocus();
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
//
//        ed6.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if(ed6.getText().toString().length()==1)
//                {
//                    loginButton.requestFocus();
//                }
//                else if(ed6.getText().toString().length() == 0)
//                    ed5.requestFocus();
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
//    }

    @OnClick(R.id.resend_activation_code)
    void resendActivationCode(){
        showProgressbar(getString(R.string.loading),getString(R.string.request_verification_code_new));
        RestClient.get().generateLoginOTP(getIntent().getStringExtra(EMAIL),"mobile")
        .enqueue(new Callback<VerifyCodeResponse>() {
                     @Override
                     public void onResponse(Call<VerifyCodeResponse> call, Response<VerifyCodeResponse> response) {
                         hideProgressBar();
                            if(response.body().getVerifySend()){
                                Toast.makeText(ActivationCodeVerificationActivity.this, "Code Sent!", Toast.LENGTH_SHORT).show();
                                pinEntryView.clearText();
                            }
                     }

                     @Override
                     public void onFailure(Call<VerifyCodeResponse> call, Throwable t) {

                     }
                 }
        );
    }

    public String getCode() {
        return  pinEntryView.getText().toString();
    }

    @Override
    public void onBackPressed() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                        .setMessage("Are sure you want to exit frm this step.?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ActivationCodeVerificationActivity.super.onBackPressed();
                            }
                        }).
                        setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                .create();
        dialog.show();

    }
}
