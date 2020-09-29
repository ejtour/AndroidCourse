package com.drak.application

import com.tencent.tinker.loader.app.TinkerApplication
import com.tencent.tinker.loader.shareutil.ShareConstants


/**
 *  @author BuMingYang
 *  @des
 */
class AppApplication : TinkerApplication(
    ShareConstants.TINKER_ENABLE_ALL, "com.drak.application.ApplicationLike",
    "com.tencent.tinker.loader.TinkerLoader", false
)