package com.example.parstagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // register parse model
        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("XprlOxSjf8GBsLMh4WkL5qsegeoLNMFOTRgU9Xrh")
                .clientKey("Jah8AXMfU2rncqD0WFBGDetQz9EKvwwghT8ynBgP")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
