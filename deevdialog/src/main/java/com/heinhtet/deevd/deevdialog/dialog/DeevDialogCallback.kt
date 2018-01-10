package com.heinhtet.deevd.deevdialog.dialog

/**
 * Created by heinhtet on 1/10/18.
 */


class DeevDialogCallback {

    companion object Callback {

    }

    interface onPositiveClickListener {
        fun onClick(deevDialog: DeevDialog.Instance.DeevDialog)
    }

    interface onNegativeClickListener {
        fun onClick(deevDialog: DeevDialog.Instance.DeevDialog)
    }

    interface CustomViewRenderingListener {
        fun onBind(deevDialog: DeevDialog.Instance.DeevDialog)
    }


}