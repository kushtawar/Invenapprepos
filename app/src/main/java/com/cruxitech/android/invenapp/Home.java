package com.cruxitech.android.invenapp;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends BaseActivity {

    SessionManager session;
    TextView txtAccountStatus=null;
    public static Home homepage;


    ListView homelist=null;

    int[] homecatimgs={R.drawable.admin_2,R.drawable.viewmy_1,R.drawable.viewall,R.drawable.addsymbol};
    String[] homecatnames={"Admin","My Devices","View All Devices","Add New Device"};
    String[] homecatcount={"","","",""};
    String[] homecatextra={"","","",""};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        session=new SessionManager(this);
        homepage=this;

        txtAccountStatus=(TextView)findViewById(R.id.txtviewAccountStatus);

        homelist = (ListView)findViewById(R.id.listhomescreen);

        HomeAdapter homeadap=new HomeAdapter(this,homecatimgs,homecatnames,homecatcount,homecatextra);

        homelist.setAdapter(homeadap);


        homelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),homecatnames[position],Toast.LENGTH_SHORT).show();
            }
        });





    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mTitle = (TextView) mToolbar.findViewById(R.id.toolbar_title);
        mTitle.setText(Html.fromHtml("<font><i>" + "Home" + "</i></font>"));
    }

}
