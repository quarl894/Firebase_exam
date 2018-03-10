package youngjung.test;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import youngjung.test.DB.DBActivity;
import youngjung.test.Login.LoginActivity;
import youngjung.test.View.Eval_Activity;
import youngjung.test.View.ListActivity;
import youngjung.test.View.RequestActivity;
import youngjung.test.ui.base.baseActivity;
import youngjung.test.ui.dialog.LodingDialog;
public class MainActivity extends baseActivity {
    Button btn, btn_request;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // dialog 띄우기
                showLoading(MainActivity.this);
                // 1초 뒤 끝내기
                postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        FirebaseAuth.getInstance().signOut();
                        hideLoadingDialog();
                        Intent i = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(i);
                        finish();
                    }
                },1000);
            }
        });


        //아직 하는 중 Fragment로 바꿔야 할듯
//        BottomBar bottomBar = findViewById(R.id.bottomBar);
//        bottomBar.setActivated(false);
//
//        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
//            @Override
//            public void onTabSelected(int tabId) {
//                switch (tabId){
//                    case R.id.tab_list :
//                        Intent i = new Intent(getApplicationContext(), ListActivity.class);
//                        startActivity(i);
//                        break;
//                    case R.id.tab_mypage:
//                        Intent i3 = new Intent(getApplicationContext(), DBActivity.class);
//                        startActivity(i3);
//                        break;
//                }
//
//            }
//        });
        btn_request = findViewById(R.id.btn_request);
        btn_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Eval_Activity.class);
                startActivity(i);
            }
        });
    }
}
