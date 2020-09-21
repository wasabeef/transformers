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
import coil.load
import jp.wasabeef.transformations.CoilAdapter.Type.Brightness
import jp.wasabeef.transformations.CoilAdapter.Type.ColorFilter
import jp.wasabeef.transformations.CoilAdapter.Type.Contrast
import jp.wasabeef.transformations.CoilAdapter.Type.CropBottom
import jp.wasabeef.transformations.CoilAdapter.Type.CropBottomRatio4x3
import jp.wasabeef.transformations.CoilAdapter.Type.CropCenter
import jp.wasabeef.transformations.CoilAdapter.Type.CropCenterRatio16x9
import jp.wasabeef.transformations.CoilAdapter.Type.CropCenterRatio4x3
import jp.wasabeef.transformations.CoilAdapter.Type.CropCircle
import jp.wasabeef.transformations.CoilAdapter.Type.CropCircleWithBorder
import jp.wasabeef.transformations.CoilAdapter.Type.CropTop
import jp.wasabeef.transformations.CoilAdapter.Type.CropTopRatio16x9
import jp.wasabeef.transformations.CoilAdapter.Type.Grayscale
import jp.wasabeef.transformations.CoilAdapter.Type.Invert
import jp.wasabeef.transformations.CoilAdapter.Type.Kuawahara
import jp.wasabeef.transformations.CoilAdapter.Type.Mask
import jp.wasabeef.transformations.CoilAdapter.Type.NinePatchMask
import jp.wasabeef.transformations.CoilAdapter.Type.PixelDeep
import jp.wasabeef.transformations.CoilAdapter.Type.PixelLight
import jp.wasabeef.transformations.CoilAdapter.Type.RoundedCorners
import jp.wasabeef.transformations.CoilAdapter.Type.RoundedCornersTopLeft
import jp.wasabeef.transformations.CoilAdapter.Type.Sepia
import jp.wasabeef.transformations.CoilAdapter.Type.Sketch
import jp.wasabeef.transformations.CoilAdapter.Type.Swirl
import jp.wasabeef.transformations.CoilAdapter.Type.ToneCurve
import jp.wasabeef.transformations.CoilAdapter.Type.Vignette
import jp.wasabeef.transformations.CoilAdapter.Type.WhiteBalance
import jp.wasabeef.transformations.CoilAdapter.Type.ZoomBlur
import jp.wasabeef.transformations.coil.BlurTransformation
import jp.wasabeef.transformations.coil.CenterCropTransformation
import jp.wasabeef.transformations.coil.ColorFilterTransformation
import jp.wasabeef.transformations.coil.CropCenterBottomTransformation
import jp.wasabeef.transformations.coil.CropCenterTopTransformation
import jp.wasabeef.transformations.coil.CropCenterTransformation
import jp.wasabeef.transformations.coil.CropCircleTransformation
import jp.wasabeef.transformations.coil.CropCircleWithBorderTransformation
import jp.wasabeef.transformations.coil.CropSquareTransformation
import jp.wasabeef.transformations.coil.CropTransformation
import jp.wasabeef.transformations.coil.GrayscaleTransformation
import jp.wasabeef.transformations.coil.MaskTransformation
import jp.wasabeef.transformations.coil.RoundedCornersTransformation
import jp.wasabeef.transformations.coil.gpu.BrightnessFilterTransformation
import jp.wasabeef.transformations.coil.gpu.ContrastFilterTransformation
import jp.wasabeef.transformations.coil.gpu.HalftoneFilterTransformation
import jp.wasabeef.transformations.coil.gpu.InvertFilterTransformation
import jp.wasabeef.transformations.coil.gpu.KuwaharaFilterTransformation
import jp.wasabeef.transformations.coil.gpu.PixelationFilterTransformation
import jp.wasabeef.transformations.coil.gpu.SepiaFilterTransformation
import jp.wasabeef.transformations.coil.gpu.SharpenFilterTransformation
import jp.wasabeef.transformations.coil.gpu.SketchFilterTransformation
import jp.wasabeef.transformations.coil.gpu.SwirlFilterTransformation
import jp.wasabeef.transformations.coil.gpu.ToneCurveFilterTransformation
import jp.wasabeef.transformations.coil.gpu.ToonFilterTransformation
import jp.wasabeef.transformations.coil.gpu.VignetteFilterTransformation
import jp.wasabeef.transformations.coil.gpu.WhiteBalanceFilterTransformation
import jp.wasabeef.transformations.coil.gpu.ZoomBlurFilterTransformation
import jp.wasabeef.transformations.types.CornerType
import jp.wasabeef.transformations.types.GravityHorizontal
import jp.wasabeef.transformations.types.GravityVertical

/**
 * Created by Wasabeef on 2020/09/20.
 */
class CoilAdapter(
  private val context: Context,
  private val dataSet: MutableList<Type>
) : RecyclerView.Adapter<CoilAdapter.ViewHolder>() {

  companion object {
    private const val IMAGE_URL = "https://images.unsplash.com/photo-1588952159215-a4b39193464e"
    private const val SKIP_CACHE = true
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