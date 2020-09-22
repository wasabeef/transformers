package jp.wasabeef.transformers.coil

import android.graphics.Bitmap
import coil.bitmap.BitmapPool
import coil.size.Size
import jp.wasabeef.transformers.core.RoundedCorners
import jp.wasabeef.transformers.core.bitmapConfig
import jp.wasabeef.transformers.core.types.CornerType

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

class RoundedCornersTransformation @JvmOverloads constructor(
  radius: Int,
  diameter: Int = radius * 2,
  margin: Int = 0,
  cornerType: CornerType = CornerType.ALL
) : BaseTransformation(RoundedCorners(radius, diameter, margin, cornerType)) {

  override suspend fun transform(pool: BitmapPool, input: Bitmap, size: Size): Bitmap {
    val output = pool.get(input.width, input.height, bitmapConfig(input))
    return transformer.transform(input, output)
  }
}