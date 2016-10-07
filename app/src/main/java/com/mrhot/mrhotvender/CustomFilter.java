package com.mrhot.mrhotvender;

import android.widget.Filter;

import com.mrhot.mrhotvender.Adapter.ItemDescriptionAdapter;
import com.mrhot.mrhotvender.Model.Model_itemDetails;

import java.util.ArrayList;

/**
 * Created by KunwarShekhar on 07-Oct-16.
 */

public class CustomFilter extends Filter {

    ItemDescriptionAdapter adapter;
    ArrayList<Model_itemDetails> filterList;

    public CustomFilter(ArrayList<Model_itemDetails> filterList,ItemDescriptionAdapter adapter)
    {
        this.adapter =adapter;
        this.filterList = filterList;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();
        if (constraint != null && constraint.length() >0)
        {
            constraint=constraint.toString().toUpperCase();
            ArrayList<Model_itemDetails>filteredList = new ArrayList<>();

            for (int i = 0 ; i<filterList.size();i++)
            {
                if (filterList.get(i).itemName.toUpperCase().contains(constraint))
                {
                    filteredList.add(filterList.get(i));
                }
            }
            results.count=filteredList.size();
            results.values=filteredList;
        }
        else
        {
            results.count=filterList.size();
            results.values=filterList;
        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {

       adapter.arrayList1 = (ArrayList<Model_itemDetails>) results.values;

        adapter.notifyDataSetChanged();



    }
}
