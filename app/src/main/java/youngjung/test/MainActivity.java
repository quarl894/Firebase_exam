package youngjung.test;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import youngjung.test.Fragment.MainFragment;
import youngjung.test.Fragment.MypageFragment;
import youngjung.test.Fragment.ReceiptFramgent;

public class MainActivity extends FragmentActivity {
    BottomBar bar;
    GestureDetector gd;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bar = findViewById(R.id.bottomBar);
        viewPager = findViewById(R.id.container);
        setupViewPager();
        viewPager.setCurrentItem(1);

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
