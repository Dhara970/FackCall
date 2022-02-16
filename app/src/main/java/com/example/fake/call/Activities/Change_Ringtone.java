package com.example.fake.call.Activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
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

import com.example.fake.call.MainActivity;
import com.example.fake.call.R;
import java.util.Stack;

/* loaded from: classes2.dex */
public class Change_Ringtone extends AppCompatActivity implements View.OnClickListener {
    public static final String ALLOW_KEY = "ALLOWED";
    public static final String AUDIO_PREF = "AUDIO_PREF";
    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 300;
    private LinearLayout adContainerView;
    private AdView adaptive_adView;
    private Uri audioFileUri = null;
    ImageView back_btn;
    FrameLayout frameLayout_1;
    FrameLayout frameLayout_2;
    InterstitialAd mInterstitialAd;
    RelativeLayout main_layout;
    private UnifiedNativeAd nativeAd;
    private UnifiedNativeAd nativeAd2;
    ImageView options;
    ImageView or_imageview;
    LinearLayout select_ringtone;
    LinearLayout select_ringtone_main;
    TextView select_ringtone_txtview;
    TextView set_as_default_ringtone_txtview;
    LinearLayout set_default_ringtone;
    LinearLayout set_default_ringtone_main;
    SharedPreferences sharedPreferences;
    ShimmerFrameLayout shimmerFrameLayout;
    ShimmerFrameLayout shimmerFrameLayout1;
    LinearLayout top_actionbar_layout;


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
        setContentView(R.layout.activity_change__ringtone);
        ShimmerFrameLayout shimmerFrameLayout = (ShimmerFrameLayout) findViewById(R.id.ad_loader1);
        this.shimmerFrameLayout1 = shimmerFrameLayout;
        shimmerFrameLayout.startShimmer();
        ShimmerFrameLayout shimmerFrameLayout2 = (ShimmerFrameLayout) findViewById(R.id.ad_loader);
        this.shimmerFrameLayout = shimmerFrameLayout2;
        shimmerFrameLayout2.startShimmer();
        initilize_ads();
        getting_prefrence();
        initilize_components();
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

    private void getting_prefrence() {
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    }

    private void initilize_components() {
        this.select_ringtone = (LinearLayout) findViewById(R.id.select_ringtone);
        this.set_default_ringtone = (LinearLayout) findViewById(R.id.set_default_ringtone);
        this.top_actionbar_layout = (LinearLayout) findViewById(R.id.top_actionbar_layout);
        this.select_ringtone_main = (LinearLayout) findViewById(R.id.select_ringtone_main);
        this.set_default_ringtone_main = (LinearLayout) findViewById(R.id.set_default_ringtone_main);
        this.main_layout = (RelativeLayout) findViewById(R.id.main_layout);
        this.back_btn = (ImageView) findViewById(R.id.back_btn);
        this.options = (ImageView) findViewById(R.id.options);
        this.or_imageview = (ImageView) findViewById(R.id.or_imageview);
        this.select_ringtone_txtview = (TextView) findViewById(R.id.select_ringtone_txtview);
        this.set_as_default_ringtone_txtview = (TextView) findViewById(R.id.set_as_default_ringtone_txtview);
        this.select_ringtone.setOnClickListener(this);
        this.set_default_ringtone.setOnClickListener(this);
        this.options.setOnClickListener(this);
        this.back_btn.setOnClickListener(this);
    }

    private void initilize_ads() {
        InterstitialAd interstitialAd = new InterstitialAd(this);
        this.mInterstitialAd = interstitialAd;
        interstitialAd.setAdUnitId(getResources().getString(R.string.Interstitial_admob));
        requestNewInterstitial();
        load_adaptive_Banner();
        loading_native_advance_ad2();
    }

