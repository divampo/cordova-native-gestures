package com.divampo.cordova.NativeGestures;

import android.annotation.TargetApi;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.LOG;

@TargetApi(17)
public class NativeGesturesPlugin extends CordovaPlugin {
    protected static final String LOGTAG = "NativeGestures";

    /* Gesture Types. */
    protected static final String GESTURE_SINGLE_TAP_UP = "nativeSingleTapUp";
    protected static final String GESTURE_SHOW_PRESS = "nativeShowPress";
    protected static final String GESTURE_LONG_PRESS = "nativeLongPress";
    protected static final String GESTURE_SCALE = "nativeScale";
    protected static final String GESTURE_SCALE_BEGIN = "nativeScaleBegin";
    protected static final String GESTURE_SCALE_END = "nativeScaleEnd";
    protected static final String GESTURE_DOWN = "nativeDown";
    protected static final String GESTURE_FLING = "nativeFling";

    protected void pluginInitialize() {
        LOG.i(LOGTAG, "NativeGesturesPlugin init");

        super.pluginInitialize();

        // create extra touch listener
        final GestureDetector gestureDetector = new GestureDetector(cordova.getActivity(),
                new NativeGesturesSimpleListener(webView));

        // create extra scale listener
        final ScaleGestureDetector scaleGestureDetector = new ScaleGestureDetector(cordova.getActivity(),
                new NativeGesturesScaleListener(webView));

        // handle view onTouch
        webView.getView().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                scaleGestureDetector.onTouchEvent(event); // pass event to extra scale listener
                gestureDetector.onTouchEvent(event); // pass event to the extra touch listener
                return webView.getView().onTouchEvent(event); // then call existing cordova listener
            }
        });
    }

}
