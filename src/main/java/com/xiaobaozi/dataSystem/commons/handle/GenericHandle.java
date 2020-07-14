package com.xiaobaozi.dataSystem.commons.handle;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.xiaobaozi.dataSystem.commons.exception.DaoException;
import com.xiaobaozi.dataSystem.commons.search.SearchCriteria;

public interface GenericHandle<T> {

	/**
	 * 统计符合条件记录数
	 * @param map
	 * @return
	 */
	public Integer count(Map<String, Object> map);
	/**
	 * 获取当前序列号
	 * @param map
	 * @return
	 */
	public Integer selectSeq();
	
	/**
	 * 分页查询
	 */
	public SearchCriteria<T> selectPage(SearchCriteria<T> sc);
	/**
	 * 查询记录数
	 */
	public Integer selectCount(SearchCriteria<T> sc);
	/**
	 * 按条件查询记录
	 * @param map
	 * @return
	 */
	public List<T> listByMap(Map<String, Object> map);
	/**
	 * 获取所有记录
	 * @return
	 */
	public List<T> listAll();
	/**
	 * 根据ID获取对象。
	 * @param id
	 * @return
	 */
	public T findById(Serializable id);
	/**
	 *  根据IDs获取对象集合。
	 * @param ids
	 * @return
	 */
	public List<T> findByIds(List<? extends Serializable> ids);
	/**
	 * 新增对象
	 * @param o
	 * @return
	 * @throws DaoException 
	 * @throws Exception
	 */
	public int insert(T o) throws DaoException;
	
	public T addAndGetkey(T o) throws DaoException;
	
	/**
	 * 更新对象
	 * @param o
	 * @return
	 * @throws DaoException 
	 * @throws Exception
	 */	
	public boolean update(T o) throws DaoException;
	/**
	 * 更新对象的部分属性
	 * @param o
	 * @return
	 * @throws DaoException 
	 * @throws Exception
	 */
	public boolean update(Map<String, Object> map) throws DaoException;
	/**
	 * Delete the given persistent instance.
	 * @param entity the persistent instance to delete
	 * @throws DaoException 
	 */
	public boolean delete(T o) throws DaoException;
	
	/**
	 * 批量插入
	 * @param list
	 * @return
	 * @throws DaoException
	 */
	public boolean batchInsert(List<T> list) throws DaoException;
	
	/**
	 * 批量更新
	 * @param list
	 * @return
	 * @throws DaoException
	 */
	public boolean batchUpdate(List<T> list) throws DaoException;
	
	/**
	 * 批量删除
	 * @param list
	 * @return
	 * @throws DaoException
	 */
	public boolean batchDelete(List<T> list) throws DaoException;
}
