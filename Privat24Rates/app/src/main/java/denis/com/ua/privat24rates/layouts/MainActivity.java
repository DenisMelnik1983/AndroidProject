package denis.com.ua.privat24rates.layouts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import denis.com.ua.privat24rates.R;
import denis.com.ua.privat24rates.adapters.RVAdapter;
import denis.com.ua.privat24rates.model.Rate;
import denis.com.ua.privat24rates.network.PrivatApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static PrivatApi service;
    private Retrofit retrofit;
    private static String URL = "https://api.privatbank.ua/";

    private TextView  tvLastUpdate, tvTextUpdate;

    List<Rate> rates;
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = (RecyclerView) findViewById(R.id.recycleView);
        tvLastUpdate = (TextView) findViewById(R.id.tvUpdateData);
        tvTextUpdate = (TextView) findViewById(R.id.tvTextUpdate);


        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        initAdapter();
        getRates();

//        LayoutInflater layoutInflater = getLayoutInflater();
//        View view = layoutInflater.inflate(R.layout.update_item, null, false);
//       ViewGroup.LayoutParams layoutParams = view.getLayoutParams();

    }

    private void initAdapter() {
        RVAdapter adapter = new RVAdapter(rates);
        rv.setAdapter(adapter);
    }

    /*in this method I used retrofit library to get
    * and parse json file*/
    private void getRates() {

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

                tvLastUpdate.setText(" " +getDate());
            }

            @Override
            public void onFailure(Call<List<Rate>> call, Throwable t) {
                tvTextUpdate.setText("");
                tvLastUpdate.setText("ошибка обновления");
            }
        });
        initAdapter();
    }

    private String getDate(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        return dateFormat.format(date);

    }
}
