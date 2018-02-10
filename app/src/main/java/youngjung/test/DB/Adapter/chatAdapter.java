package youngjung.test.DB.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import youngjung.test.Model.ChatData;
import youngjung.test.R;

/**
 * Created by HANSUNG on 2018-02-10.
 */

public class chatAdapter extends RecyclerView.Adapter<chatAdapter.ViewHolder>{
    private ArrayList<ChatData> items;
    Context mcontext;
    public chatAdapter() {
        super();
    }

    public chatAdapter(ArrayList<ChatData> items, Context mcontext) {
        this.items = items;
        this.mcontext = mcontext;
    }

    //ViewHolder는 사용자 정의 클레스다. 상속된 클레스로 하면 안된다. 내가 직접 만들어야 함.
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView user;
        public TextView message;

        public ViewHolder(View v) {
            super(v);
            user = (TextView) v.findViewById(R.id.username);
            message = (TextView) v.findViewById(R.id.message);
        }
    }

    //보여줄 layout view
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chatitem, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;

    }

    //묶어줄 items
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.user.setText(items.get(position).getUserName());
        holder.message.setText(items.get(position).getMessage());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
