/*
 * Copyright (C) 2026 LightningOS
 * SPDX-License-Identifier: Apache-2.0
 */

package com.lightning.control.nav;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FrostLayer extends FrameLayout {

    private View mSource;
    private float mCornerRadius;

    public FrostLayer(@NonNull Context context) {
        this(context, null);
    }

    public FrostLayer(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        setClipToOutline(true);
    }

    public void setSource(@Nullable View source) {
        mSource = source;
        invalidate();
    }

    public void setCornerRadius(float radius) {
        mCornerRadius = radius;
        setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), mCornerRadius);
            }
        });
        invalidateOutline();
    }

    public void enableBlur(float radius) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
            return;
        }
        setRenderEffect(RenderEffect.createBlurEffect(radius, radius, Shader.TileMode.CLAMP));
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mSource == null) {
            return;
        }
        int[] sourceLoc = new int[2];
        int[] selfLoc = new int[2];
        mSource.getLocationInWindow(sourceLoc);
        getLocationInWindow(selfLoc);

        int dx = selfLoc[0] - sourceLoc[0];
        int dy = selfLoc[1] - sourceLoc[1];

        canvas.save();
        canvas.clipRect(0, 0, getWidth(), getHeight());
        canvas.translate(-dx, -dy);
        mSource.draw(canvas);
        canvas.restore();
    }
}
