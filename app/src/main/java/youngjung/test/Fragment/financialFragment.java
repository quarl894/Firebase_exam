package youngjung.test.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import youngjung.test.R;

public class financialFragment extends Fragment {
    private Context mContext;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    static Context context;
    private TextView tv_finan;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.activity_fin, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerView_fin);
        tv_finan = rootView.findViewById(R.id.tv_finan);
        tv_finan.setText("충동구매를 참았다면 실전이다!\n제태크 전략을 알고 싶다면 코인을 누르세요.");
        tv_finan.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        //RecyclerView scroll 금지
        layoutManager = new LinearLayoutManager(context) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);

        return rootView;
    }

    public static void onItemClick(int position) {
        Toast.makeText(context, "위치"+position,Toast.LENGTH_SHORT).show();
    }

}
