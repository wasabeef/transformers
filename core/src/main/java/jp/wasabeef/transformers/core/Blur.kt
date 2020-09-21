package jp.wasabeef.transformers.core

import android.content.Context
import android.graphics.Bitmap
import android.renderscript.RSRuntimeException

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

class Blur constructor(
  context: Context,
  private val radius: Int,
  private val sampling: Int,
  private val rs: Boolean = true
) : Transformer() {

  private val stackBlur = StackBlur(radius, sampling)
  private val rsGaussianBlur = RSGaussianBlur(context, radius, sampling)

  override fun key(): String = "$id(radius=$radius, sampling=$sampling, rs=$rs)"

  override fun transform(source: Bitmap, destination: Bitmap): Bitmap {
    return if (rs) {
      try {
        rsGaussianBlur.transform(source, destination)
      } catch (e: RSRuntimeException) {
        stackBlur.transform(source, destination)
      }
    } else {
      stackBlur.transform(source, destination)
    }
  }
}