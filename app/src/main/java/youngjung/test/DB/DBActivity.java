package youngjung.test.DB;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Random;

import youngjung.test.DB.Adapter.chatAdapter;
import youngjung.test.Model.ChatData;
import youngjung.test.R;
import youngjung.test.ui.base.baseActivity;

/**
 * Created by HANSUNG on 2018-02-10.
 */

public class DBActivity extends baseActivity {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    String userName;
    Button sendButton;
    EditText edit;
    ArrayList<ChatData> list;
    RecyclerView.LayoutManager mLinearLayoutManager;
    RecyclerView mRecyclerview;
    chatAdapter mchatAdapter;
    Context mcontext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        mcontext = this.getApplicationContext();
        edit= (EditText) findViewById(R.id.edit);
        userName = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
        sendButton = (Button) findViewById(R.id.btn_ok);

        list = new ArrayList<>();

        mRecyclerview = findViewById(R.id.mrecycerview);
        mRecyclerview.setHasFixedSize(true);
        mLinearLayoutManager = new LinearLayoutManager(mcontext);
        mRecyclerview.setLayoutManager(mLinearLayoutManager);
        mchatAdapter = new chatAdapter(list, mcontext);
        mRecyclerview.setAdapter(mchatAdapter);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatData chatData = new ChatData(userName, edit.getText().toString());  // 유저 이름과 메세지로 chatData 만들기
                databaseReference.child("message").push().setValue(chatData);  // 기본 database 하위 message라는 child에 chatData를 list로 만들기
                edit.setText("");
            }
        });

        databaseReference.child("message").addChildEventListener(new ChildEventListener() {  // message는 child의 이벤트를 수신합니다.
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ChatData chatData = dataSnapshot.getValue(ChatData.class);  // chatData를 가져오고
                list.add(new ChatData(chatData.getUserName(),chatData.getMessage()));  // adapter에 추가합니다.
                mRecyclerview.setAdapter(mchatAdapter);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) { }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) { }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }

}
