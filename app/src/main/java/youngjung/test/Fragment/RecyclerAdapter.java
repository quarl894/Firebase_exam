package youngjung.test.Fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import youngjung.test.R;
import youngjung.test.View.finanActivity;

/**
 * Created by DWU on 2018-04-30.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    Context  context;
    private RecyclerAdapter.ItemClickListener mClickListener;

    private String[] titles = {"탕진이", "충동이", "결심이", "깐깐이"};
    private String[] imgs = {};

    public RecyclerAdapter() {
    }

    public RecyclerAdapter(Context context) {
        this.context = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_nick;
        private ImageButton img_tech1, img_tech2, img_tech3;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_nick = itemView.findViewById(R.id.tv_nick);
            img_tech1 = itemView.findViewById(R.id.img_tech1);
            img_tech2= itemView.findViewById(R.id.img_tech2);
            img_tech3= itemView.findViewById(R.id.img_tech3);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_coin, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final int position = i;
        viewHolder.tv_nick.setText(titles[i]);
        viewHolder.img_tech1.setBackgroundResource(R.drawable.step1);
        viewHolder.img_tech2.setBackgroundResource(R.drawable.step1);
        viewHolder.img_tech3.setBackgroundResource(R.drawable.step1);

        viewHolder.img_tech1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickListener != null) mClickListener.onItemClick(v, position, 0);
                Log.e("testtest : " , "" + 0);
            }
        });

        viewHolder.img_tech2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickListener != null) mClickListener.onItemClick(v, position, 1);
            }
        });
        viewHolder.img_tech3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickListener != null) mClickListener.onItemClick(v, position, 2);
            }
        });
    }
    void setClickListener(RecyclerAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position, int pos);
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }


}