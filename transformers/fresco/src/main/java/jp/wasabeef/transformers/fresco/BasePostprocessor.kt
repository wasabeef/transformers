package jp.wasabeef.transformers.fresco

import android.graphics.Bitmap
import com.facebook.cache.common.CacheKey
import com.facebook.cache.common.SimpleCacheKey
import com.facebook.common.references.CloseableReference
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory
import com.facebook.imagepipeline.request.BasePostprocessor
import jp.wasabeef.transformers.core.Transformer

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

abstract class BasePostprocessor constructor(
  val transformer: Transformer
) : BasePostprocessor() {

  abstract fun transform(
    source: Bitmap,
    bitmapFactory: PlatformBitmapFactory
  ): CloseableReference<Bitmap>

  override fun process(
    sourceBitmap: Bitmap,
    bitmapFactory: PlatformBitmapFactory
  ): CloseableReference<Bitmap> {
    val bitmapRef = transform(sourceBitmap, bitmapFactory)
    val destBitmap = bitmapRef.get()

    return try {
      transformer.transform(sourceBitmap, destBitmap)
      CloseableReference.cloneOrNull(bitmapRef)!!
    } finally {
      CloseableReference.closeSafely(bitmapRef)
    }
  }

  override fun getName(): String {
    return this::class.java.name
  }

  override fun getPostprocessorCacheKey(): CacheKey {
    return SimpleCacheKey(transformer.key())
  }
}