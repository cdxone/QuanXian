package com.cdx.example.quanxian;

import com.cdx.example.quanxian.activity.CheckApplyActivity;
import com.cdx.example.quanxian.activity.PermissionKnowledgeActivity;

import java.util.ArrayList;

import apis.amapv2.com.listviewlibrary.activity.BaseListActivty;
import apis.amapv2.com.listviewlibrary.bean.ItemObject;

public class MainActivity extends BaseListActivty {

    @Override
    protected void initData() {
        ArrayList<ItemObject> data = new ArrayList<>();
        data.add(new ItemObject("权限相关知识",PermissionKnowledgeActivity.class));
        data.add(new ItemObject("检查权限-申请权限",CheckApplyActivity.class));

        mMyListView.setData(data);
    }
}
