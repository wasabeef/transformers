package jp.wasabeef.transformers.glide

import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import jp.wasabeef.transformers.core.Crop
import jp.wasabeef.transformers.core.bitmapConfig
import jp.wasabeef.transformers.core.types.GravityHorizontal
import jp.wasabeef.transformers.core.types.GravityVertical

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

class CropCenterBottomTransformation : BaseTransformation(
  Crop(
    aspectRatio = 1f,
    gravityHorizontal = GravityHorizontal.CENTER,
    gravityVertical = GravityVertical.BOTTOM
  )
) {

  override fun transform(
    context: Context,
    pool: BitmapPool,
    source: Bitmap,
    outWidth: Int,
    outHeight: Int
  ): Bitmap {
    val size = (transformer as Crop).calculateSize(source)
    val output = pool.get(size.width, size.height, bitmapConfig(source))
    return transformer.transform(source, output)
  }
}