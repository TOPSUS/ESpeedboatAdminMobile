package com.example.espeedboatadmin;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.espeedboatadmin.ui.home.HomeFragment;
import com.example.espeedboatadmin.ui.profile.ProfileFragment;
import com.example.espeedboatadmin.ui.review.ReviewFragment;
import com.example.espeedboatadmin.ui.transaksi.ListTransaksiFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class DashboardAdminActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private AppBarConfiguration mAppBarConfiguration;
    private NavigationView navigationView;
    private boolean viewIsAtHome;
    ImageView profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_admin);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        profile = findViewById(R.id.nav_header_profile);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

        if (savedInstanceState == null) {
            navigationView.setCheckedItem(R.id.nav_home);
            moveFragment(new HomeFragment(), getString(R.string.menu_home));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dashboard_admin, menu);
        return true;
    }

    public void profilethis(View view){
        Fragment fragments = null;
        String title = getString(R.string.app_name);
        fragments = new ProfileFragment();
        title = getString(R.string.menu_profile);
        moveFragment(fragments, title);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);

        switch (item.getItemId()) {
            case R.id.nav_listtransaksino:
                fragment = new ListTransaksiFragment("not done");
                title = getString(R.string.menu_transaksi_no);
                viewIsAtHome = false;
                break;
            case R.id.nav_listtransaksidone:
                fragment = new ListTransaksiFragment("done");
                title = getString(R.string.menu_transaksi_done);
                viewIsAtHome = false;
                break;
            case R.id.nav_review:
                fragment = new ReviewFragment();
                title = getString(R.string.menu_review);
                break;
        }

        moveFragment(fragment, title);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        if (!viewIsAtHome) {
            navigationView.setCheckedItem(R.id.nav_home);
            moveFragment(new HomeFragment(), getString(R.string.menu_home));
        } else {
            moveTaskToBack(true);
        }
    }

    private void moveFragment(Fragment fragment, String title) {
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.nav_host_fragment, fragment);
            ft.commit();
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }
}