package com.xiaobaozi.dataSystem.indemnity.handle.impl;

import com.xiaobaozi.dataSystem.commons.handle.impl.GenericHandleImpl;
import com.xiaobaozi.dataSystem.indemnity.dao.IndemnitySubDao;
import com.xiaobaozi.dataSystem.indemnity.handle.IndemnitySubHandle;
import com.xiaobaozi.dataSystem.indemnity.pojo.IndemnitySub;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("indemnitySubHandle")
public class IndemnitySubHandleImpl extends GenericHandleImpl<IndemnitySub> implements IndemnitySubHandle {

    @Resource(name = "indemnitySubDao")
    private IndemnitySubDao indemnitySubDao;

    protected void initHandle() {
        dao = indemnitySubDao;
    }


    public List<IndemnitySub> selectByIndemnityId(String indemnityId) {
        return indemnitySubDao.selectByIndemnityId(indemnityId);
    }

    public int deleteById(String id) {
        return indemnitySubDao.deleteById(id);
    }

    public int deleteByIndemnityId(String indemnityId) {
        return indemnitySubDao.deleteByIndemnityId(indemnityId);
    }
}
