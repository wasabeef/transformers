package jp.wasabeef.transformers

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
import jp.wasabeef.transformers.FrescoAdapter.Type.Brightness
import jp.wasabeef.transformers.FrescoAdapter.Type.ColorFilter
import jp.wasabeef.transformers.FrescoAdapter.Type.Contrast
import jp.wasabeef.transformers.FrescoAdapter.Type.Grayscale
import jp.wasabeef.transformers.FrescoAdapter.Type.Halftone
import jp.wasabeef.transformers.FrescoAdapter.Type.Invert
import jp.wasabeef.transformers.FrescoAdapter.Type.Kuawahara
import jp.wasabeef.transformers.FrescoAdapter.Type.Mask
import jp.wasabeef.transformers.FrescoAdapter.Type.NinePatchMask
import jp.wasabeef.transformers.FrescoAdapter.Type.Original
import jp.wasabeef.transformers.FrescoAdapter.Type.PixelDeep
import jp.wasabeef.transformers.FrescoAdapter.Type.PixelLight
import jp.wasabeef.transformers.FrescoAdapter.Type.RSGaussianBlurDeep
import jp.wasabeef.transformers.FrescoAdapter.Type.RSGaussianBlurLight
import jp.wasabeef.transformers.FrescoAdapter.Type.Sepia
import jp.wasabeef.transformers.FrescoAdapter.Type.Sharpness
import jp.wasabeef.transformers.FrescoAdapter.Type.Sketch
import jp.wasabeef.transformers.FrescoAdapter.Type.StackBlurDeep
import jp.wasabeef.transformers.FrescoAdapter.Type.StackBlurLight
import jp.wasabeef.transformers.FrescoAdapter.Type.Swirl
import jp.wasabeef.transformers.FrescoAdapter.Type.ToneCurve
import jp.wasabeef.transformers.FrescoAdapter.Type.Toon
import jp.wasabeef.transformers.FrescoAdapter.Type.Vignette
import jp.wasabeef.transformers.FrescoAdapter.Type.WhiteBalance
import jp.wasabeef.transformers.FrescoAdapter.Type.ZoomBlur
import jp.wasabeef.transformers.fresco.BlurPostprocessor
import jp.wasabeef.transformers.fresco.ColorFilterPostprocessor
import jp.wasabeef.transformers.fresco.GrayscalePostprocessor
import jp.wasabeef.transformers.fresco.MaskPostprocessor
import jp.wasabeef.transformers.fresco.gpu.BrightnessFilterPostprocessor
import jp.wasabeef.transformers.fresco.gpu.ContrastFilterPostprocessor
import jp.wasabeef.transformers.fresco.gpu.HalftoneFilterPostprocessor
import jp.wasabeef.transformers.fresco.gpu.InvertFilterPostprocessor
import jp.wasabeef.transformers.fresco.gpu.KuwaharaFilterPostprocessor
import jp.wasabeef.transformers.fresco.gpu.PixelationFilterPostprocessor
import jp.wasabeef.transformers.fresco.gpu.SepiaFilterPostprocessor
import jp.wasabeef.transformers.fresco.gpu.SharpenFilterPostprocessor
import jp.wasabeef.transformers.fresco.gpu.SketchFilterPostprocessor
import jp.wasabeef.transformers.fresco.gpu.SwirlFilterPostprocessor
import jp.wasabeef.transformers.fresco.gpu.ToneCurveFilterPostprocessor
import jp.wasabeef.transformers.fresco.gpu.ToonFilterPostprocessor
import jp.wasabeef.transformers.fresco.gpu.VignetteFilterPostprocessor
import jp.wasabeef.transformers.fresco.gpu.WhiteBalanceFilterPostprocessor
import jp.wasabeef.transformers.fresco.gpu.ZoomBlurFilterPostprocessor

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