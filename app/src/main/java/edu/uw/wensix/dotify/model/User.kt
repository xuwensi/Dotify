package edu.uw.wensix.dotify.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
   val username: String,
   val firstName: String,
   val lastName: String,
   val hasNose: String,
   val platform: String,
   val profilePicURL: String
): Parcelable