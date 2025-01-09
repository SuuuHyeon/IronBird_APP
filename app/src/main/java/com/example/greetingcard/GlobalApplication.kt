package com.example.greetingcard

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this, "aa0d4de155389c64d11681477ffe0ad6")
    }
}
