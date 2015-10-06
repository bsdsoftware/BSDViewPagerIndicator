package it.bsdsoftware.library;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
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
public class IndicatorContainer extends LinearLayout {

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


    public IndicatorContainer(Context context) {
        super(context);
    }

    public IndicatorContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        readAttributeSet(attrs);
    }

    public IndicatorContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        readAttributeSet(attrs);
    }

    private void readAttributeSet(AttributeSet attrs){

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
