package jp.wasabeef.transformations.fresco.gpu

import android.content.Context
import jp.co.cyberagent.android.gpuimage.filter.GPUImageToonFilter

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
 * The threshold at which to apply the edges, default of 0.2.
 * The levels of quantization for the posterization of colors within the scene,
 * with a default of 10.0.
 */
class ToonFilterPostprocessor @JvmOverloads constructor(
  context: Context,
  private val threshold: Float = 0.2f,
  private val quantizationLevels: Float = 10.0f
) : GPUFilterPostprocessor(context, GPUImageToonFilter()) {

  init {
    val filter: GPUImageToonFilter = filter()
    filter.setThreshold(threshold)
    filter.setQuantizationLevels(quantizationLevels)
  }

  override fun key(): String = "$id(threshold=$threshold, quantizationLevels=$quantizationLevels)"

  override fun equals(o: Any?): Boolean {
    if (this === o) return true
    if (javaClass != o?.javaClass) return false
    if (!super.equals(o)) return false

    o as ToonFilterPostprocessor

    if (threshold != o.threshold) return false
    if (quantizationLevels != o.quantizationLevels) return false

    return true
  }

  override fun hashCode(): Int = key().hashCode()
}