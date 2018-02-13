package youngjung.test.DB.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import youngjung.test.Model.profile;
import youngjung.test.R;

/**
 * Created by HANSUNG on 2018-02-13.
 */

public class profileAdapter extends RecyclerView.Adapter<profileAdapter.ViewHolder> {
    Context mcontext;
    ArrayList<profile> profiles = new ArrayList<>();

    public profileAdapter() {
    }

    public profileAdapter(Context mcontext, ArrayList<profile> profiles) {
        this.mcontext = mcontext;
        this.profiles = profiles;
    }

    @Override
    public void onBindViewHolder(profileAdapter.ViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    //ViewHolder는 사용자 정의 클레스다. 상속된 클레스로 하면 안된다. 내가 직접 만들어야 함.
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView id;

        public ViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.username);
            id = (TextView) v.findViewById(R.id.message);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public void onBindViewHolder(profileAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
