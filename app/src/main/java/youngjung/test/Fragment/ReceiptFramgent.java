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
    static int cursor;
    MyDBHelper dbHelper;
    RecyclerView recyclerView;

    private Context mContext;
    RecyclerViewAdapter adapter;
    Button btn_left;
    Button btn_right;
    TextView msg, curDate;

    public static ArrayList<RequestForm> receipts = new ArrayList<>();
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

        if(dbHelper.get_date()!=null) {
            for (String date : dbHelper.get_date()) {
                if (!myRequestDates.contains(date)) {
                    myRequestDates.add(date);
                }
            }

            cursor = myRequestDates.size() - 1;
            if (cursor >= 0) {
                curDate.setText(myRequestDates.get(cursor));
            }

            if (myRequestReceipt.size() == 0) {
                msg.setVisibility(TextView.VISIBLE);
                curDate.setVisibility(TextView.INVISIBLE);
                btn_left.setVisibility(Button.INVISIBLE);
                btn_right.setVisibility(Button.INVISIBLE);
            } else {
                msg.setVisibility(TextView.INVISIBLE);
                addReceipts();
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
        }
            recyclerView = rootView.findViewById(R.id.recyclerView);
            int numberOfColumns = 2;
            recyclerView.setLayoutManager(new GridLayoutManager(mContext, numberOfColumns));
            adapter = new RecyclerViewAdapter(mContext, receipts);
            recyclerView.setAdapter(adapter);
            adapter.setClickListener(this);
            check();

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    // 데이터 갱신시 확인
    public void check(){
        if (myRequestReceipt.size() != 0){
            adapter.notifyDataSetChanged();
        }
    }


    public void addReceipts() {
        receipts.clear();

        for (int i = 0; i < myRequestReceipt.size(); i++) {
            RequestForm r = myRequestReceipt.get(i);
            if (r.getDate().substring(0, 8).equals(myRequestDates.get(cursor))) {
                // i는 원래 myRequestReceipt 내 해당 영수증의 인덱스(prePosition)
                // 저금하기 이후 myRequestReceipt 내 saving 변경에 사용
                RequestForm form = new RequestForm(r.getTitle(), r.getPrice(), r.getContent(), r.getDate(), r.getCheck(), r.getSaving(), r.getUid(), i);
                receipts.add(0, form);
            }
        }
    }


    @Override
    public void onItemClick(View view, int position) {
        // Detail Activity로 이동
        Intent i = new Intent(mContext, MyReceiptDetailActivity.class);

        i.putExtra("title", receipts.get(position).getTitle());
        i.putExtra("price", receipts.get(position).getPrice());
        i.putExtra("check", receipts.get(position).getCheck());
        i.putExtra("content", receipts.get(position).getContent());
        i.putExtra("saving", receipts.get(position).getSaving());
        i.putExtra("position", position);
        i.putExtra("uid", receipts.get(position).getUid());
        i.putExtra("prePosition", receipts.get(position).getPrePosition());

        startActivity(i);
    }
}
