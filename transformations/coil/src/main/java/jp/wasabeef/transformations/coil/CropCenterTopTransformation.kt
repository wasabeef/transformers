package jp.wasabeef.transformations.coil

import android.graphics.Bitmap
import coil.bitmap.BitmapPool
import coil.size.Size
import coil.transform.Transformation
import jp.wasabeef.transformations.core.Crop
import jp.wasabeef.transformations.core.bitmapConfig
import jp.wasabeef.transformations.types.GravityHorizontal
import jp.wasabeef.transformations.types.GravityVertical

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

typealias CenterTopCropTransformation = CropCenterTopTransformation

class CropCenterTopTransformation : Transformation {

  private val crop = Crop(
    aspectRatio = 1f,
    gravityHorizontal = GravityHorizontal.CENTER,
    gravityVertical = GravityVertical.TOP
  )

  override suspend fun transform(pool: BitmapPool, input: Bitmap, size: Size): Bitmap {
    val calcSize = crop.calculateSize(input)
    val output = pool.get(calcSize.width, calcSize.height, bitmapConfig(input))
    return crop.transform(input, output)
  }

  override fun key() = crop.key()

  override fun equals(o: Any?): Boolean {
    if (this === o) return true
    if (javaClass != o?.javaClass) return false

    o as CropCenterTopTransformation

    if (crop != o.crop) return false

    return true
  }

  override fun hashCode(): Int = crop.key().hashCode()
}