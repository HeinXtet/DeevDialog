## DeevDialog

This is simple android dialog  open source library witten with kotlin.



## How to use

### Preparation

1. Add project level Gradle 

   ```kotlin

   allprojects {
   		repositories {
   			maven { url 'https://jitpack.io' }
   		}
   	}

   ```

​      

2. Add add level Gradle

   ```kotlin

   dependencies {
   	       compile 'com.github.HeinXtet:DeevDialog:0.1.2'
   	}
   ```

   ​      

## 1 . Progreess Syle

```kotlin

DeevDialog mDialog = DeevDialog.Instance

 mDialog.
		into(this@MainActivity, DeevDialogAnimation.FADE_ANIMATION).
        setStyle(DeevDialogStyle.PROGRESS).setTitleText("Fatching...").
        setMessage("Loading...").
		setProgressLoadingColorRes(R.color.progress_color).
		make()


```

## 2. Message Alert Style

```kotlin

DeevDialog mDialog = DeevDialog.Instance
 mDialog.
		into(this@MainActivity, DeevDialogAnimation.PULSE_ANIMATION).
        setStyle(DeevDialogStyle.MESSAGE).
        setOnPositiveListener(object : DeevDialogCallback.onPositiveClickListener {
                        override fun onClick(dialog: DeevDialog.Instance.DeevDialog) {
                            mDialog.release()
                            Toast.makeText(this@MainActivity, "click positive action",Toast.LENGTH_SHORT).show()
                        }
                    })
       .setOnNegativeClickListener(object : DeevDialogCallback.onNegativeClickListener {
                        override fun onClick(dialog: DeevDialog.Instance.DeevDialog) {
                            Toast.makeText(this@MainActivity, "click negative  action",Toast.LENGTH_SHORT).show()
                            mDialog.release()
                        }
                    })
      .setDarkTheme(true)
      .setMessage("Hello this is DeevDialog ..")
      .setBackgroundColorRes(R.color.dark_color)
      .setPositiveTextColorRes(R.color.title_text_color)
      .setNegativeTextColorRes(R.color.title_text_color)
      .setMessageTextColorRes(R.color.title_text_color)
      .setTitleColorRes(R.color.colorAccent)
      .setTitleText("DeevDialog")
      .setPositiveText("click ok")
      .setNegativeText("click cancel")
      .setCancelableDeev(false).
       make()

```



## Custom View Dialog

```kotlin

DeevDialog mDialog = DeevDialog.Instance

  mDialog.
	     into(this@MainActivity, DeevDialogAnimation.PUSH_ANIMATION)
         .setStyle(DeevDialogStyle.CUSTOM)
         .setCustomView(R.layout.dialog_layout)
         .setCustomViewCallback(object : DeevDialogCallback.CustomViewRenderingListener {
                        override fun onBind(dialog: DeevDialog.Instance.DeevDialog) {
                            var z = dialog.findViewById<TextView>(R.id.txt_dia)
                            z.text = "This is custom DeevDialog"
                            var ok = dialog.findViewById<Button>(R.id.btn_yes)
                            var cancel = dialog.findViewById<Button>(R.id.btn_no)

                            cancel.setOnClickListener {
                                mDialog.release()
                                Toast.makeText(this@MainActivity, "Click cancel", Toast.LENGTH_SHORT).show()

                            }
                            ok.setOnClickListener {
                                mDialog.release()
                                Toast.makeText(this@MainActivity, "Click Ok", Toast.LENGTH_SHORT).show()
                            }

                        }
                    }).make()


```



### Don't forget

```kotlin
override fun onDestroy() {
    super.onDestroy()
    if (mDialog.isShowing()) {
        mDialog.release()
    }
}
```



### License

```kotlin
MIT License

Copyright (c) 2018 DevD

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```



