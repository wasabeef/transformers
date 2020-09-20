package jp.wasabeef.transformations.coil

import android.content.Context
import android.graphics.Bitmap
import androidx.annotation.DrawableRes
import coil.bitmap.BitmapPool
import coil.size.Size
import coil.transform.Transformation
import jp.wasabeef.transformations.core.Mask
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

class MaskTransformation constructor(
  context: Context,
  @DrawableRes private val maskId: Int
) : Transformation {

  private val mask = Mask(context, maskId)

  override suspend fun transform(pool: BitmapPool, input: Bitmap, size: Size): Bitmap {
    val output = pool.get(input.width, input.height, bitmapConfig(input))
    return mask.transform(input, output)
  }

  override fun key() = mask.key()

  override fun equals(o: Any?): Boolean {
    if (this === o) return true
    if (javaClass != o?.javaClass) return false

    o as MaskTransformation

    if (maskId != o.maskId) return false
    if (mask != o.mask) return false

    return true
  }

  override fun hashCode(): Int = mask.key().hashCode()
}