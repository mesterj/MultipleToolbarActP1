package com.kite.joco.multipletoolbaractp1;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    TextView tvHello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar tlbMain = (Toolbar) findViewById(R.id.tlbMain);
        tvHello = (TextView) findViewById(R.id.tvHello);

        setSupportActionBar(tlbMain);
        tlbMain.inflateMenu(R.menu.menu_main);
        if (getIntent() != null) {
            handleIntent(getIntent());
        } else {
            Log.d("TOOLBAR:MAINACTIVITY", ":Hogy a csába lehet null az intent???");
        }

    }


    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Log.d("TOOLBARSEARCH", " Search fired with : " + query);
            showsearchString(query);
        } else {
            Log.d("TOOLBARSEARCH", " Nem kereséssel lett az Activity meghívva");
        }
    }

    private void showsearchString(String text) {
        tvHello.setText(text);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = (SearchView) menu.findItem(R.id.searhchMain).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        Toast.makeText(this," A beírt kereső szöveg: " + s,Toast.LENGTH_LONG).show();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }

    public void onClick(View v) {
        Intent secondIntent = new Intent(this,SecondSearch.class);
        startActivity(secondIntent);
    }
}
