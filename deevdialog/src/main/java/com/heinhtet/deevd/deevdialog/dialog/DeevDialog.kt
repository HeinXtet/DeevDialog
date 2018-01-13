package com.heinhtet.deevd.deevdialog.dialog

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.Window
import android.graphics.PorterDuff
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.annotation.LayoutRes
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import com.heinhtet.deevd.deevdialog.R
import kotlinx.android.synthetic.main.message_dialog_layout.*
import kotlinx.android.synthetic.main.progress_dialog_view.*


/**
 * Created by heinhtet on 1/7/2018.
 */

class DeevDialog {
    companion object Instance {
        private var mPositiveText: String? = null
        private var dialogStyle: Int = 0
        private var mNegativeText: String? = null
        private var mMessage: String? = null
        private var mTitle: String? = null
        private var cancelable: Boolean? = true
        private var customLayout: Int? = null


        private var mPositiveClick: DeevDialogCallback.onPositiveClickListener? = null
        private var mNegativeClick: DeevDialogCallback.onNegativeClickListener? = null
        private var mDialog: DeevDialog? = null
        private lateinit var mActivity: Context
        private var customViewRenderring: DeevDialogCallback.CustomViewRenderingListener? = null


        // theme
        private var bgColorRes: Int? = null
        private var titleColorRes: Int? = null
        private var messageColorRes: Int? = null
        private var mPColor: Int? = null
        private var mNColor: Int? = null
        private var isDarkTheme: Boolean? = null
        private var mProgressColor: Int? = null


        //viewGroup


        class DeevDialog(activity: Activity, animation: Int) : Dialog(activity, animation), View.OnClickListener {

            override fun onClick(p0: View?) {
                when (p0?.id) {
                    R.id.cancel_action -> {

                        if (mNegativeClick != null) {
                            mNegativeClick?.onClick(this)
                        } else {
                            this.dismiss()
                        }

                    }
                    R.id.ok_btn -> {
                        if (mPositiveClick != null) {
                            mPositiveClick?.onClick(this)

                        } else {
                            this.dismiss()
                        }
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

            private fun getDarkTheme(type: Int) {
                when (type) {
                    DeevDialogStyle.PROGRESS -> {

                        if (bgColorRes != null) {
                            progress_layout.setBackgroundColor(ContextCompat.getColor(mActivity, bgColorRes!!))

                        } else {
                            progress_layout.setBackgroundColor(Color.BLACK)
                        }

                        if (titleColorRes != null) {
                            progress_title_tv.setTextColor(ContextCompat.getColor(mActivity, titleColorRes!!))
                        } else {
                            progress_title_tv.setTextColor(ContextCompat.getColor(mActivity, R.color.title_text_color))
                        }

                        if (messageColorRes != null) {
                            loading_tv.setTextColor(ContextCompat.getColor(mActivity, messageColorRes!!))

                        } else {
                            loading_tv.setTextColor(ContextCompat.getColor(mActivity, R.color.title_text_color))
                        }
                    }
                    DeevDialogStyle.MESSAGE -> {

                        if (bgColorRes == null) {
                            message_dialog_layout.setBackgroundColor(Color.BLACK)

                        } else {
                            message_dialog_layout.setBackgroundColor(ContextCompat.getColor(context, bgColorRes!!))

                        }
                        if (titleColorRes == null) {
                            title_tv.setTextColor(Color.WHITE)
                        } else {
                            title_tv.setTextColor(ContextCompat.getColor(mActivity, titleColorRes!!))
                        }

                        if (messageColorRes == null) {
                            text_dialog.setTextColor(ContextCompat.getColor(mActivity, R.color.title_text_color))

                        } else {
                            text_dialog.setTextColor(ContextCompat.getColor(mActivity, messageColorRes!!))
                        }

                        if (mPColor == null) {
                            ok_btn.setTextColor(ContextCompat.getColor(mActivity, R.color.title_text_color))
                        } else {
                            ok_btn.setTextColor(ContextCompat.getColor(mActivity, mPColor!!))
                        }

                        if (mNColor == null) {
                            cancel_action.setTextColor(ContextCompat.getColor(mActivity, R.color.title_text_color))

                        } else {
                            cancel_action.setTextColor(ContextCompat.getColor(mActivity, mNColor!!))
                        }
                    }

                }
            }

            private fun getColorState(): ColorStateList {
                val states = arrayOf(intArrayOf(android.R.attr.state_enabled), // enabled
                        intArrayOf(-android.R.attr.state_enabled), // disabled
                        intArrayOf(-android.R.attr.state_checked), // unchecked
                        intArrayOf(android.R.attr.state_pressed)  // pressed
                )

                val colors = intArrayOf(Color.BLACK, Color.RED, Color.GREEN, Color.BLUE)

                return ColorStateList(states, colors)
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
                    DeevDialogStyle.PROGRESS -> {
                        setLayout(R.layout.progress_dialog_view)
                        setViewForProgress()
                    }
                    DeevDialogStyle.MESSAGE -> {
                        setLayout(R.layout.message_dialog_view)
                        setViewForMessage()
                    }
                    DeevDialogStyle.CUSTOM -> {
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
                if (mDialog != null) {
                    if (customViewRenderring != null) {
                        customViewRenderring?.onBind(mDialog!!)

                    }

                }
            }

            private fun setViewForMessage() {

                if (isDarkTheme()) {
                    getDarkTheme(DeevDialogStyle.MESSAGE)
                } else {
                    checkTheme()
                }

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
                    message_dialog_layout.setBackgroundColor(mActivity.resources.getColor(bgColorRes!!))
                }
                if (titleColorRes != null) {
                    title_tv.setTextColor(mActivity.resources.getColor(titleColorRes!!))
                }
                if (messageColorRes != null) {
                    text_dialog.setTextColor(mActivity.resources.getColor(messageColorRes!!))
                }

                if (mPColor != null) {
                    ok_btn.setTextColor(mActivity.resources.getColor(mPColor!!))
                }
                if (mNColor != null) {
                    cancel_action.setTextColor(mActivity.resources.getColor(mNColor!!))
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
                if (isDarkTheme()) {
                    getDarkTheme(DeevDialogStyle.PROGRESS)
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


            fun setOnPositiveListener(listener: DeevDialogCallback.onPositiveClickListener): DeevDialog {
                mPositiveClick = listener
                return this
            }

            fun setOnNegativeClickListener(listener: DeevDialogCallback.onNegativeClickListener): DeevDialog {
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

            fun setCustomViewCallback(renderingListener: DeevDialogCallback.CustomViewRenderingListener): DeevDialog {
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


            fun make() {
                this.show()
            }

            override fun onDetachedFromWindow() {
                super.onDetachedFromWindow()
                setDefault()
            }


            private fun isDarkTheme(): Boolean {
                if (isDarkTheme != null) {
                    return isDarkTheme!!

                } else {
                    return false
                }
            }


        }

        fun into(activity: Activity, animation: Int?): DeevDialog {
            mActivity = activity
            if (animation != null) {
                return DeevDialog(activity, animation)
            } else {
                return DeevDialog(activity, R.style.fadeDialogAnimation)
            }
        }

        fun release() {
            if (mDialog != null) {
                if (mDialog!!.isShowing) {
                    setDefault()
                    Log.i("deevDialog", "dismiss")
                    mDialog!!.dismiss()
                }
            }

        }

        private fun changeProgressBarLoadingColor(mProgressBar: ProgressBar) {
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
            mPColor = null
            mNColor = null
            messageColorRes = null
            titleColorRes = null
            isDarkTheme = null
            mPositiveClick = null
            mNegativeClick = null
            customViewRenderring = null
        }

        fun isShowing(): Boolean {
            if (mDialog != null) {
                return mDialog!!.isShowing()

            } else {
                return false
            }
        }
    }
}