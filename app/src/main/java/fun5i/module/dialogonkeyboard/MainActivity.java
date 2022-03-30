package fun5i.module.dialogonkeyboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import fun5i.module.dialogontopkeyboard.DialogKeyboards;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private DialogKeyboards dialogKeyboards;
    private CheckBox opSI;
    private FloatingActionButton btn;
    private int inis;

    //layout
    //private UI ui = new UI(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setContentView(ui.getRootView());

        btn = findViewById(R.id.btn);
        opSI = findViewById(R.id.cek);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (opSI.isChecked()){
                    resDial();
                }else{
                    simDial();
                }
            }
        });
    }


    private void simDial(){
        dialogKeyboards = new DialogKeyboards(MainActivity.this, DialogKeyboards.SAMPLE_UI_SATU);
        dialogKeyboards.create();

        //setup
        dialogKeyboards.sampleUISatuLayout().setTvMax(45);
        dialogKeyboards.sampleUISatuLayout().changeClockText("00:00");
        dialogKeyboards.onBtnSendClicked(new DialogKeyboards.BtnSendClick() {
            @Override
            public void onSendBtn(String clock, boolean notification, String mText) {
                Toast.makeText(MainActivity.this, "{"+notification+"}"+mText, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClockClick(TextView tv) {
                Toast.makeText(MainActivity.this, "Clcok Clicked!", Toast.LENGTH_SHORT).show();
                tv.setText("12:43");
            }
        });
    }




    private void resDial(){
        //dialogKeyboards = new DialogKeyboards(MainActivity.this, DialogKeyboards.SAMPLE_UI_SATU);
        dialogKeyboards = new DialogKeyboards(MainActivity.this, R.layout.contoh);
        dialogKeyboards.create();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}