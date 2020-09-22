package jp.wasabeef.transformers.glide.gpu

import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.load.Key
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.resource.bitmap.BitmapResource
import com.bumptech.glide.util.Util
import java.security.MessageDigest
import jp.co.cyberagent.android.gpuimage.GPUImage
import jp.co.cyberagent.android.gpuimage.filter.GPUImageFilter
import jp.wasabeef.transformers.glide.gpu.BuildConfig.Version

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

abstract class GPUFilterTransformation(
  private val filter: GPUImageFilter
) : Transformation<Bitmap> {

  val version: String = Version

  protected val id: String
    get() = "${this::class.java.name}-$version"

  override fun transform(
    context: Context,
    resource: Resource<Bitmap>,
    outWidth: Int,
    outHeight: Int
  ): Resource<Bitmap> {
    require(Util.isValidDimensions(outWidth, outHeight)) {
      (
        "Cannot apply transformation on width: " + outWidth + " or height: " + outHeight +
          " less than or equal to zero and not Target.SIZE_ORIGINAL"
        )
    }
    val bitmapPool = Glide.get(context).bitmapPool
    val toTransform = resource.get()
    val transformed = transform(context.applicationContext, toTransform)
    val result: Resource<Bitmap>
    result = if (toTransform == transformed) {
      resource
    } else {
      BitmapResource.obtain(transformed, bitmapPool)!!
    }
    return result
  }

  private fun transform(
    context: Context,
    source: Bitmap
  ): Bitmap {
    val gpuImage = GPUImage(context)
    gpuImage.setImage(source)
    gpuImage.setFilter(filter)
    return gpuImage.bitmapWithFilterApplied
  }

  override fun updateDiskCacheKey(messageDigest: MessageDigest) {
    messageDigest.update(key().toByteArray(Key.CHARSET))
  }

  abstract fun key(): String
}