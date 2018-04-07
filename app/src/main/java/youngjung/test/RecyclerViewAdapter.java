package youngjung.test;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by jy on 25/02/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>  {
    private String[] mData = new String[0];
    private String[] mName = new String[0];
    private String[] mPrice = new String[0];
    private Boolean[] mStamp = new Boolean[0];
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    RecyclerViewAdapter(Context context, String[] data, String[] name, String[] price, Boolean[] stamp) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.mName = name;
        this.mPrice = price;
        this.mStamp = stamp;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String animal = mData[position];
        String test = mName[position];
        String price = mPrice[position];
        Boolean stamp = mStamp[position];

        holder.receiptDate.setText(animal);
        holder.receiptName.setText(test);
        holder.receiptPrice.setText(price);
        if (stamp) {
            // 허
            holder.receiptStamp.setImageResource(R.drawable.ok_stamp);
        } else {
            // 불허
            holder.receiptStamp.setImageResource(R.drawable.no_stamp);
        }
    }

    @Override
    public int getItemCount() {
        return mName.length;
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

    String getItem(int id) {
        return mName[id];
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
