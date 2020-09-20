package jp.wasabeef.transformations.coil

import android.content.Context
import android.graphics.Bitmap
import android.renderscript.RSRuntimeException
import coil.bitmap.BitmapPool
import coil.size.Size
import coil.transform.Transformation
import jp.wasabeef.transformations.core.RSGaussianBlur
import jp.wasabeef.transformations.core.StackBlur
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

class BlurTransformation @JvmOverloads constructor(
  context: Context,
  radius: Int = 25,
  private val sampling: Int = 1,
  private val rs: Boolean = true
) : Transformation {

  private val stackBlur = StackBlur(radius, sampling)
  private val rsGaussianBlur = RSGaussianBlur(context, radius, sampling)

  override suspend fun transform(pool: BitmapPool, input: Bitmap, size: Size): Bitmap {
    val scaledWidth: Int = input.width / sampling
    val scaledHeight: Int = input.height / sampling
    val output = pool.get(scaledWidth, scaledHeight, bitmapConfig(input))
    return if (rs) {
      return try {
        rsGaussianBlur.transform(input, output)
      } catch (e: RSRuntimeException) {
        stackBlur.transform(input, output)
      }
    } else {
      stackBlur.transform(input, output)
    }
  }

  override fun key(): String = stackBlur.key() + rsGaussianBlur.key()

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