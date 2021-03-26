package com.example.accuweather_2_0.view.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.accuweather_2_0.BuildConfig;
import com.example.accuweather_2_0.R;
import com.example.accuweather_2_0.adapter.SectionsPagerAdapter;
import com.example.accuweather_2_0.contract.MainActivityContract;
import com.example.accuweather_2_0.presenter.MainActivityPresenter;
import com.google.android.material.snackbar.Snackbar;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {
    private MainActivityContract.Presenter presenter;
    private static final int REQUEST_LOCATION_PERMISSIONS_REQUEST_CODE = 34;
    private final String[] locationPermissions = new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION};
    private SwipeRefreshLayout refreshLayout;
    private ProgressDialog locationProgressDialog;
    private ProgressDialog networkProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        presenter = new MainActivityPresenter(this);
        updateWeatherData();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_LOCATION_PERMISSIONS_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                updateWeatherData();
            } else {
                Snackbar.make(
                        findViewById(R.id.srl_mainActivity_mainContainer),
                        R.string.permission_denied_explanation,
                        Snackbar.LENGTH_INDEFINITE)
                        .setAction(R.string.settings, view -> startAppSettings())
                        .show();
            }
        }
    }

    private void initUI() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // build locationProgressDialog
        locationProgressDialog = new ProgressDialog(this);
        locationProgressDialog.setMessage(getString(R.string.getting_location));
        locationProgressDialog.setCancelable(false);
        locationProgressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, getString(R.string.dialog_cancel),
                (dialogInterface, i) -> presenter.stopUpdatesLocation());

        // build networkProgressDialog
        networkProgressDialog = new ProgressDialog(this);
        networkProgressDialog.setMessage(getString(R.string.getting_data_from_server));
        networkProgressDialog.setCancelable(false);


        // build refreshLayout
        refreshLayout = findViewById(R.id.srl_mainActivity_mainContainer);
        refreshLayout.setOnRefreshListener(() -> {
            updateWeatherData();
            refreshLayout.setRefreshing(false);
        });

        // build viewPager
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(
                this,
                getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        ViewPager viewPager = findViewById(R.id.viewPager_mainActivity);
        viewPager.setAdapter(sectionsPagerAdapter);
    }

    private void updateWeatherData() {
        if (checkPermissions()) {
            Log.i("dev", "MainActivity  updateLocation() !checkPermissions()");
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ||
                    locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                presenter.updateWeatherData();
            } else {
                showLocationSettingsDialog();
            }
        } else {
            Log.i("dev", "MainActivity  updateLocation() else");
            requestPermissions();
        }
    }

    private boolean checkPermissions() {
        return ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) == PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION) == PERMISSION_GRANTED;
    }

    private void showLocationSettingsDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(R.string.location_settings);
        alertDialog.setMessage(R.string.location_settings_message);
        alertDialog.setPositiveButton(R.string.location_settings_button, (dialog, which) -> {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        });
        alertDialog.setNegativeButton(R.string.dialog_cancel, (dialog, which) -> dialog.cancel());
        alertDialog.show();
    }

    private void requestLocationPermissions() {
        ActivityCompat.requestPermissions(MainActivity.this,
                locationPermissions,
                REQUEST_LOCATION_PERMISSIONS_REQUEST_CODE);
    }

    public void startAppSettings() {
        Intent intent = new Intent();
        intent.setAction(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package",
                BuildConfig.APPLICATION_ID, null);
        intent.setData(uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void requestPermissions() {
        Log.i("dev", "Main Activity requestPermissions()");
        boolean shouldProvideRationale =
                ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_FINE_LOCATION);
        if (shouldProvideRationale) {
            Log.i("dev", "Main Activity requestPermissions() shouldProvideRationale");
            Snackbar.make(
                    findViewById(R.id.srl_mainActivity_mainContainer),
                    R.string.permission_rationale,
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.ok, view -> requestLocationPermissions())
                    .show();
        } else {
            Log.i("dev", "Main Activity requestPermissions() else");
            requestLocationPermissions();
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showLocationUpdateDialog() {
        locationProgressDialog.show();
    }

    @Override
    public void cancelLocationUpdateDialog() {
        locationProgressDialog.dismiss();
    }

    @Override
    public void showNetworkUpdateDialog() {
        networkProgressDialog.show();
    }

    @Override
    public void cancelNetworkUpdateDialog() {
        networkProgressDialog.dismiss();
    }

    @Override
    public void onResponseFailure(String message) {
        Toast.makeText(this, getString(R.string.communication_error) + "\n" + message, Toast.LENGTH_LONG).show();
    }

}