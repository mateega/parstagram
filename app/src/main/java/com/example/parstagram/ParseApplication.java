package com.example.parstagram;

import android.app.Application;

import com.parse.Parse;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("XprlOxSjf8GBsLMh4WkL5qsegeoLNMFOTRgU9Xrh")
                .clientKey("Jah8AXMfU2rncqD0WFBGDetQz9EKvwwghT8ynBgP")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
