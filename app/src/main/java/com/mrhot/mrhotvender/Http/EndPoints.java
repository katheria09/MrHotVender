package com.mrhot.mrhotvender.Http;

import android.os.Looper;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;

/**
 * Created by KunwarShekhar on 24-Sep-16.
 */

public class EndPoints {

    public static AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
    public static AsyncHttpClient syncHttpClient = new SyncHttpClient();

    private static AsyncHttpClient getClient(){
        if (Looper.myLooper()==null)
        {
            return syncHttpClient;
        }
        else
        {
            return asyncHttpClient;
        }
    }


    public static void  getItemDetails (RequestParams requestParams,AsyncHttpResponseHandler asyncHttpResponseHandler){
        getClient().post(Config.URL_GET_ITEM_DETAILS, requestParams, asyncHttpResponseHandler);
    }
    public static void  getInstantDetails (RequestParams requestParams,AsyncHttpResponseHandler asyncHttpResponseHandler){
        getClient().post(Config.URL_GET_INSTANT_DETAILS, requestParams, asyncHttpResponseHandler);
    }


}
