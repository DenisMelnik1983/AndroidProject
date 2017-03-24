package denis.com.ua.privat24rates.network;

import java.util.List;
import denis.com.ua.privat24rates.model.Rate;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Denis.Melnik on 21.03.2017.
 */

public interface PrivatApi {
    @GET("p24api/pubinfo?json&exchange&coursid=5")
    Call<List<Rate>> getData();
}
