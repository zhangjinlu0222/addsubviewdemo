# addsubviewdem
>  是一个数量加减控件，简单好用.

## 1.在build.gralde中添加
```

allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'https://jitpack.io' }
    }
}


dependencies {
    ...
    implementation 'com.github.zhangjinlu0222:addsubviewdemo:v0.0.4'
}
```
## 2.在布局文件中添加控件
```
    <zjl.com.addsubview.addsubView
        android:id="@+id/addsubview"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:tvWidth="50dp"
        app:btnWidth="50dp"
        app:maxValue="100"
        app:minValue="1"
        app:tvTextSize="16sp"
        app:btnTextSize="16sp"/>
```
## 3.在Activity中添加
```
public class test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        addsubView view = findViewById(R.id.addsubview);
        view.setValueChangeListener(new addsubView.ValueChangeListener() {
            @Override
            public void onValueChange(View view, int value) {
                Log.i("TAG",value+"" );
            }
        });
    }
}
```
