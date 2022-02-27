package com.cryptospin;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.ClientError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

public class NetworkHelper {

    public static final String TAG = NetworkHelper.class.getName();

    //    public static final String SERVER_IP = "10.0.2.2/wen-arkhas-web";
//    public static final String SERVER_IP = "5.189.150.68/room";

//    public static final String SERVER_IP = "192.168.0.158/room_web";
//    public static final String SERVER_IP = "192.168.0.158/room_web";
//    public static final String SERVER_IP = "192.168.43.196/room_web";
    public static final String SERVER_IP = "192.168.0.103/crypto_spin";
//    public static final String SERVER_IP = "192.168.10.105/farmer_house";


    public static final String ACTION_GET_USER_WALLET = "mobile/get-user-wallet";



    public static String getUrl(String action) {
        String serverURL = "http://" + SERVER_IP + "/web/index.php?r=";
        String url = serverURL + "api/" + action;

        return url;
    }


    public static void handleError(VolleyError error) {
        NetworkResponse response = error.networkResponse;
        String statusCode = "", message = "";
        //statCode
        if (response != null && response.data != null) {
            statusCode = ": " + Integer.toString(error.networkResponse.statusCode);
        }
        //message
        try {
            if (error instanceof NetworkError) {
                message = "networkError";
            } else if (error instanceof ClientError) {
                message = "clientError";
            } else if (error instanceof ServerError) {
                message = "serverError";
            } else if (error instanceof AuthFailureError) {
                message = "authFailureError";
            } else if (error instanceof ParseError) {
                message = "parseError";
            } else if (error instanceof NoConnectionError) {
                message = "noConnectionError";
            } else if (error instanceof TimeoutError) {
                message = "timeoutError";
            } else {
                message = "defaultError";
            }
            Log.d(TAG, "Network message error: " + message + " " + statusCode);

        } catch (Exception e) {

        }
    }

}
