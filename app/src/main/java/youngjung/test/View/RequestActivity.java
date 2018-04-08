package youngjung.test.View;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

import youngjung.test.Model.RequestForm;
import youngjung.test.R;
import youngjung.test.ui.base.baseActivity;

/**
 * Created by HANSUNG on 2018-03-01.
 */

public class RequestActivity extends baseActivity {
    private EditText title, price, content;
    private Button register;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String userName;
    private final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        title = findViewById(R.id.request_title);
        price = findViewById(R.id.request_price);
        content = findViewById(R.id.request_content);
        register = findViewById(R.id.request_register);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        userName = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();

        //영수증 보낼 날짜
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy. mm. dd");
        final String getTime = sdf.format(date);

        Button btn_list = findViewById(R.id.btn_list);
        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RequestActivity.this, ListActivity.class);
                startActivity(i);
            }
        });

        //의뢰하기 입력하는 값으로 수정해야함.
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0; i<10; i++){
                    databaseReference.child("Request receipt").child("test").push().setValue(new RequestForm("남성",2000000,100000, Integer.toString(i),25000,"사고싶은데 이유가 있을까?",uid, getTime,1));
                }
            }
        });
    }
    /**
     * 의뢰서 양식
     1.성별 :  sex
     2.한달 급여 : money
     3. 한달 생활비 : monthly_money;
     4. 제목 : title
     5. 가격 : price
     6. 내용 : content
     7. 영수증 주인 uid : uuid
     8. 영수증 보내는 날짜 : data
     */
}
