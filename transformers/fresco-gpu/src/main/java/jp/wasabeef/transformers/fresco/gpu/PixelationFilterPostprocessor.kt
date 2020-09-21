package jp.wasabeef.transformers.fresco.gpu

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
import android.content.Context
import jp.co.cyberagent.android.gpuimage.filter.GPUImagePixelationFilter

/**
 * Applies a Pixelation effect to the image.
 * <p>
 * The pixel with a default of 10.0.
 */
class PixelationFilterPostprocessor @JvmOverloads constructor(
  context: Context,
  private val pixel: Float = 10f
) : GPUFilterPostprocessor(context, GPUImagePixelationFilter()) {

  init {
    val filter: GPUImagePixelationFilter = filter()
    filter.setPixel(pixel)
  }

  override fun key(): String = "$id(pixel=$pixel)"
}