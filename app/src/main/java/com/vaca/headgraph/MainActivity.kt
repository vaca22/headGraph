package com.vaca.headgraph

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.luck.picture.lib.basic.PictureSelector
import com.luck.picture.lib.config.SelectMimeType
import com.luck.picture.lib.engine.CompressFileEngine
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.interfaces.OnKeyValueResultCallbackListener
import com.luck.picture.lib.interfaces.OnResultCallbackListener
import top.zibin.luban.Luban
import top.zibin.luban.OnNewCompressListener
import java.io.File


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
            .setCompressEngine(object:CompressFileEngine{
                override fun onStartCompress(
                    context: Context?,
                    source: java.util.ArrayList<Uri>?,
                    call: OnKeyValueResultCallbackListener?
                ) {
                    Luban.with(this@MainActivity).load(source).ignoreBy(100).setCompressListener(object:OnNewCompressListener{
                        override fun onStart() {

                        }

                        override fun onSuccess(source: String?, compressFile: File?) {
                            call?.onCallback(source,compressFile?.absolutePath)
                        }

                        override fun onError(source: String?, e: Throwable?) {
                            call?.onCallback(source,null)
                        }

                    }).launch()

                }

            })
            .forResult(object : OnResultCallbackListener<LocalMedia?> {
                override fun onResult(result: ArrayList<LocalMedia?>?) {
                    Log.e("fuck",result?.size.toString())
                    result?.let {
                        for(i in it){
                            val path=i!!.compressPath
                            Log.e("fuckyout",path)
                        }
                    }


                }
                override fun onCancel() {}
            })

    }
}