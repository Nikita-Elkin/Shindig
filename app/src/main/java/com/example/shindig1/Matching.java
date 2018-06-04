package com.example.shindig1;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;
import com.gigamole.infinitecycleviewpager.OnInfiniteCyclePageTransformListener;
import com.gigamole.infinitecycleviewpager.VerticalInfiniteCycleViewPager;

import java.util.ArrayList;
import java.util.List;

import eu.davidea.flipview.FlipView;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.shindig1.SwipeMenuDropZones.DropZone;
import com.example.shindig1.SwipeMenuDropZones.SwipeMenuDropEvents;
import com.example.shindig1.SwipeMenuGestureListener.ScrollCallback;
import com.example.shindig1.VerticalViewPager.OnPageChangeListener;
import com.igalata.bubblepicker.adapter.BubblePickerAdapter;
import com.igalata.bubblepicker.model.BubbleGradient;
import com.igalata.bubblepicker.model.PickerItem;
import com.igalata.bubblepicker.rendering.BubblePicker;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;


@SuppressLint("NewApi")
public class Matching extends AppCompatActivity implements SwipeMenuDropEvents, OnPageChangeListener, ScrollCallback {

    List<Integer> lstCards= new ArrayList<>();

    public static String TAG = "TAG";

    private GestureDetector mDetector = null;
    private SwipeMenuGestureListener mSwipeGestureListener = null;
    private SwipeMenuManager mSwipeManager=null;
    private SwipeMenuDragShadow mShadow = null;
    private SwipeMenuDropZones mDropZones = null;
    private SwipeMenuTouchListener mTouchListener = null;

    private VerticalViewPager mPager = null;
    private PagerAdapter mAdapter = null;

    private View mCurrentView=this.getCurrentFocus();

    
    Button letsLunch;
    Button maybeLater;

    BubblePicker bubblePicker;

    String[] bubbles={"You Both Like Running",
            "You Both Like Leadership",
            "You Both Went To The Same College",
            "You Both Enjoy Playing Golf"};

    int images[]={
            R.drawable.info_icon,
            R.drawable.info_icon,
            R.drawable.info_icon,
            R.drawable.info_icon
    };

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        mPager.setCurrentItem(0);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching);

        initCards();
        letsLunch=(Button) findViewById(R.id.letsLunchBtn);
        maybeLater=(Button) findViewById(R.id.maybeLaterBtn);

        mPager = (VerticalViewPager) findViewById(R.id.vp);

        mSwipeGestureListener = new SwipeMenuGestureListener(this);
        mDetector = new GestureDetector(this, mSwipeGestureListener);
        mShadow = new SwipeMenuDragShadow(this, (ViewGroup) findViewById(R.id.container));
        mDropZones = new SwipeMenuDropZones(this, this);
        mDropZones.setIncreaseHitbox(60);
        mDropZones.setDropZone(DropZone.Left, findViewById(R.id.drop_zone_left));
        mDropZones.setDropZone(DropZone.Right, findViewById(R.id.drop_zone_right));
        mTouchListener = new SwipeMenuTouchListener(mPager, mDetector, mDropZones);
        mTouchListener.setDragShadow(mShadow);

        ArrayList<Integer> resIds = new ArrayList<Integer>();
        resIds.add(R.drawable.business_card2);
        resIds.add(R.drawable.business_card3);
        resIds.add(R.drawable.business_card3);
        resIds.add(R.drawable.business_card3);
        resIds.add(R.drawable.business_card3);

        mAdapter = new PagerAdapter() {

            @Override
            public View instantiateItem(ViewGroup container, int position) {
                LayoutInflater inflater = getLayoutInflater();
                View v = inflater.inflate(R.layout.vp_page, null);
                Log.d(TAG, "-------------> inflated view!");
                ImageView iv = (ImageView) v.findViewById(R.id.vp_page_image);
                int resId = mResIds.get(position);
                iv.setImageResource(resId);
                iv.setVisibility(View.VISIBLE);
                Log.d(TAG, "----------> set image view with res id: " + resId);
                float topY = v.getY();
                v.setTag(position);	// XXX: VERY IMPORTANT!!!!!
                container.addView(v);
                return v;
            }
            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                View view = (View) object;
                ((VerticalViewPager) container).removeView(view);
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public int getCount() {
                int count = mResIds.size();
                //Log.d(TAG, "count: " + count);
                return count;
            }
        };

        mAdapter.setResIds(resIds);
        mAdapter.notifyDataSetChanged();
        mPager.setOffscreenPageLimit(1);
        mPager.setAdapter(mAdapter);
        mPager.setPageTransformer(true, new DepthPageTransformer());
        mPager.setOnPageChangeListener(this);
        mPager.setOnTouchListener(mTouchListener);
        new Timer().schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        mCurrentView = mPager.findViewWithTag(mPager.getCurrentItem());
                        onPageSelected(1);
                        onPageSelected(2);

                    }
                },
                2000
        );



        // always set ontouch on viewpager, not this child views

		mSwipeManager = new SwipeMenuManager(this, null);
		mSwipeManager.setViewPager(mPager);

        bubblePicker=findViewById(R.id.bubblePicker);
        ArrayList<PickerItem> listItems= new ArrayList<>();

        for (int i=0;i<bubbles.length;i++){
            PickerItem item= new PickerItem();
            item.setIcon(getDrawable(images[i]));
            listItems.add(item);
        }

       // bubblePicker.setAdapter(new BubblePickerAdapter() {
         //   @Override
        //  public int getTotalCount() {
        //       return bubbles.length;
        //   }

        //  @Override
        //  public PickerItem getItem(int position) {
        //       PickerItem item = new PickerItem();
        //      item.setTitle(bubbles[position]);
        //      return item;
        //    }
        // });

        bubblePicker.setItems(listItems);
        bubblePicker.setCenterImmediately(true);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {
        Log.d(TAG, "onPageSelected! -----> "+position);
        if (position < mAdapter.getCount()) {
            mCurrentView = mPager.getChildAt(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {}

    @Override
    public SwipeMenuDragShadow postback_drop(DropZone dropZone) {
        if (dropZone == DropZone.Left) {
            Toast.makeText(this, "Left", Toast.LENGTH_SHORT).show();
        }
        else if (dropZone == DropZone.Right) {
            Toast.makeText(this, "Right", Toast.LENGTH_SHORT).show();
        }
        return mShadow;
    }

    @Override
    public void postback_onClick() {
        Toast.makeText(this, "onClick position: " + mPager.getCurrentItem(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void postback_onHorizontalScroll(MotionEvent event, boolean consumed) {
        try {
            if (consumed) {
                if (mCurrentView == null) {
                    mCurrentView = mPager.findViewWithTag(mPager.getCurrentItem());
                }
                //mShadow.startDrag(mPager.findViewWithTag(mPager.getCurrentItem()), event, mPager.getLeft(), mPager.getTop());
                //mShadow.setMotionEvent(this, event, mCurrentView.getX());
            } else {
                if (!mShadow.isDragging()) mPager.onTouchEvent(event);
            }
        }
        catch(Exception e){

        }
    }



    private void initCards(){
        lstCards.add(R.drawable.business_card1);
        lstCards.add(R.drawable.business_card2);
        lstCards.add(R.drawable.business_card3);
    }

    @Override
    protected void onResume() {
        super.onResume();
        bubblePicker.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        bubblePicker.onPause();
    }



}
