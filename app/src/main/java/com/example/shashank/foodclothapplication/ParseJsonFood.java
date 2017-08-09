package com.example.shashank.foodclothapplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by shashank on 21-07-2017.
 */

public class ParseJsonFood {

    public static final String JSON_ARRAY = "products";
    ArrayList<FoodClothModel> arrayList = new ArrayList<>();

    private JSONArray users = null;

    private String json;

    public ParseJsonFood(String json){
        this.json = json;
    }

    public ArrayList<FoodClothModel> ParseJson(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);

            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                FoodClothModel contactModel = new FoodClothModel();
                contactModel.setName(jo.getString("brand_name"));
                contactModel.setEmail(jo.getString("style_code"));
                contactModel.setMobile(jo.getString("short_title"));
                contactModel.setLogoUrl(jo.getString("store_logo"));
                arrayList.add(contactModel);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return arrayList;
    }
}
