package com.cruxitech.android.invenapp;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewAllDevices extends BaseActivity implements AsyncResponse {

    public static ProgressDialog m_ProgressDialog = null;
    private ArrayList<DeviceOrder> m_orders = null;
    private static DeviceOrderAdapter m_adapter;
    TextView txtviewEmptymydevices=null;
    public static String jsonstringval=null;
    Toolbar mToolbar;
    ListView lv=null;
    EditText inputSearch;
    SearchView search=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_devices);

        jsonstringval=null;
         lv = (ListView)findViewById(com.cruxitech.android.invenapp.R.id.list1);
        txtviewEmptymydevices=(TextView)findViewById(R.id.txtviewEmpty);

        this.getdatafromdatabase();


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                      @Override
                                      public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                          Intent newActivity = new Intent(ViewAllDevices.this, ViewDevice.class);

                                          Bundle b = new CommonProcs().onClickViewDeviceList(getApplicationContext(), m_adapter, i, newActivity);
                                          b.putString("callingclass", "ViewAllDevices");
                                          newActivity.putExtras(b); //Put your id to your next Intent

                                          startActivity(newActivity);
                                          finish();


                                      }


                                  }


        );

       /* search=(SearchView) findViewById(R.id.searchfield);
        search.setVisibility(View.VISIBLE);
        search.setQueryHint("Search");*/
        //*** setOnQueryTextFocusChangeListener ***




       /* search.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub


            }
        });

        /*//*** setOnQueryTextListener ***
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                ViewAllDevices.this.m_adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // TODO Auto-generated method stub


                return false;
            }
        });*/

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        startActivity(new Intent(ViewAllDevices.this, Landingpage.class));


    }



    private void getdatafromdatabase()
    {
        String method = "viewalldevices";
        BackgroundTask.jsonstring=null;
        BackgroundTask backgroundTask = new BackgroundTask(this, new AsyncResponse() {

            @Override
            public void processFinish(String output) {
                if (output.equals(StatusConstants.statusViewAllDevicesSuccessful)) {

                m_orders = BackgroundTask.m_orders;
                m_adapter = new DeviceOrderAdapter(ViewAllDevices.this, com.cruxitech.android.invenapp.R.layout.list_alldevices, m_orders);
                lv.setAdapter(m_adapter);
                    lv.setTextFilterEnabled(true);
//m_orders=BackgroundTask.m_orders;
                Log.e("viewalldeviceslog",output);
                }else
                {
                    txtviewEmptymydevices.setText("No devices found ");
                }
            }
        });
        backgroundTask.execute(method);


    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();

        View empty = findViewById(com.cruxitech.android.invenapp.R.id.txtviewEmpty);
        ListView list = (ListView) findViewById(com.cruxitech.android.invenapp.R.id.list1);
        list.setEmptyView(empty);
    }

    public void processFinish(String output){
        Log.e("viewalldeviceslog2", output);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);//Menu Resource, Menu


        MenuItem menuItem = menu.findItem(R.id.menu_search);
        android.support.v7.widget.SearchView search = (android.support.v7.widget.SearchView) MenuItemCompat.getActionView(menuItem);
        SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        //   menuItem.setVisible(false);



        EditText searchEditText = (EditText) search.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(Color.parseColor("#FFFFFF"));
        // searchEditText.setHintTextColor(getResources().getColor(R.color.white));


        search.setVisibility(View.VISIBLE);

        search.setQueryHint("Search");
        //*** setOnQueryTextFocusChangeListener ***
        search.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub


            }
        });

        //*** setOnQueryTextListener ***
        search.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                ViewAllDevices.this.m_adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // TODO Auto-generated method stub

                if(newText.length()<1){
                    ViewAllDevices.this.m_adapter.getFilter().filter(null);
                }

                return false;
            }
        });

search.setOnCloseListener(new SearchView.OnCloseListener() {
    @Override
    public boolean onClose() {
        ViewAllDevices.this.m_adapter.getFilter().filter(null);
        return false;
    }



});




        return true;
    }




}
