package zjl.com.addsubview;


import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class addsubView extends LinearLayout implements View.OnClickListener {

    public Button btnAdd,btnSubstract;
    public TextView tvValue;
    private ValueChangeListener valueChangeListener;

    public int value = 0;
    public int defaultMinValue = 0;
    public int defaultMaxValue = Integer.MAX_VALUE;
    public int minValue,maxValue;

    public interface ValueChangeListener{
        void onValueChange(View view, int value);
    }

    public void setValueChangeListener(ValueChangeListener valueChangeListener){
        this.valueChangeListener = valueChangeListener;
    }

    public int getValue() {
        String sValue = tvValue.getText().toString().trim();
        if (!TextUtils.isEmpty(sValue)){
            value = Integer.valueOf(sValue);
        }
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        tvValue.setText(this.value + "");
        valueChangeListener.onValueChange(this,value);
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public addsubView(Context context) {
        this(context,null);
    }

    public addsubView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public addsubView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.layout_addsub_view, this);

        btnAdd = (Button)findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(this);
        btnSubstract = (Button)findViewById(R.id.btn_substract);
        btnSubstract.setOnClickListener(this);
        tvValue = (TextView) findViewById(R.id.tv_value);

        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attrs, R.styleable.addsubView);
        int btnWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.addsubView_btnWidth, LayoutParams.WRAP_CONTENT);
        int tvWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.addsubView_tvWidth, 50);
        int tvTextSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.addsubView_tvTextSize, 0);
        int btnTextSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.addsubView_btnTextSize, 0);
        minValue = obtainStyledAttributes.getInteger(R.styleable.addsubView_minValue,defaultMinValue);
        maxValue = obtainStyledAttributes.getInteger(R.styleable.addsubView_maxValue,defaultMaxValue);
        obtainStyledAttributes.recycle();

        LayoutParams btnParams = new LayoutParams(btnWidth, LayoutParams.MATCH_PARENT);
        btnAdd.setLayoutParams(btnParams);
        btnSubstract.setLayoutParams(btnParams);
        if (btnTextSize != 0) {
            btnAdd.setTextSize(TypedValue.COMPLEX_UNIT_PX, btnTextSize);
            btnSubstract.setTextSize(TypedValue.COMPLEX_UNIT_PX, btnTextSize);
        }

        LayoutParams textParams = new LayoutParams(tvWidth, LayoutParams.MATCH_PARENT);
        tvValue.setLayoutParams(textParams);
        if (tvTextSize != 0) {
            tvValue.setTextSize(tvTextSize);
        }
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn_add){
            add();
        }

        if (v.getId() == R.id.btn_substract){

            substract();
        }
    }

    private void add() {
        if (value < maxValue){
            this.value ++;
        }
        setValue(value);
    }
    private void substract() {
        if (value > minValue){
            this.value --;
        }
        setValue(value);
    }
}
