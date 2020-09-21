package jp.wasabeef.transformations

import android.content.Context
import android.graphics.Color
import android.graphics.PointF
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.drawable.ScalingUtils
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.common.ResizeOptions
import com.facebook.imagepipeline.request.ImageRequest
import com.facebook.imagepipeline.request.ImageRequestBuilder
import jp.wasabeef.transformations.FrescoAdapter.Type.Brightness
import jp.wasabeef.transformations.FrescoAdapter.Type.ColorFilter
import jp.wasabeef.transformations.FrescoAdapter.Type.Contrast
import jp.wasabeef.transformations.FrescoAdapter.Type.Grayscale
import jp.wasabeef.transformations.FrescoAdapter.Type.Halftone
import jp.wasabeef.transformations.FrescoAdapter.Type.Invert
import jp.wasabeef.transformations.FrescoAdapter.Type.Kuawahara
import jp.wasabeef.transformations.FrescoAdapter.Type.Mask
import jp.wasabeef.transformations.FrescoAdapter.Type.NinePatchMask
import jp.wasabeef.transformations.FrescoAdapter.Type.Original
import jp.wasabeef.transformations.FrescoAdapter.Type.PixelDeep
import jp.wasabeef.transformations.FrescoAdapter.Type.PixelLight
import jp.wasabeef.transformations.FrescoAdapter.Type.RSGaussianBlurDeep
import jp.wasabeef.transformations.FrescoAdapter.Type.RSGaussianBlurLight
import jp.wasabeef.transformations.FrescoAdapter.Type.Sepia
import jp.wasabeef.transformations.FrescoAdapter.Type.Sharpness
import jp.wasabeef.transformations.FrescoAdapter.Type.Sketch
import jp.wasabeef.transformations.FrescoAdapter.Type.StackBlurDeep
import jp.wasabeef.transformations.FrescoAdapter.Type.StackBlurLight
import jp.wasabeef.transformations.FrescoAdapter.Type.Swirl
import jp.wasabeef.transformations.FrescoAdapter.Type.ToneCurve
import jp.wasabeef.transformations.FrescoAdapter.Type.Toon
import jp.wasabeef.transformations.FrescoAdapter.Type.Vignette
import jp.wasabeef.transformations.FrescoAdapter.Type.WhiteBalance
import jp.wasabeef.transformations.FrescoAdapter.Type.ZoomBlur
import jp.wasabeef.transformations.fresco.BlurPostprocessor
import jp.wasabeef.transformations.fresco.ColorFilterPostprocessor
import jp.wasabeef.transformations.fresco.GrayscalePostprocessor
import jp.wasabeef.transformations.fresco.MaskPostprocessor
import jp.wasabeef.transformations.fresco.gpu.BrightnessFilterPostprocessor
import jp.wasabeef.transformations.fresco.gpu.ContrastFilterPostprocessor
import jp.wasabeef.transformations.fresco.gpu.HalftoneFilterPostprocessor
import jp.wasabeef.transformations.fresco.gpu.InvertFilterPostprocessor
import jp.wasabeef.transformations.fresco.gpu.KuwaharaFilterPostprocessor
import jp.wasabeef.transformations.fresco.gpu.PixelationFilterPostprocessor
import jp.wasabeef.transformations.fresco.gpu.SepiaFilterPostprocessor
import jp.wasabeef.transformations.fresco.gpu.SharpenFilterPostprocessor
import jp.wasabeef.transformations.fresco.gpu.SketchFilterPostprocessor
import jp.wasabeef.transformations.fresco.gpu.SwirlFilterPostprocessor
import jp.wasabeef.transformations.fresco.gpu.ToneCurveFilterPostprocessor
import jp.wasabeef.transformations.fresco.gpu.ToonFilterPostprocessor
import jp.wasabeef.transformations.fresco.gpu.VignetteFilterPostprocessor
import jp.wasabeef.transformations.fresco.gpu.WhiteBalanceFilterPostprocessor
import jp.wasabeef.transformations.fresco.gpu.ZoomBlurFilterPostprocessor

/**
 * Created by Wasabeef on 2020/09/20.
 */
