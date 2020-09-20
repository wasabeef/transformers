package jp.wasabeef.transformations.picasso

import android.graphics.Bitmap
import androidx.annotation.ColorInt
import com.squareup.picasso.Transformation
import jp.wasabeef.transformations.core.ColorFilter
import jp.wasabeef.transformations.core.bitmapConfig

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

class ColorFilterTransformation constructor(
  @ColorInt private val color: Int
) : Transformation {

  private val colorFilter = ColorFilter(color)

  override fun transform(
    source: Bitmap
  ): Bitmap {
    val output = Bitmap.createBitmap(source.width, source.height, bitmapConfig(source))
    colorFilter.transform(source, output)
    source.recycle()
    return output
  }

  override fun key() = colorFilter.key()

  override fun equals(o: Any?): Boolean {
    if (this === o) return true
    if (javaClass != o?.javaClass) return false

    o as ColorFilterTransformation

    if (color != o.color) return false
    if (colorFilter != o.colorFilter) return false

    return true
  }

  override fun hashCode(): Int = colorFilter.key().hashCode()
}