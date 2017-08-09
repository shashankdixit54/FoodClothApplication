package com.example.shashank.foodclothapplication;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by shashank on 25-06-2017.
 */

public class MySingleton {

    private static MySingleton mySingleton;
    private static Context context;
    private RequestQueue requestQueue;


    private MySingleton(Context ctx){
        context = ctx;
        getRequestQueue();
    }

    private RequestQueue getRequestQueue(){

        if (requestQueue == null){

            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;

    }

    public static synchronized MySingleton getInstance(Context context){

        if (mySingleton == null){
            mySingleton= new MySingleton(context);
        }
        return mySingleton;
    }

    public<T> void addToRequestQueue(Request<T> request){
        requestQueue.add(request);
    }

}
