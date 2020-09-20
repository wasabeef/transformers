package jp.wasabeef.transformations.core

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter

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

class ColorFilter constructor(private val color: Int = 0) : Transformation() {

  override fun transform(
    context: Context,
    source: Bitmap,
    destination: Bitmap
  ): Bitmap {

    destination.density = source.density

    val canvas = Canvas(destination)
    val paint = Paint()
    paint.isAntiAlias = true
    paint.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
    canvas.drawBitmap(source, 0f, 0f, paint)

    return destination
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as ColorFilter

    if (color != other.color) return false

    return true
  }

  override fun hashCode(): Int {
    return color
  }

  override fun key(): String = "$id(color=$color)"
}