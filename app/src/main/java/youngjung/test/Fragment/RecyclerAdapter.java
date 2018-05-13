package youngjung.test.Fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import youngjung.test.R;
import youngjung.test.info.Info1;

/**
 * Created by DWU on 2018-04-30.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    static Context mContext;
    //private ItemClickListener mClickListener;

    private String[] titles = {"1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
            "11",
            "12"};

//    private String[] details = {"Item one details",
//            "Item two details", "Item three details",
//            "Item four details", "Item file details",
//            "Item six details", "Item seven details",
//            "Item eight details"};

//    private int[] images = { R.drawable.android_image_1,
//            R.drawable.android_image_2,
//            R.drawable.android_image_3,
//            R.drawable.android_image_4,
//            R.drawable.android_image_5,
//            R.drawable.android_image_6,
//            R.drawable.android_image_7,
//            R.drawable.android_image_8 };

    class ViewHolder extends RecyclerView.ViewHolder {

        // public ImageView itemImage;
        public TextView itemTitle;
        //public TextView itemDetail;

        public ViewHolder(View itemView) {
            super(itemView);
            //itemImage = (ImageView)itemView.findViewById(R.id.item_image);

            itemTitle = (TextView)itemView.findViewById(R.id.item_title);

            //itemDetail = (TextView)itemView.findViewById(R.id.item_detail);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

//                    Intent i = new Intent(mContext, Info1.class);
//                    i.putExtra("financialDetail", position);
//                    mContext.startActivity(i);

                    Log.e("test", position+"위치");

                    FinancialFragment.onItemClick(position);

                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.coin_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.itemTitle.setText(titles[i]);
        // viewHolder.itemDetail.setText(details[i]);
        // viewHolder.itemImage.setImageResource(images[i]);
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }


}
