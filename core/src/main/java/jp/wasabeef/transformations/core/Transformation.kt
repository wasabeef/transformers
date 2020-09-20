package jp.wasabeef.transformations.core

import android.content.Context
import android.graphics.Bitmap
import androidx.annotation.NonNull

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

abstract class Transformation {
  val version: String = BuildConfig.Version

  protected val id: String
    get() = "${this::class.java.name}-${version}"

  abstract fun transform(
    source: Bitmap,
    destination: Bitmap
  ): Bitmap

  abstract fun key(): String
}