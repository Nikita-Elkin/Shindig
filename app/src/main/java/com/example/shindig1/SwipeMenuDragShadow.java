package com.example.shindig1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

public class SwipeMenuDragShadow {

    public static final String TAG = "SwipeMenuDragShadow";

    private ViewGroup mLayout = null;
    private Bitmap mBitmap = null;
    private View mCurrentView = null;
    private ImageView mDragView = null;

    private float mX = 5;
    private float mTopY = 5;

    private float mMidX = 5;
    private float mMidY = 5;

    private float mInitialX = 100;
    private float mInitialY = 100;
    private float mDeltaX = 5;

    private float mLastX = 5;
    private float mLastY = 5;

    private boolean mDragging = false;
    public boolean mDragged = false;

    public SwipeMenuDragShadow(Context context, ViewGroup layout) {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);
        mTopY = 0;
        mMidX = metrics.widthPixels / 2;
        mMidY = metrics.heightPixels / 2;
        mLayout = layout;
    }

    public boolean isDragging() {
        return mDragging;
    }

    public void startDrag(View view, MotionEvent event, float initialX, float initialY) {
        if (view == null) return;
        if (mDragging) return;
        mCurrentView = view;
        mCurrentView.setVisibility(View.INVISIBLE);
        mCurrentView.invalidate();
        mBitmap = getBitmapFromView(view);
        mDragView = new ImageView(view.getContext());
        mDragView.setBackgroundColor(view.getContext().getResources().getColor(android.R.color.transparent));
        mDragView.setImageBitmap(mBitmap);
        mDragView.setX(mInitialX);
        mDragView.setY(mInitialY);
        mDragView.setTag(TAG);
        // check if already has child with tag, and if so, remove it
        View v = mLayout.findViewWithTag(TAG);
        if (v != null) {
            mLayout.removeView(v);
            Log.w(TAG, "@@@@ found artifact drag view and removed it @@@@");
        }
        mLayout.addView(mDragView, 0);
        mLayout.bringChildToFront(mDragView);
        //mLayout.invalidate();
        mDeltaX = event.getX() - initialX;
        mTopY = initialY;
        mInitialX = initialX;
        mInitialY = initialY;
        mDragged = true;
        mDragging = true;
    }

    public void stopDrag() {
        if (mCurrentView != null) {
            mCurrentView.setVisibility(View.VISIBLE);
            mCurrentView.invalidate();
        } else {
            Log.e(TAG, "mCurrentView == NULL!");
        }
        if (mDragView != null) {
            mDragView.setX(mInitialX);
            mDragView.setY(mInitialY);
            mDragView.setVisibility(View.GONE);
        } else {
            Log.e(TAG, "mDragView == NULL!");
        }
        if (mLayout != null) {
            mLayout.removeView(mDragView);
            View v = mLayout.findViewWithTag(TAG);
            if (v != null) {
                Log.w(TAG, "removed artifact drag view");
                mLayout.removeView(v);
            }
        }
        if (mBitmap != null && !mBitmap.isRecycled()) {
            mBitmap.recycle();
            mBitmap = null;
        }
        mDragging = false;
    }

    public void reset() {
        mLastX = mMidX;
        mLastY = mMidY;
    }

    public void setMotionEvent(Context context, MotionEvent event, float initialX) {
        mX = event.getX() - mDeltaX;
        if (mDragView != null && mDragView.isShown()) {
            mDragView.setY(mTopY);
            mDragView.setX(mX);
            mLastX = event.getX();
            mLastY = event.getY();
        }
    }

    public float getLastX() {
        return mLastX;
    }

    public float getLastY() {
        return mLastY;
    }

    public static Bitmap getBitmapFromView(View view) {
        Bitmap out = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(out);
        Drawable drawable = view.getBackground();
        if (drawable != null) drawable.draw(canvas);
        else canvas.drawColor(Color.TRANSPARENT);
        view.draw(canvas);
        return out;
    }
}
