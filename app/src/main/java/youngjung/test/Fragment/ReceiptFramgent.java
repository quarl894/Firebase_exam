package youngjung.test.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import youngjung.test.R;
import youngjung.test.View.MyReceiptDetailActivity;
import youngjung.test.View.RequestActivity;

/**
 * Created by HANSUNG on 2018-03-25.
 */

public class ReceiptFramgent extends Fragment implements RecyclerViewAdapter.ItemClickListener{
    private Context mContext;
    RecyclerViewAdapter adapter;
    Button btn_left;
    Button btn_right;

    String[] data = {"2018. 02. 12", "2018. 02. 23", "2018. 03. 09", "2018. 03. 25", "2018. 04. 01", "2018. 04. 14", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48"};
    String[] name = {"이니스프리 파운데이션", "아이맥", "뿌링클", "이케아 의자", "스킨스쿠버 장비", "방탄소년단 CD", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48"};
    String[] price = {"15000원", "5000000원", "14000원", "33000원", "800000원", "11000원", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48"};
    Boolean[] stamp = {true, false, false, true, false, true, false, false, true, true, true, false, false, true, true, true, false, false, true, true, true, false, false, true, true, true, false, false, true, true, true, false, false, true, true, true, false, false, true, true, true, false, false, true, true, true, false, false};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.activity_receipts, container, false);
        btn_left = rootView.findViewById(R.id.btn_left_date);
        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView curDate = rootView.findViewById(R.id.cur_date);
                String year = "", month = "";
                for (int i = 0; i < 4; i++) {
                    year += String.valueOf(curDate.getText().charAt(i));
                }
                for (int i = 5; i <= 6; i++) {
                    month += String.valueOf(curDate.getText().charAt(i));
                }
                Toast.makeText(getActivity(), "year = " + year + ", month = " + month, Toast.LENGTH_SHORT).show();
            }
        });
        btn_right = rootView.findViewById(R.id.btm_right_date);
        btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView curDate = rootView.findViewById(R.id.cur_date);
                Toast.makeText(getActivity(), "after !" + curDate.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, numberOfColumns));
        adapter = new RecyclerViewAdapter(mContext, data, name, price, stamp);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
        return rootView;
    }

    @Override
    public void onItemClick(View view, int position) {
        // Detail Activity로 이동
        Intent i = new Intent(getActivity(), MyReceiptDetailActivity.class);
        startActivity(i);
    }
}
