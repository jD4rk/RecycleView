package it.jdark.android.example.recycleview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements RecycleViewAdapter.OnItemClickListener {

    private RecyclerView mRecycleView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = Item.createItemList(100);

        mRecycleView= (RecyclerView) findViewById(R.id.recycleView);
        mRecycleView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(mLayoutManager);

        mAdapter= new RecycleViewAdapter(items, this);
        mRecycleView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(view.getContext(), "You got a call from ELEMENT at position "+position+"\n"+items.get(position).getName(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onButtonClick(View view, int position) {
        Toast.makeText(view.getContext(), "You got a call from BUTTON at position "+position, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLongClick(View view, int position) {
        Toast.makeText(view.getContext(), "You got a call from LONG CLICK at position "+position, Toast.LENGTH_LONG).show();
    }
}
