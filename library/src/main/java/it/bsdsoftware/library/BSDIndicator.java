package it.bsdsoftware.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simone on 06/10/15.
 */
public class BSDIndicator extends LinearLayout {

    private ViewPager viewPager;
    private List<Indicator> indicators = new ArrayList<>();

    private String text = null;
    private List<String> labelText = new ArrayList<>();
    private List<String> selectedText = new ArrayList<>();
    private List<String> deselectedText = new ArrayList<>();
    private int textColorSelected = Color.TRANSPARENT;
    private int textColorDeselected = Color.TRANSPARENT;
    private int backgroundColorSelected = Color.TRANSPARENT;
    private int backgroundColorDeselected = Color.TRANSPARENT;
    private Drawable backgroundDrawableSelected = null;
    private Drawable backgroundDrawableDeselected = null;
    private int padding = 0;
    private int margin = 0;
    private int typefaceText = Typeface.NORMAL;
    private int textSize = -1; // in sp
    private boolean isTypefaceCustom = false;
    private boolean isTextSizeCustom = false;
    private int typefaceTextSelected = Typeface.NORMAL;
    private int textSizeSelected = -1; // in sp
    private int typefaceTextDeselected = Typeface.NORMAL;
    private int textSizeDeselected = -1; // in sp
    private boolean indicatorClickable = false;


    public BSDIndicator(Context context) {
        super(context);
    }

