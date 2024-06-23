package com.bca.mobile_programming.unit_1;

import static java.util.stream.IntStream.range;

import android.view.View;
import android.app.Activity;
import android.graphics.Rect;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.EditText;
import android.view.inputmethod.InputMethodManager;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

public class KeyboardUtil {
    private final View rootView;
    public boolean isKeyboardVisible;
    private static final int KEYBOARD_HEIGHT = 150;
    private final OnGlobalLayoutListener keyboardListener;

    public KeyboardUtil(Activity activity) {
        rootView = activity.findViewById(android.R.id.content);
        keyboardListener = this::handleKeyboardVisibilityChange;
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(keyboardListener);
    }

    private void handleKeyboardVisibilityChange() {
        Rect r = new Rect();

        rootView.getWindowVisibleDisplayFrame(r);
        rootView.getRootView();

        int screenHeight = rootView.getHeight();
        int keypadHeight = screenHeight - r.bottom;
        int keyboardHeightThreshold = (int) (KEYBOARD_HEIGHT * rootView.getResources().getDisplayMetrics().density);
        boolean isKeyboardNowVisible = keypadHeight > keyboardHeightThreshold;

        if (isKeyboardVisible && !isKeyboardNowVisible) clearFocusFromAllInputs(rootView);

        isKeyboardVisible = isKeyboardNowVisible;
    }

    private void clearFocusFromAllInputs(View view) {
        if (view instanceof ViewGroup viewGroup) {
            int bound = viewGroup.getChildCount();
            range(0, bound).mapToObj(viewGroup::getChildAt).forEach(this::clearFocusFromAllInputs);
        } else if (view instanceof EditText) view.clearFocus();
    }

    public void removeListener() {
        rootView.getViewTreeObserver().removeOnGlobalLayoutListener(keyboardListener);
    }

    public void hideKeyboard(View view) {
        if (view != null) {
            Context context = view.getContext();
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);

            if (imm != null) imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
