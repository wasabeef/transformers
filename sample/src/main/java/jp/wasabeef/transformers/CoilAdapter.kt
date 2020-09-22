package jp.wasabeef.transformers

import android.content.Context
import android.graphics.Color
import android.graphics.PointF
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import jp.wasabeef.transformers.Type.Brightness
import jp.wasabeef.transformers.Type.ColorFilter
import jp.wasabeef.transformers.Type.Contrast
import jp.wasabeef.transformers.Type.CropBottom
import jp.wasabeef.transformers.Type.CropBottomRatio4x3
import jp.wasabeef.transformers.Type.CropCenter
import jp.wasabeef.transformers.Type.CropCenterRatio16x9
import jp.wasabeef.transformers.Type.CropCenterRatio4x3
import jp.wasabeef.transformers.Type.CropCircle
import jp.wasabeef.transformers.Type.CropCircleWithBorder
import jp.wasabeef.transformers.Type.CropTop
import jp.wasabeef.transformers.Type.CropTopRatio16x9
import jp.wasabeef.transformers.Type.Grayscale
import jp.wasabeef.transformers.Type.Invert
import jp.wasabeef.transformers.Type.Kuawahara
import jp.wasabeef.transformers.Type.Mask
import jp.wasabeef.transformers.Type.NinePatchMask
import jp.wasabeef.transformers.Type.PixelDeep
import jp.wasabeef.transformers.Type.PixelLight
import jp.wasabeef.transformers.Type.RoundedCorners
import jp.wasabeef.transformers.Type.RoundedCornersTopLeft
import jp.wasabeef.transformers.Type.Sepia
import jp.wasabeef.transformers.Type.Sketch
import jp.wasabeef.transformers.Type.Swirl
import jp.wasabeef.transformers.Type.ToneCurve
import jp.wasabeef.transformers.Type.Vignette
import jp.wasabeef.transformers.Type.WhiteBalance
import jp.wasabeef.transformers.Type.ZoomBlur
import jp.wasabeef.transformers.coil.BlurTransformation
import jp.wasabeef.transformers.coil.CenterCropTransformation
import jp.wasabeef.transformers.coil.ColorFilterTransformation
import jp.wasabeef.transformers.coil.CropCenterBottomTransformation
import jp.wasabeef.transformers.coil.CropCenterTopTransformation
import jp.wasabeef.transformers.coil.CropCenterTransformation
import jp.wasabeef.transformers.coil.CropCircleTransformation
import jp.wasabeef.transformers.coil.CropCircleWithBorderTransformation
import jp.wasabeef.transformers.coil.CropSquareTransformation
import jp.wasabeef.transformers.coil.CropTransformation
import jp.wasabeef.transformers.coil.GrayscaleTransformation
import jp.wasabeef.transformers.coil.MaskTransformation
import jp.wasabeef.transformers.coil.RoundedCornersTransformation
import jp.wasabeef.transformers.coil.gpu.BrightnessFilterTransformation
import jp.wasabeef.transformers.coil.gpu.ContrastFilterTransformation
import jp.wasabeef.transformers.coil.gpu.HalftoneFilterTransformation
import jp.wasabeef.transformers.coil.gpu.InvertFilterTransformation
import jp.wasabeef.transformers.coil.gpu.KuwaharaFilterTransformation
import jp.wasabeef.transformers.coil.gpu.PixelationFilterTransformation
import jp.wasabeef.transformers.coil.gpu.SepiaFilterTransformation
import jp.wasabeef.transformers.coil.gpu.SharpenFilterTransformation
import jp.wasabeef.transformers.coil.gpu.SketchFilterTransformation
import jp.wasabeef.transformers.coil.gpu.SwirlFilterTransformation
import jp.wasabeef.transformers.coil.gpu.ToneCurveFilterTransformation
import jp.wasabeef.transformers.coil.gpu.ToonFilterTransformation
import jp.wasabeef.transformers.coil.gpu.VignetteFilterTransformation
import jp.wasabeef.transformers.coil.gpu.WhiteBalanceFilterTransformation
import jp.wasabeef.transformers.coil.gpu.ZoomBlurFilterTransformation
import jp.wasabeef.transformers.types.CornerType
import jp.wasabeef.transformers.types.GravityHorizontal
import jp.wasabeef.transformers.types.GravityVertical

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

