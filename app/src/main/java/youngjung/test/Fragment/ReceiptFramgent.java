package youngjung.test.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import youngjung.test.R;

/**
 * Created by HANSUNG on 2018-03-25.
 */

public class ReceiptFramgent extends Fragment implements RecyclerViewAdapter.ItemClickListener{
    private Context mContext;
    RecyclerViewAdapter2 adapter;
    String[] data = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48"};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_receipts, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, numberOfColumns));
        adapter = new RecyclerViewAdapter2(mContext, data);
        recyclerView.setAdapter(adapter);
        return rootView;
    }
    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(getContext(), "You clicked number " + adapter.getItem(position), Toast.LENGTH_SHORT).show();
    }
}
