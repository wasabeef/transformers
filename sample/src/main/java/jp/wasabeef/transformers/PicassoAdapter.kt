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
import com.squareup.picasso.Picasso
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
import jp.wasabeef.transformers.Type.CropSquare
import jp.wasabeef.transformers.Type.CropTop
import jp.wasabeef.transformers.Type.CropTopRatio16x9
import jp.wasabeef.transformers.Type.Grayscale
import jp.wasabeef.transformers.Type.Invert
import jp.wasabeef.transformers.Type.Kuawahara
import jp.wasabeef.transformers.Type.Mask
import jp.wasabeef.transformers.Type.NinePatchMask
import jp.wasabeef.transformers.Type.PixelLight
import jp.wasabeef.transformers.Type.RoundedCorners
import jp.wasabeef.transformers.Type.RoundedCornersTopLeft
import jp.wasabeef.transformers.Type.Sepia
import jp.wasabeef.transformers.Type.Sketch
import jp.wasabeef.transformers.Type.Swirl
import jp.wasabeef.transformers.Type.ToneCurve
import jp.wasabeef.transformers.Type.Toon
import jp.wasabeef.transformers.Type.Vignette
import jp.wasabeef.transformers.Type.WhiteBalance
import jp.wasabeef.transformers.Type.ZoomBlur
import jp.wasabeef.transformers.picasso.BlurTransformation
import jp.wasabeef.transformers.picasso.CenterCropTransformation
import jp.wasabeef.transformers.picasso.ColorFilterTransformation
import jp.wasabeef.transformers.picasso.CropCenterBottomTransformation
import jp.wasabeef.transformers.picasso.CropCenterTopTransformation
import jp.wasabeef.transformers.picasso.CropCenterTransformation
import jp.wasabeef.transformers.picasso.CropCircleTransformation
import jp.wasabeef.transformers.picasso.CropCircleWithBorderTransformation
import jp.wasabeef.transformers.picasso.CropSquareTransformation
import jp.wasabeef.transformers.picasso.CropTransformation
import jp.wasabeef.transformers.picasso.GrayscaleTransformation
import jp.wasabeef.transformers.picasso.MaskTransformation
import jp.wasabeef.transformers.picasso.RoundedCornersTransformation
import jp.wasabeef.transformers.picasso.gpu.BrightnessFilterTransformation
import jp.wasabeef.transformers.picasso.gpu.ContrastFilterTransformation
import jp.wasabeef.transformers.picasso.gpu.HalftoneFilterTransformation
import jp.wasabeef.transformers.picasso.gpu.InvertFilterTransformation
import jp.wasabeef.transformers.picasso.gpu.KuwaharaFilterTransformation
import jp.wasabeef.transformers.picasso.gpu.PixelationFilterTransformation
import jp.wasabeef.transformers.picasso.gpu.SepiaFilterTransformation
import jp.wasabeef.transformers.picasso.gpu.SharpenFilterTransformation
import jp.wasabeef.transformers.picasso.gpu.SketchFilterTransformation
import jp.wasabeef.transformers.picasso.gpu.SwirlFilterTransformation
import jp.wasabeef.transformers.picasso.gpu.ToneCurveFilterTransformation
import jp.wasabeef.transformers.picasso.gpu.VignetteFilterTransformation
import jp.wasabeef.transformers.picasso.gpu.WhiteBalanceFilterTransformation
import jp.wasabeef.transformers.picasso.gpu.ZoomBlurFilterTransformation
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

