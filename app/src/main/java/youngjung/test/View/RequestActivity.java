package youngjung.test.View;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
    private String userEmail;


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

        userEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        Button btn_list = findViewById(R.id.btn_list);
        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RequestActivity.this, ListActivity.class);
                startActivity(i);
            }
        });


        Log.d("currentUser", "currentUser = " + FirebaseAuth.getInstance().getCurrentUser().getUid());
        Log.d("currentUser", "currentUser = " + FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
        Log.d("currentUser", "currentUser = " + FirebaseAuth.getInstance().getCurrentUser().getProviderId());
        Log.d("currentUser", "currentUser = " + FirebaseAuth.getInstance().getCurrentUser().getEmail());

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("Request receipt").push().setValue(new RequestForm(userEmail, "dddddd",20000,"ok",2000000));
//                databaseReference.child("Request receipt").child(userName).push().setValue(new RequestForm(userName, title.getText().toString(), Integer.parseInt(price.getText().toString()), content.getText().toString(),20000));
            }
        });
    }
}
