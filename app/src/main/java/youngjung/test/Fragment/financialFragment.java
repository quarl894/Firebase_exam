package youngjung.test.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import youngjung.test.R;
import youngjung.test.View.finanActivity;

public class financialFragment extends Fragment implements RecyclerAdapter.ItemClickListener{
    private Context mContext;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    RecyclerAdapter adapter;
    private TextView tv_finan;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.activity_fin, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerView_fin);
        tv_finan = rootView.findViewById(R.id.tv_finan);
        tv_finan.setText("충동구매를 참았다면 실전이다!\n제태크 전략을 알고 싶다면 코인을 누르세요.");
        tv_finan.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        //RecyclerView scroll 금지
        layoutManager = new LinearLayoutManager(mContext) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerAdapter(mContext);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        return rootView;
    }

    @Override
    public void onItemClick(View view, int position, int pos) {
        int[] arr = new int[2];
        arr[0] = position;
        arr[1] = pos;
        if(position!=0) Dialog();
        else {
            switch (pos) {
                case 0:
                    Intent i = new Intent(getActivity(), finanActivity.class);
                    i.putExtra("send", arr);
                    startActivity(i);
                    break;
                case 1:
                    Intent i2 = new Intent(getActivity(), finanActivity.class);
                    i2.putExtra("send", arr);
                    startActivity(i2);
                    break;
                case 2:
                    Intent i3 = new Intent(getActivity(), finanActivity.class);
                    i3.putExtra("send", arr);
                    startActivity(i3);
                    break;
            }
        }
    }

    void Dialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        TextView tv_title = new TextView(getContext());
        tv_title.setText("잠깐! 코인을 열지 못합니다");
        tv_title.setPadding(dp2px(22), dp2px(32), dp2px(57), dp2px(16));
        tv_title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        tv_title.setTextColor(getContext().getResources().getColor(R.color.dark_two));
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/NotoSansCJKkr-Medium.otf");
        tv_title.setTypeface(tf);

        builder.setCustomTitle(tv_title);
        builder.setMessage("충동이 이상부터 STEP4을 열 수 있어요.");
        final AlertDialog dialog = builder.setPositiveButton("확인",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getContext().getResources().getColor(R.color.cornflower));

            }
        });

        dialog.show();
    }

    int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, this.getResources().getDisplayMetrics());
    }
}
