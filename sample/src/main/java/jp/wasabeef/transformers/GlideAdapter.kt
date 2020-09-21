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
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import jp.wasabeef.transformers.GlideAdapter.Type.Brightness
import jp.wasabeef.transformers.GlideAdapter.Type.ColorFilter
import jp.wasabeef.transformers.GlideAdapter.Type.Contrast
import jp.wasabeef.transformers.GlideAdapter.Type.CropBottom
import jp.wasabeef.transformers.GlideAdapter.Type.CropBottomRatio4x3
import jp.wasabeef.transformers.GlideAdapter.Type.CropCenter
import jp.wasabeef.transformers.GlideAdapter.Type.CropCenterRatio16x9
import jp.wasabeef.transformers.GlideAdapter.Type.CropCenterRatio4x3
import jp.wasabeef.transformers.GlideAdapter.Type.CropCircle
import jp.wasabeef.transformers.GlideAdapter.Type.CropCircleWithBorder
import jp.wasabeef.transformers.GlideAdapter.Type.CropSquare
import jp.wasabeef.transformers.GlideAdapter.Type.CropTop
import jp.wasabeef.transformers.GlideAdapter.Type.CropTopRatio16x9
import jp.wasabeef.transformers.GlideAdapter.Type.Grayscale
import jp.wasabeef.transformers.GlideAdapter.Type.Invert
import jp.wasabeef.transformers.GlideAdapter.Type.Kuawahara
import jp.wasabeef.transformers.GlideAdapter.Type.Mask
import jp.wasabeef.transformers.GlideAdapter.Type.NinePatchMask
import jp.wasabeef.transformers.GlideAdapter.Type.PixelDeep
import jp.wasabeef.transformers.GlideAdapter.Type.PixelLight
import jp.wasabeef.transformers.GlideAdapter.Type.RoundedCorners
import jp.wasabeef.transformers.GlideAdapter.Type.RoundedCornersTopLeft
import jp.wasabeef.transformers.GlideAdapter.Type.Sepia
import jp.wasabeef.transformers.GlideAdapter.Type.Sketch
import jp.wasabeef.transformers.GlideAdapter.Type.Swirl
import jp.wasabeef.transformers.GlideAdapter.Type.ToneCurve
import jp.wasabeef.transformers.GlideAdapter.Type.Toon
import jp.wasabeef.transformers.GlideAdapter.Type.Vignette
import jp.wasabeef.transformers.GlideAdapter.Type.WhiteBalance
import jp.wasabeef.transformers.GlideAdapter.Type.ZoomBlur
import jp.wasabeef.transformers.glide.BlurTransformation
import jp.wasabeef.transformers.glide.CenterCropTransformation
import jp.wasabeef.transformers.glide.ColorFilterTransformation
import jp.wasabeef.transformers.glide.CropCenterBottomTransformation
import jp.wasabeef.transformers.glide.CropCenterTopTransformation
import jp.wasabeef.transformers.glide.CropCenterTransformation
import jp.wasabeef.transformers.glide.CropCircleTransformation
import jp.wasabeef.transformers.glide.CropCircleWithBorderTransformation
import jp.wasabeef.transformers.glide.CropSquareTransformation
import jp.wasabeef.transformers.glide.CropTransformation
import jp.wasabeef.transformers.glide.GrayscaleTransformation
import jp.wasabeef.transformers.glide.MaskTransformation
import jp.wasabeef.transformers.glide.RoundedCornersTransformation
import jp.wasabeef.transformers.glide.gpu.BrightnessFilterTransformation
import jp.wasabeef.transformers.glide.gpu.ContrastFilterTransformation
import jp.wasabeef.transformers.glide.gpu.HalftoneFilterTransformation
import jp.wasabeef.transformers.glide.gpu.InvertFilterTransformation
import jp.wasabeef.transformers.glide.gpu.KuwaharaFilterTransformation
import jp.wasabeef.transformers.glide.gpu.PixelationFilterTransformation
import jp.wasabeef.transformers.glide.gpu.SepiaFilterTransformation
import jp.wasabeef.transformers.glide.gpu.SharpenFilterTransformation
import jp.wasabeef.transformers.glide.gpu.SketchFilterTransformation
import jp.wasabeef.transformers.glide.gpu.SwirlFilterTransformation
import jp.wasabeef.transformers.glide.gpu.ToneCurveFilterTransformation
import jp.wasabeef.transformers.glide.gpu.ToonFilterTransformation
import jp.wasabeef.transformers.glide.gpu.VignetteFilterTransformation
import jp.wasabeef.transformers.glide.gpu.WhiteBalanceFilterTransformation
import jp.wasabeef.transformers.glide.gpu.ZoomBlurFilterTransformation
import jp.wasabeef.transformers.types.CornerType
import jp.wasabeef.transformers.types.GravityHorizontal
import jp.wasabeef.transformers.types.GravityVertical

