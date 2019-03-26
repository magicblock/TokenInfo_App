package com.tokeninfo.ui;

import android.content.Intent;
import android.os.Bundle;

import com.tokeninfo.R;
import com.tokeninfo.base.BaseActivity;
import com.tokeninfo.util.PermissionUtil;

import androidx.annotation.Nullable;

public class LaunchActivity extends BaseActivity {

    private LaunchActivity activity;

    /**
     * 需要用户申请的动态权限
     */
    private String[] Permissions = new String[]{
            PermissionUtil.PERMISSION_STORAGE,
            PermissionUtil.PERMISSION_STORAGE_WRITE,
            PermissionUtil.PERMISSION_CAMERA,
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        activity = this;
        PermissionUtil.getInstance().requestPermission(activity, Permissions, permissionCallBack);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtil.getInstance().onRequestPermissionsResult(activity, requestCode, permissions, grantResults, permissionCallBack);
    }

    protected PermissionUtil.ResultCallBack permissionCallBack = new PermissionUtil.ResultCallBack() {
        @Override
        public void granted(String[] permissions) {
            if (permissions != null || permissions.length == Permissions.length) {
                Intent intent = new Intent();
                intent.setClass(activity, MainActivity.class);
                activity.startActivity(intent);
            }
        }

        @Override
        public void deny(String[] permissions) {
            if (permissions != null || permissions.length > 0) {
                //activity.finish();
            }
        }
    };
}
