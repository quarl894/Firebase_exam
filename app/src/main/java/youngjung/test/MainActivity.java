package youngjung.test;

import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import youngjung.test.Fragment.MainFragment;
import youngjung.test.Fragment.MypageFragment;
import youngjung.test.Fragment.ReceiptFramgent;
import youngjung.test.Model.Profile;
import youngjung.test.Model.RequestForm;
import youngjung.test.View.CustomViewPager;

public class MainActivity extends FragmentActivity{
    BottomBar bar;
    CustomViewPager viewPager;
    static Profile curProfile;
    private DatabaseReference databaseReference;
    private final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    public static ArrayList<RequestForm> receipt = new ArrayList<>();
    public static ArrayList<RequestForm> myRequestReceipt = new ArrayList<>();
    static int i = 0;
    SectionPagerAdapter adapter;
    public static Set<String> uidSet = new HashSet<>();
    StringBuilder st = new StringBuilder();
    int count = 0;
<<<<<<< HEAD
    String token = "";
=======
>>>>>>> a868f088c8848a4fc7256e82beb0af1d31e86750
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bar = findViewById(R.id.bottomBar);
        viewPager = findViewById(R.id.container);
        setupViewPager();
        // Fragmentf home으로 첫화면
        viewPager.setCurrentItem(1);
        bar.setDefaultTabPosition(1);

        //viewpager swipe 막음.
        viewPager.setPagingEnabled(false);

        //FCM
        token = FirebaseInstanceId.getInstance().getToken();

        if (FirebaseInstanceId.getInstance().getToken() != null) {
            Log.d("FCM :", "token = " + FirebaseInstanceId.getInstance().getToken());
        }
        databaseReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref = databaseReference.child("Request receipt");

<<<<<<< HEAD
<<<<<<< HEAD
        //최대 100개만 받기. 먼저 온 순대로 해줘야함.
=======
>>>>>>> ba58d6469ca62a39f7ccf9500ff59920a641c3bf
=======
>>>>>>> 25df959b... DefaultApplication에서 money 컴마 형식 함수 구현.
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //final String result = dataSnapshot.getKey();
                //child 다 받기
                Iterable<DataSnapshot> child = dataSnapshot.getChildren();
                if(!dataSnapshot.getKey().equals(uid)){
                    for(DataSnapshot contact : child){
                        RequestForm rf = contact.getValue(RequestForm.class);

                        receipt.add(new RequestForm(contact.getKey(),rf.getSex(), rf.getMoney(), rf.getMonthly_money(), rf.getTitle(), rf.getPrice(), rf.getContent(), rf.getUuid(),rf.getDate(),rf.getCategory(),rf.getToken()));
                    }
                }else {
                    for (DataSnapshot contact : child) {
                        RequestForm rf = contact.getValue(RequestForm.class);
                        myRequestReceipt.add(new RequestForm(rf.getSex(), rf.getMoney(), rf.getMonthly_money(), rf.getTitle(), rf.getPrice(), rf.getContent(), rf.getUuid(), rf.getDate(), rf.getCategory(),rf.getToken()));
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

<<<<<<< HEAD
    //값 들어올때까지 재귀 호출
    // count값은 애초에 finsih영수증이 없을 경우에 대비해서 애초에 없다면 그냥 보여주기.
    @Override
    protected void onResume() {
        super.onResume();
        if(myRequestReceipt.size() ==0) {
            Toast.makeText(getApplicationContext(),"데이터를 읽어오고 있습니다.",Toast.LENGTH_SHORT).show();
//            Log.e("what the size: ", "" + myRequestReceipt.size());
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(myRequestReceipt.size() ==0 && count<=3){
                        onResume();
                        count++;
                    }else{
                        setupViewPager();
                        Toast.makeText(getApplicationContext(),"데이터 완료.",Toast.LENGTH_SHORT).show();
                        Log.e("what the size33: ", "" + myRequestReceipt.size() + ", " +count);
                    }
                }
            }, 2000);
      //      recreate();
        }else{
            Log.e("what the size: ", "" + myRequestReceipt.size());
        }
=======
        // 평가받은 영수증 경로 따로 빼서 가져오는 것
        // 위에 else문에 같이넣으면 평가 요청한 영수증 목록을 가져옴
        // 여기서 uidSet 사용이유와 myRequestReceipt를 두번 저장 하는 것이 이상함.
        DatabaseReference responseReceipt = databaseReference.child("finished receipt");
        responseReceipt.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Iterable<DataSnapshot> child = dataSnapshot.getChildren();
                if (dataSnapshot.getKey().equals(uid)) {
                    for (DataSnapshot contact : child) {
                        RequestForm rf = contact.getValue(RequestForm.class);
                        if (!uidSet.contains(contact.getKey())) {
                            uidSet.add(contact.getKey());
                            myRequestReceipt.add(new RequestForm(rf.getSex(), rf.getMoney(), rf.getMonthly_money(), rf.getTitle(), rf.getPrice(), rf.getContent(), rf.getUuid(),rf.getDate(),rf.getCategory(),rf.getCheck()));
                        }
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

>>>>>>> a868f088c8848a4fc7256e82beb0af1d31e86750
    }

    // 값 들어올때까지 재귀 호출
    // count값은 애초에 finsih영수증이 없을 경우에 대비해서 애초에 없다면 그냥 보여주기.
    @Override
    protected void onResume() {
        super.onResume();
        if(myRequestReceipt.size() == 0) {
            Toast.makeText(getApplicationContext(),"데이터를 읽어오고 있습니다.",Toast.LENGTH_SHORT).show();
//            Log.e("what the size: ", "" + myRequestReceipt.size());
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(myRequestReceipt.size() ==0 && count<=3){
                        onResume();
                        count++;
                    }else{
                        setupViewPager();
                        Toast.makeText(getApplicationContext(),"데이터 완료.",Toast.LENGTH_SHORT).show();
                        Log.e("myRequestReceipt size: ", "" + myRequestReceipt.size() + ", " +count);
                        Log.e("receipt size: ", "" + receipt.size());

                    }
                }
            }, 2000);
      //      recreate();
        }else{
            Log.e("what the size: ", "" + myRequestReceipt.size());
        }
    }




    // tab들
    private void setupViewPager() {
        adapter = new SectionPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ReceiptFramgent());
        adapter.addFragment(new MainFragment());
        adapter.addFragment(new MypageFragment());

        viewPager.setAdapter(adapter);

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

    public void saveCurUser(Profile p) {
        curProfile = p;
    }

    public Profile getCurUser() {
        return curProfile;
    }

}
