package com.xiaobaozi.dataSystem.commons.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.xiaobaozi.dataSystem.commons.search.SearchCriteria;

/**
 * 不涉及具体实现的基于范型的DAO接口。也会提供一些常用的不限制使用范型的接口。 
 * @param <T>
 */
public interface GenericDao<T> {

	/**
	 * 统计符合条件记录数
	 * @param map
	 * @return
	 */
	public Integer count(Map<String, Object> map);
	
	/**
	 * 查询记录数
	 */
	public Integer selectCount(SearchCriteria<T> sc);
	
	/**
	 * 分页查询
	 */
	public SearchCriteria<T> selectPage(SearchCriteria<T> sc);
		
	/**
	 * @param map
	 * @return
	 */
	public Integer selectSeq();
	
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
	 * @throws Exception
	 */
	public int insert(T o) throws Exception;
	
	/**
	 * 新增，返回主键
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public T addAndGetkey(T o) throws Exception;
	
	/**
	 * 更新对象
	 * @param o
	 * @return
	 * @throws Exception
	 */	
	public int update(T o) throws Exception;
	
	/**
	 * 更新对象的部分属性
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public int update(Map<String, Object> map) throws Exception;
	/**
	 * Delete the given persistent instance.
	 * @param entity the persistent instance to delete
	 * @throws Exception in case of db errors
	 */
	public int delete(T o) throws Exception;
	
	/**
	 * 批量插入
	 * 没有使用spring的事务，改动手动控制，如果和原spring事务一起使用，将无法回滚，必须注意，最好单独使用；
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public int batchInsert(List<T> list) throws Exception;
	
	/**
	 * 批量更新
	 * 没有使用spring的事务，改动手动控制，如果和原spring事务一起使用，将无法回滚，必须注意，最好单独使用；
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public int batchUpdate(List<T> list) throws Exception;
	
	/**
	 * 批量删除
	 * 没有使用spring的事务，改动手动控制，如果和原spring事务一起使用，将无法回滚，必须注意，最好单独使用；
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public int batchDelete(List<T> list) throws Exception;
	
	/**
	 * 更新对象的部分属性
	 */
	public int updateByTicket(Map<String, Object> map) throws Exception ;
	
	public int updateByMap(String name,Map<String, Object> map) throws Exception;

}
