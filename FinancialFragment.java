package youngjung.test.Fragment;

import  android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import youngjung.test.R;

/**
 * Created by DWU on 2018-04-30.
 */

public class FinancialFragment extends Fragment {

    RecyclerView recyclerView;
    GridLayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    static Context context;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.financial_layout, container, false);

        // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);


        //CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        // collapsingToolbarLayout.setTitle("My Toolbar Title");
        // collapsingToolbarLayout.setContentScrimColor(Color.GREEN);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        layoutManager = new GridLayoutManager(context, 3);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);

        return rootView;
    }




    public static void onItemClick(int position) {
        // Detail Activity  -> 제태크 fragment

        // Intent i = new Intent(mContext, MyReceiptDetailActivity.class);
        //i.putExtra("receiptTitle", adapter.getItem(position));
        //startActivity(i);

        Toast.makeText(context, "위치"+position,Toast.LENGTH_SHORT).show();
    }


}
