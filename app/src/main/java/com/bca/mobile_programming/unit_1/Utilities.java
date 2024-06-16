package com.bca.mobile_programming.unit_1;

import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class Utilities {
    private static final int BLACK = 0xFF000000;
    private static final int LIGHT_TEAL = 0xFFE9FFF7;
    private static final int LIGHT_PINK = 0xFFF2AEF3;

    public static void showMySnack(View rootLayout, String message, String closeMessage) {
        Snackbar snackbar = Snackbar.make(rootLayout, message, Snackbar.LENGTH_LONG);

        snackbar.setTextColor(LIGHT_TEAL);
        snackbar.setBackgroundTint(BLACK);
        snackbar.setActionTextColor(LIGHT_PINK);
        snackbar.setAction(closeMessage, v1 -> snackbar.dismiss());

        snackbar.show();
    }

    public enum ScreenCode {
        ABOUT(1),
        CONTACT(2);

        private final int code;

        ScreenCode(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }
}
