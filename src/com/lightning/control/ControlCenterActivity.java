/*
 * Copyright (C) 2026 LightningOS
 * SPDX-License-Identifier: Apache-2.0
 */

package com.lightning.control;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.lightning.control.hub.HubPagerAdapter;
import com.lightning.control.hub.HubTab;
import com.lightning.control.nav.FrostLayer;
import com.lightning.control.nav.PillFrost;
import com.lightning.control.nav.PillNavView;
import com.lightning.thunderbolt.R;

public class ControlCenterActivity extends AppCompatActivity {

    private static final String STATE_TAB = "hub_tab";

    private ViewPager2 mPager;
    private PillNavView mPill;
    private CollapsingToolbarLayout mCollapsing;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_center);

        mCollapsing = findViewById(R.id.hub_collapsing);
        mPager = findViewById(R.id.hub_pager);
        mPill = findViewById(R.id.hub_pill);

        mCollapsing.setTitle(getString(R.string.hub_title));

        mPager.setAdapter(new HubPagerAdapter(this));
        mPager.setOffscreenPageLimit(1);

        mPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                mPill.select(position, false);
                mCollapsing.setTitle(getString(HubTab.fromPosition(position).getTitleRes()));
            }
        });

        mPill.setOnTabSelectedListener(tab -> mPager.setCurrentItem(tab.ordinal(), true));

        int start = savedInstanceState != null
                ? savedInstanceState.getInt(STATE_TAB, 0)
                : 0;
        mPager.setCurrentItem(start, false);
        mPill.select(start, false);

        FrostLayer frost = findViewById(R.id.hub_frost);
        View pager = findViewById(R.id.hub_pager);
        PillFrost.install(frost, pager);
    }

    @Override
    protected void onSaveInstanceState(@Nullable Bundle outState) {
        super.onSaveInstanceState(outState);
        if (outState != null) {
            outState.putInt(STATE_TAB, mPager.getCurrentItem());
        }
    }
}
