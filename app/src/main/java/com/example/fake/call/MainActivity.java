package com.example.fake.call;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.Service;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.provider.Settings;
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

import com.example.fake.call.Activities.Call_Themes;
import com.example.fake.call.Activities.Caller;
import com.example.fake.call.Activities.Change_Ringtone;
import com.example.fake.call.Activities.Exit_Act;
import com.example.fake.call.Activities.Record_Audio;
import com.example.fake.call.Activities.Schedule_History_Act;
import com.example.fake.call.Activities.Setting_Act;
import com.example.fake.call.Call_Screens.Call_Now_Huawei_Mate;
import com.example.fake.call.Call_Screens.Call_Screen_A51;
import com.example.fake.call.Call_Screens.Call_Screen_Blurry_Dark;
import com.example.fake.call.Call_Screens.Call_now_Samsung_S5;
import com.example.fake.call.Call_Screens.Call_screen_HTC_1;
import com.example.fake.call.Call_Screens.Dark_blue;
import com.example.fake.call.Call_Screens.Ice_White;
import com.example.fake.call.Call_Screens.Mid_red;
import com.example.fake.call.Service.MyService;
import com.example.fake.call.Service.Screen_off_service;
import com.example.fake.call.Service.Shake_Service;
import com.example.fake.call.Utils_.Shared;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.tasks.OnSuccessListener;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.onesignal.OneSignal;

import java.util.Stack;

