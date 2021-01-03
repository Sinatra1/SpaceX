package com.vladislav.shumilov.core_ui.injection.modules

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.core.ImagePipelineConfig
import com.facebook.imagepipeline.core.ImageTranscoderType
import com.facebook.imagepipeline.core.MemoryChunkType
import com.vladislav.shumilov.core_data.ApplicationContext
import com.vladislav.shumilov.core_data.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val app: Application) {

    private val appContext: Context

    init {
        appContext = app
        initFresco(appContext)
    }

    @Provides
    @ApplicationContext
    fun provideContext(): Context = appContext

    @Provides
    @ApplicationScope
    fun provideApp(): Application = app

    @Provides
    @ApplicationScope
    fun provideResources(@ApplicationContext context: Context): Resources =
        context.resources

    private fun initFresco(appContext: Context) {
        Fresco.initialize(
            appContext,
            ImagePipelineConfig.newBuilder(appContext)
                .setMemoryChunkType(MemoryChunkType.BUFFER_MEMORY)
                .setImageTranscoderType(ImageTranscoderType.JAVA_TRANSCODER)
                .experiment().setNativeCodeDisabled(true)
                .build()
        )
    }
}