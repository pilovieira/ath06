package br.com.pilovieira.ath06.view;

import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.view.MenuItem;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import br.com.pilovieira.ath06.R;
import br.com.pilovieira.ath06.business.H06Commands;
import br.com.pilovieira.ath06.business.ListenerProvider;
import br.com.pilovieira.ath06.log.InfoFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationBarView.OnItemSelectedListener, NavigationBarView.OnItemReselectedListener {

    public static final String KEY_FRAGMENT = "fragment";

    private BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureNavigationMenu();
        requestSMSPermission();
        initializeHotButtons();
        configureAdView();
    }

    private void configureNavigationMenu() {
        navigationView = findViewById(R.id.nav_bottom);
        navigationView.setOnItemSelectedListener(this);
        navigationView.setOnItemReselectedListener(this);
        loadInitFragment();
    }

    private void configureAdView() {
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    private void loadInitFragment() {
        Bundle extras = getIntent().getExtras();
        int menuItemId = extras == null ? R.id.nav_info : extras.getInt(KEY_FRAGMENT, R.id.nav_info);
        navigationView.setSelectedItemId(menuItemId);
        selectItem(menuItemId);
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        selectItem(menuItem.getItemId());
        return true;
    }

    @Override
    public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
        selectItem(menuItem.getItemId());
    }

    private void selectItem(int itemId) {
        Fragment fragment = null;

        if (itemId == R.id.nav_info) {
            fragment = new InfoFragment();
        } else if (itemId == R.id.nav_operations) {
            fragment = new OperationsFragment();
        } else if (itemId == R.id.nav_configurations) {
            fragment = new ConfigurationsFragment();
        } else if (itemId == R.id.nav_user_manual) {
            fragment = new UserManualFragment();
        }

        replaceFragment(fragment);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_main_frame, fragment).commit();
    }
}
