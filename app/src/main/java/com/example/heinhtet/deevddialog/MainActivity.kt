package com.example.heinhtet.deevddialog

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.heinhtet.deevd.deevdialog.dialog.DeevDialog
import com.heinhtet.deevd.deevdialog.dialog.DeevDialogAnimation
import com.heinhtet.deevd.deevdialog.dialog.DeevDialogCallback
import com.heinhtet.deevd.deevdialog.dialog.DeevDialogStyle

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mDialog: DeevDialog.Instance
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mDialog = DeevDialog.Instance
        progress_dialog.setOnClickListener {
            mDialog.
                    into(this@MainActivity, DeevDialogAnimation.FADE_ANIMATION).
                    setDarkTheme(true).
                    setStyle(DeevDialogStyle.PROGRESS).
                    setTitleText("Fatching...").
                    setMessage("My Loading...").
                    setProgressLoadingColorRes(R.color.progress_color).make()
        }

        custom_dialog.setOnClickListener {
            mDialog.into(this@MainActivity, DeevDialogAnimation.PUSH_ANIMATION)
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
        }
        message_dialog.setOnClickListener {
            //            mDialog.into(this@MainActivity, DeevDialogAnimation.PULSE_ANIMATION).
//                    setStyle(DeevDialogStyle.MESSAGE).
//                    setOnPositiveListener(object : DeevDialogCallback.onPositiveClickListener {
//                        override fun onClick(dialog: DeevDialog.Instance.DeevDialog) {
//                            mDialog.release()
//                            Toast.makeText(this@MainActivity, "click positive action", Toast.LENGTH_SHORT).show()
//                        }
//                    })
//                    .setOnNegativeClickListener(object : DeevDialogCallback.onNegativeClickListener {
//                        override fun onClick(dialog: DeevDialog.Instance.DeevD
// ialog) {
//                            Toast.makeText(this@MainActivity, "click negative  action", Toast.LENGTH_SHORT).show()
//                            mDialog.release()
//                        }
//                    })
//                    .setDarkTheme(true)
//                    .setMessage("Hello this is DeevDialog ..")
//                    .setBackgroundColorRes(R.color.dark_color)
//                    .setPositiveTextColorRes(R.color.title_text_color)
//                    .setNegativeTextColorRes(R.color.title_text_color)
//                    .setMessageTextColorRes(R.color.title_text_color)
//                    .setTitleColorRes(R.color.colorAccent)
//                    .setTitleText("DeevDialog")
//                    .setPositiveText("click ok")
//                    .setNegativeText("click cancel")
//                    .setCancelableDeev(false).
//                    make()


            DeevDialog.into(this, DeevDialogAnimation.FADE_ANIMATION)
                    .setMessage("Hello")
                    .setTitleText("DeevDialog")
                    .setStyle(DeevDialogStyle.MESSAGE)
                    .setPositiveText("ok")
                    .setDarkTheme(true)
                    .setOnPositiveListener(object : DeevDialogCallback.onPositiveClickListener {
                        override fun onClick(deevDialog: DeevDialog.Instance.DeevDialog) {
                            Toast.makeText(this@MainActivity, "click ok", Toast.LENGTH_SHORT).show()
                            deevDialog.dismiss()
                        }
                    })
                    .setNegativeText("cancel")
                    .make()

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mDialog.isShowing()) {
            mDialog.release()
        }
    }
}
