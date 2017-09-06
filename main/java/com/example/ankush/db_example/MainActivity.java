package com.example.ankush.db_example;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener{


    private Button save,update,delete,display;
    private EditText name,roll,marks;
    DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        save=(Button)findViewById(R.id.save);
        display=(Button)findViewById(R.id.display);
        delete=(Button)findViewById(R.id.delete);
        update=(Button)findViewById(R.id.update);

        name=(EditText)findViewById(R.id.name);
        roll=(EditText)findViewById(R.id.roll);
        marks=(EditText)findViewById(R.id.marks);

        mydb = new DBHelper(this);

        save.setOnClickListener(this);
        display.setOnClickListener(this);
        update.setOnClickListener(this);
        delete.setOnClickListener(this);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        ArrayList<String> arrayList=new ArrayList<>();

        switch (v.getId())
        {

            case R.id.save:
                int t;

                    t=Integer.valueOf(roll.getText().toString());
                    mydb.insertStudent(name.getText().toString(),t,marks.getText().toString());

                 break;
            case R.id.display:
                arrayList=mydb.getAllStudent();

                    String st="";
                    for(String s:arrayList)
                    {
                        st += s;
                     if(st!="")
                        Toast.makeText(getApplicationContext(), st, Toast.LENGTH_SHORT).show();
                     else
                        Toast.makeText(getApplicationContext(), "Nothing to Display", Toast.LENGTH_SHORT).show();
                    }

                break;
            case R.id.update:

                    mydb.updateStudent(name.getText().toString(), Integer.valueOf(roll.getText().toString()), marks.getText().toString());

                break;
            case R.id.delete:

                    int i = Integer.valueOf(roll.getText().toString());
                    mydb.deleteStudent(i);
                    Toast.makeText(getApplicationContext(),"Name:"+name.getText().toString()+"\nRollno:"+Integer.valueOf(roll.getText().toString()) + "Deleted!!", Toast.LENGTH_LONG).show();

                break;
        }

    }
}
