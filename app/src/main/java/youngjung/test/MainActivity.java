package youngjung.test;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import youngjung.test.DB.DBActivity;
import youngjung.test.Login.LoginActivity;
import youngjung.test.ui.base.baseActivity;
import youngjung.test.ui.dialog.LodingDialog;

public class MainActivity extends baseActivity {
    Button btn, btn_db, btn_graph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        btn_db = (Button) findViewById(R.id.btn_db);
        btn_db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, DBActivity.class);
                startActivity(i);
            }
        });

        btn_graph = findViewById(R.id.btn_grpah);
        btn_graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, GraphActivity.class);
                startActivity(i);
            }
        });
    }

}
