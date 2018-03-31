package youngjung.test.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import java.util.zip.Inflater;

import youngjung.test.Login.LoginActivity;
import youngjung.test.MainActivity;
import youngjung.test.R;
import youngjung.test.ReceiptsActivity;
import youngjung.test.View.Eval_Activity;

/**
 * Created by YoungJung on 2018-03-25.
 */

public class MainFragment extends Fragment {
    private Context mContext;
    Button btn, btn_request,btn_send;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        //로그아웃
        btn = rootView.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        Intent i = new Intent(mContext, LoginActivity.class);
                        startActivity(i);
                    }
            });

        // 의뢰하기
        btn_send = rootView.findViewById(R.id.btn_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, Eval_Activity.class);
                startActivity(i);
            }
        });
        //평가하기
        btn_request = rootView.findViewById(R.id.btn_request);
        btn_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, ReceiptsActivity.class);
                startActivity(i);
            }
        });
        return rootView;
    }
}
