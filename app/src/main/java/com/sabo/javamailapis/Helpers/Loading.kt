package com.sabo.javamailapis.Helpers

import android.content.Context
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog
import com.sabo.javamailapis.R

class Loading {
    companion object {
        private var loading: SweetAlertDialog? = null

        fun onStart(context: Context) {
            loading = SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE)
            loading!!.setCancelable(false)
            loading!!.progressHelper.barColor =
                context.resources.getColor(R.color.blue_btn_bg_color, context.theme)
            loading!!.titleText = "Please wait..."
            loading!!.contentText = "Send Email."
            loading!!.show()
        }

        fun onStop() {
            loading!!.dismissWithAnimation()
        }
    }
}