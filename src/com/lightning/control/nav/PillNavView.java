/*
 * Copyright (C) 2026 LightningOS
 * SPDX-License-Identifier: Apache-2.0
 */

package com.lightning.control.nav;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.lightning.control.hub.HubTab;
import com.lightning.thunderbolt.R;

public class PillNavView extends LinearLayout {

    public interface OnTabSelectedListener {
        void onTabSelected(HubTab tab);
    }

    private final LinearLayout[] mItems = new LinearLayout[HubTab.count()];
    private OnTabSelectedListener mListener;
    private int mSelected;

    public PillNavView(Context context) {
        this(context, null);
    }

    public PillNavView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);
        build();
    }

    private void build() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        for (int i = 0; i < HubTab.count(); i++) {
            final int index = i;
            final HubTab tab = HubTab.fromPosition(i);
            LinearLayout item = (LinearLayout) inflater.inflate(R.layout.hub_pill_item, this, false);
            ImageView icon = item.findViewById(R.id.pill_icon);
            TextView label = item.findViewById(R.id.pill_label);
            icon.setImageResource(tab.getGlyphRes());
            label.setText(tab.getTitleRes());
            item.setOnClickListener(v -> select(index, true));
            mItems[i] = item;
            addView(item, new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1f));
        }
        select(0, false);
    }

    public void setOnTabSelectedListener(OnTabSelectedListener listener) {
        mListener = listener;
    }

    public int getSelected() {
        return mSelected;
    }

    public void select(int index, boolean notify) {
        if (index < 0 || index >= mItems.length) {
            return;
        }
        mSelected = index;
        for (int i = 0; i < mItems.length; i++) {
            applyState(mItems[i], HubTab.fromPosition(i), i == index);
        }
        if (notify && mListener != null) {
            mListener.onTabSelected(HubTab.fromPosition(index));
        }
    }

    private void applyState(LinearLayout item, HubTab tab, boolean active) {
        ImageView icon = item.findViewById(R.id.pill_icon);
        TextView label = item.findViewById(R.id.pill_label);

        item.setActivated(active);

        int fg = ContextCompat.getColor(getContext(), active
                ? tab.getActiveFgRes()
                : R.color.hub_pill_inactive);
        int bg = ContextCompat.getColor(getContext(), tab.getActiveBgRes());

        Drawable glyph = ContextCompat.getDrawable(getContext(), tab.getGlyphRes()).mutate();
        glyph.setTint(fg);
        icon.setImageDrawable(glyph);

        label.setTextColor(fg);
        label.setVisibility(active ? VISIBLE : GONE);

        Drawable background = item.getBackground();
        if (background != null) {
            background = background.mutate();
            background.setTint(active ? bg : 0);
            item.setBackground(background);
        }
        item.setPadding(dp(active ? 16 : 14), 0, dp(active ? 20 : 14), 0);
    }

    private int dp(int value) {
        return Math.round(value * getResources().getDisplayMetrics().density);
    }
}
