package com.example.fake.call.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.fake.call.Model.Note;
import com.example.fake.call.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/* loaded from: classes2.dex */
public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> {
    private Context context;
    Boolean dark_mode;
    private List<Note> notesList;

   
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView call_time_txtview;
        public TextView contact_number_textview;
        public TextView date_n_time;
        CircleImageView image_of_caller;
        public TextView note;
        public TextView timestamp;

        public MyViewHolder(View view) {
            super(view);
            this.note = (TextView) view.findViewById(R.id.note);
            this.contact_number_textview = (TextView) view.findViewById(R.id.contact_number_textview);
            this.call_time_txtview = (TextView) view.findViewById(R.id.call_time_txtview);
            this.date_n_time = (TextView) view.findViewById(R.id.date_n_time);
            this.timestamp = (TextView) view.findViewById(R.id.timestamp);
            this.image_of_caller = (CircleImageView) view.findViewById(R.id.image_of_caller);
        }
    }

    public NotesAdapter(Context context, List<Note> list, Boolean bool) {
        this.context = context;
        this.notesList = list;
        this.dark_mode = bool;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.note_list_row, viewGroup, false));
    }

    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        try {
            Note note = this.notesList.get(i);
            myViewHolder.note.setText(note.getNote());
            myViewHolder.contact_number_textview.setText(note.getNumber());
            myViewHolder.call_time_txtview.setText(note.getTime());
            myViewHolder.date_n_time.setText(note.getTimestamp());
            myViewHolder.timestamp.setText(formatDate(note.getTimestamp()));
            if (this.dark_mode.booleanValue()) {
                myViewHolder.note.setTextColor(this.context.getResources().getColor(R.color.text_color_dark));
                myViewHolder.contact_number_textview.setTextColor(this.context.getResources().getColor(R.color.text_color_dark));
                myViewHolder.call_time_txtview.setTextColor(this.context.getResources().getColor(R.color.text_color_dark));
                myViewHolder.date_n_time.setTextColor(this.context.getResources().getColor(R.color.text_color_dark));
                myViewHolder.timestamp.setTextColor(this.context.getResources().getColor(R.color.text_color_dark));
            }
            String path = note.getPath();
            char c = 65535;
            switch (path.hashCode()) {
                case -2110980246:
                    if (path.equals("boyfriend")) {
                        c = 3;
                        break;
                    }
                    break;
                case -1326477025:
                    if (path.equals("doctor")) {
                        c = '\b';
                        break;
                    }
                    break;
                case -1109772796:
                    if (path.equals("lawyer")) {
                        c = '\t';
                        break;
                    }
                    break;
                case -918561344:
                    if (path.equals("president")) {
                        c = '\n';
                        break;
                    }
                    break;
                case -42172315:
                    if (path.equals("Tax Insurance")) {
                        c = '\r';
                        break;
                    }
                    break;
                case 99207:
                    if (path.equals("dad")) {
                        c = 4;
                        break;
                    }
                    break;
                case 108299:
                    if (path.equals("mom")) {
                        c = 2;
                        break;
                    }
                    break;
                case 3029869:
                    if (path.equals("boss")) {
                        c = 7;
                        break;
                    }
                    break;
                case 3649297:
                    if (path.equals("wife")) {
                        c = 6;
                        break;
                    }
                    break;
                case 106683528:
                    if (path.equals("pizza")) {
                        c = 0;
                        break;
                    }
                    break;
                case 109204045:
                    if (path.equals("santa")) {
                        c = '\f';
                        break;
                    }
                    break;
                case 1269934139:
                    if (path.equals("husband")) {
                        c = 5;
                        break;
                    }
                    break;
                case 1378624327:
                    if (path.equals("ronaldo")) {
                        c = 11;
                        break;
                    }
                    break;
                case 1636503610:
                    if (path.equals("girlfriend")) {
                        c = 1;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    Glide.with(this.context).load(Integer.valueOf((int) R.mipmap.pizza)).into(myViewHolder.image_of_caller);
                    return;
                case 1:
                    Glide.with(this.context).load(Integer.valueOf((int) R.mipmap.girlfriend)).into(myViewHolder.image_of_caller);
                    return;
                case 2:
                    Glide.with(this.context).load(Integer.valueOf((int) R.mipmap.mom)).into(myViewHolder.image_of_caller);
                    return;
                case 3:
                    Glide.with(this.context).load(Integer.valueOf((int) R.mipmap.boyfriend)).into(myViewHolder.image_of_caller);
                    return;
                case 4:
                    Glide.with(this.context).load(Integer.valueOf((int) R.mipmap.dad)).into(myViewHolder.image_of_caller);
                    return;
                case 5:
                    Glide.with(this.context).load(Integer.valueOf((int) R.mipmap.husband)).into(myViewHolder.image_of_caller);
                    return;
                case 6:
                    Glide.with(this.context).load(Integer.valueOf((int) R.mipmap.wife)).into(myViewHolder.image_of_caller);
                    return;
                case 7:
                    Glide.with(this.context).load(Integer.valueOf((int) R.mipmap.boss)).into(myViewHolder.image_of_caller);
                    return;
                case '\b':
                    Glide.with(this.context).load(Integer.valueOf((int) R.mipmap.doctor)).into(myViewHolder.image_of_caller);
                    return;
                case '\t':
                    Glide.with(this.context).load(Integer.valueOf((int) R.mipmap.lawyer)).into(myViewHolder.image_of_caller);
                    return;
                case '\n':
                    Glide.with(this.context).load(Integer.valueOf((int) R.mipmap.president)).into(myViewHolder.image_of_caller);
                    return;
                case 11:
                    Glide.with(this.context).load(Integer.valueOf((int) R.mipmap.ronaldo)).into(myViewHolder.image_of_caller);
                    return;
                case '\f':
                    Glide.with(this.context).load(Integer.valueOf((int) R.mipmap.santa)).into(myViewHolder.image_of_caller);
                    return;
                case '\r':
                    Glide.with(this.context).load(Integer.valueOf((int) R.mipmap.tax_insurance)).into(myViewHolder.image_of_caller);
                    return;
                default:
                    String path2 = note.getPath();
                    System.out.println("my pictures path=>  " + path2);
                    Glide.with(this.context).load(path2).into(myViewHolder.image_of_caller);
                    return;
            }
        } catch ( Exception unused) {
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.notesList.size();
    }

    private String formatDate(String str) {
        try {
            return new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(str));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
