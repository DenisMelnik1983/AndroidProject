package denis.com.ua.privat24rates.network;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Denis.Melnik on 21.03.2017.
 */

public class App extends Application {

    private static PrivatApi service;
    private Retrofit retrofit;
    private static String URL = "https://api.privatbank.ua/";


    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(PrivatApi.class);
    }

    public static PrivatApi getApi(){
        return  service;
    }
}
