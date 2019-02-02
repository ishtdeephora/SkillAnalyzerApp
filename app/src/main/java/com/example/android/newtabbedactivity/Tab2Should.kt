package com.example.android.newtabbedactivity

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import java.util.*


class Tab2Should : Fragment() {
    private var et: EditText? = null

    private val REQ_CODE_SPEECH_INPUT = 100

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.tab2should, container, false)

        et = rootView.findViewById<View>(R.id.enterSkillB) as EditText
        //        String value=this.getArguments().getString("parsed_key").toString();
        //            et.setText(value);

        //        Spinner spin=(Spinner)rootView.findViewById(R.id.spinner);

        //        ArrayAdapter<CharSequence> spinAdapter=ArrayAdapter.createFromResource(getContext(),R.array.planets_array, android.R.layout.simple_spinner_item);
        //
        //        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //
        //        spin.setAdapter(spinAdapter);

        val places = arrayOf("React Native", "Node JS", "Material design", "iOS app building")
        val listElementArrayList = ArrayList(Arrays.asList(*places))
        val adapter = ArrayAdapter(context, android.R.layout.simple_list_item_activated_1, listElementArrayList)
        val et = rootView.findViewById<View>(R.id.enterSkillB) as EditText
        val lstV = rootView.findViewById<View>(R.id.checkList) as ListView
        lstV.adapter = adapter

        lstV.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->
            val dg = Dialog(activity)
            dg.setContentView(R.layout.inputbox)

            val et = dg.findViewById<View>(R.id.editText) as EditText
            et.setText(listElementArrayList[i])
            val btn = dg.findViewById<View>(R.id.button3) as ImageButton
            val removeButton = dg.findViewById<View>(R.id.removeBtn) as ImageButton

            removeButton.setOnClickListener {
                listElementArrayList.removeAt(i)
                increment -= 2
                val txt = rootView.findViewById<View>(R.id.eval) as TextView
                val chVal = java.lang.Double.toString(increment)
                txt.text = chVal
                adapter.notifyDataSetChanged()
                dg.dismiss()
            }

            btn.setOnClickListener {
                listElementArrayList[i] = et.text.toString()
                adapter.notifyDataSetChanged()
                dg.dismiss()
            }
            dg.show()
        }

        val btn = rootView.findViewById<View>(R.id.submit) as Button


        btn.setOnClickListener {
            if (et.text.toString() == "") {
                Toast.makeText(context, "Please enter some text", Toast.LENGTH_SHORT).show()
            } else {
                val `val` = et.text.toString()
                listElementArrayList.add(`val`)
                Toast.makeText(context, `val` + " is added", Toast.LENGTH_SHORT).show()
                adapter.notifyDataSetChanged()
                et.setText("")

                val txt = rootView.findViewById<View>(R.id.eval) as TextView
                increment += 2
                val chVal = java.lang.Double.toString(increment)
                txt.text = chVal
                //               InputMethodManager imm=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                //               imm.hideSoftInputFromInputMethod(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS

                val editor = activity.getSharedPreferences("MY_PREF_NAME", Context.MODE_PRIVATE).edit()
                val l = increment.toFloat() / 20
                editor.putFloat("shouldVals", l)
                editor.apply()
            }
        }
        val imgB = rootView.findViewById<View>(R.id.imageButton5) as ImageButton

        imgB.setOnClickListener { promptSpeechInput() }
        return rootView
    }

    private fun promptSpeechInput() {
        val rootView = layoutInflater.inflate(R.layout.tab2should, null)
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        startActivityForResult(intent, REQ_CODE_SPEECH_INPUT)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        when (requestCode) {
            REQ_CODE_SPEECH_INPUT -> {
                if (resultCode == Activity.RESULT_OK && null != data) {
                    val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)

                    val v = activity.currentFocus
                    val et = v!!.findViewById<EditText>(R.id.enterSkillB)
                    et.setText(result[0])

                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)

    }

    companion object {
        internal var increment = 0.0
    }


}
