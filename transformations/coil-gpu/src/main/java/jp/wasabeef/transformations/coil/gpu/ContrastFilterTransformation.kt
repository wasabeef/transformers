package jp.wasabeef.transformations.coil.gpu

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
import jp.co.cyberagent.android.gpuimage.filter.GPUImageContrastFilter

/**
 * contrast value ranges from 0.0 to 4.0, with 1.0 as the normal level
 */
class ContrastFilterTransformation @JvmOverloads constructor(
  context: Context,
  private val contrast: Float = 1.0f
) : GPUFilterTransformation(context, GPUImageContrastFilter()) {

  init {
    val filter: GPUImageContrastFilter = filter()
    filter.setContrast(contrast)
  }

  override fun key(): String = "$id(contrast=$contrast)"

  override fun equals(o: Any?): Boolean {
    if (this === o) return true
    if (javaClass != o?.javaClass) return false
    if (!super.equals(o)) return false

    o as ContrastFilterTransformation

    if (contrast != o.contrast) return false

    return true
  }

  override fun hashCode(): Int = key().hashCode()
}