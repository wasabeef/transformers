package jp.wasabeef.transformers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.drawee.backends.pipeline.Fresco

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

class FrescoActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    Fresco.initialize(this)
    setContentView(R.layout.activity_fresco)
    val recyclerView = findViewById<RecyclerView>(R.id.list).apply {
      layoutManager = GridLayoutManager(context, 2)
      addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

    val dataSet: MutableList<Type> = arrayListOf()
    dataSet.add(Type.Original)
    dataSet.add(Type.Mask)
    dataSet.add(Type.NinePatchMask)
    dataSet.add(Type.ColorFilter)
    dataSet.add(Type.Grayscale)
    dataSet.add(Type.RoundedCorners)
    dataSet.add(Type.RoundedCornersTopLeft)
    dataSet.add(Type.RSGaussianBlurLight)
    dataSet.add(Type.RSGaussianBlurDeep)
    dataSet.add(Type.StackBlurLight)
    dataSet.add(Type.StackBlurDeep)
    dataSet.add(Type.Toon)
    dataSet.add(Type.Sepia)
    dataSet.add(Type.Contrast)
    dataSet.add(Type.Invert)
    dataSet.add(Type.PixelLight)
    dataSet.add(Type.PixelDeep)
    dataSet.add(Type.Sketch)
    dataSet.add(Type.Swirl)
    dataSet.add(Type.Brightness)
    dataSet.add(Type.Kuawahara)
    dataSet.add(Type.Vignette)
    dataSet.add(Type.ZoomBlur)
    dataSet.add(Type.WhiteBalance)
    dataSet.add(Type.Halftone)
    dataSet.add(Type.Sharpness)
    dataSet.add(Type.ToneCurve)
    recyclerView.adapter = FrescoAdapter(this, dataSet)
  }
}