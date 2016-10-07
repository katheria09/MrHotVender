package com.mrhot.mrhotvender.Http;


import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.mrhot.mrhotvender.Activity.Activity_code;
import com.mrhot.mrhotvender.ShowProgressDialog;

import org.json.JSONObject;


import cz.msebera.android.httpclient.Header;

/**
 * Created by KunwarShekhar on 24-Sep-16.
 */

public class HttpCall {
    int result;
    ShowProgressDialog showProgressDialog;

    private void defineDialog(Context context) {
        showProgressDialog = new ShowProgressDialog(context);
    }

    public void getItemCode (final Context context, String day, String shift) {
        RequestParams requestParams = new RequestParams();
        requestParams.put("day", day);
        requestParams.put("shift", shift);
        defineDialog(context);

        EndPoints.getInstantDetails(requestParams, new JsonHttpResponseHandler() {

                    @Override
                    public void onStart() {
                        showProgressDialog.show();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                        showProgressDialog.dismiss();

                        new DataParser().parseInstantDetails(context, response);


                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers,
                                          Throwable throwable, JSONObject errorResponse) {
                        Toast.makeText(context, "Please try again later", Toast.LENGTH_SHORT).show();
                        showProgressDialog.dismiss();
                    }

                }
        );

    }

    public void getItemDetails (final Context context) {
        RequestParams requestParams = new RequestParams();
        //requestParams.put("day", day);
        //requestParams.put("shift", shift);
        defineDialog(context);

        EndPoints.getItemDetails(requestParams, new JsonHttpResponseHandler() {

                    @Override
                    public void onStart() {
                        showProgressDialog.show();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                        showProgressDialog.dismiss();

                        new DataParser().parseItemDetails(context, response);


                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers,
                                          Throwable throwable, JSONObject errorResponse) {
                        Toast.makeText(context, "Please try again later", Toast.LENGTH_SHORT).show();
                        showProgressDialog.dismiss();
                    }

                });

    }
}


