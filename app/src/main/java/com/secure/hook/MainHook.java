package com.device.secure.hook;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class MainHook implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) {
        // Hook KeyguardManager
        try {
            XposedHelpers.findAndHookMethod(
                "android.app.KeyguardManager",
                lpparam.classLoader,
                "isDeviceSecure",
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        param.setResult(true);
                    }
                });
        } catch (Throwable t) {}
        
        // Hook DevicePolicyManager
        try {
            XposedHelpers.findAndHookMethod(
                "android.app.admin.DevicePolicyManager",
                lpparam.classLoader,
                "isDeviceSecure",
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        param.setResult(true);
                    }
                });
        } catch (Throwable t) {}
    }
}
