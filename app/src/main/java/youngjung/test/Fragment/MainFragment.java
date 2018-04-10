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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import youngjung.test.Login.LoginActivity;
import youngjung.test.MainActivity;
import youngjung.test.Model.Profile;
import youngjung.test.R;
import youngjung.test.View.Eval_Activity;
import youngjung.test.View.RequestActivity;

/**
 * Created by YoungJung on 2018-03-25.
 */

public class MainFragment extends Fragment {
    private Context mContext;
    Button btn, btn_request,btn_send;
    TextView tv_name, tv_goal, tv_goal_money;
    private DatabaseReference databaseReference;
    String[] info = new String[3];

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        tv_name = rootView.findViewById(R.id.tv_name);
        tv_goal = rootView.findViewById(R.id.tv_goal);
        tv_goal_money = rootView.findViewById(R.id.tv_goal_money);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference.child("Member Information").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if(dataSnapshot.getKey().equals(uid)){
                    Profile pro = dataSnapshot.getValue(Profile.class);
                    Log.e("test:", pro.getEmail());
                    tv_name.setText(pro.getName());
                    tv_goal.setText(pro.getGoal());
                    tv_goal_money.setText(pro.getGoal_money());
                    info[0] = pro.getSex();
                    info[1] = pro.getGoal_money();
                    info[2] = pro.getMonthly_money();
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
        btn = rootView.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(mContext, LoginActivity.class);
                startActivity(i);
                    }
            });

        // 의뢰하기
        //Firebase 불러오기가 늦어서 예외처리.
        btn_send = rootView.findViewById(R.id.btn_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.receipt.size()==0){
                    Toast.makeText(getActivity(), "영수증을 불러오는 중입니다. 다시 눌러주세요.",Toast.LENGTH_SHORT).show();
                }else{
                    Intent i = new Intent(mContext, Eval_Activity.class);
                    startActivity(i);
                }
            }
        });
        //평가하기
        btn_request = rootView.findViewById(R.id.btn_request);
        btn_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(info[0]==null){
                    Toast.makeText(getActivity(), "정보를 불러오는 중입니다. 다시 눌러주세요.",Toast.LENGTH_SHORT).show();
                }else{
                    Intent i = new Intent(getActivity(), RequestActivity.class);
                    i.putExtra("infomation", info);
                    startActivity(i);
                }
            }
        });
        return rootView;
    }
}
