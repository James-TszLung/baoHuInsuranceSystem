package com.xiaobaozi.dataSystem.product.handle;

import com.xiaobaozi.dataSystem.commons.handle.GenericHandle;
import com.xiaobaozi.dataSystem.product.relation.ClauseFile;

public interface ClauseFileHandle extends GenericHandle<ClauseFile> {
    public int deleByProductId(String id);
}
