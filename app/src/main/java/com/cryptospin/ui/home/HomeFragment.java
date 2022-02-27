package com.cryptospin.ui.home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.cryptospin.CustomDialog;
import com.cryptospin.MainActivity;
import com.cryptospin.R;
import com.cryptospin.WalletActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.Hashtable;
import java.util.Map;
import java.util.Random;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    androidx.appcompat.widget.AppCompatButton button;
    TextView textView;
    ImageView wheelRoul,wallet;
    ImageView resultImage;
    View colorClicked1, colorClicked2, colorClicked3, colorClicked4, colorClicked5, colorClicked6;

    Random r;
    int degree = 0, degree_old = 0;
    //his was 37 but i had an extra zero
    //becau[se there is 38 sectors on the wheel (9.47 degrees each)
    private static final float FACTOR = 30;

    public boolean result;
    public boolean userType = false;
    public String result1;

    public boolean isColoredClicked = false;
    public boolean isSpinning = false;
    MediaPlayer mediaPlayer;
    private InterstitialAd mInterstitialAd;
    private AdView mAdView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

////rewarded   ca-app-pub-6970476059348568/9521414644
        InterstitialAd.load(getContext(), "ca-app-pub-3940256099942544/1033173712", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        Log.i("TAG", "onAdLoaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.i("TAG", loadAdError.getMessage());
                        mInterstitialAd = null;
                    }
                });
        CustomDialog cdd = new CustomDialog(getActivity());
        cdd.show();


        button = view.findViewById(R.id.btn_spin);
        textView = (TextView) view.findViewById(R.id.textView);
//        resultImage = findViewById(R.id.result);
        wheelRoul = (ImageView) view.findViewById(R.id.imRoulette);
        button.setClickable(false);
//        button.setBackgroundColor(Color.parseColor("#a9431e"));
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        String userId = prefs.getString("id", "");
        checkIfSpinned(userId);
        wallet = view.findViewById(R.id.wallet);
        wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), WalletActivity.class);
                startActivity(i);
            }
        });

        r = new Random();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                mediaPlayer = MediaPlayer.create(getContext(), R.raw.spin_sound);
