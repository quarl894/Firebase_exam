package youngjung.test.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import youngjung.test.DB.MyDBHelper;
import youngjung.test.R;
import youngjung.test.View.MyReceiptDetailActivity;
import youngjung.test.Model.RequestForm;
import static youngjung.test.MainActivity.myRequestReceipt;

/**
 * Created by HANSUNG on 2018-03-25.
 */

public class ReceiptFramgent extends Fragment implements RecyclerViewAdapter.ItemClickListener{
    static int numUid = -1;
    static int cursor;
    MyDBHelper dbHelper;
    RecyclerView recyclerView;

    private Context mContext;
    RecyclerViewAdapter adapter;
    Button btn_left;
    Button btn_right;
    TextView msg, curDate;

    ArrayList<String> dates = new ArrayList<>();
    ArrayList<String> names = new ArrayList<>();
    ArrayList<Integer> prices = new ArrayList<>();
    ArrayList<Integer> stamps = new ArrayList<>();
    ArrayList<String> contents = new ArrayList<>();
    ArrayList<String> myRequestDates = new ArrayList<>();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        dbHelper = new MyDBHelper(getActivity());
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.activity_receipts, container, false);
        msg = rootView.findViewById(R.id.no_receipt_msg);
        curDate = rootView.findViewById(R.id.cur_date);
        btn_left = rootView.findViewById(R.id.btn_left_date);
        btn_right = rootView.findViewById(R.id.btm_right_date);
        cursor = myRequestDates.size() - 1;

        for (String date : dbHelper.get_date()) {
            if (!myRequestDates.contains(date)) {
                myRequestDates.add(date);
            }
        }
        if (cursor >= 0) {
            curDate.setText(myRequestDates.get(cursor));
        }

        if (myRequestReceipt.size() == 0) {
            msg.setVisibility(TextView.VISIBLE);
            curDate.setVisibility(TextView.INVISIBLE);
            btn_left.setVisibility(Button.INVISIBLE);
            btn_right.setVisibility(Button.INVISIBLE);
        } else if (myRequestReceipt.size() > numUid) {
            numUid = myRequestReceipt.size();
            msg.setVisibility(TextView.INVISIBLE);
            addReceipts();
        } else {
            // 삭제됐지만 1개이상은 남아있는 경우
            msg.setVisibility(TextView.INVISIBLE);
        }

        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursor--;
                if (cursor < 0) {
                    cursor = myRequestDates.size() - 1;
                }
                curDate.setText(myRequestDates.get(cursor));

                addReceipts();
                adapter.notifyDataSetChanged();
            }
        });

        btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursor++;
                if (cursor >= myRequestDates.size()) {
                    cursor = 0;
                }
                curDate.setText(myRequestDates.get(cursor));

                addReceipts();
                adapter.notifyDataSetChanged();
            }
        });

        recyclerView = rootView.findViewById(R.id.recyclerView);
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, numberOfColumns));
        adapter = new RecyclerViewAdapter(mContext, dates, names, prices, stamps);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
        check();

        return rootView;
    }


    // 데이터 갱신시 확인
    public void check(){
        if (myRequestReceipt.size() == 0){
            //check();
        } else {
            adapter.notifyDataSetChanged();
        }
    }


    public void addReceipts() {
        dates.clear();
        names.clear();
        prices.clear();
        stamps.clear();
        contents.clear();

        for (RequestForm r : myRequestReceipt) {
            if (r.getDate().substring(0, 8).equals(myRequestDates.get(cursor))) {
                dates.add(r.getDate());
                names.add(r.getTitle());
                prices.add(r.getPrice());
                stamps.add((r.getCheck() != 0) ? 1 : 0);
                contents.add(r.getContent());
            }
        }
    }


    @Override
    public void onItemClick(View view, int position) {
        // Detail Activity로 이동
        Intent i = new Intent(mContext, MyReceiptDetailActivity.class);
        i.putExtra("name", names.get(position));
        i.putExtra("price", prices.get(position));
        i.putExtra("stamp", stamps.get(position));
        i.putExtra("content", contents.get(position));
        startActivity(i);
    }
}
