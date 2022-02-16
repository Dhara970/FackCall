package com.example.fake.call.Adapter;

import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.viewpager.widget.PagerAdapter;
import com.bumptech.glide.Glide;
import com.example.fake.call.R;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class SlidingImage_Adapter extends PagerAdapter {
    public static ArrayList<Integer> IMAGES;
    private Context context;
    private ArrayList<String> imagesArray_names;
    private LayoutInflater inflater;
    Boolean is_dark_mode_enable;

    @Override // androidx.viewpager.widget.PagerAdapter
    public void restoreState(Parcelable parcelable, ClassLoader classLoader) {
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Parcelable saveState() {
        return null;
    }

    public SlidingImage_Adapter(Context context, ArrayList<Integer> arrayList, ArrayList<String> arrayList2, Boolean bool) {
        this.imagesArray_names = new ArrayList<>();
        this.context = context;
        IMAGES = arrayList;
        this.imagesArray_names = arrayList2;
        this.is_dark_mode_enable = bool;
        this.inflater = LayoutInflater.from(context);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }

    public void remove(int i) {
        IMAGES.remove(i);
        notifyDataSetChanged();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return IMAGES.size();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View inflate = this.inflater.inflate(R.layout.slidingimages_layout, viewGroup, false);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.image);
        ImageView imageView2 = (ImageView) inflate.findViewById(R.id.tick);
        TextView textView = (TextView) inflate.findViewById(R.id.name_of_theme);
        textView.setText(this.imagesArray_names.get(i));
        if (this.is_dark_mode_enable.booleanValue()) {
            textView.setTextColor(this.context.getResources().getColor(R.color.white));
        }
        Glide.with(this.context).load(IMAGES.get(i)).into(imageView);
        viewGroup.addView(inflate, 0);
        return inflate;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view.equals(obj);
    }
}
