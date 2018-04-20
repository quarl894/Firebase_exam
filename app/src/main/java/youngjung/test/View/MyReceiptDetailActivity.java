package youngjung.test.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import youngjung.test.MainActivity;
import youngjung.test.Model.Profile;
import youngjung.test.R;
import youngjung.test.ui.base.baseActivity;

/**
 * Created by jy on 09/04/2018.
 */

public class MyReceiptDetailActivity extends AppCompatActivity {
    TextView receipt_title, cur_sex, cur_goal_money, cur_month_money, price, content;
    ImageView stamp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiptdetail);

        receipt_title = findViewById(R.id.detail_title);
        cur_sex = findViewById(R.id.cur_sex);
        cur_goal_money = findViewById(R.id.cur_goal_money);
        cur_month_money = findViewById(R.id.cur_month_money);
        price = findViewById(R.id.detail_price);
        stamp = findViewById(R.id.detail_stamp_imgview);
        content = findViewById(R.id.detail_content);

        MainActivity a = new MainActivity();
        Profile curUser = a.getCurUser();
        cur_sex.setText(curUser.getSex());
        cur_goal_money.setText(curUser.getGoal_money() + "원");
        cur_month_money.setText(curUser.getMonthly_money() + "원");

        Intent i = getIntent();
        receipt_title.setText(i.getStringExtra("name"));
        price.setText(i.getIntExtra("price", 0) + "원");
        if (i.getIntExtra("stamp",0) == 1) {
            stamp.setImageResource(R.drawable.ok_stamp);
        } else {
            stamp.setImageResource(R.drawable.no_stamp);
        }
        content.setText(i.getStringExtra("content"));
    }

    // 저금하기
    public void onClick_btn_saving(View v) {
        Toast.makeText(getApplicationContext(), "온클릭 구현하자...", Toast.LENGTH_SHORT).show();
    }
}
