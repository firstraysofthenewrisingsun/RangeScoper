package com.bcl.rangescoper;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.ViewCompat;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * TODO: document your custom view class.
 */
public class TargetView extends View {
    Paint bullsEyePaint = new Paint();
    Paint hitPaint = new Paint();
    private ArrayList<Hit> hits = new ArrayList<>();
    private float ringIncrement;
    Paint ringsPaint = new Paint();

    /* renamed from: x */
    private float f31x;

    /* renamed from: y */
    private float f32y;



    public TargetView(Context context) {
        super(context);

        int color = ContextCompat.getColor(context, R.color.black);
        int hitColor = ContextCompat.getColor(context, R.color.light_blue_400);
        this.ringsPaint.setColor(color);
        this.ringsPaint.setStyle(Paint.Style.STROKE);
        this.bullsEyePaint.setColor(color);
        this.bullsEyePaint.setStyle(Paint.Style.FILL);
        this.hitPaint.setColor(hitColor);
        this.hitPaint.setStyle(Paint.Style.FILL);
    }

    public TargetView(float f, float f2, float f3, Context context) {
        super(context);
        int color = ContextCompat.getColor(context, R.color.gray_600);
        int hitColor = ContextCompat.getColor(context, R.color.light_blue_400);
        this.f31x = f2;
        this.f32y = f3;
        this.ringIncrement = f / 6.0f;
        this.ringsPaint.setColor(color);
        this.ringsPaint.setStyle(Paint.Style.STROKE);
        this.bullsEyePaint.setColor(color);
        this.bullsEyePaint.setStyle(Paint.Style.FILL);
        this.hitPaint.setColor(hitColor);
        this.hitPaint.setStyle(Paint.Style.FILL);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        canvas.drawCircle(this.f31x, this.f32y, this.ringIncrement * 3.0f, this.ringsPaint);
        canvas.drawCircle(this.f31x, this.f32y, this.ringIncrement * 2.0f, this.ringsPaint);
        canvas.drawCircle(this.f31x, this.f32y, this.ringIncrement, this.bullsEyePaint);

        Iterator<Hit> it = this.hits.iterator();
        while (it.hasNext()) {
            Hit next = it.next();
            canvas.drawCircle(next.getX(), next.getY(), 10.0f, this.hitPaint);
        }
    }

    public void addHit(float f, float f2) {
        this.hits.add(new Hit(f, f2));
    }



}