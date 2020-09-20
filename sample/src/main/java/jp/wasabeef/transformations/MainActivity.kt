package jp.wasabeef.transformations

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jp.wasabeef.transformations.MainAdapter.Type

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    val recyclerView = findViewById<RecyclerView>(R.id.list).apply {
      layoutManager = GridLayoutManager(context, 2)
      addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

    val dataSet: MutableList<Type> = arrayListOf()
    dataSet.add(Type.Mask)
    dataSet.add(Type.NinePatchMask)
    dataSet.add(Type.CropTop)
    dataSet.add(Type.CropCenter)
    dataSet.add(Type.CropBottom)
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
//    dataSet.add(Type.Toon)
//    dataSet.add(Type.Sepia)
//    dataSet.add(Type.Contrast)
//    dataSet.add(Type.Invert)
//    dataSet.add(Type.Pixel)
//    dataSet.add(Type.Sketch)
//    dataSet.add(Type.Swirl)
//    dataSet.add(Type.Brightness)
//    dataSet.add(Type.Kuawahara)
//    dataSet.add(Type.Vignette)
//    dataSet.add(Type.CropLeftTop)
//    dataSet.add(Type.CropLeftCenter)
//    dataSet.add(Type.CropLeftBottom)
//    dataSet.add(Type.CropRightTop)
//    dataSet.add(Type.CropRightCenter)
//    dataSet.add(Type.CropRightBottom)
//    dataSet.add(Type.Crop169CenterCenter)
//    dataSet.add(Type.Crop43CenterCenter)
//    dataSet.add(Type.Crop31CenterCenter)
//    dataSet.add(Type.Crop31CenterTop)
//    dataSet.add(Type.CropSquareCenterCenter)
//    dataSet.add(Type.CropQuarterCenterCenter)
//    dataSet.add(Type.CropQuarterCenterTop)
//    dataSet.add(Type.CropQuarterBottomRight)
//    dataSet.add(Type.CropHalfWidth43CenterCenter)
    recyclerView.adapter = MainAdapter(this, dataSet)
  }
}