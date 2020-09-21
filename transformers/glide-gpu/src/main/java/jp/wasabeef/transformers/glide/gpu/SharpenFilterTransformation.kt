package jp.wasabeef.transformers.glide.gpu

import jp.co.cyberagent.android.gpuimage.filter.GPUImageSharpenFilter

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

/**
 * Sharpens the picture. <br>
 * <br>
 * sharpness: from -4.0 to 4.0, with 0.0 as the normal level
 */
class SharpenFilterTransformation @JvmOverloads constructor(
  private val sharpness: Float = 0.0f
) : GPUFilterTransformation(
  GPUImageSharpenFilter().apply {
    setSharpness(sharpness)
  }
) {

  override fun key(): String = "$id(sharpness=$sharpness)"
}