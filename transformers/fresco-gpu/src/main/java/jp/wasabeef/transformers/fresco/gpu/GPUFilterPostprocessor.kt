package jp.wasabeef.transformers.fresco.gpu

import android.content.Context
import android.graphics.Bitmap
import com.facebook.cache.common.CacheKey
import com.facebook.cache.common.SimpleCacheKey
import com.facebook.imagepipeline.request.BasePostprocessor
import jp.co.cyberagent.android.gpuimage.GPUImage
import jp.co.cyberagent.android.gpuimage.filter.GPUImageFilter
import jp.wasabeef.transformers.fresco.gpu.BuildConfig.Version

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

abstract class GPUFilterPostprocessor(
  private val context: Context,
  private val filter: GPUImageFilter
) : BasePostprocessor() {

  val version: String = Version

  protected val id: String
    get() = "${this::class.java.name}-$version"

  override fun process(destBitmap: Bitmap, sourceBitmap: Bitmap) {
    val gpuImage = GPUImage(context)
    gpuImage.setImage(sourceBitmap)
    gpuImage.setFilter(filter)
    val bitmap = gpuImage.bitmapWithFilterApplied

    super.process(destBitmap, bitmap)
  }

  override fun getName(): String {
    return this::class.java.name
  }

  override fun getPostprocessorCacheKey(): CacheKey {
    return SimpleCacheKey(key())
  }

  abstract fun key(): String
}