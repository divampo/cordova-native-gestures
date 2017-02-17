package com.divampo.cordova.NativeGestures;

import android.annotation.TargetApi;
import android.view.ScaleGestureDetector;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.LOG;
import org.json.JSONException;
import org.json.JSONObject;

@TargetApi(17)
public class NativeGesturesScaleListener implements ScaleGestureDetector.OnScaleGestureListener {

    private final CordovaWebView webView;
    private float mScaleFactor = 1.f;

    NativeGesturesScaleListener(CordovaWebView webView) {
        LOG.i(NativeGesturesPlugin.LOGTAG, "NativeGesturesScaleListener init");
        this.webView = webView;
    }

    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        JSONObject data = new JSONObject();
        mScaleFactor *= detector.getScaleFactor();
        mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 5.0f));

        try {
            data.put("scaleFactor", mScaleFactor);
        } catch(JSONException e) {
            LOG.e(NativeGesturesPlugin.LOGTAG, e.getMessage());
        }

        sendEvent(NativeGesturesPlugin.GESTURE_SCALE, data);

        return true;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        mScaleFactor = 1.f;
        sendEvent(NativeGesturesPlugin.GESTURE_SCALE_BEGIN, new JSONObject());
        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {
        sendEvent(NativeGesturesPlugin.GESTURE_SCALE_END, new JSONObject());
    }

    private void sendEvent(final String name, JSONObject json) {
        final String data = json.toString();

        String jsEvent = String.format("javascript:cordova.fireDocumentEvent('%s', { 'data': %s });", name, data);
        webView.loadUrl(jsEvent );
    }
}
