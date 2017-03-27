package denis.com.ua.privat24rates.layouts;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import denis.com.ua.privat24rates.R;
import denis.com.ua.privat24rates.adapters.RVAdapter;
import denis.com.ua.privat24rates.model.Rate;
import denis.com.ua.privat24rates.network.App;
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
    RecyclerView mRecyclerView;
    LinearLayout mLinearLayout;
    private int snackBarShowLong = -2;
    private int snackBarShowShort = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycleView);
        tvLastUpdate = (TextView) findViewById(R.id.tvUpdateData);
        tvTextUpdate = (TextView) findViewById(R.id.tvTextUpdate);
        mLinearLayout = (LinearLayout) findViewById(R.id.llMain);


        LinearLayoutManager llm = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(llm);
        getRates();
        initAdapter();

//        LayoutInflater layoutInflater = getLayoutInflater();
//        View view = layoutInflater.inflate(R.layout.update_item, null, false);
//       ViewGroup.LayoutParams layoutParams = view.getLayoutParams();

    }

    private void initAdapter() {
        RVAdapter adapter = new RVAdapter(rates);
        mRecyclerView.setAdapter(adapter);
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

                mRecyclerView.getAdapter().notifyDataSetChanged();

                tvLastUpdate.setText(" " + getDate());
                showMessage(R.string.updated, snackBarShowShort);
            }

            @Override
            public void onFailure(Call<List<Rate>> call, Throwable t) {
                tvTextUpdate.setText("");
                tvLastUpdate.setText("");
                showMessage(R.string.error_update, snackBarShowLong);
            }
        });
        //initAdapter();
    }

    /*get current date*/
    private String getDate(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        return dateFormat.format(date);

    }

    /* in this method I used SnackBar and inform user about update information or error connection*/
    private void showMessage(int message, int showSanckbar){
        final Snackbar snackbar = Snackbar.make(mLinearLayout, message, showSanckbar);
        if(showSanckbar == snackBarShowLong) {
            snackbar.setAction("OK", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    snackbar.dismiss();
                }
            });
        }
        snackbar.show();
    }
}
