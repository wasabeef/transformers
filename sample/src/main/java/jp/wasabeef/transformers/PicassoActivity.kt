package jp.wasabeef.transformers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jp.wasabeef.transformers.PicassoAdapter.Type

class PicassoActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_picasso)
    val recyclerView = findViewById<RecyclerView>(R.id.list).apply {
      layoutManager = GridLayoutManager(context, 2)
      addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

    val dataSet: MutableList<Type> = arrayListOf()
    dataSet.add(Type.Original)
    dataSet.add(Type.Mask)
    dataSet.add(Type.NinePatchMask)
    dataSet.add(Type.CropTop)
    dataSet.add(Type.CropCenter)
    dataSet.add(Type.CropBottom)
    dataSet.add(Type.CropCenterRatio16x9)
    dataSet.add(Type.CropCenterRatio4x3)
    dataSet.add(Type.CropTopRatio16x9)
    dataSet.add(Type.CropBottomRatio4x3)
    dataSet.add(Type.CropCircle)
    dataSet.add(Type.CropCircleWithBorder)
    dataSet.add(Type.ColorFilter)
    dataSet.add(Type.Grayscale)
    dataSet.add(Type.RoundedCorners)
    dataSet.add(Type.RoundedCornersTopLeft)
    dataSet.add(Type.CropSquare)
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
    recyclerView.adapter = PicassoAdapter(this, dataSet)
  }
}