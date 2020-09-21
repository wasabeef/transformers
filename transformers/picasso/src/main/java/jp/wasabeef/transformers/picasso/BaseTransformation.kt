package jp.wasabeef.transformers.picasso

import com.squareup.picasso.Transformation
import jp.wasabeef.transformers.core.Transformer

abstract class BaseTransformation(
  val transformer: Transformer
) : Transformation {

  override fun key() = transformer.key()
}