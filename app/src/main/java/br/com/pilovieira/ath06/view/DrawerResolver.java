package br.com.pilovieira.ath06.view;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import br.com.pilovieira.ath06.R;
import br.com.pilovieira.cartracker.client.comm.ClientEmitters;
import br.com.pilovieira.cartracker.client.live.LiveTrackerFragment;
import br.com.pilovieira.cartracker.client.lock.LockFragment;
import br.com.pilovieira.ath06.log.InfoFragment;
import br.com.pilovieira.cartracker.client.utils.Alerts;
import br.com.pilovieira.cartracker.client.view.OptionsActivity;
import br.com.pilovieira.cartracker.client.view.VirtualFenceFragment;
import br.com.pilovieira.ath06.comm.Message;

public class DrawerResolver implements NavigationView.OnNavigationItemSelectedListener{

    private MainActivity activity;

    public DrawerResolver(MainActivity activity){
        this.activity = activity;
    }

    public void loadInfoFragment(InfoFragment infoFragment) {
        replaceFragment(infoFragment);
        activity.setTitle(R.string.app_name);
        closeDrawers();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        boolean resolved = resolveFunctions(menuItem);

        if (!resolved) {
            Fragment fragment = null;

            switch(menuItem.getItemId()) {
                case R.id.nav_info:
                    fragment = new InfoFragment();
                    break;
                case R.id.nav_lock:
                    fragment = new LockFragment();
                    break;
                case R.id.nav_live:
                    fragment = new LiveTrackerFragment();
                    break;
                case R.id.nav_virtual_fence:
                    fragment = new VirtualFenceFragment();
                    break;
            }

            replaceFragment(fragment);

            menuItem.setChecked(true);
            activity.setTitle(menuItem.getTitle());
        }

        closeDrawers();
        return true;
    }

    private void closeDrawers() {
        DrawerLayout drawer = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        drawer.closeDrawers();
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_main_frame, fragment).commit();
    }

    private boolean resolveFunctions(MenuItem menuItem) {
        switch(menuItem.getItemId()) {
            case R.id.nav_get_location:
                emitFunction(menuItem, new Localization(), R.string.function_location_message);
                break;
            case R.id.nav_get_wallet:
                emitFunction(menuItem, new Wallet(), R.string.function_wallet_message);
                break;
            case R.id.nav_get_status:
                emitFunction(menuItem, new Status(), R.string.function_status_message);
                break;
            case R.id.action_settings:
                activity.startActivity(new Intent(activity, OptionsActivity.class));
                break;
            default:
                return false;
        }

        return true;
    }

    public void emitFunction(MenuItem menuItem, Message function, int successMessage) {
        Alerts alerts = new Alerts(activity);

        try {
            new ClientEmitters(activity).emit(function);
            alerts.showAlert(menuItem.getTitle().toString(), activity.getString(successMessage));
        } catch (Exception e) {
            alerts.showErrorAlert(e.getMessage());
        }
    }

}
