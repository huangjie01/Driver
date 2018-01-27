package com.huangjie.driver.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.huangjie.driver.utils.ResUtils;

/**
 * Created by huangjie on 2017/1/21.
 */

public class SideBarBean extends BaseObservable {
    private int icon;
    private String title;
    private boolean hasChoise;
    private String choiseText;
    private boolean hasBtn;

    @Bindable
    public int getIcon() {
        return icon;
    }

    @BindingAdapter("bind:icon")
    public static void   getImage(ImageView iv, int icon) {
        iv.setImageDrawable(ResUtils.getDrawable(icon));
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    @Bindable
    public boolean isHasChoise() {
        return hasChoise;
    }

    @Bindable
    public String getChoiseText() {
        return choiseText;
    }

    @Bindable
    public boolean isHasBtn() {
        return hasBtn;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setHasChoise(boolean hasChoise) {
        this.hasChoise = hasChoise;
    }

    public void setChoiseText(String choiseText) {
        this.choiseText = choiseText;
    }

    public void setHasBtn(boolean hasBtn) {
        this.hasBtn = hasBtn;
    }
}
