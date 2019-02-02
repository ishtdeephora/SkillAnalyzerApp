package com.example.android.newtabbedactivity;

/**
 * Created by pcs on 18-Mar-18.
 */

import android.app.Activity;
import android.app.Dialog;
import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Parcelable;
import android.os.ResultReceiver;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.nhaarman.supertooltips.ToolTip;
import com.nhaarman.supertooltips.ToolTipRelativeLayout;
import com.nhaarman.supertooltips.ToolTipView;

import java.sql.RowId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;


public class Tab1Known extends Fragment {
    private EditText et;
    static double increment;
    ArrayList<RowItem> rowItems = new ArrayList<RowItem>();
    static int counter;


//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        View v=getLayoutInflater().inflate(R.layout.tab1known,null);
//
//
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.tab1known, container, false);
        final ListView lstV = (ListView) rootView.findViewById(R.id.checkList);

        if(savedInstanceState==null && counter!=1) {
            counter=1;
            RowItem newE = new RowItem("Working with Android apps", "Beginner");
            RowItem newT = new RowItem("Giving Feedback", "Intermediate");
            rowItems.add(newE);
            rowItems.add(newT);

        }
        if(savedInstanceState!=null){
            rowItems=savedInstanceState.getParcelableArrayList("outList");
        }

        else {
            TextView txt = (TextView) rootView.findViewById(R.id.eval);
            String chVal = Double.toString(increment);
            txt.setText(chVal);
            et = (EditText) rootView.findViewById(R.id.enterSkillA);
//        String value=this.getArguments().getString("parsed_key").toString();
//            et.setText(value);


            final Spinner spin = (Spinner) rootView.findViewById(R.id.spinner);

            final ArrayAdapter<CharSequence> spinAdapter = ArrayAdapter.createFromResource(getContext(), R.array.planets_array, android.R.layout.simple_spinner_item);
            spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


            int pos = spinAdapter.getPosition("Beginner");
            spin.setSelection(pos);
            spin.setAdapter(spinAdapter);
            spinAdapter.notifyDataSetChanged();

            final EditText et = (EditText) rootView.findViewById(R.id.enterSkillA);

            final CustomListAdapter adapter = new CustomListAdapter(getContext(), R.layout.field, rowItems);
            lstV.setAdapter(adapter);
//            ImageButton editContent=(ImageButton)getLayoutInflater().inflate(R.layout.field,null).findViewById(R.id.editContent);
//            editContent.setOnClickListener(new View.OnClickListener() {




                    lstV.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                            System.out.println("Value coming");
                            final Dialog dg=new Dialog(getActivity());
                            System.out.println("Yes baby it is click");
                            dg.setContentView(R.layout.inputbox);


                            final EditText et=(EditText)dg.findViewById(R.id.editText);

                            final RowItem newE=rowItems.get(i);
                            et.setText(newE.getTitle());





                            ImageButton imgB=(ImageButton)dg.findViewById(R.id.button3);
                            ImageButton removeB=(ImageButton)dg.findViewById(R.id.removeBtn);

                            imgB.setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    String n= et.getText().toString();
                                    newE.setTitle(n);
                                    adapter.notifyDataSetChanged();
                                    dg.dismiss();

                                }
                            });

                            removeB.setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    rowItems.remove(i);
                                    increment=increment-3;
                                    TextView txt = (TextView)rootView.findViewById(R.id.eval);
                                    String chVal = Double.toString(increment);
                                    txt.setText(chVal);
                                    adapter.notifyDataSetChanged();
                                    dg.dismiss();
                                }
                            });



        dg.show();
                        }
                    });
//





            Button btn = (Button) rootView.findViewById(R.id.submit);
            ImageButton edt = (ImageButton) rootView.findViewById(R.id.editContent);



            btn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (et.getText().toString().equals("")) {
                        Toast.makeText(getContext(), "Please enter some text", Toast.LENGTH_SHORT).show();
                    } else {
                        String val = et.getText().toString();
                        String dropdownVal = spin.getSelectedItem().toString();
                        RowItem newE = new RowItem(val, dropdownVal);
                        rowItems.add(newE);
//                    Gson gson=new Gson();
//                    String jsonList=gson.toJson(rowItems);
//                    Bundle args=new Bundle();
//                    args.putString("outList",jsonList);


                        Toast.makeText(getContext(), val + " is added", Toast.LENGTH_SHORT).show();
                        adapter.notifyDataSetChanged();
                        et.setText("");
                        spin.setAdapter(spinAdapter);
                        TextView txt = (TextView) rootView.findViewById(R.id.eval);
                        increment = increment + 3;
                        String chVal = Double.toString(increment);
                        txt.setText(chVal);


//               InputMethodManager imm=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
//               imm.hideSoftInputFromInputMethod(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS
                        SharedPreferences.Editor editor = getActivity().getSharedPreferences("MY_PREF_NAME", Context.MODE_PRIVATE).edit();
                        float l = (float) increment / 45;
                        editor.putFloat("name", l);
                        editor.apply();

                    }
                }
            });


//        Intent one=new Intent(getContext(),ResultPieChartActivity.class);
//        one.putExtra("ParsedValue", increment);
//      startActivity(one);
            ImageButton imgB=(ImageButton)rootView.findViewById(R.id.imageButton5);

            imgB.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    promptSpeechInput();
                }
            });



        }
        return rootView;
    }

    public double getScoreKnown(){
        return increment;
    }









//    public void onViewCreated(View view, Bundle savedInstanceState){
//        super.onViewCreated(view,savedInstanceState);
//        Bundle args=this.getArguments();
//       String strText=args.getString("parsed");
//       EditText et=(EditText)view.findViewById(R.id.editKnownSkill);
//       et.setText(strText);
//   }
//



   public void setTextForEdit(EditText et, String abc){

    System.out.println(abc);
      et.setText(abc);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("outList",rowItems);
        outState.putDouble("incrementPassed",increment);
    }

    private final int REQ_CODE_SPEECH_INPUT=100;



    public void promptSpeechInput(){
        View rootView = getLayoutInflater().inflate(R.layout.tab1known, null);
        Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        startActivityForResult(intent,REQ_CODE_SPEECH_INPUT);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){

       switch (requestCode){
            case REQ_CODE_SPEECH_INPUT:{
                if(resultCode==getActivity().RESULT_OK && null !=data){
                    ArrayList<String>result=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    View v=getActivity().getCurrentFocus();
                    EditText et=v.findViewById(R.id.enterSkillA);
                    et.setText(result.get(0));


                }break;
            }
        }
        super.onActivityResult(requestCode,resultCode,data);


    }

//    @Override
//    public void onResume() {
//
//        SharedPreferences editor = this.getActivity().getSharedPreferences("MY_PREF_NAME", Context.MODE_PRIVATE);
//        String str=editor.getString("voiceValue","Default");
//
//        View v=getActivity().getCurrentFocus();
//
//        EditText et=v.findViewById(R.id.enterSkillA);
//        et.setText(str);
//        super.onResume();
//
//    }
}










