package com.example.heinhtet.deevddialog

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var mDialog: DeevDialog.Companion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mDialog = DeevDialog.Companion


        progress_dialog.setOnClickListener {
            com.example.heinhtet.deevddialog.DeevDialog.
                    into(this@MainActivity, DeevDialog.FADE_ANIMATION).
                    setStyle(DeevDialog.PROGRESS).setTitleText("Fatching...").
                    setMessage("My Loading...").setProgressLoadingColorRes(R.color.progress_color).show()
        }

        custom_dialog.setOnClickListener {
            mDialog.into(this@MainActivity, DeevDialog.PUSH_ANIMATION)
                    .setStyle(DeevDialog.CUSTOM)
                    .setCustomView(R.layout.dialog_layout)
                    .setCustomViewCallback(object : DeevDialog.Companion.CustomViewRenderingListener {
                        override fun bindView(dialog: DeevDialog.Companion.DeevDialog) {
                            var z = dialog.findViewById<TextView>(R.id.txt_dia)
                            z.text = "This is custom DeevDialog"
                            var ok = dialog.findViewById<Button>(R.id.btn_yes)
                            ok.setOnClickListener { Toast.makeText(this@MainActivity,"Click Ok",Toast.LENGTH_SHORT).show() }
                        }
                    }).show()
        }
        message_dialog.setOnClickListener {
            mDialog.into(this@MainActivity, DeevDialog.PULSE_ANIMATION).
                    setStyle(DeevDialog.MESSAGE).
                    setOnPositiveListener(object : DeevDialog.Companion.onPositiveClickListener {
                        override fun onClick(dialog: DeevDialog.Companion.DeevDialog) {
                            mDialog.dismiss()
                            Toast.makeText(this@MainActivity, "adfadf", Toast.LENGTH_SHORT).show()
                        }
                    })
                    .setOnNegativeClickListener(object : DeevDialog.Companion.onNegativeClickListener {
                        override fun onClick(ada: DeevDialog.Companion.DeevDialog) {
                            mDialog.dismiss()
                        }
                    })
                    .setMessage("This is DeevDialog!!")
                    .setPositiveTextColorRes(R.color.message_color)
                    .setNegativeTextColorRes(R.color.message_color)
                    .setMessageTextColorRes(R.color.message_color)
                    .setTitleColorRes(R.color.title_text_color)
                    .setTitleText("This is DeevDialog")
                    .setBackgroundColorRes(R.color.colorAccent)
                    .setPositiveText("click ok")
                    .setNegativeText("click cancel")
                    .setCancelableDeev(false)
                    .show()
        }
    }
}
