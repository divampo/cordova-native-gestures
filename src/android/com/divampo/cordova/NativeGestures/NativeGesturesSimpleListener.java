package com.divampo.cordova.NativeGestures;

import android.annotation.TargetApi;
import android.view.GestureDetector;
import android.view.MotionEvent;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.LOG;
import org.json.JSONException;
import org.json.JSONObject;

@TargetApi(17)
class NativeGesturesSimpleListener extends GestureDetector.SimpleOnGestureListener {

    private CordovaWebView webView;

    NativeGesturesSimpleListener(CordovaWebView webView) {
        LOG.i(NativeGesturesPlugin.LOGTAG, "NativeGesturesSimpleListener init");
        this.webView = webView;
    }

    @Override
    public boolean onSingleTapUp(MotionEvent event) {
        sendEvent(NativeGesturesPlugin.GESTURE_SINGLE_TAP_UP, new JSONObject());
        return true;
    }

    @Override
    public void onShowPress(MotionEvent ev) {
        sendEvent(NativeGesturesPlugin.GESTURE_SHOW_PRESS, new JSONObject());
    }

    @Override
    public void onLongPress(MotionEvent ev) {
        sendEvent(NativeGesturesPlugin.GESTURE_LONG_PRESS, new JSONObject());
    }

    @Override
    public boolean onDown(MotionEvent ev) {
        sendEvent(NativeGesturesPlugin.GESTURE_DOWN, new JSONObject());
        return true;
    }
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        JSONObject data = new JSONObject();

        try {
            data.put("velocityX", velocityX);
            data.put("velocityY", velocityY);
        } catch(JSONException e) {
            LOG.e(NativeGesturesPlugin.LOGTAG, e.getMessage());
        }
        sendEvent(NativeGesturesPlugin.GESTURE_FLING, data);
        return true;
    }

    private void sendEvent(final String name, JSONObject json) {
        final String data = json.toString();

        String jsEvent = String.format("javascript:cordova.fireDocumentEvent('%s', { 'data': %s });", name, data);
        webView.loadUrl(jsEvent );
    }
}