    private void load_adaptive_Banner() {
        MobileAds.initialize(this, new OnInitializationCompleteListener() { // from class: com.softdroid.fake.call.Activities.Change_Ringtone.1
            @Override // com.google.android.gms.ads.initialization.OnInitializationCompleteListener
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        this.adContainerView = (LinearLayout) findViewById(R.id.banner_container);
        AdView adView = new AdView(this);
        this.adaptive_adView = adView;
        adView.setAdUnitId(getResources().getString(R.string.banner_admob));
        this.adContainerView.addView(this.adaptive_adView);
        AdRequest build = new AdRequest.Builder().addTestDevice(getResources().getString(R.string.m11_test_device)).addTestDevice(getResources().getString(R.string.vicky_s8)).build();
        this.adaptive_adView.setAdSize(getAdSize());
        this.adaptive_adView.loadAd(build);
        this.adaptive_adView.setAdListener(new AdListener() { // from class: com.softdroid.fake.call.Activities.Change_Ringtone.2
            @Override // com.google.android.gms.ads.AdListener, com.google.android.gms.internal.ads.zzve
            public void onAdClicked() {
                super.onAdClicked();
                Bundle bundle = new Bundle();
                bundle.putString("Chng_Rngtn_Act_banner_click", "Chng_Rngtn_Act_banner_click");
               // FirebaseAnalytics.getInstance(Change_Ringtone.this).logEvent("Chng_Rngtn_Act_banner_click", bundle);
            }

            @Override // com.google.android.gms.ads.AdListener
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                Bundle bundle = new Bundle();
                bundle.putString("Chng_Rngtn_Act_banner_failed", "Chng_Rngtn_Act_banner_failed");
               // FirebaseAnalytics.getInstance(Change_Ringtone.this).logEvent("Chng_Rngtn_Act_banner_failed", bundle);
                Change_Ringtone.this.shimmerFrameLayout.setVisibility(View.GONE);
                Change_Ringtone.this.shimmerFrameLayout.stopShimmer();
            }

            @Override // com.google.android.gms.ads.AdListener
            public void onAdLoaded() {
                super.onAdLoaded();
                Change_Ringtone.this.shimmerFrameLayout.setVisibility(View.GONE);
                Change_Ringtone.this.shimmerFrameLayout.stopShimmer();
            }
        });
    }

    private AdSize getAdSize() {
        Display defaultDisplay = getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, (int) (((float) displayMetrics.widthPixels) / displayMetrics.density));
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
            videoController.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() { // from class: com.softdroid.fake.call.Activities.Change_Ringtone.3
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
        builder.forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() { // from class: com.softdroid.fake.call.Activities.Change_Ringtone.4
            @Override // com.google.android.gms.ads.formats.UnifiedNativeAd.OnUnifiedNativeAdLoadedListener
            public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                if (Change_Ringtone.this.nativeAd2 != null) {
                    Change_Ringtone.this.nativeAd2.destroy();
                }
                Change_Ringtone.this.nativeAd2 = unifiedNativeAd;
                FrameLayout frameLayout = (FrameLayout) Change_Ringtone.this.findViewById(R.id.fl_adplaceholder_2);
                UnifiedNativeAdView unifiedNativeAdView = (UnifiedNativeAdView) Change_Ringtone.this.getLayoutInflater().inflate(R.layout.ad_unified_caller, (ViewGroup) null);
                Change_Ringtone.this.populateUnifiedNativeAdView(unifiedNativeAd, unifiedNativeAdView);
                frameLayout.removeAllViews();
                frameLayout.addView(unifiedNativeAdView);
            }
        });
        builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(true).build()).build());
        builder.withAdListener(new AdListener() { // from class: com.softdroid.fake.call.Activities.Change_Ringtone.5
            @Override // com.google.android.gms.ads.AdListener
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                Bundle bundle = new Bundle();
                bundle.putString("Chng_Rngtn_Act_native_failed", "Chng_Rngtn_Act_native_failed");
               // FirebaseAnalytics.getInstance(Change_Ringtone.this).logEvent("Chng_Rngtn_Act_native_failed", bundle);
                Change_Ringtone.this.shimmerFrameLayout1.setVisibility(View.GONE);
                Change_Ringtone.this.shimmerFrameLayout1.stopShimmer();
            }

            @Override // com.google.android.gms.ads.AdListener
            public void onAdLoaded() {
                super.onAdLoaded();
                Change_Ringtone.this.frameLayout_2.setVisibility(View.VISIBLE);
                Change_Ringtone.this.shimmerFrameLayout1.setVisibility(View.GONE);
                Change_Ringtone.this.shimmerFrameLayout1.stopShimmer();
                Bundle bundle = new Bundle();
                bundle.putString("Chng_Rngtn_Act_native_show", "Chng_Rngtn_Act_native_show");
              //  FirebaseAnalytics.getInstance(Change_Ringtone.this).logEvent("Chng_Rngtn_Act_native_show", bundle);
            }
        }).build().loadAd(new AdRequest.Builder().addTestDevice(getResources().getString(R.string.vicky_s8)).addTestDevice(getResources().getString(R.string.m11_test_device)).addTestDevice(MainActivity.test_mobile_white).addTestDevice("A86A0D556F68465C49063589837FCF98").build());
    }

    private void requestNewInterstitial() {
        this.mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice(getResources().getString(R.string.vicky_s8)).addTestDevice(getResources().getString(R.string.m11_test_device)).addTestDevice(MainActivity.test_mobile_white).addTestDevice("A86A0D556F68465C49063589837FCF98").build());
    }

    @Override
    public void onBackPressed() {
        if (this.mInterstitialAd.isLoaded()) {
            this.mInterstitialAd.show();
            Bundle bundle = new Bundle();
            bundle.putString("Chng_Rngtn_Act_Intrst_Ad_show_back_prsd", "Chng_Rngtn_Act_Intrst_Ad_show_back_prsd");
         //   FirebaseAnalytics.getInstance(this).logEvent("Chng_Rngtn_Act_Intrst_Ad_show_back_prsd", bundle);
        } else {
            finish();
        }
        this.mInterstitialAd.setAdListener(new AdListener() { // from class: com.softdroid.fake.call.Activities.Change_Ringtone.6
            @Override // com.google.android.gms.ads.AdListener
            public void onAdClosed() {
                super.onAdClosed();
                Bundle bundle2 = new Bundle();
                bundle2.putString("Chng_Rngtn_Act_Intrst_closed_back_prsd", "Chng_Rngtn_Act_Intrst_closed_back_prsd");
              //  FirebaseAnalytics.getInstance(Change_Ringtone.this).logEvent("Chng_Rngtn_Act_Intrst_closed_back_prsd", bundle2);
                Change_Ringtone.this.finish();
            }

            @Override // com.google.android.gms.ads.AdListener, com.google.android.gms.internal.ads.zzve
            public void onAdClicked() {
                super.onAdClicked();
                Bundle bundle2 = new Bundle();
                bundle2.putString("Chng_Rngtn_Act_Intrst_click_back_prsd", "Chng_Rngtn_Act_Intrst_click_back_prsd");
              //  FirebaseAnalytics.getInstance(Change_Ringtone.this).logEvent("Chng_Rngtn_Act_Intrst_click_back_prsd", bundle2);
            }
        });
    }


    public void mypermissions() {
        if (ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            marshmallow_permissions_WRITE_EXTERNAL_STORAGE();
        } else {
            startActivityForResult(Intent.createChooser(new Intent("android.intent.action.PICK", MediaStore.Audio.Media.EXTERNAL_CONTENT_URI), ""), 1);
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

    private void showSettingsAlert() {
        AlertDialog create = new AlertDialog.Builder(this).create();
        create.setTitle("Alert");
        create.setCancelable(false);
        create.setMessage("App needs to access this Permissions. In order to Select Custom Ringtone");
        create.setButton(-2, "DONT ALLOW", new DialogInterface.OnClickListener() { // from class: com.softdroid.fake.call.Activities.Change_Ringtone.7
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        create.setButton(-1, "ALLOW", new DialogInterface.OnClickListener() { // from class: com.softdroid.fake.call.Activities.Change_Ringtone.8
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                Change_Ringtone.this.mypermissions();
            }
        });
        create.show();
    }

    @Override
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 300) {
            if (iArr.length <= 0 || iArr[0] != 0) {
                showSettingsAlert();
            } else {
                mypermissions();
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn /* 2131230813 */:
                onBackPressed();
                return;
            case R.id.options /* 2131231049 */:
                pop_up_menu();
                return;
            case R.id.select_ringtone /* 2131231139 */:
                if (Build.VERSION.SDK_INT > 21) {
                    mypermissions();
                    return;
                } else {
                    startActivityForResult(Intent.createChooser(new Intent("android.intent.action.PICK", MediaStore.Audio.Media.EXTERNAL_CONTENT_URI), ""), 1);
                    return;
                }
            case R.id.set_default_ringtone /* 2131231145 */:
                this.sharedPreferences.edit().putBoolean("custom_ring_selected", false).commit();
                Toast.makeText(this, "Default Ringtone Selected",  Toast.LENGTH_LONG).show();
                return;
            default:
                return;
        }
    }

    public void pop_up_menu() {
        PopupMenu popupMenu = new PopupMenu(this, this.options);
        popupMenu.getMenuInflater().inflate(R.menu.menu_setting, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() { // from class: com.softdroid.fake.call.Activities.Change_Ringtone.9
            @Override // android.widget.PopupMenu.OnMenuItemClickListener
            public boolean onMenuItemClick(MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.like_btn) {
                    Change_Ringtone.this.show_rating_dialoug();
                    return true;
                } else if (itemId == R.id.more_free_apps) {
                    try {
                        Change_Ringtone.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://search?q=pub:" + Change_Ringtone.this.getResources().getString(R.string.account_name))));
                    } catch (ActivityNotFoundException unused) {
                        Change_Ringtone.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/developer?id=" + Change_Ringtone.this.getResources().getString(R.string.account_name))));
                    }
                    return true;
                } else if (itemId != R.id.privacy) {
                    return true;
                } else {
                    Change_Ringtone.this.privacy_policy();
                    return true;
                }
            }
        });
        popupMenu.show();
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_setting, menu);
        return true;
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.like_btn /* 2131230995 */:
                String packageName = getPackageName();
                try {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + packageName)));
                } catch (ActivityNotFoundException unused) {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + packageName)));
                }
                return true;
            case R.id.more_free_apps /* 2131231018 */:
                try {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://search?q=pub:" + getResources().getString(R.string.account_name))));
                } catch (ActivityNotFoundException unused2) {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/developer?id=" + getResources().getString(R.string.account_name))));
                }
                return true;
            case R.id.options /* 2131231049 */:
                pop_up_menu();
                return true;
            case R.id.privacy /* 2131231075 */:
                webviewDialoug();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    public void fb_link_clicked(View view) {
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse(getResources().getString(R.string.fb_page_url))));
    }

    public void email_link_clicked(View view) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("*/*");
        intent.putExtra("android.intent.extra.EMAIL", new String[]{getResources().getString(R.string.info_softdroid_org)});
        intent.putExtra("android.intent.extra.SUBJECT", getResources().getString(R.string.app_name) + " FeedBack");
        startActivity(createEmailOnlyChooserIntent(intent, "Send via email"));
    }

    public void admob_policy_link_clicked(View view) {
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse(getResources().getString(R.string.google_privcay_policy_link))));
    }


    public void privacy_policy() {
        AlertDialog create = new AlertDialog.Builder(this).create();
        create.setCancelable(true);
        LayoutInflater layoutInflater = getLayoutInflater();
        if (this.sharedPreferences.getBoolean(getResources().getString(R.string.dark_mode_pref), false)) {
            create.setView(layoutInflater.inflate(R.layout.privacy_policy_dark, (ViewGroup) null));
        } else {
            create.setView(layoutInflater.inflate(R.layout.privacy_policy, (ViewGroup) null));
        }
        create.setButton(-1, "Close", new DialogInterface.OnClickListener() { // from class: com.softdroid.fake.call.Activities.Change_Ringtone.10
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        create.show();
    }


    public void show_rating_dialoug() {
        final Dialog dialog = new Dialog(this);
        try {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        } catch ( Exception unused) {
        }
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.rating_dialoug);
        final RatingBar ratingBar = (RatingBar) dialog.findViewById(R.id.rating_bar);
        ratingBar.setNumStars(5);
        ((Button) dialog.findViewById(R.id.submit)).setOnClickListener(new View.OnClickListener() { // from class: com.softdroid.fake.call.Activities.Change_Ringtone.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ratingBar.getRating() <= 3.0f) {
                    Change_Ringtone.this.sendFeedback();
                    dialog.dismiss();
                    return;
                }
                String packageName = Change_Ringtone.this.getPackageName();
                try {
                    Change_Ringtone.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + packageName)));
                } catch (ActivityNotFoundException unused2) {
                    Change_Ringtone.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + packageName)));
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    public void sendFeedback() {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("*/*");
        intent.putExtra("android.intent.extra.EMAIL", new String[]{getResources().getString(R.string.account_email)});
        intent.putExtra("android.intent.extra.SUBJECT", getResources().getString(R.string.app_name) + " FeedBack");
        startActivity(createEmailOnlyChooserIntent(intent, "Send via email"));
    }

    public Intent createEmailOnlyChooserIntent(Intent intent, CharSequence charSequence) {
        Stack stack = new Stack();
        for (ResolveInfo resolveInfo : getPackageManager().queryIntentActivities(new Intent("android.intent.action.SENDTO", Uri.fromParts("mailto", "", null)), 0)) {
            Intent intent2 = new Intent(intent);
            intent2.setPackage(resolveInfo.activityInfo.packageName);
            stack.add(intent2);
        }
        if (stack.isEmpty()) {
            return Intent.createChooser(intent, charSequence);
        }
        Intent createChooser = Intent.createChooser((Intent) stack.remove(0), charSequence);
        createChooser.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) stack.toArray(new Parcelable[stack.size()]));
        return createChooser;
    }

    private void webviewDialoug() {
        AlertDialog create = new AlertDialog.Builder(this).create();
        create.setCancelable(true);
        LayoutInflater layoutInflater = getLayoutInflater();
        if (this.sharedPreferences.getBoolean(getResources().getString(R.string.dark_mode_pref), false)) {
            create.setView(layoutInflater.inflate(R.layout.privacy_policy_dark, (ViewGroup) null));
        } else {
            create.setView(layoutInflater.inflate(R.layout.privacy_policy, (ViewGroup) null));
        }
        create.setButton(-1, "Close", new DialogInterface.OnClickListener() { // from class: com.softdroid.fake.call.Activities.Change_Ringtone.12
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        create.show();
    }

    @Override
    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1 && i2 == -1) {
            try {
                this.audioFileUri = intent.getData();
                Toast.makeText(getApplicationContext(), "Ringtone Selected ", Toast.LENGTH_SHORT).show();
                this.sharedPreferences.edit().putBoolean("custom_ring_selected", true).commit();
                this.sharedPreferences.edit().putString("ringtone_path", this.audioFileUri.toString()).commit();
            } catch (  Exception unused) {
            }
        }
        super.onActivityResult(i, i2, intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (this.sharedPreferences.getBoolean(getResources().getString(R.string.dark_mode_pref), false)) {
            this.top_actionbar_layout.setBackgroundColor(getResources().getColor(R.color.top_bar_dark));
            this.main_layout.setBackgroundColor(getResources().getColor(R.color.background_dark));
            this.select_ringtone_main.setBackgroundColor(getResources().getColor(R.color.top_bar_dark));
            this.set_default_ringtone_main.setBackgroundColor(getResources().getColor(R.color.top_bar_dark));
            this.or_imageview.setColorFilter(getResources().getColor(R.color.text_color_dark));
            this.select_ringtone_txtview.setTextColor(getResources().getColor(R.color.text_color_dark));
            this.set_as_default_ringtone_txtview.setTextColor(getResources().getColor(R.color.text_color_dark));
        }
    }
}
