package com.example.android.newtabbedactivity

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class CustomListAdapter(internal var context: Context, resourceId: Int,
                        items: ArrayList<RowItem>?) : ArrayAdapter<RowItem>(context, resourceId, items) {

    private inner class ViewHolder {
        internal var txtTitle: TextView? = null
        internal var txtDesc: TextView? = null
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        var holder: ViewHolder? = null
        val rowItem = getItem(position)

        val mInflater = context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.field, null)
            holder = ViewHolder()
            holder.txtDesc = convertView!!.findViewById<View>(R.id.subHeading) as TextView
            holder.txtTitle = convertView.findViewById<View>(R.id.title) as TextView
            convertView.tag = holder
        } else
            holder = convertView.tag as ViewHolder

        holder.txtDesc!!.text = rowItem!!.subTitle
        holder.txtTitle!!.text = rowItem.title!! + "\n"

        return convertView
    }
}
