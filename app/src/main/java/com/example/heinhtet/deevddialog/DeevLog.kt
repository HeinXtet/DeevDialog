package com.example.heinhtet.deevddialog

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.heinhtet.deevddialog.R.id.txt_dia

/**
 * Created by heinhtet on 1/7/2018.
 */
class DeevLog {

    companion object {
        lateinit var mMessage: String
        lateinit var mActivity: Activity
        lateinit var mPositiveClick: onPositiveListener
        var mType: Int = 0
        lateinit var yesBtn: Button
        lateinit var text: TextView

        class DeevLog(activity: Activity) : Dialog(activity), View.OnClickListener {

            override fun onClick(p0: View) {
                when (p0.id) {
                    R.id.btn_yes -> {
                        mPositiveClick.onClick()
                    }
                }
            }

            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.dialog_layout)
                yesBtn = findViewById(R.id.btn_yes)
                text = findViewById(R.id.txt_dia)
                yesBtn.setOnClickListener(this)


                if (mMessage != null) {
                    text.text = mMessage
                }

            }
        }

        fun into(activity: Activity): DeevLog {
            mActivity = activity

            return DeevLog(mActivity)
        }

        fun setMessage(message: String): Companion {
            mMessage = message
            return this
        }

        fun setOnPositiveListener(listener: onPositiveListener): Companion {
            mPositiveClick = listener
            return this
        }

        fun setStyle(deevDialogStyle: Int): Companion {
            mType = deevDialogStyle
            return this
        }

        interface onPositiveListener {
            fun onClick()
        }


    }

}