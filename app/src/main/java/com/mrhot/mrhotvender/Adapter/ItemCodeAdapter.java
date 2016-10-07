package com.mrhot.mrhotvender.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mrhot.mrhotvender.Activity.Activity_NewItem;
import com.mrhot.mrhotvender.Activity.LoginActivity;
import com.mrhot.mrhotvender.Activity.Select_Activity;
import com.mrhot.mrhotvender.Model.Model_itemCode;
import com.mrhot.mrhotvender.R;

import java.util.ArrayList;

/**
 * Created by KunwarShekhar on 24-Sep-16.
 */

public class ItemCodeAdapter extends RecyclerView.Adapter<ItemCodeAdapter.MyViewHolder> {


    private ArrayList<Model_itemCode> arrayList;
    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_Srno,tv_Name,tv_Price,tv_Description,tv_Category;
        Button btn;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.tv_Srno = (TextView) itemView.findViewById(R.id.tvSrNo);
            this.tv_Name = (TextView) itemView.findViewById(R.id.tvName);
            this.tv_Price = (TextView) itemView.findViewById(R.id.tvPrice);
            //this.tv_Description = (TextView) itemView.findViewById(R.id.tvDescription);
            this.tv_Category = (TextView) itemView.findViewById(R.id.tvCategory);
            this.btn = (Button) itemView.findViewById(R.id.button2);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(context,Activity_NewItem.class);
                    context.startActivity(intent);
                }
            });



        }
    }
    public ItemCodeAdapter(Context context, ArrayList<Model_itemCode> people) {
        this.arrayList = people;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_itemchange, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Model_itemCode model_itemCode = arrayList.get(position);
        TextView tvSrNo = holder.tv_Srno;
        TextView tvName = holder.tv_Name;
        TextView tvPrice = holder.tv_Price;
        TextView tvCategory = holder.tv_Category;

        tvSrNo.setText(model_itemCode.itemSrNo+".");
        tvName.setText(" " + model_itemCode.itemName);
        tvPrice.setText("\u20b9 " + model_itemCode.itemPrice + " \u002f-");
        tvCategory.setText(model_itemCode.itemCategory);



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
