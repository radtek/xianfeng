package com.qingyii.hxt.base.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * ClassName: RefreshHead
 * Author:oubowu
 * Fuction: 头部需要实现这个抽象类，以告诉UcRefreshLayout头部的状态
 * CreateDate:2016/2/11 22:56
 * UpdateUser:
 * UpdateDate:
 */
public abstract class RefreshHead extends View {

    public RefreshHead(Context context) {
        super(context);
    }

    public RefreshHead(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RefreshHead(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    public RefreshHead(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context,attrs,defStyleAttr);
    }

    /**
     * 头部正在刷新
     *
     * @return
     */
    public abstract boolean isLoading();

    /**
     * 头部准备好刷新
     *
     * @return
     */
    public abstract boolean isReadyLoad();

    /**
     * 执行加载完的效果，把加载动画移除
     */
    public abstract void performLoaded();

    /**
     * 执行加载动画
     */
    public abstract void performLoading();

    /**
     * 响应拉动时的操作
     *
     * @param v
     */
    public abstract void performPull(float v);
}
