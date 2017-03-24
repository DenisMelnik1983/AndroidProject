package denis.com.ua.privat24rates.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import denis.com.ua.privat24rates.R;
import denis.com.ua.privat24rates.model.Rate;


public class RVAdapter extends RecyclerView.Adapter<RVAdapter.RateViewHolder> {

    List<Rate> rates;

    public RVAdapter(List<Rate> rates) {

        this.rates = rates;
    }

    @Override
    public RateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        RateViewHolder rvh = new RateViewHolder(v);
        return rvh;
    }

    @Override
    public void onBindViewHolder(RateViewHolder holder, int position) {
        holder.tvCcy.setText(rates.get(position).getCcy());
        holder.tvSale.setText(rates.get(position).getSale());
        holder.tvBuy.setText(rates.get(position).getBuy());
        holder.tvBaseCcy.setText(rates.get(position).getBaseCcy());

        String value = holder.tvCcy.getText().toString();
//        if(holder.tvCcy.getText().equals("usd")){
//             holder.ivPic.setImageResource(R.drawable.test);
        switch (value) {
            case "USD":
                holder.ivPic.setImageResource(R.drawable.usd);
                Log.d("MyLog", "OK " + value);
                break;
            case "RUR":
                holder.ivPic.setImageResource(R.drawable.rur);
                Log.d("MyLog", "OK " + value);
                break;
            case "EUR":
                holder.ivPic.setImageResource(R.drawable.euro);
                break;
            case "BTC":
                holder.ivPic.setImageResource(R.drawable.bit);
        }

    }

    @Override
    public int getItemCount() {
        if (rates == null) {
            return 0;
        }
        return rates.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class RateViewHolder extends RecyclerView.ViewHolder {
        CardView mCV;
        TextView tvCcy, tvBuy, tvSale, tvBaseCcy;
        ImageView ivPic;


        public RateViewHolder(View itemView) {
            super(itemView);
            mCV = (CardView) itemView.findViewById(R.id.cv);
            tvBaseCcy = (TextView) itemView.findViewById(R.id.tvBaseCcy);
            tvBuy = (TextView) itemView.findViewById(R.id.tvBuy);
            tvSale = (TextView) itemView.findViewById(R.id.tvSale);
            tvCcy = (TextView) itemView.findViewById(R.id.tvCcy);
            ivPic = (ImageView) itemView.findViewById(R.id.ivPic);
//
//            if(tvCcy.getText() == "usd"){
//                ivPic.setImageResource(R.drawable.test);
//            }
        }
    }
}
