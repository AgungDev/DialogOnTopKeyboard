# DialogOnTopKeyboard
Simple Component Dialog

### Step 1. Add the repository & dependency
Add it in your root build.gradle at the end of repositories:
```java
  allprojects {
    repositories {
      
      maven { url 'https://jitpack.io' }
    }
  }
```

and dapedency
```java
  dependencies {
          implementation 'com.github.AgungDev:DialogOnTopKeyboard:1.0'
  }
```

### Step 1. Add MainActivity

```java
//setup
DialogKeyboards dialogKeyboards;
dialogKeyboards = new DialogKeyboards(MainActivity.this, DialogKeyboards.SAMPLE_UI_SATU);
dialogKeyboards.create();

//function
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
```
