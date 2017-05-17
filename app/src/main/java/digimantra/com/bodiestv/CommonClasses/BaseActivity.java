package digimantra.com.bodiestv.CommonClasses;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import digimantra.com.bodiestv.R;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by mridul on 7/7/16.
 */
public class BaseActivity extends AppCompatActivity{

    private ProgressDialog progressDialog;
    private AlertDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(this);
    }

    public void showProgressbar(String title, String message) {

        if(!progressDialog.isShowing())
        progressDialog.setMessage(message);
        progressDialog.setTitle(title);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }





    public void hideProgressBar(){
        if(progressDialog.isShowing())
        progressDialog.dismiss();
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    public boolean isInternetAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!isInternetAvailable()){
            showErrorDialog();
        }
    }

    private void showErrorDialog() {
        dialog = new AlertDialog.Builder(this)
                .setTitle(getString(R.string.no_internet))
                .setMessage(getString(R.string.washout_internet))
                .setCancelable(false)
                .setNeutralButton(getString(R.string.go_to_settings), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
                        dialogInterface.dismiss();
                    }
                })
                .create();
        dialog.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(dialog != null && dialog.isShowing()){
            dialog.dismiss();
        }
    }
}
