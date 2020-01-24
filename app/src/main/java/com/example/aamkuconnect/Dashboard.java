package com.example.aamkuconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.aamkuconnect.Salesperson.AddRetailer;
import com.example.aamkuconnect.Salesperson.AllRetailers;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.Objects;


public class Dashboard extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawer;

    FirebaseAuth fAuth;
    FirebaseUser user;

    SharedPreferences sharedPreferences;
    String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        fAuth = FirebaseAuth.getInstance();
        user = fAuth.getCurrentUser();

        sharedPreferences = getSharedPreferences("simplifiedcodingsharedpref", Context.MODE_PRIVATE);
        s = sharedPreferences.getString("keytype","");

        if(user == null && !s.contains("Salesperson") && !s.contains("Retailer")){

             Intent i = new Intent(Dashboard.this,ChooseLogin.class);
             startActivity(i);
             finish();
        }
     /*   else if(s.equals("Salesperson")){

            Intent i = new Intent(Dashboard.this,SalesDashboard.class);
            startActivity(i);
            finish();
        }
        else{

            Intent i = new Intent(Dashboard.this,RetailDashboard.class);
            startActivity(i);
            finish();
        }  */

       FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
           @Override
           public void onComplete(@NonNull Task<InstanceIdResult> task) {

               if (!task.isSuccessful()) {
                   //  Log.w(TAG, "getInstanceId failed", task.getException());
                   //  return;
                   Toast.makeText(getApplicationContext(),"Not generated",Toast.LENGTH_SHORT).show();

               }

               // Get new Instance ID token1
               String token = task.getResult().getToken();
               Log.i("My Token", "Current token=" + token);
           }
       });

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FirstFragment()).commit();
           // navigationView.setCheckedItem(R.id.nav_add);
        }

        Menu menu = navigationView.getMenu();

        if(s.equals("Salesperson")){

            getSupportActionBar().setTitle("Salesperson dashboard");
            menu.findItem(R.id.nav_add).setVisible(false);
            menu.findItem(R.id.nav_users).setVisible(false);
            menu.findItem(R.id.nav_pending_requests).setVisible(false);
            menu.findItem(R.id.nav_add_retailer).setVisible(true);
            menu.findItem(R.id.nav_all_retailers).setVisible(true);
            menu.findItem(R.id.nav_add_item_master).setVisible(false);
            menu.findItem(R.id.nav_admin_retailers).setVisible(false);

        }else if(s.equals("Retailer")){

            getSupportActionBar().setTitle("Retailer dashboard");
            menu.findItem(R.id.nav_add).setVisible(false);
            menu.findItem(R.id.nav_users).setVisible(false);
            menu.findItem(R.id.nav_pending_requests).setVisible(false);
            menu.findItem(R.id.nav_add_retailer).setVisible(false);
            menu.findItem(R.id.nav_all_retailers).setVisible(false);
            menu.findItem(R.id.nav_add_item_master).setVisible(false);
            menu.findItem(R.id.nav_admin_retailers).setVisible(false);
        }
        else{

            getSupportActionBar().setTitle("Admin dashboard");
            menu.findItem(R.id.nav_add).setVisible(true);
            menu.findItem(R.id.nav_users).setVisible(true);
            menu.findItem(R.id.nav_pending_requests).setVisible(true);
            menu.findItem(R.id.nav_add_retailer).setVisible(false);
            menu.findItem(R.id.nav_all_retailers).setVisible(false);
            menu.findItem(R.id.nav_add_item_master).setVisible(true);
            menu.findItem(R.id.nav_admin_retailers).setVisible(true);
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

                    case R.id.nav_pending_requests:

                        Intent go = new Intent(Dashboard.this,PendingRetailers.class);
                        startActivity(go);
                        finish();
                        drawer.closeDrawers();
                        break;

                    case R.id.nav_admin_retailers:

                        Intent gone = new Intent(Dashboard.this,AdminSeeRetailers.class);
                        startActivity(gone);
                        finish();
                        drawer.closeDrawers();
                        break;

                    case R.id.nav_add_item_master:

                        Intent goo = new Intent(Dashboard.this,AddItemAdmin.class);
                        startActivity(goo);
                        finish();
                        drawer.closeDrawers();
                        break;

                    case R.id.nav_add_retailer:

                        Intent inn = new Intent(Dashboard.this, AddRetailer.class);
                        startActivity(inn);
                        drawer.closeDrawers();
                        finish();
                        break;

                    case R.id.nav_all_retailers:

                        Intent ino = new Intent(Dashboard.this, AllRetailers.class);
                        startActivity(ino);
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

        MenuItem item = menu.findItem(R.id.action_logout);
        MenuItem item1 = menu.findItem(R.id.action_sales_logout);
        MenuItem item2 = menu.findItem(R.id.action_retail_logout);

        if(user != null){

            item.setVisible(true);
            item1.setVisible(false);
            item2.setVisible(false);
        }
        else if(s.equals("Salesperson")){

            item.setVisible(false);
            item1.setVisible(true);
            item2.setVisible(false);

        }
        else{

            item.setVisible(false);
            item1.setVisible(false);
            item2.setVisible(true);
        }

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

            case R.id.action_retail_logout:

                SharedPrefManager.getInstance(Dashboard.this).logout();
                Intent in = new Intent(Dashboard.this,ChooseLogin.class);
                startActivity(in);
                finish();
                break;

            case R.id.action_sales_logout:

                SharedPrefManager.getInstance(Dashboard.this).logout();
                Intent go = new Intent(Dashboard.this,ChooseLogin.class);
                startActivity(go);
                finish();
                break;

        }

        return super.onOptionsItemSelected(item);
    }


}
