package com.example.accuweather_2_0.view;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.accuweather_2_0.BuildConfig;
import com.example.accuweather_2_0.R;
import com.example.accuweather_2_0.adapter.SectionsPagerAdapter;
import com.example.accuweather_2_0.MainActivityContract;
import com.example.accuweather_2_0.presenter.MainActivityPresenter;
import com.example.accuweather_2_0.utils.DialogUtils;
import com.google.android.material.snackbar.Snackbar;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static android.location.LocationManager.GPS_PROVIDER;
import static android.location.LocationManager.NETWORK_PROVIDER;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

    private MainActivityContract.Presenter presenter;
    public static final int LOCATION_SETTINGS_REQUEST_CODE = 52456;
    private static final int REQUEST_LOCATION_PERMISSIONS_REQUEST_CODE = 34;
    private final String[] locationPermissions = new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION};
    private SwipeRefreshLayout refreshLayout;
    private ProgressDialog locationProgressDialog;
    private ProgressDialog networkProgressDialog;
    public static ViewPager viewPager;
    private AlertDialog.Builder locationSettingsDialog;
    private boolean refresh;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        presenter = new MainActivityPresenter(this);
        if (savedInstanceState == null) {
            updateWeatherData();
        } else {
            presenter.refreshUI();
        }
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

        networkProgressDialog = DialogUtils.buildNetworkProgressDialog(this);
        locationProgressDialog = DialogUtils.buildLocationProgressDialog(this, presenter);
        locationSettingsDialog = DialogUtils.buildLocationSettingsDialog(this);


        // build refreshLayout
        refreshLayout = findViewById(R.id.srl_mainActivity_mainContainer);
        refreshLayout.setOnRefreshListener(() -> {
            refresh = true;
            updateWeatherData();
            refreshLayout.setRefreshing(false);
        });

        // build viewPager
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(
                getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        viewPager = findViewById(R.id.viewPager_mainActivity);
        viewPager.setAdapter(sectionsPagerAdapter);
    }

    private void updateWeatherData() {
        if (checkPermissions()) {
            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            if (locationManager.isProviderEnabled(NETWORK_PROVIDER) ||
                    locationManager.isProviderEnabled(GPS_PROVIDER)) {
                if (!refresh) {
                    presenter.updateLocationAndWeatherData();
                } else {
                    presenter.updateWeatherData();
                    refresh = false;
                }
            } else {
                locationSettingsDialog.show();
            }
        } else {
            requestPermissions();
        }
    }

    private boolean checkPermissions() {
        return ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) == PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION) == PERMISSION_GRANTED;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOCATION_SETTINGS_REQUEST_CODE) {
            updateWeatherData();
        }
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
        boolean shouldProvideRationale =
                ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_FINE_LOCATION);
        if (shouldProvideRationale) {
            Snackbar.make(
                    findViewById(R.id.srl_mainActivity_mainContainer),
                    R.string.permission_rationale,
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.ok, view -> requestLocationPermissions())
                    .show();
        } else {
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