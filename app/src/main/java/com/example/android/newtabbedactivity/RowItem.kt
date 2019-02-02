package com.example.android.newtabbedactivity

import android.os.Parcel
import android.os.Parcelable

class RowItem : Parcelable {
    var title: String? = null
    var subTitle: String? = null

    constructor(title: String, subTitle: String) {
        this.title = title
        this.subTitle = subTitle
    }

    private constructor(`in`: Parcel) {
        title = `in`.readString()
        subTitle = `in`.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return title + "\n" + subTitle
    }

    override fun writeToParcel(out: Parcel, flags: Int) {
        out.writeString(title)
        out.writeString(subTitle)
    }

    companion object {
        val CREATOR: Parcelable.Creator<RowItem> = object : Parcelable.Creator<RowItem> {
            override fun createFromParcel(`in`: Parcel): RowItem {
                return RowItem(`in`)
            }

            override fun newArray(i: Int): Array<RowItem?> {
                return arrayOfNulls(i)
            }
        }
    }

}
