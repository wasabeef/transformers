package jp.wasabeef.transformers.fresco

import android.graphics.Bitmap
import com.facebook.cache.common.CacheKey
import com.facebook.cache.common.SimpleCacheKey
import com.facebook.common.references.CloseableReference
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory
import com.facebook.imagepipeline.request.BasePostprocessor
import jp.wasabeef.transformers.core.Transformer

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