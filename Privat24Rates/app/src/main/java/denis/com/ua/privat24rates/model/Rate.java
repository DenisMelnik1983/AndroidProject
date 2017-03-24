package denis.com.ua.privat24rates.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Rate {

    @SerializedName("ccy")
    @Expose
    private String ccy;
    @SerializedName("base_ccy")
    @Expose
    private String baseCcy;
    @SerializedName("buy")
    @Expose
    private String buy;
    @SerializedName("sale")
    @Expose
    private String sale;


    private List<Rate> rates;

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getBaseCcy() {
        return baseCcy;
    }

    public void setBaseCcy(String baseCcy) {
        this.baseCcy = baseCcy;
    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "ccy='" + ccy + '\'' +
                ", baseCcy='" + baseCcy + '\'' +
                ", buy='" + buy + '\'' +
                ", sale='" + sale + '\'' +
                '}';
    }

    public Rate(String ccy, String baseCcy, String buy, String sale) {
        this.ccy = ccy;
        this.baseCcy = baseCcy;
        this.buy = buy;
        this.sale = sale;
    }


}