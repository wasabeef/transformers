package jp.wasabeef.transformers.picasso.gpu

import android.content.Context
import jp.co.cyberagent.android.gpuimage.filter.GPUImageWhiteBalanceFilter

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
 * Allows adjustment of color temperature in terms of what an image was effectively shot in.
 * This means higher Kelvin values will warm the image, while lower values will cool it.
 * @param temperature choose color temperature, in degrees Kelvin
 * @param tint adjust tint to compensate
 */
class WhiteBalanceFilterTransformation @JvmOverloads constructor(
  context: Context,
  private val temperature: Float = 5000.0f,
  private val tint: Float = 0.0f
) : GPUFilterTransformation(context, GPUImageWhiteBalanceFilter()) {

  init {
    val filter: GPUImageWhiteBalanceFilter = filter()
    filter.setTemperature(temperature)
    filter.setTint(tint)
  }

  override fun key(): String = "$id(temperature=$temperature, tint=$tint)"
}