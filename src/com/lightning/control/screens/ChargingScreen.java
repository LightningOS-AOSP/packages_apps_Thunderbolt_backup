/*
 * Copyright (C) 2026 LightningOS
 * SPDX-License-Identifier: Apache-2.0
 */

package com.lightning.control.screens;

import androidx.preference.PreferenceFragmentCompat;

import com.android.settings.R;

public class ChargingScreen extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(android.os.Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.hub_charging, rootKey);
        setDivider(null);
    }
}
