package com.mrhot.mrhotvender.Http;

import android.content.Context;

import com.mrhot.mrhotvender.Activity.Activity_NewItem;
import com.mrhot.mrhotvender.Activity.Activity_code;
import com.mrhot.mrhotvender.Model.Model_itemCode;
import com.mrhot.mrhotvender.Model.Model_itemDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Created by KunwarShekhar on 24-Sep-16.
 */

public class DataParser {


    public void parseInstantDetails(Context context, JSONObject jsonObject) {

        ArrayList<Model_itemCode> arrayList = new ArrayList<>();

        try {
            {

                JSONArray message = jsonObject.getJSONArray("data");

                boolean success = false;

                for (int i = 0; i < message.length(); i++) {

                    JSONObject temp = message.getJSONObject(i);
                    if (i == 0) {
                        if (temp.getString("success").equals("1")) {
                            success = true;
                        }
                    }
                    if (i != 0 && success) {
                        Model_itemCode modalItemCode = new Model_itemCode();
                        int a=i;
                        modalItemCode.itemSrNo = String.valueOf(a);
                       // modalItemCode.itemCode = temp.getString("itemCode");
                        modalItemCode.itemName = temp.getString("itemName");
                        modalItemCode.itemPrice = temp.getString("itemPrice");
                        modalItemCode.itemDescription = temp.getString("itemDescription");
                        modalItemCode.itemCategory = temp.getString("itemCategory");

                        arrayList.add(modalItemCode);
                    }


                }

                Activity_code.getInstance().setData(context, arrayList);


            }
        } catch (JSONException e1) {
            e1.printStackTrace();
        }


    }

    public void parseItemDetails(Context context, JSONObject jsonObject) {

        ArrayList<Model_itemDetails> arrayList1 = new ArrayList<>();

        try {
            {

                JSONArray message = jsonObject.getJSONArray("data");

                boolean success = false;

                for (int i = 0; i < message.length(); i++) {

                    JSONObject temp = message.getJSONObject(i);
                    if (i == 0) {
                        if (temp.getString("success").equals("1")) {
                            success = true;
                        }
                    }
                    if (i != 0 && success) {
                        Model_itemDetails modalItemDetails = new Model_itemDetails();
                        modalItemDetails.itemName = temp.getString("itemName");
                        modalItemDetails.itemPrice = temp.getString("itemPrice");
                        modalItemDetails.itemCategory = temp.getString("itemCategory");

                        arrayList1.add(modalItemDetails);
                    }


                }

                Activity_NewItem.getInstance().setData(context, arrayList1);


            }
        } catch (JSONException e1) {
            e1.printStackTrace();
        }


    }

}
