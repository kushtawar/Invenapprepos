package com.cruxitech.android.invenapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class Home extends BaseActivity {

    ListView homelist=null;

    int[] homecatimgs={R.drawable.admin_2,R.drawable.viewmy_1,R.drawable.viewall,R.drawable.addsymbol};
    String[] homecatnames={"Admin","My Devices","View All Devices","Add New Device"};
    String[] homecatcount={"","","",""};
    String[] homecatextra={"","","",""};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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

}
