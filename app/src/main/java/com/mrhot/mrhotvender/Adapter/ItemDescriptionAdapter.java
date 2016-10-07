package com.mrhot.mrhotvender.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mrhot.mrhotvender.CustomFilter;
import com.mrhot.mrhotvender.ItemClickListener;
import com.mrhot.mrhotvender.Model.Model_itemDetails;
import com.mrhot.mrhotvender.R;

import java.util.ArrayList;

/**
 * Created by KunwarShekhar on 05-Oct-16.
 */

public class ItemDescriptionAdapter extends RecyclerView
        .Adapter<ItemDescriptionAdapter.MyViewHolder> implements Filterable {

    Context context;
    public ArrayList<Model_itemDetails> arrayList1;
    ArrayList<Model_itemDetails> filterList;
    CustomFilter filter;

    private String[] list;
    private int lastCheckedPosition = -1;


    public ItemDescriptionAdapter(Context context, ArrayList<Model_itemDetails> arrayList1) {
        this.context = context;
        this.arrayList1 = arrayList1;
        this.filterList=arrayList1;
    }

    @Override
    public Filter getFilter() {
        if (filter==null)
        {
            filter=new CustomFilter(filterList,this);
        }
        return filter;
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvname, tvprice, tvcategory;

        ItemClickListener itemClickListener;
        RadioButton rb;


        public MyViewHolder(View itemView) {
            super(itemView);
//            this.tv1 = (TextView) itemView.findViewById(R.id.ItemCode);
            this.tvname = (TextView) itemView.findViewById(R.id.tvName_item);
            this.tvprice = (TextView) itemView.findViewById(R.id.tvPrice_item);
            this.tvcategory = (TextView) itemView.findViewById(R.id.tvCategory_item);
            this.rb = (RadioButton) itemView.findViewById(R.id.rb);
            rb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // lastCheckedPosition = getAdapterPosition();
                   // notifyItemRangeChanged(0,list.length);
                }
            });


        }

        @Override
        public void onClick(View v) {

            this.itemClickListener.onItemClick(v,getLayoutPosition());
        }
        public void setItemClickListener(ItemClickListener ic)
        {
            this.itemClickListener=ic;
        }

    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_new_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Model_itemDetails model_itemDetails = arrayList1.get(position);
//        TextView tv1 = holder.tv1;
        TextView tvname = holder.tvname;
        TextView tvprice = holder.tvprice;
        TextView tvcategory = holder.tvcategory;
        RadioButton rb = holder.rb;
        rb.setChecked(position == lastCheckedPosition);

        // tv1.setText(model_itemDetails.itemCode);
        tvname.setText(model_itemDetails.itemName);
        tvprice.setText("\u20b9 " + model_itemDetails.itemPrice + " \u002f-");
        tvcategory.setText(model_itemDetails.itemCategory);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList1.size();
    }
}