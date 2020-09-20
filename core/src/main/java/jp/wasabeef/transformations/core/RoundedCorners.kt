package jp.wasabeef.transformations.core

import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Shader
import jp.wasabeef.transformations.types.CornerType

/**
 * Copyright (C) 2020 Wasabeef
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

class RoundedCorners constructor(
  private val radius: Int,
  private var diameter: Int,
  private val margin: Int,
  private val cornerType: CornerType
) : Transformation() {

  override fun transform(
    source: Bitmap,
    destination: Bitmap
  ): Bitmap {

    destination.density = source.density
    destination.setHasAlpha(true)

    val paint = Paint().apply {
      isAntiAlias = true
      isFilterBitmap = true
      shader = BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
    }
    val canvas = Canvas(destination)
    drawRoundRect(canvas, paint, source.width.toFloat(), source.height.toFloat())
    canvas.setBitmap(null)

    return destination
  }

  private fun drawRoundRect(canvas: Canvas, paint: Paint, width: Float, height: Float) {
    val right = width - margin
    val bottom = height - margin
    when (cornerType) {
      CornerType.ALL -> canvas.drawRoundRect(
        RectF(
          margin.toFloat(),
          margin.toFloat(), right, bottom
        ),
        radius.toFloat(), radius.toFloat(), paint
      )
      CornerType.TOP_LEFT -> drawTopLeftRoundRect(canvas, paint, right, bottom)
      CornerType.TOP_RIGHT -> drawTopRightRoundRect(canvas, paint, right, bottom)
      CornerType.BOTTOM_LEFT -> drawBottomLeftRoundRect(canvas, paint, right, bottom)
      CornerType.BOTTOM_RIGHT -> drawBottomRightRoundRect(canvas, paint, right, bottom)
      CornerType.TOP -> drawTopRoundRect(canvas, paint, right, bottom)
      CornerType.BOTTOM -> drawBottomRoundRect(canvas, paint, right, bottom)
      CornerType.LEFT -> drawLeftRoundRect(canvas, paint, right, bottom)
      CornerType.RIGHT -> drawRightRoundRect(canvas, paint, right, bottom)
      CornerType.OTHER_TOP_LEFT -> drawOtherTopLeftRoundRect(canvas, paint, right, bottom)
      CornerType.OTHER_TOP_RIGHT -> drawOtherTopRightRoundRect(canvas, paint, right, bottom)
      CornerType.OTHER_BOTTOM_LEFT -> drawOtherBottomLeftRoundRect(canvas, paint, right, bottom)
      CornerType.OTHER_BOTTOM_RIGHT -> drawOtherBottomRightRoundRect(canvas, paint, right, bottom)
      CornerType.DIAGONAL_FROM_TOP_LEFT -> drawDiagonalFromTopLeftRoundRect(
        canvas,
        paint,
        right,
        bottom
      )
      CornerType.DIAGONAL_FROM_TOP_RIGHT -> drawDiagonalFromTopRightRoundRect(
        canvas,
        paint,
        right,
        bottom
      )
    }
  }

  private fun drawTopLeftRoundRect(canvas: Canvas, paint: Paint, right: Float, bottom: Float) {
    canvas.drawRoundRect(
      RectF(
        margin.toFloat(), margin.toFloat(),
        (margin + diameter).toFloat(), (margin + diameter).toFloat()
      ),
      radius.toFloat(),
      radius.toFloat(), paint
    )
    canvas.drawRect(
      RectF(
        margin.toFloat(), (margin + radius).toFloat(),
        (margin + radius).toFloat(), bottom
      ),
      paint
    )
    canvas.drawRect(RectF((margin + radius).toFloat(), margin.toFloat(), right, bottom), paint)
  }

  private fun drawTopRightRoundRect(canvas: Canvas, paint: Paint, right: Float, bottom: Float) {
    canvas.drawRoundRect(
      RectF(
        right - diameter, margin.toFloat(), right,
        (margin + diameter).toFloat()
      ),
      radius.toFloat(),
      radius.toFloat(), paint
    )
    canvas.drawRect(RectF(margin.toFloat(), margin.toFloat(), right - radius, bottom), paint)
    canvas.drawRect(RectF(right - radius, (margin + radius).toFloat(), right, bottom), paint)
  }

  private fun drawBottomLeftRoundRect(canvas: Canvas, paint: Paint, right: Float, bottom: Float) {
    canvas.drawRoundRect(
      RectF(
        margin.toFloat(), bottom - diameter,
        (margin + diameter).toFloat(), bottom
      ),
      radius.toFloat(),
      radius.toFloat(), paint
    )
    canvas.drawRect(
      RectF(
        margin.toFloat(),
        margin.toFloat(),
        (margin + diameter).toFloat(),
        bottom - radius
      ),
      paint
    )
    canvas.drawRect(RectF((margin + radius).toFloat(), margin.toFloat(), right, bottom), paint)
  }

  private fun drawBottomRightRoundRect(canvas: Canvas, paint: Paint, right: Float, bottom: Float) {
    canvas.drawRoundRect(
      RectF(right - diameter, bottom - diameter, right, bottom), radius.toFloat(),
      radius.toFloat(), paint
    )
    canvas.drawRect(RectF(margin.toFloat(), margin.toFloat(), right - radius, bottom), paint)
    canvas.drawRect(RectF(right - radius, margin.toFloat(), right, bottom - radius), paint)
  }

  private fun drawTopRoundRect(canvas: Canvas, paint: Paint, right: Float, bottom: Float) {
    canvas.drawRoundRect(
      RectF(
        margin.toFloat(), margin.toFloat(), right,
        (margin + diameter).toFloat()
      ),
      radius.toFloat(), radius.toFloat(),
      paint
    )
    canvas.drawRect(RectF(margin.toFloat(), (margin + radius).toFloat(), right, bottom), paint)
  }

  private fun drawBottomRoundRect(canvas: Canvas, paint: Paint, right: Float, bottom: Float) {
    canvas.drawRoundRect(
      RectF(margin.toFloat(), bottom - diameter, right, bottom), radius.toFloat(), radius.toFloat(),
      paint
    )
    canvas.drawRect(RectF(margin.toFloat(), margin.toFloat(), right, bottom - radius), paint)
  }

  private fun drawLeftRoundRect(canvas: Canvas, paint: Paint, right: Float, bottom: Float) {
    canvas.drawRoundRect(
      RectF(
        margin.toFloat(), margin.toFloat(),
        (margin + diameter).toFloat(), bottom
      ),
      radius.toFloat(), radius.toFloat(),
      paint
    )
    canvas.drawRect(RectF((margin + radius).toFloat(), margin.toFloat(), right, bottom), paint)
  }

  private fun drawRightRoundRect(canvas: Canvas, paint: Paint, right: Float, bottom: Float) {
    canvas.drawRoundRect(
      RectF(right - diameter, margin.toFloat(), right, bottom),
      radius.toFloat(),
      radius.toFloat(),
      paint
    )
    canvas.drawRect(RectF(margin.toFloat(), margin.toFloat(), right - radius, bottom), paint)
  }

  private fun drawOtherTopLeftRoundRect(canvas: Canvas, paint: Paint, right: Float, bottom: Float) {
    canvas.drawRoundRect(
      RectF(margin.toFloat(), bottom - diameter, right, bottom), radius.toFloat(), radius.toFloat(),
      paint
    )
    canvas.drawRoundRect(
      RectF(right - diameter, margin.toFloat(), right, bottom),
      radius.toFloat(),
      radius.toFloat(),
      paint
    )
    canvas.drawRect(
      RectF(margin.toFloat(), margin.toFloat(), right - radius, bottom - radius),
      paint
    )
  }

  private fun drawOtherTopRightRoundRect(
    canvas: Canvas,
    paint: Paint,
    right: Float,
    bottom: Float
  ) {
    canvas.drawRoundRect(
      RectF(
        margin.toFloat(), margin.toFloat(),
        (margin + diameter).toFloat(), bottom
      ),
      radius.toFloat(), radius.toFloat(),
      paint
    )
    canvas.drawRoundRect(
      RectF(margin.toFloat(), bottom - diameter, right, bottom), radius.toFloat(), radius.toFloat(),
      paint
    )
    canvas.drawRect(
      RectF((margin + radius).toFloat(), margin.toFloat(), right, bottom - radius),
      paint
    )
  }

  private fun drawOtherBottomLeftRoundRect(
    canvas: Canvas,
    paint: Paint,
    right: Float,
    bottom: Float
  ) {
    canvas.drawRoundRect(
      RectF(
        margin.toFloat(), margin.toFloat(), right,
        (margin + diameter).toFloat()
      ),
      radius.toFloat(), radius.toFloat(),
      paint
    )
    canvas.drawRoundRect(
      RectF(right - diameter, margin.toFloat(), right, bottom),
      radius.toFloat(),
      radius.toFloat(),
      paint
    )
    canvas.drawRect(
      RectF(margin.toFloat(), (margin + radius).toFloat(), right - radius, bottom),
      paint
    )
  }

  private fun drawOtherBottomRightRoundRect(
    canvas: Canvas,
    paint: Paint,
    right: Float,
    bottom: Float
  ) {
    canvas.drawRoundRect(
      RectF(
        margin.toFloat(), margin.toFloat(), right,
        (margin + diameter).toFloat()
      ),
      radius.toFloat(), radius.toFloat(),
      paint
    )
    canvas.drawRoundRect(
      RectF(
        margin.toFloat(), margin.toFloat(),
        (margin + diameter).toFloat(), bottom
      ),
      radius.toFloat(), radius.toFloat(),
      paint
    )
    canvas.drawRect(
      RectF((margin + radius).toFloat(), (margin + radius).toFloat(), right, bottom),
      paint
    )
  }

  private fun drawDiagonalFromTopLeftRoundRect(
    canvas: Canvas,
    paint: Paint,
    right: Float,
    bottom: Float
  ) {
    canvas.drawRoundRect(
      RectF(
        margin.toFloat(), margin.toFloat(),
        (margin + diameter).toFloat(), (margin + diameter).toFloat()
      ),
      radius.toFloat(),
      radius.toFloat(), paint
    )
    canvas.drawRoundRect(
      RectF(right - diameter, bottom - diameter, right, bottom), radius.toFloat(),
      radius.toFloat(), paint
    )
    canvas.drawRect(
      RectF(margin.toFloat(), (margin + radius).toFloat(), right - radius, bottom),
      paint
    )
    canvas.drawRect(
      RectF((margin + radius).toFloat(), margin.toFloat(), right, bottom - radius),
      paint
    )
  }

  private fun drawDiagonalFromTopRightRoundRect(
    canvas: Canvas,
    paint: Paint,
    right: Float,
    bottom: Float
  ) {
    canvas.drawRoundRect(
      RectF(
        right - diameter, margin.toFloat(), right,
        (margin + diameter).toFloat()
      ),
      radius.toFloat(),
      radius.toFloat(), paint
    )
    canvas.drawRoundRect(
      RectF(
        margin.toFloat(), bottom - diameter,
        (margin + diameter).toFloat(), bottom
      ),
      radius.toFloat(),
      radius.toFloat(), paint
    )
    canvas.drawRect(
      RectF(margin.toFloat(), margin.toFloat(), right - radius, bottom - radius),
      paint
    )
    canvas.drawRect(
      RectF((margin + radius).toFloat(), (margin + radius).toFloat(), right, bottom),
      paint
    )
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as RoundedCorners

    if (radius != other.radius) return false
    if (diameter != other.diameter) return false
    if (margin != other.margin) return false
    if (cornerType != other.cornerType) return false

    return true
  }

  override fun hashCode(): Int {
    var result = radius
    result = 31 * result + diameter
    result = 31 * result + margin
    result = 31 * result + cornerType.hashCode()
    return result
  }

  override fun key(): String {
    return "$id(radius=$radius, margin=$margin, diameter=$diameter, cornerType=${cornerType.name})"
  }
}