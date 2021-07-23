package ru.nobird.android.myapplication.fragment.arguments

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Person(
    val firstName: String,
    val lastName: String,
    val age: Int
) : Parcelable

//data class Person(
//    val firstName: String,
//    val lastName: String,
//    val age: Int
//) : Parcelable {
//    override fun describeContents(): Int = 0
//
//    override fun writeToParcel(dest: Parcel, flags: Int) {
//        dest.writeString(firstName)
//        dest.writeString(lastName)
//        dest.writeInt(age)
//    }
//
//    companion object CREATOR : Parcelable.Creator<Person> {
//        override fun createFromParcel(parcel: Parcel): Person =
//            Person(
//                parcel.readString() ?: "",
//                parcel.readString() ?: "",
//                parcel.readInt()
//            )
//
//        override fun newArray(size: Int): Array<Person?> =
//            arrayOfNulls(size)
//    }
//}