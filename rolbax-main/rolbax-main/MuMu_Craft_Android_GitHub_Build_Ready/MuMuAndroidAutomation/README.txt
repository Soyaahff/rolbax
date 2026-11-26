MuMu Craft Automation - Android starter project

Android-native coordinate tap macro for MuMu Player. This is NOT a compiled APK.

BUILD:
1. Install Android Studio on Windows (https://developer.android.com/studio)
2. Extract this project and open the MuMuAndroidAutomation folder in Android Studio.
3. Let Gradle sync and download required Android SDK components.
4. Select Build > Build APK(s).
5. APK path: app/build/outputs/apk/debug/app-debug.apk

SETUP:
1. Install APK in MuMu and open it.
2. Tap Allow floating window and enable overlay permission.
3. Tap Enable Accessibility Service and enable MuMu Craft Automation.
4. Return to app and configure X,Y coordinates for each action (same emulator resolution).
5. Save settings; show the floating panel; switch to the game; press Start.

Workflow rows include crafting Prismatic Halter, buy-dialogue steps (optional), and selling dialogue steps. Empty coordinate rows are skipped. This is a timed macro and does NOT detect bag-full, ingredient shortage, item names, or crafting success. It taps coordinates blindly. Test with a small loop count first. Accessibility gesture tap support requires Android 7+.
