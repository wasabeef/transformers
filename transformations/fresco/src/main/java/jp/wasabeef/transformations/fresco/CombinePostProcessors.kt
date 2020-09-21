package jp.wasabeef.transformations.fresco

import android.graphics.Bitmap
import com.facebook.common.references.CloseableReference
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory

class CombinePostProcessors constructor(
  private vararg val processors: BasePostprocessor
) : com.facebook.imagepipeline.request.BasePostprocessor() {

  override fun process(
    sourceBitmap: Bitmap,
    bitmapFactory: PlatformBitmapFactory
  ): CloseableReference<Bitmap> {

    var source = sourceBitmap
    var ref: CloseableReference<Bitmap>? = null
    for (processor in processors) {
      val bitmapRef = processor.process(source, bitmapFactory)
      source = bitmapRef.get()
      try {
        ref = CloseableReference.cloneOrNull(bitmapRef)!!
      } finally {
        CloseableReference.closeSafely(bitmapRef)
      }
    }
    return ref ?: super.process(sourceBitmap, bitmapFactory)
  }
}