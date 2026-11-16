/*
 * Copyright (C) 2026 LightningOS
 * SPDX-License-Identifier: Apache-2.0
 */

package com.lightning.control.hub;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

public abstract class HubPageFragment extends PreferenceFragmentCompat {

    private static final String ARG_TAB = "tab";

    public static Bundle argsFor(HubTab tab) {
        Bundle b = new Bundle();
        b.putString(ARG_TAB, tab.name());
        return b;
    }

    protected HubTab getTab() {
        Bundle args = getArguments();
        if (args == null) {
            return HubTab.SYSTEM;
        }
        String name = args.getString(ARG_TAB);
        if (name == null) {
            return HubTab.SYSTEM;
        }
        try {
            return HubTab.valueOf(name);
        } catch (IllegalArgumentException e) {
            return HubTab.SYSTEM;
        }
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(getPreferencesRes(), rootKey);
        setDivider(null);
    }

    protected abstract int getPreferencesRes();
}
