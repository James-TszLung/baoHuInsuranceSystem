package com.xiaobaozi.dataSystem.commons.handle.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

import com.xiaobaozi.dataSystem.commons.Constants;
import com.xiaobaozi.dataSystem.commons.dao.GenericDao;
import com.xiaobaozi.dataSystem.commons.exception.DaoException;
import com.xiaobaozi.dataSystem.commons.handle.GenericHandle;
import com.xiaobaozi.dataSystem.commons.search.SearchCriteria;
import com.xiaobaozi.dataSystem.commons.utils.GenericsUtils;

/**
 * 基础handler实现类
 * @author bing
 *
 * @param <T>
 */
public abstract class GenericHandleImpl<T> implements GenericHandle<T>, InitializingBean {

	/**
	 * Service和DAO之间的接口比较特殊，一般DAO只是对应一个Handler，不需要被多个Handler共享不基于接口编程。如果需要使用基类提供的以外的数据库访问方法，实现自己的dao并注入。
	 */
	protected GenericDao<T> dao = null;

	public Class<T> entityClass;
	
	protected final transient Logger logger = Logger.getLogger(getClass());
	
	public GenericHandleImpl() {
		entityClass = GenericsUtils.getSuperClassGenricType(getClass());
	}
	
	/**
	 * 子类需要重载这个方法来实现初始化，包括设置dao
	 * 
	 */
	protected abstract void initHandle();
	
	@Override
	public void afterPropertiesSet() throws Exception {
		initHandle();
		if (dao == null) {
			logger.info("Generic dao is not set. Please set it in the initService method if database access is desired!");
		}
		
	}

	/**
	 * 统计符合条件记录数
	 * @param map
	 * @return
	 */
	public Integer count(Map<String, Object> map) {
		return dao.count(map);
	}
	/**
	 * 获取当前序列号
	 */
	public Integer selectSeq(){
		return dao.selectSeq();
	}
	
	/**
	 * 分页查询
	 */
	public SearchCriteria<T> selectPage(SearchCriteria<T> sc) {
		return dao.selectPage(sc);
	}
	/**
	 * 查询记录数
	 */
	@Override
	public Integer selectCount(SearchCriteria<T> sc) {
		return dao.selectCount(sc);
	}
	/**
	 * 按条件查询记录
	 * @param map
	 * @return
	 */
	public List<T> listByMap(Map<String, Object> map) {
		return dao.listByMap(map);
	}

	/**
	 * 获取所有记录
	 * @return
	 */
	@Override
	public List<T> listAll() {
		return dao.listAll();
	}

	/**
	 * 根据ID获取对象。
	 * @param id
	 * @return
	 */
	public T findById(Serializable id) {
		return dao.findById(id);
	}

	/**
	 *  根据IDs获取对象集合。
	 * @param ids
	 * @return
	 */
	public List<T> findByIds(List<? extends Serializable> ids) {
		return dao.findByIds(ids);
	}

	/**
	 * 更新对象
	 * @param o
	 * @return
	 * @throws DaoException 
	 * @throws Exception
	 */	
	public boolean update(T o) throws DaoException{
		try {
			if(dao.update(o)>=1){
				return true;
			}
		} catch (Exception e) {
			logger.error("update data "+o.toString() + " error:" +e.getMessage());
//			e.printStackTrace();
			throw new DaoException(e.getMessage(), Constants.EXCEPTION_DAOEXCEPTION_UPDATE_CODE, null);
		}
		return false;
	}
	
	/**
	 * 更新对象的部分属性
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public boolean update(Map<String, Object> map) throws DaoException{
		try {
			if(dao.update(map)>=1){
				return true;
			}
		} catch (Exception e) {
//			e.printStackTrace();
			logger.error("update data "+map.toString() + " error:" +e.getMessage());
			throw new DaoException(e.getMessage(), Constants.EXCEPTION_DAOEXCEPTION_UPDATE_CODE, null);
		}
		return false;
	}

	/**
	 * 新增对象
	 * @param o
	 * @return
	 * @throws DaoException 
	 * @throws Exception
	 */
	public int insert(T o) throws DaoException{
		try {
			return dao.insert(o);
		} catch (Exception e) {
			logger.error("insert data "+o.toString() + " error:" +e.getMessage());
			throw new DaoException(e.getMessage(), Constants.EXCEPTION_DAOEXCEPTION_INSERT_CODE, null);
		}
	}
	
	/**
	 * 新增，返回主键
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public T addAndGetkey(T o) throws DaoException{
		try {
			dao.insert(o);
			return o;
		} catch (Exception e) {
			logger.error("insert data "+o.toString() + " error:" +e.getMessage());
			throw new DaoException(e.getMessage(), Constants.EXCEPTION_DAOEXCEPTION_INSERT_CODE, null);
		}
	}
	
	/**
	 * Delete the given persistent instance.
	 * @param entity the persistent instance to delete
	 * @throws DaoException 
	 */
	public boolean delete(T o) throws DaoException{
		try {
			if(dao.delete(o)==1){
				return true;
			}
		} catch (Exception e) {
			logger.error("delete data "+o.toString() + " error:" +e.getMessage());
//			e.printStackTrace();
			throw new DaoException(e.getMessage(), Constants.EXCEPTION_DAOEXCEPTION_DELETE_CODE, null);
		}
		return false;
	}
	
	@Override
	public boolean batchInsert(List<T> list) throws DaoException {
		try {
			if(dao.batchInsert(list) == list.size()){
				return true;
			}
		} catch (Exception e) {
			logger.error("batchInsert error:" + e.getMessage());
			throw new DaoException(e.getMessage(), Constants.EXCEPTION_DAOEXCEPTION_INSERT_CODE, null);
		}
		return false;
	}
	
	@Override
	public boolean batchUpdate(List<T> list) throws DaoException {
		boolean b=true;
		try {
			dao.batchUpdate(list);
		} catch (Exception e) {
			logger.error("batchUpdate error:" + e.getMessage());
			b=false;
			throw new DaoException(e.getMessage(), Constants.EXCEPTION_DAOEXCEPTION_UPDATE_CODE, null);
		}
		return b;
	}
	
	@Override
	public boolean batchDelete(List<T> list) throws DaoException {
		try {
			if(dao.batchDelete(list) == list.size()){
				return true;
			}
		} catch (Exception e) {
			logger.error("batchDelete error:" + e.getMessage());
			throw new DaoException(e.getMessage(), Constants.EXCEPTION_DAOEXCEPTION_DELETE_CODE, null);
		}
		return false;
	}
}
