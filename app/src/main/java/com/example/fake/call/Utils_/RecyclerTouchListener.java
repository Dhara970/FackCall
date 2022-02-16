package com.example.fake.call.Utils_;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes2.dex */
public class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {
    private ClickListener clicklistener;
    private GestureDetector gestureDetector;


    public interface ClickListener {
        void onClick(View view, int i);

        void onLongClick(View view, int i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public void onRequestDisallowInterceptTouchEvent(boolean z) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
    }

    public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
        this.clicklistener = clickListener;
        this.gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() { // from class: com.softdroid.fake.call.Utils_.RecyclerTouchListener.1
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                return true;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public void onLongPress(MotionEvent motionEvent) {
                ClickListener clickListener2;
                View findChildViewUnder = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                if (findChildViewUnder != null && (clickListener2 = clickListener) != null) {
                    clickListener2.onLongClick(findChildViewUnder, recyclerView.getChildAdapterPosition(findChildViewUnder));
                }
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        View findChildViewUnder = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
        if (findChildViewUnder == null || this.clicklistener == null || !this.gestureDetector.onTouchEvent(motionEvent)) {
            return false;
        }
        this.clicklistener.onClick(findChildViewUnder, recyclerView.getChildAdapterPosition(findChildViewUnder));
        return false;
    }
}
