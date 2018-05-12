package youngjung.test.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import youngjung.test.DB.MyDBHelper;
import youngjung.test.MainActivity;
import youngjung.test.Model.Profile;
import youngjung.test.R;
import static youngjung.test.Fragment.ReceiptFramgent.receipts;
import static youngjung.test.MainActivity.myRequestReceipt;



/**
 * Created by jy on 09/04/2018.
 */

public class MyReceiptDetailActivity extends AppCompatActivity {
    TextView receipt_title, cur_sex, cur_goal_money, cur_month_money, price, content;
    Button btn_saving;
    ImageView stamp;
    private MyDBHelper dbHelper;
    Profile curUser;
    Intent intent;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new MyDBHelper(getApplicationContext());
        setContentView(R.layout.activity_receiptdetail);

        receipt_title = findViewById(R.id.detail_title);
        cur_sex = findViewById(R.id.cur_sex);
        cur_goal_money = findViewById(R.id.cur_goal_money);
        cur_month_money = findViewById(R.id.cur_month_money);
        price = findViewById(R.id.detail_price);
        stamp = findViewById(R.id.detail_stamp_imgview);
        content = findViewById(R.id.detail_content);
        btn_saving = findViewById(R.id.btn_saving);

        MainActivity a = new MainActivity();
        curUser = a.getCurUser();
        cur_sex.setText(curUser.getSex());
        cur_goal_money.setText(curUser.getGoal_money() + "원");
        cur_month_money.setText(curUser.getMonthly_money() + "원");

        intent = getIntent();
        receipt_title.setText(intent.getStringExtra("title"));
        price.setText(intent.getIntExtra("price", 0) + "원");
        if (intent.getIntExtra("check", 0) == 1) {
            stamp.setImageResource(R.drawable.ok_stamp);
        } else {
            stamp.setImageResource(R.drawable.no_stamp);
        }
        content.setText(intent.getStringExtra("content"));
        if (intent.getIntExtra("saving", 1) == 1) {
            btn_saving.setBackgroundResource(R.drawable.saving_finish_btn);
            btn_saving.setText("저금완료");
        } else {
            btn_saving.setBackgroundResource(R.drawable.main_btn);
            btn_saving.setText("저금하기");
        }
    }

    // 저금하기
    public void onClick_btn_saving(View v) {
        if (intent.getIntExtra("saving", 1) != 1) {
            // 누적금액 증가시키기
            dbHelper.insert_money(intent.getIntExtra("price", 0));
            Log.e("current money", dbHelper.get_money() + "");

            Toast.makeText(getApplicationContext(), "저금이 완료되었습니다.", Toast.LENGTH_SHORT).show();

            // 버튼 레이아웃 변경
            btn_saving.setBackgroundResource(R.drawable.saving_finish_btn);
            btn_saving.setText("저금완료");

            // 각 영수증의 saving 변경
            receipts.get(intent.getIntExtra("position", 0)).setSaving(1);
            myRequestReceipt.get(intent.getIntExtra("prePosition",0)).setSaving(1);

            // 내부DB에 저금한 아이템 추가
            dbHelper.insert_saving_item(intent.getStringExtra("uid"));
            Log.e("아이템 리스트 길이 : ", dbHelper.get_saving_item().size() + "");

            finish();
        }
    }
}
