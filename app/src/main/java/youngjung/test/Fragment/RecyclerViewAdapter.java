package youngjung.test.Fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import youngjung.test.DefaultApplication;
import youngjung.test.Model.RequestForm;
import youngjung.test.R;

/**
 * Created by jy on 25/02/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>  {
    private ArrayList<RequestForm> receipts;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private DefaultApplication app;

    public RecyclerViewAdapter(Context context, ArrayList<RequestForm> receipts) {
        this.mInflater = LayoutInflater.from(context);
        this.receipts = receipts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        app = new DefaultApplication();
        View view = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String date = receipts.get(position).getDate();
        String title = receipts.get(position).getTitle();
        int price = receipts.get(position).getPrice();
        int stamp = receipts.get(position).getCheck();

        holder.receiptDate.setText(date);
        holder.receiptName.setText(title);
        holder.receiptPrice.setText(app.MoneyfomatWithoutWon(price));

        if (stamp == 1) {
            holder.receiptStamp.setImageResource(R.drawable.ok_stamp);
        } else if (stamp == 0){
            holder.receiptStamp.setImageResource(R.drawable.no_stamp);
        } else {
            Log.e("stamp값 이상", stamp + "");
        }
    }

    @Override
    public int getItemCount() {
        return receipts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView receiptName;
        TextView receiptDate;
        TextView receiptPrice;
        ImageView receiptStamp;

        public ViewHolder(View itemView) {
            super(itemView);
            receiptName = itemView.findViewById(R.id.receipt_name);
            receiptDate = itemView.findViewById(R.id.receipt_date);
            receiptPrice = itemView.findViewById(R.id.receipt_price);
            receiptStamp = itemView.findViewById(R.id.receipt_list_stamp);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}