    public BSDIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        readAttributeSet(context,attrs);
    }

    public BSDIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        readAttributeSet(context,attrs);
    }

    private void readAttributeSet(Context context, AttributeSet attrs){
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BSDIndicator);
        text = a.getString(R.styleable.BSDIndicator_indicatorText);
        textColorSelected = a.getColor(R.styleable.BSDIndicator_textColorSelected, Color.TRANSPARENT);
        textColorDeselected = a.getColor(R.styleable.BSDIndicator_textColorDeselected, Color.TRANSPARENT);
        backgroundColorSelected = a.getColor(R.styleable.BSDIndicator_backgroundColorSelected, Color.TRANSPARENT);
        backgroundColorDeselected = a.getColor(R.styleable.BSDIndicator_backgroundColorDeselected, Color.TRANSPARENT);
        int drawableSelected = a.getResourceId(R.styleable.BSDIndicator_backgroundDrawableSelected, -1);
        if(drawableSelected != -1) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                backgroundDrawableSelected = getResources().getDrawable(drawableSelected, context.getTheme());
            } else {
                backgroundDrawableSelected = getResources().getDrawable(drawableSelected);
            }
        }
        int drawableDeselected = a.getResourceId(R.styleable.BSDIndicator_backgroundDrawableDeselected, -1);
        if(drawableDeselected != -1) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                backgroundDrawableDeselected = getResources().getDrawable(drawableDeselected, context.getTheme());
            } else {
                backgroundDrawableDeselected = getResources().getDrawable(drawableDeselected);
            }
        }
        padding = a.getInt(R.styleable.BSDIndicator_indicatorPadding, 0);
        margin = a.getInt(R.styleable.BSDIndicator_indicatorMargin, 0);
        int enumTypefaceText = a.getInt(R.styleable.BSDIndicator_indicatorTextStyle, 0);
        typefaceText = getTypeface(enumTypefaceText);
        textSize = a.getInt(R.styleable.BSDIndicator_indicatorTextSize, -1);
        int enumTypefaceTextSelected = a.getInt(R.styleable.BSDIndicator_indicatorTextStyleSelected, -1);
        int enumTypefaceTextDeselected = a.getInt(R.styleable.BSDIndicator_indicatorTextStyleDeselected, -1);
        if(enumTypefaceTextSelected>0 && enumTypefaceTextDeselected > 0) {
            isTypefaceCustom = true;
            typefaceTextSelected = getTypeface(enumTypefaceTextSelected);
            typefaceTextDeselected = getTypeface(enumTypefaceTextDeselected);
        }
        textSizeSelected = a.getInt(R.styleable.BSDIndicator_indicatorTextSizeSelected, -1);
        textSizeDeselected = a.getInt(R.styleable.BSDIndicator_indicatorTextSizeDeselected, -1);
        if(textSizeDeselected>0 && textColorSelected > 0)
            isTextSizeCustom = true;
        indicatorClickable = a.getBoolean(R.styleable.BSDIndicator_indicatorClickable, false);
        a.recycle();
    }

    private int getTypeface(int pos){
        int typeface = Typeface.NORMAL;
        switch (pos){
            case 0:
                typeface = Typeface.NORMAL;
                break;
            case 1:
                typeface = Typeface.BOLD;
                break;
            case 2:
                typeface = Typeface.ITALIC;
                break;
            case 3:
                typeface = Typeface.BOLD_ITALIC;
                break;
        }
        return typeface;
    }

    public void init(){
        this.removeAllViews();
        int pageCount = viewPager.getAdapter().getCount();
        OnClickListener onClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (int) v.getTag();
                viewPager.setCurrentItem(position);
            }
        };
        for(int i = 0; i < pageCount; i++){
            Indicator indicator = new Indicator(getContext());
            indicator.setTag(i);

            if(textColorDeselected != Color.TRANSPARENT)
                indicator.setTextColorDeselected(textColorDeselected);
            if(textColorSelected != Color.TRANSPARENT)
                indicator.setTextColorSelected(textColorSelected);

            if(isTypefaceCustom)
                indicator.setTypefaceCustom(typefaceTextSelected, typefaceTextDeselected);
            else
                indicator.setTypeface(null, typefaceText);
            if(isTextSizeCustom)
                indicator.setTextSizeCustom(textSizeSelected, textSizeDeselected);
            else if(textSize != -1)
                indicator.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
            indicator.setBackgroundColorDeselected(backgroundColorDeselected);
            indicator.setBackgroundColorSelected(backgroundColorSelected);
            indicator.setBackgroundDrawableSelected(backgroundDrawableSelected);
            indicator.setBackgroundDrawableDeselected(backgroundDrawableDeselected);
            indicator.setPadding(padding, padding, padding, padding);
            if(selectedText.size()==pageCount && deselectedText.size()==pageCount)
                indicator.setText(selectedText.get(i), deselectedText.get(i));
            else if(labelText.size()==pageCount)
                indicator.setText(labelText.get(i));
            else if(text!=null)
                indicator.setText(text);

            if(indicatorClickable){
                indicator.setOnClickListener(onClickListener);
            }
            this.addView(indicator);
            indicator.setMargins(margin);
            indicators.add(indicator);
        }
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                selectedItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        if(pageCount>0)
            selectedItem(0);
    }

    public void selectedItem(int pos){
        for(Indicator indicator : indicators){
            indicator.setStateSelected(false);
        }
        indicators.get(pos).setStateSelected(true);
    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
        init();
    }

    public ViewPager getViewPager() {
        return viewPager;
    }

    public List<Indicator> getIndicators() {
        return indicators;
    }

    public void setIndicators(List<Indicator> indicators) {
        this.indicators = indicators;
    }

    public String getText() {
        return text;
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

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getLabelText() {
        return labelText;
    }

    public void setLabelText(List<String> labelText) {
        this.labelText = labelText;
    }

    public List<String> getSelectedText() {
        return selectedText;
    }

    public void setSelectedText(List<String> selectedText) {
        this.selectedText = selectedText;
    }

    public List<String> getDeselectedText() {
        return deselectedText;
    }

    public void setDeselectedText(List<String> deselectedText) {
        this.deselectedText = deselectedText;
    }

    public int getTextColorSelected() {
        return textColorSelected;
    }

    public void setTextColorSelected(int textColorSelected) {
        this.textColorSelected = textColorSelected;
    }

    public int getTextColorDeselected() {
        return textColorDeselected;
    }

    public void setTextColorDeselected(int textColorDeselected) {
        this.textColorDeselected = textColorDeselected;
    }

    public int getBackgroundColorSelected() {
        return backgroundColorSelected;
    }

    public void setBackgroundColorSelected(int backgroundColorSelected) {
        this.backgroundColorSelected = backgroundColorSelected;
    }

    public int getBackgroundColorDeselected() {
        return backgroundColorDeselected;
    }

    public void setBackgroundColorDeselected(int backgroundColorDeselected) {
        this.backgroundColorDeselected = backgroundColorDeselected;
    }

    public Drawable getBackgroundDrawableSelected() {
        return backgroundDrawableSelected;
    }

    public void setBackgroundDrawableSelected(Drawable backgroundDrawableSelected) {
        this.backgroundDrawableSelected = backgroundDrawableSelected;
    }

    public Drawable getBackgroundDrawableDeselected() {
        return backgroundDrawableDeselected;
    }

    public void setBackgroundDrawableDeselected(Drawable backgroundDrawableDeselected) {
        this.backgroundDrawableDeselected = backgroundDrawableDeselected;
    }

    public int getPadding() {
        return padding;
    }

    public void setPadding(int padding) {
        this.padding = padding;
    }

    public int getMargin() {
        return margin;
    }

    public void setMargin(int margin) {
        this.margin = margin;
    }

    public int getTypefaceText() {
        return typefaceText;
    }

    public void setTypefaceText(int typefaceText) {
        this.typefaceText = typefaceText;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public boolean isIndicatorClickable() {
        return indicatorClickable;
    }

    public void setIndicatorClickable(boolean indicatorClickable) {
        this.indicatorClickable = indicatorClickable;
    }
}
