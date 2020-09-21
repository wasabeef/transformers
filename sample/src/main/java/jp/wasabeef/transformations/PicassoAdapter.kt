package jp.wasabeef.transformations

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
import jp.wasabeef.transformations.PicassoAdapter.Type.Brightness
import jp.wasabeef.transformations.PicassoAdapter.Type.ColorFilter
import jp.wasabeef.transformations.PicassoAdapter.Type.Contrast
import jp.wasabeef.transformations.PicassoAdapter.Type.CropBottom
import jp.wasabeef.transformations.PicassoAdapter.Type.CropBottomRatio4x3
import jp.wasabeef.transformations.PicassoAdapter.Type.CropCenter
import jp.wasabeef.transformations.PicassoAdapter.Type.CropCenterRatio16x9
import jp.wasabeef.transformations.PicassoAdapter.Type.CropCenterRatio4x3
import jp.wasabeef.transformations.PicassoAdapter.Type.CropCircle
import jp.wasabeef.transformations.PicassoAdapter.Type.CropCircleWithBorder
import jp.wasabeef.transformations.PicassoAdapter.Type.CropSquare
import jp.wasabeef.transformations.PicassoAdapter.Type.CropTop
import jp.wasabeef.transformations.PicassoAdapter.Type.CropTopRatio16x9
import jp.wasabeef.transformations.PicassoAdapter.Type.Grayscale
import jp.wasabeef.transformations.PicassoAdapter.Type.Invert
import jp.wasabeef.transformations.PicassoAdapter.Type.Kuawahara
import jp.wasabeef.transformations.PicassoAdapter.Type.Mask
import jp.wasabeef.transformations.PicassoAdapter.Type.NinePatchMask
import jp.wasabeef.transformations.PicassoAdapter.Type.PixelLight
import jp.wasabeef.transformations.PicassoAdapter.Type.RoundedCorners
import jp.wasabeef.transformations.PicassoAdapter.Type.RoundedCornersTopLeft
import jp.wasabeef.transformations.PicassoAdapter.Type.Sepia
import jp.wasabeef.transformations.PicassoAdapter.Type.Sketch
import jp.wasabeef.transformations.PicassoAdapter.Type.Swirl
import jp.wasabeef.transformations.PicassoAdapter.Type.ToneCurve
import jp.wasabeef.transformations.PicassoAdapter.Type.Toon
import jp.wasabeef.transformations.PicassoAdapter.Type.Vignette
import jp.wasabeef.transformations.PicassoAdapter.Type.WhiteBalance
import jp.wasabeef.transformations.PicassoAdapter.Type.ZoomBlur
import jp.wasabeef.transformations.picasso.BlurTransformation
import jp.wasabeef.transformations.picasso.CenterCropTransformation
import jp.wasabeef.transformations.picasso.ColorFilterTransformation
import jp.wasabeef.transformations.picasso.CropCenterBottomTransformation
import jp.wasabeef.transformations.picasso.CropCenterTopTransformation
import jp.wasabeef.transformations.picasso.CropCenterTransformation
import jp.wasabeef.transformations.picasso.CropCircleTransformation
import jp.wasabeef.transformations.picasso.CropCircleWithBorderTransformation
import jp.wasabeef.transformations.picasso.CropSquareTransformation
import jp.wasabeef.transformations.picasso.CropTransformation
import jp.wasabeef.transformations.picasso.GrayscaleTransformation
import jp.wasabeef.transformations.picasso.MaskTransformation
import jp.wasabeef.transformations.picasso.RoundedCornersTransformation
import jp.wasabeef.transformations.picasso.gpu.BrightnessFilterTransformation
import jp.wasabeef.transformations.picasso.gpu.ContrastFilterTransformation
import jp.wasabeef.transformations.picasso.gpu.HalftoneFilterTransformation
import jp.wasabeef.transformations.picasso.gpu.InvertFilterTransformation
import jp.wasabeef.transformations.picasso.gpu.KuwaharaFilterTransformation
import jp.wasabeef.transformations.picasso.gpu.PixelationFilterTransformation
import jp.wasabeef.transformations.picasso.gpu.SepiaFilterTransformation
import jp.wasabeef.transformations.picasso.gpu.SharpenFilterTransformation
import jp.wasabeef.transformations.picasso.gpu.SketchFilterTransformation
import jp.wasabeef.transformations.picasso.gpu.SwirlFilterTransformation
import jp.wasabeef.transformations.picasso.gpu.ToneCurveFilterTransformation
import jp.wasabeef.transformations.picasso.gpu.VignetteFilterTransformation
import jp.wasabeef.transformations.picasso.gpu.WhiteBalanceFilterTransformation
import jp.wasabeef.transformations.picasso.gpu.ZoomBlurFilterTransformation
import jp.wasabeef.transformations.types.CornerType
import jp.wasabeef.transformations.types.GravityHorizontal
import jp.wasabeef.transformations.types.GravityVertical

/**
 * Created by Wasabeef on 2020/09/20.
 */
class PicassoAdapter(
  private val context: Context,
  private val dataSet: MutableList<Type>
) : RecyclerView.Adapter<PicassoAdapter.ViewHolder>() {

  companion object {
    private const val IMAGE_URL = "https://images.unsplash.com/photo-1588952159215-a4b39193464e"
  }

  enum class Type {
    Original,
    Mask,
    NinePatchMask,
    CropTop,
    CropCenter,
    CropCenterRatio16x9,
    CropCenterRatio4x3,
    CropTopRatio16x9,
    CropBottomRatio4x3,
    CropBottom,
    CropSquare,
    CropCircle,
    CropCircleWithBorder,
    ColorFilter,
    Grayscale,
    RoundedCorners,
    RoundedCornersTopLeft,
    RSGaussianBlurLight,
    RSGaussianBlurDeep,
    StackBlurLight,
    StackBlurDeep,
    Toon,
    Sepia,
    Contrast,
    Invert,
    PixelLight,
    PixelDeep,
    Sketch,
    Swirl,
    Brightness,
    Kuawahara,
    Vignette,
    ZoomBlur,
    WhiteBalance,
    Halftone,
    Sharpness,
    ToneCurve
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
            jp.wasabeef.transformations.picasso.gpu.ToonFilterTransformation(
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

      else -> {
        // no op
      }
    }
    holder.title.text = dataSet[position].name
  }

  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var image: ImageView = itemView.findViewById(R.id.image)
    var title: TextView = itemView.findViewById(R.id.title)
  }
}