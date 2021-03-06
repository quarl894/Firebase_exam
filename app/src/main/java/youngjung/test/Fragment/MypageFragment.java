package youngjung.test.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import youngjung.test.DB.MyDBHelper;
import youngjung.test.DefaultApplication;
import youngjung.test.MainActivity;
import youngjung.test.Model.Profile;
import youngjung.test.R;

/**
 * Created by HANSUNG on 2018-03-25.
 */

public class MypageFragment extends Fragment {
    private DatabaseReference databaseReference;
    private MyDBHelper dbHelper;
    private TextView tv_name, tv_goal, tv_goal_money, tv_acc_money;
    String[] info = new String[3];
    private DefaultApplication app;
    int sum_money = 0;
    int goal_money = 0;
    MyProgressBar mProgressBar;
    ImageView img_myprofile;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = new DefaultApplication();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mypage, container, false);

        mProgressBar = rootView.findViewById(R.id.main_myPage_progressbar);
        int titleSize = getResources().getDimensionPixelSize(R.dimen.main_progressbar_title);
        int convertColor = ContextCompat.getColor(getActivity(), R.color.colorPrimary);

        mProgressBar.setStrokeWidth(60f);
        mProgressBar.setColor(convertColor);
        mProgressBar.setTextColor(convertColor);
//        mProgressBar.setTextSize(300, 100)
        mProgressBar.setTextSize(titleSize);
        mProgressBar.setMax(100, 0);
        mProgressBar.setProgress(0.0F);

        tv_name = rootView.findViewById(R.id.tv_name);
        tv_goal = rootView.findViewById(R.id.tv_goal);
        tv_goal_money = rootView.findViewById(R.id.tv_goal_money);
        tv_acc_money = rootView.findViewById(R.id.tv_acc_money);
        tv_acc_money.setText(app.Moneyfomat(sum_money));
        img_myprofile = rootView.findViewById(R.id.img_myprofile);

        dbHelper = new MyDBHelper(getContext());
        databaseReference = FirebaseDatabase.getInstance().getReference();
        final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference.child("Member Information").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot.getKey().equals(uid)) {
                    Profile pro = dataSnapshot.getValue(Profile.class);
                    Log.e("test:", pro.getEmail());
                    goal_money = Integer.parseInt(pro.getGoal_money());

                    tv_name.setText(pro.getName());
                    tv_goal.setText(pro.getGoal());
                    tv_goal_money.setText(app.Moneyfomat(goal_money));

                    info[0] = pro.getSex();
                    info[1] = pro.getGoal_money();
                    info[2] = pro.getMonthly_money();
                    mProgressBar.setProgressWithAnimation(DefaultApplication.getPercentage(sum_money, goal_money));

                    if (info[0].equals("남성")) {
                        img_myprofile.setImageResource(R.drawable.man);
                    } else {
                        img_myprofile.setImageResource(R.drawable.finalprofile);
                    }
                    // 영수증 디테일페이지에서 자기 정보 가져오기 위한 것
                    MainActivity a = (MainActivity) getActivity();
                    a.saveCurUser(pro);
                }
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

        RecyclerView main_myPage_list = rootView.findViewById(R.id.main_myPage_list);

        LinearLayoutManager lm = new LinearLayoutManager(getActivity());
        main_myPage_list.setLayoutManager(lm);

        MyPageListAdapter adapter = new MyPageListAdapter(getActivity());
        main_myPage_list.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        sum_money = Integer.parseInt(dbHelper.get_money());
        tv_acc_money.setText(app.Moneyfomat(sum_money));
        mProgressBar.setProgressWithAnimation(DefaultApplication.getPercentage(sum_money, goal_money));
    }
}
