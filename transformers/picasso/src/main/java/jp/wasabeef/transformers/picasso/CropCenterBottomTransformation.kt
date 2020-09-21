package jp.wasabeef.transformers.picasso

import android.graphics.Bitmap
import com.squareup.picasso.Transformation
import jp.wasabeef.transformers.core.Crop
import jp.wasabeef.transformers.core.bitmapConfig
import jp.wasabeef.transformers.types.GravityHorizontal
import jp.wasabeef.transformers.types.GravityVertical

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

typealias CenterBottomCropTransformation = CropCenterTopTransformation

class CropCenterBottomTransformation : Transformation {

  private val crop = Crop(
    aspectRatio = 1f,
    gravityHorizontal = GravityHorizontal.CENTER,
    gravityVertical = GravityVertical.BOTTOM
  )

  override fun transform(
    source: Bitmap
  ): Bitmap {
    val size = crop.calculateSize(source)
    val output = Bitmap.createBitmap(size.width, size.height, bitmapConfig(source))
    crop.transform(source, output)
    source.recycle()
    return output
  }

  override fun key() = crop.key()
}