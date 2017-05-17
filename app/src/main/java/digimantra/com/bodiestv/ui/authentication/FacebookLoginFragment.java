package digimantra.com.bodiestv.ui.authentication;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import butterknife.ButterKnife;
import butterknife.OnClick;
import digimantra.com.bodiestv.CommonClasses.BaseActivity;
import digimantra.com.bodiestv.R;
import digimantra.com.bodiestv.models.User;
import digimantra.com.bodiestv.networking.RestClient;
import digimantra.com.bodiestv.ui.signup.RegisterStepActivity;
import digimantra.com.bodiestv.ui.signup.SignupActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FacebookLoginFragment extends Fragment {


    private static final String SIGN_UP = "sign_up";
    private CallbackManager callbackManager;
    private BaseActivity activity;

    public FacebookLoginFragment() {
        // Required empty public constructor
    }


    public static FacebookLoginFragment newInstance(boolean signUp) {

        Bundle args = new Bundle();
        args.putBoolean(SIGN_UP,signUp);
        FacebookLoginFragment fragment = new FacebookLoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
//                Handle API Facebook Login Here

                getProfileDetails(loginResult);
                Log.d("TAG", loginResult.getAccessToken().getToken());
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.activity = (BaseActivity)getActivity();
    }

    private void getProfileDetails(final LoginResult loginResult) {
        activity.showProgressbar(getString(R.string.loading),getString(R.string.logging_you_in));
        GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.d("LoginActivity", response.toString());

                        try {
                            String email = null;
                            String first_name = object.getString("first_name");
                            String last_name = object.getString("last_name");
                            if(object.has("email")){
                                email = object.getString("email");
                            }
                            String id= object.getString("id");
                            Log.d("TAG",email+first_name+last_name);
                            loginToServer(email,id,loginResult.getAccessToken().getToken(),String.format("%s %s",first_name,last_name));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "first_name,last_name,email");
        request.setParameters(parameters);
        request.executeAsync();
    }

    private void loginToServer(String email, String id, String token, String name) {
        if(getArguments().getBoolean(SIGN_UP)){
            signUp(email,name);
        }
        else
            login(email,id,token,name);
            

    }

    private void login(final String email, String id, String token, String name) {


        RestClient.get().socialLogin(id,token,email,"1#1326").enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Intent intent = new Intent(getActivity(), ActivationCodeVerificationActivity.class);
                intent.putExtra(ActivationCodeVerificationActivity.EMAIL,email);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().finish();
                startActivity(intent);

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    private void signUp(String email, String name) {
        if(email!=null){
            Intent intent = new Intent(getActivity(), RegisterStepActivity.class);
            intent.putExtra(RegisterStepActivity.SIGNUP_NAME, name);
            intent.putExtra(RegisterStepActivity.SIGNUP_EMAIL, email);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(getActivity(), SignupActivity.class);
            intent.putExtra(SignupActivity.SIGNUP_NAME, name);
            intent.putExtra(SignupActivity.SOCIAL_LOGIN_FLOW,true);
            startActivity(intent);
        }

    }


    @OnClick(R.id.fb_login)
    void fbLogin(){
        LoginManager.getInstance().logInWithReadPermissions(FacebookLoginFragment.this, Arrays.asList("public_profile", "email", "user_friends"));

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_facebook_login, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

}
