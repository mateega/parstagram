package com.example.parstagram;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.parstagram.fragments.FeedFragment;
import com.example.parstagram.fragments.ProfileFragment;
import com.example.parstagram.fragments.ShareFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static String TAG = "Main Activity";
    final FragmentManager fragmentManager = getSupportFragmentManager();
    private BottomNavigationView bottomNavigationView;
    public static String username;
    public static String description;
    public static String timeStamp;
    public static String image;
    public static String profilePic;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.logout) {
            Log.i(TAG, "clicked logout button");
            ParseUser.logOut();
            ParseUser currentUser = ParseUser.getCurrentUser();
            if (currentUser == null) {
                Toast.makeText(MainActivity.this, "user null", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "user is not null", Toast.LENGTH_SHORT).show();
            }
            goToLogin();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.actionHome:
                        fragment = new FeedFragment();
                        break;
                    case R.id.actionCamera:
                        fragment = new ShareFragment();
                        break;
                    case R.id.actionProfile:
                        fragment = new ProfileFragment();
                        break;
                    default:
                        fragment = new FeedFragment();
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });
        // Set default selection
        bottomNavigationView.setSelectedItemId(R.id.actionHome);




    }


    private void goToLogin() {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }

    public static String getUsername(){
        return username;
    }
    public static String getDescription(){
        return description;
    }
    public static String getTimeStamp(){
        return timeStamp;
    }
    public static String getImage(){
        return image;
    }
//    public static String getProfilePic(){
//        return profilePic;
//    }

    public static void setUsername(String newUsername) {
        username = newUsername;
    }
    public static void setDescription(String newDescription){
        description = newDescription;
    }
    public static void setTimeStamp(String newTimeStamp){
        timeStamp = newTimeStamp;
    }
    public static void setImage(String newImage){
        image = newImage;
    }
//    public static void setProfilePic(String newProfilePic){
//        profilePic = newProfilePic;
//    }

}