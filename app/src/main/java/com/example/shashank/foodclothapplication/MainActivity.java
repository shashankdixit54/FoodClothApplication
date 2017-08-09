package com.example.shashank.foodclothapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button button;
    Context context;
    public static ArrayList<FoodClothModel> arrayList ;
    public static ArrayList<FoodClothModel> foodArrayList ;
    ListView listView;
    public static final String JSON_CLOTH_URL = "https://sky-firebase.firebaseapp.com/mobmerry/cloths.json";
    public static final String JSON_FOOD_URL = "https://sky-firebase.firebaseapp.com/mobmerry/food.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        listView = (ListView) findViewById(R.id.listView);
        arrayList = new ArrayList<>();
        foodArrayList = new ArrayList<>();


    }

    private void sendRequest(){

        StringRequest stringRequest = new StringRequest(JSON_CLOTH_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        showJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        MySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    private void showJSON(String json){
        ParseJsonClothes pj = new ParseJsonClothes(json);
        arrayList = pj.ParseJson();
       /* MyAdapter adapter = new MyAdapter(context,arrayList);
        listView.setAdapter(adapter);*/
    }

    @Override
    public void onClick(View v) {
        sendRequest();
        sendRequestFood();
    }

    private void parseFoodJson(String json){
        ParseJsonFood pj = new ParseJsonFood(json);
        foodArrayList = pj.ParseJson();

        ArrayList<FoodClothModel> arrayList1 = new ArrayList<>(foodArrayList.size()+arrayList.size());

        for (int i=0; i< foodArrayList.size()+arrayList.size()-1 ;i++){
            if (i<arrayList.size() && arrayList.get(i) != null)
            arrayList1.add(arrayList.get(i));
            if (i<foodArrayList.size() && foodArrayList.get(i) != null)
            arrayList1.add(foodArrayList.get(i));
        }

        MyAdapter adapter = new MyAdapter(context,arrayList1);
        listView.setAdapter(adapter);
    }


    private void sendRequestFood(){

        StringRequest stringRequest = new StringRequest(JSON_FOOD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        parseFoodJson(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        MySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

}
