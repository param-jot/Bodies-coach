package digimantra.com.bodiestv.networking.Auth;

import java.io.IOException;


import digimantra.com.bodiestv.networking.BodiesTvService;
import digimantra.com.bodiestv.networking.responses.TokenResponse;
import digimantra.com.bodiestv.networking.RestClient;
import digimantra.com.bodiestv.utilities.Preferences;
import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

/**
 * Created by shivam on 8/8/16.
 */
public class AuthInterceptor implements Authenticator {




    @Override
    public Request authenticate(Route route, Response response) throws IOException {
        if(response.code() == 401 || Preferences.getInstance().getAccessToken() == null) {
            retrofit2.Response<TokenResponse> tokenResponseCall = RestClient.get().getAccessToken(BodiesTvService.GRANT_TYPE, BodiesTvService.CLIENT_ID, BodiesTvService.CLIENT_SECRET, BodiesTvService.SCOPE).execute();
            Preferences.getInstance().setAccessToken(tokenResponseCall.body().getAccessToken());
        }
            return response.request().newBuilder().header("Authorization","Bearer "+Preferences.getInstance().getAccessToken()).build();

    }
}
