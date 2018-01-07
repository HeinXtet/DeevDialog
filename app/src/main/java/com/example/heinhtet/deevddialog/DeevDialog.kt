package com.example.heinhtet.deevddialog

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.view.View
import android.view.Window
import kotlinx.android.synthetic.main.a.*
import kotlinx.android.synthetic.main.progress_dialog_view.*
import android.graphics.LightingColorFilter


/**
 * Created by heinhtet on 1/7/2018.
 */

class DeevDialog {


    companion object {

        private var mPositiveText: String? = null
        private var dialogStyle: Int = 0
        private var mNegativeText: String? = null
        private var mMessage: String? = null
        private var mTitle: String? = null


        private lateinit var mPositiveClick: onPositiveClickListener
        private lateinit var mNegativeClick: onNegativeClickListener
        lateinit var mDialog: DeevDialog


        // theme
        var bgColorRes: Int? = null
        var titleColorRes: Int? = null
        var messageColorRes: Int? = null
        var mPColor: Int? = null
        var mNColor: Int? = null


        var PROGRESS = 3232
        var MESSAGE = 233

        var PULSE_ANIMATION = R.style.pulseDialogAnimation

        class DeevDialog(activity: Activity, animation: Int) : Dialog(activity, animation), View.OnClickListener {
            override fun onClick(p0: View?) {
                when (p0?.id) {
                    R.id.cancel_action -> {
                        mPositiveClick.onClick(this)
                    }
                    R.id.ok_btn -> {
                        mNegativeClick.onClick(this)
                    }
                }
            }

            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                requestWindowFeature(Window.FEATURE_NO_TITLE)
                mDialog = this
                style()
            }

            private fun style() {
                when (dialogStyle) {
                    PROGRESS -> {
                        setLayout(R.layout.progress_dialog_view)
                        setViewForProgress()
                    }
                    MESSAGE -> {
                        setLayout(R.layout.a)
                        setViewForMessage()
                    }
                }
            }

            private fun setViewForMessage() {

                checkTheme()

                if (mMessage != null) {
                    text_dialog.text = mMessage
                }
                if (mPositiveText != null) {
                    ok_btn.text = mPositiveText
                }
                if (mNegativeText != null) {
                    cancel_action.text = mNegativeText
                }
                if (mTitle != null) {
                    title_tv.text = mTitle
                } else {
                    title_tv.visibility = View.GONE
                }
                cancel_action.setOnClickListener(this)
                ok_btn.setOnClickListener(this)
            }

            private fun checkTheme() {
                if (bgColorRes != null) {
                    message_dialog_layout.setBackgroundResource(bgColorRes!!)
                }
                if (titleColorRes != null) {
                    title_tv.setTextColor(titleColorRes!!)
                }
                if (messageColorRes != null) {
                    text_dialog.setTextColor(messageColorRes!!)
                }

                if (mPColor != null) {
                    ok_btn.setTextColor(mPColor!!)
                }
                if (mNColor != null) {
                    cancel_action.setTextColor(mNColor!!)
                }
            }

            private fun setViewForProgress() {
                if (mMessage != null) {
                    loading_tv.text = mMessage
                } else {
                    loading_tv.text = "Loading...."
                }
            }

            private fun setLayout(layout: Int) {
                setContentView(layout)
            }

            fun setMessage(message: String): DeevDialog {
                mMessage = message
                return this
            }

            fun setPositiveText(positiveText: String): DeevDialog {
                mPositiveText = positiveText
                return this
            }


            fun setStyle(deevDialogStyle: Int, message: String?): DeevDialog {
                dialogStyle = deevDialogStyle
                mMessage = message
                return this
            }

            fun setTitleText(title: String): DeevDialog {
                mTitle = title
                return this
            }

            fun setNegativeText(negativeText: String): DeevDialog {
                mNegativeText = negativeText
                return this
            }

            fun setNegativeTextColorRes(nColor: Int): DeevDialog {
                mNColor = nColor
                return this
            }

            fun setPositiveTextColorRes(pColor: Int): DeevDialog {
                mPColor = pColor
                return this
            }


            fun setOnPositiveListener(listener: onPositiveClickListener): DeevDialog {
                mPositiveClick = listener
                return this
            }

            fun setOnNegativeClickListener(listener: onNegativeClickListener): DeevDialog {
                mNegativeClick = listener
                return this
            }

            fun setBackgroundColorRes(bg: Int): DeevDialog {
                bgColorRes = bg
                return this
            }

            fun setTitleColorRes(tColor: Int): DeevDialog {
                titleColorRes = titleColorRes
                return this
            }

            fun setMessageTextColorRes(mColor: Int): DeevDialog {
                messageColorRes = mColor
                return this
            }


        }

        fun into(activity: Activity, animation: Int?): DeevDialog {
            if (animation != null) {
                return DeevDialog(activity, animation)
            } else {
                return Companion.DeevDialog(activity, R.style.PauseDialog)
            }
        }

        fun dissmiss() {
            if (mDialog.isShowing) {
                setDefault()
                mDialog.dismiss()
            }
        }

        fun setDefault() {
            mNegativeText = null
            mMessage = null
            mPositiveText = null
            mTitle = null
        }

        interface onPositiveClickListener {
            fun onClick(dialog: DeevDialog)
        }

        interface onNegativeClickListener {
            fun onClick(dialog: DeevDialog)
        }


    }
}