package youngjung.test.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import youngjung.test.R;
import youngjung.test.ui.base.baseActivity;

/**
 * Created by jy on 09/04/2018.
 */

public class MyReceiptDetailActivity extends AppCompatActivity {
    TextView receipt_title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiptdetail);
        receipt_title = findViewById(R.id.detail_title);

        Intent i = getIntent();
        String title = i.getStringExtra("receiptTitle");
        receipt_title.setText(title);
    }

    // 저금하기
    public void onClick_btn_saving(View v) {
        Toast.makeText(getApplicationContext(), "온클릭 구현하자...", Toast.LENGTH_SHORT).show();
    }
}
