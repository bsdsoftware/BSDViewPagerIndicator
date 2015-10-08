package it.bsdsoftware.library;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Simone on 06/10/15.
 */
class Indicator extends TextView {

    private String textSelected = null;
    private String textDeselected = null;
    private int textColorSelected ;
    private int textColorDeselected;
    private int backgroundColorSelected = Color.TRANSPARENT;
    private int backgroundColorDeselected = Color.TRANSPARENT;
    private Drawable backgroundDrawableSelected = null;
    private Drawable backgroundDrawableDeselected = null;
    private boolean isTypefaceCustom = false;
    private boolean isTextSizeCustom = false;
    private int typefaceTextSelected = Typeface.NORMAL;
    private int textSizeSelected = -1; // in sp
    private int typefaceTextDeselected = Typeface.NORMAL;
    private int textSizeDeselected = -1; // in sp

    private boolean stateSelected;

    public Indicator(Context context) {
        super(context);
        textColorSelected = this.getTextColors().getDefaultColor();
        textColorDeselected = this.getTextColors().getDefaultColor();
        this.setGravity(Gravity.CENTER);
    }

    public void setMargins(int margin){
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) getLayoutParams();
        lp.setMargins(margin, margin, margin, margin);
        this.setLayoutParams(lp);
    }

    public void setSize(int height, int width){
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) getLayoutParams();
        lp.width = width;
        lp.height = height;
        this.setLayoutParams(lp);
    }

    public void setSquare(){
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        this.measure(width, height);

        int size = getMeasuredHeight() > getMeasuredWidth() ? getMeasuredHeight() : getMeasuredWidth();
        setSize(size, size);
    }

    public void setText(String textSelected, String textDeselected) {
        this.textSelected = textSelected;
        this.textDeselected = textDeselected;
    }

    public void setTextColorSelected(int textColorSelected) {
        this.textColorSelected = textColorSelected;
    }

    public void setTextColorDeselected(int textColorDeselected) {
        this.textColorDeselected = textColorDeselected;
    }

    public void setBackgroundColorSelected(int backgroundColorSelected) {
        this.backgroundColorSelected = backgroundColorSelected;
    }

    public void setBackgroundColorDeselected(int backgroundColorDeselected) {
        this.backgroundColorDeselected = backgroundColorDeselected;
    }

    public void setBackgroundDrawableSelected(Drawable backgroundDrawableSelected) {
        this.backgroundDrawableSelected = backgroundDrawableSelected;
    }

    public void setBackgroundDrawableDeselected(Drawable backgroundDrawableDeselected) {
        this.backgroundDrawableDeselected = backgroundDrawableDeselected;
    }

    public void setTextSizeCustom(int selected, int deselected) {
        this.textSizeSelected = selected;
        this.textSizeDeselected = deselected;
        this.isTextSizeCustom = true;
    }

    public void setTypefaceCustom(int selected, int deselected) {
        this.typefaceTextSelected = selected;
        this.typefaceTextDeselected = deselected;
        isTypefaceCustom = true;
    }

    public boolean isStateSelected() {
        return stateSelected;
    }

    public void setStateSelected(boolean stateSelected) {
        this.stateSelected = stateSelected;
        if(stateSelected) {
            if(backgroundDrawableSelected!=null)
                this.setBackgroundByDrawable(backgroundDrawableSelected);
            else
                this.setBackgroundColor(backgroundColorSelected);
            this.setTextColor(textColorSelected);
            if(textSelected!=null)
                this.setText(textSelected);
            if(isTextSizeCustom)
                this.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSizeSelected);
            if(isTypefaceCustom)
                this.setTypeface(null, typefaceTextSelected);
        }else{
            if(backgroundDrawableDeselected!=null)
                this.setBackgroundByDrawable(backgroundDrawableDeselected);
            else
                this.setBackgroundColor(backgroundColorDeselected);
            this.setTextColor(textColorDeselected);
            if(textDeselected!=null)
                this.setText(textDeselected);
            if(isTextSizeCustom)
                this.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSizeDeselected);
            if(isTypefaceCustom)
                this.setTypeface(null, typefaceTextDeselected);
        }
    }


    private void setBackgroundByDrawable(Drawable background) {
        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            super.setBackgroundDrawable(background);
        } else {
            super.setBackground(background);
        }
    }
}
