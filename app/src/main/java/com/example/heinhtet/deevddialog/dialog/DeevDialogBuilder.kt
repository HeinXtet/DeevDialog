package com.example.heinhtet.deevddialog.dialog

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log

/**
 * Created by heinhtet on 1/6/2018.
 */
class DeevDialogBuilder {
    companion object : DeevDialog.positiveListener {
        override fun onClick() {

        }


        var mMessage: String? = null
        var titile: String = ""
        var mPositive: String? = null
        var mNegative: String? = null
        @SuppressLint("StaticFieldLeak")
        lateinit var c: Activity

        fun a(t: String): Companion {
            this.titile = t
            Log.i("title", titile)
            return Companion
        }

        fun create(activity: Activity): Companion {
            this.c = activity
            return Companion
        }

        fun setMessage(text: String) {
            this.mMessage = text
        }

        fun setPositive(text: String) {
            this.mPositive = text
        }

        fun setNegative(text: String) {
            this.mNegative = text
        }

        fun build() {
            if (mMessage != null) {
                var dev = DeevDialog(c, DeevDialog.Message, mMessage, mPositive, mNegative, this)
                dev.show()
            }
        }

        fun setPositiveListener(click: DeevDialog.positiveListener) {
            click.onClick()
        }

    }

}
