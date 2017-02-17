var exec = require('cordova/exec');

var nativeGestures = {};

nativeGestures.EVENTS = {
	GESTURE_SINGLE_TAP_UP: "nativeSingleTapUp",
	GESTURE_SHOW_PRESS: "nativeShowPress",
	GESTURE_LONG_PRESS: "nativeLongPress",
	GESTURE_SCALE: "nativeScale",
	GESTURE_SCALE_BEGIN: "nativeScaleBegin",
	GESTURE_SCALE_END: "nativeScaleEnd",
	GESTURE_DOWN: "nativeDown",
	GESTURE_FLING: "nativeFling"
}

module.exports = nativeGestures;
