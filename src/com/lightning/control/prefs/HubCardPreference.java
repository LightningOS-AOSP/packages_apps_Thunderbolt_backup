/*
 * Copyright (C) 2026 LightningOS
 * SPDX-License-Identifier: Apache-2.0
 */

package com.lightning.control.prefs;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;

import com.android.settings.R;

public class HubCardPreference extends Preference {

    private Drawable mIcon;
    private CharSequence mSummary;

    public HubCardPreference(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public HubCardPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public HubCardPreference(Context context) {
        super(context);
        init(context, null);
    }

    private void init(Context context, AttributeSet attrs) {
        setLayoutResource(R.layout.hub_card);
        if (attrs == null) {
            return;
        }
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.HubCardPreference);
        mIcon = a.getDrawable(R.styleable.HubCardPreference_hubIcon);
        a.recycle();
    }

    @Override
    public void setIcon(Drawable icon) {
        mIcon = icon;
        notifyChanged();
    }

    @Override
    public Drawable getIcon() {
        return mIcon;
    }

    @Override
    public void setSummary(CharSequence summary) {
        mSummary = summary;
        notifyChanged();
    }

    @Override
    public CharSequence getSummary() {
        return mSummary;
    }

    @Override
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);

        ImageView icon = (ImageView) holder.findViewById(android.R.id.icon);
        if (icon != null) {
            icon.setImageDrawable(mIcon);
            icon.setVisibility(mIcon != null ? View.VISIBLE : View.GONE);
        }

        TextView title = (TextView) holder.findViewById(android.R.id.title);
        if (title != null) {
            title.setText(getTitle());
        }

        TextView summary = (TextView) holder.findViewById(android.R.id.summary);
        if (summary != null) {
            boolean empty = TextUtils.isEmpty(mSummary);
            summary.setText(empty ? null : mSummary);
            summary.setVisibility(empty ? View.GONE : View.VISIBLE);
        }
    }
}
