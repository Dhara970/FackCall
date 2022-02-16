package com.example.fake.call.Activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
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
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
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
import com.example.fake.call.MainActivity;
import com.example.fake.call.R;
import java.util.Stack;

/* loaded from: classes2.dex */
public class Exit_Act extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout adContainerView;
    private AdView adaptive_adView;
    Animation animation;
    ImageView back_btn;
    LinearLayout exit_layout;
    FrameLayout frameLayout_1;
    FrameLayout frameLayout_2;
    ImageView g1;
    LinearLayout g1_layout;
    TextView g1_textview;
    ImageView g2;
    LinearLayout g2_layout;
    TextView g2_textview;
    ImageView g3;
    LinearLayout g3_layout;
    TextView g3_textview;
    RelativeLayout main_layout;
    LinearLayout more_apps_layout;
    LinearLayout more_apps_layout_main;
    TextView more_apps_txtview;
    TextView more_from_developer_txtview;
    LinearLayout myads_layout;
    SharedPreferences mysharedPreference;
    private UnifiedNativeAd nativeAd;
    private UnifiedNativeAd nativeAd2;
    ImageView options;
    LinearLayout privacy_policy_layout;
    LinearLayout privacy_policy_layout_main;
    TextView privacy_policy_txtview;
    LinearLayout rate_us_layout;
    LinearLayout rate_us_layout_main;
    TextView rate_us_txtview;
    LinearLayout share_app_layout;
    LinearLayout share_app_layout_main;
    TextView share_app_txtview;
    ShimmerFrameLayout shimmerFrameLayout;
    ShimmerFrameLayout shimmerFrameLayout1;
    public String test_mobile_white;
    LinearLayout top_actionbar_layout;
    public String uxi_device;

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
        setContentView(R.layout.activity_exit_);
        ShimmerFrameLayout shimmerFrameLayout = (ShimmerFrameLayout) findViewById(R.id.ad_loader1);
        this.shimmerFrameLayout1 = shimmerFrameLayout;
        shimmerFrameLayout.startShimmer();
        ShimmerFrameLayout shimmerFrameLayout2 = (ShimmerFrameLayout) findViewById(R.id.ad_loader);
        this.shimmerFrameLayout = shimmerFrameLayout2;
        shimmerFrameLayout2.startShimmer();
        this.test_mobile_white = getResources().getString(R.string.test_mobile_white);
        this.uxi_device = getResources().getString(R.string.uxi_device);
        loading_native_advance_ad();
        load_adaptive_Banner();
        initlize_components();
        initilizeing_small_ads();
        getting_prefrence();
    }

    private void getting_prefrence() {
        this.mysharedPreference = PreferenceManager.getDefaultSharedPreferences(this);
    }

    private void initilizeing_small_ads() {
        this.animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim);
        this.g1 = (ImageView) findViewById(R.id.g1);
        this.g2 = (ImageView) findViewById(R.id.g2);
        this.g3 = (ImageView) findViewById(R.id.g3);
        this.g1_layout = (LinearLayout) findViewById(R.id.g1_layout);
        this.g2_layout = (LinearLayout) findViewById(R.id.g2_layout);
        this.g3_layout = (LinearLayout) findViewById(R.id.g3_layout);
        this.myads_layout = (LinearLayout) findViewById(R.id.more_from_developer_layout);
        this.g3_textview = (TextView) findViewById(R.id.title3);
        this.more_from_developer_txtview = (TextView) findViewById(R.id.more_from_developer_txtview);
        this.more_apps_txtview = (TextView) findViewById(R.id.more_apps_txtview);
        this.rate_us_txtview = (TextView) findViewById(R.id.rate_us_txtview);
        this.share_app_txtview = (TextView) findViewById(R.id.share_app_txtview);
        this.privacy_policy_txtview = (TextView) findViewById(R.id.privacy_policy_txtview);
        this.g2_textview = (TextView) findViewById(R.id.title2);
        this.g1_textview = (TextView) findViewById(R.id.title1);
        this.g1_layout.setOnClickListener(this);
        this.g2_layout.setOnClickListener(this);
        this.g3_layout.setOnClickListener(this);
        if (MainActivity.drawableG1 != null) {
            this.g1.setImageDrawable(MainActivity.drawableG1);
            this.g1_textview.setText(MainActivity.title1);
            this.myads_layout.setVisibility(View.VISIBLE);
            this.g1.startAnimation(this.animation);
        }
        if (MainActivity.drawableG2 != null) {
            this.g2.setImageDrawable(MainActivity.drawableG2);
            this.g2_textview.setText(MainActivity.title2);
            this.myads_layout.setVisibility(View.VISIBLE);
            this.g2.startAnimation(this.animation);
        }
        if (MainActivity.drawableG3 != null) {
            this.g3.setImageDrawable(MainActivity.drawableG3);
            this.g3_textview.setText(MainActivity.title3);
            this.myads_layout.setVisibility(View.VISIBLE);
            this.g3.startAnimation(this.animation);
        }
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
            videoController.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() { // from class: com.softdroid.fake.call.Activities.Exit_Act.1
                @Override // com.google.android.gms.ads.VideoController.VideoLifecycleCallbacks
                public void onVideoEnd() {
                    super.onVideoEnd();
                }
            });
        }
    }

    private void loading_native_advance_ad() {
        AdLoader.Builder builder = new AdLoader.Builder(this, getResources().getString(R.string.native_advance_admob));
        this.frameLayout_1 = (FrameLayout) findViewById(R.id.fl_adplaceholder_1);
        builder.forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() { // from class: com.softdroid.fake.call.Activities.Exit_Act.2
            @Override // com.google.android.gms.ads.formats.UnifiedNativeAd.OnUnifiedNativeAdLoadedListener
            public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                if (Exit_Act.this.nativeAd != null) {
                    Exit_Act.this.nativeAd.destroy();
                }
                Exit_Act.this.nativeAd = unifiedNativeAd;
                FrameLayout frameLayout = (FrameLayout) Exit_Act.this.findViewById(R.id.fl_adplaceholder_1);
                UnifiedNativeAdView unifiedNativeAdView = (UnifiedNativeAdView) Exit_Act.this.getLayoutInflater().inflate(R.layout.ad_unified, (ViewGroup) null);
                Exit_Act.this.populateUnifiedNativeAdView(unifiedNativeAd, unifiedNativeAdView);
                frameLayout.removeAllViews();
                frameLayout.addView(unifiedNativeAdView);
            }
        });
        builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(true).build()).build());
        builder.withAdListener(new AdListener() { // from class: com.softdroid.fake.call.Activities.Exit_Act.3
            @Override // com.google.android.gms.ads.AdListener
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                Exit_Act.this.shimmerFrameLayout1.setVisibility(View.GONE);
                Exit_Act.this.shimmerFrameLayout1.stopShimmer();
                Exit_Act.this.frameLayout_1.setVisibility(View.GONE);
                Bundle bundle = new Bundle();
                bundle.putString("Exit_Act_native_failed", "Exit_Act_native_failed");
             //   FirebaseAnalytics.getInstance(Exit_Act.this).logEvent("Exit_Act_native_failed", bundle);
            }

            @Override // com.google.android.gms.ads.AdListener
            public void onAdLoaded() {
                super.onAdLoaded();
                Exit_Act.this.frameLayout_1.setVisibility(View.VISIBLE);
                Exit_Act.this.shimmerFrameLayout1.setVisibility(View.GONE);
                Exit_Act.this.shimmerFrameLayout1.stopShimmer();
                Bundle bundle = new Bundle();
                bundle.putString("Exit_Act_native_show", "Exit_Act_native_show");
                //FirebaseAnalytics.getInstance(Exit_Act.this).logEvent("Exit_Act_native_show", bundle);
            }

            @Override // com.google.android.gms.ads.AdListener, com.google.android.gms.internal.ads.zzve
            public void onAdClicked() {
                super.onAdClicked();
                Bundle bundle = new Bundle();
                bundle.putString("Exit_Act_native_clicked", "Exit_Act_native_clicked");
                //FirebaseAnalytics.getInstance(Exit_Act.this).logEvent("Exit_Act_native_clicked", bundle);
            }
        }).build().loadAd(new AdRequest.Builder().addTestDevice(getResources().getString(R.string.vicky_s8)).addTestDevice(getResources().getString(R.string.rorjan_test_device)).addTestDevice(this.uxi_device).addTestDevice(this.test_mobile_white).addTestDevice("A86A0D556F68465C49063589837FCF98").build());
    }

    private void load_adaptive_Banner() {
        MobileAds.initialize(this, new OnInitializationCompleteListener() { // from class: com.softdroid.fake.call.Activities.Exit_Act.4
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
        this.adaptive_adView.setAdListener(new AdListener() { // from class: com.softdroid.fake.call.Activities.Exit_Act.5
            @Override // com.google.android.gms.ads.AdListener, com.google.android.gms.internal.ads.zzve
            public void onAdClicked() {
                super.onAdClicked();
                Bundle bundle = new Bundle();
                bundle.putString("Exit_Act_banner_clicked", "Exit_Act_banner_clicked");
               // FirebaseAnalytics.getInstance(Exit_Act.this).logEvent("Exit_Act_banner_clicked", bundle);
            }

            @Override // com.google.android.gms.ads.AdListener
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                Exit_Act.this.shimmerFrameLayout.setVisibility(View.GONE);
                Exit_Act.this.shimmerFrameLayout.stopShimmer();
                Bundle bundle = new Bundle();
                bundle.putString("Exit_Act_banner_failed", "Exit_Act_banner_failed");
               // FirebaseAnalytics.getInstance(Exit_Act.this).logEvent("Exit_Act_banner_failed", bundle);
            }

            @Override // com.google.android.gms.ads.AdListener
            public void onAdImpression() {
                super.onAdImpression();
                Exit_Act.this.shimmerFrameLayout.setVisibility(View.GONE);
                Exit_Act.this.shimmerFrameLayout.stopShimmer();
                Bundle bundle = new Bundle();
                bundle.putString("Exit_Act_banner_show", "Exit_Act_banner_show");
               // FirebaseAnalytics.getInstance(Exit_Act.this).logEvent("Exit_Act_banner_show", bundle);
            }
        });
    }

    private AdSize getAdSize() {
        Display defaultDisplay = getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, (int) (((float) displayMetrics.widthPixels) / displayMetrics.density));
    }

    private void initlize_components() {
        this.back_btn = (ImageView) findViewById(R.id.back_btn);
        this.options = (ImageView) findViewById(R.id.options);
        this.rate_us_layout = (LinearLayout) findViewById(R.id.rate_us_layout);
        this.more_apps_layout = (LinearLayout) findViewById(R.id.more_apps_layout);
        this.exit_layout = (LinearLayout) findViewById(R.id.exit_layout);
        this.share_app_layout = (LinearLayout) findViewById(R.id.share_app_layout);
        this.privacy_policy_layout = (LinearLayout) findViewById(R.id.privacy_policy_layout);
        this.top_actionbar_layout = (LinearLayout) findViewById(R.id.top_actionbar_layout);
        this.more_apps_layout_main = (LinearLayout) findViewById(R.id.more_apps_layout_main);
        this.rate_us_layout_main = (LinearLayout) findViewById(R.id.rate_us_layout_main);
        this.share_app_layout_main = (LinearLayout) findViewById(R.id.share_app_layout_main);
        this.privacy_policy_layout_main = (LinearLayout) findViewById(R.id.privacy_policy_layout_main);
        this.main_layout = (RelativeLayout) findViewById(R.id.main_layout);
        this.rate_us_layout.setOnClickListener(this);
        this.more_apps_layout.setOnClickListener(this);
        this.exit_layout.setOnClickListener(this);
        this.share_app_layout.setOnClickListener(this);
        this.privacy_policy_layout.setOnClickListener(this);
        this.options.setOnClickListener(this);
        this.back_btn.setOnClickListener(this);
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
        ratingBar.setMax(5);
        ratingBar.setNumStars(5);
        ((Button) dialog.findViewById(R.id.submit)).setOnClickListener(new View.OnClickListener() { // from class: com.softdroid.fake.call.Activities.Exit_Act.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ratingBar.getRating() <= 3.0f) {
                    Exit_Act.this.sendFeedback();
                    dialog.dismiss();
                    return;
                }
                String packageName = Exit_Act.this.getPackageName();
                try {
                    Exit_Act.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + packageName)));
                } catch (ActivityNotFoundException unused2) {
                    Exit_Act.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + packageName)));
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

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn /* 2131230813 */:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                return;
            case R.id.exit_layout /* 2131230933 */:
                finish();
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
            case R.id.more_apps_layout /* 2131231015 */:
                try {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://search?q=pub:" + getResources().getString(R.string.account_name))));
                    return;
                } catch (ActivityNotFoundException unused4) {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/developer?id=" + getResources().getString(R.string.account_name))));
                    return;
                }
            case R.id.options /* 2131231049 */:
                pop_up_menu();
                return;
            case R.id.privacy_policy_layout /* 2131231076 */:
                privacy_policy();
                return;
            case R.id.rate_us_layout /* 2131231085 */:
                show_rating_dialoug();
                return;
            case R.id.share_app_layout /* 2131231151 */:
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("text/plain");
                intent.putExtra("android.intent.extra.SUBJECT", getResources().getString(R.string.app_name));
                intent.putExtra("android.intent.extra.TEXT", "Please download this awesome fake call app to do prank with friends and family  \n  http://play.google.com/store/apps/details?id=" + getPackageName());
                startActivity(Intent.createChooser(intent, "Share via"));
                return;
            default:
                return;
        }
    }

    public void pop_up_menu() {
        PopupMenu popupMenu = new PopupMenu(this, this.options);
        popupMenu.getMenuInflater().inflate(R.menu.menu_setting, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() { // from class: com.softdroid.fake.call.Activities.Exit_Act.7
            @Override // android.widget.PopupMenu.OnMenuItemClickListener
            public boolean onMenuItemClick(MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.like_btn) {
                    Exit_Act.this.show_rating_dialoug();
                    return true;
                } else if (itemId == R.id.more_free_apps) {
                    try {
                        Exit_Act.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://search?q=pub:" + Exit_Act.this.getResources().getString(R.string.account_name))));
                    } catch (ActivityNotFoundException unused) {
                        Exit_Act.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/developer?id=" + Exit_Act.this.getResources().getString(R.string.account_name))));
                    }
                    return true;
                } else if (itemId != R.id.privacy) {
                    return true;
                } else {
                    Exit_Act.this.privacy_policy();
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
        int itemId = menuItem.getItemId();
        if (itemId == R.id.like_btn) {
            String packageName = getPackageName();
            try {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + packageName)));
            } catch (ActivityNotFoundException unused) {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + packageName)));
            }
            return true;
        } else if (itemId == R.id.more_free_apps) {
            try {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://search?q=pub:" + getResources().getString(R.string.account_name))));
            } catch (ActivityNotFoundException unused2) {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/developer?id=" + getResources().getString(R.string.account_name))));
            }
            return true;
        } else if (itemId != R.id.privacy) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            webviewDialoug();
            return true;
        }
    }


    public void privacy_policy() {
        AlertDialog create = new AlertDialog.Builder(this).create();
        create.setCancelable(true);
        LayoutInflater layoutInflater = getLayoutInflater();
        if (this.mysharedPreference.getBoolean(getResources().getString(R.string.dark_mode_pref), false)) {
            create.setView(layoutInflater.inflate(R.layout.privacy_policy_dark, (ViewGroup) null));
        } else {
            create.setView(layoutInflater.inflate(R.layout.privacy_policy, (ViewGroup) null));
        }
        create.setButton(-1, "Close", new DialogInterface.OnClickListener() { // from class: com.softdroid.fake.call.Activities.Exit_Act.8
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        create.show();
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

    private void webviewDialoug() {
        AlertDialog create = new AlertDialog.Builder(this).create();
        create.setCancelable(true);
        LayoutInflater layoutInflater = getLayoutInflater();
        if (this.mysharedPreference.getBoolean(getResources().getString(R.string.dark_mode_pref), false)) {
            create.setView(layoutInflater.inflate(R.layout.privacy_policy_dark, (ViewGroup) null));
        } else {
            create.setView(layoutInflater.inflate(R.layout.privacy_policy, (ViewGroup) null));
        }
        create.setButton(-1, "Close", new DialogInterface.OnClickListener() { // from class: com.softdroid.fake.call.Activities.Exit_Act.9
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        create.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        try {
            this.g1.clearAnimation();
        } catch ( Exception unused) {
        }
        try {
            this.g2.clearAnimation();
        } catch ( Exception unused2) {
        }
        try {
            this.g3.clearAnimation();
        } catch ( Exception unused3) {
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (this.mysharedPreference.getBoolean(getResources().getString(R.string.dark_mode_pref), false)) {
            this.top_actionbar_layout.setBackgroundColor(getResources().getColor(R.color.top_bar_dark));
            this.main_layout.setBackgroundColor(getResources().getColor(R.color.background_dark));
            this.more_from_developer_txtview.setTextColor(getResources().getColor(R.color.text_color_dark));
            this.g1_textview.setTextColor(getResources().getColor(R.color.text_color_dark));
            this.g2_textview.setTextColor(getResources().getColor(R.color.text_color_dark));
            this.g3_textview.setTextColor(getResources().getColor(R.color.text_color_dark));
            this.more_apps_txtview.setTextColor(getResources().getColor(R.color.text_color_dark));
            this.rate_us_txtview.setTextColor(getResources().getColor(R.color.text_color_dark));
            this.share_app_txtview.setTextColor(getResources().getColor(R.color.text_color_dark));
            this.privacy_policy_txtview.setTextColor(getResources().getColor(R.color.text_color_dark));
            this.more_apps_layout_main.setBackgroundResource(R.mipmap.exit_activity_btn_dark);
            this.rate_us_layout_main.setBackgroundResource(R.mipmap.exit_activity_btn_dark);
            this.share_app_layout_main.setBackgroundResource(R.mipmap.exit_activity_btn_dark);
            this.privacy_policy_layout_main.setBackgroundResource(R.mipmap.exit_activity_btn_dark);
        }
    }
}
