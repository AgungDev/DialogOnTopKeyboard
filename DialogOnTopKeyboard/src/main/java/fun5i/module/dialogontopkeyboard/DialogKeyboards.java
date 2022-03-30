package fun5i.module.dialogontopkeyboard;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.InputFilter;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DialogKeyboards extends Dialog {

    //interface
    public interface BtnSendClick{
        void onSendBtn(String clock, boolean notification, String mText);
        void onClockClick(TextView tv);
    }

    //init anonymous class
    private BtnSendClick interfaceDialogSample;
    public void onBtnSendClicked(BtnSendClick inter){
        interfaceDialogSample = inter;
    }


    //btn send
    private void onSendBtn(boolean notif, String clock, String mText){
        interfaceDialogSample.onSendBtn(clock, notif, mText);
    }

    //btn clock
    private void onClockClick(TextView v){
        interfaceDialogSample.onClockClick(v);
    }

    private static final String TAG = "DialogKeyboards";

    public static final int SAMPLE_UI_SATU = 123123;
    private SampleUISatu sampleUiSatu;

    public DialogKeyboards(Context context, int layout) {
        super(context);

        if (layout == SAMPLE_UI_SATU){
            sampleUiSatu = new SampleUISatu(context);
            setContentView(sampleUiSatu);
            simpleeee();
        }else{
            setContentView(layout);
        }

    }

    //component

    private void simpleeee(){
        Button bb = sampleUiSatu.sendBTNS();
        TextView clockSS = sampleUiSatu.clockView();
        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSendBtn(
                        sampleUiSatu.getNotif(),
                        sampleUiSatu.getCloksds(),
                        sampleUiSatu.getMtext()
                );
            }
        });

        clockSS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClockClick(clockSS);
            }
        });
    }

    public SampleUISatu sampleUISatuLayout(){
        return sampleUiSatu;
    }

    /*@Override
    public void setOnDismissListener(@Nullable OnDismissListener listener) {
        super.setOnDismissListener(listener);
        listener.onDismiss(new DialogInterface() {
            @Override
            public void cancel() {
                Toast.makeText(getContext(), "cencel", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void dismiss() {
                Toast.makeText(getContext(), "dismiss", Toast.LENGTH_SHORT).show();
            }
        });
    }*/

    public void create(){

        WindowManager.LayoutParams lWindowParams = new WindowManager.LayoutParams();
        lWindowParams.copyFrom(getWindow().getAttributes());
        lWindowParams.width = WindowManager.LayoutParams.MATCH_PARENT; // this is where the magic happens
        lWindowParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(lWindowParams);

        //position bottom
        Window window = getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.BOTTOM;
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);



        /*Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.BOTTOM;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);*/

        // Setting dialogview
        /*Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);

        window.setLayout(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
        dialog.setTitle(null);
        dialog.setContentView(R.layout.selectpic_dialog);
        dialog.setCancelable(true);
*/

        show();

    }

}
