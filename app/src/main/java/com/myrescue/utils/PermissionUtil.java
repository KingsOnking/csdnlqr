package com.myrescue.utils;

import android.content.pm.PackageManager;

/**
 * Created by Men on 2017/5/23.
 */
    public abstract class PermissionUtil {
        /**
         * Check that all given permissions have been granted by verifying that each entry in the
         * given array is of the value {@link PackageManager#PERMISSION_GRANTED}.
         *
         * @see
         */
        public static boolean verifyPermissions(int[] grantResults) {
            // At least one result must be checked.
            if (grantResults.length < 1) {
                return false;
            }
            // Verify that each required permission has been granted, otherwise return false.
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
            return true;
        }
}