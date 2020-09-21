package jp.wasabeef.transformers.picasso.gpu

import android.content.Context
import jp.co.cyberagent.android.gpuimage.filter.GPUImageSepiaToneFilter

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
 * Applies a simple sepia effect.
 * <p>
 * The intensity with a default of 1.0.
 */
class SepiaFilterTransformation @JvmOverloads constructor(
  context: Context,
  private val intensity: Float = 1.0f
) : GPUFilterTransformation(
  context,
  GPUImageSepiaToneFilter().apply {
    setIntensity(intensity)
  }
) {

  override fun key(): String = "$id(intensity=$intensity)"
}