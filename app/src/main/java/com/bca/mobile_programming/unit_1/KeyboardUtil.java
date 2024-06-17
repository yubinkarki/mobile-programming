package com.bca.mobile_programming.unit_1;

import android.view.View;
import android.app.Activity;
import android.graphics.Rect;
import android.view.ViewGroup;
import android.widget.EditText;
import android.view.ViewTreeObserver;

public class KeyboardUtil {
    private final View rootView;
    private boolean isKeyboardVisible;
    private static final int KEYBOARD_HEIGHT = 150;
    private final ViewTreeObserver.OnGlobalLayoutListener keyboardLayoutListener = this::handleKeyboardVisibilityChange;

    public KeyboardUtil(Activity activity) {
        rootView = activity.findViewById(android.R.id.content);
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(keyboardLayoutListener);
    }

    private void handleKeyboardVisibilityChange() {
        Rect r = new Rect();
        rootView.getWindowVisibleDisplayFrame(r);

        int screenHeight = rootView.getRootView().getHeight();
        int keypadHeight = screenHeight - r.bottom;
        int keyboardHeightThreshold = (int) (KEYBOARD_HEIGHT * rootView.getResources().getDisplayMetrics().density);
        boolean isKeyboardNowVisible = keypadHeight > keyboardHeightThreshold;

        if (isKeyboardVisible && !isKeyboardNowVisible) clearFocusFromAllInputs(rootView);

        isKeyboardVisible = isKeyboardNowVisible;
    }

    private void clearFocusFromAllInputs(View view) {
        if (view instanceof ViewGroup) {
            int i = 0;
            ViewGroup viewGroup = (ViewGroup) view;

            while (i < viewGroup.getChildCount()) {
                clearFocusFromAllInputs(viewGroup.getChildAt(i));
                i++;
            }
        } else if (view instanceof EditText) view.clearFocus();
    }

    public void removeListener() {
        rootView.getViewTreeObserver().removeOnGlobalLayoutListener(keyboardLayoutListener);
    }
}
