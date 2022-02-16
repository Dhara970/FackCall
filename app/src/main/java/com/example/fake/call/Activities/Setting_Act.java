package com.example.fake.call.Activities;

import android.annotation.SuppressLint;
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
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.formats.MediaView;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;

import com.example.fake.call.MainActivity;
import com.example.fake.call.R;
import com.example.fake.call.Service.MyService;
import com.example.fake.call.Service.Screen_off_service;
import com.example.fake.call.Service.Shake_Service;
import com.example.fake.call.Utils;
import java.util.Stack;

import io.github.florent37.shapeofview.shapes.RoundRectView;

/* loaded from: classes2.dex */
public class Setting_Act extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    public static int ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE = 202;
    public static ImageView cancel_shedule_call;
    public static TextView schedule_call_txtview_remaining;
    ImageView back_btn;
    LinearLayout change_ringtone_layout_layout;
    TextView change_ringtone_txtview;
    CheckBox dark_mode_checkbox;
    LinearLayout dark_mode_layout;
    TextView dark_mode_txtview;
    FrameLayout frameLayout_2;
    LinearLayout items_layout;
    InterstitialAd mInterstitialAd;
    RelativeLayout main_layout;
    private UnifiedNativeAd nativeAd2;
    ImageView options;
    LinearLayout power_btn_four_times_layout;
    TextView power_textview;
    CheckBox power_tick;
    LinearLayout schedule_call_layout;
    TextView schedule_call_txtview;
    LinearLayout shake_layout;
    TextView shake_textview;
    CheckBox shake_tick;
    SharedPreferences sharedPreferences;
    ShimmerFrameLayout shimmerFrameLayout;
    LinearLayout top_actionbar_layout;
    LinearLayout vibrate_layout;
    TextView vibrate_textview;
    CheckBox vibrate_tick;
    RoundRectView topRoundrectview;
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
        setContentView(R.layout.activity_setting_);
        ShimmerFrameLayout shimmerFrameLayout = (ShimmerFrameLayout) findViewById(R.id.ad_loader);
        this.shimmerFrameLayout = shimmerFrameLayout;
        shimmerFrameLayout.startShimmer();
        initilize_adz();
        initilize_components();
        getting_prefrence();
    }

    public void checkPermission() {
        if (Build.VERSION.SDK_INT <= 28) {
            setSchedule_dialoug();
        } else if (!Settings.canDrawOverlays(this)) {
            window_permission();
        } else {
            setSchedule_dialoug();
        }
    }

    private void setSchedule_dialoug() {
        final Dialog dialog = new Dialog(this);
        try {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        } catch ( Exception unused) {
        }
        dialog.requestWindowFeature(1);
        if (this.sharedPreferences.getBoolean(getResources().getString(R.string.dark_mode_pref), false)) {
            dialog.setContentView(R.layout.schedule_dialoug_dark);
        } else {
            dialog.setContentView(R.layout.schedule_dialoug);
        }
        ((LinearLayout) dialog.findViewById(R.id.ten_sec)).setOnClickListener(new View.OnClickListener() { // from class: com.softdroid.fake.call.Activities.Setting_Act.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Setting_Act.this.stopService(new Intent(Setting_Act.this, MyService.class));
                Setting_Act.this.sharedPreferences.edit().putInt("milliseconds", 10000).apply();
                Utils.millisecs = 10000;
                Setting_Act.this.startService(new Intent(Setting_Act.this, MyService.class));
                Toast.makeText(Setting_Act.this, "You will receive a call in 10 Seconds !",  Toast.LENGTH_LONG).show();
                Setting_Act.cancel_shedule_call.setVisibility(View.VISIBLE);
                dialog.dismiss();
            }
        });
        ((LinearLayout) dialog.findViewById(R.id.thirty_sec)).setOnClickListener(new View.OnClickListener() { // from class: com.softdroid.fake.call.Activities.Setting_Act.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Setting_Act.this.stopService(new Intent(Setting_Act.this, MyService.class));
                Utils.millisecs = 30000;
                Setting_Act.this.sharedPreferences.edit().putInt("milliseconds", 30000).apply();
                Setting_Act.this.startService(new Intent(Setting_Act.this, MyService.class));
                Toast.makeText(Setting_Act.this, "You will receive a call in 30 Seconds !",  Toast.LENGTH_LONG).show();
                Setting_Act.cancel_shedule_call.setVisibility(View.VISIBLE);
                dialog.dismiss();
            }
        });
        ((LinearLayout) dialog.findViewById(R.id.one_min)).setOnClickListener(new View.OnClickListener() { // from class: com.softdroid.fake.call.Activities.Setting_Act.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Setting_Act.this.stopService(new Intent(Setting_Act.this, MyService.class));
                Utils.millisecs = 60000;
                Setting_Act.this.sharedPreferences.edit().putInt("milliseconds", 60000).apply();
                Setting_Act.this.startService(new Intent(Setting_Act.this, MyService.class));
                Toast.makeText(Setting_Act.this, "You will receive a call in 1 Minute!",  Toast.LENGTH_LONG).show();
                Setting_Act.cancel_shedule_call.setVisibility(View.VISIBLE);
                dialog.dismiss();
            }
        });
        ((LinearLayout) dialog.findViewById(R.id.five_min)).setOnClickListener(new View.OnClickListener() { // from class: com.softdroid.fake.call.Activities.Setting_Act.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Setting_Act.this.stopService(new Intent(Setting_Act.this, MyService.class));
                Utils.millisecs = 300000;
                Setting_Act.this.sharedPreferences.edit().putInt("milliseconds", 300000).apply();
                Setting_Act.this.startService(new Intent(Setting_Act.this, MyService.class));
                Toast.makeText(Setting_Act.this, "You will receive a call in 5 Minutes!",  Toast.LENGTH_LONG).show();
                Setting_Act.cancel_shedule_call.setVisibility(View.VISIBLE);
                dialog.dismiss();
            }
        });
        ((LinearLayout) dialog.findViewById(R.id.fifteen_min)).setOnClickListener(new View.OnClickListener() { // from class: com.softdroid.fake.call.Activities.Setting_Act.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Setting_Act.this.stopService(new Intent(Setting_Act.this, MyService.class));
                Utils.millisecs = 900000;
                Setting_Act.this.sharedPreferences.edit().putInt("milliseconds", 900000).apply();
                Setting_Act.this.startService(new Intent(Setting_Act.this, MyService.class));
                Toast.makeText(Setting_Act.this, "You will receive a call in 15 Minutes!",  Toast.LENGTH_LONG).show();
                Setting_Act.cancel_shedule_call.setVisibility(View.VISIBLE);
                dialog.dismiss();
            }
        });
        ((LinearLayout) dialog.findViewById(R.id.thirty_min)).setOnClickListener(new View.OnClickListener() { // from class: com.softdroid.fake.call.Activities.Setting_Act.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Setting_Act.this.stopService(new Intent(Setting_Act.this, MyService.class));
                Utils.millisecs = 1800000;
                Setting_Act.this.sharedPreferences.edit().putInt("milliseconds", 1800000).apply();
                Setting_Act.this.startService(new Intent(Setting_Act.this, MyService.class));
                Toast.makeText(Setting_Act.this, "You will receive a call in 30 Minutes!",  Toast.LENGTH_LONG).show();
                Setting_Act.cancel_shedule_call.setVisibility(View.VISIBLE);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void window_permission() {
        final Dialog dialog = new Dialog(this);
        try {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        } catch ( Exception unused) {
        }
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.alert_permission);
        ((TextView) dialog.findViewById(R.id.allow)).setOnClickListener(new View.OnClickListener() { // from class: com.softdroid.fake.call.Activities.Setting_Act.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Setting_Act.this.startActivityForResult(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + Setting_Act.this.getPackageName())), Setting_Act.ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE);
                dialog.dismiss();
            }
        });
        ((TextView) dialog.findViewById(R.id.cancle)).setOnClickListener(new View.OnClickListener() { // from class: com.softdroid.fake.call.Activities.Setting_Act.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void initilize_adz() {
        InterstitialAd interstitialAd = new InterstitialAd(this);
        this.mInterstitialAd = interstitialAd;
        interstitialAd.setAdUnitId(getResources().getString(R.string.Interstitial_admob));
        requestNewInterstitial();
        loading_native_advance_ad2();
    }

    private void getting_prefrence() {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        this.sharedPreferences = defaultSharedPreferences;
        this.vibrate_tick.setChecked(defaultSharedPreferences.getBoolean(getResources().getString(R.string.vibration_pref), true));
        this.shake_tick.setChecked(this.sharedPreferences.getBoolean(getResources().getString(R.string.shake_pref), false));
        this.power_tick.setChecked(this.sharedPreferences.getBoolean(getResources().getString(R.string.triple_tap_pref), false));
        if (this.sharedPreferences.getBoolean(getResources().getString(R.string.dark_mode_pref), false)) {
            this.top_actionbar_layout.setBackgroundColor(getResources().getColor(R.color.top_bar_dark));
            this.items_layout.setBackgroundColor(getResources().getColor(R.color.top_bar_dark));
            this.main_layout.setBackgroundColor(getResources().getColor(R.color.background_dark));
            this.shake_textview.setTextColor(getResources().getColor(R.color.text_color_dark));
            this.power_textview.setTextColor(getResources().getColor(R.color.text_color_dark));
            this.vibrate_textview.setTextColor(getResources().getColor(R.color.text_color_dark));
            this.dark_mode_txtview.setTextColor(getResources().getColor(R.color.text_color_dark));
            this.change_ringtone_txtview.setTextColor(getResources().getColor(R.color.text_color_dark));
            this.topRoundrectview.setBackgroundColor(getResources().getColor(R.color.top_bar_dark));

            this.schedule_call_txtview.setTextColor(getResources().getColor(R.color.text_color_dark));
            this.shake_tick.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.toggle_checkbox_selector_dark, 0);
            this.power_tick.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.toggle_checkbox_selector_dark, 0);
            this.vibrate_tick.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.toggle_checkbox_selector_dark, 0);
            this.dark_mode_checkbox.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.toggle_checkbox_selector_dark, 0);
        }
        this.topRoundrectview.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        this.dark_mode_checkbox.setChecked(this.sharedPreferences.getBoolean(getResources().getString(R.string.dark_mode_pref), false));
        this.dark_mode_checkbox.setOnCheckedChangeListener(this);
    }

    public void initilize_banner() {
        AdRequest build = new AdRequest.Builder().addTestDevice(getResources().getString(R.string.vicky_s8)).addTestDevice(MainActivity.uxi_device).addTestDevice(MainActivity.test_mobile_white).addTestDevice("A86A0D556F68465C49063589837FCF98").build();
        final AdView adView = (AdView) findViewById(R.id.myadView1);
        adView.loadAd(build);
        adView.setAdListener(new AdListener() { // from class: com.softdroid.fake.call.Activities.Setting_Act.9
            @Override // com.google.android.gms.ads.AdListener
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                adView.setVisibility(View.GONE);
            }

            @Override // com.google.android.gms.ads.AdListener
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                adView.setVisibility(View.GONE);
                Setting_Act.this.shimmerFrameLayout.setVisibility(View.GONE);
                Setting_Act.this.shimmerFrameLayout.stopShimmer();
                Bundle bundle = new Bundle();
                bundle.putString("Setting_Act_banner_failed", "Setting_Act_banner_failed");
                //FirebaseAnalytics.getInstance(Setting_Act.this).logEvent("Setting_Act_banner_failed", bundle);
            }

            @Override // com.google.android.gms.ads.AdListener
            public void onAdLoaded() {
                super.onAdLoaded();
                adView.setVisibility(View.VISIBLE);
                Setting_Act.this.shimmerFrameLayout.setVisibility(View.GONE);
                Setting_Act.this.shimmerFrameLayout.stopShimmer();
                Bundle bundle = new Bundle();
                bundle.putString("Setting_Act_banner_show", "Setting_Act_banner_show");
                //FirebaseAnalytics.getInstance(Setting_Act.this).logEvent("Setting_Act_banner_show", bundle);
            }

            @Override // com.google.android.gms.ads.AdListener, com.google.android.gms.internal.ads.zzve
            public void onAdClicked() {
                super.onAdClicked();
                Bundle bundle = new Bundle();
                bundle.putString("Setting_Act_banner_clicked", "Setting_Act_banner_clicked");
             //   FirebaseAnalytics.getInstance(Setting_Act.this).logEvent("Setting_Act_banner_clicked", bundle);
            }
        });
    }

    public void requestNewInterstitial() {
        this.mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice(getResources().getString(R.string.vicky_s8)).addTestDevice(getResources().getString(R.string.rorjan_test_device)).addTestDevice(MainActivity.uxi_device).addTestDevice(MainActivity.test_mobile_white).addTestDevice("A86A0D556F68465C49063589837FCF98").build());
    }

    public void pop_up_menu() {
        PopupMenu popupMenu = new PopupMenu(this, this.options);
        popupMenu.getMenuInflater().inflate(R.menu.menu_setting, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() { // from class: com.softdroid.fake.call.Activities.Setting_Act.10
            @Override // android.widget.PopupMenu.OnMenuItemClickListener
            public boolean onMenuItemClick(MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.like_btn) {
                    Setting_Act.this.show_rating_dialoug();
                    return true;
                } else if (itemId == R.id.more_free_apps) {
                    try {
                        Setting_Act.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://search?q=pub:" + Setting_Act.this.getResources().getString(R.string.account_name))));
                    } catch (ActivityNotFoundException unused) {
                        Setting_Act.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/developer?id=" + Setting_Act.this.getResources().getString(R.string.account_name))));
                    }
                    return true;
                } else if (itemId != R.id.privacy) {
                    return true;
                } else {
                    Setting_Act.this.privacy_policy();
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
            String str = getPackageName().toString();
            try {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + str)));
            } catch (ActivityNotFoundException unused) {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + str)));
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
        create.setButton(-1, "Close", new DialogInterface.OnClickListener() { // from class: com.softdroid.fake.call.Activities.Setting_Act.11
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
        ((Button) dialog.findViewById(R.id.submit)).setOnClickListener(new View.OnClickListener() { // from class: com.softdroid.fake.call.Activities.Setting_Act.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ratingBar.getRating() <= 3.0f) {
                    Setting_Act.this.sendFeedback();
                    dialog.dismiss();
                    return;
                }
                String str = Setting_Act.this.getPackageName().toString();
                try {
                    Setting_Act.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + str)));
                } catch (ActivityNotFoundException unused2) {
                    Setting_Act.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + str)));
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
        create.setButton(-1, "Close", new DialogInterface.OnClickListener() { // from class: com.softdroid.fake.call.Activities.Setting_Act.13
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        create.show();
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
            videoController.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() { // from class: com.softdroid.fake.call.Activities.Setting_Act.14
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
        builder.forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() { // from class: com.softdroid.fake.call.Activities.Setting_Act.15
            @Override // com.google.android.gms.ads.formats.UnifiedNativeAd.OnUnifiedNativeAdLoadedListener
            public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                if (Setting_Act.this.nativeAd2 != null) {
                    Setting_Act.this.nativeAd2.destroy();
                }
                Setting_Act.this.nativeAd2 = unifiedNativeAd;
                FrameLayout frameLayout = (FrameLayout) Setting_Act.this.findViewById(R.id.fl_adplaceholder_2);
                UnifiedNativeAdView unifiedNativeAdView = (UnifiedNativeAdView) Setting_Act.this.getLayoutInflater().inflate(R.layout.ad_unified, (ViewGroup) null);
                Setting_Act.this.populateUnifiedNativeAdView(unifiedNativeAd, unifiedNativeAdView);
                frameLayout.removeAllViews();
                frameLayout.addView(unifiedNativeAdView);
            }
        });
        builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(true).build()).build());
        builder.withAdListener(new AdListener() { // from class: com.softdroid.fake.call.Activities.Setting_Act.16
            @Override // com.google.android.gms.ads.AdListener
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                Setting_Act.this.initilize_banner();
                Bundle bundle = new Bundle();
                bundle.putString("Setting_Act_native_failed", "Setting_Act_native_failed");
                //FirebaseAnalytics.getInstance(Setting_Act.this).logEvent("Setting_Act_native_failed", bundle);
            }

            @Override // com.google.android.gms.ads.AdListener
            public void onAdLoaded() {
                super.onAdLoaded();
                Setting_Act.this.frameLayout_2.setVisibility(View.VISIBLE);
                Setting_Act.this.shimmerFrameLayout.setVisibility(View.GONE);
                Setting_Act.this.shimmerFrameLayout.stopShimmer();
                Bundle bundle = new Bundle();
                bundle.putString("Setting_Act_native_show", "Setting_Act_native_show");
               // FirebaseAnalytics.getInstance(Setting_Act.this).logEvent("Setting_Act_native_show", bundle);
            }

            @Override // com.google.android.gms.ads.AdListener, com.google.android.gms.internal.ads.zzve
            public void onAdClicked() {
                super.onAdClicked();
                Bundle bundle = new Bundle();
                bundle.putString("Setting_Act_native_clicked", "Setting_Act_native_clicked");
                //FirebaseAnalytics.getInstance(Setting_Act.this).logEvent("Setting_Act_native_clicked", bundle);
            }
        }).build().loadAd(new AdRequest.Builder().addTestDevice(getResources().getString(R.string.vicky_s8)).addTestDevice(getResources().getString(R.string.rorjan_test_device)).addTestDevice(MainActivity.uxi_device).addTestDevice(MainActivity.test_mobile_white).addTestDevice("A86A0D556F68465C49063589837FCF98").build());
    }

    private void initilize_components() {
        this.shake_layout = (LinearLayout) findViewById(R.id.shake_layout);
        this.power_btn_four_times_layout = (LinearLayout) findViewById(R.id.power_btn_four_times_layout);
        this.vibrate_layout = (LinearLayout) findViewById(R.id.vibrate_layout);
        this.schedule_call_layout = (LinearLayout) findViewById(R.id.schedule_call_layout);
        this.dark_mode_layout = (LinearLayout) findViewById(R.id.dark_mode_layout);
        this.change_ringtone_layout_layout = (LinearLayout) findViewById(R.id.change_ringtone_layout_layout);
        this.top_actionbar_layout = (LinearLayout) findViewById(R.id.top_actionbar_layout);
        this.items_layout = (LinearLayout) findViewById(R.id.items_layout);
        this.main_layout = (RelativeLayout) findViewById(R.id.main_layout);
        this.options = (ImageView) findViewById(R.id.options);
        this.back_btn = (ImageView) findViewById(R.id.back_btn);
        this.topRoundrectview = (RoundRectView) findViewById(R.id.topRoundrectview);

        cancel_shedule_call = (ImageView) findViewById(R.id.cancel_shedule_call);
        schedule_call_txtview_remaining = (TextView) findViewById(R.id.schedule_call_txtview_remaining);
        this.shake_textview = (TextView) findViewById(R.id.shake_textview);
        this.power_textview = (TextView) findViewById(R.id.power_textview);
        this.vibrate_textview = (TextView) findViewById(R.id.vibrate_textview);
        this.dark_mode_txtview = (TextView) findViewById(R.id.dark_mode_txtview);
        this.change_ringtone_txtview = (TextView) findViewById(R.id.change_ringtone_txtview);
        this.schedule_call_txtview = (TextView) findViewById(R.id.schedule_call_txtview);
        this.vibrate_tick = (CheckBox) findViewById(R.id.vibrate_tick);
        this.power_tick = (CheckBox) findViewById(R.id.power_tick);
        this.shake_tick = (CheckBox) findViewById(R.id.shake_tick);
        this.dark_mode_checkbox = (CheckBox) findViewById(R.id.dark_mode_checkbox);
        this.shake_layout.setOnClickListener(this);
        this.power_btn_four_times_layout.setOnClickListener(this);
        this.vibrate_layout.setOnClickListener(this);
        this.dark_mode_layout.setOnClickListener(this);
        this.schedule_call_layout.setOnClickListener(this);
        this.change_ringtone_layout_layout.setOnClickListener(this);
        this.back_btn.setOnClickListener(this);
        cancel_shedule_call.setOnClickListener(this);
        this.vibrate_tick.setOnCheckedChangeListener(this);
        this.options.setOnClickListener(this);
        this.shake_tick.setOnCheckedChangeListener(this);
        this.power_tick.setOnCheckedChangeListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn /* 2131230813 */:
                onBackPressed();
                return;
            case R.id.cancel_shedule_call :
                stopService(new Intent(this, MyService.class));
                cancel_shedule_call.setVisibility(View.GONE);
                schedule_call_txtview_remaining.setText("");
                return;
            case R.id.change_ringtone_layout_layout /* 2131230864 */:
                if (this.mInterstitialAd.isLoaded()) {
                    this.mInterstitialAd.show();
                    Bundle bundle = new Bundle();
                    bundle.putString("Setting_Act_intrst_show_ringtone_layout", "Setting_Act_intrst_show_ringtone_layout");
                   // FirebaseAnalytics.getInstance(this).logEvent("Setting_Act_intrst_show_ringtone_layout", bundle);
                } else {
                    startActivity(new Intent(this, Change_Ringtone.class));
                    requestNewInterstitial();
                }
                this.mInterstitialAd.setAdListener(new AdListener() { // from class: com.softdroid.fake.call.Activities.Setting_Act.17
                    @Override // com.google.android.gms.ads.AdListener
                    public void onAdClosed() {
                        super.onAdClosed();
                        Setting_Act.this.startActivity(new Intent(Setting_Act.this, Change_Ringtone.class));
                        Setting_Act.this.requestNewInterstitial();
                        Bundle bundle2 = new Bundle();
                        bundle2.putString("Setting_Act_intrst_close_ringtone_layout", "Setting_Act_intrst_close_ringtone_layout");
                      //  FirebaseAnalytics.getInstance(Setting_Act.this).logEvent("Setting_Act_intrst_close_ringtone_layout", bundle2);
                    }

                    @Override // com.google.android.gms.ads.AdListener, com.google.android.gms.internal.ads.zzve
                    public void onAdClicked() {
                        super.onAdClicked();
                        Bundle bundle2 = new Bundle();
                        bundle2.putString("Setting_Act_intrst_click_ringtone_layout", "Setting_Act_intrst_click_ringtone_layout");
                       // FirebaseAnalytics.getInstance(Setting_Act.this).logEvent("Setting_Act_intrst_click_ringtone_layout", bundle2);
                    }
                });
                return;
            case R.id.dark_mode_layout /* 2131230903 */:
                CheckBox checkBox = this.dark_mode_checkbox;
                checkBox.setChecked(!checkBox.isChecked());
                return;
            case R.id.options /* 2131231049 */:
                pop_up_menu();
                return;
            case R.id.power_btn_four_times_layout /* 2131231071 */:
                CheckBox checkBox2 = this.power_tick;
                checkBox2.setChecked(!checkBox2.isChecked());
                return;
            case R.id.schedule_call_layout /* 2131231117 */:
                checkPermission();
                return;
            case R.id.shake_layout /* 2131231148 */:
                CheckBox checkBox3 = this.shake_tick;
                checkBox3.setChecked(!checkBox3.isChecked());
                return;
            case R.id.vibrate_layout /* 2131231250 */:
                CheckBox checkBox4 = this.vibrate_tick;
                checkBox4.setChecked(!checkBox4.isChecked());
                return;
            default:
                return;
        }
    }

    @SuppressLint("WrongConstant")
    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        switch (compoundButton.getId()) {
            case R.id.dark_mode_checkbox /* 2131230902 */:
                this.sharedPreferences.edit().putBoolean(getResources().getString(R.string.dark_mode_pref), z).apply();
                Intent intent = getIntent();
                intent.addFlags(65536);
                startActivity(intent);
                finish();
                return;
           /* case R.id.power_tick *//* 2131231073 *//*:
                if (z) {
                    startService(new Intent(this, Screen_off_service.class));
                } else {
                    //getString(R.string.dark_mode_pref), z);
                   // getString(R.string.dark_mode_pref), z).apply();
                Intent intent1 = getIntent();
                intent1.addFlags(65536);
                startActivity(intent1);
                finish();
                return;*/
            case R.id.power_tick /* 2131231073 */:
                if (z) {
                    startService(new Intent(this, Screen_off_service.class));
                } else {
                    stopService(new Intent(this, Screen_off_service.class));
                }
                this.sharedPreferences.edit().putBoolean(getResources().getString(R.string.triple_tap_pref), z).apply();
                return;
            case R.id.shake_tick /* 2131231150 */:
                if (z) {
                    startService(new Intent(this, Shake_Service.class));
                } else {
                    stopService(new Intent(this, Shake_Service.class));
                }
                this.sharedPreferences.edit().putBoolean(getResources().getString(R.string.shake_pref), z).apply();
                return;
            case R.id.vibrate_tick /* 2131231252 */:
                this.sharedPreferences.edit().putBoolean(getResources().getString(R.string.vibration_pref), z).apply();
                return;
            default:
                return;
        }
    }

    @Override
    public void onBackPressed() {
        if (this.mInterstitialAd.isLoaded()) {
            this.mInterstitialAd.show();
            Bundle bundle = new Bundle();
            bundle.putString("Setting_Act_intrst_show_back_prsd", "Setting_Act_intrst_show_back_prsd");
            //FirebaseAnalytics.getInstance(this).logEvent("Setting_Act_intrst_show_back_prsd", bundle);
        } else {
            finish();
        }
        this.mInterstitialAd.setAdListener(new AdListener() { // from class: com.softdroid.fake.call.Activities.Setting_Act.18
            @Override // com.google.android.gms.ads.AdListener
            public void onAdClosed() {
                super.onAdClosed();
                Bundle bundle2 = new Bundle();
                bundle2.putString("Setting_Act_intrst_closed_back_prsd", "Setting_Act_intrst_closed_back_prsd");
                //FirebaseAnalytics.getInstance(Setting_Act.this).logEvent("Setting_Act_intrst_closed_back_prsd", bundle2);
                Setting_Act.this.finish();
            }

            @Override // com.google.android.gms.ads.AdListener, com.google.android.gms.internal.ads.zzve
            public void onAdClicked() {
                super.onAdClicked();
                Bundle bundle2 = new Bundle();
                bundle2.putString("Setting_Act_intrst_click_back_prsd", "Setting_Act_intrst_click_back_prsd");
               // FirebaseAnalytics.getInstance(Setting_Act.this).logEvent("Setting_Act_intrst_click_back_prsd", bundle2);
            }
        });
    }
}
