package com.example.heinhtet.deevddialog

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.Window
import kotlinx.android.synthetic.main.message_dialog_layout.*
import kotlinx.android.synthetic.main.progress_dialog_view.*
import android.graphics.PorterDuff
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.annotation.LayoutRes
import android.util.Log
import android.view.LayoutInflater
import android.widget.ProgressBar


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
        private var cancelable: Boolean? = true
        private var customLayout: Int? = null


        private lateinit var mPositiveClick: onPositiveClickListener
        private lateinit var mNegativeClick: onNegativeClickListener
        lateinit var mDialog: DeevDialog
        lateinit var mActivity: Context
        private lateinit var customViewRenderring: CustomViewRenderingListener


        // theme
        private var bgColorRes: Int? = null
        private var titleColorRes: Int? = null
        private var messageColorRes: Int? = null
        private var mPColor: Int? = null
        private var mNColor: Int? = null
        private var isDarkTheme: Boolean? = null
        private var mProgressColor: Int? = null


        var PROGRESS = 3232
        var MESSAGE = 233
        var CUSTOM = 2342

        var PULSE_ANIMATION = R.style.pulseDialogAnimation
        var PUSH_ANIMATION = R.style.pushDialogAnimation
        var FADE_ANIMATION = R.style.fadeDialogAnimation

        class DeevDialog(activity: Activity, animation: Int) : Dialog(activity, animation), View.OnClickListener {

            fun DeevDialog(activity: Activity, animation: Int, layout: Int) {

            }


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
                checkCancelable()
            }

            private fun checkCancelable() {
                if (cancelable != null) {
                    if (cancelable!!) {
                        this.setCancelable(true)
                    } else {
                        this.setCancelable(false)
                    }
                }
            }

            private fun style() {
                when (dialogStyle) {
                    PROGRESS -> {
                        setLayout(R.layout.progress_dialog_view)
                        setViewForProgress()
                    }
                    MESSAGE -> {
                        setLayout(R.layout.message_dialog_layout)
                        setViewForMessage()
                    }
                    CUSTOM -> {
                        if (customLayout != null) {
                            setLayout(customLayout!!)
                            setViewForCustomLayout()
                        } else {
                            Log.i("DeevDialog", "need to set setCustomLayout")
                        }
                    }
                }
            }

            private fun setViewForCustomLayout() {
                customViewRenderring.bindView(mDialog)
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
                if (mProgressColor != null) {
                    changeProgressBarLoadingColor(loading)
                }
                if (mTitle != null) {
                    progress_title_tv.text = mTitle
                } else {
                    progress_title_tv.text = "Loading..."

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

            fun setCancelableDeev(flag: Boolean): DeevDialog {
                cancelable = flag
                return this
            }


            fun setStyle(deevDialogStyle: Int): DeevDialog {
                dialogStyle = deevDialogStyle
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
                titleColorRes = tColor
                return this
            }

            fun setCustomView(@LayoutRes layout: Int): DeevDialog {
                customLayout = layout
                return this
            }

            fun setCustomViewCallback(renderingListener: CustomViewRenderingListener): DeevDialog {
                customViewRenderring = renderingListener
                return this
            }

            fun setMessageTextColorRes(mColor: Int): DeevDialog {
                messageColorRes = mColor
                return this
            }

            fun setProgressLoadingColorRes(proColor: Int): DeevDialog {
                mProgressColor = proColor
                return this
            }

            fun setDarkTheme(darkFlag: Boolean): DeevDialog {
                isDarkTheme = darkFlag
                return this
            }


        }

        fun into(activity: Activity, animation: Int?): DeevDialog {
            mActivity = activity
            if (animation != null) {
                return DeevDialog(activity, animation)
            } else {
                return Companion.DeevDialog(activity, R.style.fadeDialogAnimation)
            }
        }


        fun dismiss() {
            if (mDialog.isShowing) {
                setDefault()
                mDialog.dismiss()
            }
        }

        fun changeProgressBarLoadingColor(mProgressBar: ProgressBar) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                val wrapDrawable = DrawableCompat.wrap(mProgressBar.getIndeterminateDrawable())
                DrawableCompat.setTint(wrapDrawable, ContextCompat.getColor(mActivity, mProgressColor!!))
                mProgressBar.indeterminateDrawable = DrawableCompat.unwrap<Drawable>(wrapDrawable)
            } else {
                mProgressBar.indeterminateDrawable.setColorFilter(ContextCompat.getColor(mActivity, mProgressColor!!), PorterDuff.Mode.SRC_IN)
            }
        }

        private fun setDefault() {
            mNegativeText = null
            mMessage = null
            mPositiveText = null
            mTitle = null
            cancelable = true
            mProgressColor = null
        }

        interface onPositiveClickListener {
            fun onClick(dialog: DeevDialog)
        }

        interface onNegativeClickListener {
            fun onClick(dialog: DeevDialog)
        }

        interface CustomViewRenderingListener {
            fun bindView(dialog: DeevDialog)
        }


    }
}