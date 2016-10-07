package com.mrhot.mrhotvender.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.mrhot.mrhotvender.Http.HttpCall;
import com.mrhot.mrhotvender.Model.Model_itemCode;
import com.mrhot.mrhotvender.R;

public class Select_Activity extends AppCompatActivity {

    String[] days = new String[]{"SELECT DAY", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    String[] shifts = new String[]{"SELECT SLOT", "Lunch", "Dinner"};
    Spinner s1, s2;
    Button b1;
    Model_itemCode model_itemCode = new Model_itemCode();
    Context context = Select_Activity.this;
    boolean updatededItem =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        setUpToolBar();
        s1 = (Spinner) findViewById(R.id.spinner_day);
        s2 = (Spinner) findViewById(R.id.spinner_slot);
        ArrayAdapter<String> adapter_day = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, days);
        ArrayAdapter<String> adapter_slot = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, shifts);
        adapter_day.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(adapter_day);
        adapter_slot.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s2.setAdapter(adapter_slot);

        b1 = (Button) findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //if (updatededItem){
                if (s1.getSelectedItemPosition() == 0 && s2.getSelectedItemPosition() == 0 )
                {
                    Snackbar.make(view, "Please select options correctly", Snackbar.LENGTH_LONG).show();
                }

                //getItemCode();
                model_itemCode.day = s1.getSelectedItem().toString();
                model_itemCode.shift = s2.getSelectedItem().toString();

                Bundle bundle = new Bundle();
                bundle.putString("day",model_itemCode.day);
                bundle.putString("shift",model_itemCode.shift);

                Intent intent= new Intent(Select_Activity.this,Activity_code.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
    private void setUpToolBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Select Day And Shift");

    }

    private void getItemCode() {
        new HttpCall().getItemCode(context,s1.getSelectedItem().toString(),s2.getSelectedItem().toString());
    }



}
