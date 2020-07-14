package com.xiaobaozi.dataSystem.commons.dao.mybatis;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

public abstract class Mybatis3DaoSuport extends Mybatis3DaoBase{
	
/*	protected String mapperPath;		//映射路径
	@PostConstruct
	public void initMapperPath(){
		this.mapperPath = "";
	}
	//取得映射全路径
	protected String getFullMapperPath(String s){
		StringBuffer sbf = new StringBuffer(this.mapperPath);
		sbf.append(".");
		sbf.append(s);
		return sbf.toString();
	}
	*/
	/*public Object selectOne(String s){
		return getSqlSession().selectOne(getFullMapperPath(s));
	}

    public Object selectOne(String s, Object obj){
    	return getSqlSession().selectOne(getFullMapperPath(s), obj);
    }

    public List selectList(String s){
    	return getSqlSession().selectList(getFullMapperPath(s));
    }

    public List selectList(String s, Object obj){
    	return getSqlSession().selectList(getFullMapperPath(s), obj);
    }

    public List selectList(String s, Object obj, RowBounds rowbounds){
    	return getSqlSession().selectList(getFullMapperPath(s), obj, rowbounds);
    }
    
    public Map selectMap(String s, String s1){
    	return getSqlSession().selectMap(getFullMapperPath(s), s1);
    }

    public Map selectMap(String s, Object obj, String s1){
    	return getSqlSession().selectMap(getFullMapperPath(s), obj, s1);
    }

    public Map selectMap(String s, Object obj, String s1, RowBounds rowbounds){
    	return getSqlSession().selectMap(getFullMapperPath(s), obj, s1, rowbounds);
    }

    public void select(String s, Object obj, ResultHandler resulthandler){
    	getSqlSession().select(getFullMapperPath(s), obj, resulthandler);
    }

    public void select(String s, ResultHandler resulthandler){
    	getSqlSession().select(getFullMapperPath(s), resulthandler);
    }

    public void select(String s, Object obj, RowBounds rowbounds, ResultHandler resulthandler){
    	getSqlSession().select(getFullMapperPath(s), obj, rowbounds, resulthandler);
    }*/

}
