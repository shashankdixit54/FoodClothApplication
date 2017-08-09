package com.example.shashank.foodclothapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

import java.util.ArrayList;

/**
 * Created by shashank on 26-06-2017.
 */

public class MyAdapter extends BaseAdapter {

    Context context;
    ArrayList<FoodClothModel> contactModels;

    public MyAdapter(Context context, ArrayList<FoodClothModel> contactModels) {

        this.contactModels = contactModels;
        this.context = context;
    }

    @Override
    public int getCount() {
        return contactModels.size();
    }

    @Override
    public Object getItem(int position) {
        return contactModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if ( v == null){
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.row_item, null);
        }

        TextView nameTv = (TextView)v.findViewById(R.id.name);
        TextView mobileTv = (TextView)v.findViewById(R.id.mobile);
        TextView emailTv = (TextView)v.findViewById(R.id.email);
        final ImageView imgView = (ImageView)v.findViewById(R.id.imageView);

        nameTv.setText(contactModels.get(position).getName());
        mobileTv.setText(contactModels.get(position).getMobile());
        emailTv.setText(contactModels.get(position).getEmail());

        String imageURL = contactModels.get(position).getLogoUrl();
        ImageRequest request = new ImageRequest(imageURL,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {

                        imgView.setImageBitmap(response);

                    }
                },
                90, 90,
                Bitmap.Config.ARGB_8888,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.d("error",error.getMessage().toString());
                    }
                });
        MySingleton.getInstance(context).addToRequestQueue(request);

        return v;
    }
}
