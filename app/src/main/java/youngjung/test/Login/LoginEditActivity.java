package youngjung.test.Login;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import youngjung.test.MainActivity;
import youngjung.test.Model.Profile;
import youngjung.test.R;
import youngjung.test.ui.base.baseActivity;
// 이름, 이메일, 목표, 목표금액, 한달 생활비
public class LoginEditActivity extends baseActivity {
    Button btn_editok, btn_male, btn_female;
    EditText edit_name, edit_goal, edit_goal_money, edit_month_money;
    String sex= "";
    CheckBox ch;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String str = "이용약관과 개인정보보호정책에 동의(필수)";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_edit);

        btn_editok = findViewById(R.id.btn_editok);
        btn_male = findViewById(R.id.btn_male);
        btn_female = findViewById(R.id.btn_female);

        edit_name = findViewById(R.id.edit_name);
        edit_goal = findViewById(R.id.edit_goal);
        edit_goal_money = findViewById(R.id.edit_goal_money);
        edit_month_money = findViewById(R.id.edit_month_money);
        ch = findViewById(R.id.checkBox);
        SpannableStringBuilder ssb = new SpannableStringBuilder(str);
        ssb.setSpan(new ForegroundColorSpan(Color.parseColor("#d0021b")), 18, 22, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        ch.setText(ssb);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        edit_name.setText(user.getDisplayName());

        btn_male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_male.setBackgroundResource(R.drawable.main_btn);
                btn_female.setBackgroundResource(R.drawable.saving_finish_btn);
                sex = "남성";
            }
        });
        btn_female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_female.setBackgroundResource(R.drawable.main_btn);
                btn_male.setBackgroundResource(R.drawable.saving_finish_btn);
                sex = "여성";
            }
        });

        //Firebase Meber information에 저장.
        btn_editok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("information: ",edit_name.getText().toString() + ", "+ edit_goal.getText().toString() + ", " + edit_goal_money.getText().toString() + ", " + edit_month_money.getText());
                if(sex.equals("") || edit_name.getText().toString().equals("") || edit_goal.getText().toString().equals("") || edit_goal_money.getText().equals("") || edit_month_money.getText().equals("")){
                    Toast.makeText(getApplicationContext(),"모든 항목을 입력해주세요.",Toast.LENGTH_SHORT).show();
                }else{
                    Profile profile = new Profile(user.getDisplayName(), user.getEmail(),user.getUid(),edit_goal.getText().toString(),edit_goal_money.getText().toString(),edit_month_money.getText().toString(),sex);
                    databaseReference.child("Member Information").child(user.getUid()).setValue(profile);
                    Intent i = new Intent(LoginEditActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
