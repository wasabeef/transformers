package jp.wasabeef.transformations.picasso.gpu

import android.content.Context
import android.graphics.PointF
import jp.co.cyberagent.android.gpuimage.filter.GPUImageZoomBlurFilter

/**
 * Copyright (C) 2020 Wasabeef
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @param blurCenter A multiplier for the blur size, ranging from 0.0 on up, with a default of 1.0
 * @param blurSize  The normalized center of the blur. (0.5, 0.5) by default
 */
class ZoomBlurFilterTransformation @JvmOverloads constructor(
  context: Context,
  private val blurCenter: PointF = PointF(0.5f, 0.5f),
  private val blurSize: Float = 1.0f
) : GPUFilterTransformation(context, GPUImageZoomBlurFilter()) {

  init {
    val filter: GPUImageZoomBlurFilter = filter()
    filter.setBlurCenter(blurCenter)
    filter.setBlurSize(blurSize)
  }

  override fun key(): String = "$id(blurCenter=$blurCenter, blurSize=$blurSize)"

  override fun equals(o: Any?): Boolean {
    if (this === o) return true
    if (javaClass != o?.javaClass) return false
    if (!super.equals(o)) return false

    o as ZoomBlurFilterTransformation

    if (blurCenter != o.blurCenter) return false
    if (blurSize != o.blurSize) return false

    return true
  }

  override fun hashCode(): Int = key().hashCode()
}