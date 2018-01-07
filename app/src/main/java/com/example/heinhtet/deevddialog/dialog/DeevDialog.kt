package com.example.heinhtet.deevddialog.dialog

import android.view.Window.FEATURE_NO_TITLE
import android.os.Bundle
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Message
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.heinhtet.deevddialog.MainActivity
import com.example.heinhtet.deevddialog.R
import kotlinx.android.synthetic.main.a.*
import org.w3c.dom.Text


/**
 * Created by heinhtet on 1/6/2018.
 */
class DeevDialog
constructor(var c: Activity, var type: Int?, var message: String?, var positiveText: String?,
            var negativeText: String?,
            click: positiveListener)
    : Dialog(c, R.style.PauseDialog), android.view.View.OnClickListener {
    var d: Dialog? = null
    lateinit var yes: Button
    lateinit var cancel: Button
    lateinit var messageTv: TextView
    var mType = type
    var mClick = click


    companion object {
        val PROGRESS: Int = 232
        val Message: Int = 343

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        when (type) {
            PROGRESS -> {
                setContentView(R.layout.progress_dialog_view)
                setViewForLoading()
            }
            else -> {
                setContentView(R.layout.a)
                setViewForSimple()
            }
        }
    }

    private fun setViewForLoading() {

    }

    private fun setViewForSimple() {
        cancel = findViewById<Button>(R.id.cancel_action)
        yes = findViewById(R.id.ok_btn) as Button
        messageTv = findViewById(R.id.text_dialog)
        checkButton()
        bindData()
        yes.setOnClickListener(this)
        cancel.setOnClickListener(this)
    }

    private fun bindData() {
        messageTv.text = message
    }

    private fun checkButton() {
        if (negativeText == null) {
            cancel.text = negativeText
            cancel.visibility = View.GONE

        } else if (positiveText == null) {
            ok_btn.text = positiveText
            yes.visibility = View.GONE
        }
    }

    override fun onClick(v: View) {
        when (v.getId()) {
            R.id.btn_yes -> {
                mClick.onClick()
            }
            R.id.btn_no -> {
            }
            else -> {

            }
        }
     }

    interface positiveListener{
       fun onClick()
    }


    interface DeevdClickListener {
        fun positiveActionListener()
        fun negativeActionListener()
    }
}