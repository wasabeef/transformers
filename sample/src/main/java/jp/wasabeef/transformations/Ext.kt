package jp.wasabeef.transformations

import android.content.res.Resources

val Int.dp: Int
  get() = (this * Resources.getSystem().displayMetrics.density).toInt()