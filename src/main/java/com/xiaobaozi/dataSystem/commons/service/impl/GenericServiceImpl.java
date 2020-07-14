package com.xiaobaozi.dataSystem.commons.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.transaction.annotation.Transactional;

import com.xiaobaozi.dataSystem.commons.exception.DaoException;
import com.xiaobaozi.dataSystem.commons.handle.GenericHandle;
import com.xiaobaozi.dataSystem.commons.search.SearchCriteria;
import com.xiaobaozi.dataSystem.commons.service.GenericService;
import com.xiaobaozi.dataSystem.commons.utils.GenericsUtils;

public abstract class GenericServiceImpl<T> implements GenericService<T>, InitializingBean {

	/**
	 * Service和Handler之间的接口比较特殊，一般Handler只是对应一个Service，不需要被多个Service共享不基于接口编程。
	 */
	protected GenericHandle<T> handle = null;

	public Class<T> entityClass;

	protected final transient Logger logger = Logger.getLogger(getClass());

	public GenericServiceImpl() {
		entityClass = GenericsUtils.getSuperClassGenricType(getClass());
	}

	/**
	 * 子类需要重载这个方法来实现初始化，包括设置dao
	 * 
	 */
	protected abstract void initService();

	@Override
	public void afterPropertiesSet() throws Exception {
		initService();
		if (handle == null) {
			logger.info("Generic handle is not set. Please set it in the initService method if handle access is desired!");
		}

	}

	/**
	 * 统计符合条件记录数
	 * 
	 * @param map
	 * @return
	 */
	@Override
	public Integer count(Map<String, Object> map) {
		return handle.count(map);
	}

	/**
	 * 获取当前序列号
	 * 
	 * @param map
	 * @return
	 */
	public Integer selectSeq() {
		return handle.selectSeq();
	}

	/**
	 * 分页查询
	 */
	public SearchCriteria<T> selectPage(SearchCriteria<T> sc) {
		return handle.selectPage(sc);
	}

	/**
	 * 按条件查询记录
	 * 
	 * @param map
	 * @return
	 */
	@Override
	public List<T> listByMap(Map<String, Object> map) {
		return handle.listByMap(map);
	}

	/**
	 * 获取所有记录
	 * 
	 * @return
	 */
	@Override
	public List<T> listAll() {
		return handle.listAll();
	}

	/**
	 * 根据ID获取对象。
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public T findById(Serializable id) {
		return handle.findById(id);
	}

	/**
	 * 根据IDs获取对象集合。
	 * 
	 * @param ids
	 * @return
	 */
	@Override
	public List<T> findByIds(List<? extends Serializable> ids) {
		return handle.findByIds(ids);
	}

	/**
	 * 更新对象
	 * 
	 * @param o
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean update(T o) throws DaoException {
		return handle.update(o);
	}

	/**
	 * 更新对象的部分属性
	 * 
	 * @param o
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean updateByMap(Map<String, Object> map) throws DaoException {
		return handle.update(map);
	}

	/**
	 * 新增对象
	 * 
	 * @param o
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public int insert(T o) throws DaoException {
		return handle.insert(o);
	}

	@Transactional
	public T addAndGetkey(T o) throws DaoException {
		handle.insert(o);
		return o;
	}

	/**
	 * Delete the given persistent instance.
	 * 
	 * @param entity
	 *            the persistent instance to delete
	 */
	@Override
	public boolean delete(T o) throws DaoException {
		return handle.delete(o);
	}

	@Override
	public boolean batchInsert(List<T> list) throws DaoException {
		return handle.batchInsert(list);
	}

	@Override
	public boolean batchUpdate(List<T> list) throws DaoException {
		return handle.batchUpdate(list);
	}

	@Override
	public boolean batchDelete(List<T> list) throws DaoException {
		return handle.batchDelete(list);
	}

	public Integer selectCount(SearchCriteria<T> sc) throws DaoException {
		return handle.selectCount(sc);
	}
}
