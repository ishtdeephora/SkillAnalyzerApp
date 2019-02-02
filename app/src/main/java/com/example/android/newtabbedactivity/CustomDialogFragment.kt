package com.example.android.newtabbedactivity

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment

/**
 * Created by pcs on 29-Jan-19.
 */
class CustomDialogFragment : DialogFragment() {

    companion object {
        fun instance(values:ArrayList<String>){
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
    }
}