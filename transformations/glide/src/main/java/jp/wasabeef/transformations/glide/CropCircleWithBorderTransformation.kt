package jp.wasabeef.transformations.glide

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import androidx.annotation.ColorInt
import com.bumptech.glide.load.Key
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import java.security.MessageDigest
import jp.wasabeef.transformations.core.CropCircleWithBorder
import jp.wasabeef.transformations.core.bitmapConfig
import jp.wasabeef.transformations.core.dp
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

class CropCircleWithBorderTransformation @JvmOverloads constructor(
  private val borderSize: Int = 4.dp,
  @ColorInt private val borderColor: Int = Color.BLACK
) : BitmapTransformation() {

  private val cropCircleWithBorder = CropCircleWithBorder(borderSize, borderColor)

  override fun transform(
    context: Context,
    pool: BitmapPool,
    source: Bitmap,
    outWidth: Int,
    outHeight: Int
  ): Bitmap {
    val size = min(source.width, source.height)
    val output = pool.get(size, size, bitmapConfig(source))
    return cropCircleWithBorder.transform(source, output)
  }

  override fun updateDiskCacheKey(messageDigest: MessageDigest) {
    messageDigest.update(cropCircleWithBorder.key().toByteArray(Key.CHARSET))
  }
}