/* loaded from: classes2.dex */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static int ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE = 202;
    public static final String ALLOW_KEY = "ALLOWED";
    public static final String AUDIO_PREF = "AUDIO_PREF";
    public static final int MY_PERMISSIONS_REQUEST_PHONE = 320;
    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 300;
    public static final int MY_REQUEST_CODE = 110;
    private static final String ONESIGNAL_APP_ID = "9cdcdbe4-fcbe-4e7d-8836-bacfcd3eaad8";
    public static ImageView cancel_shedule_call;
    public static Drawable drawableG1;
    public static Drawable drawableG2;
    public static Drawable drawableG3;
    public static String gridPackage1;
    public static String gridPackage2;
    public static String gridPackage3;
    public static ImageView schedule_call_image;
    public static TextView schedule_call_txtview_remaining;
    public static ImageView shedule_arrow;
    public static String test_mobile_white;
    public static String title1;
    public static String title2;
    public static String title3;
    public static String uxi_device;
    private LinearLayout adContainerView;
    private AdView adaptive_adView;
    Animation animation;
    AppUpdateManager appUpdateManager;


    TextView call_now_txtview;
    ImageView ivCallTheam;

    LinearLayout caller;
    ImageView ivCallerinfo;


    LinearLayout callnow;
    Drawable drawable;

    LinearLayout g2_layout;

    LinearLayout g3_layout;

    InterstitialAd mInterstitialAd;
    RelativeLayout main_layout;
    //ImageView menu_btn;
    TextView more_from_developer_txtview;
    LinearLayout myads_layout;
    private String packageName;
    LinearLayout pick_contact;
    ImageView ivCallAudio;
    LinearLayout ringtone;
    LinearLayout schedule;


    ImageView ivCallHistory;
    LinearLayout schedule_call_layout_main;
    LinearLayout settings_layout;
    SharedPreferences sharedPreferences;
    ShimmerFrameLayout shimmerFrameLayout;
    // LinearLayout top_actionbar_layout;

    TextView you_will_receive_call_after;
    private Uri audioFileUri = null;
    Boolean call_now_click = false;
    Boolean is_call_active = false;

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


    @Override
    // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
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
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_home);
        ShimmerFrameLayout shimmerFrameLayout = (ShimmerFrameLayout) findViewById(R.id.ad_loader);
        this.shimmerFrameLayout = shimmerFrameLayout;
        shimmerFrameLayout.startShimmer();
        test_mobile_white = getResources().getString(R.string.test_mobile_white);
        uxi_device = getResources().getString(R.string.uxi_device);
        this.animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim);
        InterstitialAd interstitialAd = new InterstitialAd(this);
        this.mInterstitialAd = interstitialAd;
        interstitialAd.setAdUnitId(getResources().getString(R.string.Interstitial_admob));
        requestNewInterstitial();
        load_adaptive_Banner();
        getting_prefrence();
        initlize_componenets();
        before_loading_ads();
      /*  if (this.sharedPreferences.getBoolean(getResources().getString(R.string.fisrt_time_dialoug), true)) {
           // new GridAsyncTask(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getResources().getString(R.string.main_grid_images_link));
            this.sharedPreferences.edit().putBoolean(getResources().getString(R.string.fisrt_time_dialoug), false).apply();
        } else {
            new SingleAdTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "");
        }*/
        if (this.sharedPreferences.getBoolean(getResources().getString(R.string.shake_pref), false)) {
            if (Build.VERSION.SDK_INT > 25) {
                startForegroundService(new Intent(this, Shake_Service.class));
            } else {
                startService(new Intent(this, Shake_Service.class));
            }
        }
        if (this.sharedPreferences.getBoolean(getResources().getString(R.string.triple_tap_pref), false)) {
            if (Build.VERSION.SDK_INT > 25) {
                startForegroundService(new Intent(this, Screen_off_service.class));
            } else {
                startService(new Intent(this, Screen_off_service.class));
            }
        }
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(this);

        OneSignal.setAppId(ONESIGNAL_APP_ID);
        check_for_update();
    }

    private void check_for_update() {
        AppUpdateManager create = AppUpdateManagerFactory.create(getApplicationContext());
        this.appUpdateManager = create;
        create.getAppUpdateInfo().addOnSuccessListener(new OnSuccessListener<AppUpdateInfo>() { // from class: com.softdroid.fake.call.MainActivity.1
            @SuppressLint("WrongConstant")
            public void onSuccess(AppUpdateInfo appUpdateInfo) {
                if (appUpdateInfo.updateAvailability() == 2 && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
                    try {
                        MainActivity.this.appUpdateManager.startUpdateFlowForResult(appUpdateInfo, AppUpdateType.IMMEDIATE, MainActivity.this, 110);
                    } catch (IntentSender.SendIntentException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }



  /*  public class GridAsyncTask extends AsyncTask<String, String, List<AdsModel>> {
        private final List<AdsModel> adsModelList = new ArrayList();
        private final Context mContext;

        public GridAsyncTask(Context context) {
            this.mContext = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        public List<AdsModel> doInBackground(String... strArr) {
            try {
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new DefaultHttpClient().execute(new HttpPost(MainActivity.this.getResources().getString(R.string.main_grid_images_link))).getEntity().getContent()));
                    StringBuffer stringBuffer = new StringBuffer();
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        stringBuffer.append(readLine);
                    }
                    JSONArray jSONArray = new JSONObject(stringBuffer.toString().replace("\\/", "/")).getJSONArray("application");
                    StringBuffer stringBuffer2 = new StringBuffer();
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObject = jSONArray.getJSONObject(i);
                        AdsModel adsModel = new AdsModel();
                        adsModel.setUrlApp(jSONObject.getString(ImagesContract.URL));
                        adsModel.setImageUrl(jSONObject.getString("image"));
                       // adsModel.setAppTitle(jSONObject.getString(OneSignalDbContract.NotificationTable.COLUMN_NAME_TITLE));
                        stringBuffer2.append(adsModel);
                        System.out.println("URLS: " + jSONObject.getString(ImagesContract.URL) + " Image: " + jSONObject.getString("image"));
                        this.adsModelList.add(adsModel);
                    }
                    return this.adsModelList;
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                } catch (JSONException e2) {
                    e2.printStackTrace();
                    return null;
                }
            } catch (Exception e3) {
                e3.printStackTrace();
                return null;
            }
        }


        public void onPostExecute(List<AdsModel> list) {
            super.onPostExecute((List<AdsModel>) list);
            if (list != null) {
                DisplayImageOptions build = new DisplayImageOptions.Builder().resetViewBeforeLoading(true).resetViewBeforeLoading(false).delayBeforeLoading(Service.BIND_EXTERNAL_SERVICE).cacheInMemory(true).cacheOnDisk(true).considerExifParams(false).imageScaleType(ImageScaleType.NONE).bitmapConfig(Bitmap.Config.RGB_565).displayer(new SimpleBitmapDisplayer()).handler(new Handler()).build();
               // DisplayImageOptions build = new DisplayImageOptions.Builder().resetViewBeforeLoading(true).resetViewBeforeLoading(false).delayBeforeLoading(ServiceStarter.ERROR_UNKNOWN).cacheInMemory(true).cacheOnDisk(true).considerExifParams(false).imageScaleType(ImageScaleType.NONE).bitmapConfig(Bitmap.Config.RGB_565).displayer(new SimpleBitmapDisplayer()).handler(new Handler()).build();
                ImageLoader instance = ImageLoader.getInstance();
                ImageSize imageSize = new ImageSize(100, 100);
                MainActivity mainActivity = MainActivity.this;
                mainActivity.g1 = (ImageView) mainActivity.findViewById(R.id.g1);
                MainActivity mainActivity2 = MainActivity.this;
                mainActivity2.g2 = (ImageView) mainActivity2.findViewById(R.id.g2);
                MainActivity mainActivity3 = MainActivity.this;
                mainActivity3.g3 = (ImageView) mainActivity3.findViewById(R.id.g3);

                MainActivity mainActivity5 = MainActivity.this;
                mainActivity5.g2_layout = (LinearLayout) mainActivity5.findViewById(R.id.g2_layout);
                MainActivity mainActivity6 = MainActivity.this;
                mainActivity6.g3_layout = (LinearLayout) mainActivity6.findViewById(R.id.g3_layout);
                MainActivity mainActivity7 = MainActivity.this;
                mainActivity7.myads_layout = (LinearLayout) mainActivity7.findViewById(R.id.more_from_developer_layout);
                MainActivity.this.g2_layout.setOnClickListener(MainActivity.this);
                MainActivity.this.g3_layout.setOnClickListener(MainActivity.this);
                for (int i = 0; i < list.size(); i++) {
                    if (i == 0) {
                        AdsModel adsModel = list.get(0);
                        MainActivity.title1 = adsModel.getAppTitle();
                        String imageUrl = adsModel.getImageUrl();
                        MainActivity.gridPackage1 = adsModel.getUrlApp();
                        instance.loadImage(imageUrl, imageSize, build, new SimpleImageLoadingListener() { // from class: com.softdroid.fake.call.MainActivity.GridAsyncTask.1
                            @Override // com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener, com.nostra13.universalimageloader.core.listener.ImageLoadingListener
                            public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                                super.onLoadingComplete(str, view, bitmap);
                                try {
                                    MainActivity.drawableG1 = new BitmapDrawable(MainActivity.this.getResources(), bitmap);
                                    MainActivity.this.g1_layout.setVisibility(View.VISIBLE);
                                    MainActivity.this.myads_layout.setVisibility(View.VISIBLE);
                                    MainActivity.this.g1.setImageDrawable(MainActivity.drawableG1);
                                    MainActivity.this.g1_textview.setText(MainActivity.title1);
                                    MainActivity.this.g1.startAnimation(MainActivity.this.animation);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    if (i == 1) {
                        AdsModel adsModel2 = list.get(1);
                        String imageUrl2 = adsModel2.getImageUrl();
                        MainActivity.gridPackage2 = adsModel2.getUrlApp();
                        MainActivity.title2 = adsModel2.getAppTitle();
                        instance.loadImage(imageUrl2, imageSize, build, new SimpleImageLoadingListener() { // from class: com.softdroid.fake.call.MainActivity.GridAsyncTask.2
                            @Override // com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener, com.nostra13.universalimageloader.core.listener.ImageLoadingListener
                            public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                                super.onLoadingComplete(str, view, bitmap);
                                try {
                                    MainActivity.drawableG2 = new BitmapDrawable(MainActivity.this.getResources(), bitmap);
                                    MainActivity.this.g2.setImageDrawable(MainActivity.drawableG2);
                                    MainActivity.this.g2_textview.setText(MainActivity.title2);
                                    MainActivity.this.g2.startAnimation(MainActivity.this.animation);
                                    MainActivity.this.g1_layout.setVisibility(View.VISIBLE);
                                    MainActivity.this.myads_layout.setVisibility(View.VISIBLE);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    if (i == 2) {
                        AdsModel adsModel3 = list.get(2);
                        String imageUrl3 = adsModel3.getImageUrl();
                        MainActivity.gridPackage3 = adsModel3.getUrlApp();
                        MainActivity.title3 = adsModel3.getAppTitle();
                        instance.loadImage(imageUrl3, imageSize, build, new SimpleImageLoadingListener() { // from class: com.softdroid.fake.call.MainActivity.GridAsyncTask.3
                            @Override // com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener, com.nostra13.universalimageloader.core.listener.ImageLoadingListener
                            public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                                super.onLoadingComplete(str, view, bitmap);
                                try {
                                    MainActivity.drawableG3 = new BitmapDrawable(MainActivity.this.getResources(), bitmap);
                                    MainActivity.this.g3.setImageDrawable(MainActivity.drawableG3);
                                    MainActivity.this.g3_textview.setText(MainActivity.title3);
                                    MainActivity.this.g3.startAnimation(MainActivity.this.animation);
                                    MainActivity.this.g1_layout.setVisibility(View.VISIBLE);
                                    MainActivity.this.myads_layout.setVisibility(View.VISIBLE);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }
            }
        }
    }*/


   /* public class SingleAdTask extends AsyncTask<String, String, String> {
        public SingleAdTask() {
        }


        public String doInBackground(String... strArr) {
            try {
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new DefaultHttpClient().execute(new HttpPost(MainActivity.this.getResources().getString(R.string.single_ad_url))).getEntity().getContent()));
                    StringBuffer stringBuffer = new StringBuffer();
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine != null) {
                            stringBuffer.append(readLine);
                        } else {
                            JSONObject jSONObject = new JSONObject(stringBuffer.toString().replace("\\/", "/")).getJSONArray("application").getJSONObject(0);
                            String string = jSONObject.getString(ImagesContract.URL);
                            String string2 = jSONObject.getString("image");
                            String string3 = jSONObject.getString("name");
                            String string4 = jSONObject.getString("short_desc");
                            System.out.println("Image URL: " + string2 + " APP URL " + string);
                            return string + "#" + string2 + "#" + string3 + "#" + string4;
                        }
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    return null;
                } catch (IOException e2) {
                    e2.printStackTrace();
                    return null;
                }
            } catch (NullPointerException unused) {
                return null;
            } catch (JSONException e3) {
                e3.printStackTrace();
                return null;
            }
        }


        public void onPostExecute(String str) {
            super.onPostExecute( str);
            try {
                if (str.isEmpty() || str == null) {
                    MainActivity mainActivity = MainActivity.this;
                    new GridAsyncTask(mainActivity).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, MainActivity.this.getResources().getString(R.string.main_grid_images_link));
                } else {
                    String[] split = str.split("#");
                    MainActivity.this.packageName = split[0];
                    String str2 = split[1];
                    final String str3 = split[2];
                    final String str4 = split[3];
                    System.out.println("Image: " + str2);
                    ImageLoader.getInstance().loadImage(str2, new ImageSize(720, 1280), new DisplayImageOptions.Builder().resetViewBeforeLoading(false).delayBeforeLoading(1000).cacheInMemory(true).cacheOnDisk(true).considerExifParams(false).imageScaleType(ImageScaleType.NONE).bitmapConfig(Bitmap.Config.ARGB_8888).displayer(new SimpleBitmapDisplayer()).handler(new Handler()).build(), new SimpleImageLoadingListener() { // from class: com.softdroid.fake.call.MainActivity.SingleAdTask.1
                        @Override // com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener, com.nostra13.universalimageloader.core.listener.ImageLoadingListener
                        public void onLoadingComplete(String str5, View view, Bitmap bitmap) {
                            super.onLoadingComplete(str5, view, bitmap);
                            try {
                                MainActivity.this.drawable = new BitmapDrawable(MainActivity.this.getResources(), bitmap);
                                MainActivity.this.show_ad(str3, str4);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            } catch (Exception unused) {
                MainActivity mainActivity2 = MainActivity.this;
                new GridAsyncTask(mainActivity2).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, MainActivity.this.getResources().getString(R.string.main_grid_images_link));
            }
        }
    }*/


   /* public void show_ad(String str, String str2) {
        final Dialog dialog = new Dialog(this);
        try {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        } catch ( Exception unused) {
        }
        dialog.requestWindowFeature(1);
        if (this.sharedPreferences.getBoolean(getResources().getString(R.string.dark_mode_pref), false)) {
            dialog.setContentView(R.layout.small_ad_dialoug_dark);
        } else {
            dialog.setContentView(R.layout.small_ad_dialoug);
        }
        ((TextView) dialog.findViewById(R.id.apptitle)).setText(str);
        ((TextView) dialog.findViewById(R.id.appdesc)).setText(str2);
        ((Button) dialog.findViewById(R.id.download)).setOnClickListener(new View.OnClickListener() { // from class: com.softdroid.fake.call.MainActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                try {
                    MainActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + MainActivity.this.packageName)));
                } catch (Exception unused2) {
                    MainActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.map/store/apps/details?id=" + MainActivity.this.packageName)));
                }
                dialog.dismiss();
            }
        });
        ((ImageView) dialog.findViewById(R.id.ad_image)).setImageDrawable(this.drawable);
        ((RelativeLayout) dialog.findViewById(R.id.mymain)).setOnClickListener(new View.OnClickListener() { // from class: com.softdroid.fake.call.MainActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                try {
                    MainActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + MainActivity.this.packageName)));
                } catch (Exception unused2) {
                    MainActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.map/store/apps/details?id=" + MainActivity.this.packageName)));
                }
                dialog.dismiss();
            }
        });
        ((ImageView) dialog.findViewById(R.id.close)).setOnClickListener(new View.OnClickListener() { // from class: com.softdroid.fake.call.MainActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
        new GridAsyncTask(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getResources().getString(R.string.main_grid_images_link));
    }*/

    private void before_loading_ads() {
        ImageLoader.getInstance().init(new ImageLoaderConfiguration.Builder(getApplicationContext()).defaultDisplayImageOptions(new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).imageScaleType(ImageScaleType.EXACTLY).bitmapConfig(Bitmap.Config.ARGB_8888).displayer(new FadeInBitmapDisplayer(Service.BIND_AUTO_CREATE)).build()).memoryCache(new WeakMemoryCache()).memoryCacheSizePercentage(13).diskCache(new UnlimitedDiskCache(StorageUtils.getCacheDirectory(this))).threadPriority(3).threadPoolSize(3).discCacheSize(104857600).discCacheExtraOptions(480, 800, null).diskCacheFileCount(100).diskCacheFileNameGenerator(new HashCodeFileNameGenerator()).imageDownloader(new BaseImageDownloader(this)).imageDecoder(new BaseImageDecoder(true)).defaultDisplayImageOptions(DisplayImageOptions.createSimple()).writeDebugLogs().build());
    }

    private void load_adaptive_Banner() {
        MobileAds.initialize(this, new OnInitializationCompleteListener() { // from class: com.softdroid.fake.call.MainActivity.5
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
        this.adaptive_adView.setAdListener(new AdListener() { // from class: com.softdroid.fake.call.MainActivity.6
            @Override // com.google.android.gms.ads.AdListener
            public void onAdLoaded() {
                super.onAdLoaded();
                MainActivity.this.shimmerFrameLayout.setVisibility(View.GONE);
                MainActivity.this.shimmerFrameLayout.stopShimmer();
                Bundle bundle = new Bundle();
                bundle.putString("Main_Act_Banner_Ad_Loaded", "Main_Act_Banner_Ad_Loaded");
                //   FirebaseAnalytics.getInstance(MainActivity.this).logEvent("Main_Act_Banner_Ad_Loaded", bundle);
            }

            @Override // com.google.android.gms.ads.AdListener
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                MainActivity.this.shimmerFrameLayout.setVisibility(View.GONE);
                MainActivity.this.shimmerFrameLayout.stopShimmer();
                Bundle bundle = new Bundle();
                bundle.putString("Main_Act_Banner_Ad_Failed", "Main_Act_Banner_Ad_Failed");
                // FirebaseAnalytics.getInstance(MainActivity.this).logEvent("Main_Act_Banner_Ad_Failed", bundle);
            }

            @Override
            // com.google.android.gms.ads.AdListener, com.google.android.gms.internal.ads.zzve
            public void onAdClicked() {
                super.onAdClicked();
                Bundle bundle = new Bundle();
                bundle.putString("Main_Act_Banner_Ad_Clicked", "Main_Act_Banner_Ad_Clicked");
                //  FirebaseAnalytics.getInstance(MainActivity.this).logEvent("Main_Act_Banner_Ad_Clicked", bundle);
            }
        });
    }

    private AdSize getAdSize() {
        Display defaultDisplay = getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, (int) (((float) displayMetrics.widthPixels) / displayMetrics.density));
    }


    public void requestNewInterstitial() {
        this.mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice(getResources().getString(R.string.vicky_s8)).addTestDevice(test_mobile_white).addTestDevice("A86A0D556F68465C49063589837FCF98").build());
    }

    private void initlize_componenets() {
        schedule_call_txtview_remaining = (TextView) findViewById(R.id.schedule_call_txtview_remaining);
        cancel_shedule_call = (ImageView) findViewById(R.id.cancel_shedule_call);
        shedule_arrow = (ImageView) findViewById(R.id.shedule_arrow);
        schedule_call_image = (ImageView) findViewById(R.id.schedule_call_image);
        this.you_will_receive_call_after = (TextView) findViewById(R.id.you_will_receive_call_after);


        this.more_from_developer_txtview = (TextView) findViewById(R.id.more_from_developer_txtview);
        this.call_now_txtview = (TextView) findViewById(R.id.call_now_txtview);

        // this.back_btn = (ImageView) findViewById(R.id.back_btn);
        // this.menu_btn = (ImageView) findViewById(R.id.options);

        this.callnow = (LinearLayout) findViewById(R.id.call_now_layout);
        this.schedule = (LinearLayout) findViewById(R.id.schedule_call_layout);
        this.ringtone = (LinearLayout) findViewById(R.id.change_ringtone_layout);


        this.ivCallTheam = (ImageView) findViewById(R.id.ivCallTheam);
        this.ivCallerinfo = (ImageView) findViewById(R.id.ivCallerinfo);
        this.ivCallAudio = (ImageView) findViewById(R.id.ivCallAudio);
        this.ivCallHistory = (ImageView) findViewById(R.id.ivCallHistory);
        this.schedule_call_layout_main = (LinearLayout) findViewById(R.id.schedule_call_layout_main);
        this.settings_layout = (LinearLayout) findViewById(R.id.settings_layout);
        // this.top_actionbar_layout = (LinearLayout) findViewById(R.id.top_actionbar_layout);
        this.main_layout = (RelativeLayout) findViewById(R.id.main_layout);
        cancel_shedule_call.setOnClickListener(this);
        this.ivCallerinfo.setOnClickListener(this);
        this.callnow.setOnClickListener(this);
        this.schedule.setOnClickListener(this);
        this.ringtone.setOnClickListener(this);
        this.ivCallAudio.setOnClickListener(this);
        this.ivCallHistory.setOnClickListener(this);
        this.ivCallTheam.setOnClickListener(this);
        // this.menu_btn.setOnClickListener(this);
        this.settings_layout.setOnClickListener(this);
        // this.back_btn.setOnClickListener(this);
        this.schedule_call_layout_main.setOnClickListener(this);
        setthemedata();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void setthemedata() {
        if (this.sharedPreferences.getBoolean("first time", true)) {
            this.sharedPreferences.edit().putBoolean("custom_ring_selected", false).apply();
        }
        this.sharedPreferences.edit().putBoolean("first time", false).commit();
        int i = this.sharedPreferences.getInt(getResources().getString(R.string.call_screen_position), 1);
        if (i == R.id.back_btn) {
            onBackPressed();
        } else if (i != R.id.options) {
            switch (i) {
                case 1:
                    this.sharedPreferences.edit().putInt(getResources().getString(R.string.call_screen_position), 1).apply();
                    return;
                case 2:
                    this.sharedPreferences.edit().putInt(getResources().getString(R.string.call_screen_position), 2).apply();
                    return;
                case 3:
                    this.sharedPreferences.edit().putString(getResources().getString(R.string.start_color_pref), "#5fb76f").apply();
                    this.sharedPreferences.edit().putString(getResources().getString(R.string.mid_color_pref), "#2c986a").apply();
                    this.sharedPreferences.edit().putString(getResources().getString(R.string.end_color), "#9ec85e").apply();
                    this.sharedPreferences.edit().putInt(getResources().getString(R.string.call_screen_position), 3).apply();
                    return;
                case 4:
                    this.sharedPreferences.edit().putInt(getResources().getString(R.string.call_screen_position), 4).apply();
                    break;
                case 5:
                    break;
                case 6:
                    this.sharedPreferences.edit().putInt(getResources().getString(R.string.call_screen_position), 6).apply();
                    return;
                case 7:
                    this.sharedPreferences.edit().putInt(getResources().getString(R.string.call_screen_position), 7).apply();
                    return;
                case 8:
                    this.sharedPreferences.edit().putInt(getResources().getString(R.string.call_screen_position), 8).apply();
                    return;
                case 9:
                    this.sharedPreferences.edit().putString(getResources().getString(R.string.start_color_pref), "#5fb76f").apply();
                    this.sharedPreferences.edit().putString(getResources().getString(R.string.mid_color_pref), "#2c986a").apply();
                    this.sharedPreferences.edit().putString(getResources().getString(R.string.end_color), "#9ec85e").apply();
                    this.sharedPreferences.edit().putInt(getResources().getString(R.string.call_screen_position), 9).apply();
                    return;
                case 10:
                    this.sharedPreferences.edit().putInt(getResources().getString(R.string.call_screen_position), 10).apply();
                    return;
                case 11:
                    this.sharedPreferences.edit().putInt(getResources().getString(R.string.call_screen_position), 11).apply();
                    return;
                case 12:
                    this.sharedPreferences.edit().putInt(getResources().getString(R.string.call_screen_position), 12).apply();
                    return;
                default:
                    return;
            }
            this.sharedPreferences.edit().putInt(getResources().getString(R.string.call_screen_position), 5).apply();
        } else {
            //  pop_up_menu();
        }
    }

    private void getting_prefrence() {
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    }

    public void count_down_timer() {
        this.call_now_click = true;
        new CountDownTimer(4100, (long) 1000) { // from class: com.softdroid.fake.call.MainActivity.7
            @Override // android.os.CountDownTimer
            public void onTick(long j) {
                int i = j > 4000 ? 4 : j > 3000 ? 3 : j > 2000 ? 2 : j > 1000 ? 1 : 0;
                MainActivity.this.is_call_active = Shared.getInstance().getBooleanValueFromPreference(MainActivity.this.getResources().getString(R.string.call_is_active), false, MainActivity.this);
                MainActivity.this.you_will_receive_call_after.setText(MainActivity.this.getResources().getString(R.string.you_will_receive_call_in) + " " + i + " second(s)");
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                MainActivity.this.you_will_receive_call_after.setText("");
                MainActivity.this.call_now_click = false;
                if (!Utils.fake_call_actvie && !MainActivity.this.is_call_active.booleanValue()) {
                    switch (MainActivity.this.sharedPreferences.getInt(MainActivity.this.getResources().getString(R.string.call_screen_position), 1)) {
                        case 0:
                            MainActivity.this.startActivity(new Intent(MainActivity.this, Call_Screen_Blurry_Dark.class));
                            break;
                        case 1:
                            MainActivity.this.startActivity(new Intent(MainActivity.this, Dark_blue.class));
                            break;
                        case 2:
                            MainActivity.this.startActivity(new Intent(MainActivity.this, Call_Screen_A51.class));
                            break;
                        case 3:
                            MainActivity.this.startActivity(new Intent(MainActivity.this, Call_screen_HTC_1.class));
                            break;
                        case 4:
                            MainActivity.this.startActivity(new Intent(MainActivity.this, Call_now_Samsung_S5.class));
                            break;
                        case 5:
                            MainActivity.this.startActivity(new Intent(MainActivity.this, Call_Now_Huawei_Mate.class));
                            break;
                        case 6:
                            MainActivity.this.startActivity(new Intent(MainActivity.this, Ice_White.class));
                            break;
                        case 7:
                            MainActivity.this.startActivity(new Intent(MainActivity.this, Mid_red.class));
                            break;
                    }
                    Utils.fake_call_actvie = true;
                }
                MainActivity.this.callnow.setEnabled(true);
                MainActivity.this.call_now_txtview.setBackground(MainActivity.this.getResources().getDrawable(R.drawable.receive_fake_bg));
                MainActivity.this.call_now_txtview.setTextColor(MainActivity.this.getResources().getColor(R.color.white));
            }
        }.start();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            /* case R.id.back_btn *//* 2131230813 *//*:
                onBackPressed();
                return;*/
            case R.id.call_now_layout /* 2131230841 */:
                Utils.fake_call_actvie = false;
                if (ContextCompat.checkSelfPermission(this, "android.permission.READ_PHONE_STATE") != 0) {
                    marshmallow_permission_phone();
                    return;
                }
                this.callnow.setEnabled(false);
                this.call_now_txtview.setBackground(getResources().getDrawable(R.drawable.receive_fake_bg_disable));
                this.call_now_txtview.setTextColor(getResources().getColor(R.color.receive_call_disable_text_color));
                if (!this.call_now_click.booleanValue()) {
                    count_down_timer();
                    return;
                }
                return;
            case R.id.ivCallTheam:
                startActivity(new Intent(this, Call_Themes.class));
                return;
            case R.id.ivCallerinfo:
                if (this.mInterstitialAd.isLoaded()) {
                    this.mInterstitialAd.show();
                    Bundle bundle = new Bundle();
                    bundle.putString("Main_Act_Intrstal_Ad_show_caller", "Main_Act_Intrstal_Ad_show_caller");

                } else {
                    requestNewInterstitial();
                    startActivity(new Intent(this, Caller.class));
                }
                this.mInterstitialAd.setAdListener(new AdListener() { // from class: com.softdroid.fake.call.MainActivity.8
                    @Override
                    public void onAdClosed() {
                        super.onAdClosed();
                        MainActivity.this.requestNewInterstitial();
                        MainActivity.this.startActivity(new Intent(MainActivity.this, Caller.class));
                        Bundle bundle2 = new Bundle();
                        bundle2.putString("Main_Act_Intrstal_Ad_closed_caller", "Main_Act_Intrstal_Ad_closed_caller");

                    }

                    @Override

                    public void onAdClicked() {
                        super.onAdClicked();
                        Bundle bundle2 = new Bundle();
                        bundle2.putString("Main_Act_Intrstal_Ad_clck_caller_layout", "Main_Act_Intrstal_Ad_clck_caller_layout");

                    }
                });
                return;
            case R.id.cancel_shedule_call :
                stopService(new Intent(this, MyService.class));
                cancel_shedule_call.setVisibility(View.GONE);
                shedule_arrow.setVisibility(View.VISIBLE);
                schedule_call_image.setVisibility(View.VISIBLE);
                schedule_call_txtview_remaining.setText(getResources().getString(R.string.schedule_call));
                return;
            case R.id.change_ringtone_layout :
                startActivity(new Intent(this, Change_Ringtone.class));
                return;
            case R.id.g1_layout /* 2131230950 */:
                try {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + gridPackage1)));
                    return;
                } catch (Exception unused) {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.map/store/apps/details?id=" + gridPackage1)));
                    return;
                }
            case R.id.g2_layout :
                try {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + gridPackage2)));
                    return;
                } catch (Exception unused2) {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.map/store/apps/details?id=" + gridPackage2)));
                    return;
                }
            case R.id.g3_layout :
                try {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + gridPackage3)));
                    return;
                } catch (Exception unused3) {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.map/store/apps/details?id=" + gridPackage3)));
                    return;
                }
                /*   case R.id.options *//* 2131231049 *//*:
                //pop_up_menu();
                return;*/
            case R.id.ivCallAudio:
                startActivity(new Intent(this, Record_Audio.class));
                return;
            case R.id.ivCallHistory:
                startActivity(new Intent(this, Schedule_History_Act.class));
                return;
            case R.id.schedule_call_layout /* 2131231117 */:
                checkPermission();
                return;
            case R.id.settings_layout /* 2131231147 */:
                startActivity(new Intent(this, Setting_Act.class));
                return;
            default:
                return;
        }
    }

    private void ringtone_dialog() {
        final Dialog dialog = new Dialog(this);
        try {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        } catch (Exception unused) {
        }
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.activity_change__ringtone);
        ((LinearLayout) dialog.findViewById(R.id.select_ringtone)).setOnClickListener(new View.OnClickListener() { // from class: com.softdroid.fake.call.MainActivity.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT > 21) {
                    MainActivity.this.mypermissions();
                } else {
                    MainActivity.this.startActivityForResult(Intent.createChooser(new Intent("android.intent.action.PICK", MediaStore.Audio.Media.EXTERNAL_CONTENT_URI), ""), 1);
                }
                dialog.dismiss();
            }
        });
        ((LinearLayout) dialog.findViewById(R.id.set_default_ringtone)).setOnClickListener(new View.OnClickListener() { // from class: com.softdroid.fake.call.MainActivity.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity.this.sharedPreferences.edit().putBoolean("custom_ring_selected", false).apply();
                Toast.makeText(MainActivity.this, "Default Ringtone Selected", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    public void mypermissions() {
        if (ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            marshmallow_permissions_WRITE_EXTERNAL_STORAGE();
        } else if (ContextCompat.checkSelfPermission(this, "android.permission.READ_PHONE_STATE") != 0) {
            marshmallow_permission_phone();
        } else {
            startActivityForResult(Intent.createChooser(new Intent("android.intent.action.PICK", MediaStore.Audio.Media.EXTERNAL_CONTENT_URI), ""), 1);
        }
    }

    private void marshmallow_permission_phone() {
        if (ContextCompat.checkSelfPermission(this, "android.permission.READ_PHONE_STATE") == 0) {
            return;
        }
        if (getFromPref(this, "ALLOWED").booleanValue()) {
            showSettingsAlert();
        } else if (ContextCompat.checkSelfPermission(this, "android.permission.READ_PHONE_STATE") == 0) {
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.READ_PHONE_STATE")) {
                ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_PHONE_STATE"}, MY_PERMISSIONS_REQUEST_PHONE);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_PHONE_STATE"}, MY_PERMISSIONS_REQUEST_PHONE);
            }
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
        create.setMessage("App needs to access this Permissions. In order to work properly");
        create.setButton(-2, "DONT ALLOW", new DialogInterface.OnClickListener() { // from class: com.softdroid.fake.call.MainActivity.11
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        create.setButton(-1, "ALLOW", new DialogInterface.OnClickListener() { // from class: com.softdroid.fake.call.MainActivity.12
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                MainActivity.this.mypermissions();
            }
        });
        create.show();
    }

    @Override

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i != 300) {
            if (i == 320) {
                if (iArr.length <= 0 || iArr[0] != 0) {
                    showSettingsAlert();
                }
            }
        } else if (iArr.length <= 0 || iArr[0] != 0) {
            showSettingsAlert();
        } else {
            mypermissions();
        }
    }

   /* public void pop_up_menu() {
        PopupMenu popupMenu = new PopupMenu(this, this.menu_btn);
        popupMenu.getMenuInflater().inflate(R.menu.menu_setting, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() { // from class: com.softdroid.fake.call.MainActivity.13
            @Override // android.widget.PopupMenu.OnMenuItemClickListener
            public boolean onMenuItemClick(MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.like_btn) {
                    MainActivity.this.show_rating_dialoug();
                    return true;
                } else if (itemId == R.id.more_free_apps) {
                    try {
                        MainActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://search?q=pub:" + MainActivity.this.getResources().getString(R.string.account_name))));
                    } catch (ActivityNotFoundException unused) {
                        MainActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/developer?id=" + MainActivity.this.getResources().getString(R.string.account_name))));
                    }
                    return true;
                } else if (itemId != R.id.privacy) {
                    return true;
                } else {
                    MainActivity.this.privacy_policy();
                    return true;
                }
            }
        });
        popupMenu.show();
    }*/

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
        if (this.sharedPreferences.getBoolean(getResources().getString(R.string.dark_mode_pref), false)) {
            create.setView(layoutInflater.inflate(R.layout.privacy_policy_dark, (ViewGroup) null));
        } else {
            create.setView(layoutInflater.inflate(R.layout.privacy_policy, (ViewGroup) null));
        }
        create.setButton(-1, "Close", new DialogInterface.OnClickListener() { // from class: com.softdroid.fake.call.MainActivity.14
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
        } catch (Exception unused) {
        }
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.rating_dialoug);
        final RatingBar ratingBar = (RatingBar) dialog.findViewById(R.id.rating_bar);
        ratingBar.setNumStars(5);
        ((Button) dialog.findViewById(R.id.submit)).setOnClickListener(new View.OnClickListener() { // from class: com.softdroid.fake.call.MainActivity.15
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ratingBar.getRating() <= 3.0f) {
                    MainActivity.this.sendFeedback();
                    dialog.dismiss();
                    return;
                }
                String packageName = MainActivity.this.getPackageName();
                try {
                    MainActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + packageName)));
                } catch (ActivityNotFoundException unused2) {
                    MainActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + packageName)));
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

    private void setSchedule_dialoug() {
        final Dialog dialog = new Dialog(this);
        try {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        } catch (Exception unused) {
        }
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.schedule_dialoug);
        ((LinearLayout) dialog.findViewById(R.id.ten_sec)).setOnClickListener(new View.OnClickListener() { // from class: com.softdroid.fake.call.MainActivity.16
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity.this.stopService(new Intent(MainActivity.this, MyService.class));
                MainActivity.this.sharedPreferences.edit().putInt("milliseconds", 10000).apply();
                Utils.millisecs = 10000;
                MainActivity.this.startService(new Intent(MainActivity.this, MyService.class));
                Toast.makeText(MainActivity.this, "You will receive a call in 10 Seconds !", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
        ((LinearLayout) dialog.findViewById(R.id.thirty_sec)).setOnClickListener(new View.OnClickListener() { // from class: com.softdroid.fake.call.MainActivity.17
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity.this.stopService(new Intent(MainActivity.this, MyService.class));
                Utils.millisecs = 30000;
                MainActivity.this.sharedPreferences.edit().putInt("milliseconds", 30000).apply();
                MainActivity.this.startService(new Intent(MainActivity.this, MyService.class));
                Toast.makeText(MainActivity.this, "You will receive a call in 30 Seconds !", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
        ((LinearLayout) dialog.findViewById(R.id.one_min)).setOnClickListener(new View.OnClickListener() { // from class: com.softdroid.fake.call.MainActivity.18
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity.this.stopService(new Intent(MainActivity.this, MyService.class));
                Utils.millisecs = 60000;
                MainActivity.this.sharedPreferences.edit().putInt("milliseconds", 60000).apply();
                MainActivity.this.startService(new Intent(MainActivity.this, MyService.class));
                Toast.makeText(MainActivity.this, "You will receive a call in 1 Minute!", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
        ((LinearLayout) dialog.findViewById(R.id.five_min)).setOnClickListener(new View.OnClickListener() { // from class: com.softdroid.fake.call.MainActivity.19
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity.this.stopService(new Intent(MainActivity.this, MyService.class));
                Utils.millisecs = 300000;
                MainActivity.this.sharedPreferences.edit().putInt("milliseconds", 300000).apply();
                MainActivity.this.startService(new Intent(MainActivity.this, MyService.class));
                Toast.makeText(MainActivity.this, "You will receive a call in 5 Minutes!", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
        ((LinearLayout) dialog.findViewById(R.id.fifteen_min)).setOnClickListener(new View.OnClickListener() { // from class: com.softdroid.fake.call.MainActivity.20
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity.this.stopService(new Intent(MainActivity.this, MyService.class));
                Utils.millisecs = 900000;
                MainActivity.this.sharedPreferences.edit().putInt("milliseconds", 900000).apply();
                MainActivity.this.startService(new Intent(MainActivity.this, MyService.class));
                Toast.makeText(MainActivity.this, "You will receive a call in 15 Minutes!", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
        ((LinearLayout) dialog.findViewById(R.id.thirty_min)).setOnClickListener(new View.OnClickListener() { // from class: com.softdroid.fake.call.MainActivity.21
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity.this.stopService(new Intent(MainActivity.this, MyService.class));
                Utils.millisecs = 1800000;
                MainActivity.this.sharedPreferences.edit().putInt("milliseconds", 1800000).apply();
                MainActivity.this.startService(new Intent(MainActivity.this, MyService.class));
                Toast.makeText(MainActivity.this, "You will receive a call in 30 Minutes!", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1 && i2 == -1) {
            try {
                Uri data = intent.getData();
                this.audioFileUri = data;
                data.getPath();
                RingtoneManager.getRingtone(this, (Uri) intent.getParcelableExtra("android.intent.extra.ringtone.PICKED_URI")).getTitle(this);
                Toast.makeText(getApplicationContext(), "Ringtone Selected ", Toast.LENGTH_SHORT).show();
                this.sharedPreferences.edit().putBoolean("custom_ring_selected", true).apply();
                this.sharedPreferences.edit().putString("ringtone_path", this.audioFileUri.toString()).apply();
            } catch (Exception unused) {
            }
        }
        if (i == ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE && Build.VERSION.SDK_INT > 28 && !Settings.canDrawOverlays(this)) {
            checkPermission();
        }
        super.onActivityResult(i, i2, intent);
    }

    public void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, "android.permission.READ_PHONE_STATE") != 0) {
            marshmallow_permission_phone();
        } else if (Build.VERSION.SDK_INT <= 28) {
            setSchedule_dialoug();
        } else if (!Settings.canDrawOverlays(this)) {
            window_permission();
        } else {
            setSchedule_dialoug();
        }
    }

    private void window_permission() {
        final Dialog dialog = new Dialog(this);
        try {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        } catch (Exception unused) {
        }
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.alert_permission);
        ((TextView) dialog.findViewById(R.id.allow)).setOnClickListener(new View.OnClickListener() { // from class: com.softdroid.fake.call.MainActivity.22
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity.this.startActivityForResult(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + MainActivity.this.getPackageName())), MainActivity.ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE);
                dialog.dismiss();
            }
        });
        ((TextView) dialog.findViewById(R.id.cancle)).setOnClickListener(new View.OnClickListener() { // from class: com.softdroid.fake.call.MainActivity.23
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
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
        create.setButton(-1, "Close", new DialogInterface.OnClickListener() { // from class: com.softdroid.fake.call.MainActivity.24
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        create.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (this.sharedPreferences.getBoolean(getResources().getString(R.string.dark_mode_pref), false)) {
            //    this.top_actionbar_layout.setBackgroundColor(getResources().getColor(R.color.top_bar_dark));
            this.main_layout.setBackgroundResource(R.drawable.background);
            schedule_call_txtview_remaining.setTextColor(getResources().getColor(R.color.text_color_dark));


            this.ivCallTheam.setBackgroundResource(R.drawable.call_themes);
            this.ivCallAudio.setBackgroundResource(R.drawable.call_audio);
            this.ivCallHistory.setBackgroundResource(R.drawable.call_history);
            this.schedule_call_layout_main.setBackgroundResource(R.mipmap.main_activity_btn_dark);


            this.you_will_receive_call_after.setTextColor(getResources().getColor(R.color.text_color_dark));
        } else {

            this.main_layout.setBackgroundResource(R.drawable.background);
            schedule_call_txtview_remaining.setTextColor(getResources().getColor(R.color.main_textview_color));
            this.ivCallerinfo.setBackgroundResource(R.drawable.caller_info);
            this.ivCallTheam.setBackgroundResource(R.drawable.call_themes);
            this.ivCallAudio.setBackgroundResource(R.drawable.call_audio);
            this.ivCallHistory.setBackgroundResource(R.drawable.call_history);
            this.schedule_call_layout_main.setBackgroundResource(R.drawable.round_corner);


            this.you_will_receive_call_after.setTextColor(getResources().getColor(R.color.main_textview_color));
        }
        this.appUpdateManager.getAppUpdateInfo().addOnSuccessListener(new OnSuccessListener<AppUpdateInfo>() { // from class: com.softdroid.fake.call.MainActivity.25
            @SuppressLint("WrongConstant")
            public void onSuccess(AppUpdateInfo appUpdateInfo) {
                if (appUpdateInfo.updateAvailability() == 3) {
                    try {
                        MainActivity.this.appUpdateManager.startUpdateFlowForResult(appUpdateInfo, AppUpdateType.IMMEDIATE, MainActivity.this, 110);
                    } catch (IntentSender.SendIntentException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        try {

        } catch (Exception unused) {
        }
        if (this.mInterstitialAd.isLoaded()) {
            this.mInterstitialAd.show();
            Bundle bundle = new Bundle();
            bundle.putString("Main_Act_Intrstal_Ad_show_back_prsd", "Main_Act_Intrstal_Ad_show_back_prsd");
            // FirebaseAnalytics.getInstance(this).logEvent("Main_Act_Intrstal_Ad_show_back_prsd", bundle);
        } else {
            startActivity(new Intent(this, Exit_Act.class));
            finish();
        }
        this.mInterstitialAd.setAdListener(new AdListener() { // from class: com.softdroid.fake.call.MainActivity.26
            @Override // com.google.android.gms.ads.AdListener
            public void onAdClosed() {
                super.onAdClosed();
                MainActivity.this.startActivity(new Intent(MainActivity.this, Exit_Act.class));
                Bundle bundle2 = new Bundle();
                bundle2.putString("Main_Act_Intrstal_Ad_Closed_back_prsd", "Main_Act_Intrstal_Ad_Closed_back_prsd");
                //  FirebaseAnalytics.getInstance(MainActivity.this).logEvent("Main_Act_Intrstal_Ad_Closed_back_prsd", bundle2);
                MainActivity.this.finish();
            }

            @Override
            // com.google.android.gms.ads.AdListener, com.google.android.gms.internal.ads.zzve
            public void onAdClicked() {
                super.onAdClicked();
                Bundle bundle2 = new Bundle();
                bundle2.putString("Main_Act_Intrstal_Ad_Clicked_back_prsd", "Main_Act_Intrstal_Ad_Clicked_back_prsd");
                // FirebaseAnalytics.getInstance(MainActivity.this).logEvent("Main_Act_Intrstal_Ad_Clicked_back_prsd", bundle2);
            }
        });
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
