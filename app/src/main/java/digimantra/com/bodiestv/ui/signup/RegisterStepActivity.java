package digimantra.com.bodiestv.ui.signup;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.model.LatLng;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import digimantra.com.bodiestv.R;
import digimantra.com.bodiestv.models.User;
import digimantra.com.bodiestv.ui.authentication.ActivationCodeVerificationActivity;
import digimantra.com.bodiestv.ui.LocationActivity;
import digimantra.com.bodiestv.utilities.GeoLocationUtils;
import digimantra.com.bodiestv.utilities.ValidationUtils;

public class RegisterStepActivity extends LocationActivity {

    public static final String SIGNUP_NAME = "signup_name";
    public static final String SIGNUP_EMAIL = "signup_email";
    public static final String SIGNUP_PASSWORD = "signup_password";
    private static final int PICK_IMAGE_REQUEST = 0;


    @BindView(R.id.signup_phone)
    EditText phoneField;

    @BindView(R.id.signup_dob)
    EditText dob;

    @BindView(R.id.user_image)
    CircularImageView userImage;


    @BindView(R.id.city)
    TextView cityTextView;

    @BindView(R.id.state)
    TextView stateTextView;

    @BindView(R.id.user_name)
    TextView name;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private String encodedImage ="";
    private LatLng currentLocation;
    private static final int CROP = 1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_step_2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);



        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_keyboard_arrow_left_black_24dp);
        upArrow.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        fillDetails();
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void fillDetails() {
        name.setText(String.format(getString(R.string.welcome_user),getIntent().getStringExtra(SIGNUP_NAME)));
    }

    @OnClick(R.id.next_button)
    void nextButton() {
        if (validate()) {
            showProgressbar(getString(R.string.loading),getString(R.string.signing_you_up));
            final Bundle extras = getIntent().getExtras();
            User.createUser(extras.getString(SIGNUP_EMAIL), extras.getString(SIGNUP_PASSWORD), phoneField.getText().toString(), extras.getString(SIGNUP_NAME), String.format("%f,%f",currentLocation .latitude, currentLocation.longitude), dob.getText().toString(), encodedImage, new User.onUserCreated() {
                @Override
                public void onSucesss(String mobile) {
                    hideProgressBar();
                    Intent intent = new Intent(RegisterStepActivity.this, ActivationCodeVerificationActivity.class);
                    intent.putExtra(ActivationCodeVerificationActivity.MOBILE,mobile);
                    intent.putExtra(ActivationCodeVerificationActivity.EMAIL,extras.getString(SIGNUP_EMAIL));
                    startActivity(intent);
                    RegisterStepActivity.this.finish();
                }

                @Override
                public void onFailure(String msg) {
                    hideProgressBar();
                    finish();
                    Toast.makeText(RegisterStepActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @OnClick(R.id.signup_dob)
    void openDatePicker(){
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                setDob(year,month,day);
            }


        },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    private void setDob(int year, int month, int day) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month);
        calendar.set(Calendar.DAY_OF_MONTH,day);
        dob.setText(dateFormat.format(calendar.getTime()));
    }

    @OnClick(R.id.user_image)
    void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();

//            try {
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
//                generatebase64(bitmap);
//                userImage.setImageBitmap(bitmap);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            Intent intent = new Intent("com.android.camera.action.CROP");
//            Uri uri = Uri.fromFile(new File(cameraFileName));
            intent.setDataAndType(uri, "image/*");
            startActivityForResult(getCropIntent(intent), CROP);
        }

        else if (requestCode == CROP && resultCode == RESULT_OK) {
//            Toast.makeText(RegisterStepActivity.this, "Control reached here", Toast.LENGTH_SHORT).show();
            Uri uri = data.getData();
            Bundle extras = data.getExtras();
            Bitmap selectedBitmap = extras.getParcelable("data");
            generatebase64(selectedBitmap);
            userImage.setImageBitmap(selectedBitmap);
        }

    }

    private Intent getCropIntent(Intent intent) {
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 320);
        intent.putExtra("outputY", 320);
        intent.putExtra("return-data", true);
        return intent;
    }

    private void generatebase64(Bitmap bm) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
        byte[] b = baos.toByteArray();
         encodedImage = Base64.encodeToString(b, Base64.DEFAULT);

    }

    private boolean validate() {
//        if(!ValidationUtils.isValidPhoneNumber(phoneField.getText().toString())){
//            phoneField.setError("Invalid Phone Number");
//            return false;
//        }
        dob.setError(null);
        if(!ValidationUtils.isValidDate(dob.getText().toString(),"MM/dd/yyyy") || !ValidationUtils.isValidBirthDate(dob.getText().toString(),"MM/dd/yyyy")){
            dob.setError("Invalid date mm/dd/yy");
            Toast.makeText(RegisterStepActivity.this, "Not a valid birth date", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @OnClick(R.id.select_location)
    public void selectLocation() {
        startActivity(new Intent(this, LocationSearch.class));
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "RegisterStep Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://digimantra.com.bodiestv/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "RegisterStep Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://digimantra.com.bodiestv/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    @Override
    public void onCurrentLocationAvailaible(LatLng latLng) {
        currentLocation = latLng;
        GeoLocationUtils.Location location = GeoLocationUtils.getGeoLocation(latLng,this);
        if (location != null) {
            cityTextView.setText(location.getCity());
            stateTextView.setText(location.getState());

        }

    }


}
