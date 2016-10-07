package com.mrhot.mrhotvender.Activity;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.mrhot.mrhotvender.Adapter.ItemCodeAdapter;
import com.mrhot.mrhotvender.Adapter.ItemDescriptionAdapter;
import com.mrhot.mrhotvender.Http.HttpCall;
import com.mrhot.mrhotvender.Model.Model_itemCode;
import com.mrhot.mrhotvender.Model.Model_itemDetails;
import com.mrhot.mrhotvender.R;

import java.util.ArrayList;

public class Activity_NewItem extends AppCompatActivity implements View.OnClickListener
{

    Context context = Activity_NewItem.this;
    SwipeRefreshLayout mSwipeRefreshLayout;
    RecyclerView recyclerView;
    private ArrayList<Model_itemDetails> arrayList1;

    SearchView sv;

    static Activity_NewItem activity_newItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__new_item);
        sv = (SearchView) findViewById(R.id.search_view);
        //arrayList = new ArrayAdapter<>()
        activity_newItem = this;
        init();
        setUpToolBar();
        setUpRecyclerView();




    }

    private void setUpToolBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Choose Meal ");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_newitem_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                this.finish();
                return true;

            case R.id.btn_done:
                // action bar button click event
                Toast.makeText(getApplicationContext(),"Done Clicked",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
            /*finish();*/
        }


    }

    public static Activity_NewItem getInstance(){
        return activity_newItem;
    }

    private void init() {

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setHasFixedSize(true);
        //LinearLayoutManager layoutManagerLectureDay = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }




    private void setUpRecyclerView() {


        new HttpCall().getItemDetails(context);
    }




    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
        }

    }
    public void setData(final  Context context, final ArrayList<Model_itemDetails> arrayList1){
        ((Activity_NewItem)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final ItemDescriptionAdapter itemDescriptionAdapter = new ItemDescriptionAdapter(context,arrayList1);
                recyclerView.setAdapter(itemDescriptionAdapter);
                sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String query) {
                        itemDescriptionAdapter.getFilter().filter(query);
                        return false;
                    }
                });


            }
        });

    }
}