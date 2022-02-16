package com.example.fake.call.Activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
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
import androidx.fragment.app.FragmentActivity;
import androidx.room.RoomDatabase;
import com.bumptech.glide.Glide;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
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
import de.hdodenhof.circleimageview.CircleImageView;
import io.github.florent37.shapeofview.shapes.RoundRectView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Stack;

/* loaded from: classes2.dex */
public class Caller extends AppCompatActivity implements View.OnClickListener {
    static final boolean $assertionsDisabled = false;
    public static final String ALLOW_KEY = "ALLOWED";
    public static final String AUDIO_PREF = "AUDIO_PREF";
    public static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 200;
    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 300;
    static final int PICK_CONTACT = 1;
    ImageView back_btn;
    ImageView btns_separator_imageview;
    EditText caller_name;
    EditText caller_number;
    TextView change_caller_txtview;
    LinearLayout choose_from_characters_layout;
    LinearLayout choose_from_characters_main_layout;
    TextView choose_from_characters_txtview;
    TextView choose_from_contacts_txtview;
    TextView contact_txtview;
    ImageView ivsave;
    FrameLayout frameLayout_1;
    CircleImageView image_of_caller;
    LinearLayout image_of_caller_layout;
    InterstitialAd mInterstitialAd;
    RelativeLayout main_layout;
    SharedPreferences mysharedPreference;
    TextView name_txtview;
    private UnifiedNativeAd nativeAd;
    ImageView options;
    LinearLayout pick_contact;
    LinearLayout pick_contact_main_layout;
    ShimmerFrameLayout shimmerFrameLayout;
    TextView tap_to_change_pic_txtview;
    ImageView title_line_imageview;
    LinearLayout top_actionbar_layout;
    RoundRectView topRoundrectview;
    Boolean from_contact = false;
    Boolean characters_selected = false;
    LinearLayout settings_layout;


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
        setContentView(R.layout.activity_caller);
        ShimmerFrameLayout shimmerFrameLayout = (ShimmerFrameLayout) findViewById(R.id.ad_loader);
        this.shimmerFrameLayout = shimmerFrameLayout;
        shimmerFrameLayout.startShimmer();
        initilize_ads();
        initilize_componenets();
        getting_prefrence();
    }

    private void initilize_ads() {
        InterstitialAd interstitialAd = new InterstitialAd(this);
        this.mInterstitialAd = interstitialAd;
        interstitialAd.setAdUnitId(getResources().getString(R.string.Interstitial_admob));
        requestNewInterstitial();
        loading_native_advance_ad();
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
        Boolean valueOf = Boolean.valueOf(this.mysharedPreference.getBoolean(getResources().getString(R.string.dark_mode_pref), false));
        ((TextView) unifiedNativeAdView.getHeadlineView()).setText(unifiedNativeAd.getHeadline());
        if (valueOf.booleanValue()) {
            ((TextView) unifiedNativeAdView.getHeadlineView()).setTextColor(getResources().getColor(R.color.text_color_dark));
        }
        if (unifiedNativeAd.getBody() == null) {
            unifiedNativeAdView.getBodyView().setVisibility(View.GONE);
        } else {
            unifiedNativeAdView.getBodyView().setVisibility(View.VISIBLE);
            ((TextView) unifiedNativeAdView.getBodyView()).setText(unifiedNativeAd.getBody());
            if (valueOf.booleanValue()) {
                ((TextView) unifiedNativeAdView.getBodyView()).setTextColor(getResources().getColor(R.color.text_color_dark));
            }
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
            if (valueOf.booleanValue()) {
                ((TextView) unifiedNativeAdView.getPriceView()).setTextColor(getResources().getColor(R.color.text_color_dark));
            }
        }
        if (unifiedNativeAd.getStore() == null) {
            unifiedNativeAdView.getStoreView().setVisibility(View.GONE);
        } else {
            unifiedNativeAdView.getStoreView().setVisibility(View.VISIBLE);
            ((TextView) unifiedNativeAdView.getStoreView()).setText(unifiedNativeAd.getStore());
            if (valueOf.booleanValue()) {
                ((TextView) unifiedNativeAdView.getStoreView()).setTextColor(getResources().getColor(R.color.text_color_dark));
            }
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
            videoController.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() { // from class: com.softdroid.fake.call.Activities.Caller.1
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
        builder.forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() { // from class: com.softdroid.fake.call.Activities.Caller.2
            @Override // com.google.android.gms.ads.formats.UnifiedNativeAd.OnUnifiedNativeAdLoadedListener
            public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                if (Caller.this.nativeAd != null) {
                    Caller.this.nativeAd.destroy();
                }
                Caller.this.nativeAd = unifiedNativeAd;
                FrameLayout frameLayout = (FrameLayout) Caller.this.findViewById(R.id.fl_adplaceholder_1);
                UnifiedNativeAdView unifiedNativeAdView = (UnifiedNativeAdView) Caller.this.getLayoutInflater().inflate(R.layout.ad_unified_caller, (ViewGroup) null);
                Caller.this.populateUnifiedNativeAdView(unifiedNativeAd, unifiedNativeAdView);
                frameLayout.removeAllViews();
                frameLayout.addView(unifiedNativeAdView);
            }
        });
        builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(true).build()).build());
        builder.withAdListener(new AdListener() { // from class: com.softdroid.fake.call.Activities.Caller.3
            @Override // com.google.android.gms.ads.AdListener
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                Bundle bundle = new Bundle();
                bundle.putString("Caller_Act_Native_Ad_failed", "Caller_Act_Native_Ad_failed");
                //FirebaseAnalytics.getInstance(Caller.this).logEvent("Caller_Act_Native_Ad_failed", bundle);
                Caller.this.shimmerFrameLayout.setVisibility(View.GONE);
                Caller.this.shimmerFrameLayout.stopShimmer();
            }

            @Override // com.google.android.gms.ads.AdListener
            public void onAdLoaded() {
                super.onAdLoaded();
                Caller.this.frameLayout_1.setVisibility(View.VISIBLE);
                Caller.this.shimmerFrameLayout.setVisibility(View.GONE);
                Caller.this.shimmerFrameLayout.stopShimmer();
                Bundle bundle = new Bundle();
                bundle.putString("Caller_Act_Native_Ad_show", "Caller_Act_Native_Ad_show");
              //  FirebaseAnalytics.getInstance(Caller.this).logEvent("Caller_Act_Native_Ad_show", bundle);
            }

            @Override // com.google.android.gms.ads.AdListener, com.google.android.gms.internal.ads.zzve
            public void onAdClicked() {
                super.onAdClicked();
                Bundle bundle = new Bundle();
                bundle.putString("Caller_Act_Native_Ad_clicked", "Caller_Act_Native_Ad_clicked");
               // FirebaseAnalytics.getInstance(Caller.this).logEvent("Caller_Act_Native_Ad_clicked", bundle);
            }
        }).build().loadAd(new AdRequest.Builder().addTestDevice(getResources().getString(R.string.vicky_s8)).addTestDevice(getResources().getString(R.string.rorjan_test_device)).addTestDevice(MainActivity.uxi_device).addTestDevice(MainActivity.test_mobile_white).addTestDevice("A86A0D556F68465C49063589837FCF98").build());
    }

    private void requestNewInterstitial() {
        this.mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice(getResources().getString(R.string.vicky_s8)).addTestDevice(getResources().getString(R.string.rorjan_test_device)).addTestDevice(MainActivity.uxi_device).addTestDevice(MainActivity.test_mobile_white).addTestDevice("A86A0D556F68465C49063589837FCF98").build());
    }

    private void getting_prefrence() {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        this.mysharedPreference = defaultSharedPreferences;
        this.caller_name.setText(defaultSharedPreferences.getString("name_of_contact", "Girl Friend"));
        this.caller_number.setText(this.mysharedPreference.getString("number_of_contact", "(202) 555-0128"));
        if (this.mysharedPreference.getBoolean("gallery_image", false)) {
            Glide.with((FragmentActivity) this).load(this.mysharedPreference.getString("imagepath", "")).into(this.image_of_caller);
        } else if (this.mysharedPreference.getBoolean("custom_image", false)) {
            this.image_of_caller.setImageURI(Uri.parse(this.mysharedPreference.getString("custom_image_path", "")));
        } else {
            switch (this.mysharedPreference.getInt("position", 2)) {
                case 1:
                    this.image_of_caller.setImageResource(R.mipmap.pizza);
                    return;
                case 2:
                    this.image_of_caller.setImageResource(R.mipmap.girlfriend);
                    return;
                case 3:
                    this.image_of_caller.setImageResource(R.mipmap.mom);
                    return;
                case 4:
                    this.image_of_caller.setImageResource(R.mipmap.boyfriend);
                    return;
                case 5:
                    this.image_of_caller.setImageResource(R.mipmap.dad);
                    return;
                case 6:
                    this.image_of_caller.setImageResource(R.mipmap.husband);
                    return;
                case 7:
                    this.image_of_caller.setImageResource(R.mipmap.wife);
                    return;
                case 8:
                    this.image_of_caller.setImageResource(R.mipmap.boss);
                    return;
                case 9:
                    this.image_of_caller.setImageResource(R.mipmap.doctor);
                    return;
                case 10:
                    this.image_of_caller.setImageResource(R.mipmap.lawyer);
                    return;
                case 11:
                    this.image_of_caller.setImageResource(R.mipmap.president);
                    return;
                case 12:
                    this.image_of_caller.setImageResource(R.mipmap.ronaldo);
                    return;
                case 13:
                    this.image_of_caller.setImageResource(R.mipmap.santa);
                    return;
                case 14:
                    this.image_of_caller.setImageResource(R.mipmap.tax_insurance);
                    return;
                default:
                    return;
            }
        }
    }

    private void initilize_componenets() {
        this.caller_name = (EditText) findViewById(R.id.caller_name);
        this.caller_number = (EditText) findViewById(R.id.caller_number);
        this.image_of_caller_layout = (LinearLayout) findViewById(R.id.image_of_caller_layout);
        this.choose_from_characters_layout = (LinearLayout) findViewById(R.id.choose_from_characters_layout);
        this.pick_contact = (LinearLayout) findViewById(R.id.pick_contact);
        this.top_actionbar_layout = (LinearLayout) findViewById(R.id.top_actionbar_layout);
        this.topRoundrectview = (RoundRectView) findViewById(R.id.topRoundrectview);
        this.pick_contact_main_layout = (LinearLayout) findViewById(R.id.pick_contact_main_layout);
        this.choose_from_characters_main_layout = (LinearLayout) findViewById(R.id.choose_from_characters_main_layout);
        this.ivsave = (ImageView) findViewById(R.id.ivsave);
        this.tap_to_change_pic_txtview = (TextView) findViewById(R.id.tap_to_change_pic_txtview);
        this.name_txtview = (TextView) findViewById(R.id.name_txtview);
        this.contact_txtview = (TextView) findViewById(R.id.contact_txtview);
        this.change_caller_txtview = (TextView) findViewById(R.id.change_caller_txtview);
        this.choose_from_contacts_txtview = (TextView) findViewById(R.id.choose_from_contacts_txtview);
        this.choose_from_characters_txtview = (TextView) findViewById(R.id.choose_from_characters_txtview);
        this.main_layout = (RelativeLayout) findViewById(R.id.main_layout);
        this.settings_layout = (LinearLayout) findViewById(R.id.settings_layout);
        this.back_btn = (ImageView) findViewById(R.id.back_btn);
        this.options = (ImageView) findViewById(R.id.options);
        this.title_line_imageview = (ImageView) findViewById(R.id.title_line_imageview);
        this.btns_separator_imageview = (ImageView) findViewById(R.id.btns_separator_imageview);
        this.image_of_caller = (CircleImageView) findViewById(R.id.image_of_caller);
        this.ivsave.setOnClickListener(this);
        this.image_of_caller_layout.setOnClickListener(this);
        this.pick_contact.setOnClickListener(this);
        this.options.setOnClickListener(this);
        this.back_btn.setOnClickListener(this);
        this.choose_from_characters_layout.setOnClickListener(this);
        this.settings_layout.setOnClickListener(this);
    }


    public void mypermissions() {
        if (ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            marshmallow_permissions_WRITE_EXTERNAL_STORAGE();
        } else {
            pick_image_from_gallery();
        }
    }


    public void contact_permissions() {
        if (ContextCompat.checkSelfPermission(this, "android.permission.READ_CONTACTS") != 0) {
            marshmallow_permissions_READ_CONTACTS();
        } else if (ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            marshmallow_permissions_WRITE_EXTERNAL_STORAGE();
        } else {
            try {
                startActivityForResult(new Intent("android.intent.action.PICK", ContactsContract.Contacts.CONTENT_URI), 1);
            } catch ( Exception unused) {
            }
        }
    }

    private void marshmallow_permissions_WRITE_EXTERNAL_STORAGE() {
        if (ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            return;
        }
        if (getFromPref(this, "ALLOWED").booleanValue()) {
            showSettingsAlert(getResources().getString(R.string.photo_permission_dialoug_message), false);
        } else if (ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.WRITE_EXTERNAL_STORAGE")) {
                ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 300);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 300);
            }
        }
    }

    private void marshmallow_permissions_READ_CONTACTS() {
        if (ContextCompat.checkSelfPermission(this, "android.permission.READ_CONTACTS") == 0) {
            return;
        }
        if (getFromPref(this, "ALLOWED").booleanValue()) {
            showSettingsAlert(getResources().getString(R.string.contact_permission_dialoug_message), true);
        } else if (ContextCompat.checkSelfPermission(this, "android.permission.READ_CONTACTS") == 0) {
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.READ_CONTACTS")) {
                ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_CONTACTS"}, 200);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_CONTACTS"}, 200);
            }
        }
    }

    public static Boolean getFromPref(Context context, String str) {
        return Boolean.valueOf(context.getSharedPreferences("AUDIO_PREF", 0).getBoolean(str, false));
    }

    private void showSettingsAlert(String str, final Boolean bool) {
        AlertDialog create = new AlertDialog.Builder(this).create();
        create.setTitle("Alert");
        create.setCancelable(false);
        create.setMessage(str);
        create.setButton(-2, "DONT ALLOW", new DialogInterface.OnClickListener() { // from class: com.softdroid.fake.call.Activities.Caller.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        create.setButton(-1, "ALLOW", new DialogInterface.OnClickListener() { // from class: com.softdroid.fake.call.Activities.Caller.5
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                if (bool.booleanValue()) {
                    Caller.this.contact_permissions();
                } else {
                    Caller.this.mypermissions();
                }
            }
        });
        create.show();
    }

    @Override
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i != 200) {
            if (i == 300) {
                if (iArr.length <= 0 || iArr[0] != 0) {
                    showSettingsAlert(getResources().getString(R.string.photo_permission_dialoug_message), false);
                } else if (this.from_contact.booleanValue()) {
                    contact_permissions();
                } else {
                    mypermissions();
                }
            }
        } else if (iArr.length <= 0 || iArr[0] != 0) {
            showSettingsAlert(getResources().getString(R.string.contact_permission_dialoug_message), true);
        } else {
            contact_permissions();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn /* 2131230813 */:
                onBackPressed();
                return;
            case R.id.choose_from_characters_layout /* 2131230867 */:
                this.characters_selected = true;
                startActivity(new Intent(this, Character.class));
                return;
            case R.id.ivsave /* 2131230922 */:
                String obj = this.caller_name.getText().toString();
                String obj2 = this.caller_number.getText().toString();
                if (obj2.equalsIgnoreCase("")) {
                    Toast.makeText(this, getResources().getString(R.string.please_fill_contact_name_n_number),  Toast.LENGTH_LONG).show();
                    return;
                }
                this.mysharedPreference.edit().putString("name_of_contact", obj).commit();
                this.mysharedPreference.edit().putString("number_of_contact", obj2).commit();
                Toast.makeText(this, getResources().getString(R.string.contact_info_saved),  Toast.LENGTH_LONG).show();
                finish();
                return;
            case R.id.image_of_caller_layout /* 2131230978 */:
                if (Build.VERSION.SDK_INT > 21) {
                    this.from_contact = false;
                    mypermissions();
                    return;
                }
                pick_image_from_gallery();
                return;
            case R.id.options /* 2131231049 */:
                pop_up_menu();
                return;
            case R.id.settings_layout /* 2131231147 */:
                startActivity(new Intent(this, Setting_Act.class));
                return;
            case R.id.pick_contact /* 2131231067 */:
                if (Build.VERSION.SDK_INT > 21) {
                    this.from_contact = true;
                    contact_permissions();
                    return;
                }
                try {
                    startActivityForResult(new Intent("android.intent.action.PICK", ContactsContract.Contacts.CONTENT_URI), 1);
                    return;
                } catch ( Exception unused) {
                    return;
                }
            default:
                return;
        }
    }

    public void pop_up_menu() {
        PopupMenu popupMenu = new PopupMenu(this, this.options);
        popupMenu.getMenuInflater().inflate(R.menu.menu_setting, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() { // from class: com.softdroid.fake.call.Activities.Caller.6
            @Override // android.widget.PopupMenu.OnMenuItemClickListener
            public boolean onMenuItemClick(MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.like_btn) {
                    Caller.this.show_rating_dialoug();
                    return true;
                } else if (itemId == R.id.more_free_apps) {
                    try {
                        Caller.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://search?q=pub:" + Caller.this.getResources().getString(R.string.account_name))));
                    } catch (ActivityNotFoundException unused) {
                        Caller.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/developer?id=" + Caller.this.getResources().getString(R.string.account_name))));
                    }
                    return true;
                } else if (itemId != R.id.privacy) {
                    return true;
                } else {
                    Caller.this.privacy_policy();
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
        create.setButton(-1, "Close", new DialogInterface.OnClickListener() { // from class: com.softdroid.fake.call.Activities.Caller.7
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
        ((Button) dialog.findViewById(R.id.submit)).setOnClickListener(new View.OnClickListener() { // from class: com.softdroid.fake.call.Activities.Caller.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ratingBar.getRating() <= 3.0f) {
                    Caller.this.sendFeedback();
                    dialog.dismiss();
                    return;
                }
                String packageName = Caller.this.getPackageName();
                try {
                    Caller.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + packageName)));
                } catch (ActivityNotFoundException unused2) {
                    Caller.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + packageName)));
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
        if (this.mysharedPreference.getBoolean(getResources().getString(R.string.dark_mode_pref), false)) {
            create.setView(layoutInflater.inflate(R.layout.privacy_policy_dark, (ViewGroup) null));
        } else {
            create.setView(layoutInflater.inflate(R.layout.privacy_policy, (ViewGroup) null));
        }
        create.setButton(-1, "Close", new DialogInterface.OnClickListener() { // from class: com.softdroid.fake.call.Activities.Caller.9
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        create.show();
    }

    private void pick_image_from_gallery() {
        try {
            startActivityForResult(new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI), 101);
        } catch ( Exception unused) {
        }
    }

    @SuppressLint("Range")
    @Override
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 101 && i2 == -1 && intent != null) {
            String[] strArr = {"_data"};
            try {
                Cursor query = getContentResolver().query(intent.getData(), strArr, null, null, null);
                query.moveToFirst();
                @SuppressLint("Range") String string = query.getString(query.getColumnIndex(strArr[0]));
                this.mysharedPreference.edit().putString("imagepath", string).commit();
                this.mysharedPreference.edit().putBoolean("gallery_image", true).commit();
                query.close();
                Glide.with((FragmentActivity) this).load(string).into(this.image_of_caller);
            } catch ( Exception unused) {
            }
        }
        if (i == 1 && i2 == -1) {
            try {
                Cursor managedQuery = managedQuery(intent.getData(), null, null, null, null);
                if (managedQuery.moveToFirst()) {
                    String string2 = managedQuery.getString(managedQuery.getColumnIndexOrThrow("_id"));
                    if (managedQuery.getString(managedQuery.getColumnIndex("has_phone_number")).equalsIgnoreCase("1")) {
                        Cursor query2 = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, "contact_id = " + string2, null, null);
                        query2.moveToFirst();
                        String string3 = query2.getString(query2.getColumnIndex("data1"));
                        System.out.println("number is:" + string3);
                        this.caller_number.setText(string3);
                        this.mysharedPreference.edit().putString("number_of_contact", string3).commit();
                    }
                    String string4 = managedQuery.getString(managedQuery.getColumnIndex("display_name"));
                    this.caller_name.setText(string4);
                    this.mysharedPreference.edit().putString("name_of_contact", string4).commit();
                    Bitmap bitmap = null;
                    InputStream openContactPhotoInputStream = ContactsContract.Contacts.openContactPhotoInputStream(getContentResolver(), ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, new Long(string2).longValue()));
                    if (openContactPhotoInputStream != null) {
                        bitmap = BitmapFactory.decodeStream(openContactPhotoInputStream);
                    }
                    try {
                        openContactPhotoInputStream.close();
                    } catch (AssertionError | Exception unused2) {
                    }
                    if (bitmap == null) {
                        this.image_of_caller.setImageResource(R.mipmap.dumyimg);
                        storeImage(BitmapFactory.decodeResource(getResources(), R.mipmap.dumyimg));
                    } else {
                        this.image_of_caller.setImageBitmap(bitmap);
                        storeImage(bitmap);
                    }
                    this.mysharedPreference.edit().putBoolean("custom_image", true).commit();
                    this.mysharedPreference.edit().putBoolean("gallery_image", false).commit();
                }
            } catch (Exception unused3) {
            }
        }
    }

    private void storeImage(Bitmap bitmap) {
        File outputMediaFile = getOutputMediaFile();
        if (outputMediaFile != null) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(outputMediaFile);
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, fileOutputStream);
                fileOutputStream.close();
            } catch ( IOException unused) {
            }
        }
    }

    private File getOutputMediaFile() {
        File file = new File(Environment.getExternalStorageDirectory() + "/Android/data/" + getApplicationContext().getPackageName() + "/Files");
        if (!file.exists() && !file.mkdirs()) {
            return null;
        }
        @SuppressLint("RestrictedApi") String str = "Fake_Call_" + new SimpleDateFormat("ddMMyyyy_HHmm").format(new Date()) + "_" + (new Random().nextInt(RoomDatabase.MAX_BIND_PARAMETER_CNT) + 0) + ".jpg";
        File file2 = new File(file.getPath() + File.separator + str);
        this.mysharedPreference.edit().putString("custom_image_path", file.getPath() + File.separator + str).commit();
        return file2;
    }

    @Override
    public void onBackPressed() {
        if (this.mInterstitialAd.isLoaded()) {
            this.mInterstitialAd.show();
            Bundle bundle = new Bundle();
            bundle.putString("Caller_Act_Intrstal_Ad_show_back_prsd", "Caller_Act_Intrstal_Ad_show_back_prsd");
           // FirebaseAnalytics.getInstance(this).logEvent("Caller_Act_Intrstal_Ad_show_back_prsd", bundle);
        } else {
            finish();
        }
        this.mInterstitialAd.setAdListener(new AdListener() { // from class: com.softdroid.fake.call.Activities.Caller.10
            @Override // com.google.android.gms.ads.AdListener
            public void onAdClosed() {
                super.onAdClosed();
                Bundle bundle2 = new Bundle();
                bundle2.putString("Caller_Act_Intrstal_Ad_closed_back_prsd", "Caller_Act_Intrstal_Ad_closed_back_prsd");
               // FirebaseAnalytics.getInstance(Caller.this).logEvent("Caller_Act_Intrstal_Ad_closed_back_prsd", bundle2);
                Caller.this.finish();
            }

            @Override // com.google.android.gms.ads.AdListener, com.google.android.gms.internal.ads.zzve
            public void onAdClicked() {
                super.onAdClicked();
                Bundle bundle2 = new Bundle();
                bundle2.putString("Caller_Act_Intrstal_Ad_click_back_prsd", "Caller_Act_Intrstal_Ad_click_back_prsd");
               // FirebaseAnalytics.getInstance(Caller.this).logEvent("Caller_Act_Intrstal_Ad_closed_back_prsd", bundle2);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (this.characters_selected.booleanValue()) {
            this.characters_selected = false;
            getting_prefrence();
        }
        if (this.mysharedPreference.getBoolean(getResources().getString(R.string.dark_mode_pref), false)) {
            this.top_actionbar_layout.setBackgroundColor(getResources().getColor(R.color.top_bar_dark));
            this.topRoundrectview.setBackgroundColor(getResources().getColor(R.color.top_bar_dark));
            this.main_layout.setBackgroundColor(getResources().getColor(R.color.background_dark));
            this.tap_to_change_pic_txtview.setTextColor(getResources().getColor(R.color.text_color_dark));
            this.name_txtview.setTextColor(getResources().getColor(R.color.text_color_dark));
            this.contact_txtview.setTextColor(getResources().getColor(R.color.text_color_dark));
            this.change_caller_txtview.setTextColor(getResources().getColor(R.color.text_color_dark));
            this.choose_from_contacts_txtview.setTextColor(getResources().getColor(R.color.text_color_dark));
            this.choose_from_characters_txtview.setTextColor(getResources().getColor(R.color.text_color_dark));
            this.caller_name.setTextColor(getResources().getColor(R.color.text_color_dark));
            this.caller_number.setTextColor(getResources().getColor(R.color.text_color_dark));
            this.caller_name.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.edit_text_dark_mode, 0);
            this.caller_number.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.edit_text_dark_mode, 0);
            this.title_line_imageview.setColorFilter(getResources().getColor(R.color.text_color_dark));
            this.btns_separator_imageview.setColorFilter(getResources().getColor(R.color.text_color_dark));
            this.pick_contact_main_layout.setBackgroundResource(R.mipmap.main_activity_btn_dark);
            this.choose_from_characters_main_layout.setBackgroundResource(R.mipmap.main_activity_btn_dark);
            return;
        }
        this.top_actionbar_layout.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        this.topRoundrectview.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        this.main_layout.setBackgroundResource(R.drawable.background);
        this.tap_to_change_pic_txtview.setTextColor(getResources().getColor(R.color.main_textview_color));
        this.name_txtview.setTextColor(getResources().getColor(R.color.main_textview_color));
        this.contact_txtview.setTextColor(getResources().getColor(R.color.main_textview_color));
        this.change_caller_txtview.setTextColor(getResources().getColor(R.color.main_textview_color));
        this.choose_from_contacts_txtview.setTextColor(getResources().getColor(R.color.main_textview_color));
        this.choose_from_characters_txtview.setTextColor(getResources().getColor(R.color.main_textview_color));
        this.caller_name.setTextColor(getResources().getColor(R.color.main_textview_color));
        this.caller_number.setTextColor(getResources().getColor(R.color.main_textview_color));
        this.caller_name.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.edit_text, 0);
        this.caller_number.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.edit_text, 0);
        this.title_line_imageview.setColorFilter(getResources().getColor(R.color.main_textview_color));
        this.btns_separator_imageview.setColorFilter(getResources().getColor(R.color.main_textview_color));

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
}
