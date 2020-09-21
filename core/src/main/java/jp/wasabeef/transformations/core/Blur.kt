package jp.wasabeef.transformations.core

import android.content.Context
import android.graphics.Bitmap
import android.renderscript.RSRuntimeException

class Blur constructor(
  context: Context,
  private val radius: Int,
  private val sampling: Int,
  private val rs: Boolean = true
) : Transformation() {

  private val stackBlur = StackBlur(radius, sampling)
  private val rsGaussianBlur = RSGaussianBlur(context, radius, sampling)

  override fun key(): String = "$id(radius=$radius, sampling=$sampling, rs=$rs)"

  override fun transform(source: Bitmap, destination: Bitmap): Bitmap {
    return if (rs) {
      try {
        rsGaussianBlur.transform(source, destination)
      } catch (e: RSRuntimeException) {
        stackBlur.transform(source, destination)
      }
    } else {
      stackBlur.transform(source, destination)
    }
  }
}