class CoilAdapter(
  private val context: Context,
  private val dataSet: MutableList<Type>
) : RecyclerView.Adapter<CoilAdapter.ViewHolder>() {

  companion object {
    private const val IMAGE_URL = "https://images.unsplash.com/photo-1588952159215-a4b39193464e"
    private const val SKIP_CACHE = true
  }

  override fun getItemCount(): Int {
    return dataSet.size
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val v = LayoutInflater.from(context).inflate(R.layout.layout_list_item, parent, false)
    return ViewHolder(v)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    when (dataSet[position]) {
      Type.Original -> {
        holder.image.load(IMAGE_URL)
      }
      Mask -> {
        holder.image.load(IMAGE_URL) {
          transformations(
            CropCenterTransformation(),
            MaskTransformation(context, R.drawable.mask_starfish)
          )
        }
      }
      NinePatchMask -> {
        holder.image.load(IMAGE_URL) {
          transformations(
            CropCenterTransformation(),
            MaskTransformation(context, R.drawable.mask_chat_right)
          )
        }
      }

      CropTop -> holder.image.load(IMAGE_URL) {
        transformations(CropCenterTopTransformation())
      }

      CropCenter -> holder.image.load(IMAGE_URL) {
        transformations(CenterCropTransformation())
      }

      CropCenterRatio16x9 -> holder.image.load(IMAGE_URL) {
        transformations(
          CropTransformation(
            16f / 9f
          )
        )
      }

      CropCenterRatio4x3 -> holder.image.load(IMAGE_URL) {
        transformations(
          CropTransformation(4f / 3f)
        )
      }

      CropTopRatio16x9 -> holder.image.load(IMAGE_URL) {
        transformations(
          CropTransformation(
            16f / 9f,
            GravityHorizontal.START,
            GravityVertical.TOP
          )
        )
      }

      CropBottomRatio4x3 -> holder.image.load(IMAGE_URL) {
        transformations(
          CropTransformation(4f / 3f, GravityHorizontal.START, GravityVertical.BOTTOM)
        )
      }

      CropBottom -> holder.image.load(IMAGE_URL) {
        transformations(CropCenterBottomTransformation())
      }

      Type.CropSquare -> holder.image.load(IMAGE_URL) {
        transformations(CropSquareTransformation())
      }

      CropCircle ->
        holder.image.load(IMAGE_URL) {
          transformations(
            CropCenterTransformation(),
            CropCircleTransformation()
          )
        }

      CropCircleWithBorder -> holder.image.load(IMAGE_URL) {
        transformations(
          CropCenterTransformation(),
          CropCircleWithBorderTransformation(4.dp, Color.rgb(0, 191, 255))
        )
      }

      ColorFilter -> holder.image.load(IMAGE_URL) {
        transformations(ColorFilterTransformation(Color.argb(90, 51, 204, 255)))
      }

      Grayscale -> holder.image.load(IMAGE_URL) {
        transformations(GrayscaleTransformation())
      }

      RoundedCorners -> holder.image.load(IMAGE_URL) {
        transformations(
          CropCenterTransformation(),
          RoundedCornersTransformation(radius = 120)
        )
      }

      RoundedCornersTopLeft -> holder.image.load(IMAGE_URL) {
        transformations(
          CropCenterTransformation(),
          RoundedCornersTransformation(
            radius = 120,
            cornerType = CornerType.DIAGONAL_FROM_TOP_LEFT
          )
        )
      }

      Type.RSGaussianBlurLight -> holder.image.load(IMAGE_URL) {
        transformations(
          BlurTransformation(context, 15, sampling = 1)
        )
      }

      Type.RSGaussianBlurDeep -> holder.image.load(IMAGE_URL) {
        transformations(
          BlurTransformation(context, 25, sampling = 4)
        )
      }

      Type.StackBlurLight -> holder.image.load(IMAGE_URL) {
        transformations(BlurTransformation(context, 25, sampling = 1, rs = false))
      }

      Type.StackBlurDeep -> holder.image.load(IMAGE_URL) {
        transformations(BlurTransformation(context, 25, sampling = 8, rs = false))
      }

      Type.Toon -> holder.image.load(IMAGE_URL) {
        transformations(ToonFilterTransformation(context, 0.2f, 10.0f))
      }

      Sepia -> holder.image.load(IMAGE_URL) {
        transformations(SepiaFilterTransformation(context))
      }

      Contrast -> holder.image.load(IMAGE_URL) {
        transformations(ContrastFilterTransformation(context, 2.0f))
      }

      Invert -> holder.image.load(IMAGE_URL) {
        transformations(InvertFilterTransformation(context))
      }

      PixelLight -> holder.image.load(IMAGE_URL) {
        transformations(PixelationFilterTransformation(context, 20f))
      }

      PixelDeep -> holder.image.load(IMAGE_URL) {
        transformations(PixelationFilterTransformation(context, 80f))
      }

      Sketch -> holder.image.load(IMAGE_URL) {
        transformations(SketchFilterTransformation(context))
      }

      Swirl -> holder.image.load(IMAGE_URL) {
        transformations(
          SwirlFilterTransformation(context, 0.5f, 1.0f, PointF(0.5f, 0.5f))
        )
      }

      Brightness -> holder.image.load(IMAGE_URL) {
        transformations(BrightnessFilterTransformation(context, 0.4f))
      }

      Kuawahara -> holder.image.load(IMAGE_URL) {
        transformations(KuwaharaFilterTransformation(context, 25))
      }

      Vignette -> holder.image.load(IMAGE_URL) {
        transformations(
          VignetteFilterTransformation(
            context,
            PointF(0.5f, 0.5f),
            floatArrayOf(0.0f, 0.0f, 0.0f), 0f, 0.75f
          )
        )
      }

      ZoomBlur -> holder.image.load(IMAGE_URL) {
        transformations(
          ZoomBlurFilterTransformation(
            context,
            PointF(0.5f, 0.5f),
            2.0f
          )
        )
      }

      WhiteBalance -> holder.image.load(IMAGE_URL) {
        transformations(
          WhiteBalanceFilterTransformation(
            context,
            3000f, 1f
          )
        )
      }

      Type.Halftone -> holder.image.load(IMAGE_URL) {
        transformations(
          HalftoneFilterTransformation(context)
        )
      }

      Type.Sharpness -> holder.image.load(IMAGE_URL) {
        transformations(
          SharpenFilterTransformation(context, 2.0f)
        )
      }

      ToneCurve -> holder.image.load(IMAGE_URL) {
        transformations(
          ToneCurveFilterTransformation(context, R.raw.tone_cuver_sample)
        )
      }
    }
    holder.title.text = dataSet[position].name
  }

  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var image: ImageView = itemView.findViewById(R.id.image)
    var title: TextView = itemView.findViewById(R.id.title)
  }
}