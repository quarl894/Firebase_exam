package youngjung.test.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.Locale;

import youngjung.test.DefaultApplication;
import youngjung.test.DB.MyDBHelper;
import youngjung.test.Login.LoginActivity;
import youngjung.test.MainActivity;
import youngjung.test.Model.Profile;
import youngjung.test.R;
import youngjung.test.View.Eval_Activity;
import youngjung.test.View.RequestActivity;
import youngjung.test.ui.dialog.LodingDialog;

/**
 * Created by YoungJung on 2018-03-25.
 */

public class MainFragment extends Fragment {
    private Context mContext;
    Button btn, btn_request, btn_send;
    TextView tv_name, tv_goal, tv_goal_money, tv_acc_money, tv_percentage;
    ImageView img_profile;
    private MyDBHelper dbHelper;
    private DatabaseReference databaseReference;
    String[] info = new String[3];
    DefaultApplication app;
    String sum_money = "0";
    String goal_money = "0";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        app = new DefaultApplication();
        dbHelper = new MyDBHelper(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        tv_name = rootView.findViewById(R.id.tv_name);
        tv_goal = rootView.findViewById(R.id.tv_goal);
        tv_goal_money = rootView.findViewById(R.id.tv_goal_money);
        tv_acc_money = rootView.findViewById(R.id.tv_acc_money);
        tv_percentage = rootView.findViewById(R.id.tv_percentage);
        tv_acc_money.setText(app.Moneyfomat(Integer.parseInt(sum_money)));
        img_profile = rootView.findViewById(R.id.img_profile);

        Log.e("acc_money: ", "" + dbHelper.get_money());
        sum_money = dbHelper.get_money();

        databaseReference = FirebaseDatabase.getInstance().getReference();
        final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference.child("Member Information").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot.getKey().equals(uid)) {
                    Profile pro = dataSnapshot.getValue(Profile.class);
                    goal_money = pro.getGoal_money();

                    Log.e("test:", pro.getEmail());
                    tv_name.setText(pro.getName());
                    tv_goal.setText(pro.getGoal());
                    tv_goal_money.setText(app.Moneyfomat(Integer.parseInt(goal_money)));
                    tv_percentage.setText((int)DefaultApplication.getPercentage(Integer.parseInt(sum_money), Integer.parseInt(goal_money)) + "%");

                    info[0] = pro.getSex();
                    info[1] = pro.getGoal_money();
                    info[2] = pro.getMonthly_money();

                    if (info[0].equals("남성")) {
                        img_profile.setImageResource(R.drawable.man);
                    } else {
                        img_profile.setImageResource(R.drawable.finalprofile);
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

        //로그아웃
//        btn = rootView.findViewById(R.id.btn);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FirebaseAuth.getInstance().signOut();
//                Intent i = new Intent(mContext, LoginActivity.class);
//                startActivity(i);
//                    }
//            });

        // 평가하기
        //Firebase 불러오기가 늦어서 예외처리.
        btn_send = rootView.findViewById(R.id.btn_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.receipt.size() < 3) {
                    Toast.makeText(getActivity(), "현재 평가할 영수증이 없습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent(mContext, Eval_Activity.class);
                    startActivity(i);
                }
            }
        });
        // 의뢰하기
        btn_request = rootView.findViewById(R.id.btn_request);
        btn_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (info[0] == null) {
                    Toast.makeText(getActivity(), "정보를 불러오는 중입니다. 다시 눌러주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent(getActivity(), RequestActivity.class);
                    i.putExtra("infomation", info);
                    startActivity(i);
                }
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        sum_money = dbHelper.get_money();
        tv_acc_money.setText(app.Moneyfomat(Integer.parseInt(sum_money)));
        DefaultApplication.getPercentage(Integer.parseInt(goal_money), Integer.parseInt(sum_money));
    }

}
