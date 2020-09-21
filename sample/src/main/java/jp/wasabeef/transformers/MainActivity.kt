package jp.wasabeef.transformers

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    glide.setOnClickListener {
      startActivity(Intent(this, GlideActivity::class.java))
    }
    picasso.setOnClickListener {
      startActivity(Intent(this, PicassoActivity::class.java))
    }
    coil.setOnClickListener {
      startActivity(Intent(this, CoilActivity::class.java))
    }
    fresco.setOnClickListener {
      startActivity(Intent(this, FrescoActivity::class.java))
    }
  }
}