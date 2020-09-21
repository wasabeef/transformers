package jp.wasabeef.transformations

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.drawee.backends.pipeline.Fresco
import jp.wasabeef.transformations.FrescoAdapter.Type

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