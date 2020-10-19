package br.com.pilovieira.ath06.view;

import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import br.com.pilovieira.ath06.R;
import br.com.pilovieira.ath06.business.H06Commands;
import br.com.pilovieira.ath06.business.ListenerProvider;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_FRAGMENT = "fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureToolbar();
        configureNavigationMenu();
        requestSMSPermission();
        initializeHotButtons();
        configureAdView();
    }

    private void configureToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    private void configureNavigationMenu() {
        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new DrawerResolver(this));
        loadInitFragment(navigationView.getMenu());
    }

    private void configureAdView() {
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    private void loadInitFragment(Menu menu) {
        Bundle extras = getIntent().getExtras();
        int menuItemId = extras == null ? R.id.nav_info : extras.getInt(KEY_FRAGMENT, R.id.nav_info);
        new DrawerResolver(this).onNavigationItemSelected(menu.findItem(menuItemId));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    private void requestSMSPermission() {
        String permission = android.Manifest.permission.SEND_SMS;
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{permission}, 1000);
        }
    }

    private void initializeHotButtons() {
        H06Commands commands = new H06Commands();
        ListenerProvider.emitCommandListener(findViewById(R.id.btn_hot_get_location), getString(R.string.get_location), commands.getLocation());
        ListenerProvider.emitCommandListener(findViewById(R.id.btn_hot_lock_vehicle), getString(R.string.lock_vehicle), commands.lockVehicle());
        ListenerProvider.emitCommandListener(findViewById(R.id.btn_hot_unlock_vehicle), getString(R.string.unlock_vehicle), commands.unlockVehicle());
    }
}
