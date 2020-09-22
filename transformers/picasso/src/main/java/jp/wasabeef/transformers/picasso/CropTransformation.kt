package jp.wasabeef.transformers.picasso

import android.graphics.Bitmap
import jp.wasabeef.transformers.core.Crop
import jp.wasabeef.transformers.core.bitmapConfig
import jp.wasabeef.transformers.core.types.GravityHorizontal
import jp.wasabeef.transformers.core.types.GravityVertical

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

class CropTransformation : BaseTransformation {

  private var aspectRatio = 0f
  private var left = 0
  private var top = 0
  private var width = 0
  private var height = 0
  private var widthRatio = 0f
  private var heightRatio = 0f
  private var gravityHorizontal = GravityHorizontal.CENTER
  private var gravityVertical = GravityVertical.CENTER

  constructor(left: Int, top: Int, width: Int, height: Int) : super(
    Crop(
      left,
      top,
      width,
      height
    )
  ) {
    this.left = left
    this.top = top
    this.width = width
    this.height = height
  }

  @JvmOverloads
  constructor(
    width: Int,
    height: Int,
    gravityHorizontal: GravityHorizontal = GravityHorizontal.CENTER,
    gravityVertical: GravityVertical = GravityVertical.CENTER
  ) : super(Crop(width, height, gravityHorizontal, gravityVertical)) {
    this.width = width
    this.height = height
    this.gravityHorizontal = gravityHorizontal
    this.gravityVertical = gravityVertical
  }

  @JvmOverloads
  constructor(
    widthRatio: Float,
    heightRatio: Float,
    gravityHorizontal: GravityHorizontal = GravityHorizontal.CENTER,
    gravityVertical: GravityVertical = GravityVertical.CENTER
  ) : super(Crop(widthRatio, heightRatio, gravityHorizontal, gravityVertical)) {
    this.widthRatio = widthRatio
    this.heightRatio = heightRatio
    this.gravityHorizontal = gravityHorizontal
    this.gravityVertical = gravityVertical
  }

  @JvmOverloads
  constructor(
    width: Int,
    height: Int,
    aspectRatio: Float,
    gravityHorizontal: GravityHorizontal = GravityHorizontal.CENTER,
    gravityVertical: GravityVertical = GravityVertical.CENTER
  ) : super(Crop(width, height, aspectRatio, gravityHorizontal, gravityVertical)) {
    this.width = width
    this.height = height
    this.aspectRatio = aspectRatio
    this.gravityHorizontal = gravityHorizontal
    this.gravityVertical = gravityVertical
  }

  @JvmOverloads
  constructor(
    widthRatio: Float,
    heightRatio: Float,
    aspectRatio: Float,
    gravityHorizontal: GravityHorizontal = GravityHorizontal.CENTER,
    gravityVertical: GravityVertical = GravityVertical.CENTER
  ) : super(Crop(widthRatio, heightRatio, aspectRatio, gravityHorizontal, gravityVertical)) {
    this.widthRatio = widthRatio
    this.heightRatio = heightRatio
    this.aspectRatio = aspectRatio
    this.gravityHorizontal = gravityHorizontal
    this.gravityVertical = gravityVertical
  }

  @JvmOverloads
  constructor(
    aspectRatio: Float,
    gravityHorizontal: GravityHorizontal = GravityHorizontal.CENTER,
    gravityVertical: GravityVertical = GravityVertical.CENTER
  ) : super(Crop(aspectRatio, gravityHorizontal, gravityVertical)) {
    this.aspectRatio = aspectRatio
    this.gravityHorizontal = gravityHorizontal
    this.gravityVertical = gravityVertical
  }

  override fun transform(
    source: Bitmap
  ): Bitmap {
    val size = (transformer as Crop).calculateSize(source)
    val output = Bitmap.createBitmap(size.width, size.height, bitmapConfig(source))
    transformer.transform(source, output)
    source.recycle()
    return output
  }
}