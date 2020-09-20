package jp.wasabeef.transformations.core

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import kotlin.math.max

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

class CropSquare : Transformation() {

  private var size = 0

  override fun transform(
    context: Context,
    source: Bitmap,
    destination: Bitmap
  ): Bitmap {

    destination.density = source.density
    destination.setHasAlpha(source.hasAlpha())

    size = max(destination.width, destination.height)

    if (source.width == size && source.height == size) {
      return source
    }

    val scale: Float
    val dx: Float
    val dy: Float
    val matrix = Matrix()
    if (source.width * size > size * source.height) {
      scale = size.toFloat() / source.height.toFloat()
      dx = (size - source.width * scale) * 0.5f
      dy = 0f
    } else {
      scale = size.toFloat() / source.width.toFloat()
      dx = 0f
      dy = (size - source.height * scale) * 0.5f
    }

    matrix.setScale(scale, scale)
    matrix.postTranslate(dx + 0.5f, dy + 0.5f)

    val canvas = Canvas(destination)
    canvas.drawBitmap(source, matrix, Paint(Paint.DITHER_FLAG or Paint.FILTER_BITMAP_FLAG))
    canvas.setBitmap(null)

    return destination
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as CropSquare

    if (size != other.size) return false

    return true
  }

  override fun hashCode(): Int {
    return size
  }

  override fun key(): String = "$id(size=$size)"
}