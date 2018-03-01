package youngjung.test.View;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                databaseReference.child("Request receipt").child(userName).setValue(new RequestForm(userName, "HI",18000,"ok",2000000));
//                databaseReference.child("Request receipt").child(userName).setValue(new RequestForm(userName, "Hello",20000,"ok",2000000));
            }
        });
    }
}
