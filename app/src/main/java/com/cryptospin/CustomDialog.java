package com.cryptospin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;

public class CustomDialog extends Dialog{



        public Activity c;
        public Dialog d;
        public Button yes, no;

        public CustomDialog(Activity a) {
            super(a);
            // TODO Auto-generated constructor stub
            this.c = a;
        }



    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.activity_custom_dialog);


        }


    }
