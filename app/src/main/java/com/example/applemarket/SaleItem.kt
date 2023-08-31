package com.example.applemarket

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SaleItem(
    val Image: Int,
    val ItemTitle: String,
    val ItemDetail: String,
    val SellerName: String,
    val Price: Int,
    val Address: String,
    var InterestCnt: Int,
    val ChatCnt: Int,
    var isLike: Boolean
) : Parcelable


