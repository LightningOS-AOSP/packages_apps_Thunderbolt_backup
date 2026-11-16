/*
 * Copyright (C) 2026 LightningOS
 * SPDX-License-Identifier: Apache-2.0
 */

package com.lightning.control.hub;

import com.android.settings.R;

public class StatusbarPage extends HubPageFragment {

    @Override
    protected int getPreferencesRes() {
        return R.xml.hub_statusbar;
    }
}
