package com.example.android.newtabbedactivity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import kotlinx.android.synthetic.main.activity_result_pie_chart.*
import java.util.*


class ResultpdActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_pie_chart)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val prefs = getSharedPreferences("MY_PREF_NAME", 0)
        val `val` = prefs.getFloat("name", 33.3f)
        val val2 = prefs.getFloat("shouldVals", 33.3f)
        val val3 = prefs.getFloat("mayVals", 33.3f)

        pd.isRotationEnabled = true
        pd.setUsePercentValues(true)
        pd.holeRadius = 25f
        pd.centerText = "Skill Analyzer"

        addDataSet(`val`, val2, val3)

/*        recyclerViewLearn.adapter=RecyclerViewAdapter(arrayListOf("one","two","three"))
        recyclerViewLearn.layoutManager= LinearLayoutManager(this)*/

        pd.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
            override fun onValueSelected(e: Entry, dataSetIndex: Int, h: Highlight) {
                Log.d("TAG", "onValueSelected: On Click event")
            }

            override fun onNothingSelected() {
            }
        })

        Toast.makeText(applicationContext, "You can take a screenshot for future reference", Toast.LENGTH_LONG).show()

        add_button.setOnClickListener {
            startActivity(Intent(this@ResultpdActivity, MainActivity::class.java))
        }
    }

    private fun addDataSet(val1: Float, val2: Float, val3: Float) {
        val yData = floatArrayOf(val1, val2, val3)
        val xData = arrayOf("Ishtdeep", "Harsh", "Arshi")

        val yEntrys = yData.indices.map { Entry(yData[it], it) }

        val xEntry = xData.indices.map { xData[it] }

        val pieDataSet = PieDataSet(yEntrys, "Skills percentage")
        pieDataSet.sliceSpace = 2f
        pieDataSet.valueTextSize = 14f

        pd.setDescription("Your entered skill analysis")
        pd.setDescriptionTextSize(18f)

        pd.setDescriptionPosition(545f, 175f)
        pd.setDescriptionColor(Color.RED)

        val colors = ArrayList<Int>()

        colors.add(Color.CYAN)
        colors.add(Color.BLUE)
        colors.add(Color.MAGENTA)

        pieDataSet.colors = colors

        //Add legend

        val legend = pd.legend
        legend.isEnabled = true
        legend.form = Legend.LegendForm.CIRCLE
        legend.position = Legend.LegendPosition.LEFT_OF_CHART
        val colorsA = intArrayOf(Color.CYAN, Color.BLUE, Color.MAGENTA)
        val xLabels = arrayOf("Skills you know", "Skills you think you should know", "Skills you think you will know")
        legend.setCustom(colorsA, xLabels)

        val xVals = ArrayList<String>()
        xVals.add("Known")
        xVals.add("Should")
        xVals.add("may")

        val pieData = PieData(xVals, pieDataSet)

        pd.data = pieData
        pd.invalidate()

        pieData.setValueFormatter(PercentFormatter())
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }


}




