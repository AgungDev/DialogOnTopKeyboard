package fun5i.module.dialogontopkeyboard;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

public class SimpleUIDua extends RelativeLayout {

    public void setTvMax(int Typing){
        MAX_LENGTH_TYPING = Typing;
        Log.d(TAG, "setTvMax: "+Typing);
        //reset
        maxText.setText(String.valueOf(MAX_LENGTH_TYPING));
        InputFilter[] filterArray = new InputFilter[1];
        filterArray[0] = new InputFilter.LengthFilter(MAX_LENGTH_TYPING);
        editText.setFilters(filterArray);
    }

    public ImageView sendBTNS(){
        return create;
    }
    public ImageView menuBTNS(){
        return menu;
    }

    //gets
    public String getMtext(){
        return editText.getText().toString();
    }

    private static final String TAG = "SimpleUISatu";

    //private Typeface headerFont, editFont, btnFont;
    private RelativeLayout root;
    private RelativeLayout.LayoutParams paramroot, paramsET, paramsETM, paramsCreate, paramsMenu;
    private TextView maxText;
    private EditText editText;
    private ImageView create, menu;

    //asdas
    private int MAX_LENGTH_TYPING = 40;
    private int PADINGX2 = 40;

    private void layoutSet(){
        //root
        paramroot = new RelativeLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
        );
        root.setPadding(pxFromDp(5), 0, pxFromDp(5),0);
        root.setLayoutParams(paramroot);
        root.setBackgroundResource(R.drawable.background_dua);

        // TODO: init CREATE BTN
        paramsCreate = new RelativeLayout.LayoutParams(layoutParamsWrap());
        create.setLayoutParams(paramsCreate);
        create.setImageResource(R.drawable.ic_create_task);
        create.setScaleType(ImageView.ScaleType.CENTER_CROP);

        // TODO: init Menu BTN
        paramsMenu = new RelativeLayout.LayoutParams(layoutParamsWrap());
        menu.setLayoutParams(paramsMenu);
        menu.setImageResource(R.drawable.ic_main_menu);
        menu.setScaleType(ImageView.ScaleType.FIT_START);

        //TODO: EditText init
        editText.setMaxLines(1);
        editText.setSingleLine(true);
        editText.setLines(1);
        paramsET = new RelativeLayout.LayoutParams(layoutParams());
        editText.setLayoutParams(paramsET);
        editText.setHint("Hello Worlds");
        editText.setTextSize(12f);
        editText.setLetterSpacing(0.2f);
        editText.setBackgroundResource(R.drawable.backround_edit_text_dua);
        InputFilter[] filterArray = new InputFilter[1];
        filterArray[0] = new InputFilter.LengthFilter(MAX_LENGTH_TYPING);
        editText.setFilters(filterArray);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                double dangerr = MAX_LENGTH_TYPING-persen(MAX_LENGTH_TYPING, 20);
                //dangerr *= 20;
                double warningg =  MAX_LENGTH_TYPING-persen(MAX_LENGTH_TYPING, 30);
                if (s.length() >= (dangerr)){
                    maxText.setTextSize(10f);
                    maxText.setTypeface(null, Typeface.BOLD);
                    maxText.setTextColor(Color.parseColor("#FF810E44"));
                }else if(s.length() >= (warningg)){
                    maxText.setTextSize(8f);
                    maxText.setTypeface(null, Typeface.BOLD);
                    maxText.setTextColor(Color.parseColor("#BAA640"));
                }else{
                    maxText.setTextSize(8f);
                    maxText.setTypeface(null, Typeface.NORMAL);
                    maxText.setTextColor(Color.BLACK);
                }
                //Log.d(TAG, "afterTextChanged: "+s.length()+" >= "+dangerr);
                maxText.setText(String.valueOf(MAX_LENGTH_TYPING-s.length()));
            }
        });

        //TODO : init max Text
        paramsETM = new RelativeLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
        );
        maxText.setLayoutParams(paramsETM);
        maxText.setText(String.valueOf(MAX_LENGTH_TYPING));
        maxText.setGravity(Gravity.RIGHT);
        maxText.setTextSize(8f);

        // TODO: Rules
        paramroot.topMargin = pxFromDp(50);

        paramsMenu.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        paramsCreate.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        paramsET.addRule(RelativeLayout.LEFT_OF, create.getId());
        paramsET.addRule(RelativeLayout.RIGHT_OF, menu.getId());
        //paramsET.height = SIZE_ALL/2+SIZE_ALL/3;
        //paramsET.topMargin = pxFromDp(12);

        paramsETM.topMargin = pxFromDp(5);
        paramsETM.leftMargin = pxFromDp(-15);
        paramsETM.addRule(RelativeLayout.RIGHT_OF, editText.getId());



    }

    private double persen(double a, double b){
        return (a/100)*b;
    }



    public SimpleUIDua(Context context) {
        super(context);
        setLayoutParams(layoutParams());
        setBackgroundColor(Color.TRANSPARENT);
        init();
        layoutSet();

        // add to root layouts
        Animation bounce = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);
        create.startAnimation(bounce);


        Animation rotate = AnimationUtils.loadAnimation(getContext(), R.anim.rotate);
        maxText.startAnimation(rotate);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                maxText.clearAnimation();
            }
        },500);


        addView(root);
        root.addView(menu);
        root.addView(editText);
        root.addView(maxText);
        root.addView(create);
    }

    public void setOutBackgroundColor(int colorbg){
        setBackgroundColor(colorbg);
    }

    public View getLayout(){
        return getRootView();
    }

    private void init(){
        root = new RelativeLayout(getContext());
        create = new ImageView(getContext());
        menu = new ImageView(getContext());
        maxText = new TextView(getContext());
        editText = new EditText(getContext());

        root.setId(generateViewId());
        menu.setId(generateViewId());
        editText.setId(generateViewId());
        maxText.setId(generateViewId());
        create.setId(generateViewId());
    }

    private int SIZE_ALL = 120;

    private RelativeLayout.LayoutParams layoutParams(){
        return new RelativeLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                SIZE_ALL
        );
    }

    private RelativeLayout.LayoutParams layoutParamsWrap(){
        return new RelativeLayout.LayoutParams(
                SIZE_ALL,
                SIZE_ALL
        );
    }

    private int dpFromPx( int px) {
        return Math.round(px / getResources().getDisplayMetrics().density);
    }

    private int pxFromDp(int dp) {
        return Math.round(dp * getResources().getDisplayMetrics().density);
    }

    private void setTextViewDrawableColor(TextView textView, int color) {
        for (Drawable drawable : textView.getCompoundDrawables()) {
            if (drawable != null) {
                drawable.setColorFilter(new PorterDuffColorFilter(ContextCompat.getColor(textView.getContext(), color), PorterDuff.Mode.SRC_IN));
            }
        }
    }
}
