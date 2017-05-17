package digimantra.com.bodiestv.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import digimantra.com.bodiestv.R;

/**
 * Created by shivam on 29/8/16.
 */
public class SelectorWidget extends LinearLayout implements View.OnClickListener {


    private OnSelectedListener listener;


    private CharSequence[] values;
    private int selectColor;
    private int textColor;
    private TextView selectedTextView;
    private int selectTextColor;
    private int textPadding;
    private GradientDrawable gradientDrawable;
    private GradientDrawable selectBackground;

    public SelectorWidget(Context context) {
        this(context,null);
    }

    public SelectorWidget(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SelectorWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
        setOrientation(HORIZONTAL);

    }

    public void setListener(OnSelectedListener listener) {
        this.listener = listener;
    }

    private void init(AttributeSet attrs) {




        TypedArray typedArray;
        typedArray = getContext()
                .obtainStyledAttributes(attrs, R.styleable.SelectorWidget);
        values = typedArray
                .getTextArray(R.styleable.SelectorWidget_selectors);
        selectColor = typedArray.getColor(R.styleable.SelectorWidget_selectColor, Color.WHITE);
        selectTextColor = typedArray.getColor(R.styleable.SelectorWidget_selectedTextColor,Color.BLACK);
        textColor = typedArray.getColor(R.styleable.SelectorWidget_textColor, Color.BLACK);
        textPadding = typedArray.getDimensionPixelSize(R.styleable.SelectorWidget_textPadding,10);


        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setStroke(1, Color.WHITE);
        drawable.setCornerRadius(8);
        drawable.setColor(Color.TRANSPARENT);
        setBackgroundDrawable(drawable);

        selectBackground = new GradientDrawable();
        selectBackground.setShape(GradientDrawable.RECTANGLE);
        selectBackground.setCornerRadius(8);
        selectBackground.setColor(selectColor);



        typedArray.recycle();
        addTextViews();
    }

    private void addTextViews() {
//        int count = 0;
        for (CharSequence value:values) {

            int colors[] = { 0xffffffff , 0x000000};

             gradientDrawable = (GradientDrawable) getContext().getResources().getDrawable(R.drawable.textview_background);



            TextView textView = new TextView(getContext());
            textView.setTag(value);


            int padding = textPadding;
            textView.setPadding(padding,padding,padding,padding);
            textView.setBackgroundDrawable(gradientDrawable);
            textView.setOnClickListener(this);
            textView.setTextColor(textColor);
            textView.setText(value);
            addView(textView);
        }
    }


    @Override
    public void onClick(View view) {






        if(listener!=null){
            if(selectedTextView!=null){
                selectedTextView.setBackgroundDrawable(gradientDrawable);
                selectedTextView.setTextColor(textColor);
            }
            listener.onSelect((String) view.getTag());
            selectedTextView = (TextView)view;
            selectedTextView.setTextColor(selectTextColor);
            selectedTextView.setBackgroundDrawable(selectBackground);
        }

    }

    public interface OnSelectedListener{
        void onSelect(String value);

    }
}
