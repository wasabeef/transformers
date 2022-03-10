package jp.wasabeef.transformers.coil.gpu

import android.content.Context
import jp.co.cyberagent.android.gpuimage.filter.GPUImageContrastFilter

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
 * contrast value ranges from 0.0 to 4.0, with 1.0 as the normal level
 */
class ContrastFilterTransformation @JvmOverloads constructor(
  context: Context,
  private val contrast: Float = 1.0f
) : GPUFilterTransformation(
  context,
  GPUImageContrastFilter().apply {
    setContrast(contrast)
  }
) {
  override val cacheKey: String get() = "$id(contrast=$contrast)"
}