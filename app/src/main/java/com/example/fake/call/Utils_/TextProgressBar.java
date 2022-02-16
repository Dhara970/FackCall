package com.example.fake.call.Utils_;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/* loaded from: classes2.dex */
public class TextProgressBar extends ProgressBar {
    private String text = "0/100";
    private Paint textPaint;

    public TextProgressBar(Context context) {
        super(context);
        Paint paint = new Paint();
        this.textPaint = paint;
        paint.setColor(-1);
    }

    public TextProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Paint paint = new Paint();
        this.textPaint = paint;
        paint.setColor(-1);
    }

    public TextProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Paint paint = new Paint();
        this.textPaint = paint;
        paint.setColor(-16777216);
    }

    @Override // android.widget.ProgressBar, android.view.View
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect rect = new Rect();
        this.textPaint.setTextSize(30.0f);
        Paint paint = this.textPaint;
        String str = this.text;
        paint.getTextBounds(str, 0, str.length(), rect);
        canvas.drawText(this.text, (float) ((getWidth() / 2) - rect.centerX()), (float) ((getHeight() / 2) - rect.centerY()), this.textPaint);
    }

    public synchronized void setText(String str) {
        this.text = str;
        drawableStateChanged();
    }

    public void setTextColor(int i) {
        this.textPaint.setColor(i);
        drawableStateChanged();
    }
}
