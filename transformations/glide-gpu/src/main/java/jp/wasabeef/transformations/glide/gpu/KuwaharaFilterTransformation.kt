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
import jp.co.cyberagent.android.gpuimage.filter.GPUImageKuwaharaFilter

/**
 * Kuwahara all the colors in the image.
 * <p>
 * The radius to sample from when creating the brush-stroke effect, with a default of 25.
 * The larger the radius, the slower the filter.
 */
class KuwaharaFilterTransformation @JvmOverloads constructor(
  private val radius: Int = 25
) : GPUFilterTransformation(GPUImageKuwaharaFilter()) {

  init {
    val filter: GPUImageKuwaharaFilter = filter()
    filter.setRadius(radius)
  }

  override fun key(): String = "$id(radius=$radius)"
}