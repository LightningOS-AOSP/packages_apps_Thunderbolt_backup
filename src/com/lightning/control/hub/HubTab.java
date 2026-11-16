/*
 * Copyright (C) 2026 LightningOS
 * SPDX-License-Identifier: Apache-2.0
 */

package com.lightning.control.hub;

import androidx.annotation.NonNull;

import com.android.settings.R;

public enum HubTab {

    SYSTEM(R.string.hub_tab_system, R.drawable.hub_nav_system,
            R.color.hub_green_circle, R.color.hub_green_glyph),
    LOCKSCREEN(R.string.hub_tab_lockscreen, R.drawable.hub_nav_lockscreen,
            R.color.hub_red_circle, R.color.hub_red_glyph),
    STATUSBAR(R.string.hub_tab_statusbar, R.drawable.hub_nav_statusbar,
            R.color.hub_blue_circle, R.color.hub_blue_glyph),
    HARDWARE(R.string.hub_tab_hardware, R.drawable.hub_nav_hardware,
            R.color.hub_amber_circle, R.color.hub_amber_glyph);

    private final int mTitleRes;
    private final int mGlyphRes;
    private final int mActiveBgRes;
    private final int mActiveFgRes;

    HubTab(int titleRes, int glyphRes, int activeBgRes, int activeFgRes) {
        mTitleRes = titleRes;
        mGlyphRes = glyphRes;
        mActiveBgRes = activeBgRes;
        mActiveFgRes = activeFgRes;
    }

    public int getTitleRes() {
        return mTitleRes;
    }

    public int getGlyphRes() {
        return mGlyphRes;
    }

    public int getActiveBgRes() {
        return mActiveBgRes;
    }

    public int getActiveFgRes() {
        return mActiveFgRes;
    }

    @NonNull
    public static HubTab fromPosition(int position) {
        HubTab[] values = values();
        if (position < 0 || position >= values.length) {
            return SYSTEM;
        }
        return values[position];
    }

    public static int count() {
        return values().length;
    }
}
