package com.xiaobaozi.dataSystem.indemnity.handle;

import com.xiaobaozi.dataSystem.commons.handle.GenericHandle;
import com.xiaobaozi.dataSystem.indemnity.pojo.IndemnitySub;

import java.util.List;

public interface IndemnitySubHandle extends GenericHandle<IndemnitySub> {

    public List<IndemnitySub> selectByIndemnityId(String indemnityId);

    public int deleteById(String id);

    public int deleteByIndemnityId(String indemnityId);

}
