package jp.wasabeef.transformations.picasso

import android.content.Context
import android.graphics.Bitmap
import android.renderscript.RSRuntimeException
import com.squareup.picasso.Transformation
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
  private val context: Context,
  radius: Int = 25,
  private val sampling: Int = 1,
  private val rs: Boolean = true
) : Transformation {


  private val stackBlur = StackBlur(radius, sampling)
  private val rsGaussianBlur = RSGaussianBlur(context, radius, sampling)

  override fun transform(
    source: Bitmap
  ): Bitmap {
    val scaledWidth: Int = source.width / sampling
    val scaledHeight: Int = source.height / sampling
    val output = Bitmap.createBitmap(scaledWidth, scaledHeight, bitmapConfig(source))
    if (rs) {
      try {
        rsGaussianBlur.transform(source, output)
      } catch (e: RSRuntimeException) {
        stackBlur.transform(source, output)
      }
    } else {
      stackBlur.transform(source, output)
    }
    source.recycle()
    return output
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