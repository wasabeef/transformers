package jp.wasabeef.transformations.core

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter

class ColorFilter constructor(private val color: Int = 0) : Transformation() {

  override val version: Int
    get() = 1

  override fun transform(
    context: Context,
    source: Bitmap,
    destination: Bitmap,
    outWidth: Int,
    outHeight: Int
  ): Bitmap {

    destination.density = source.density

    val canvas = Canvas(destination)
    val paint = Paint()
    paint.isAntiAlias = true
    paint.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
    canvas.drawBitmap(source, 0f, 0f, paint)

    return destination
  }

  override fun key(): String = "$id(color=$color)"
}