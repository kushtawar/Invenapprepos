package com.cruxitech.android.invenapp;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;



public class ViewMyDevices extends BaseActivity implements AsyncResponse {

    public static ProgressDialog m_ProgressDialog = null;
    private ArrayList<DeviceOrder> m_orders = null;
    private static DeviceOrderAdapter m_adapter;
SearchView search=null;

    public static String jsonstringval=null;

    ListView lv=null;

    TextView txtviewEmptymydevices=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        jsonstringval=null;
        setContentView(R.layout.activity_view_my_devices);
        txtviewEmptymydevices=(TextView)findViewById(R.id.txtviewEmptymydevices);
        lv = (ListView)findViewById(R.id.listmydevices);


        this.getdatafromdatabase();


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                      @Override
                                      public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                          //Toast.makeText(getApplicationContext(), "You Clicked " + m_adapter.getItem(i).getDevuniqueid(), Toast.LENGTH_SHORT).show();
                                          Intent newActivity = new Intent(ViewMyDevices.this, ViewDevice.class);
                                          Bundle b = new CommonProcs().onClickViewDeviceList(getApplicationContext(), m_adapter, i, newActivity);
                                          b.putString("callingclass", "ViewMyDevices");
                                          newActivity.putExtras(b); //Put your id to your next Intent
                                          startActivity(newActivity);
                                          finish();


                                      }


                                  }

        );




    }



    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        finish();
        startActivity(new Intent(ViewMyDevices.this, Landingpage.class));


    }



    private void getdatafromdatabase()
    {
        String method = "viewmydevices";
        BackgroundTask backgroundTask = new BackgroundTask(ViewMyDevices.this, new AsyncResponse() {

            @Override
            public void processFinish(String output) {
                Log.e("invenapp:method:", new CommonProcs().getMethodname() + ":output:" + output);
                if (output.equals(StatusConstants.statusViewMyDeviceSuccessful)) {

                    m_orders = BackgroundTask.m_orders;
                    m_adapter = new DeviceOrderAdapter(ViewMyDevices.this, R.layout.list_mydevice, m_orders);
                    lv.setAdapter(m_adapter);

                    Log.e("invenapp:viewmydevices", output);
            }else
                {
                    txtviewEmptymydevices.setText("No devices found that are tagged on your name.");
                }


     }
        });
        backgroundTask.execute(method);

    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();

        View empty = findViewById(R.id.txtviewEmptymydevices);
        ListView list = (ListView) findViewById(R.id.listmydevices);
        list.setEmptyView(empty);
    }

    public void processFinish(String output){
        Log.e("viewmydeviceslog2", output);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);//Menu Resource, Menu


        MenuItem menuItem = menu.findItem(R.id.menu_search);
        SearchView search = (SearchView) MenuItemCompat.getActionView(menuItem);
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
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                ViewMyDevices.this.m_adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // TODO Auto-generated method stub
                if(newText.length()<1){
                    ViewMyDevices.this.m_adapter.getFilter().filter(null);
                }

                return false;
            }
        });
        search.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                ViewMyDevices.this.m_adapter.getFilter().filter(null);
                return false;
            }
        });



        return true;
    }
}
