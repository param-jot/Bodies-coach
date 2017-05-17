package digimantra.com.bodiestv.ui.signup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import digimantra.com.bodiestv.CommonClasses.BaseActivity;
import digimantra.com.bodiestv.R;
import digimantra.com.bodiestv.ui.authentication.SocialLoginActivity;
import digimantra.com.bodiestv.utilities.ActivityTransitUtils;
import digimantra.com.bodiestv.utilities.ValidationUtils;

public class SignupActivity extends BaseActivity {

    public static final String SIGNUP_NAME = "signup_name";
    public static final String SIGNUP_EMAIL = "signup_email";
    public static final String SOCIAL_LOGIN_FLOW = "no_password_needed";

    @BindView(R.id.signup_name)
    EditText nameField;

    @BindView(R.id.signup_email)
    EditText emailField;

    @BindView(R.id.signup_password)
    EditText passwordField;

    @BindView(R.id.terms_and_conditions_toggle)
    ToggleButton tAndCToggle;


    @BindView(R.id.social_login)
    LinearLayout socialLogin;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//
//
//        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_keyboard_arrow_left_black_24dp);
//        upArrow.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
//        getSupportActionBar().setHomeAsUpIndicator(upArrow);
//
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);



        ButterKnife.bind(this);

        if(getIntent().getBooleanExtra(SOCIAL_LOGIN_FLOW,false)){
            passwordField.setVisibility(View.GONE);
            socialLogin.setVisibility(View.GONE);
        }
        else{
            configureSocialFragments();
        }


    }

    private void configureSocialFragments() {

    }

    @OnClick(R.id.signup_button)
    void signUp(){
     if(validate()){
         Intent intent = new Intent(this, RegisterStepActivity.class);
         intent.putExtra(RegisterStepActivity.SIGNUP_NAME, nameField.getText().toString());
         intent.putExtra(RegisterStepActivity.SIGNUP_EMAIL, emailField.getText().toString());
         intent.putExtra(RegisterStepActivity.SIGNUP_PASSWORD, passwordField.getText().toString());
         startActivity(intent);
     }
    }

    private boolean validate() {
        if(nameField.getText().toString().trim().length() <= 0){
            nameField.setError(getString(R.string.signup_error_name));
            return false;
        }
        else if(!ValidationUtils.isValidEmail(emailField.getText())){
            emailField.setError(getString(R.string.signup_error_email));
            return false;
        }
        else if(!ValidationUtils.isValidPassword(passwordField.getText().toString())){
            passwordField.setError("Atleast 8 characters");
            return false;
        }
        else if(!tAndCToggle.isChecked()){
            Toast.makeText(this, R.string.check_terms_and_conditions,Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }


    @OnClick(R.id.signup_social_login)
    void signup(){
        Intent intent = new Intent(this,SocialLoginActivity.class);
        ActivityTransitUtils.SlideDownAndOpen(intent,this);
    }
}
