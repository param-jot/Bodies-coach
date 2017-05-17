package digimantra.com.bodiestv.ui.onboard;

import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import digimantra.com.bodiestv.CommonClasses.BaseActivity;
import digimantra.com.bodiestv.R;
import digimantra.com.bodiestv.networking.responses.ContactUsResponse;
import digimantra.com.bodiestv.networking.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactUsActivity extends BaseActivity {


    @BindView(R.id.contact_email)
    EditText emailField;

    @BindView(R.id.contact_name)
    EditText nameField;

    @BindView(R.id.contact_phone)
    EditText phoneField;

    @BindView(R.id.time_text_view)
    TextView timeTextView;
    private String selectedTime = null;

//
//    @BindView(R.id.get_in_touch_button)
//    Button submitForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_form);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_keyboard_arrow_left_black_24dp);
        upArrow.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setIcon(R.drawable.logo_white_back_small);

        ButterKnife.bind(this);

        //Toast.makeText(ContactUsActivity.this, nameField.getTag().toString(), Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.open_time_picker)
    void openTimePicker(){
        TimePickerDialog dialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY,i);
                calendar.set(Calendar.MINUTE,i1);
                calendar.set(Calendar.SECOND,0);
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                selectedTime = dateFormat.format(calendar.getTime());
                timeTextView.setText(selectedTime);
                timeTextView.setTextColor(Color.BLACK);
                timeTextView.setError(null);
            }
        },2,2,false);

        dialog.show();
    }

    @OnClick(R.id.get_in_touch_button)
    void contact(){
        if (validate()) {
            RestClient.get().sendEmail(nameField.getText().toString(),emailField.getText().toString(),
                                    phoneField.getText().toString(), timeTextView.getText().toString())
                        .enqueue(new Callback<ContactUsResponse>() {
                            @Override
                            public void onResponse(Call<ContactUsResponse> call, Response<ContactUsResponse> response) {
                                if(response.body().isSuccess()){
                                    Toast.makeText(ContactUsActivity.this, "We will get back back to you soon",Toast.LENGTH_LONG).show();
                                    finish();
                                }
                            }

                            @Override
                            public void onFailure(Call<ContactUsResponse> call, Throwable t) {

                            }
                        });

        }
    }


    @OnClick(R.id.cancel_text)
    void cancel(){
        finish();
    }

    private boolean validate() {
        if(nameField.getText().toString().trim().length() == 0){
            nameField.setError("Enter name");
            return false;
        }
        else if(!isValidEmail(emailField.getText().toString().trim())){
            emailField.setError("Not a avalid email");
            return false;
        }

        else if(phoneField.getText().toString().length() == 0){
            timeTextView.setError("Phone number");
            return false;
        }
        else if(selectedTime == null){
            timeTextView.setError("Choose time");
            return false;
        }
        return true;
    }

    private boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}
