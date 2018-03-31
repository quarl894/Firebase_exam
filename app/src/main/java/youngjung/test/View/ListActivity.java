package youngjung.test.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import youngjung.test.Model.RequestForm;
import youngjung.test.Model.firechild;
import youngjung.test.R;
import youngjung.test.ui.base.baseActivity;

/**
 * Created by HANSUNG on 2018-03-01.
 */

public class ListActivity extends baseActivity {
    private TextView show_list;
    private DatabaseReference databaseReference;
    private final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    //ArrayList<String> arr = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        //ArrayList<String> arr = new ArrayList<>();

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        show_list = findViewById(R.id.show_list);

        DatabaseReference ref = databaseReference.child("Request receipt");

        Query query =ref.orderByPriority().equalTo("contenet","hihi").limitToFirst(100);
        String a = query.getRef().getKey();

        Log.e("what???",a);

        // Child 다받기
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                firechild request = dataSnapshot.getValue(firechild.class);
                String result = dataSnapshot.getKey();
                show_list.setText(request.getId());
                Log.e("test commit: ", show_list.getText().toString() + "clone: "+ databaseList().clone().toString()+" key: "+result);
//                if(result!=uid){
//                    arr.add(result);
//                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


//        databaseReference.child("Request receipt").child(uid).addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                RequestForm request = dataSnapshot.getValue(RequestForm.class);
//                show_list.setText(request.getId() + " " + request.getTitle());
//                Log.e("test commit: ", show_list.getText().toString());
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
    }
}
