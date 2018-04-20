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


import java.util.ArrayList;

import youngjung.test.R;
import youngjung.test.View.MyReceiptDetailActivity;
import youngjung.test.Model.RequestForm;
import static youngjung.test.MainActivity.myRequestReceipt;
import static youngjung.test.MainActivity.uidSet;

/**
 * Created by HANSUNG on 2018-03-25.
 */

public class ReceiptFramgent extends Fragment implements RecyclerViewAdapter.ItemClickListener{
    static int numUid = -1;

    private Context mContext;
    RecyclerViewAdapter adapter;
    Button btn_left;
    Button btn_right;
    TextView msg;

    ArrayList<String> dates = new ArrayList<>();
    ArrayList<String> names = new ArrayList<>();
    ArrayList<Integer> prices = new ArrayList<>();
    ArrayList<Integer> stamps = new ArrayList<>();
    ArrayList<String> contents = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.activity_receipts, container, false);

        msg = rootView.findViewById(R.id.no_receipt_msg);

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
                Toast.makeText(mContext, "year = " + year + ", month = " + month, Toast.LENGTH_SHORT).show();
            }
        });
        btn_right = rootView.findViewById(R.id.btm_right_date);
        btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView curDate = rootView.findViewById(R.id.cur_date);
                Toast.makeText(mContext, "after !" + curDate.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        if (uidSet.size() == 0) msg.setVisibility(TextView.VISIBLE);
        else if (uidSet.size() > numUid) {
            // 데이터 들어오면
            numUid = uidSet.size();
            msg.setVisibility(TextView.INVISIBLE);
            for (RequestForm r : myRequestReceipt) {
                dates.add(r.getDate());
                names.add(r.getTitle());
                prices.add(r.getPrice());
                stamps.add((r.getCheck() != 0) ? 1 : 0);
                contents.add(r.getContent());
            }
        } else {
            // 삭제됐지만 1개이상은 남아있는 경우
            msg.setVisibility(TextView.INVISIBLE);
        }

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, numberOfColumns));
        adapter = new RecyclerViewAdapter(mContext, dates, names, prices, stamps);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);

        return rootView;
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
