package com.cruxitech.android.invenapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by kushtawar on 30/07/16.
 */
public class HomeAdapter extends ArrayAdapter<String> {

    int[] homecatimg={};
    String [] homecatname={};
    String [] homecatcount={};
    String[]homecatextra={};
    Context ctx;
    LayoutInflater inflater;

    public HomeAdapter(Context context, int[] homecatimg,String[] homecatname,String[] homecatcount,String[] homecatextra) {
        super(context, R.layout.list_homepage,homecatname);

        this.ctx=context;
        this.homecatimg=homecatimg;
        this.homecatcount=homecatcount;
        this.homecatname=homecatname;
        this.homecatextra=homecatextra;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_homepage, null);
        }
        ImageView homecategoryimg=(ImageView)convertView.findViewById(R.id.homecategoryimage);
        TextView homecategoryname = (TextView) convertView.findViewById(R.id.homecategoryname);
        TextView homecategorycount = (TextView) convertView.findViewById(R.id.homecategoryitemscount);
        TextView homecategoryextra = (TextView) convertView.findViewById(R.id.homecategoryextra);




        homecategoryimg.setImageResource(homecatimg[position]);
        homecategoryname.setText(homecatname[position]);
        homecategorycount.setText(homecatcount[position]);
        homecategoryextra.setText(homecatextra[position]);



        return convertView;
    }
}
