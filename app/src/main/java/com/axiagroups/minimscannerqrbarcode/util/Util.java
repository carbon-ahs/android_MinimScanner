package com.axiagroups.minimscannerqrbarcode.util;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;


public class Util {
    private static void CheckForCoarseLocationPermission()
    {
        if (android.os.Build.VERSION.SDK_INT >= 23)
        {
            // ANDROID 6.0 AND UP!
            boolean accessCameraAllowed = false;
            try {
                // Invoke checkSelfPermission method from Android 6 (API 23 and UP)
                java.lang.reflect.Method methodCheckPermission = Activity.class.getMethod("checkSelfPermission", java.lang.String.class);
//                Object resultObj = methodCheckPermission.invoke(this, Manifest.permission.CAMERA);
//                int result = Integer.parseInt(resultObj.toString());
//                if (result == PackageManager.PERMISSION_GRANTED) {
//                    accessCameraAllowed = true;
//                }
            } catch (Exception ex) {
            }

            if (accessCameraAllowed) {
                return;
            }
            try
            {
                // We have to invoke the method "void requestPermissions (Activity activity, String[] permissions, int requestCode) "
                // from android 6
                java.lang.reflect.Method methodRequestPermission = Activity.class.getMethod("requestPermissions", java.lang.String[].class, int.class);
//                methodRequestPermission.invoke(this, new String[]
//                        {
//                                Manifest.permission.CAMERA
//                        }, 0x12345);
            }
            catch (Exception ex)
            {
            }
        }
    }


}
