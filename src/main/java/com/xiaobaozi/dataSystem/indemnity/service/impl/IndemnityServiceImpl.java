package com.xiaobaozi.dataSystem.indemnity.service.impl;


import com.xiaobaozi.dataSystem.commons.service.impl.GenericServiceImpl;
import com.xiaobaozi.dataSystem.commons.utils.UUIDUtil;
import com.xiaobaozi.dataSystem.indemnity.handle.IndemnityHandle;
import com.xiaobaozi.dataSystem.indemnity.handle.IndemnitySubHandle;
import com.xiaobaozi.dataSystem.indemnity.pojo.Indemnity;
import com.xiaobaozi.dataSystem.indemnity.pojo.IndemnitySub;
import com.xiaobaozi.dataSystem.indemnity.search.IndemnitySearch;
import com.xiaobaozi.dataSystem.indemnity.service.IndemnityService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Component("indemnityService")
public class IndemnityServiceImpl extends GenericServiceImpl<Indemnity> implements IndemnityService {

    @Resource(name = "indemnityHandle")
    private IndemnityHandle indemnityHandle;
    @Resource(name = "indemnitySubHandle")
    private IndemnitySubHandle indemnitySubHandle;

    protected void initService() {
        handle = indemnityHandle;
    }

    public int getIndemnityCount(IndemnitySearch sc) {
        return indemnityHandle.getIndemnityCount(sc);
    }

    public List<Indemnity> getIndemnityByPage(IndemnitySearch sc) {
        return indemnityHandle.getIndemnityByPage(sc);
    }

    public List<Indemnity> getIndemnityByDictionaryId(String dictionaryContentId) {
        return indemnityHandle.getIndemnityByDictionaryId(dictionaryContentId);
    }

    public int insertIndemnity(Indemnity indemnity) throws Exception{
        indemnity.setId(UUIDUtil.getUUID());
        List<IndemnitySub> indemnitySubLs = indemnity.getIndemnitySubLs();
        if(indemnitySubLs!=null && indemnitySubLs.size()>0){
            for(IndemnitySub sub : indemnitySubLs){
                if(StringUtils.isNotEmpty(sub.getName())){
                    sub.setId(UUIDUtil.getUUID());
                    sub.setIndemnityId(indemnity.getId());
                    sub.setCreateById(indemnity.getUpdateById());
                    sub.setCreateName(indemnity.getUpdateName());
                    sub.setUpdateById(indemnity.getUpdateById());
                    sub.setUpdateName(indemnity.getUpdateName());
                    indemnitySubHandle.insert(sub);
                }
            }
        }
        return indemnityHandle.insert(indemnity);
    }

    public boolean updateIndemnity(Indemnity indemnity) throws Exception {
        List<IndemnitySub> indemnitySubLs = indemnity.getIndemnitySubLs();
        List<String> subIds = new ArrayList<String>();
        if(indemnitySubLs!=null && indemnitySubLs.size()>0){
            for(IndemnitySub sub : indemnitySubLs){
                if(StringUtils.isNotEmpty(sub.getName())){
                    sub.setUpdateById(indemnity.getUpdateById());
                    sub.setUpdateName(indemnity.getUpdateName());
                    sub.setIndemnityId(indemnity.getId());
                    if(StringUtils.isNotBlank(sub.getId())){
                        indemnitySubHandle.update(sub);
                    }else{
                        sub.setId(UUIDUtil.getUUID());
                        sub.setCreateById(indemnity.getUpdateById());
                        sub.setCreateName(indemnity.getUpdateName());
                        indemnitySubHandle.insert(sub);
                    }
                    subIds.add(sub.getId());
                }
            }
        }
        if(subIds.size()>0){
            List<IndemnitySub> indemnitySubAll = indemnitySubHandle.selectByIndemnityId(indemnity.getId());
            for(IndemnitySub sub : indemnitySubAll){
                if(!subIds.contains(sub.getId())){
                    indemnitySubHandle.deleteById(sub.getId());
                }
            }
        }
        return indemnityHandle.update(indemnity);
    }

    public Indemnity selectBy(Map<String, Object> map) {
        return indemnityHandle.selectBy(map);
    }

    public boolean deleteIndemnityById(Indemnity indemnity) throws Exception {
        List<IndemnitySub> subList = indemnity.getIndemnitySubLs();
        for(IndemnitySub sub : subList){
            indemnitySubHandle.deleteById(sub.getId());
        }
        return indemnityHandle.delete(indemnity);
    }

    public int countRelation(String dictionaryContentId) {
        return indemnityHandle.countRelation(dictionaryContentId);
    }
}
