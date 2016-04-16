package it.jdark.android.example.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jDark on 08/11/15.
 */
public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.RecyclerViewHolder> {

    public static final int LAST_POSITION = -1;

    private Context mContext;
    private OnItemClickListener mOnItemClickListener;
    private List<Item> mData;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onButtonClick(View view, int position);
        void onLongClick(View view, int position);
    }


    public RecycleViewAdapter(Context context, List<Item> items, OnItemClickListener onItemClickListener) {
        this.mContext = context;
        this.mData = items;
        this.mOnItemClickListener = onItemClickListener;

    }

    public void add(Item item, int position) {
        position = position == LAST_POSITION ? getItemCount() : position;
        mData.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        if (position == LAST_POSITION && getItemCount() > 0) {
            position = getItemCount() -1;
        }
        if (position > LAST_POSITION && position < getItemCount()) {
            mData.remove(position);
            notifyItemRemoved(position);
        }
    }


    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("RecyclingTest", "onCreateViewHolder method is called");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, null);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {
        Log.d("RecyclingTest", "onBindViewHolder method is called");
        holder.name.setText(mData.get(position).getName());
        holder.surName.setText(mData.get(position).getSurName());
        holder.status.setText(mData.get(position).isStatus() + "");
        holder.row.setTag(-1, mData.get(position));


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
        if (mData.get(position).isStatus()) {
            holder.status.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onButtonClick(v, position);
                }
            });
        }
//        setAnimation(holder.row,position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    /**
     * Here is the key method to apply the animation
     */
    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > LAST_POSITION)
        {
            Animation animation = AnimationUtils.loadAnimation(mContext, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
        }
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
