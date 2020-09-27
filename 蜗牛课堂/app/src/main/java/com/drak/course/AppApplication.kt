package com.drak.course

import com.tencent.tinker.loader.app.TinkerApplication
import com.tencent.tinker.loader.shareutil.ShareConstants


/**
 *  @author BuMingYang
 *  @des
 */
class AppApplication : TinkerApplication(
    ShareConstants.TINKER_ENABLE_ALL, "com.drak.course.ApplicationLike",
    "com.tencent.tinker.loader.TinkerLoader", false
)