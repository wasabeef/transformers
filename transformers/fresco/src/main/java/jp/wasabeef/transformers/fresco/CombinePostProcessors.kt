package jp.wasabeef.transformers.fresco

import android.graphics.Bitmap
import com.facebook.common.references.CloseableReference
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory

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