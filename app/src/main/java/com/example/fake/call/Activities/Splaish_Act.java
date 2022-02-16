package com.example.fake.call.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
//import com.google.firebase.analytics.FirebaseAnalytics;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.example.fake.call.MainActivity;
import com.example.fake.call.R;
import com.example.fake.call.Utils_.Shared;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class Splaish_Act extends AppCompatActivity {
    Handler handler;
    LinearLayout hidePBar;
    InterstitialAd mInterstitialAd;
    LinearLayout next;
    ProgressBar progrezzbars;
    Runnable runnable;
    String test_mobile_white;
    String uxi_device;
    private final int SPLASH_TIME_OUT = BaseImageDownloader.DEFAULT_HTTP_CONNECT_TIMEOUT;
    int progressStatus = 0;
    String packg_name = null;

    @Override
    public void onBackPressed() {
    }


    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        setTheme(R.style.AppTheme);
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_splaish_);
        this.test_mobile_white = getResources().getString(R.string.test_mobile_white);
        this.uxi_device = getResources().getString(R.string.uxi_device);
        MobileAds.setRequestConfiguration(new RequestConfiguration.Builder().setTestDeviceIds(Arrays.asList(getString(R.string.m11_device), getString(R.string.redme10_device))).build());
        ads_loading();
        this.progrezzbars = (ProgressBar) findViewById(R.id.progrezzbars);
        this.hidePBar = (LinearLayout) findViewById(R.id.hidePbar);
        this.next = (LinearLayout) findViewById(R.id.lets_start);
        this.handler = new Handler();
        try {
            this.packg_name = Shared.getInstance().getStringValueFromPreference(getResources().getString(R.string.promotion_package_name_pref), null, getApplicationContext());
        } catch ( Exception unused) {
        }
        try {
            if (this.packg_name == null) {
                this.packg_name = getIntent().getStringExtra("promo_package_name");
            }
        } catch ( Exception unused2) {
        }
        if (this.packg_name != null) {
            startActivity(new Intent(this, Promotion_act.class).putExtra("promo_package_name", this.packg_name));
            Shared.getInstance().saveStringToPreferences(getResources().getString(R.string.promotion_package_name_pref), null, getApplicationContext());
            finish();
            return;
        }
        this.mInterstitialAd.setAdListener(new AdListener() { // from class: com.softdroid.fake.call.Activities.Splaish_Act.1
            @Override // com.google.android.gms.ads.AdListener
            public void onAdLoaded() {
                super.onAdLoaded();
                Splaish_Act.this.next.setVisibility(View.VISIBLE);
                Splaish_Act.this.hidePBar.setVisibility(View.GONE);
            }

            @Override // com.google.android.gms.ads.AdListener
            public void onAdClosed() {
                super.onAdClosed();
                Splaish_Act.this.startActivity(new Intent(Splaish_Act.this, MainActivity.class));
                Bundle bundle2 = new Bundle();
                bundle2.putString("Splaish_Act_intrst_closed", "Splaish_Act_intrst_closed");
             //   FirebaseAnalytics.getInstance(Splaish_Act.this).logEvent("Splaish_Act_intrst_closed", bundle2);
                Splaish_Act.this.finish();
            }

            @Override // com.google.android.gms.ads.AdListener
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                Bundle bundle2 = new Bundle();
                bundle2.putString("Splaish_Act_intrst_failed", "Splaish_Act_intrst_failed");
              //  FirebaseAnalytics.getInstance(Splaish_Act.this).logEvent("Splaish_Act_intrst_failed", bundle2);
                Splaish_Act.this.next.setVisibility(View.VISIBLE);
                Splaish_Act.this.hidePBar.setVisibility(View.GONE);
            }

            @Override // com.google.android.gms.ads.AdListener, com.google.android.gms.internal.ads.zzve
            public void onAdClicked() {
                super.onAdClicked();
                Bundle bundle2 = new Bundle();
                bundle2.putString("Splaish_Act_intrst_clicked", "Splaish_Act_intrst_clicked");
             //   FirebaseAnalytics.getInstance(Splaish_Act.this).logEvent("Splaish_Act_intrst_clicked", bundle2);
            }
        });
        this.next.setOnClickListener(new View.OnClickListener() { // from class: com.softdroid.fake.call.Activities.Splaish_Act.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Splaish_Act.this.nextAct();
            }
        });
    }


    public void nextAct() {
        if (this.mInterstitialAd.isLoaded()) {
            this.mInterstitialAd.show();
            Bundle bundle = new Bundle();
            bundle.putString("Splaish_Act_intrst_show", "Splaish_Act_intrst_show");
            //FirebaseAnalytics.getInstance(this).logEvent("Splaish_Act_intrst_show", bundle);
            return;
        }
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void ads_loading() {
        InterstitialAd interstitialAd = new InterstitialAd(this);
        this.mInterstitialAd = interstitialAd;
        interstitialAd.setAdUnitId(getResources().getString(R.string.Interstitial_admob));
        requestNewInterstitial();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.progressStatus = 0;
        new Thread(new Runnable() { // from class: com.softdroid.fake.call.Activities.Splaish_Act.3
            @Override // java.lang.Runnable
            public void run() {
                while (Splaish_Act.this.progressStatus < 100) {
                    Splaish_Act.this.progressStatus++;
                    try {
                        Splaish_Act.this.progrezzbars.setProgress(Splaish_Act.this.progressStatus);
                    } catch ( Exception unused) {
                    }
                    try {
                        Thread.sleep(60);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void requestNewInterstitial() {
        this.mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice(getResources().getString(R.string.vicky_s8)).addTestDevice(this.uxi_device).addTestDevice(this.test_mobile_white).addTestDevice(getResources().getString(R.string.zegar_device)).addTestDevice(getResources().getString(R.string.rorjan_test_device)).build());
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.handler.removeCallbacks(this.runnable);
    }
}