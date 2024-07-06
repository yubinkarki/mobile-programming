package com.bca.mobile_programming.unit_1;

import android.os.Bundle;
import android.view.View;
import android.app.Dialog;
import android.content.Context;
import android.app.AlertDialog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.bca.mobile_programming.R;

public class AlertUtil extends DialogFragment {
    private final Context context;
    private final View rootLayout;

    public AlertUtil(Context ctx, View root) {
        this.context = ctx;
        this.rootLayout = root;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle b) {
        String noText = context.getString(R.string.no);
        String yesText = context.getString(R.string.yes);
        String positive = context.getString(R.string.positive);
        String negative = context.getString(R.string.negative);
        String title = context.getString(R.string.alert_title);
        String closeMessage = context.getString(R.string.dismiss);
        String message = context.getString(R.string.alert_message);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(title).setMessage(message);

        builder.setPositiveButton(yesText, (dialog, which) -> {
            dialog.dismiss();
            GeneralUtil.showMySnack(rootLayout, positive, closeMessage);
        });

        builder.setNegativeButton(noText, (dialog, which) -> {
            dialog.dismiss();
            GeneralUtil.showMySnack(rootLayout, negative, closeMessage);
        });

        setCancelable(false);

        return builder.create();
    }
}
