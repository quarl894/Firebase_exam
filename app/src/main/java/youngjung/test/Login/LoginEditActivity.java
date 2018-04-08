package youngjung.test.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import youngjung.test.MainActivity;
import youngjung.test.R;
import youngjung.test.ui.base.baseActivity;
// 이름, 이메일, 목표, 목표금액, 한달 생활비
public class LoginEditActivity extends baseActivity {
    Button btn_editok, btn_male, btn_female;
    EditText edit_name, edit_email, edit_goal, edit_goal_money, edit_month_money;
    int sex;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_edit);

        btn_editok = findViewById(R.id.btn_editok);
        btn_male = findViewById(R.id.btn_male);
        btn_female = findViewById(R.id.btn_female);

        edit_name = findViewById(R.id.edit_name);
        edit_email = findViewById(R.id.edit_email);
        edit_goal = findViewById(R.id.edit_goal);
        edit_goal_money = findViewById(R.id.edit_goal_money);
        edit_month_money = findViewById(R.id.edit_month_money);

        sex = -1;

        btn_male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_male.setBackgroundResource(R.color.golden_yellow);
                btn_female.setBackgroundResource(R.color.white);
                sex = 0;
            }
        });
        btn_female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_female.setBackgroundResource(R.color.golden_yellow);
                btn_male.setBackgroundResource(R.color.white);
            }
        });

        btn_editok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("information: ",edit_name.getText().toString() + ", "+ edit_email.getText().toString()+ ", "+ edit_goal.getText().toString() + ", " + edit_goal_money.getText().toString() + ", " + edit_month_money.getText());
                if(sex ==-1 || edit_name.getText().toString().equals("") || edit_email.getText().toString().equals("") || edit_goal.getText().toString().equals("") || edit_goal_money.getText().equals("") || edit_month_money.getText().equals("")){
                    Toast.makeText(getApplicationContext(),"모든 항목을 입력해주세요.",Toast.LENGTH_SHORT).show();
                }else{
                    Intent i = new Intent(LoginEditActivity.this, MainActivity.class);
                    startActivity(i);
                }
            }
        });
    }
}
