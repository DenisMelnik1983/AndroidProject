package denis.com.ua.privat24rates.network;

import android.app.Application;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import denis.com.ua.privat24rates.R;
import denis.com.ua.privat24rates.adapters.RVAdapter;
import denis.com.ua.privat24rates.model.Rate;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Denis.Melnik on 21.03.2017.
 */

public class App {
    private static PrivatApi service;
    private Retrofit retrofit;
    private static String URL = "https://api.privatbank.ua/";
    private List<Rate> rates;
    private RecyclerView rv;

    public void initAdapter() {
        RVAdapter adapter = new RVAdapter(rates);
        rv.setAdapter(adapter);
    }

     public void getRates() {

        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(PrivatApi.class);
        rates = new ArrayList<>();

        service.getData().enqueue(new Callback<List<Rate>>() {
            @Override
            public void onResponse(Call<List<Rate>> call, Response<List<Rate>> response) {
                rates.addAll(response.body());

                rv.getAdapter().notifyDataSetChanged();

//                tvLastUpdate.setText(" " + getDate());
//                showMessage(R.string.updated);
            }

            @Override
            public void onFailure(Call<List<Rate>> call, Throwable t) {
//                tvTextUpdate.setText("");
//                tvLastUpdate.setText("");
//                showMessage(R.string.error_update);
            }
        });
        //initAdapter();
    }
}