class PicassoAdapter(
  private val context: Context,
  private val dataSet: MutableList<Type>
) : RecyclerView.Adapter<PicassoAdapter.ViewHolder>() {

  companion object {
    private const val IMAGE_URL = "https://images.unsplash.com/photo-1588952159215-a4b39193464e"
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
        Picasso.get()
          .load(IMAGE_URL)
          .fit().centerInside().rotate(90f)
          .into(holder.image)
      }
      Mask -> {
        Picasso.get()
          .load(IMAGE_URL)
          .fit().centerInside().rotate(90f)
          .transform(
            mutableListOf(
              CropCenterTransformation(),
              MaskTransformation(context, R.drawable.mask_starfish)
            )
          ).into(holder.image)
      }
      NinePatchMask -> {
        Picasso.get()
          .load(IMAGE_URL)
          .fit().centerInside().rotate(90f)
          .transform(
            mutableListOf(
              CropCenterTransformation(),
              MaskTransformation(context, R.drawable.mask_chat_right)
            )
          ).into(holder.image)
      }

      CropTop ->
        Picasso.get()
          .load(IMAGE_URL)
          .fit().centerInside().rotate(90f)
          .transform(CropCenterTopTransformation())
          .into(holder.image)

      CropCenter ->
        Picasso.get()
          .load(IMAGE_URL)
          .fit().centerInside().rotate(90f)
          .transform(CenterCropTransformation())
          .into(holder.image)

      CropCenterRatio16x9 ->
        Picasso.get()
          .load(IMAGE_URL)
          .fit().centerInside().rotate(90f)
          .transform(
            CropTransformation(
              16f / 9f
            )
          )
          .into(holder.image)

      CropCenterRatio4x3 ->
        Picasso.get()
          .load(IMAGE_URL)
          .fit().centerInside().rotate(90f)
          .transform(
            CropTransformation(4f / 3f)
          ).into(holder.image)

      CropTopRatio16x9 ->
        Picasso.get()
          .load(IMAGE_URL)
          .fit().centerInside().rotate(90f)
          .transform(
            CropTransformation(
              16f / 9f,
              GravityHorizontal.START,
              GravityVertical.TOP
            )
          )
          .into(holder.image)

      CropBottomRatio4x3 ->
        Picasso.get()
          .load(IMAGE_URL)
          .fit().centerInside().rotate(90f)
          .transform(
            CropTransformation(4f / 3f, GravityHorizontal.START, GravityVertical.BOTTOM)
          ).into(holder.image)

      CropBottom ->
        Picasso.get()
          .load(IMAGE_URL)
          .fit().centerInside().rotate(90f)
          .transform(CropCenterBottomTransformation())
          .into(holder.image)

      CropSquare ->
        Picasso.get()
          .load(IMAGE_URL)
          .fit().centerInside().rotate(90f)
          .transform(CropSquareTransformation())
          .into(holder.image)

      CropCircle ->
        Picasso.get()
          .load(IMAGE_URL)
          .fit().centerInside().rotate(90f)
          .transform(
            mutableListOf(
              CropCenterTransformation(),
              CropCircleTransformation()
            )

          )
          .into(holder.image)

      CropCircleWithBorder ->
        Picasso.get()
          .load(IMAGE_URL)
          .fit().centerInside().rotate(90f)
          .transform(
            mutableListOf(
              CropCenterTransformation(),
              CropCircleWithBorderTransformation(4.dp, Color.rgb(0, 191, 255))
            )
          )
          .into(holder.image)

      ColorFilter ->
        Picasso.get()
          .load(IMAGE_URL)
          .fit().centerInside().rotate(90f)
          .transform(ColorFilterTransformation(Color.argb(90, 51, 204, 255)))
          .into(holder.image)

      Grayscale ->
        Picasso.get()
          .load(IMAGE_URL)
          .fit().centerInside().rotate(90f)
          .transform(GrayscaleTransformation())
          .into(holder.image)

      RoundedCorners ->
        Picasso.get()
          .load(IMAGE_URL)
          .fit().centerInside().rotate(90f)
          .transform(
            mutableListOf(
              CropCenterTransformation(),
              RoundedCornersTransformation(radius = 120)
            )
          )
          .into(holder.image)

      RoundedCornersTopLeft ->
        Picasso.get()
          .load(IMAGE_URL)
          .fit().centerInside().rotate(90f)
          .transform(
            mutableListOf(
              CropCenterTransformation(),
              RoundedCornersTransformation(
                radius = 120,
                cornerType = CornerType.DIAGONAL_FROM_TOP_LEFT
              )
            )
          )
          .into(holder.image)

      Type.RSGaussianBlurLight ->
        Picasso.get()
          .load(IMAGE_URL)
          .fit().centerInside().rotate(90f)
          .transform(BlurTransformation(context, 15, sampling = 1)).into(holder.image)
      Type.RSGaussianBlurDeep ->
        Picasso.get()
          .load(IMAGE_URL)
          .fit().centerInside().rotate(90f)
          .transform(
            BlurTransformation(context, 25, sampling = 4)
          ).into(holder.image)
      Type.StackBlurLight ->
        Picasso.get()
          .load(IMAGE_URL)
          .fit().centerInside().rotate(90f)
          .transform(BlurTransformation(context, 25, sampling = 1, rs = false))
          .into(holder.image)
      Type.StackBlurDeep ->
        Picasso.get()
          .load(IMAGE_URL)
          .fit().centerInside().rotate(90f)
          .transform(BlurTransformation(context, 25, sampling = 8, rs = false))
          .into(holder.image)

      Toon ->
        Picasso.get()
          .load(IMAGE_URL)
          .fit().centerInside().rotate(90f)
          .transform(
            jp.wasabeef.transformers.picasso.gpu.ToonFilterTransformation(
              context,
              0.2f,
              10.0f
            )
          )
          .into(holder.image)

      Sepia ->
        Picasso.get()
          .load(IMAGE_URL)
          .fit().centerInside().rotate(90f)
          .transform(SepiaFilterTransformation(context))
          .into(holder.image)

      Contrast ->
        Picasso.get()
          .load(IMAGE_URL)
          .fit().centerInside().rotate(90f)
          .transform(ContrastFilterTransformation(context, 2.0f))
          .into(holder.image)

      Invert ->
        Picasso.get()
          .load(IMAGE_URL)
          .fit().centerInside().rotate(90f)
          .transform(InvertFilterTransformation(context))
          .into(holder.image)

      PixelLight ->
        Picasso.get()
          .load(IMAGE_URL)
          .fit().centerInside().rotate(90f)
          .transform(PixelationFilterTransformation(context, 20f))
          .into(holder.image)

      Type.PixelDeep ->
        Picasso.get()
          .load(IMAGE_URL)
          .fit().centerInside().rotate(90f)
          .transform(PixelationFilterTransformation(context, 80f))
          .into(holder.image)

      Sketch ->
        Picasso.get()
          .load(IMAGE_URL)
          .fit().centerInside().rotate(90f)
          .transform(SketchFilterTransformation(context))
          .into(holder.image)

      Swirl ->
        Picasso.get()
          .load(IMAGE_URL)
          .fit().centerInside().rotate(90f)
          .transform(SwirlFilterTransformation(context, 0.5f, 1.0f, PointF(0.5f, 0.5f)))
          .into(holder.image)

      Brightness ->
        Picasso.get()
          .load(IMAGE_URL)
          .fit().centerInside().rotate(90f)
          .transform(BrightnessFilterTransformation(context, 0.4f))
          .into(holder.image)

      Kuawahara ->
        Picasso.get()
          .load(IMAGE_URL)
          .fit().centerInside().rotate(90f)
          .transform(KuwaharaFilterTransformation(context, 25))
          .into(holder.image)

      Vignette ->
        Picasso.get()
          .load(IMAGE_URL)
          .fit().centerInside().rotate(90f)
          .transform(
            VignetteFilterTransformation(
              context,
              PointF(0.5f, 0.5f),
              floatArrayOf(0.0f, 0.0f, 0.0f), 0f, 0.75f
            )
          )
          .into(holder.image)

      ZoomBlur ->
        Picasso.get()
          .load(IMAGE_URL)
          .fit().centerInside().rotate(90f)
          .transform(
            ZoomBlurFilterTransformation(
              context,
              PointF(0.5f, 0.5f),
              2.0f
            )
          )
          .into(holder.image)

      WhiteBalance ->
        Picasso.get()
          .load(IMAGE_URL)
          .fit().centerInside().rotate(90f)
          .transform(
            WhiteBalanceFilterTransformation(
              context,
              3000f, 1f
            )
          )
          .into(holder.image)

      Type.Halftone ->
        Picasso.get()
          .load(IMAGE_URL)
          .fit().centerInside().rotate(90f)
          .transform(
            HalftoneFilterTransformation(context)
          )
          .into(holder.image)

      Type.Sharpness ->
        Picasso.get()
          .load(IMAGE_URL)
          .fit().centerInside().rotate(90f)
          .transform(
            SharpenFilterTransformation(context, 2.0f)
          )
          .into(holder.image)

      ToneCurve ->
        Picasso.get()
          .load(IMAGE_URL)
          .fit().centerInside().rotate(90f)
          .transform(
            ToneCurveFilterTransformation(context, R.raw.tone_cuver_sample)
          )
          .into(holder.image)
    }
    holder.title.text = dataSet[position].name
  }

  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var image: ImageView = itemView.findViewById(R.id.image)
    var title: TextView = itemView.findViewById(R.id.title)
  }
}