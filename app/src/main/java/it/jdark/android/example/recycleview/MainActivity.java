package it.jdark.android.example.recycleview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnItemSelected;

public class MainActivity extends AppCompatActivity implements RecycleViewAdapter.OnItemClickListener {

    private RecyclerView mRecycleView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Spinner spinner;

    private List<Item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        spinnerInit();

        items = Item.createItemList(10);

        mRecycleView= (RecyclerView) findViewById(R.id.recycleView);
        mRecycleView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(mLayoutManager);

        mAdapter= new RecycleViewAdapter(this, items, this);
        // Setup divider item decoration
        mRecycleView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        mRecycleView.setAdapter(mAdapter);

        // Auto-Scroll to a specific position
        mLayoutManager.smoothScrollToPosition(mRecycleView, null, 20);    // Set auto-scroll to that position

    }

//    @OnItemSelected(R.id.spinner)
//    void onItemSelected(int position) {
//        switch (position) {
//            case 0: mRecycleView.setAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left));
//            case 1: mRecycleView.setAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right));
//        }
//        Toast.makeText(this, "Selected position " + position + "!", Toast.LENGTH_SHORT).show();
//    }

    private void spinnerInit() {
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.animators, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        mRecycleView.setAnimation(AnimationUtils.loadAnimation(parent.getContext(), android.R.anim.slide_in_left));
                    case 1:
                        mRecycleView.setAnimation(AnimationUtils.loadAnimation(parent.getContext(), android.R.anim.slide_out_right));
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    @Override
    public void onItemClick(View view, int position) {
//        Log.w("PROVA", "--- " + ((Item)view.getTag(-1)).getName());
        Toast.makeText(view.getContext(), "You got a call from ELEMENT at position "+position+"\n"+items.get(position).getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onButtonClick(View view, int position) {
        Toast.makeText(view.getContext(), "You got a call from BUTTON at position "+position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLongClick(View view, int position) {
        Toast.makeText(view.getContext(), "You got a call from LONG CLICK at position "+position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.recycle, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add) {
            ((RecycleViewAdapter)mRecycleView.getAdapter()).add(new Item("Name Added", "Surname Added", true), RecycleViewAdapter.LAST_POSITION);

            return true;
        }
        if (id == R.id.action_remove) {
            ((RecycleViewAdapter)mRecycleView.getAdapter()).remove(RecycleViewAdapter.LAST_POSITION);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
