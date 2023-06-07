package com.syc.sycmodule

import android.util.Log

class SycUtil {

    companion object {
        fun log(log: String) {
            Log.d("sycUtil","##################START#################")
            Log.d("sycUtil",log)
            Log.d("sycUtil","###################END##################")
        }
    }

}