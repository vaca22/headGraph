package com.vaca.headgraph

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.luck.picture.lib.basic.PictureSelector
import com.luck.picture.lib.config.SelectMimeType
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.interfaces.OnResultCallbackListener


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        PictureSelector.create(this)
//            .openCamera(SelectMimeType.ofImage())
//            .forResultActivity(object : OnResultCallbackListener<LocalMedia?> {
//                override fun onResult(result: ArrayList<LocalMedia?>?) {}
//                override fun onCancel() {}
//            })

        PictureSelector.create(this)
            .openGallery(SelectMimeType.ofImage())
            .setImageEngine(GlideEngine.createGlideEngine())
            .forResult(object : OnResultCallbackListener<LocalMedia?> {
                override fun onResult(result: ArrayList<LocalMedia?>?) {}
                override fun onCancel() {}
            })

    }
}