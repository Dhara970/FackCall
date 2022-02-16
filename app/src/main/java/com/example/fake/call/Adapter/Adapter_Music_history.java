package com.example.fake.call.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;
//import android.support.v4.media.session.PlaybackStateCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.fake.call.Activities.Record_Audio;
import com.example.fake.call.Model.Model_Audio;
import com.example.fake.call.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class Adapter_Music_history extends RecyclerView.Adapter<Adapter_Music_history.ViewHolder> {
    public static MediaPlayer mediaPlayer;
    Activity activity;
    ArrayList<Model_Audio> al_video;
    ArrayList<String> arrayList;
    Context context;
    Boolean dark_mode;
    String[] files;
    ViewHolder holder;
    String mypaths;
    String[] paths;
    String[] pathz;
    int path = 0;
    int i = 0;
    String[] getpath = new String[4];
    private List<Model_Audio> appArrayList = this.appArrayList;
   // private List<Model_Audio> appArrayList = this.appArrayList;

    public Adapter_Music_history(Context context, ArrayList<Model_Audio> arrayList, Activity activity, Boolean bool) {
        ArrayList<String> arrayList2 = new ArrayList<>();
        this.arrayList = arrayList2;
        this.al_video = arrayList;
        this.context = context;
        this.activity = activity;
        this.arrayList = arrayList2;
        this.dark_mode = bool;
    }

   
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public static int progressStatus;
        LinearLayout delete;
        ViewGroup hiddenPanel;
        public ImageView iv_image;
        TextView music_name;
        TextView music_size;
        SharedPreferences mysharedPreference;
        RelativeLayout rl_select;
        RotateAnimation rotate;
        public ImageView tick;

        public ViewHolder(View view) {
            super(view);
            this.iv_image = (ImageView) view.findViewById(R.id.iv_image);
            this.delete = (LinearLayout) view.findViewById(R.id.bg_layout);
            this.tick = (ImageView) view.findViewById(R.id.tick);
            this.rl_select = (RelativeLayout) view.findViewById(R.id.rl_select);
            this.music_name = (TextView) view.findViewById(R.id.music_name);
            this.hiddenPanel = (ViewGroup) view.findViewById(R.id.hidden_panel);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view;
        if (this.dark_mode.booleanValue()) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_audio_history_dark, viewGroup, false);
        } else {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_audio_history, viewGroup, false);
        }
        return new ViewHolder(view);
    }

    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        this.al_video.get(i);
        this.holder = viewHolder;
        viewHolder.mysharedPreference = PreferenceManager.getDefaultSharedPreferences(this.context);
        viewHolder.music_name.setText(this.al_video.get(i).getAudio_video_name());
        if (viewHolder.mysharedPreference.getBoolean(this.context.getResources().getString(R.string.stop_media_player_pref), false)) {
            stopPlaying(i);
            viewHolder.mysharedPreference.edit().putBoolean(this.context.getResources().getString(R.string.stop_media_player_pref), false).apply();
        }
        if (viewHolder.mysharedPreference.getString(this.context.getResources().getString(R.string.audio_path_pref), "").equalsIgnoreCase(this.al_video.get(i).getStr_path())) {
            this.al_video.get(i).set_checked(true);
        }
        if (this.al_video.get(i).is_checked()) {
            if (this.dark_mode.booleanValue()) {
                viewHolder.delete.setBackgroundResource(R.drawable.ad_bg_selected_dark);
            } else {
                viewHolder.delete.setBackgroundResource(R.drawable.ad_bg_selected);
            }
            viewHolder.tick.setVisibility(View.VISIBLE);
        } else {
            viewHolder.delete.setBackgroundResource(R.drawable.ad_bg_new);
            viewHolder.tick.setVisibility(View.GONE);
        }
        if (this.al_video.get(i).is_playing()) {
            viewHolder.iv_image.setImageResource(R.mipmap.pause);
        } else {
            viewHolder.iv_image.setImageResource(R.mipmap.play);
        }
        viewHolder.iv_image.setOnClickListener(new View.OnClickListener() { // from class: com.softdroid.fake.call.Adapter.Adapter_Music_history.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Record_Audio.recording.booleanValue()) {
                    Toast.makeText(Adapter_Music_history.this.context, Adapter_Music_history.this.context.getResources().getString(R.string.please_stop_rec_before_playing),  Toast.LENGTH_LONG).show();
                    return;
                }
                new File(Adapter_Music_history.this.al_video.get(i).getStr_path());
                Adapter_Music_history.this.stopPlaying(i);
                for (int i2 = 0; i2 < Adapter_Music_history.this.al_video.size(); i2++) {
                    if (!Adapter_Music_history.this.al_video.get(i).is_playing()) {
                        Adapter_Music_history.this.al_video.get(i2).set_playing(false);
                    }
                }
                if (!Adapter_Music_history.this.al_video.get(i).is_playing()) {
                    Adapter_Music_history.this.al_video.get(i).set_playing(true);
                    Adapter_Music_history adapter_Music_history = Adapter_Music_history.this;
                    adapter_Music_history.initMediaPlayer(adapter_Music_history.al_video.get(i).getStr_path(), i);
                } else {
                    Adapter_Music_history.this.al_video.get(i).set_playing(false);
                }
                Adapter_Music_history.this.notifyDataSetChanged();
            }
        });
        viewHolder.delete.setOnClickListener(new View.OnClickListener() { // from class: com.softdroid.fake.call.Adapter.Adapter_Music_history.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                for (int i2 = 0; i2 < Adapter_Music_history.this.al_video.size(); i2++) {
                    Adapter_Music_history.this.al_video.get(i2).set_checked(false);
                }
                Adapter_Music_history.this.al_video.get(i).set_checked(true);
                viewHolder.mysharedPreference.edit().putString(Adapter_Music_history.this.context.getResources().getString(R.string.audio_path_pref), Adapter_Music_history.this.al_video.get(i).getStr_path()).apply();
                Adapter_Music_history.this.notifyDataSetChanged();
                Toast.makeText(Adapter_Music_history.this.context, "Recording Selected",  Toast.LENGTH_LONG).show();
            }
        });
        viewHolder.delete.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.softdroid.fake.call.Adapter.Adapter_Music_history.3
            @SuppressLint("ResourceType")
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                new AlertDialog.Builder(Adapter_Music_history.this.activity).setTitle("Delete Recording !").setMessage("Are you sure you want to delete this recording ?").setPositiveButton(17039379, new DialogInterface.OnClickListener() { // from class: com.softdroid.fake.call.Adapter.Adapter_Music_history.3.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        try {
                            new File(Adapter_Music_history.this.al_video.get(i).getStr_path()).delete();
                            Adapter_Music_history.this.stopPlaying(i);
                            Toast.makeText(Adapter_Music_history.this.context, "Recording Deleted",  Toast.LENGTH_LONG).show();
                            Adapter_Music_history.this.al_video.remove(Adapter_Music_history.this.al_video.get(i));
                            if (Adapter_Music_history.this.al_video.size() < 1) {
                                Record_Audio.recording_availbe_or_not_txtview.setVisibility(View.VISIBLE);
                                Record_Audio.record_availble.setVisibility(View.GONE);
                            }
                            Adapter_Music_history.this.notifyDataSetChanged();
                        } catch (IndexOutOfBoundsException unused) {
                            Toast.makeText(Adapter_Music_history.this.context, "opps somehting went wrong..",  Toast.LENGTH_LONG).show();
                        } catch (Exception unused2) {
                        }
                    }
                }).setNegativeButton(17039369, (DialogInterface.OnClickListener) null).show();
                return false;
            }
        });
    }

    
    public void stopPlaying(int i) {
        try {
            MediaPlayer mediaPlayer2 = mediaPlayer;
            if (mediaPlayer2 != null) {
                mediaPlayer2.stop();
                mediaPlayer.release();
                mediaPlayer = null;
                notifyDataSetChanged();
            }
        } catch ( Exception unused) {
        }
    }

    
    public void initMediaPlayer(String str, final int i) {
        MediaPlayer mediaPlayer2 = new MediaPlayer();
        mediaPlayer = mediaPlayer2;
        try {
            mediaPlayer2.stop();
        } catch (Exception unused) {
        }
        mediaPlayer.reset();
        mediaPlayer.setAudioStreamType(3);
        try {
            mediaPlayer.setDataSource(str);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.softdroid.fake.call.Adapter.Adapter_Music_history.4
            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mediaPlayer3) {
                try {
                    Adapter_Music_history.this.al_video.get(i).set_playing(false);
                } catch ( Exception unused2) {
                }
                try {
                    Adapter_Music_history.this.notifyDataSetChanged();
                } catch (Exception unused3) {
                }
            }
        });
        mediaPlayer.start();
    }

/*    private String getHumanReadableSize(long j) {
        if (j < PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) {
            return j + " Bytes";
        }
        double d = (double) j;
        if (d < Math.pow(1024.0d, 2.0d)) {
            return "" + (j / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) + " KB";
        }
        if (d < Math.pow(1024.0d, 3.0d)) {
            double pow = Math.pow(1024.0d, 2.0d);
            Double.isNaN(d);
            return new DecimalFormat("#.##").format(d / pow) + " MB";
        }
        double pow2 = Math.pow(1024.0d, 3.0d);
        Double.isNaN(d);
        return new DecimalFormat("#.##").format(d / pow2) + " GB";
    }*/

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.al_video.size();
    }
}
