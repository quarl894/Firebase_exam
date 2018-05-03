package youngjung.test.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

<<<<<<< HEAD
import youngjung.test.Model.EvalForm;
=======
import youngjung.test.DefaultApplication;
import youngjung.test.Model.Category;
>>>>>>> 3d946e8c... ridickle7
import youngjung.test.R;

/**
 * Created by ridickle on 2018. 4. 17..
 */

public class MyPageListAdapter extends RecyclerView.Adapter<MyPageListAdapter.MyPageHolder> {
    Context context;
<<<<<<< HEAD
    ArrayList<EvalForm> list = new ArrayList<EvalForm>();
    String[] dummy = {"패션", "음식", "뷰티"};
=======
    ArrayList<Category> list = new ArrayList<>();
>>>>>>> 3d946e8c... ridickle7

    public MyPageListAdapter(Context context) {
        this.context = context;
        listInit();
    }

    public void listInit() {
        list.clear();
<<<<<<< HEAD

        // dummy Data
        for (int i = 0; i < 3; i++)
            list.add(new EvalForm("남", (i + 1) * 10000, (i + 1) * 1000, "title", (i + 1) * 100, "content", "uuid", "date", i + 4, i % 2 == 0 ? true : false));
    }

    public void add(EvalForm data) {
        list.add(data);
        notifyDataSetChanged();
    }

    public void add(ArrayList<EvalForm> dataList) {
        listInit();
        list.addAll(dataList);
        notifyDataSetChanged();
    }
=======
        list = DefaultApplication.getDbHelper().getCategoryRankingList();
    }

>>>>>>> 3d946e8c... ridickle7

    @Override
    public MyPageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_category_mypage, parent, false);
        MyPageHolder holder = new MyPageHolder(itemView);

        return holder;
    }

    @Override
<<<<<<< HEAD
    public void onBindViewHolder(MyPageListAdapter.MyPageHolder holder, int position) {
        EvalForm item = list.get(position);
        holder.category_ranking.setText((item.getCategory() - 3) + "");
//        holder.category_image.setBackground()
        holder.category_title.setText(dummy[position]);
        holder.category_num.setText(item.getCategory() + "");
=======
    public void onBindViewHolder(MyPageHolder holder, int position) {
        Category item = list.get(position);
        holder.category_ranking.setText(position + "");
        holder.category_image.setImageResource(item.getImageId());
        holder.category_title.setText(item.getName());
        holder.category_num.setText(item.getNumber() + "");
>>>>>>> 3d946e8c... ridickle7

        if (position == 0) {
            ConstraintLayout layout = holder.category_layout;
            int convertColor = ContextCompat.getColor(context, R.color.colorPrimary);
            layout.setBackgroundColor(convertColor);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) layout.getLayoutParams();
            params.leftMargin = params.leftMargin - dpToPx(12);
            params.rightMargin = params.rightMargin - dpToPx(12);
            layout.setLayoutParams(params);

            holder.category_ranking.setTextColor(Color.WHITE);
            holder.category_image.setImageResource(item.getImageId());
            holder.category_title.setTextColor(Color.WHITE);
            holder.category_num.setTextColor(Color.WHITE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyPageHolder extends RecyclerView.ViewHolder {
        ConstraintLayout category_layout;
        TextView category_ranking;
        ImageView category_image;
        TextView category_title;
        TextView category_num;

        public MyPageHolder(View itemView) {
            super(itemView);

            category_layout = itemView.findViewById(R.id.category_layout);
            category_ranking = itemView.findViewById(R.id.category_ranking);
            category_image = itemView.findViewById(R.id.category_image);
            category_title = itemView.findViewById(R.id.category_title);
            category_num = itemView.findViewById(R.id.category_num);
        }
    }

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float density = displayMetrics.density;

        int px = Math.round((float) dp * density);
        return px;
    }
}
