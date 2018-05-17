package youngjung.test.View;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.Log;
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
import com.google.firebase.iid.FirebaseInstanceId;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import youngjung.test.DB.MyDBHelper;
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
    ImageButton btn_trip, btn_food, btn_hobby, btn_elec, btn_etc, btn_beauty;
    String category = "";
    String getTime;
    String[] result = new String[3];
    private final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    MyDBHelper dbHelper;
    ArrayList<String> arr_date;
    String token = "";

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

        token = FirebaseInstanceId.getInstance().getToken();


        btn_food = findViewById(R.id.btn_food);
        btn_trip = findViewById(R.id.btn_trip);
        btn_hobby = findViewById(R.id.btn_hobby);
        btn_elec = findViewById(R.id.btn_elec);
        btn_etc = findViewById(R.id.btn_etc);
        btn_beauty = findViewById(R.id.btn_beauty);

        Intent intent = getIntent();
        result = intent.getExtras().getStringArray("infomation");
        btn_food.setOnClickListener(this);
        btn_trip.setOnClickListener(this);
        btn_hobby.setOnClickListener(this);
        btn_elec.setOnClickListener(this);
        btn_etc.setOnClickListener(this);
        btn_beauty.setOnClickListener(this);

        dbHelper = new MyDBHelper(RequestActivity.this);

        arr_date = new ArrayList<>();
        try{
            if(dbHelper.get_date()==null) {
                Log.e("get_date: ", "is null");
            }else{
                arr_date.addAll(dbHelper.get_date());
                Log.e("db test: " , ""+dbHelper.get_date().size());
            }
        }catch(Exception e){
            Log.e("error : ","" +e.getStackTrace());
        }

        //영수증 보낼 날짜
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy. MM. dd");
        getTime = sdf.format(date);
        Log.e("getdate: ",""+ getTime.substring(0,8));

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
                btn_beauty.setBackgroundResource(R.color.white);
                btn_etc.setBackgroundResource(R.color.white);
                break;
            case R.id.btn_food :
                category = "음식";
                btn_food.setBackgroundResource(R.color.golden_yellow);
                btn_elec.setBackgroundResource(R.color.white);
                btn_trip.setBackgroundResource(R.color.white);
                btn_hobby.setBackgroundResource(R.color.white);
                btn_beauty.setBackgroundResource(R.color.white);
                btn_etc.setBackgroundResource(R.color.white);
                break;
            case R.id.btn_elec:
                category ="전자기기";
                btn_elec.setBackgroundResource(R.color.golden_yellow);
                btn_food.setBackgroundResource(R.color.white);
                btn_trip.setBackgroundResource(R.color.white);
                btn_hobby.setBackgroundResource(R.color.white);
                btn_beauty.setBackgroundResource(R.color.white);
                btn_etc.setBackgroundResource(R.color.white);
                break;
            case R.id.btn_hobby :
                category = "취미생활";
                btn_hobby.setBackgroundResource(R.color.golden_yellow);
                btn_elec.setBackgroundResource(R.color.white);
                btn_trip.setBackgroundResource(R.color.white);
                btn_food.setBackgroundResource(R.color.white);
                btn_beauty.setBackgroundResource(R.color.white);
                btn_etc.setBackgroundResource(R.color.white);
                break;
            case R.id.btn_etc :
                category = "기타";
                btn_etc.setBackgroundResource(R.color.golden_yellow);
                btn_elec.setBackgroundResource(R.color.white);
                btn_trip.setBackgroundResource(R.color.white);
                btn_food.setBackgroundResource(R.color.white);
                btn_beauty.setBackgroundResource(R.color.white);
                btn_hobby.setBackgroundResource(R.color.white);
                break;
            case R.id.btn_beauty :
                category = "패션및뷰티";
                btn_beauty.setBackgroundResource(R.color.golden_yellow);
                btn_elec.setBackgroundResource(R.color.white);
                btn_trip.setBackgroundResource(R.color.white);
                btn_food.setBackgroundResource(R.color.white);
                btn_hobby.setBackgroundResource(R.color.white);
                btn_etc.setBackgroundResource(R.color.white);
                break;
        }
    }

    //버튼 색만 왜 안바뀌지?
    void Dialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("의뢰서가 등록되었습니다.");
        builder.setMessage("평가가 완료되면 푸시알림이 울립니다.");
        builder.setPositiveButton(Html.fromHtml("<font color='#7C70F4'>확인</font>"),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                                        databaseReference.child("Request receipt").child(uid).push().setValue(new RequestForm(result[0],Integer.parseInt(result[1]),Integer.parseInt(result[2]), title.getText().toString(),Integer.parseInt(price.getText().toString()),content.getText().toString(),uid, getTime, category,token));
                                        dbHelper.insert_ctg(category);
                                        if(!arr_date.contains(getTime.substring(0,8))){
                                            dbHelper.insert_date(getTime.substring(0,8));
//                                            Log.e("dbtest_ok: ", ""+dbHelper.get_date());
                                        }
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
