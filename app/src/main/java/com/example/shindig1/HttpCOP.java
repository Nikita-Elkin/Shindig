package com.example.shindig1;
import com.android.volley.*;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;


public class HttpCOP {
    private static HttpCOP singleInstance;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private static Context mContext;


    public static synchronized HttpCOP getInstance(Context context) {
        if (singleInstance == null) {
            singleInstance = new HttpCOP(context);
        }
        return singleInstance;
    }

    private HttpCOP(Context context) {
        mContext = context;
        mRequestQueue = getRequestQueue();

        mImageLoader = new ImageLoader(mRequestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });
    }



    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req,String tag) {
        req.setTag(tag);
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}
