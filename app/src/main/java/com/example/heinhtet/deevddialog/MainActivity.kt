package com.example.heinhtet.deevddialog

import android.app.Dialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var dialog: DeevDialog.Companion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dialog = DeevDialog.Companion


        progress_dialog.setOnClickListener {
            com.example.heinhtet.deevddialog.DeevDialog.
                    into(this@MainActivity, null).
                    setStyle(DeevDialog.PROGRESS, "Loading....").
                    setMessage("My Loading...").show()
        }

        custom_dialog.setOnClickListener {
            dialog.into(this@MainActivity, DeevDialog.PULSE_ANIMATION).
                    setStyle(DeevDialog.MESSAGE, "Progress View").
                    setOnPositiveListener(object : DeevDialog.Companion.onPositiveClickListener {
                        override fun onClick(dialog: DeevDialog.Companion.DeevDialog) {
                            dialog.dismiss()
                            Toast.makeText(this@MainActivity, "adfadf", Toast.LENGTH_SHORT).show()
                        }
                    })
                    .setOnNegativeClickListener(object : DeevDialog.Companion.onNegativeClickListener {
                        override fun onClick(ada: DeevDialog.Companion.DeevDialog) {
                            dialog.dissmiss()
                        }
                    })
                    .setPositiveTextColorRes(R.color.message_color)
                    .setNegativeTextColorRes(R.color.message_color  )
                    .setMessageTextColorRes(R.color.message_color)
                    .setTitleColorRes(R.color.title_text_color)
                    .setTitleText("This is DeevDialog")
                    .setBackgroundColorRes(R.color.colorAccent)
                    .setPositiveText("click ok")
                    .setNegativeText("click cancel")
                    .show()
        }
    }
}
