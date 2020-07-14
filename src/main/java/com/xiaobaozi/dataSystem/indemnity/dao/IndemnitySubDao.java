package com.xiaobaozi.dataSystem.indemnity.dao;

import com.xiaobaozi.dataSystem.commons.dao.GenericDao;
import com.xiaobaozi.dataSystem.indemnity.pojo.IndemnitySub;

import java.util.List;

public interface IndemnitySubDao extends GenericDao<IndemnitySub> {

    public List<IndemnitySub> selectByIndemnityId(String indemnityId);

    public int deleteById(String id);

    public int deleteByIndemnityId(String indemnityId);


}
