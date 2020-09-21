package jp.wasabeef.transformations.fresco

import android.graphics.Bitmap
import com.facebook.common.references.CloseableReference
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory
import jp.wasabeef.transformations.core.Transformation
import jp.wasabeef.transformations.core.bitmapConfig

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

open class SimplePostprocessor constructor(
  transformation: Transformation
) : BasePostprocessor(transformation) {
  override fun transform(
    source: Bitmap,
    bitmapFactory: PlatformBitmapFactory
  ): CloseableReference<Bitmap> {
    return bitmapFactory.createBitmap(source.width, source.height, bitmapConfig(source))
  }
}