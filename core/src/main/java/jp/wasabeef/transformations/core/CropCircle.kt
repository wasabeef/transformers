package jp.wasabeef.transformations.core

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Shader
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

class CropCircle : Transformation() {

  override fun transform(
    source: Bitmap,
    destination: Bitmap
  ): Bitmap {

    destination.density = source.density
    destination.setHasAlpha(true)

    val size = min(destination.width, destination.height)
    val width = (source.width - size) / 2
    val height = (source.height - size) / 2

    val canvas = Canvas(destination)
    val paint = Paint()
    val shader = BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
    if (width != 0 || height != 0) {
      // source isn't square, move viewport to center
      val matrix = Matrix()
      matrix.setTranslate(-width.toFloat(), -height.toFloat())
      shader.setLocalMatrix(matrix)
    }
    paint.shader = shader
    paint.isAntiAlias = true

    val r: Float = size / 2f
    canvas.drawCircle(r, r, r, paint)
    canvas.setBitmap(null)

    return destination
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false
    return true
  }

  override fun hashCode(): Int {
    return javaClass.hashCode()
  }

  override fun key(): String = "$id()"
}