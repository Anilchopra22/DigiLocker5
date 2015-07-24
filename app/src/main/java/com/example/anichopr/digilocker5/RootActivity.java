package com.example.anichopr.digilocker5;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;


public class RootActivity extends Activity {
    private static final int CAMERA_REQUEST = 1888;
    DocumentInfo[] documents;
    static DocumentInfo[] tabADocuments;
    static DocumentInfo[] tabBDocuments;

    {
        tabADocuments = new DocumentInfo[6];
        for (int i=0;i<tabADocuments.length;i++) {
            Bitmap bm = null;
            tabADocuments[i] = new DocumentInfo("Driving License", bm);
        }

        tabBDocuments = new DocumentInfo[4];
        for (int i=0;i<tabBDocuments.length;i++) {
            Bitmap bm = null;
            tabBDocuments[i] = new DocumentInfo("Electricity Bill", bm);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root);

        InitializeTabs();
    }

    public DocumentInfo[] GetDocumentsForTab(ActionBar.Tab tab) {
        CharSequence text = tab.getText();

        if (text.equals(getResources().getString(R.string.TagTabA))) {
            documents = tabADocuments;
            for (int i=0;i<tabADocuments.length;i++) {
                if (tabADocuments[i].bitmap == null)
                    tabADocuments[i].bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
            }
        } else if (text.equals(getResources().getString(R.string.TagTabB))) {
            documents = tabBDocuments;
            for (int i=0;i<tabBDocuments.length;i++) {
                if (tabBDocuments[i].bitmap == null)
                    tabBDocuments[i].bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
            }
        }

        return documents;
    }

    public void InitializeGridView(ActionBar.Tab tab) {
        final DocumentInfo[] documentsInfo = GetDocumentsForTab(tab);
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this, documentsInfo));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
              if (documentsInfo[position].uri == null) {
                  Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                  startActivityForResult(intent, CAMERA_REQUEST);
              }
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            tabADocuments[0].bitmap = photo;
        }
    }
    public void InitializeTabs() {
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create a tab listener that is called when the user changes tabs.
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                InitializeGridView(tab);
//                LinearLayout gridViewA =  (LinearLayout) findViewById(R.id.Tab1);
//                GridLayout gridViewB =  (GridLayout) findViewById(R.id.selfUploadedDocGrid);
//                CharSequence text = tab.getText();
//                if (text.equals(getResources().getString(R.string.TagTabA))) {
//                    gridViewA.setVisibility(View.VISIBLE);
//                    gridViewB.setVisibility(View.GONE);
//                } else if (text.equals(getResources().getString(R.string.TagTabB))){
//                    gridViewA.setVisibility(View.GONE);
//                    gridViewB.setVisibility(View.VISIBLE);
//                }
            }

            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) { /* hide the given tab */ }
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) { /* probably ignore this event */ }
        };


        // Add 3 tabs, specifying the tab's text and TabListener
        actionBar.addTab(actionBar.newTab().setText(getResources().getString(R.string.TagTabA)).setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab().setText(getResources().getString(R.string.TagTabB)).setTabListener(tabListener));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.root, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
