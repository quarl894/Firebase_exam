package youngjung.test.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
    TextView receipt_title, cur_sex, cur_goal_money, cur_month_money;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiptdetail);

        receipt_title = findViewById(R.id.detail_title);
        cur_sex = findViewById(R.id.cur_sex);
        cur_goal_money = findViewById(R.id.cur_goal_money);
        cur_month_money = findViewById(R.id.cur_month_money);

        MainActivity a = new MainActivity();
        Profile curUser = a.getCurUser();
        cur_sex.setText(curUser.getSex());
        cur_goal_money.setText(curUser.getGoal_money() + "원");
        cur_month_money.setText(curUser.getMonthly_money() + "원");

        Intent i = getIntent();
        String title = i.getStringExtra("receiptTitle");
        receipt_title.setText(title);
    }

    // 저금하기
    public void onClick_btn_saving(View v) {
        Toast.makeText(getApplicationContext(), "온클릭 구현하자...", Toast.LENGTH_SHORT).show();
    }
}
