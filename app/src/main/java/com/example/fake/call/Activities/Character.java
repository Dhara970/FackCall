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
import com.example.fake.call.MainActivity;
import com.example.fake.call.R;
import java.util.Stack;

/* loaded from: classes2.dex */
public class Character extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout adContainerView;
    private AdView adaptive_adView;
    ImageView back_btn;
    RelativeLayout boss;
    RelativeLayout boyfriend;
    RelativeLayout dad;
    RelativeLayout doctor;
    FrameLayout frameLayout_2;
    RelativeLayout girlfriend;
    RelativeLayout husband;
    RelativeLayout lawyer;
    InterstitialAd mInterstitialAd;
    RelativeLayout main_layout;
    RelativeLayout mom;
    SharedPreferences mysharedPreference;
    private UnifiedNativeAd nativeAd2;
    ImageView options;
    RelativeLayout pizza;
    RelativeLayout president;
    RelativeLayout ronaldo;
    RelativeLayout santa;
    ShimmerFrameLayout shimmerFrameLayout;
    ShimmerFrameLayout shimmerFrameLayout1;
    RelativeLayout tax_insurance;
    ImageView tick_eight;
    ImageView tick_eleven;
    ImageView tick_fifteen;
    ImageView tick_five;
    ImageView tick_four;
    ImageView tick_fourteen;
    ImageView tick_nine;
    ImageView tick_seven;
    ImageView tick_six;
    ImageView tick_ten;
    ImageView tick_thirteen;
    ImageView tick_three;
    ImageView tick_twelve;
    ImageView tick_two;
    LinearLayout top_actionbar_layout;
    RelativeLayout wife;

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
        setContentView(R.layout.activity_character);
        ShimmerFrameLayout shimmerFrameLayout = (ShimmerFrameLayout) findViewById(R.id.ad_loader1);
        this.shimmerFrameLayout1 = shimmerFrameLayout;
        shimmerFrameLayout.startShimmer();
        ShimmerFrameLayout shimmerFrameLayout2 = (ShimmerFrameLayout) findViewById(R.id.ad_loader);
        this.shimmerFrameLayout = shimmerFrameLayout2;
        shimmerFrameLayout2.startShimmer();
        initilize_ads();
        initilize_componenets();
        getting_prefrence();
    }

    private void initilize_componenets() {
        this.pizza = (RelativeLayout) findViewById(R.id.pizza);
        this.girlfriend = (RelativeLayout) findViewById(R.id.girlfriend);
        this.mom = (RelativeLayout) findViewById(R.id.mom);
        this.boyfriend = (RelativeLayout) findViewById(R.id.boyfriend);
        this.husband = (RelativeLayout) findViewById(R.id.husband);
        this.wife = (RelativeLayout) findViewById(R.id.wife);
        this.dad = (RelativeLayout) findViewById(R.id.dad);
        this.boss = (RelativeLayout) findViewById(R.id.boss);
        this.doctor = (RelativeLayout) findViewById(R.id.doctor);
        this.lawyer = (RelativeLayout) findViewById(R.id.lawyer);
        this.president = (RelativeLayout) findViewById(R.id.president);
        this.ronaldo = (RelativeLayout) findViewById(R.id.ronaldo);
        this.tax_insurance = (RelativeLayout) findViewById(R.id.tax_insurance);
        this.main_layout = (RelativeLayout) findViewById(R.id.main_layout);
        this.santa = (RelativeLayout) findViewById(R.id.santa);
        this.top_actionbar_layout = (LinearLayout) findViewById(R.id.top_actionbar_layout);
        this.tick_two = (ImageView) findViewById(R.id.tick_two);
        this.tick_three = (ImageView) findViewById(R.id.tick_three);
        this.tick_four = (ImageView) findViewById(R.id.tick_four);
        this.tick_five = (ImageView) findViewById(R.id.tick_five);
        this.tick_six = (ImageView) findViewById(R.id.tick_six);
        this.tick_seven = (ImageView) findViewById(R.id.tick_seven);
        this.tick_eight = (ImageView) findViewById(R.id.tick_eight);
        this.tick_nine = (ImageView) findViewById(R.id.tick_nine);
        this.tick_ten = (ImageView) findViewById(R.id.tick_ten);
        this.tick_eleven = (ImageView) findViewById(R.id.tick_eleven);
        this.tick_twelve = (ImageView) findViewById(R.id.tick_twelve);
        this.tick_thirteen = (ImageView) findViewById(R.id.tick_thirteen);
        this.tick_fourteen = (ImageView) findViewById(R.id.tick_fourteen);
        this.tick_fifteen = (ImageView) findViewById(R.id.tick_fifteen);
        this.back_btn = (ImageView) findViewById(R.id.back_btn);
        ImageView imageView = (ImageView) findViewById(R.id.options);
        this.options = imageView;
        imageView.setOnClickListener(this);
        this.pizza.setOnClickListener(this);
        this.girlfriend.setOnClickListener(this);
        this.mom.setOnClickListener(this);
        this.boyfriend.setOnClickListener(this);
        this.husband.setOnClickListener(this);
        this.wife.setOnClickListener(this);
        this.boss.setOnClickListener(this);
        this.dad.setOnClickListener(this);
        this.doctor.setOnClickListener(this);
        this.lawyer.setOnClickListener(this);
        this.president.setOnClickListener(this);
        this.ronaldo.setOnClickListener(this);
        this.santa.setOnClickListener(this);
        this.tax_insurance.setOnClickListener(this);
        this.back_btn.setOnClickListener(this);
    }

    private void getting_prefrence() {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        this.mysharedPreference = defaultSharedPreferences;
        switch (defaultSharedPreferences.getInt("position", 2)) {
            case 0:
                set_checked(0, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8);
                return;
            case 1:
                set_checked(8, 0, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8);
                return;
            case 2:
                set_checked(8, 8, 0, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8);
                return;
            case 3:
                set_checked(8, 8, 8, 0, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8);
                return;
            case 4:
                set_checked(8, 8, 8, 8, 0, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8);
                return;
            case 5:
                set_checked(8, 8, 8, 8, 8, 0, 8, 8, 8, 8, 8, 8, 8, 8, 8);
                return;
            case 6:
                set_checked(8, 8, 8, 8, 8, 8, 0, 8, 8, 8, 8, 8, 8, 8, 8);
                return;
            case 7:
                set_checked(8, 8, 8, 8, 8, 8, 8, 0, 8, 8, 8, 8, 8, 8, 8);
                return;
            case 8:
                set_checked(8, 8, 8, 8, 8, 8, 8, 8, 0, 8, 8, 8, 8, 8, 8);
                return;
            case 9:
                set_checked(8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 8, 8, 8, 8, 8);
                return;
            case 10:
                set_checked(8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 8, 8, 8, 8);
                return;
            case 11:
                set_checked(8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 8, 8, 8);
                return;
            case 12:
                set_checked(8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 8, 8);
                return;
            case 13:
                set_checked(8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 8);
                return;
            case 14:
                set_checked(8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0);
                return;
            default:
                return;
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn /* 2131230813 */:
                onBackPressed();
                return;
            case R.id.boss /* 2131230825 */:
                save_prefrence(8, "Boss", "(652) 468-5413");
                set_checked(8, 8, 8, 8, 8, 8, 8, 8, 0, 8, 8, 8, 8, 8, 8);
                return;
            case R.id.boyfriend /* 2131230828 */:
                save_prefrence(4, "Boy Friend", "(303) 123-4567");
                set_checked(8, 8, 8, 8, 0, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8);
                return;
            case R.id.dad /* 2131230900 */:
                save_prefrence(5, "Dad", "(304) 234-5568");
                set_checked(8, 8, 8, 8, 8, 0, 8, 8, 8, 8, 8, 8, 8, 8, 8);
                return;
            case R.id.doctor /* 2131230921 */:
                save_prefrence(9, "Doctor", "(346) 378-3456");
                set_checked(8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 8, 8, 8, 8, 8);
                return;
            case R.id.girlfriend /* 2131230956 */:
                save_prefrence(2, "Girl Friend", "(202) 555-0128");
                set_checked(8, 8, 0, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8);
                return;
            case R.id.husband /* 2131230971 */:
                save_prefrence(6, "Husband", "(360) 325-2165");
                set_checked(8, 8, 8, 8, 8, 8, 0, 8, 8, 8, 8, 8, 8, 8, 8);
                return;
            case R.id.lawyer /* 2131230991 */:
                save_prefrence(10, "Lawyer", "(124) 647-3467");
                set_checked(8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 8, 8, 8, 8);
                return;
            case R.id.mom /* 2131231014 */:
                save_prefrence(3, "MoM", "(009) 010-0000");
                set_checked(8, 8, 8, 0, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8);
                return;
            case R.id.options /* 2131231049 */:
                pop_up_menu();
                return;
            case R.id.pizza /* 2131231070 */:
                save_prefrence(1, "Pizza", "(800) 100-7007");
                set_checked(8, 0, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8);
                return;
            case R.id.president /* 2131231074 */:
                save_prefrence(11, "President", "(999) 111-3331");
                set_checked(8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 8, 8, 8);
                return;
            case R.id.ronaldo /* 2131231108 */:
                save_prefrence(12, "Ronaldo", "(895) 256-4581");
                set_checked(8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 8, 8);
                return;
            case R.id.santa /* 2131231110 */:
                save_prefrence(13, "Santa", "(956) 254-4785");
                set_checked(8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 8);
                return;
            case R.id.tax_insurance /* 2131231191 */:
                save_prefrence(14, "Tax Insurance", "(452) 126-987");
                set_checked(8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0);
                return;
            case R.id.wife /* 2131231258 */:
                save_prefrence(7, "Wife", "(555) 554-5555");
                set_checked(8, 8, 8, 8, 8, 8, 8, 0, 8, 8, 8, 8, 8, 8, 8);
                return;
            default:
                return;
        }
    }

    private void set_checked(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15) {
        this.tick_two.setVisibility(i2);
        this.tick_three.setVisibility(i3);
        this.tick_four.setVisibility(i4);
        this.tick_five.setVisibility(i5);
        this.tick_six.setVisibility(i6);
        this.tick_seven.setVisibility(i7);
        this.tick_eight.setVisibility(i8);
        this.tick_nine.setVisibility(i9);
        this.tick_ten.setVisibility(i10);
        this.tick_eleven.setVisibility(i11);
        this.tick_twelve.setVisibility(i12);
        this.tick_thirteen.setVisibility(i13);
        this.tick_fourteen.setVisibility(i14);
        this.tick_fifteen.setVisibility(i15);
    }

    public void pop_up_menu() {
        PopupMenu popupMenu = new PopupMenu(this, this.options);
        popupMenu.getMenuInflater().inflate(R.menu.menu_setting, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() { // from class: com.softdroid.fake.call.Activities.Character.1
            @Override // android.widget.PopupMenu.OnMenuItemClickListener
            public boolean onMenuItemClick(MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.like_btn) {
                    Character.this.show_rating_dialoug();
                    return true;
                } else if (itemId == R.id.more_free_apps) {
                    try {
                        Character.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://search?q=pub:" + Character.this.getResources().getString(R.string.account_name))));
                    } catch (ActivityNotFoundException unused) {
                        Character.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/developer?id=" + Character.this.getResources().getString(R.string.account_name))));
                    }
                    return true;
                } else if (itemId != R.id.privacy) {
                    return true;
                } else {
                    Character.this.privacy_policy();
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
        if (this.mysharedPreference.getBoolean(getResources().getString(R.string.dark_mode_pref), false)) {
            create.setView(layoutInflater.inflate(R.layout.privacy_policy_dark, (ViewGroup) null));
        } else {
            create.setView(layoutInflater.inflate(R.layout.privacy_policy, (ViewGroup) null));
        }
        create.setButton(-1, "Close", new DialogInterface.OnClickListener() { // from class: com.softdroid.fake.call.Activities.Character.2
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
        ((Button) dialog.findViewById(R.id.submit)).setOnClickListener(new View.OnClickListener() { // from class: com.softdroid.fake.call.Activities.Character.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ratingBar.getRating() <= 3.0f) {
                    Character.this.sendFeedback();
                    dialog.dismiss();
                    return;
                }
                String packageName = Character.this.getPackageName();
                try {
                    Character.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + packageName)));
                } catch (ActivityNotFoundException unused2) {
                    Character.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + packageName)));
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
        create.setButton(-1, "Close", new DialogInterface.OnClickListener() { // from class: com.softdroid.fake.call.Activities.Character.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        create.show();
    }

    private void save_prefrence(int i, String str, String str2) {
        this.mysharedPreference.edit().putBoolean("gallery_image", false).apply();
        this.mysharedPreference.edit().putBoolean("custom_image", false).apply();
        this.mysharedPreference.edit().putInt("position", i).apply();
        this.mysharedPreference.edit().putString("name_of_contact", str).apply();
        this.mysharedPreference.edit().putString("number_of_contact", str2).apply();
        Toast.makeText(this, "Character Selected", Toast.LENGTH_SHORT).show();
    }

    private void load_adaptive_Banner() {
        MobileAds.initialize(this, new OnInitializationCompleteListener() { // from class: com.softdroid.fake.call.Activities.Character.5
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
        this.adaptive_adView.setAdListener(new AdListener() { // from class: com.softdroid.fake.call.Activities.Character.6
            @Override // com.google.android.gms.ads.AdListener
            public void onAdLoaded() {
                super.onAdLoaded();
                Bundle bundle = new Bundle();
                bundle.putString("Character_Act_banner_show", "Character_Act_banner_show");
                //FirebaseAnalytics.getInstance(Character.this).logEvent("Character_Act_banner_show", bundle);
                Character.this.shimmerFrameLayout.setVisibility(View.GONE);
                Character.this.shimmerFrameLayout.stopShimmer();
            }

            @Override // com.google.android.gms.ads.AdListener
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                Bundle bundle = new Bundle();
                bundle.putString("Character_Act_banner_failed", "Character_Act_banner_failed");
                //FirebaseAnalytics.getInstance(Character.this).logEvent("Character_Act_banner_failed", bundle);
                Character.this.shimmerFrameLayout.setVisibility(View.GONE);
                Character.this.shimmerFrameLayout.stopShimmer();
            }

            @Override // com.google.android.gms.ads.AdListener, com.google.android.gms.internal.ads.zzve
            public void onAdClicked() {
                super.onAdClicked();
                Bundle bundle = new Bundle();
                bundle.putString("Character_Act_banner_clicked", "Character_Act_banner_clicked");
                //FirebaseAnalytics.getInstance(Character.this).logEvent("Character_Act_banner_clicked", bundle);
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
            videoController.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() { // from class: com.softdroid.fake.call.Activities.Character.7
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
        builder.forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() { // from class: com.softdroid.fake.call.Activities.Character.8
            @Override // com.google.android.gms.ads.formats.UnifiedNativeAd.OnUnifiedNativeAdLoadedListener
            public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                if (Character.this.nativeAd2 != null) {
                    Character.this.nativeAd2.destroy();
                }
                Character.this.nativeAd2 = unifiedNativeAd;
                FrameLayout frameLayout = (FrameLayout) Character.this.findViewById(R.id.fl_adplaceholder_2);
                UnifiedNativeAdView unifiedNativeAdView = (UnifiedNativeAdView) Character.this.getLayoutInflater().inflate(R.layout.ad_unified_character, (ViewGroup) null);
                Character.this.populateUnifiedNativeAdView(unifiedNativeAd, unifiedNativeAdView);
                frameLayout.removeAllViews();
                frameLayout.addView(unifiedNativeAdView);
            }
        });
        builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(true).build()).build());
        builder.withAdListener(new AdListener() { // from class: com.softdroid.fake.call.Activities.Character.9
            @Override // com.google.android.gms.ads.AdListener
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                Character.this.shimmerFrameLayout1.setVisibility(View.GONE);
                Character.this.shimmerFrameLayout1.stopShimmer();
                Character.this.frameLayout_2.setVisibility(View.GONE);
                Bundle bundle = new Bundle();
                bundle.putString("Character_Act_native_failed", "Character_Act_native_failed");
                ///FirebaseAnalytics.getInstance(Character.this).logEvent("Character_Act_native_failed", bundle);
            }

            @Override // com.google.android.gms.ads.AdListener
            public void onAdLoaded() {
                super.onAdLoaded();
                Character.this.frameLayout_2.setVisibility(View.VISIBLE);
                Character.this.shimmerFrameLayout1.setVisibility(View.GONE);
                Character.this.shimmerFrameLayout1.stopShimmer();
                Bundle bundle = new Bundle();
                bundle.putString("Character_Act_native_show", "Character_Act_native_show");
                //FirebaseAnalytics.getInstance(Character.this).logEvent("Character_Act_native_show", bundle);
            }

            @Override // com.google.android.gms.ads.AdListener, com.google.android.gms.internal.ads.zzve
            public void onAdClicked() {
                super.onAdClicked();
                Bundle bundle = new Bundle();
                bundle.putString("Character_Act_native_clicked", "Character_Act_native_clicked");
               // FirebaseAnalytics.getInstance(Character.this).logEvent("Character_Act_native_clicked", bundle);
            }
        }).build().loadAd(new AdRequest.Builder().addTestDevice(getResources().getString(R.string.vicky_s8)).addTestDevice(MainActivity.test_mobile_white).addTestDevice("A86A0D556F68465C49063589837FCF98").build());
    }

    private void initilize_ads() {
        InterstitialAd interstitialAd = new InterstitialAd(this);
        this.mInterstitialAd = interstitialAd;
        interstitialAd.setAdUnitId(getResources().getString(R.string.Interstitial_admob));
        requestNewInterstitial();
        load_adaptive_Banner();
        loading_native_advance_ad2();
    }

    private void requestNewInterstitial() {
        this.mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice(getResources().getString(R.string.vicky_s8)).addTestDevice(getResources().getString(R.string.rorjan_test_device)).addTestDevice(MainActivity.test_mobile_white).addTestDevice("A86A0D556F68465C49063589837FCF98").build());
    }

    @Override
    public void onBackPressed() {
        if (this.mInterstitialAd.isLoaded()) {
            this.mInterstitialAd.show();
            Bundle bundle = new Bundle();
            bundle.putString("Character_Act_intrstial_show_back_prsd", "Character_Act_intrstial_show_back_prsd");
            //FirebaseAnalytics.getInstance(this).logEvent("Character_Act_intrstial_show_back_prsd", bundle);
        } else {
            finish();
        }
        this.mInterstitialAd.setAdListener(new AdListener() { // from class: com.softdroid.fake.call.Activities.Character.10
            @Override // com.google.android.gms.ads.AdListener
            public void onAdClosed() {
                super.onAdClosed();
                Bundle bundle2 = new Bundle();
                bundle2.putString("Character_Act_intrstial_closed_back_prsd", "Character_Act_intrstial_closed_back_prsd");
               // FirebaseAnalytics.getInstance(Character.this).logEvent("Character_Act_intrstial_closed_back_prsd", bundle2);
                Character.this.finish();
            }

            @Override // com.google.android.gms.ads.AdListener, com.google.android.gms.internal.ads.zzve
            public void onAdClicked() {
                Bundle bundle2 = new Bundle();
                bundle2.putString("Character_Act_intrstial_click_back_prsd", "Character_Act_intrstial_click_back_prsd");
               // FirebaseAnalytics.getInstance(Character.this).logEvent("Character_Act_intrstial_click_back_prsd", bundle2);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (this.mysharedPreference.getBoolean(getResources().getString(R.string.dark_mode_pref), false)) {
            this.top_actionbar_layout.setBackgroundColor(getResources().getColor(R.color.top_bar_dark));
            this.main_layout.setBackgroundColor(getResources().getColor(R.color.background_dark));
            return;
        }
        this.top_actionbar_layout.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        this.main_layout.setBackgroundColor(getResources().getColor(R.color.background_color));
    }
}
