package jp.wasabeef.transformers

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

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

class GlideDownloader constructor(
  private val context: Context,
  private val name: String
) : CustomTarget<Drawable>(240.dp, 240.dp) {
  override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
    val bitmap = (resource as BitmapDrawable).bitmap
    val dir = context.getDir("glide", Context.MODE_PRIVATE)
    val fileName = "$name.png"

    if (!dir.exists()) {
      dir.mkdir()
    }
    val imageFile = File(dir, fileName)

    try {
      val fOut: OutputStream = FileOutputStream(imageFile)
      bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut)
      fOut.close()
    } catch (e: Exception) {
      e.printStackTrace()
    }
  }

  override fun onLoadCleared(placeholder: Drawable?) {
    // no-op
  }
}