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
): Parcelable {
   override fun writeToParcel(dest: Parcel?, flags: Int) {
      TODO("Not yet implemented")
   }

   override fun describeContents(): Int {
      TODO("Not yet implemented")
   }
}