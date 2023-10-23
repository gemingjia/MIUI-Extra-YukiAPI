package moe.chenxy.miuiextra.hooker

import com.highcapable.yukihookapi.YukiHookAPI
import com.highcapable.yukihookapi.YukiHookAPI.configs
import com.highcapable.yukihookapi.annotation.xposed.InjectYukiHookWithXposed
import com.highcapable.yukihookapi.hook.xposed.proxy.IYukiHookXposedInit
import de.robv.android.xposed.XSharedPreferences
import moe.chenxy.miuiextra.BuildConfig
import moe.chenxy.miuiextra.hooker.entity.MiWallpaperHook
import moe.chenxy.miuiextra.hooker.entity.MiuiHomeHook
import moe.chenxy.miuiextra.hooker.entity.MiuiSettingsHook
import moe.chenxy.miuiextra.hooker.entity.PowerKeeperHook
import moe.chenxy.miuiextra.hooker.entity.SystemHooker
import moe.chenxy.miuiextra.hooker.entity.systemui.NavigationBarHooker
import moe.chenxy.miuiextra.hooker.entity.systemui.SystemUIPluginHook
import moe.chenxy.miuiextra.hooker.entity.systemui.SystemUIMainHooker

@InjectYukiHookWithXposed
object HookEntry : IYukiHookXposedInit {
    private val mainPrefs = XSharedPreferences(BuildConfig.APPLICATION_ID, "chen_main_settings")

    override fun onHook() = YukiHookAPI.encase {
        mainPrefs.reload()
        loadSystem(SystemHooker)
        loadApp("com.android.systemui", SystemUIMainHooker)
        loadApp("com.miui.powerkeeper", PowerKeeperHook)
        loadApp("com.miui.home", MiuiHomeHook)
        loadApp("com.xiaomi.misettings", MiuiSettingsHook)
        loadApp("com.miui.miwallpaper", MiWallpaperHook)

    }

    override fun onInit() = configs {
//        isDebug = BuildConfig.DEBUG
        isDebug = false
    }
}