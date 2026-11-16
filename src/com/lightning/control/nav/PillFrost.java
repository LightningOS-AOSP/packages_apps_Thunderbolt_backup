/*
 * Copyright (C) 2026 LightningOS
 * SPDX-License-Identifier: Apache-2.0
 */

package com.lightning.control.nav;

import android.os.Build;
import android.view.View;

import androidx.annotation.NonNull;

public final class PillFrost {

    private static final float BLUR_DP = 24f;
    private static final float CORNER_DP = 34f;

    private PillFrost() {
    }

    public static boolean isSupported() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.S;
    }

    public static void install(@NonNull FrostLayer layer, @NonNull View source) {
        float density = layer.getResources().getDisplayMetrics().density;
        layer.setCornerRadius(CORNER_DP * density);
        layer.setSource(source);
        if (isSupported()) {
            layer.enableBlur(BLUR_DP * density);
        }
    }
}
