package jp.wasabeef.transformations.glide

import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.load.Key
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import java.security.MessageDigest
import jp.wasabeef.transformations.core.bitmapConfig
import jp.wasabeef.transformations.types.CornerType
import jp.wasabeef.transformations.types.RoundedCorners

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
  private val radius: Int,
  private var diameter: Int = radius * 2,
  private val margin: Int = 0,
  private val cornerType: CornerType = CornerType.ALL
) : BitmapTransformation() {

  private val roundedCorners = RoundedCorners(radius, diameter, margin, cornerType)

  override fun transform(
    context: Context,
    pool: BitmapPool,
    source: Bitmap,
    outWidth: Int,
    outHeight: Int
  ): Bitmap {
    val output = pool.get(source.width, source.height, bitmapConfig(source))
    return roundedCorners.transform(source, output)
  }

  override fun updateDiskCacheKey(messageDigest: MessageDigest) {
    messageDigest.update(roundedCorners.key().toByteArray(Key.CHARSET))
  }

  override fun equals(o: Any?): Boolean {
    if (this === o) return true
    if (javaClass != o?.javaClass) return false

    o as RoundedCornersTransformation

    if (radius != o.radius) return false
    if (diameter != o.diameter) return false
    if (margin != o.margin) return false
    if (cornerType != o.cornerType) return false
    if (roundedCorners != o.roundedCorners) return false

    return true
  }

  override fun hashCode(): Int = roundedCorners.key().hashCode()
}