/**
 * Created by Wasabeef on 2020/09/20.
 */
class GlideAdapter(
  private val context: Context,
  private val dataSet: MutableList<Type>
) : RecyclerView.Adapter<GlideAdapter.ViewHolder>() {

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
        Glide.with(context)
          .load(IMAGE_URL)
          .into(holder.image)
      }
      Mask -> {
        Glide.with(context)
          .load(IMAGE_URL)
          .skipMemoryCache(SKIP_CACHE)
          .apply(
            bitmapTransform(
              MultiTransformation(
                CropCenterTransformation(),
                MaskTransformation(context, R.drawable.mask_starfish)
              )
            )
          ).into(holder.image)
      }
      NinePatchMask -> {
        Glide.with(context)
          .load(IMAGE_URL)
          .skipMemoryCache(SKIP_CACHE)
          .apply(
            bitmapTransform(
              MultiTransformation(
                CropCenterTransformation(),
                MaskTransformation(context, R.drawable.mask_chat_right)
              )
            )
          ).into(holder.image)
      }

      CropTop ->
        Glide.with(context)
          .load(IMAGE_URL)
          .skipMemoryCache(SKIP_CACHE)
          .apply(bitmapTransform(CropCenterTopTransformation()))
          .into(holder.image)

      CropCenter ->
        Glide.with(context)
          .load(IMAGE_URL)
          .skipMemoryCache(SKIP_CACHE)
          .apply(bitmapTransform(CenterCropTransformation()))
          .into(holder.image)

      CropCenterRatio16x9 ->
        Glide.with(context)
          .load(IMAGE_URL)
          .skipMemoryCache(SKIP_CACHE)
          .apply(
            bitmapTransform(
              CropTransformation(
                16f / 9f
              )
            )
          )
          .into(holder.image)

      CropCenterRatio4x3 ->
        Glide.with(context)
          .load(IMAGE_URL)
          .skipMemoryCache(SKIP_CACHE)
          .apply(
            bitmapTransform(
              CropTransformation(4f / 3f)
            )
          ).into(holder.image)

      CropTopRatio16x9 ->
        Glide.with(context)
          .load(IMAGE_URL)
          .skipMemoryCache(SKIP_CACHE)
          .apply(
            bitmapTransform(
              CropTransformation(
                16f / 9f,
                GravityHorizontal.START,
                GravityVertical.TOP
              )
            )
          )
          .into(holder.image)

      CropBottomRatio4x3 ->
        Glide.with(context)
          .load(IMAGE_URL)
          .skipMemoryCache(SKIP_CACHE)
          .apply(
            bitmapTransform(
              CropTransformation(4f / 3f, GravityHorizontal.START, GravityVertical.BOTTOM)
            )
          ).into(holder.image)

      CropBottom ->
        Glide.with(context)
          .load(IMAGE_URL)
          .skipMemoryCache(SKIP_CACHE)
          .apply(bitmapTransform(CropCenterBottomTransformation()))
          .into(holder.image)

      CropSquare ->
        Glide.with(context)
          .load(IMAGE_URL)
          .skipMemoryCache(SKIP_CACHE)
          .apply(bitmapTransform(CropSquareTransformation()))
          .into(holder.image)

      CropCircle ->
        Glide.with(context)
          .load(IMAGE_URL)
          .skipMemoryCache(SKIP_CACHE)
          .apply(
            bitmapTransform(
              MultiTransformation(
                CropCenterTransformation(),
                CropCircleTransformation()
              )
            )
          )
          .into(holder.image)

      CropCircleWithBorder ->
        Glide.with(context)
          .load(IMAGE_URL)
          .skipMemoryCache(SKIP_CACHE)
          .apply(
            bitmapTransform(
              MultiTransformation(
                CropCenterTransformation(),
                CropCircleWithBorderTransformation(4.dp, Color.rgb(0, 191, 255))
              )
            )
          )
          .into(holder.image)

      ColorFilter ->
        Glide.with(context)
          .load(IMAGE_URL)
          .skipMemoryCache(SKIP_CACHE)
          .apply(bitmapTransform(ColorFilterTransformation(Color.argb(90, 51, 204, 255))))
          .into(holder.image)

      Grayscale ->
        Glide.with(context)
          .load(IMAGE_URL)
          .skipMemoryCache(SKIP_CACHE)
          .apply(bitmapTransform(GrayscaleTransformation()))
          .into(holder.image)

      RoundedCorners ->
        Glide.with(context)
          .load(IMAGE_URL)
          .skipMemoryCache(SKIP_CACHE)
          .apply(
            bitmapTransform(
              MultiTransformation(
                CropCenterTransformation(),
                RoundedCornersTransformation(radius = 120)
              )
            )
          )
          .into(holder.image)

      RoundedCornersTopLeft ->
        Glide.with(context)
          .load(IMAGE_URL)
          .skipMemoryCache(SKIP_CACHE)
          .apply(
            bitmapTransform(
              MultiTransformation(
                CropCenterTransformation(),
                RoundedCornersTransformation(
                  radius = 120,
                  cornerType = CornerType.DIAGONAL_FROM_TOP_LEFT
                )
              )
            )
          )
          .into(holder.image)

      Type.RSGaussianBlurLight ->
        Glide.with(context)
          .load(IMAGE_URL)
          .skipMemoryCache(SKIP_CACHE)
          .apply(
            bitmapTransform(
              MultiTransformation(
                BlurTransformation(context, 15, sampling = 1)
              )
            )
          ).into(holder.image)
      Type.RSGaussianBlurDeep ->
        Glide.with(context)
          .load(IMAGE_URL)
          .skipMemoryCache(SKIP_CACHE)
          .apply(
            bitmapTransform(
              MultiTransformation(
                BlurTransformation(context, 25, sampling = 4)
              )
            )
          )
          .into(holder.image)
      Type.StackBlurLight ->
        Glide.with(context)
          .load(IMAGE_URL)
          .skipMemoryCache(SKIP_CACHE)
          .apply(bitmapTransform(BlurTransformation(context, 25, sampling = 1, rs = false)))
          .into(holder.image)
      Type.StackBlurDeep ->
        Glide.with(context)
          .load(IMAGE_URL)
          .skipMemoryCache(SKIP_CACHE)
          .apply(bitmapTransform(BlurTransformation(context, 25, sampling = 8, rs = false)))
          .into(holder.image)

      Toon ->
        Glide.with(context)
          .load(IMAGE_URL)
          .skipMemoryCache(SKIP_CACHE)
          .apply(bitmapTransform(ToonFilterTransformation(0.2f, 10.0f)))
          .into(holder.image)

      Sepia ->
        Glide.with(context)
          .load(IMAGE_URL)
          .skipMemoryCache(SKIP_CACHE)
          .apply(bitmapTransform(SepiaFilterTransformation()))
          .into(holder.image)

      Contrast ->
        Glide.with(context)
          .load(IMAGE_URL)
          .skipMemoryCache(SKIP_CACHE)
          .apply(bitmapTransform(ContrastFilterTransformation(2.0f)))
          .into(holder.image)

      Invert ->
        Glide.with(context)
          .load(IMAGE_URL)
          .skipMemoryCache(SKIP_CACHE)
          .apply(bitmapTransform(InvertFilterTransformation()))
          .into(holder.image)

      PixelLight ->
        Glide.with(context)
          .load(IMAGE_URL)
          .skipMemoryCache(SKIP_CACHE)
          .apply(bitmapTransform(PixelationFilterTransformation(20f)))
          .into(GlideDownloader(context, PixelLight.name))

      PixelDeep ->
        Glide.with(context)
          .load(IMAGE_URL)
          .skipMemoryCache(SKIP_CACHE)
          .apply(bitmapTransform(PixelationFilterTransformation(80f)))
          .into(GlideDownloader(context, PixelDeep.name))

      Sketch ->
        Glide.with(context)
          .load(IMAGE_URL)
          .skipMemoryCache(SKIP_CACHE)
          .apply(bitmapTransform(SketchFilterTransformation()))
          .into(holder.image)

      Swirl ->
        Glide.with(context)
          .load(IMAGE_URL)
          .skipMemoryCache(SKIP_CACHE)
          .apply(
            bitmapTransform(
              SwirlFilterTransformation(0.5f, 1.0f, PointF(0.5f, 0.5f))
            ).dontAnimate()
          )
          .into(holder.image)

      Brightness ->
        Glide.with(context)
          .load(IMAGE_URL)
          .skipMemoryCache(SKIP_CACHE)
          .apply(bitmapTransform(BrightnessFilterTransformation(0.4f)))
          .into(holder.image)

      Kuawahara ->
        Glide.with(context)
          .load(IMAGE_URL)
          .skipMemoryCache(SKIP_CACHE)
          .apply(bitmapTransform(KuwaharaFilterTransformation(25)).dontAnimate())
          .into(holder.image)

      Vignette ->
        Glide.with(context)
          .load(IMAGE_URL)
          .skipMemoryCache(SKIP_CACHE)
          .apply(
            bitmapTransform(
              VignetteFilterTransformation(
                PointF(0.5f, 0.5f),
                floatArrayOf(0.0f, 0.0f, 0.0f), 0f, 0.75f
              )
            )
          )
          .into(holder.image)

      ZoomBlur ->
        Glide.with(context)
          .load(IMAGE_URL)
          .skipMemoryCache(SKIP_CACHE)
          .apply(
            bitmapTransform(
              ZoomBlurFilterTransformation(
                PointF(0.5f, 0.5f),
                2.0f
              )
            )
          )
          .into(holder.image)

      WhiteBalance ->
        Glide.with(context)
          .load(IMAGE_URL)
          .skipMemoryCache(SKIP_CACHE)
          .apply(
            bitmapTransform(
              WhiteBalanceFilterTransformation(
                3000f, 1f
              )
            )
          )
          .into(holder.image)

      Type.Halftone ->
        Glide.with(context)
          .load(IMAGE_URL)
          .skipMemoryCache(SKIP_CACHE)
          .apply(
            bitmapTransform(
              HalftoneFilterTransformation()
            )
          )
          .into(holder.image)

      Type.Sharpness ->
        Glide.with(context)
          .load(IMAGE_URL)
          .skipMemoryCache(SKIP_CACHE)
          .apply(
            bitmapTransform(
              SharpenFilterTransformation(2.0f)
            )
          )
          .into(holder.image)

      ToneCurve ->
        Glide.with(context)
          .load(IMAGE_URL)
          .skipMemoryCache(SKIP_CACHE)
          .apply(
            bitmapTransform(
              ToneCurveFilterTransformation(context, R.raw.tone_cuver_sample)
            )
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