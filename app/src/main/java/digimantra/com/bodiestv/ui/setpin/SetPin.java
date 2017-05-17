package digimantra.com.bodiestv.ui.setpin;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import digimantra.com.bodiestv.CommonClasses.BaseActivity;
import digimantra.com.bodiestv.R;
import digimantra.com.bodiestv.ui.dashboard.DashboardActivity;
import digimantra.com.bodiestv.utilities.Preferences;
import me.philio.pinentry.PinEntryView;

public class SetPin extends BaseActivity {

    public static int MODE_PIN = 0;
    public static int MODE_CONFIRM = 1;




    @BindView(R.id.base_text)
    TextView baseButton;

    @BindView(R.id.pin_heading)
    TextView pinheading;

    @BindView(R.id.pin_subheading)
    TextView pinSubHeading;

    @BindView(R.id.pin_mpin)
    PinEntryView mPin;



    int mode = MODE_PIN;
    private String code;




    @OnClick(R.id.base_text)
    public void pinEntered() {
        if(mode == MODE_PIN){
            code = getCode();
            switchtoConfirmMode();
        }
        else if(mode == MODE_CONFIRM){

            checkPin();

        }
    }

    private void checkPin() {
        if(validate()){
            if(code.equals(getCode())){
                Preferences.getInstance().setMpin(code);
                goToMain();
            }
            else{
                Toast.makeText(getApplicationContext(),"Pin do not match",Toast.LENGTH_LONG).show();
                switchtoNormalMode();
            }
        }


    }

    private void clearEditTexts() {


    }

    private void goToMain() {
        Intent intent = new Intent(this,DashboardActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void switchtoConfirmMode() {
        mPin.clearText();
        pinheading.setText(getString(R.string.pin_heading_confirm));
        pinSubHeading.setText(getString(R.string.pin_sub_heading_confirm));

        mode = MODE_CONFIRM;
    }

    private void switchtoNormalMode() {
        clearEditTexts();
        pinheading.setText(getString(R.string.choose_a_6_digit_pin));
        pinSubHeading.setText(getString(R.string.use_the_pin_next_time));

        mode = MODE_PIN;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_pin);

        ButterKnife.bind(this);


    }


    private boolean validate() {
        if(mPin.getText().length() == 6){
            return true;
        }
        else{
            Toast.makeText(getApplicationContext(),"Enter code",Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public String getCode() {
        return mPin.getText().toString();
    }
}
