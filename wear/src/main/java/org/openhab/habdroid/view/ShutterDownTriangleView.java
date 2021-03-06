package org.openhab.habdroid.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import org.openhab.habdroid.R;

/**
 * TODO: document your custom view class.
 */
public class ShutterDownTriangleView extends View {

    private static final String TAG = ShutterDownTriangleView.class.getSimpleName();

    private Paint mPaint;

    private Paint mTextPaint;

    public ShutterDownTriangleView(Context context) {
        super(context);
        init(null, 0);
    }

    public ShutterDownTriangleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public ShutterDownTriangleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public static float convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return px;
    }

    @Override
    public boolean isInEditMode() {
        return true;
    }

    private void init(AttributeSet attrs, int defStyle) {
        mPaint = new Paint();
        mPaint.setColor(getResources().getColor(R.color.light_gray));
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setAntiAlias(true);

        mTextPaint = new Paint();
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setStrokeWidth(2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Point left = new Point(10, 10);
        Point right = new Point(canvas.getWidth() - 10, 10);
        int measuredHeight = getMeasuredHeight();
        Log.d(TAG, "Measured height: " + measuredHeight);
        Point bottom = new Point(canvas.getWidth() / 2, measuredHeight - 10);
        Path trianglePath = new Path();
        trianglePath.setFillType(Path.FillType.EVEN_ODD);
        trianglePath.moveTo(left.x, left.y);
        trianglePath.lineTo(right.x, right.y);
        trianglePath.lineTo(bottom.x, bottom.y);
        trianglePath.lineTo(left.x, left.y);
        trianglePath.close();
        canvas.drawPath(trianglePath, mPaint);

        canvas.drawLine((canvas.getWidth() / 2) - 32, measuredHeight - 74, canvas.getWidth() / 2, measuredHeight - 42, mTextPaint);
        canvas.drawLine(canvas.getWidth() / 2, measuredHeight - 42, (canvas.getWidth() / 2) + 32, measuredHeight - 74, mTextPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        int desiredWidth = widthSize;
        int desiredHeight = (int) convertDpToPixel(110, getContext());
        Log.d(TAG, "DesiredHeight " + desiredHeight);

        //Measure Width
        if (widthMode == MeasureSpec.EXACTLY) {
            //Must be this size
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            width = Math.min(desiredWidth, widthSize);
        } else {
            //Be whatever you want
            width = desiredWidth;
        }

        //Measure Height
        if (heightMode == MeasureSpec.EXACTLY) {
            //Must be this size
            height = heightSize;
            Log.d(TAG, "Using EXACTLY heightSize ->" + height);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            height = Math.min(desiredHeight, heightSize);
            Log.d(TAG, "Using AT_MOST min(desired,heightSize ->" + height);
        } else {
            //Be whatever you want
            height = desiredHeight;
            Log.d(TAG, "Using ELSE desired ->" + height);
        }

        //MUST CALL THIS
        setMeasuredDimension(width, height);
    }
}
