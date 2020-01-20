package com.example.aamkuconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;


public class Dashboard extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawer;

    FirebaseAuth fAuth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        fAuth = FirebaseAuth.getInstance();
        user = fAuth.getCurrentUser();

        if(user == null && !SharedPrefManager.getInstance(this).isLoggedIn()){

             Intent i = new Intent(Dashboard.this,ChooseLogin.class);
             startActivity(i);
             finish();
        }
      /*  else if( SharedPrefManager.getInstance(this).isLoggedIn()){

            Intent i = new Intent(Dashboard.this,SalesDashboard.class);
            startActivity(i);
            finish();
        }
        else{

            Intent i = new Intent(Dashboard.this,RetailDashboard.class);
            startActivity(i);
            finish();
        }  */

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Admin dashboard");

        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FirstFragment()).commit();
           // navigationView.setCheckedItem(R.id.nav_add);
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch(menuItem.getItemId()){

                    case R.id.nav_add:
                        //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FirstFragment()).commit();

                        Intent i = new Intent(Dashboard.this,AddUser.class);
                        startActivity(i);
                        drawer.closeDrawers();
                        finish();
                        break;

                    case R.id.nav_users:

                        Intent in = new Intent(Dashboard.this,AllUsers.class);
                        startActivity(in);
                        drawer.closeDrawers();
                        finish();
                        break;
                }

                return true;
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,
                                                  R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {

     /*   if(drawer.isDrawerOpen(GravityCompat.START)){

            drawer.closeDrawer(GravityCompat.START);
        }else{


            super.onBackPressed();
        }   */

        AlertDialog.Builder builder = new AlertDialog.Builder(Dashboard.this);
        builder.setMessage("Do you want to exit.");
        builder.setCancelable(true);
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.cancel();
            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                finish();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.dashboard, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){

            case R.id.action_logout:

                    fAuth.signOut();
                    Intent i = new Intent(Dashboard.this,ChooseLogin.class);
                    startActivity(i);
                    finish();
                    break;

        }

        return super.onOptionsItemSelected(item);
    }


}
