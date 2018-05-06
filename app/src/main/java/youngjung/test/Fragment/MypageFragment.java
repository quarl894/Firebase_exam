package youngjung.test.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import youngjung.test.R;

/**
 * Created by HANSUNG on 2018-03-25.
 */

public class MypageFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mypage, container, false);

        MyProgressBar mProgressBar = rootView.findViewById(R.id.main_myPage_progressbar);
        int titleSize = getResources().getDimensionPixelSize(R.dimen.main_progressbar_title);
        int convertColor = ContextCompat.getColor(getActivity(), R.color.colorPrimary);

        mProgressBar.setStrokeWidth(60f);
        mProgressBar.setColor(convertColor);
        mProgressBar.setTextColor(convertColor);
//        mProgressBar.setTextSize(300, 100)
        mProgressBar.setTextSize(titleSize);
        mProgressBar.setMax(100, 0);
        mProgressBar.setProgress(0.0F);
        mProgressBar.setProgressWithAnimation(50);

        RecyclerView main_myPage_list = rootView.findViewById(R.id.main_myPage_list);
        main_myPage_list.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        main_myPage_list.setAdapter(new MyPageListAdapter(getActivity()));

        return rootView;
    }
}
