package jp.wasabeef.transformations.core

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import kotlin.math.max
import kotlin.math.min

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

class CropCircleWithBorder constructor(
  private val borderSize: Int,
  private val borderColor: Int
) : Transformation() {

  override fun transform(
    source: Bitmap,
    destination: Bitmap
  ): Bitmap {

    CropCircle().transform(source, destination)

    val size = min(destination.width, destination.height)
    destination.density = source.density
    destination.setHasAlpha(true)

    val paint = Paint()
    paint.color = borderColor
    paint.style = Paint.Style.STROKE
    paint.strokeWidth = borderSize.toFloat()
    paint.isAntiAlias = true

    val canvas = Canvas(destination)
    canvas.drawCircle(
      size / 2f,
      size / 2f,
      max(size, size) / 2f - borderSize / 2f,
      paint
    )
    canvas.setBitmap(null)

    return destination
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as CropCircleWithBorder

    if (borderSize != other.borderSize) return false
    if (borderColor != other.borderColor) return false

    return true
  }

  override fun hashCode(): Int {
    var result = borderSize
    result = 31 * result + borderColor
    return result
  }

  override fun key(): String = "$id(borderSize=$borderSize, borderColor=$borderColor)"
}