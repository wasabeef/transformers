package jp.wasabeef.transformations.glide

import android.content.Context
import android.graphics.Bitmap
import android.renderscript.RSRuntimeException
import com.bumptech.glide.load.Key
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import jp.wasabeef.transformations.core.RSGaussianBlur
import jp.wasabeef.transformations.core.StackBlur
import jp.wasabeef.transformations.core.bitmapConfig
import java.security.MessageDigest

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

class BlurTransformation @JvmOverloads constructor(
  context: Context,
  radius: Int = 25,
  private val sampling: Int = 1,
  private val rs: Boolean = true
) : BitmapTransformation() {

  private val stackBlur = StackBlur(radius, sampling)
  private val rsGaussianBlur = RSGaussianBlur(context, radius, sampling)

  override fun transform(
    context: Context,
    pool: BitmapPool,
    source: Bitmap,
    outWidth: Int,
    outHeight: Int
  ): Bitmap {
    val scaledWidth: Int = source.width / sampling
    val scaledHeight: Int = source.height / sampling
    val output = pool.get(scaledWidth, scaledHeight, bitmapConfig(source))
    return if (rs) {
      return try {
        rsGaussianBlur.transform(source, output)
      } catch (e: RSRuntimeException) {
        stackBlur.transform(source, output)
      }
    } else {
      stackBlur.transform(source, output)
    }
  }

  override fun updateDiskCacheKey(messageDigest: MessageDigest) {
    messageDigest.update((stackBlur.key() + rsGaussianBlur.key()).toByteArray(Key.CHARSET))
  }

  override fun equals(o: Any?): Boolean {
    if (this === o) return true
    if (javaClass != o?.javaClass) return false

    o as BlurTransformation

    if (stackBlur != o.stackBlur) return false
    if (rsGaussianBlur != o.rsGaussianBlur) return false

    return true
  }

  override fun hashCode(): Int = stackBlur.key().hashCode() + rsGaussianBlur.key().hashCode()
}