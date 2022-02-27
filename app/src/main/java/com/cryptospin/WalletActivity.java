package com.cryptospin;

import static com.cryptospin.R.drawable.ic_yello_button;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.cryptospin.ui.models.Wallet;

import java.util.HashMap;
import java.util.Map;

public class WalletActivity extends AppCompatActivity {
    TextView polcCredit,usdtCredit,dodgeCredit,adaCredit,aluCredit,xrpCredit,ftmCredit;
    Button polcClaim,usdtClaim,dodgeClaim,adaClaim,aluClaim,xrpClaim,ftmClaim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor("#2e4360"));
        getData();
        polcCredit = findViewById(R.id.polcCredit);
        usdtCredit = findViewById(R.id.usdtCredit);
        dodgeCredit = findViewById(R.id.dodgeCredit);
        adaCredit = findViewById(R.id.adaCredit);
        aluCredit = findViewById(R.id.aluCredit);
        xrpCredit = findViewById(R.id.xrpCredit);
        ftmCredit = findViewById(R.id.ftmCredit);

        polcClaim = findViewById(R.id.polcClaim);
        usdtClaim = findViewById(R.id.usdtClaim);
        dodgeClaim = findViewById(R.id.dodgeClaim);
        adaClaim = findViewById(R.id.adaClaim);
        aluClaim = findViewById(R.id.aluClaim);
        xrpClaim = findViewById(R.id.xrpClaim);
        ftmClaim = findViewById(R.id.ftmClaim);
        polcClaim.setEnabled(false);
        usdtClaim.setEnabled(false);
        dodgeClaim.setEnabled(false);
        adaClaim.setEnabled(false);
        aluClaim.setEnabled(false);
        xrpClaim.setEnabled(false);
        ftmClaim.setEnabled(false);

        polcClaim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WalletActivity.this,Claim.class);
                startActivity(i);
                finish();
            }
        });
        usdtClaim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WalletActivity.this,Claim.class);
                startActivity(i);
                finish();
            }
        });
        dodgeClaim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WalletActivity.this,Claim.class);
                startActivity(i);
                finish();
            }
        });
        adaClaim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WalletActivity.this,Claim.class);
                startActivity(i);
                finish();
            }
        });
        xrpClaim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WalletActivity.this,Claim.class);
                startActivity(i);
                finish();
            }
        });
        ftmClaim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WalletActivity.this,Claim.class);
                startActivity(i);
                finish();
            }
        });
        ftmClaim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WalletActivity.this,Claim.class);
                startActivity(i);
                finish();
            }
        });


    }



    public void getData(){

        Log.d("TAG", "getProfileData: ");
        final ProgressDialog dialog = ProgressDialog.show(WalletActivity.this, "",
                "Please wait...", true);


        String url = NetworkHelper.getUrl(NetworkHelper.ACTION_GET_USER_WALLET);
        Log.d("TAG", "onResponse: " + url);
        Map<String, String> params = new HashMap();

        params.put("userId", "1");



        GsonRequest<Wallet> myGsonRequest = new GsonRequest<Wallet>(Request.Method.POST, url, Wallet.class, null, params,
                new Response.Listener<Wallet>() {
                    @Override
                    public void onResponse(Wallet userWallet) {
                        polcCredit.setText(userWallet.getPolc());
                        usdtCredit.setText(userWallet.getUsdt());
                        dodgeCredit.setText(userWallet.getDodge());
                        adaCredit.setText(userWallet.getAda());
                        aluCredit.setText(userWallet.getAlu());
                        xrpCredit.setText(userWallet.getXrp());
                        ftmCredit.setText(userWallet.getFtm());

                        if(userWallet.getPolc().equals("20")){
                            polcClaim.setEnabled(true);
                            polcClaim.setText("Claim");
                            polcClaim.setBackground(getResources().getDrawable(ic_yello_button));
                            polcClaim.setTextColor(Color.parseColor("#C1272D"));
                        }
                        if(userWallet.getAda().equals("10")){
                            adaClaim.setEnabled(true);
                            adaClaim.setText("Claim");
                            adaClaim.setBackground(getResources().getDrawable(ic_yello_button));
                            adaClaim.setTextColor(Color.parseColor("#C1272D"));
                        }
                        if(userWallet.getAlu().equals("50")){
                            aluClaim.setEnabled(true);
                            aluClaim.setText("Claim");
                            aluClaim.setBackground(getResources().getDrawable(ic_yello_button));
                            aluClaim.setTextColor(Color.parseColor("#C1272D"));
                        }
                        if(userWallet.getUsdt().equals("10")){
                            usdtClaim.setEnabled(true);
                            usdtClaim.setText("Claim");
                            usdtClaim.setBackground(getResources().getDrawable(ic_yello_button));
                            usdtClaim.setTextColor(Color.parseColor("#C1272D"));
                        }
                        if(userWallet.getXrp().equals("10")){
                            xrpClaim.setEnabled(true);
                            xrpClaim.setText("Claim");
                            xrpClaim.setBackground(getResources().getDrawable(ic_yello_button));
                            xrpClaim.setTextColor(Color.parseColor("#C1272D"));
                        }
                        if(userWallet.getDodge().equals("30")){
                            dodgeClaim.setEnabled(true);
                            dodgeClaim.setText("Claim");
                            dodgeClaim.setBackground(getResources().getDrawable(ic_yello_button));
                            dodgeClaim.setTextColor(Color.parseColor("#C1272D"));
                        }
                        if(userWallet.getFtm().equals("5")){
                            ftmClaim.setEnabled(true);
                            ftmClaim.setText("Claim");
                            ftmClaim.setBackground(getResources().getDrawable(ic_yello_button));
                            ftmClaim.setTextColor(Color.parseColor("#C1272D"));
                        }







                        dialog.dismiss();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        dialog.dismiss();
                        NetworkHelper.handleError(error);
                    }
                });

        VolleySingleton.getInstance(WalletActivity.this).addToRequestQueue(myGsonRequest);
    }
}