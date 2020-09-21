package jp.wasabeef.transformers.coil

import coil.transform.Transformation
import jp.wasabeef.transformers.core.Transformer

abstract class BaseTransformation(
  val transformer: Transformer
) : Transformation {

  override fun key() = transformer.key()
}