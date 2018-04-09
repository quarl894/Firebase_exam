package youngjung.test.View;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

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

public class RequestActivity extends baseActivity implements View.OnClickListener {
    private EditText title, price, content;
    private Button register;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String userName;
    ImageButton btn_trip, btn_food, btn_hobby, btn_elec;
    String category = "";
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

        btn_food = findViewById(R.id.btn_food);
        btn_trip = findViewById(R.id.btn_trip);
        btn_hobby = findViewById(R.id.btn_hobby);
        btn_elec = findViewById(R.id.btn_elec);

        btn_food.setOnClickListener(this);
        btn_trip.setOnClickListener(this);
        btn_hobby.setOnClickListener(this);
        btn_elec.setOnClickListener(this);

        //영수증 보낼 날짜
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy. MM. dd");
        final String getTime = sdf.format(date);

        //의뢰하기 입력하는 값으로 수정해야함.
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
//                for(int i=0; i<10; i++){
//                    databaseReference.child("Request receipt").child("test").push().setValue(new RequestForm("남성",2000000,100000, Integer.toString(i),25000,"사고싶은데 이유가 있을까?",uid, getTime,1));
//                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_trip :
                category = "여행";
                btn_trip.setBackgroundResource(R.color.golden_yellow);
                btn_elec.setBackgroundResource(R.color.white);
                btn_food.setBackgroundResource(R.color.white);
                btn_hobby.setBackgroundResource(R.color.white);
                break;
            case R.id.btn_food :
                category = "음식";
                btn_food.setBackgroundResource(R.color.golden_yellow);
                btn_elec.setBackgroundResource(R.color.white);
                btn_trip.setBackgroundResource(R.color.white);
                btn_hobby.setBackgroundResource(R.color.white);
                break;
            case R.id.btn_elec:
                category ="전자기기";
                btn_elec.setBackgroundResource(R.color.golden_yellow);
                btn_food.setBackgroundResource(R.color.white);
                btn_trip.setBackgroundResource(R.color.white);
                btn_hobby.setBackgroundResource(R.color.white);
                break;
            case R.id.btn_hobby :
                category = "취미생활";
                btn_hobby.setBackgroundResource(R.color.golden_yellow);
                btn_elec.setBackgroundResource(R.color.white);
                btn_trip.setBackgroundResource(R.color.white);
                btn_food.setBackgroundResource(R.color.white);
                break;
        }
    }

    //Dialog
    void show()
    {
        final EditText edittext = new EditText(this);
        edittext.setHint("입력해주세요");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("구매 이유를 입력하세요");
        builder.setView(edittext);
        builder.setPositiveButton("추가",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        new AlertDialog.Builder(RequestActivity.this)
                            .setTitle("의뢰서가 등록되었습니다.")
                            .setMessage("평가가 완료되면 푸시알림이 울립니다.")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int id) {
                                    Toast.makeText(getApplicationContext(),edittext.getText().toString() ,Toast.LENGTH_LONG).show();
                                    finish();
                                }
                            })
                            .setNegativeButton("알람 설정", null)
                            .setCancelable(false)
                            .show();
                    }
                });
        builder.setNegativeButton("취소",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.show();
    }

}
