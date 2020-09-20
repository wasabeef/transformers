package jp.wasabeef.transformations.core

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Build
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.RenderScript.RSMessageHandler
import android.renderscript.ScriptIntrinsicBlur

/**
 * Copyright (C) 2020 Wasabeef
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

class RSGaussianBlur constructor(
  private val context: Context,
  private val radius: Int,
  private val sampling: Int
) : Transformation() {

  override fun transform(source: Bitmap, destination: Bitmap): Bitmap {

    val canvas = Canvas(destination)
    canvas.scale(1 / sampling.toFloat(), 1 / sampling.toFloat())
    val paint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG)
    canvas.drawBitmap(source, 0f, 0f, paint)
    canvas.setBitmap(null)

    var rs: RenderScript? = null
    var input: Allocation? = null
    var output: Allocation? = null
    var blur: ScriptIntrinsicBlur? = null
    try {
      rs = RenderScript.create(context)
      rs.messageHandler = RSMessageHandler()
      input = Allocation.createFromBitmap(
        rs, destination, Allocation.MipmapControl.MIPMAP_NONE,
        Allocation.USAGE_SCRIPT
      )
      output = Allocation.createTyped(rs, input.type)
      blur = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))
      blur.setInput(input)
      blur.setRadius(radius.toFloat())
      blur.forEach(output)
      output.copyTo(destination)
    } finally {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        RenderScript.releaseAllContexts()
      } else {
        rs?.destroy()
      }
      input?.destroy()
      output?.destroy()
      blur?.destroy()
    }
    return destination
  }

  override fun key(): String = "$id(radius=$radius, sampling=$sampling)"
}