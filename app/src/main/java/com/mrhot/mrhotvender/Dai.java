package com.mrhot.mrhotvender;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ProgressBar;

/**
 * Created by KunwarShekhar on 24-Sep-16.
 */

public class Dai  extends Dialog {

    ProgressBar progressBar;

    public Dai(Context context){
        super(context);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.sialog);

    }
}