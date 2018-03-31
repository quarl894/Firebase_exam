package youngjung.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

public class ReceiptsActivity extends AppCompatActivity implements RecyclerViewAdapter.ItemClickListener {

    RecyclerViewAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipts);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        // ReclyclerView에 적용할 data
        String[] data = {"2018. 02. 12", "2018. 02. 23", "2018. 03. 09", "2018. 03. 25", "2018. 04. 01", "2018. 04. 14", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48"};
        String[] name = {"이니스프리 파운데이션", "아이맥", "뿌링클", "이케아 의자", "스킨스쿠버 장비", "방탄소년단 CD", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48"};
        String[] price = {"15000원", "5000000원", "14000원", "33000원", "800000원", "11000원", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48"};
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));

        adapter = new RecyclerViewAdapter(this, data, name, price);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked number " + adapter.getItem(position), Toast.LENGTH_SHORT).show();
    }
}