//
//                mediaPlayer.start();
                degree_old = degree % 360;

                degree = r.nextInt(360) + 720;
                RotateAnimation rotate = new RotateAnimation(degree_old, degree,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                rotate.setDuration(10800);
                rotate.setFillAfter(true);
                rotate.setInterpolator(new DecelerateInterpolator());
                rotate.setAnimationListener(new Animation.AnimationListener() {

                    @Override
                    public void onAnimationStart(Animation animation) {
                        textView.setText("");
                        isSpinning = true;
//                        Drawable drawable1 = getResources().getDrawable(R.drawable.closed_icon);
//                        resultImage.setImageDrawable(drawable1);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
//                        textView.setText(currentNumber(360 - (degree % 360)));
                        if (mInterstitialAd != null) {
                            mInterstitialAd.show(getActivity());
                        } else {
                            Log.d("TAG", "The interstitial ad wasn't ready yet.");
                        }
                        button.setClickable(false);
//                        Drawable drawable = getResources().getDrawable(R.drawable.rounded_red);
//                        button.setBackground(drawable);
                        button.setText("Spin Again Tomorrow");
                        String prizeId = currentNumber(360 - (degree % 360));
                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
                        String userId = prefs.getString("id", "");


                        if (prizeId.equals("6")) {


                        }

                        if (prizeId.equals("1")) {


                        }
                        if (prizeId.equals("2")) {


                        }
                        if (prizeId.equals("3")) {


                        }


                        if (prizeId.equals("4")) {

                        }
                        if (prizeId.equals("5")) {

                        Log.d("TAG", "onAnimationEnd: " + prizeId);

                        String res = sendResult(prizeId, userId);


//                        int duration = Toast.LENGTH_SHORT;
//                        Toast toast = Toast.makeText(spin_silver.this, prizeId, duration);
//                        toast.show();
                    }}

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        button.setClickable(false);

//                        Drawable drawable = getResources().getDrawable(R.drawable.rounded_red);
//                        button.setBackground(drawable);
                        button.setText("Spin Again Tomorrow");
                    }
                });
                wheelRoul.startAnimation(rotate);
            }
        });

    }


    private String currentNumber(int degrees) {
        String prizeId = "";

        if (degrees >= 0 && degrees < 36) {
            prizeId = "1";
        }
        if (degrees >= 36 && degrees < 72) {
            prizeId = "2";
        }
        if (degrees >= 72 && degrees < 108) {
            prizeId = "3";
        }
        if (degrees >= 108 && degrees < 144) {
            prizeId = "4";
        }
        if (degrees >= 144 && degrees < 180) {
            prizeId = "5";
        }
        if (degrees >= 180 && degrees < 216) {
            prizeId = "5";
        }
        if (degrees >= 216 && degrees < 252) {
            prizeId = "5";
        }
        if (degrees >= 252 && degrees < 288) {
            prizeId = "5";
        }
        if (degrees >= 288 && degrees < 324) {
            prizeId = "5";
        }
        if (degrees >= 324 && degrees < 360) {
            prizeId = "5";
        }

        return prizeId;
    }


    private String sendResult(String prizeId, String userId) {


//        String url = NetworkHelper.getUrl(NetworkHelper.ACTION_SILVER_SPIN_REWARD);
//
//
//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(spin_silver.this);
//
////if(!checkIfSpinned(userId)){
////        if (!prizeId.equals("6")) {
//
//
//        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                        Log.d("spin rewareded ", response);
//                        result1 = "true";
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        result1 = "false";
//                        Log.d("spin not rewareded ", error.getMessage());
//                        Toast.makeText(spin_silver.this, "No internet connection", Toast.LENGTH_LONG).show();
//
//                    }
//                }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//
//                Map<String, String> params = new Hashtable<String, String>();
//                params.put("userId", userId);
//                params.put("prizeId", prizeId);
//                Log.d("TAG", "getParams: " + params);
//
//
//                return params;
//            }
//        };
//        {
//            int socketTimeout = 30000;
//            RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
//            stringRequest.setRetryPolicy(policy);
//            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//            requestQueue.add(stringRequest);
//        }

//    }

//else {
//    button.setClickable(false);
//    button.setBackgroundColor(Color.parseColor("#a9431e"));
//    button.setText("Spin Again Tomorrow");
//
//}


        return result1;
    }

    private Boolean checkIfSpinned(String userId) {

//
//        String url = NetworkHelper.getUrl(NetworkHelper.ACTION_CHECK_SPIN);
//
//
//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(spin_silver.this);
//
//
//        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.d("TAG", "onResponse: " + response.toString());
//
//                        if (response.contains("true")) {
//                            button.setClickable(false);
//                            Drawable drawable = getResources().getDrawable(R.drawable.rounded_red);
//                            button.setBackground(drawable);
//                            button.setText("Spin Again Tomorrow");
//                            result = true;
//
//                        } else {
//                            button.setClickable(true);
//                            Drawable drawable = getResources().getDrawable(R.drawable.rounded);
//                            button.setBackground(drawable);
//                            button.setText("Spin And Win");
//                            result = false;
//
//                        }
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                        Toast.makeText(spin_silver.this, "No internet connection", Toast.LENGTH_LONG).show();
//
//                    }
//                }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//
//                Map<String, String> params = new Hashtable<String, String>();
//                params.put("userId", userId);
//
//
//                return params;
//            }
//        };
//        {
//            int socketTimeout = 30000;
//            RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
//            stringRequest.setRetryPolicy(policy);
//            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//            requestQueue.add(stringRequest);
//        }


        return result;
    }

}