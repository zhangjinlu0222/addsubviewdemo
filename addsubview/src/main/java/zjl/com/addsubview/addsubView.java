package zjl.com.addsubview;


import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class addsubView extends LinearLayout implements View.OnClickListener, TextWatcher {

    public int value = 0;
    public int defaultMinValue = 0;
    public int defaultMaxValue = 999;
    public int minValue,maxValue;
    public int defaultWidth = 100;
    public int defaultEtTextSize = 18;
    public int defaultBtnTextSize = 36;

    public Button btnAdd,btnSubstract;
    public EditText etValue;
    private ValueChangeListener valueChangeListener;
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String temp = s.toString() ;
        if (temp.length() > 3){
            temp = temp.substring(0,3);
            etValue.setText(temp );
        }
        etValue.setSelection(temp.length());
        if (!TextUtils.isEmpty(temp)){
            this.value = Integer.valueOf(temp);
        }else{
            this.value = 0;
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    public interface ValueChangeListener{
        void onValueChange(View view, int value);
    }

    public void setValueChangeListener(ValueChangeListener valueChangeListener){
        this.valueChangeListener = valueChangeListener;
    }

    public int getValue() {
        String sValue = etValue.getText().toString().trim();
        if (!TextUtils.isEmpty(sValue)){
            value = Integer.valueOf(sValue);
        }
        return value;
    }

    public void setValue(int value) {
        if (valueChangeListener != null){
            valueChangeListener.onValueChange(this,value);
            this.value = value;
            etValue.setText(this.value + "");
        }else{
            Toast.makeText(getContext(),"please add valueChangeListener" ,Toast.LENGTH_SHORT ).show();
        }
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
        etValue = (EditText) findViewById(R.id.et_value);

        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attrs, R.styleable.addsubView);
        int btnWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.addsubView_btnWidth, defaultWidth);
        int tvWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.addsubView_tvWidth, defaultWidth);
        int tvTextSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.addsubView_tvTextSize, defaultEtTextSize);
        int btnTextSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.addsubView_btnTextSize, defaultBtnTextSize);
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
        etValue.setLayoutParams(textParams);
        if (tvTextSize != 0) {
            etValue.setTextSize(tvTextSize);
        }

        etValue.addTextChangedListener(this);
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
