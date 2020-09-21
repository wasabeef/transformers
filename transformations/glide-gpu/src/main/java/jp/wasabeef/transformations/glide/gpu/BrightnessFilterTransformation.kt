package jp.wasabeef.transformations.glide.gpu

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
import jp.co.cyberagent.android.gpuimage.filter.GPUImageBrightnessFilter

/**
 * brightness value ranges from -1.0 to 1.0, with 0.0 as the normal level
 */
class BrightnessFilterTransformation @JvmOverloads constructor(
  private val brightness: Float = 0.0f
) : GPUFilterTransformation(GPUImageBrightnessFilter()) {

  init {
    val filter: GPUImageBrightnessFilter = filter()
    filter.setBrightness(brightness)
  }

  override fun key(): String = "$id(brightness=$brightness)"
}