package youngjung.test.View;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
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
    ImageButton btn_trip, btn_food, btn_hobby, btn_elec, btn_fashion, btn_etc;
    String category = "";
    String getTime;
    String[] result = new String[3];
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
        btn_fashion=findViewById(R.id.btn_fashion);
        btn_etc = findViewById(R.id.btn_etc);

        Intent intent = getIntent();
        result = intent.getExtras().getStringArray("infomation");
        btn_food.setOnClickListener(this);
        btn_trip.setOnClickListener(this);
        btn_hobby.setOnClickListener(this);
        btn_elec.setOnClickListener(this);
        btn_fashion.setOnClickListener(this);
        btn_etc.setOnClickListener(this);

        //영수증 보낼 날짜
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy. MM. dd");
        getTime = sdf.format(date);

        //test용
//        for(int i=0; i<10; i++) databaseReference.child("Request receipt").child("testuid").push().setValue(new RequestForm(result[0],Integer.parseInt(result[1]),Integer.parseInt(result[2]), Integer.toString(i),10000,"배고프다","testuid", getTime, "음식"));

        //의뢰하기 입력하는 값으로 수정해야함.
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(title.getText().toString().equals("") || content.getText().toString().equals("") || price.getText().toString().equals("") || category.equals("")){
                    Toast.makeText(getApplicationContext(), "의뢰서 양식을 채워주세요.",Toast.LENGTH_SHORT).show();
                }else{
                    Dialog();
                }
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
                btn_fashion.setBackgroundResource(R.color.white);
                btn_etc.setBackgroundResource(R.color.white);
                break;
            case R.id.btn_food :
                category = "음식";
                btn_food.setBackgroundResource(R.color.golden_yellow);
                btn_elec.setBackgroundResource(R.color.white);
                btn_trip.setBackgroundResource(R.color.white);
                btn_hobby.setBackgroundResource(R.color.white);
                btn_fashion.setBackgroundResource(R.color.white);
                btn_etc.setBackgroundResource(R.color.white);
                break;
            case R.id.btn_elec:
                category ="전자기기";
                btn_elec.setBackgroundResource(R.color.golden_yellow);
                btn_food.setBackgroundResource(R.color.white);
                btn_trip.setBackgroundResource(R.color.white);
                btn_hobby.setBackgroundResource(R.color.white);
                btn_fashion.setBackgroundResource(R.color.white);
                btn_etc.setBackgroundResource(R.color.white);
                break;
            case R.id.btn_hobby :
                category = "취미생활";
                btn_hobby.setBackgroundResource(R.color.golden_yellow);
                btn_elec.setBackgroundResource(R.color.white);
                btn_trip.setBackgroundResource(R.color.white);
                btn_food.setBackgroundResource(R.color.white);
                btn_fashion.setBackgroundResource(R.color.white);
                btn_etc.setBackgroundResource(R.color.white);
                break;
            case R.id.btn_fashion:
                category="패션 및 뷰티";
                btn_fashion.setBackgroundResource(R.color.golden_yellow);
                btn_elec.setBackgroundResource(R.color.white);
                btn_trip.setBackgroundResource(R.color.white);
                btn_food.setBackgroundResource(R.color.white);
                btn_hobby.setBackgroundResource(R.color.white);
                btn_etc.setBackgroundResource(R.color.white);
                break;
            case R.id.btn_etc:
                category="기타";
                btn_etc.setBackgroundResource(R.color.golden_yellow);
                btn_elec.setBackgroundResource(R.color.white);
                btn_trip.setBackgroundResource(R.color.white);
                btn_food.setBackgroundResource(R.color.white);
                btn_fashion.setBackgroundResource(R.color.white);
                btn_hobby.setBackgroundResource(R.color.white);
                break;
        }
    }

    //Dialog
//    void show()
//    {
//        final EditText edittext = new EditText(this);
//        edittext.setHint("입력해주세요");
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("구매 이유를 입력하세요");
//        builder.setView(edittext);
//        builder.setPositiveButton("추가",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        new AlertDialog.Builder(RequestActivity.this)
//                            .setTitle("의뢰서가 등록되었습니다.")
//                            .setMessage("평가가 완료되면 푸시알림이 울립니다.")
//                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialogInterface, int id) {
//                                    databaseReference.child("Request receipt").child(uid).push().setValue(new RequestForm("남성",Integer.parseInt(result[1]),Integer.parseInt(result[2]), title.getText().toString(),Integer.parseInt(price.getText().toString()),content.getText().toString(),uid, getTime, category));
//                                    //Toast.makeText(getApplicationContext(),edittext.getText().toString() ,Toast.LENGTH_LONG).show();
//                                    finish();
//                                }
//                            })
//                            .setNegativeButton("알람 설정", null)
//                            .setCancelable(false)
//                            .show();
//                    }
//                });
//        builder.setNegativeButton("취소",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//        builder.show();
//    }

    //버튼 색만 왜 안바뀌지?
    void Dialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("의뢰서가 등록되었습니다.");
        builder.setMessage("평가가 완료되면 푸시알림이 울립니다.");
        builder.setPositiveButton(Html.fromHtml("<font color='#7C70F4'>확인</font>"),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                                        databaseReference.child("Request receipt").child(uid).push().setValue(new RequestForm(result[0],Integer.parseInt(result[1]),Integer.parseInt(result[2]), title.getText().toString(),Integer.parseInt(price.getText().toString()),content.getText().toString(),uid, getTime, category));
                                        finish();
                                    }
                                });
        builder.setNegativeButton(Html.fromHtml("<font color='#7C70F4'>취소</font>"),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        builder.show();
    }
}
