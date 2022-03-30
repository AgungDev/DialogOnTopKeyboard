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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

public class SampleUISatu extends RelativeLayout {

    //setup interface
    /*public interface SetTextView {
        void setTvMax(int Typing);
    }
    private SetTextView setTextView;
    public void onSetTextView(SetTextView e){
        setTextView = e;
    }*/

    public void setTvMax(int Typing){
        MAX_LENGTH_TYPING = Typing;
        Log.d(TAG, "setTvMax: "+Typing);
        //reset
        maxText.setText(String.valueOf(MAX_LENGTH_TYPING));
        InputFilter[] filterArray = new InputFilter[1];
        filterArray[0] = new InputFilter.LengthFilter(MAX_LENGTH_TYPING);
        editText.setFilters(filterArray);
    }

    public Button sendBTNS(){
        return btn;
    }

    //gets
    public boolean getNotif(){
        return NOTIFF;
    }
    public String getCloksds(){
        return CLOCKSS;
    }
    public String getMtext(){
        return editText.getText().toString();
    }

    public void changeClockText(String clock){
        timeTv.setText(clock);
    }

    public TextView clockView(){
        return timeTv;
    }

    private static final String TAG = "SimpleUISatu";

    //private Typeface headerFont, editFont, btnFont;
    private RelativeLayout root;
    private RelativeLayout.LayoutParams paramroot;
    private TextView timeTv, notifTv, maxText;
    private EditText editText;
    private Button btn;

    //asdas
    private int MAX_LENGTH_TYPING = 40;
    private boolean NOTIFF;
    private String CLOCKSS = "00:00";

    private void layoutSet(){
        //root
        paramroot = new RelativeLayout.LayoutParams(layoutParams());
        root.setPadding(pxFromDp(25), 0, pxFromDp(25),0);
        root.setLayoutParams(paramroot);
        paramroot.topMargin = pxFromDp(25);
        root.setBackgroundResource(R.drawable.background_root);

        //btn
        paramroot = new RelativeLayout.LayoutParams(layoutParamsWrap());
        btn.setLayoutParams(paramroot);
        paramroot.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        paramroot.rightMargin = pxFromDp(20);
        btn.setText("Send");

        //TextView time
        timeTv.setId(View.generateViewId());
        paramroot = new RelativeLayout.LayoutParams(layoutParamsWrap());
        timeTv.setLayoutParams(paramroot);
        paramroot.topMargin = pxFromDp(12);
        timeTv.setGravity(Gravity.CENTER_VERTICAL);
        timeTv.setText("00:00");
        timeTv.setTextSize(10f);
        timeTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_time, 0, 0, 0);
        timeTv.setCompoundDrawablePadding(pxFromDp(5));
        timeTv.setTextColor(getResources().getColor(R.color.active));

        timeTv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do somthing
            }
        });

        //textview notif
        notifTv.setId(View.generateViewId());
        paramroot = new RelativeLayout.LayoutParams(layoutParamsWrap());
        notifTv.setLayoutParams(paramroot);
        paramroot.addRule(RelativeLayout.RIGHT_OF, timeTv.getId());
        paramroot.topMargin = pxFromDp(12);
        paramroot.leftMargin = pxFromDp(10);
        notifTv.setText("Notifications");
        notifTv.setGravity(Gravity.CENTER_VERTICAL);
        notifTv.setTextSize(10f);
        notifTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_notifications, 0, 0, 0);
        notifTv.setCompoundDrawablePadding(pxFromDp(5));
        notifTv.setTextColor(getResources().getColor(R.color.disabled));
        setTextViewDrawableColor(notifTv, R.color.disabled);

        notifTv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                notifTv.setTextColor(getResources().getColor(
                        (NOTIFF)?R.color.disabled:R.color.active
                ));

                if (NOTIFF){
                    setTextViewDrawableColor(notifTv, R.color.disabled);
                    NOTIFF=false;
                }else {
                    setTextViewDrawableColor(notifTv, R.color.active);
                    NOTIFF=true;
                }
            }
        });

        //textview notif
        maxText.setId(View.generateViewId());
        maxText.setBackgroundResource(R.drawable.background_root);
        paramroot = new RelativeLayout.LayoutParams(layoutParamsWrap());
        maxText.setLayoutParams(paramroot);
        paramroot.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        paramroot.addRule(RelativeLayout.BELOW, notifTv.getId());
        paramroot.topMargin = pxFromDp(35);
        paramroot.leftMargin = pxFromDp(10);
        maxText.setText(String.valueOf(MAX_LENGTH_TYPING));
        maxText.setGravity(Gravity.RIGHT);
        maxText.setTextSize(8f);

        //EditText
        editText.setId(View.generateViewId());
        editText.setMaxLines(1);
        editText.setSingleLine(true);
        editText.setLines(1);
        paramroot = new RelativeLayout.LayoutParams(layoutParams());
        editText.setLayoutParams(paramroot);
        paramroot.topMargin = pxFromDp(12);
        paramroot.addRule(RelativeLayout.BELOW, timeTv.getId());
        editText.setHint("Hello Worlds");
        editText.setTextSize(12f);
        editText.setLetterSpacing(0.2f);
        editText.setBackgroundResource(R.drawable.backround_edit_text);

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

    }

    private double persen(double a, double b){
        return (a/100)*b;
    }



    public SampleUISatu(Context context) {
        super(context);
        setLayoutParams(layoutParams());
        setBackgroundColor(Color.TRANSPARENT);
        init();
        layoutSet();

        // add to root layouts
        Animation bounce = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);
        btn.startAnimation(bounce);

        Animation righttoleft = AnimationUtils.loadAnimation(getContext(), R.anim.righttoleft);
        notifTv.startAnimation(righttoleft);

        Animation rotate = AnimationUtils.loadAnimation(getContext(), R.anim.rotate);
        maxText.startAnimation(rotate);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                maxText.clearAnimation();
            }
        },500);


        addView(root);
        addView(btn);
        root.addView(timeTv);
        root.addView(notifTv);
        root.addView(editText);
        root.addView(maxText);
    }

    public void setOutBackgroundColor(int colorbg){
        setBackgroundColor(colorbg);
    }

    public View getLayout(){
        return getRootView();
    }

    private void init(){
        root = new RelativeLayout(getContext());
        btn = new Button(getContext());
        timeTv = new TextView(getContext());
        notifTv = new TextView(getContext());
        maxText = new TextView(getContext());
        editText = new EditText(getContext());
    }

    private RelativeLayout.LayoutParams layoutParams(){
        return new RelativeLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
        );
    }

    private RelativeLayout.LayoutParams layoutParamsWrap(){
        return new RelativeLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
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
