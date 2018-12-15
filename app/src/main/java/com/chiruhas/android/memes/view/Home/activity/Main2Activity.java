package com.chiruhas.android.memes.view.Home.activity;

import com.chiruhas.android.memes.Model.Meme_Model.MemeTemplates.Meme;
import com.chiruhas.android.memes.view.Bookmarks.activity.ActivityBookmarks;
import com.chiruhas.android.memes.view.Home.fragments.GiphFragment;
import com.chiruhas.android.memes.view.Home.fragments.MemeTempFragment;
import com.chiruhas.android.memes.view.developer.activity.ActivityDeveloper;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.chiruhas.android.memes.R;

import java.util.List;

public class Main2Activity extends AppCompatActivity implements MemeTempFragment.OnFragmentInteractionListener, GiphFragment.OnFragmentInteractionListener, EasyPermissions.PermissionCallbacks {

    private static final int PERMISSION_REQUEST_CODE = 1;
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        // for animating the menu icon
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(Main2Activity.this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setCheckedItem(R.id.home);
        // adding click events for navigation view

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.bookmark:

                        startActivity(new Intent(Main2Activity.this, ActivityBookmarks.class));


                        overridePendingTransition(R.anim.none, R.anim.activity_fade_out);

                        //getSupportFragmentManager().beginTransaction().replace(R.id.);
                        break;
                    case R.id.feature:

                        // opening email app
                        Intent intent = new Intent(Intent.ACTION_SENDTO);
                        intent.setType("text/plain");
                        intent.setData(Uri.parse("mailto:chiruhas.bobbadi123@gmail.com"));
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Meme's App Feature Request");
                        //intent.putExtra(Intent.EXTRA_TEXT, "Place your email message here ...");
                        startActivity(Intent.createChooser(intent, "Send Email"));


                        break;
                    case R.id.devloper:

                        startActivity(new Intent(Main2Activity.this, ActivityDeveloper.class));


                        overridePendingTransition(R.anim.none, R.anim.activity_fade_out);
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);

                return true;
            }
        });


        String perms[] = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        if (!EasyPermissions.hasPermissions(Main2Activity.this, perms)) {
            EasyPermissions.requestPermissions(Main2Activity.this, "This app need's permission for downloading media", PERMISSION_REQUEST_CODE, perms);
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }





    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            // Do something after user returned from app settings screen, like showing a Toast.
            Toast.makeText(Main2Activity.this, "Welcome back", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigationView.setCheckedItem(R.id.home);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
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
    public void onGiphFragCallBack() {

    }

    @Override
    public void onMemeTempFragmentClick(Meme m) {

    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

        if (EasyPermissions.somePermissionPermanentlyDenied(Main2Activity.this, perms)) {
            new AppSettingsDialog.Builder(Main2Activity.this).build().show();
        }
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = new MemeTempFragment();
                    break;
                case 1:
                    fragment = new GiphFragment();
                    break;
            }
            return fragment;
        }


        @Override
        public int getCount() {
            return 2;
        }
    }
}
