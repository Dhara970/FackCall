package com.example.fake.call.Activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.internal.view.SupportMenu;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.formats.MediaView;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
//import com.google.firebase.analytics.FirebaseAnalytics;
import com.example.fake.call.Adapter.Adapter_Music_history;
import com.example.fake.call.MainActivity;
import com.example.fake.call.Model.Model_Audio;
import com.example.fake.call.R;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class Record_Audio extends AppCompatActivity implements View.OnClickListener {
    public static final String ALLOW_KEY = "ALLOWED";
    public static final String AUDIO_PREF = "AUDIO_PREF";
    public static final int MY_PERMISSIONS_REQUEST_RECORD_AUDIO = 200;
    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 300;
    public static String mFileName;
    public static ImageView record_availble;
    public static Boolean recording = false;
    public static TextView recording_availbe_or_not_txtview;
    private LinearLayout adContainerView;
    private AdView adaptive_adView;
    ImageView back_btn;
    ImageView delete_recording;
    FrameLayout frameLayout_2;
    InterstitialAd mInterstitialAd;
    RelativeLayout main_layout;
    String mynewfile;
    private UnifiedNativeAd nativeAd2;
    Adapter_Music_history obj_adapter;
    Model_Audio obj_model;
    ImageView record_imageview;
    TextView recording_textview;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager recyclerViewLayoutManager;
    SharedPreferences sharedPreferences;
    ShimmerFrameLayout shimmerFrameLayout;
    Chronometer timer;
    LinearLayout top_actionbar_layout;
    Boolean playing = false;
    Boolean dark_mode = false;
    Boolean waiit_one_sec = false;
    private MediaRecorder mRecorder = null;
    ArrayList<File> filearray = new ArrayList<>();
    ArrayList<Model_Audio> al_images = new ArrayList<>();

    private void initilize_local_ads() {
    }

    public static void setWindowFlag(Activity activity, int i, boolean z) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        if (z) {
            attributes.flags = i | attributes.flags;
        } else {
            attributes.flags = (i ^ -1) & attributes.flags;
        }
        window.setAttributes(attributes);
    }


    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().addFlags(6815872);
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, 67108864, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(1280);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, 67108864, false);
            getWindow().setStatusBarColor(0);
        }
        setContentView(R.layout.activity_record__audio);
        ShimmerFrameLayout shimmerFrameLayout = (ShimmerFrameLayout) findViewById(R.id.ad_loader);
        this.shimmerFrameLayout = shimmerFrameLayout;
        shimmerFrameLayout.startShimmer();
        getting_prefrence();
        initilize_ads();
        initilize_components();
        try {
            mFileName = getExternalCacheDir().getAbsolutePath() + "/Fake Call";
        } catch ( Exception unused) {
        }
        try {
            try {
                if (new File(mFileName).mkdir()) {
                    System.out.println("Directory created");
                } else {
                    System.out.println("Directory is not created");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            mFileName += "/fake_call_recording";
            if (new File(mFileName).exists()) {
                this.delete_recording.setVisibility(View.VISIBLE);
                recording_availbe_or_not_txtview.setText(getResources().getString(R.string.recording_availible));
                recording_availbe_or_not_txtview.setTextColor(-16777216);
                record_availble.setVisibility(View.VISIBLE);
            } else {
                recording_availbe_or_not_txtview.setText(getResources().getString(R.string.no_recording_availible));
                recording_availbe_or_not_txtview.setTextColor(SupportMenu.CATEGORY_MASK);
            }
        } catch ( Exception unused2) {
        }
        initilize_local_ads();
        mypermissions();
        new bg_task().execute(new Void[0]);
    }

    private void getting_prefrence() {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        this.sharedPreferences = defaultSharedPreferences;
        if (defaultSharedPreferences.getBoolean(getResources().getString(R.string.dark_mode_pref), false)) {
            this.dark_mode = true;
        }
    }


    public class bg_task extends AsyncTask<Void, Void, Void> {
        public bg_task() {
        }


        public Void doInBackground(Void... voidArr) {
            try {
                File[] listFiles = new File(Record_Audio.this.getExternalCacheDir().getAbsolutePath() + "/Fake Call").listFiles();
                if (listFiles.length != 0) {
                    int i = 0;
                    while (i < listFiles.length) {
                        String absolutePath = listFiles[i].getAbsolutePath();
                        String name = listFiles[i].getName();
                        if (absolutePath.contains("vicky007")) {
                            File file = new File(absolutePath);
                            if (file.exists()) {
                                file.delete();
                            }
                        } else {
                            try {
                                Record_Audio.this.filearray.add(listFiles[i]);
                                long length = listFiles[i].length();
                                Record_Audio.this.obj_model = new Model_Audio();
                                Record_Audio.this.obj_model.setStr_path(absolutePath);
                                Record_Audio.this.obj_model.setAudio_video_name(name);
                                Record_Audio.this.obj_model.setSize(length);
                                Record_Audio.this.obj_model.set_playing(false);
                                Record_Audio.this.obj_model.set_checked(false);
                                Record_Audio.this.al_images.add(Record_Audio.this.obj_model);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        i++;
                    }
                    Record_Audio.record_availble.setVisibility(View.VISIBLE);
                    return null;
                }
                Record_Audio.record_availble.setVisibility(View.GONE);
                Record_Audio.recording_availbe_or_not_txtview.setVisibility(View.VISIBLE);
                return null;
            } catch ( Exception unused) {
                return null;
            }
        }


        public void onPostExecute(Void r6) {
            super.onPostExecute( r6);
            Record_Audio record_Audio = Record_Audio.this;
            Record_Audio record_Audio2 = Record_Audio.this;
            ArrayList<Model_Audio> arrayList = record_Audio2.al_images;
            Record_Audio record_Audio3 = Record_Audio.this;
            record_Audio.obj_adapter = new Adapter_Music_history(record_Audio2, arrayList, record_Audio3, record_Audio3.dark_mode);
            Record_Audio.this.recyclerView.setAdapter(Record_Audio.this.obj_adapter);
        }
    }

    private void initilize_components() {
        this.delete_recording = (ImageView) findViewById(R.id.delete_recording);
        this.record_imageview = (ImageView) findViewById(R.id.record_imageview);
        this.back_btn = (ImageView) findViewById(R.id.back_btn);
        recording_availbe_or_not_txtview = (TextView) findViewById(R.id.recording_availbe_or_not_txtview);
        record_availble = (ImageView) findViewById(R.id.record_availble);
        this.recording_textview = (TextView) findViewById(R.id.recording_textview);
        this.top_actionbar_layout = (LinearLayout) findViewById(R.id.top_actionbar_layout);
        this.main_layout = (RelativeLayout) findViewById(R.id.main_layout);
        this.recyclerView = (RecyclerView) findViewById(R.id.record_voice_recycleview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        this.recyclerViewLayoutManager = gridLayoutManager;
        this.recyclerView.setLayoutManager(gridLayoutManager);
        this.timer = (Chronometer) findViewById(R.id.timer);
        this.delete_recording.setOnClickListener(this);
        this.back_btn.setOnClickListener(this);
        this.record_imageview.setOnClickListener(this);
    }

    private void initilize_ads() {
        InterstitialAd interstitialAd = new InterstitialAd(this);
        this.mInterstitialAd = interstitialAd;
        interstitialAd.setAdUnitId(getResources().getString(R.string.Interstitial_admob));
        requestNewInterstitial();
        loading_native_advance_ad2();
    }


    public void populateUnifiedNativeAdView(UnifiedNativeAd unifiedNativeAd, UnifiedNativeAdView unifiedNativeAdView) {
        unifiedNativeAdView.setMediaView((MediaView) unifiedNativeAdView.findViewById(R.id.ad_media));
        unifiedNativeAdView.setHeadlineView(unifiedNativeAdView.findViewById(R.id.ad_headline));
        unifiedNativeAdView.setBodyView(unifiedNativeAdView.findViewById(R.id.ad_body));
        unifiedNativeAdView.setCallToActionView(unifiedNativeAdView.findViewById(R.id.ad_call_to_action));
        unifiedNativeAdView.setIconView(unifiedNativeAdView.findViewById(R.id.ad_app_icon));
        unifiedNativeAdView.setPriceView(unifiedNativeAdView.findViewById(R.id.ad_price));
        unifiedNativeAdView.setStarRatingView(unifiedNativeAdView.findViewById(R.id.ad_stars));
        unifiedNativeAdView.setStoreView(unifiedNativeAdView.findViewById(R.id.ad_store));
        unifiedNativeAdView.setAdvertiserView(unifiedNativeAdView.findViewById(R.id.ad_advertiser));
        ((TextView) unifiedNativeAdView.getHeadlineView()).setText(unifiedNativeAd.getHeadline());
        if (unifiedNativeAd.getBody() == null) {
            unifiedNativeAdView.getBodyView().setVisibility(View.GONE);
        } else {
            unifiedNativeAdView.getBodyView().setVisibility(View.VISIBLE);
            ((TextView) unifiedNativeAdView.getBodyView()).setText(unifiedNativeAd.getBody());
        }
        if (unifiedNativeAd.getCallToAction() == null) {
            unifiedNativeAdView.getCallToActionView().setVisibility(View.INVISIBLE);
        } else {
            unifiedNativeAdView.getCallToActionView().setVisibility(View.VISIBLE);
            ((Button) unifiedNativeAdView.getCallToActionView()).setText(unifiedNativeAd.getCallToAction());
        }
        if (unifiedNativeAd.getIcon() == null) {
            unifiedNativeAdView.getIconView().setVisibility(View.GONE);
        } else {
            ((ImageView) unifiedNativeAdView.getIconView()).setImageDrawable(unifiedNativeAd.getIcon().getDrawable());
            unifiedNativeAdView.getIconView().setVisibility(View.VISIBLE);
        }
        if (unifiedNativeAd.getPrice() == null) {
            unifiedNativeAdView.getPriceView().setVisibility(View.INVISIBLE);
        } else {
            unifiedNativeAdView.getPriceView().setVisibility(View.VISIBLE);
            ((TextView) unifiedNativeAdView.getPriceView()).setText(unifiedNativeAd.getPrice());
        }
        if (unifiedNativeAd.getStore() == null) {
            unifiedNativeAdView.getStoreView().setVisibility(View.GONE);
        } else {
            unifiedNativeAdView.getStoreView().setVisibility(View.VISIBLE);
            ((TextView) unifiedNativeAdView.getStoreView()).setText(unifiedNativeAd.getStore());
        }
        if (unifiedNativeAd.getStarRating() == null) {
            unifiedNativeAdView.getStarRatingView().setVisibility(View.GONE);
        } else {
            ((RatingBar) unifiedNativeAdView.getStarRatingView()).setRating(unifiedNativeAd.getStarRating().floatValue());
            unifiedNativeAdView.getStarRatingView().setVisibility(View.GONE);
        }
        if (unifiedNativeAd.getAdvertiser() == null) {
            unifiedNativeAdView.getAdvertiserView().setVisibility(View.GONE);
        } else {
            ((TextView) unifiedNativeAdView.getAdvertiserView()).setText(unifiedNativeAd.getAdvertiser());
            unifiedNativeAdView.getAdvertiserView().setVisibility(View.GONE);
        }
        unifiedNativeAdView.setNativeAd(unifiedNativeAd);
        VideoController videoController = unifiedNativeAd.getVideoController();
        if (videoController.hasVideoContent()) {
            videoController.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() { // from class: com.softdroid.fake.call.Activities.Record_Audio.1
                @Override // com.google.android.gms.ads.VideoController.VideoLifecycleCallbacks
                public void onVideoEnd() {
                    super.onVideoEnd();
                }
            });
        }
    }

    private void loading_native_advance_ad2() {
        AdLoader.Builder builder = new AdLoader.Builder(this, getResources().getString(R.string.native_advance_admob));
        this.frameLayout_2 = (FrameLayout) findViewById(R.id.fl_adplaceholder_2);
        builder.forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() { // from class: com.softdroid.fake.call.Activities.Record_Audio.2
            @Override // com.google.android.gms.ads.formats.UnifiedNativeAd.OnUnifiedNativeAdLoadedListener
            public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                if (Record_Audio.this.nativeAd2 != null) {
                    Record_Audio.this.nativeAd2.destroy();
                }
                Record_Audio.this.nativeAd2 = unifiedNativeAd;
                FrameLayout frameLayout = (FrameLayout) Record_Audio.this.findViewById(R.id.fl_adplaceholder_2);
                UnifiedNativeAdView unifiedNativeAdView = (UnifiedNativeAdView) Record_Audio.this.getLayoutInflater().inflate(R.layout.ad_unified, (ViewGroup) null);
                Record_Audio.this.populateUnifiedNativeAdView(unifiedNativeAd, unifiedNativeAdView);
                frameLayout.removeAllViews();
                frameLayout.addView(unifiedNativeAdView);
            }
        });
        builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(true).build()).build());
        builder.withAdListener(new AdListener() { // from class: com.softdroid.fake.call.Activities.Record_Audio.3
            @Override // com.google.android.gms.ads.AdListener
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                Record_Audio.this.load_adaptive_Banner();
                Bundle bundle = new Bundle();
                bundle.putString("Record_Audio_native_failed", "Record_Audio_native_failed");
                //FirebaseAnalytics.getInstance(Record_Audio.this).logEvent("Record_Audio_native_failed", bundle);
            }

            @Override // com.google.android.gms.ads.AdListener
            public void onAdLoaded() {
                super.onAdLoaded();
                Record_Audio.this.frameLayout_2.setVisibility(View.VISIBLE);
                Record_Audio.this.shimmerFrameLayout.setVisibility(View.GONE);
                Record_Audio.this.shimmerFrameLayout.stopShimmer();
                Bundle bundle = new Bundle();
                bundle.putString("Record_Audio_native_show", "Record_Audio_native_show");
              //  FirebaseAnalytics.getInstance(Record_Audio.this).logEvent("Record_Audio_native_show", bundle);
            }

            @Override // com.google.android.gms.ads.AdListener, com.google.android.gms.internal.ads.zzve
            public void onAdClicked() {
                super.onAdClicked();
                Bundle bundle = new Bundle();
                bundle.putString("Record_Audio_native_clicked", "Record_Audio_native_clicked");
               // FirebaseAnalytics.getInstance(Record_Audio.this).logEvent("Record_Audio_native_clicked", bundle);
            }
        }).build().loadAd(new AdRequest.Builder().addTestDevice(getResources().getString(R.string.vicky_s8)).addTestDevice(getResources().getString(R.string.rorjan_test_device)).addTestDevice(MainActivity.test_mobile_white).addTestDevice("A86A0D556F68465C49063589837FCF98").build());
    }


    public void load_adaptive_Banner() {
        MobileAds.initialize(this, new OnInitializationCompleteListener() { // from class: com.softdroid.fake.call.Activities.Record_Audio.4
            @Override // com.google.android.gms.ads.initialization.OnInitializationCompleteListener
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        this.adContainerView = (LinearLayout) findViewById(R.id.banner_container);
        AdView adView = new AdView(this);
        this.adaptive_adView = adView;
        adView.setAdUnitId(getResources().getString(R.string.banner_admob));
        this.adContainerView.addView(this.adaptive_adView);
        AdRequest build = new AdRequest.Builder().addTestDevice("B3EEABB8EE11C2BE770B684D95219ECB").addTestDevice(getResources().getString(R.string.vicky_s8)).build();
        this.adaptive_adView.setAdSize(getAdSize());
        this.adaptive_adView.loadAd(build);
        this.adaptive_adView.setAdListener(new AdListener() { // from class: com.softdroid.fake.call.Activities.Record_Audio.5
            @Override // com.google.android.gms.ads.AdListener, com.google.android.gms.internal.ads.zzve
            public void onAdClicked() {
                super.onAdClicked();
                Bundle bundle = new Bundle();
                bundle.putString("Record_Audio_banner_clicked", "Record_Audio_banner_clicked");
               // FirebaseAnalytics.getInstance(Record_Audio.this).logEvent("Record_Audio_banner_clicked", bundle);
            }

            @Override // com.google.android.gms.ads.AdListener
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                Record_Audio.this.shimmerFrameLayout.setVisibility(View.GONE);
                Record_Audio.this.shimmerFrameLayout.stopShimmer();
                Bundle bundle = new Bundle();
                bundle.putString("Record_Audio_banner_failed", "Record_Audio_banner_failed");
              //  FirebaseAnalytics.getInstance(Record_Audio.this).logEvent("Record_Audio_banner_failed", bundle);
            }

            @Override // com.google.android.gms.ads.AdListener
            public void onAdLoaded() {
                super.onAdLoaded();
                Record_Audio.this.shimmerFrameLayout.setVisibility(View.GONE);
                Record_Audio.this.shimmerFrameLayout.stopShimmer();
            }

            @Override // com.google.android.gms.ads.AdListener
            public void onAdImpression() {
                super.onAdImpression();
                Bundle bundle = new Bundle();
                bundle.putString("Record_Audio_banner_show", "Record_Audio_banner_show");
              //  FirebaseAnalytics.getInstance(Record_Audio.this).logEvent("Record_Audio_banner_show", bundle);
            }
        });
    }

    private AdSize getAdSize() {
        Display defaultDisplay = getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, (int) (((float) displayMetrics.widthPixels) / displayMetrics.density));
    }

    private void requestNewInterstitial() {
        this.mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice(getResources().getString(R.string.vicky_s8)).addTestDevice(getResources().getString(R.string.vicky_s8)).addTestDevice(getResources().getString(R.string.rorjan_test_device)).addTestDevice("A86A0D556F68465C49063589837FCF98").build());
    }


    public void mypermissions() {
        if (ContextCompat.checkSelfPermission(this, "android.permission.RECORD_AUDIO") != 0) {
            marshmallow_permissions_audio();
        } else if (ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            marshmallow_permissions_WRITE_EXTERNAL_STORAGE();
        }
    }

    private void marshmallow_permissions_WRITE_EXTERNAL_STORAGE() {
        if (ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            return;
        }
        if (getFromPref(this, "ALLOWED").booleanValue()) {
            showSettingsAlert();
        } else if (ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.WRITE_EXTERNAL_STORAGE")) {
                ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 300);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 300);
            }
        }
    }

    public static Boolean getFromPref(Context context, String str) {
        return Boolean.valueOf(context.getSharedPreferences("AUDIO_PREF", 0).getBoolean(str, false));
    }

    private void delete_dialoug() {
        if (recording.booleanValue()) {
            Toast.makeText(this, getResources().getString(R.string.please_stop_rec_before_deleting),  Toast.LENGTH_LONG).show();
        } else if (this.al_images.size() < 1) {
            Toast.makeText(this, getResources().getString(R.string.no_recording_availible),  Toast.LENGTH_LONG).show();
        } else {
            final Dialog dialog = new Dialog(this);
            dialog.setCancelable(false);
            try {
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            } catch ( Exception unused) {
            }
            dialog.requestWindowFeature(1);
            dialog.setContentView(R.layout.delete_dialoug);
            ((Button) dialog.findViewById(R.id.no_dialoug)).setOnClickListener(new View.OnClickListener() { // from class: com.softdroid.fake.call.Activities.Record_Audio.6
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            ((Button) dialog.findViewById(R.id.yes_dialoug)).setOnClickListener(new View.OnClickListener() { // from class: com.softdroid.fake.call.Activities.Record_Audio.7
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    for (int i = 0; i < Record_Audio.this.al_images.size(); i++) {
                        File file = new File(Record_Audio.this.al_images.get(i).getStr_path());
                        if (file.exists()) {
                            file.delete();
                        }
                    }
                    Record_Audio.this.al_images.clear();
                    Record_Audio.this.stopPlaying();
                    Record_Audio.recording = false;
                    Record_Audio.this.playing = false;
                    Record_Audio record_Audio = Record_Audio.this;
                    Record_Audio record_Audio2 = Record_Audio.this;
                    ArrayList<Model_Audio> arrayList = record_Audio2.al_images;
                    Record_Audio record_Audio3 = Record_Audio.this;
                    record_Audio.obj_adapter = new Adapter_Music_history(record_Audio2, arrayList, record_Audio3, record_Audio3.dark_mode);
                    Record_Audio.this.recyclerView.setAdapter(Record_Audio.this.obj_adapter);
                    Record_Audio.recording_availbe_or_not_txtview.setText(Record_Audio.this.getResources().getString(R.string.no_recording_availible));
                    Record_Audio.recording_availbe_or_not_txtview.setTextColor(SupportMenu.CATEGORY_MASK);
                    Record_Audio.recording_availbe_or_not_txtview.setVisibility(View.VISIBLE);
                    Record_Audio.record_availble.setVisibility(View.GONE);
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
    }

    private void showSettingsAlert() {
        AlertDialog create = new AlertDialog.Builder(this).create();
        create.setTitle("Alert");
        create.setCancelable(false);
        create.setMessage("App needs to access this Permissions. In order to record voice ");
        create.setButton(-2, "DONT ALLOW", new DialogInterface.OnClickListener() { // from class: com.softdroid.fake.call.Activities.Record_Audio.8
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                Record_Audio.this.finish();
                dialogInterface.dismiss();
            }
        });
        create.setButton(-1, "ALLOW", new DialogInterface.OnClickListener() { // from class: com.softdroid.fake.call.Activities.Record_Audio.9
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                Record_Audio.this.mypermissions();
            }
        });
        create.show();
    }

    private void showSettingsAlert_Externel_Storage() {
        AlertDialog create = new AlertDialog.Builder(this).create();
        create.setTitle("Alert");
        create.setCancelable(false);
        create.setMessage("App needs to access this Permissions. In order PLay Recorded voice ");
        create.setButton(-2, "DONT ALLOW", new DialogInterface.OnClickListener() { // from class: com.softdroid.fake.call.Activities.Record_Audio.10
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                Record_Audio.this.finish();
                dialogInterface.dismiss();
            }
        });
        create.setButton(-1, "ALLOW", new DialogInterface.OnClickListener() { // from class: com.softdroid.fake.call.Activities.Record_Audio.11
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                Record_Audio.this.mypermissions();
            }
        });
        create.show();
    }

    private void marshmallow_permissions_audio() {
        if (ContextCompat.checkSelfPermission(this, "android.permission.RECORD_AUDIO") == 0) {
            return;
        }
        if (getFromPref(this, "ALLOWED").booleanValue()) {
            showSettingsAlert();
        } else if (ContextCompat.checkSelfPermission(this, "android.permission.RECORD_AUDIO") == 0) {
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.RECORD_AUDIO")) {
                ActivityCompat.requestPermissions(this, new String[]{"android.permission.RECORD_AUDIO"}, 200);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{"android.permission.RECORD_AUDIO"}, 200);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i != 200) {
            if (i == 300) {
                if (iArr.length <= 0 || iArr[0] != 0) {
                    showSettingsAlert_Externel_Storage();
                } else {
                    mypermissions();
                }
            }
        } else if (iArr.length <= 0 || iArr[0] != 0) {
            showSettingsAlert();
        } else {
            mypermissions();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn /* 2131230813 */:
                onBackPressed();
                return;
            case R.id.delete_recording /* 2131230911 */:
                delete_dialoug();
                return;
            case R.id.g1_layout /* 2131230950 */:
                try {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + MainActivity.gridPackage1)));
                    return;
                } catch (Exception unused) {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.map/store/apps/details?id=" + MainActivity.gridPackage1)));
                    return;
                }
            case R.id.g2_layout /* 2131230952 */:
                try {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + MainActivity.gridPackage2)));
                    return;
                } catch (Exception unused2) {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.map/store/apps/details?id=" + MainActivity.gridPackage2)));
                    return;
                }
            case R.id.g3_layout /* 2131230954 */:
                try {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + MainActivity.gridPackage3)));
                    return;
                } catch (Exception unused3) {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.map/store/apps/details?id=" + MainActivity.gridPackage3)));
                    return;
                }
            case R.id.record_imageview /* 2131231090 */:
                if (!this.waiit_one_sec.booleanValue()) {
                    if (recording.booleanValue()) {
                        this.recording_textview.setText(R.string.record);
                        this.record_imageview.setImageResource(R.mipmap.record);
                        this.playing = false;
                        stopPlaying();
                        for (int i = 0; i < this.al_images.size(); i++) {
                            this.al_images.get(i).set_playing(false);
                        }
                        this.obj_adapter.notifyDataSetChanged();
                        stopRecording();
                        this.timer.stop();
                        this.timer.setVisibility(View.INVISIBLE);
                        recording = false;
                    } else {
                        this.playing = false;
                        stopPlaying();
                        for (int i2 = 0; i2 < this.al_images.size(); i2++) {
                            this.al_images.get(i2).set_playing(false);
                        }
                        this.obj_adapter.notifyDataSetChanged();
                        this.recording_textview.setText(getResources().getString(R.string.recording_in_progress));
                        this.timer.setBase(SystemClock.elapsedRealtime());
                        this.timer.start();
                        this.timer.setVisibility(View.VISIBLE);
                        this.record_imageview.setImageResource(R.mipmap.stop_rec);
                        recording = true;
                        try {
                            startRecording();
                        } catch ( Exception unused4) {
                        }
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.softdroid.fake.call.Activities.Record_Audio.12
                        @Override // java.lang.Runnable
                        public void run() {
                            Record_Audio.this.waiit_one_sec = false;
                        }
                    }, 1000);
                    this.waiit_one_sec = true;
                    return;
                }
                return;
            default:
                return;
        }
    }


    public void stopPlaying() {
        if (Adapter_Music_history.mediaPlayer != null) {
            Adapter_Music_history.mediaPlayer.stop();
            Adapter_Music_history.mediaPlayer.release();
            Adapter_Music_history.mediaPlayer = null;
        }
    }

    private void startRecording() {
        this.mynewfile = mFileName + Long.valueOf(System.currentTimeMillis() / 1000).toString() + ".3gp";
        MediaRecorder mediaRecorder = new MediaRecorder();
        this.mRecorder = mediaRecorder;
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        this.mRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
        this.mRecorder.setOutputFile(this.mynewfile);
        this.mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try {
            this.mRecorder.prepare();
        } catch (IOException unused) {
        }
        try {
            this.mRecorder.start();
        } catch (IllegalStateException unused2) {
            Toast.makeText(this, getResources().getString(R.string.opps_something_went_wrong),  Toast.LENGTH_LONG).show();
        } catch (Exception unused3) {
            Toast.makeText(this, getResources().getString(R.string.opps_something_went_wrong),  Toast.LENGTH_LONG).show();
        }
    }

    private void stopRecording() {
        try {
            this.mRecorder.stop();
            this.mRecorder.release();
            this.mRecorder = null;
        } catch ( Exception unused) {
        }
        try {
            this.delete_recording.setVisibility(View.VISIBLE);
        } catch ( Exception unused2) {
        }
        try {
            record_availble.setVisibility(View.VISIBLE);
        } catch ( Exception unused3) {
        }
        try {
            recording_availbe_or_not_txtview.setVisibility(View.GONE);
        } catch ( Exception unused4) {
        }
        try {
            File file = new File(this.mynewfile);
            String absolutePath = file.getAbsolutePath();
            String name = file.getName();
            long length = file.length();
            Model_Audio model_Audio = new Model_Audio();
            this.obj_model = model_Audio;
            model_Audio.setStr_path(absolutePath);
            this.obj_model.setAudio_video_name(name);
            this.obj_model.setSize(length);
            this.obj_model.set_playing(false);
            this.al_images.add(this.obj_model);
            this.obj_adapter.notifyDataSetChanged();
        } catch ( Exception unused5) {
        }
    }

    @Override
    public void onBackPressed() {
        this.sharedPreferences.edit().putBoolean(getResources().getString(R.string.stop_media_player_pref), true).apply();
        this.obj_adapter.notifyDataSetChanged();
        if (recording.booleanValue()) {
            stopRecording();
        }
        if (this.playing.booleanValue()) {
            stopPlaying();
        }
        recording = false;
        if (this.mInterstitialAd.isLoaded()) {
            this.mInterstitialAd.show();
            Bundle bundle = new Bundle();
            bundle.putString("Record_Audio_intrstial_show_back_prsd", "Record_Audio_intrstial_show_back_prsd");
           // FirebaseAnalytics.getInstance(this).logEvent("Record_Audio_intrstial_show_back_prsd", bundle);
        } else {
            finish();
        }
        this.mInterstitialAd.setAdListener(new AdListener() { // from class: com.softdroid.fake.call.Activities.Record_Audio.13
            @Override // com.google.android.gms.ads.AdListener
            public void onAdClosed() {
                super.onAdClosed();
                Bundle bundle2 = new Bundle();
                bundle2.putString("Record_Audio_intrstial_closed_back_prsd", "Record_Audio_intrstial_closed_back_prsd");
              //  FirebaseAnalytics.getInstance(Record_Audio.this).logEvent("Record_Audio_intrstial_closed_back_prsd", bundle2);
                Record_Audio.this.finish();
            }

            @Override // com.google.android.gms.ads.AdListener, com.google.android.gms.internal.ads.zzve
            public void onAdClicked() {
                super.onAdClicked();
                Bundle bundle2 = new Bundle();
                bundle2.putString("Record_Audio_intrstial_clicked_back_prsd", "Record_Audio_intrstial_clicked_back_prsd");
               // FirebaseAnalytics.getInstance(Record_Audio.this).logEvent("Record_Audio_intrstial_clicked_back_prsd", bundle2);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (this.sharedPreferences.getBoolean(getResources().getString(R.string.dark_mode_pref), false)) {
            this.top_actionbar_layout.setBackgroundColor(getResources().getColor(R.color.top_bar_dark));
            this.main_layout.setBackgroundColor(getResources().getColor(R.color.background_dark));
            this.recyclerView.setBackground(getResources().getDrawable(R.drawable.ad_bg_new_dark));
            this.recording_textview.setTextColor(getResources().getColor(R.color.text_color_dark));
            record_availble.setImageResource(R.mipmap.avalible_dashes_dark);
            this.timer.setTextColor(getResources().getColor(R.color.white));
        }
    }
}
