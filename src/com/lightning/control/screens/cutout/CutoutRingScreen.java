/*
 * Copyright (C) 2026 LightningOS
 * SPDX-License-Identifier: Apache-2.0
 */

package com.lightning.control.screens.cutout;

import androidx.preference.PreferenceFragmentCompat;

import com.lightning.thunderbolt.R;

public class CutoutRingScreen extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(android.os.Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.hub_cutout_ring, rootKey);
        setDivider(null);
    }
}
