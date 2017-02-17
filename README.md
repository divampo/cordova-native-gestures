# cordova-native-gestures

Use native platform gestures in your cordova app for better gesture sensitivity and higher triggering frequency.

## Dependency

 - Android SDK 17.+

## Install

Run this command to add this plugin to your project:
```shell
cordova plugins https://github.com/divampo/google-campaign-tracking --save
```
## Usage

Add event listener to one of the available events and handle it:

```javascript
document.addEventListener(window.nativeGestures.EVENTS.GESTURE_SCALE, (e) => {
    // your code here
});
```

## Available events

- nativeSingleTapUp
- nativeShowPress
- nativeLongPress
- nativeScale
- nativeScaleBegin
- nativeScaleEnd
- nativeDown
- nativeFling

## License

This code is licensed under the MIT License
