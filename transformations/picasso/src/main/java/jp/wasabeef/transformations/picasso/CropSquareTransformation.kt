package jp.wasabeef.transformations.picasso

import android.graphics.Bitmap
import com.squareup.picasso.Transformation
import jp.wasabeef.transformations.core.CropSquare
import jp.wasabeef.transformations.core.bitmapConfig
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

class CropSquareTransformation : Transformation {

  private val cropSquare = CropSquare()

  override fun transform(
    source: Bitmap
  ): Bitmap {
    val size = min(source.width, source.height)
    val output = Bitmap.createBitmap(size, size, bitmapConfig(source))
    cropSquare.transform(source, output)
    source.recycle()
    return output
  }

  override fun key() = cropSquare.key()
}