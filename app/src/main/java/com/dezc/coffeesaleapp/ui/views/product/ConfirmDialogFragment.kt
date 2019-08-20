package com.dezc.coffeesaleapp.ui.views.product

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.dezc.coffeesaleapp.R
import kotlinx.android.synthetic.main.dialog_confirm.view.*
import kotlinx.android.synthetic.main.fragment_home.*

class ConfirmDialogFragment(): DialogFragment(){

    companion object {
        fun newInstance(title: String): ConfirmDialogFragment {
            val args = Bundle()
            args.putString("title", title)
            val fragment = ConfirmDialogFragment()
            fragment.arguments = args
            return fragment
        }
    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return  inflater.inflate(R.layout.dialog_confirm, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.dialog_confirm, container, false)
        val builder = AlertDialog.Builder(context)
        builder.setView(dialogLayout)
        builder.setTitle("Confirmación")
        builder.setCancelable(false)
        builder.setPositiveButton("OK"){_,_ ->
            Log.i("ConfirmaDialogFragment","Volviendo a la pantalla inicial")
        }.setNegativeButton("Cancel"){_,_ ->

        }

        return builder.create()

    }

}