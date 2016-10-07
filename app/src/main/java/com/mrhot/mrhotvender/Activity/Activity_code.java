package com.mrhot.mrhotvender.Activity;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.mrhot.mrhotvender.Adapter.ItemCodeAdapter;
import com.mrhot.mrhotvender.Http.HttpCall;
import com.mrhot.mrhotvender.Model.Model_itemCode;
import com.mrhot.mrhotvender.R;

import java.util.ArrayList;

public class Activity_code extends AppCompatActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener
{

    Context context = Activity_code.this;
    String  day;
    String shift;
    SwipeRefreshLayout mSwipeRefreshLayout;
    RecyclerView recyclerView;

    static Activity_code activity_code;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);
        Bundle bundle =getIntent().getExtras();
        day=bundle.getString("day");
        shift=bundle.getString("shift");

        activity_code = this;

        init();
        setUpToolBar();


        setUpRecyclerView();
    }

    private void setUpToolBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Instant Order Menu");

    }

    public static Activity_code getInstance(){
        return activity_code;
    }

    private void init() {

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(layoutManager);
        this.mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeRefreshLayout);
        this.mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);


    }
    private void setUpRecyclerView() {


        new HttpCall().getItemCode(context,day,shift);
    }




    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
        }

    }
    public void setData(final  Context context, final ArrayList<Model_itemCode> arrayList){
        ((Activity_code)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ItemCodeAdapter itemCodeAdapter = new ItemCodeAdapter(context,arrayList);
                recyclerView.setAdapter(itemCodeAdapter);
                if (mSwipeRefreshLayout.isRefreshing())
                {
                    mSwipeRefreshLayout.setRefreshing(false);
                }

            }
        });

    }
}