package com.almadev.znaniesila;

import android.app.Application;
import android.content.Context;

import com.flurry.android.FlurryAgent;
import com.google.ads.conversiontracking.InstallReceiver;
import com.yandex.metrica.YandexMetrica;

/**
 * Created by Aleksey on 11.11.2015.
 */
public class ZSApp extends Application {
    private static final String YA_API_KEY = "6f03830c-8106-4325-abf6-bb75d347d387";//"2c8dd60e-e769-4708-ab48-6e68b733ebd6";
    private static final String FLURRY_API_KEY = "GX2NGYFFF3TSWSPSMBTC";

    public static final boolean DEBUG_ENV = true;

    public static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();

        sContext = getApplicationContext();

        if (YA_API_KEY != null) {
            YandexMetrica.activate(getApplicationContext(), YA_API_KEY);
            // Отслеживание активности пользователей
            if (DEBUG_ENV) {
                YandexMetrica.setLogEnabled();
            }

            YandexMetrica.enableActivityAutoTracking(this);
            YandexMetrica.setReportCrashesEnabled(false);
            YandexMetrica.setReportNativeCrashesEnabled(false);
            YandexMetrica.setTrackLocationEnabled(false);

            InstallReceiver mInstallReceiver = new InstallReceiver();
            YandexMetrica.registerReferrerBroadcastReceivers(mInstallReceiver);
        }

        if (FLURRY_API_KEY != null) {
            FlurryAgent.init(this, FLURRY_API_KEY);
        }
    }
}
