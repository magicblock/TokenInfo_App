package com.tokeninfo.util;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * Rights management tools
 */
public class PermissionUtil {

    private static PermissionUtil instance;
    public static final int PERMISSIONS_REQUEST_CODE = 10;
    private final String PACKAGE_URL_SCHEME = "package:";
    private final String NOT_ALLOW = "NOT_ALLOW";
    public static final String PERMISSION_CAMERA = Manifest.permission.CAMERA;
    public static final String PERMISSION_RECORD_AUDIO = Manifest.permission.RECORD_AUDIO;
    public static final String PERMISSION_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE;
    public static final String PERMISSION_STORAGE_WRITE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    public static final String PERMISSION_CONTACTS = Manifest.permission.READ_CONTACTS;
    public static final String PERMISSION_SMS = Manifest.permission.SEND_SMS;
    public static final String PERMISSION_PHONE = Manifest.permission.READ_PHONE_STATE;
    public static final String WRITE_CALENDAR = Manifest.permission.WRITE_CALENDAR;
    public static final String READ_CALENDAR = Manifest.permission.READ_CALENDAR;


    public static PermissionUtil getInstance() {
        if (instance == null) {
            instance = new PermissionUtil();
        }
        return instance;
    }

    /**
     * Request permission
     *
     * @param activity
     * @param permissions
     * @param resultCallBack
     */
    public void requestPermission(Activity activity, String[] permissions, ResultCallBack resultCallBack) {
        // check android version
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            checkPermissionLow(activity, permissions, resultCallBack);
            return;
        }

        ArrayList<String> permissionNotAllowed = new ArrayList<>();
        for (String name : permissions) {
            if (ActivityCompat.checkSelfPermission(activity, name) != PackageManager.PERMISSION_GRANTED) {
                permissionNotAllowed.add(name);
            }
        }

        if (permissionNotAllowed.size() > 0) {
            String[] string = new String[permissionNotAllowed.size()];
            permissionNotAllowed.toArray(string);
            ActivityCompat.requestPermissions(activity, string, PERMISSIONS_REQUEST_CODE);
        } else {
            resultCallBack.granted(permissions);
        }
    }

    /**
     * To deal with the callback returns the result
     *
     * @param target
     * @param requestCode
     * @param permissions
     * @param grantResults
     * @param resultCallBack
     */
    public void onRequestPermissionsResult(Activity target, int requestCode, String[] permissions, int[] grantResults, ResultCallBack resultCallBack) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_CODE:
                if (verifyPermissions(grantResults)) {
                    resultCallBack.granted(permissions);
                } else {
                    resultCallBack.deny(permissions);
                    if (permissions != null) {
                        for (String name : permissions) {
                            if (ContextCompat.checkSelfPermission(target, name) != PackageManager.PERMISSION_GRANTED) {
                                showDialog(target, name);
                                return;
                            }
                        }
                    }
                    showDialog(target, NOT_ALLOW);
                }
                break;
            default:
                break;
        }
    }

    /**
     * whether permissions are allowed
     *
     * @param grantResults
     * @return
     */
    private boolean verifyPermissions(int... grantResults) {
        if (grantResults == null || grantResults.length == 0) {
            return false;
        }
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * Tick is not a reminder to remind the listener
     *
     * @param activity
     * @param permissions
     * @return
     */
    public String shouldShowRequestPermissionRationale(Activity activity, String... permissions) {
        for (String permission : permissions) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                return permission;
            }
        }
        return "";
    }

    /**
     * 兼容不同手机摄像头权限
     *
     * @param activity
     * @param resultCallBack
     */
    public void checkCameraPermission(Activity activity, ResultCallBack resultCallBack) {
        String[] PermissionArray = new String[]{
                PermissionUtil.PERMISSION_CAMERA,
                PermissionUtil.PERMISSION_RECORD_AUDIO};
        checkPermissionLow(activity, PermissionArray, resultCallBack);
    }

    /**
     * 6.0 before check special privileges (mainly for the third party system custom permissions manager)
     *
     * @param activity
     * @param permissions
     * @param resultCallBack
     */
    private void checkPermissionLow(Activity activity, String[] permissions, ResultCallBack resultCallBack) {
        boolean isAllow = true;
        for (String name : permissions) {
            switch (name) {
                case Manifest.permission.CAMERA:
                    isAllow = checkCamera(activity);
                    break;
                case Manifest.permission.RECORD_AUDIO:
                    break;
                default:
                    break;
            }
            //If one item is refused to directly not through
            if (!isAllow) {
                resultCallBack.deny(permissions);
                return;
            }
        }
        if (isAllow) {
            resultCallBack.granted(permissions);
        }
    }

    /**
     * Version 6.0 to determine whether a camera available before (now mainly rely on the try... catch... catch exceptions)
     *
     * @param activity
     * @return
     */
    private boolean checkCamera(Activity activity) {
        boolean canUse = true;
        Camera mCamera = null;
        try {
            mCamera = Camera.open();
            Camera.Parameters mParameters = mCamera.getParameters();
            mCamera.setParameters(mParameters);
        } catch (Exception e) {
            canUse = false;
        }
        if (mCamera != null) {
            mCamera.release();
        }
        if (!canUse) {
            showDialog(activity, Manifest.permission.CAMERA);
        }
        return canUse;
    }

    /**
     * No permission prompt
     *
     * @param activity
     * @param permissom
     */
    private void showDialog(final Activity activity, String permissom) {
        String message = "";
        switch (permissom) {
            case PERMISSION_CAMERA:
                break;
            case PERMISSION_RECORD_AUDIO:
                break;
            case PERMISSION_STORAGE:
                break;
            case PERMISSION_CONTACTS:
                break;
            case PERMISSION_SMS:
                break;
            case NOT_ALLOW:
                break;
            case PERMISSION_PHONE:
                break;
        }

        Toast.makeText(activity,"sdsds",Toast.LENGTH_SHORT).show();
    }

    /**
     * Authorization check result callback
     */
    public interface ResultCallBack {
        void granted(String[] permissions);

        void deny(String[] permissions);
    }
}
