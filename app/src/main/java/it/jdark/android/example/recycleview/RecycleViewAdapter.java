package it.jdark.android.example.recycleview;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jDark on 08/11/15.
 */
public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.RecyclerViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onButtonClick(View view, int position);
        void onLongClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;
    private List<Item> items;

    public RecycleViewAdapter(List<Item> items, OnItemClickListener onItemClickListener) {
        this.items = items;
        this.mOnItemClickListener = onItemClickListener;

    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("RecyclingTest","onCreateViewHolder method is called");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, null);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {
        Log.d("RecyclingTest", "onBindViewHolder method is called");
        holder.name.setText(items.get(position).getName());
        holder.surName.setText(items.get(position).getSurName());
        holder.status.setText(items.get(position).isStatus()+"");

        // Declare the listener (CLick and LongClick) for the single element of the RecycleView
        holder.row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(v, position);
            }
        });
        holder.row.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mOnItemClickListener.onLongClick(v, position);
                return true;
            }
        });
        // Declare the listener for the click on the button of the single element of the RecycleView
        if (items.get(position).isStatus()) {
            holder.status.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onButtonClick(v, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout row;
        private TextView name;
        private TextView surName;
        private Button status;

        public RecyclerViewHolder(final View itemView) {
            super(itemView);
            row = (RelativeLayout) itemView.findViewById(R.id.row);
            name = (TextView) itemView.findViewById(R.id.name);
            surName = (TextView) itemView.findViewById(R.id.surName);
            status = (Button) itemView.findViewById(R.id.status);
        }
    }
}
