package gov.louisiana.wlf.swordfish.model;

import com.activeandroid.ActiveAndroid;

/**
 * Created by noah on 6/23/14.
 */
public class MainApplication extends com.activeandroid.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ActiveAndroid.dispose();
    }

}
