package com.example.fake.call.Activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.Display;
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
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
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

import com.example.fake.call.Adapter.NotesAdapter;
import com.example.fake.call.Helper.DatabaseHelper;
import com.example.fake.call.MainActivity;
import com.example.fake.call.Model.Note;
import com.example.fake.call.R;
import com.example.fake.call.Utils_.MyDividerItemDecoration;
import com.example.fake.call.Utils_.RecyclerTouchListener;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class Schedule_History_Act extends AppCompatActivity {
    private LinearLayout adContainerView;
    private AdView adaptive_adView;
    ImageView back_btn;
    private DatabaseHelper db;
    ImageView delete_all_btn;
    FrameLayout frameLayout_2;
    private NotesAdapter mAdapter;
    InterstitialAd mInterstitialAd;
    RelativeLayout main_layout;
    private UnifiedNativeAd nativeAd2;
    private TextView noNotesView;
    private RecyclerView recyclerView;
    SharedPreferences sharedPreferences;
    ShimmerFrameLayout shimmerFrameLayout;
    LinearLayout top_actionbar_layout;
    private List<Note> notesList = new ArrayList();
    int myposition = 0;
    Boolean dark_mode_enable = false;

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
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        this.sharedPreferences = defaultSharedPreferences;
        if (defaultSharedPreferences.getBoolean(getResources().getString(R.string.dark_mode_pref), false)) {
            this.dark_mode_enable = true;
        }
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
        setContentView(R.layout.activity_schedule__history_);
        ShimmerFrameLayout shimmerFrameLayout = (ShimmerFrameLayout) findViewById(R.id.ad_loader);
        this.shimmerFrameLayout = shimmerFrameLayout;
        shimmerFrameLayout.startShimmer();
        getting_prefrence();
        initilize_components();
        InterstitialAd interstitialAd = new InterstitialAd(this);
        this.mInterstitialAd = interstitialAd;
        interstitialAd.setAdUnitId(getResources().getString(R.string.Interstitial_admob));
        requestNewInterstitial();
        load_adaptive_Banner();
    }

    private void load_adaptive_Banner() {
        MobileAds.initialize(this, new OnInitializationCompleteListener() { // from class: com.softdroid.fake.call.Activities.Schedule_History_Act.1
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
        this.adaptive_adView.setAdListener(new AdListener() { // from class: com.softdroid.fake.call.Activities.Schedule_History_Act.2
            @Override // com.google.android.gms.ads.AdListener, com.google.android.gms.internal.ads.zzve
            public void onAdClicked() {
                super.onAdClicked();
                Bundle bundle = new Bundle();
                bundle.putString("History_Act_banner_clicked", "History_Act_banner_clicked");
               // FirebaseAnalytics.getInstance(Schedule_History_Act.this).logEvent("History_Act_banner_clicked", bundle);
            }

            @Override // com.google.android.gms.ads.AdListener
            public void onAdImpression() {
                super.onAdImpression();
                Bundle bundle = new Bundle();
                bundle.putString("History_Act_banner_show", "History_Act_banner_show");
               // FirebaseAnalytics.getInstance(Schedule_History_Act.this).logEvent("History_Act_banner_show", bundle);
            }

            @Override // com.google.android.gms.ads.AdListener
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                Schedule_History_Act.this.shimmerFrameLayout.setVisibility(View.GONE);
                Schedule_History_Act.this.shimmerFrameLayout.stopShimmer();
                Bundle bundle = new Bundle();
                bundle.putString("History_Act_banner_failed", "History_Act_banner_failed");
               // FirebaseAnalytics.getInstance(Schedule_History_Act.this).logEvent("History_Act_banner_failed", bundle);
            }

            @Override // com.google.android.gms.ads.AdListener
            public void onAdLoaded() {
                super.onAdLoaded();
                Schedule_History_Act.this.shimmerFrameLayout.setVisibility(View.GONE);
                Schedule_History_Act.this.shimmerFrameLayout.stopShimmer();
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
        this.mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice(getResources().getString(R.string.vicky_s8)).addTestDevice(getResources().getString(R.string.rorjan_test_device)).addTestDevice(MainActivity.uxi_device).addTestDevice(MainActivity.test_mobile_white).addTestDevice("A86A0D556F68465C49063589837FCF98").build());
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
            videoController.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() { // from class: com.softdroid.fake.call.Activities.Schedule_History_Act.3
                @Override // com.google.android.gms.ads.VideoController.VideoLifecycleCallbacks
                public void onVideoEnd() {
                    super.onVideoEnd();
                }
            });
        }
    }

    private void loading_native_advance_ad2() {
        AdLoader.Builder builder = new AdLoader.Builder(this, getResources().getString(R.string.native_advance_admob));
        this.frameLayout_2 = (FrameLayout) findViewById(R.id.fl_adplaceholder_1);
        builder.forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() { // from class: com.softdroid.fake.call.Activities.Schedule_History_Act.4
            @Override // com.google.android.gms.ads.formats.UnifiedNativeAd.OnUnifiedNativeAdLoadedListener
            public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                if (Schedule_History_Act.this.nativeAd2 != null) {
                    Schedule_History_Act.this.nativeAd2.destroy();
                }
                try {
                    Schedule_History_Act.this.nativeAd2 = unifiedNativeAd;
                    FrameLayout frameLayout = (FrameLayout) Schedule_History_Act.this.findViewById(R.id.fl_adplaceholder_1);
                    UnifiedNativeAdView unifiedNativeAdView = (UnifiedNativeAdView) Schedule_History_Act.this.getLayoutInflater().inflate(R.layout.ad_unified, (ViewGroup) null);
                    Schedule_History_Act.this.populateUnifiedNativeAdView(unifiedNativeAd, unifiedNativeAdView);
                    frameLayout.removeAllViews();
                    frameLayout.addView(unifiedNativeAdView);
                } catch (NullPointerException unused) {
                }
            }
        });
        builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(true).build()).build());
        builder.withAdListener(new AdListener() { // from class: com.softdroid.fake.call.Activities.Schedule_History_Act.5
            @Override // com.google.android.gms.ads.AdListener
            public void onAdFailedToLoad(int i) {
            }

            @Override // com.google.android.gms.ads.AdListener
            public void onAdLoaded() {
                super.onAdLoaded();
                Schedule_History_Act.this.frameLayout_2.setVisibility(View.VISIBLE);
            }
        }).build().loadAd(new AdRequest.Builder().addTestDevice(getResources().getString(R.string.vicky_s8)).addTestDevice(getResources().getString(R.string.rorjan_test_device)).addTestDevice(MainActivity.uxi_device).addTestDevice(MainActivity.test_mobile_white).addTestDevice("A86A0D556F68465C49063589837FCF98").build());
    }

    private void initilize_components() {
        this.recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        this.noNotesView = (TextView) findViewById(R.id.empty_notes_view);
        this.back_btn = (ImageView) findViewById(R.id.back_btn);
        this.delete_all_btn = (ImageView) findViewById(R.id.delete_all_btn);
        this.main_layout = (RelativeLayout) findViewById(R.id.main_layout);
        this.top_actionbar_layout = (LinearLayout) findViewById(R.id.top_actionbar_layout);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        this.db = databaseHelper;
        this.notesList.addAll(databaseHelper.getAllNotes());
        this.mAdapter = new NotesAdapter(this, this.notesList, this.dark_mode_enable);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
        this.recyclerView.addItemDecoration(new MyDividerItemDecoration(this, 1, 16));
        this.recyclerView.setAdapter(this.mAdapter);
        this.recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, this.recyclerView, new RecyclerTouchListener.ClickListener() { // from class: com.softdroid.fake.call.Activities.Schedule_History_Act.6
            @Override // com.softdroid.fake.call.Utils_.RecyclerTouchListener.ClickListener
            public void onClick(View view, int i) {
            }

            @Override // com.softdroid.fake.call.Utils_.RecyclerTouchListener.ClickListener
            public void onLongClick(View view, int i) {
                Schedule_History_Act.this.myposition = i;
                Schedule_History_Act.this.showActionsDialog(i);
            }
        }));
        this.back_btn.setOnClickListener(new View.OnClickListener() { // from class: com.softdroid.fake.call.Activities.Schedule_History_Act.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Schedule_History_Act.this.onBackPressed();
            }
        });
        this.delete_all_btn.setOnClickListener(new View.OnClickListener() { // from class: com.softdroid.fake.call.Activities.Schedule_History_Act.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Schedule_History_Act.this.db.getNotesCount() > 0) {
                    Schedule_History_Act.this.delete_all_record_Dialog();
                } else {
                    Toast.makeText(Schedule_History_Act.this, "No Records found !",  Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_setting_shedule_history, menu);
        return true;
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.delete_all_menu) {
            return super.onOptionsItemSelected(menuItem);
        }
        if (this.db.getNotesCount() > 0) {
            delete_all_record_Dialog();
        } else {
            Toast.makeText(this, "No Records found !",  Toast.LENGTH_LONG).show();
        }
        return true;
    }


    public void showActionsDialog(final int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.delete_title));
        builder.setMessage(getResources().getString(R.string.delete_this));
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() { // from class: com.softdroid.fake.call.Activities.Schedule_History_Act.9
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                dialogInterface.dismiss();
            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() { // from class: com.softdroid.fake.call.Activities.Schedule_History_Act.10
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                Schedule_History_Act.this.deleteNote(i);
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }


    public void delete_all_record_Dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.delete_all));
        builder.setMessage(getResources().getString(R.string.clear_all_record));
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() { // from class: com.softdroid.fake.call.Activities.Schedule_History_Act.11
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                Schedule_History_Act.this.delete_All_Records();
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() { // from class: com.softdroid.fake.call.Activities.Schedule_History_Act.12
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }


    public void deleteNote(int i) {
        this.db.deleteNote(this.notesList.get(i));
        try {
            this.notesList.remove(i);
            this.mAdapter.notifyItemRemoved(i);
            toggleEmptyNotes();
        } catch ( Exception unused) {
        }
    }


    public void delete_All_Records() {
        try {
            this.db.deleteallNotes();
        } catch ( Exception unused) {
        }
        try {
            this.notesList.clear();
            toggleEmptyNotes();
            this.mAdapter.notifyDataSetChanged();
        } catch ( Exception unused2) {
        }
    }

    private void toggleEmptyNotes() {
        if (this.db.getNotesCount() > 0) {
            this.noNotesView.setVisibility(View.GONE);
        } else {
            this.noNotesView.setVisibility(View.VISIBLE);
        }
    }

    private void createNote(String str, String str2) {
        Note note = this.db.getNote(this.db.insertNote(str, str2, "", ""));
        if (note != null) {
            this.notesList.add(0, note);
            this.mAdapter.notifyDataSetChanged();
            toggleEmptyNotes();
        }
    }

    private void updateNote(String str, int i) {
        Note note = this.notesList.get(i);
        note.setNote(str);
        this.db.updateNote(note);
        this.notesList.set(i, note);
        this.mAdapter.notifyItemChanged(i);
        toggleEmptyNotes();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (this.mInterstitialAd.isLoaded()) {
            this.mInterstitialAd.show();
            Bundle bundle = new Bundle();
            bundle.putString("History_Act_intrstital_show_back_presd", "History_Act_intrstital_show_back_presd");
          //  FirebaseAnalytics.getInstance(this).logEvent("History_Act_intrstital_show_back_presd", bundle);
        } else {
            finish();
        }
        this.mInterstitialAd.setAdListener(new AdListener() { // from class: com.softdroid.fake.call.Activities.Schedule_History_Act.13
            @Override // com.google.android.gms.ads.AdListener
            public void onAdClosed() {
                super.onAdClosed();
                Bundle bundle2 = new Bundle();
                bundle2.putString("History_Act_intrstital_closed_back_presd", "History_Act_intrstital_closed_back_presd");
               // FirebaseAnalytics.getInstance(Schedule_History_Act.this).logEvent("History_Act_intrstital_closed_back_presd", bundle2);
                Schedule_History_Act.this.finish();
            }

            @Override // com.google.android.gms.ads.AdListener, com.google.android.gms.internal.ads.zzve
            public void onAdClicked() {
                super.onAdClicked();
                Bundle bundle2 = new Bundle();
                bundle2.putString("History_Act_intrstital_click_back_presd", "History_Act_intrstital_click_back_presd");
               // FirebaseAnalytics.getInstance(Schedule_History_Act.this).logEvent("History_Act_intrstital_click_back_presd", bundle2);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        toggleEmptyNotes();
        if (this.sharedPreferences.getBoolean(getResources().getString(R.string.dark_mode_pref), false)) {
            this.top_actionbar_layout.setBackgroundColor(getResources().getColor(R.color.top_bar_dark));
            this.main_layout.setBackgroundColor(getResources().getColor(R.color.background_dark));
            this.recyclerView.setBackground(getResources().getDrawable(R.drawable.ad_bg_new_dark));
            this.noNotesView.setTextColor(getResources().getColor(R.color.text_color_dark));
        }
    }
}
