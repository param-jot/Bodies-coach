package digimantra.com.bodiestv.networking;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import digimantra.com.bodiestv.networking.Auth.AuthInterceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by shivam on 4/13/16.
 */
public class RestClient {

    private static BodiesTvService REST_CLIENT = null;
    private static String ROOT = "http://198.199.121.44/api/web/v1/";

    static {
        setupRestClient();
    }

    private RestClient() {}

    public static BodiesTvService get() {
        return REST_CLIENT;
    }

    public static void setupRestClient() {

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new  OkHttpClient.Builder().
                addInterceptor(interceptor).
                addNetworkInterceptor(new StethoInterceptor()).
                authenticator(new AuthInterceptor()).build();
        if(REST_CLIENT==null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ROOT)
                    .client(httpClient)

                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();


            REST_CLIENT = retrofit.create(BodiesTvService.class);
        }
    }
}
