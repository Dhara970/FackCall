package com.example.fake.call.Activities;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
//import com.google.firebase.analytics.FirebaseAnalytics;
import com.example.fake.call.Adapter.SlidingImage_Adapter;

import com.example.fake.call.MainActivity;
import com.example.fake.call.R;

import java.util.ArrayList;

public class Call_Themes extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<Integer> ImagesArray = new ArrayList<>();
    private ArrayList<String> ImagesArray_name = new ArrayList<>();
    ImageView arrow_left;
    ImageView arrow_right;
    ImageView back_btn;
    int call_screen_selected_position = 0;
    ImageView circle_1;
    ImageView circle_2;
    ImageView circle_3;
    ImageView circle_4;
    ImageView circle_5;
    ImageView circle_6;
    ImageView circle_7;
    ImageView circle_8;
    int current_pager_position = 0;
    Boolean dark_mode = false;
    InterstitialAd mInterstitialAd;
    private ViewPager mPager;
    RelativeLayout main_layout;
    String path;
    TextView select_theme;
    SharedPreferences sharedPreferences;
    SlidingImage_Adapter slidingImage_adapter;
    ImageView tick;
    LinearLayout top_actionbar_layout;

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

    /* access modifiers changed from: protected */
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
        setContentView((int) R.layout.activity_call__themes);
        intilizing_ads();
        initilize_components();
        getting_prefrence();
    }

    private void intilizing_ads() {
        InterstitialAd interstitialAd = new InterstitialAd(this);
        this.mInterstitialAd = interstitialAd;
        interstitialAd.setAdUnitId(getResources().getString(R.string.Interstitial_admob));
        requestNewInterstitial();
    }

    private void requestNewInterstitial() {
        this.mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice(getResources().getString(R.string.vicky_s8)).addTestDevice(MainActivity.uxi_device).addTestDevice(MainActivity.test_mobile_white).addTestDevice("A86A0D556F68465C49063589837FCF98").build());
    }

    private void getting_prefrence() {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        this.sharedPreferences = defaultSharedPreferences;
        if (defaultSharedPreferences.getBoolean(getResources().getString(R.string.dark_mode_pref), false)) {
            this.top_actionbar_layout.setBackgroundColor(getResources().getColor(R.color.top_bar_dark));
            this.main_layout.setBackgroundColor(getResources().getColor(R.color.background_dark));
            this.dark_mode = true;
        }
        int i = this.sharedPreferences.getInt(getResources().getString(R.string.call_screen_position), 1);
        this.call_screen_selected_position = i;
        if (i == 0) {
            this.tick.setVisibility(View.VISIBLE);
        }
        this.mPager.setCurrentItem(this.call_screen_selected_position);
        SlidingImage_Adapter slidingImage_Adapter = new SlidingImage_Adapter(this, this.ImagesArray, this.ImagesArray_name, this.dark_mode);
        this.slidingImage_adapter = slidingImage_Adapter;
        this.mPager.setAdapter(slidingImage_Adapter);
        this.mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int i) {
            }

            public void onPageScrolled(int i, float f, int i2) {
                Call_Themes.this.current_pager_position = i;
            }

            public void onPageSelected(int i) {
                Call_Themes.this.slidingImage_adapter.notifyDataSetChanged();
                if (i == 0) {
                    Call_Themes.this.arrow_left.setEnabled(false);
                    Call_Themes.this.arrow_right.setEnabled(true);
                    Call_Themes.this.arrow_left.setImageResource(R.mipmap.arrow_left_disabled);
                } else if (i == 7) {
                    Call_Themes.this.arrow_left.setEnabled(true);
                    Call_Themes.this.arrow_right.setEnabled(false);
                    Call_Themes.this.arrow_right.setImageResource(R.mipmap.arrow_right_disabled);
                } else {
                    Call_Themes.this.arrow_right.setImageResource(R.drawable.arrow_right_slctr);
                    Call_Themes.this.arrow_left.setImageResource(R.drawable.arrow_left_slctr);
                    Call_Themes.this.arrow_left.setEnabled(true);
                    Call_Themes.this.arrow_right.setEnabled(true);
                }
                switch (i) {
                    case 0:
                        Call_Themes.this.circle_1.setImageResource(R.mipmap.circle_filled);
                        Call_Themes.this.circle_2.setImageResource(R.mipmap.circle);
                        Call_Themes.this.circle_3.setImageResource(R.mipmap.circle);
                        Call_Themes.this.circle_4.setImageResource(R.mipmap.circle);
                        Call_Themes.this.circle_5.setImageResource(R.mipmap.circle);
                        Call_Themes.this.circle_6.setImageResource(R.mipmap.circle);
                        Call_Themes.this.circle_7.setImageResource(R.mipmap.circle);
                        Call_Themes.this.circle_8.setImageResource(R.mipmap.circle);
                        break;
                    case 1:
                        Call_Themes.this.circle_1.setImageResource(R.mipmap.circle);
                        Call_Themes.this.circle_2.setImageResource(R.mipmap.circle_filled);
                        Call_Themes.this.circle_3.setImageResource(R.mipmap.circle);
                        Call_Themes.this.circle_4.setImageResource(R.mipmap.circle);
                        Call_Themes.this.circle_5.setImageResource(R.mipmap.circle);
                        Call_Themes.this.circle_6.setImageResource(R.mipmap.circle);
                        Call_Themes.this.circle_7.setImageResource(R.mipmap.circle);
                        Call_Themes.this.circle_8.setImageResource(R.mipmap.circle);
                        break;
                    case 2:
                        Call_Themes.this.circle_1.setImageResource(R.mipmap.circle);
                        Call_Themes.this.circle_2.setImageResource(R.mipmap.circle);
                        Call_Themes.this.circle_3.setImageResource(R.mipmap.circle_filled);
                        Call_Themes.this.circle_4.setImageResource(R.mipmap.circle);
                        Call_Themes.this.circle_5.setImageResource(R.mipmap.circle);
                        Call_Themes.this.circle_6.setImageResource(R.mipmap.circle);
                        Call_Themes.this.circle_7.setImageResource(R.mipmap.circle);
                        Call_Themes.this.circle_8.setImageResource(R.mipmap.circle);
                        break;
                    case 3:
                        Call_Themes.this.circle_1.setImageResource(R.mipmap.circle);
                        Call_Themes.this.circle_2.setImageResource(R.mipmap.circle);
                        Call_Themes.this.circle_3.setImageResource(R.mipmap.circle);
                        Call_Themes.this.circle_4.setImageResource(R.mipmap.circle_filled);
                        Call_Themes.this.circle_5.setImageResource(R.mipmap.circle);
                        Call_Themes.this.circle_6.setImageResource(R.mipmap.circle);
                        Call_Themes.this.circle_7.setImageResource(R.mipmap.circle);
                        Call_Themes.this.circle_8.setImageResource(R.mipmap.circle);
                        break;
                    case 4:
                        Call_Themes.this.circle_1.setImageResource(R.mipmap.circle);
                        Call_Themes.this.circle_2.setImageResource(R.mipmap.circle);
                        Call_Themes.this.circle_3.setImageResource(R.mipmap.circle);
                        Call_Themes.this.circle_4.setImageResource(R.mipmap.circle);
                        Call_Themes.this.circle_5.setImageResource(R.mipmap.circle_filled);
                        Call_Themes.this.circle_6.setImageResource(R.mipmap.circle);
                        Call_Themes.this.circle_7.setImageResource(R.mipmap.circle);
                        Call_Themes.this.circle_8.setImageResource(R.mipmap.circle);
                        break;
                    case 5:
                        Call_Themes.this.circle_1.setImageResource(R.mipmap.circle);
                        Call_Themes.this.circle_2.setImageResource(R.mipmap.circle);
                        Call_Themes.this.circle_3.setImageResource(R.mipmap.circle);
                        Call_Themes.this.circle_4.setImageResource(R.mipmap.circle);
                        Call_Themes.this.circle_5.setImageResource(R.mipmap.circle);
                        Call_Themes.this.circle_6.setImageResource(R.mipmap.circle_filled);
                        Call_Themes.this.circle_7.setImageResource(R.mipmap.circle);
                        Call_Themes.this.circle_8.setImageResource(R.mipmap.circle);
                        break;
                    case 6:
                        Call_Themes.this.circle_1.setImageResource(R.mipmap.circle);
                        Call_Themes.this.circle_2.setImageResource(R.mipmap.circle);
                        Call_Themes.this.circle_3.setImageResource(R.mipmap.circle);
                        Call_Themes.this.circle_4.setImageResource(R.mipmap.circle);
                        Call_Themes.this.circle_5.setImageResource(R.mipmap.circle);
                        Call_Themes.this.circle_6.setImageResource(R.mipmap.circle);
                        Call_Themes.this.circle_7.setImageResource(R.mipmap.circle_filled);
                        Call_Themes.this.circle_8.setImageResource(R.mipmap.circle);
                        break;
                    case 7:
                        Call_Themes.this.circle_1.setImageResource(R.mipmap.circle);
                        Call_Themes.this.circle_2.setImageResource(R.mipmap.circle);
                        Call_Themes.this.circle_3.setImageResource(R.mipmap.circle);
                        Call_Themes.this.circle_4.setImageResource(R.mipmap.circle);
                        Call_Themes.this.circle_5.setImageResource(R.mipmap.circle);
                        Call_Themes.this.circle_6.setImageResource(R.mipmap.circle);
                        Call_Themes.this.circle_7.setImageResource(R.mipmap.circle);
                        Call_Themes.this.circle_8.setImageResource(R.mipmap.circle_filled);
                        break;
                }
                if (i == Call_Themes.this.call_screen_selected_position) {
                    Call_Themes.this.tick.setVisibility(View.VISIBLE);
                    Call_Themes.this.select_theme.setEnabled(false);
                    Call_Themes.this.select_theme.setBackground(Call_Themes.this.getResources().getDrawable(R.drawable.ad_btn_bg_disable));
                    return;
                }
                Call_Themes.this.select_theme.setEnabled(true);
                Call_Themes.this.select_theme.setBackground(Call_Themes.this.getResources().getDrawable(R.drawable.btn_slctr));
                Call_Themes.this.tick.setVisibility(View.GONE);
            }
        });
        this.mPager.setCurrentItem(this.sharedPreferences.getInt(getResources().getString(R.string.call_screen_position), 1));
        this.select_theme.setEnabled(false);
        this.select_theme.setBackground(getResources().getDrawable(R.drawable.ad_btn_bg_disable));
    }

    private void adding_items_in_array() {
        this.ImagesArray.add(Integer.valueOf(R.mipmap.theme_1_blurry_dark));
        this.ImagesArray_name.add("Blurry dark");
        this.ImagesArray.add(Integer.valueOf(R.mipmap.theme_2_dark_blue));
        this.ImagesArray_name.add("Dark blue");
        this.ImagesArray.add(Integer.valueOf(R.mipmap.theme_3_galaxy_a51));
        this.ImagesArray_name.add("Galaxy A51");
        this.ImagesArray.add(Integer.valueOf(R.mipmap.theme_4_htc_one));
        this.ImagesArray_name.add("HTC One");
        this.ImagesArray.add(Integer.valueOf(R.mipmap.theme_5_htc_one_m7));
        this.ImagesArray_name.add("HTC One M7");
        this.ImagesArray.add(Integer.valueOf(R.mipmap.theme_6_huawei_y9));
        this.ImagesArray_name.add("Huawei Y9");
        this.ImagesArray.add(Integer.valueOf(R.mipmap.theme_7_ice_white));
        this.ImagesArray_name.add("Ice white");
        this.ImagesArray.add(Integer.valueOf(R.mipmap.theme_8_mid_red));
        this.ImagesArray_name.add("Mid red");
    }

    private void initilize_components() {
        this.mPager = (ViewPager) findViewById(R.id.pager);
        this.arrow_right = (ImageView) findViewById(R.id.arrow_right);
        this.arrow_left = (ImageView) findViewById(R.id.arrow_left);
        this.circle_1 = (ImageView) findViewById(R.id.circle_1);
        this.circle_2 = (ImageView) findViewById(R.id.circle_2);
        this.circle_3 = (ImageView) findViewById(R.id.circle_3);
        this.circle_4 = (ImageView) findViewById(R.id.circle_4);
        this.circle_5 = (ImageView) findViewById(R.id.circle_5);
        this.circle_6 = (ImageView) findViewById(R.id.circle_6);
        this.circle_7 = (ImageView) findViewById(R.id.circle_7);
        this.circle_8 = (ImageView) findViewById(R.id.circle_8);
        this.tick = (ImageView) findViewById(R.id.tick);
        this.back_btn = (ImageView) findViewById(R.id.back_btn);
        this.select_theme = (TextView) findViewById(R.id.select_theme);
        this.top_actionbar_layout = (LinearLayout) findViewById(R.id.top_actionbar_layout);
        this.main_layout = (RelativeLayout) findViewById(R.id.main_layout);
        adding_items_in_array();
        this.arrow_right.setOnClickListener(this);
        this.arrow_left.setOnClickListener(this);
        this.select_theme.setOnClickListener(this);
        this.back_btn.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.arrow_left /*2131230807*/:
                if (this.current_pager_position >= 1) {
                    this.mPager.setCurrentItem(this.mPager.getCurrentItem() - 1);
                    return;
                }
                return;
            case R.id.arrow_right /*2131230808*/:
                if (this.current_pager_position <= 7) {
                    this.mPager.setCurrentItem(this.mPager.getCurrentItem() + 1);
                    return;
                }
                return;
            case R.id.back_btn /*2131230813*/:
                onBackPressed();
                return;
            case R.id.select_theme /*2131231142*/:
                this.call_screen_selected_position = this.mPager.getCurrentItem();
                this.tick.setVisibility(View.VISIBLE);
                this.sharedPreferences.edit().putInt(getResources().getString(R.string.call_screen_position), this.call_screen_selected_position).commit();
                Toast.makeText(this, "Theme Applied " + this.ImagesArray_name.get(this.current_pager_position), Toast.LENGTH_LONG).show();
                this.select_theme.setEnabled(false);
                this.select_theme.setBackground(getResources().getDrawable(R.drawable.ad_btn_bg_disable));
                return;
            default:
                return;
        }
    }

    public void onBackPressed() {
        if (this.mInterstitialAd.isLoaded()) {
            this.mInterstitialAd.show();
            Bundle bundle = new Bundle();
            bundle.putString("Call_Themes_Intrstal_Ad_show_back_prsd", "Call_Themes_Intrstal_Ad_show_back_prsd");
           // FirebaseAnalytics.getInstance(this).logEvent("Call_Themes_Intrstal_Ad_show_back_prsd", bundle);
        } else {
            finish();
        }
        this.mInterstitialAd.setAdListener(new AdListener() {
            public void onAdClosed() {
                super.onAdClosed();
                Bundle bundle = new Bundle();
                bundle.putString("Call_Themes_Intrstal_Ad_closed_back_prsd", "Call_Themes_Intrstal_Ad_closed_back_prsd");
               // FirebaseAnalytics.getInstance(Call_Themes.this).logEvent("Call_Themes_Intrstal_Ad_closed_back_prsd", bundle);
                Call_Themes.this.finish();
            }

            public void onAdClicked() {
                super.onAdClicked();
                Bundle bundle = new Bundle();
                bundle.putString("Call_Themes_Intrstal_Ad_click_back_prsd", "Call_Themes_Intrstal_Ad_click_back_prsd");
               // FirebaseAnalytics.getInstance(Call_Themes.this).logEvent("Call_Themes_Intrstal_Ad_click_back_prsd", bundle);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.sharedPreferences.getBoolean(getResources().getString(R.string.dark_mode_pref), false)) {
            this.top_actionbar_layout.setBackgroundColor(getResources().getColor(R.color.top_bar_dark));
            this.main_layout.setBackgroundColor(getResources().getColor(R.color.background_dark));
        }
    }
}
