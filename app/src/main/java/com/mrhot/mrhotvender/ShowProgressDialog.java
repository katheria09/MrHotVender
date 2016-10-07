package com.mrhot.mrhotvender;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;

import java.security.AccessControlContext;

/**
 * Created by KunwarShekhar on 24-Sep-16.
 */

public class ShowProgressDialog {

    Dai dai;

    public ShowProgressDialog(Context context) {



        dai = new Dai(context);
        dai.setCancelable(false);
        dai.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
    }

    public void show() {
        dai.show();


    }

    public void dismiss() {
        dai.dismiss();


    }


}
