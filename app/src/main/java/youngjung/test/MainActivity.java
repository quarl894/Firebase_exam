package youngjung.test;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import youngjung.test.Fragment.MainFragment;
import youngjung.test.Fragment.MypageFragment;
import youngjung.test.Fragment.ReceiptFramgent;
import youngjung.test.Model.RequestForm;
import youngjung.test.Model.firechild;

public class MainActivity extends FragmentActivity {
    BottomBar bar;
    GestureDetector gd;
    ViewPager viewPager;
    private DatabaseReference databaseReference;
    private final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    public static ArrayList<String> arr = new ArrayList<>();
    public static ArrayList<RequestForm> form = new ArrayList<>();
    public static ArrayList<DataSnapshot> arr2 = new ArrayList<>();
    static int i = 0;
    StringBuilder st = new StringBuilder();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bar = findViewById(R.id.bottomBar);
        viewPager = findViewById(R.id.container);
        setupViewPager();
        viewPager.setCurrentItem(1);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref = databaseReference.child("Request receipt");

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //final String result = dataSnapshot.getKey();
                //child 다 받기
                Iterable<DataSnapshot> child = dataSnapshot.getChildren();
                if(!dataSnapshot.getKey().equals(uid)){
                    for(DataSnapshot contact : child){
                        RequestForm rf = contact.getValue(RequestForm.class);
                        Log.e("rf: ",rf.getId() + "name: "+rf.getTitle());
                    }
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

    }


    // tab들
    private void setupViewPager() {
        SectionPagerAdapter adapter = new SectionPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ReceiptFramgent());
        adapter.addFragment(new MainFragment());
        adapter.addFragment(new MypageFragment());

        viewPager.setAdapter(adapter);


        //swipe를 통한 이동 막음.
        gd = new GestureDetector(new GestureDetector.SimpleOnGestureListener());
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        gd.onTouchEvent(event);
                        break;
                }
                return true;
            }
        });

        bar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch(tabId){
                    case R.id.tab_list:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.tab_home:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.tab_mypage:
                        viewPager.setCurrentItem(2);
                        break;
                }

            }
        });
    }

//    public void initFragment(){
//        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.add(R.id.container,mainFragment);
//        transaction.addToBackStack(null);
//        transaction.commit();
//    }
}
