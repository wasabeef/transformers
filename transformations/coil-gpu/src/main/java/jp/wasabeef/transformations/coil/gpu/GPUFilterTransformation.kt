package jp.wasabeef.transformations.coil.gpu

/**
 * Copyright (C) 2020 Wasabeef
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import android.content.Context
import android.graphics.Bitmap
import coil.bitmap.BitmapPool
import coil.size.Size
import coil.transform.Transformation
import jp.co.cyberagent.android.gpuimage.GPUImage
import jp.co.cyberagent.android.gpuimage.filter.GPUImageFilter
import jp.wasabeef.transformations.coil.gpu.BuildConfig.Version

abstract class GPUFilterTransformation(
  private val context: Context,
  private val filter: GPUImageFilter
) : Transformation {

  val version: String = Version

  protected val id: String
    get() = "${this::class.java.name}-${version}"

  override suspend fun transform(pool: BitmapPool, input: Bitmap, size: Size): Bitmap {
    val gpuImage = GPUImage(context)
    gpuImage.setImage(input)
    gpuImage.setFilter(filter)
    return gpuImage.bitmapWithFilterApplied
  }

  @Suppress("UNCHECKED_CAST")
  fun <T> filter(): T = filter as T

  override fun equals(o: Any?): Boolean {
    if (this === o) return true
    if (javaClass != o?.javaClass) return false

    o as GPUFilterTransformation

    if (filter != o.filter) return false
    if (version != o.version) return false

    return true
  }
}