class FrescoAdapter(
  private val context: Context,
  private val dataSet: MutableList<Type>
) : RecyclerView.Adapter<FrescoAdapter.ViewHolder>() {

  companion object {
    private const val IMAGE_URL = "https://images.unsplash.com/photo-1588952159215-a4b39193464e"
  }

  enum class Type {
    Original,
    Mask,
    NinePatchMask,
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
    val v = LayoutInflater.from(context).inflate(R.layout.layout_list_item_fresco, parent, false)
    return ViewHolder(v)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    holder.image.hierarchy.actualImageScaleType = ScalingUtils.ScaleType.CENTER_INSIDE
    val processor = when (dataSet[position]) {
      Original -> {
        null
      }
      Mask -> {
        holder.image.hierarchy.actualImageScaleType = ScalingUtils.ScaleType.CENTER_INSIDE
        MaskPostprocessor(context, R.drawable.mask_starfish)
      }
      NinePatchMask -> {
        holder.image.hierarchy.actualImageScaleType = ScalingUtils.ScaleType.CENTER_INSIDE
        MaskPostprocessor(context, R.drawable.mask_chat_right)
      }

      ColorFilter -> ColorFilterPostprocessor(Color.argb(90, 51, 204, 255))

      Grayscale -> GrayscalePostprocessor()

      RSGaussianBlurLight -> {
        holder.image.hierarchy.actualImageScaleType = ScalingUtils.ScaleType.FIT_CENTER
        BlurPostprocessor(context, 15, sampling = 1)
      }

      RSGaussianBlurDeep -> {
        holder.image.hierarchy.actualImageScaleType = ScalingUtils.ScaleType.FIT_CENTER
        BlurPostprocessor(context, 25, sampling = 4)
      }

      StackBlurLight -> {
        holder.image.hierarchy.actualImageScaleType = ScalingUtils.ScaleType.FIT_CENTER
        BlurPostprocessor(context, 25, sampling = 1, rs = false)
      }

      StackBlurDeep -> {
        holder.image.hierarchy.actualImageScaleType = ScalingUtils.ScaleType.FIT_CENTER
        BlurPostprocessor(context, 25, sampling = 8, rs = false)
      }

      Toon -> ToonFilterPostprocessor(context, 0.2f, 10.0f)

      Sepia -> SepiaFilterPostprocessor(context)

      Contrast -> ContrastFilterPostprocessor(context, 2.0f)

      Invert -> InvertFilterPostprocessor(context)

      PixelLight -> PixelationFilterPostprocessor(context, 20f)

      PixelDeep -> PixelationFilterPostprocessor(context, 80f)

      Sketch -> SketchFilterPostprocessor(context)

      Swirl -> SwirlFilterPostprocessor(context, 0.5f, 1.0f, PointF(0.5f, 0.5f))

      Brightness -> BrightnessFilterPostprocessor(context, 0.4f)

      Kuawahara -> KuwaharaFilterPostprocessor(context, 25)

      Vignette -> VignetteFilterPostprocessor(
        context,
        PointF(0.5f, 0.5f),
        floatArrayOf(0.0f, 0.0f, 0.0f), 0f, 0.75f
      )

      ZoomBlur -> ZoomBlurFilterPostprocessor(
        context,
        PointF(0.5f, 0.5f),
        2.0f
      )

      WhiteBalance -> WhiteBalanceFilterPostprocessor(
        context,
        3000f, 1f
      )

      Halftone -> HalftoneFilterPostprocessor(context)

      Sharpness -> SharpenFilterPostprocessor(context, 2.0f)

      ToneCurve -> ToneCurveFilterPostprocessor(context, R.raw.tone_cuver_sample)

      else -> {
        null
      }
    }
    val request: ImageRequest =
      ImageRequestBuilder.newBuilderWithSource(Uri.parse(IMAGE_URL)).apply {
        if (processor != null) {
          postprocessor = processor
        }
        resizeOptions = ResizeOptions.forDimensions(180.dp, 180.dp)
      }.build()

    holder.image.controller = Fresco.newDraweeControllerBuilder()
      .setImageRequest(request)
      .setOldController(holder.image.controller)
      .build()
    holder.title.text = dataSet[position].name
  }

  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var image: SimpleDraweeView = itemView.findViewById(R.id.image)
    var title: TextView = itemView.findViewById(R.id.title)
  }
}