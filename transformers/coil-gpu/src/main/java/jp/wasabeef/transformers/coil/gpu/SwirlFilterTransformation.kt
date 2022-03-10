package jp.wasabeef.transformers.coil.gpu

import android.content.Context
import android.graphics.PointF
import jp.co.cyberagent.android.gpuimage.filter.GPUImageSwirlFilter

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

/**
 * Creates a swirl distortion on the image.
 * @param radius from 0.0 to 1.0, default 0.5
 * @param angle  minimum 0.0, default 1.0
 * @param center default (0.5, 0.5)
 */
class SwirlFilterTransformation @JvmOverloads constructor(
  context: Context,
  private val radius: Float = 0.5f,
  private val angle: Float = 1.0f,
  private val center: PointF = PointF(0.5f, 0.5f)
) : GPUFilterTransformation(
  context,
  GPUImageSwirlFilter().apply {
    setRadius(radius)
    setAngle(angle)
    setCenter(center)
  }
) {

  override val cacheKey: String get() = "$id(radius=$radius, angle=$angle, center=$center)"
}