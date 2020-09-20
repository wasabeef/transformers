package jp.wasabeef.transformations.core

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes

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

class Mask constructor(
  private val context: Context,
  @DrawableRes private val maskId: Int
) : Transformation() {

  private val paint = Paint().apply {
    xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
  }

  override fun transform(
    source: Bitmap,
    destination: Bitmap
  ): Bitmap {

    destination.density = source.density
    destination.setHasAlpha(true)

    val mask: Drawable = getMaskDrawable(context.applicationContext, maskId)!!

    val canvas = Canvas(destination)
    mask.setBounds(0, 0, source.width, source.height)
    mask.draw(canvas)
    canvas.drawBitmap(
      source,
      0f,
      0f,
      paint
    )
    canvas.setBitmap(null)

    return destination
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as Mask

    if (maskId != other.maskId) return false

    return true
  }

  override fun hashCode(): Int {
    var result = maskId
    result = 31 * result + paint.hashCode()
    return result
  }

  override fun key(): String = "$id(maskId=$maskId)"
}