/*
 * Copyright (C) 2026 LightningOS
 * SPDX-License-Identifier: Apache-2.0
 */

package com.lightning.control.hub;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class HubPagerAdapter extends FragmentStateAdapter {

    public HubPagerAdapter(@NonNull FragmentActivity activity) {
        super(activity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        HubTab tab = HubTab.fromPosition(position);
        Fragment fragment;
        switch (tab) {
            case LOCKSCREEN:
                fragment = new LockscreenPage();
                break;
            case STATUSBAR:
                fragment = new StatusbarPage();
                break;
            case HARDWARE:
                fragment = new HardwarePage();
                break;
            case SYSTEM:
            default:
                fragment = new SystemPage();
                break;
        }
        fragment.setArguments(HubPageFragment.argsFor(tab));
        return fragment;
    }

    @Override
    public int getItemCount() {
        return HubTab.count();
    }
}
