package com.xiaobaozi.dataSystem.commons.dao.mybatis.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;

import com.xiaobaozi.dataSystem.commons.dao.GenericDao;
import com.xiaobaozi.dataSystem.commons.dao.mybatis.Mybatis3DaoBase;
import com.xiaobaozi.dataSystem.commons.search.SearchCriteria;
import com.xiaobaozi.dataSystem.commons.utils.GenericsUtils;

public abstract class GenericDaoImpl<T> extends Mybatis3DaoBase implements GenericDao<T>, InitializingBean  {

	protected final Logger logger = Logger.getLogger(getClass());

	/**
	 * 按主键查询Mapper Statement id
	 */
	public static final String POSTFIX_SELECTBYID = ".selectById";
	
	/**
	 * 查询主键Mapper Statement id
	 */
	public static final String POSTFIX_SELECTBYSEQ=".selectSeq";
	
	/**
	 * 分页查询
	 */
	public static final String POSTFIX_SELECTPAGE = ".selectPage";
	
	public static final String POSTFIX_SELECTCOUNT = ".selectCount";
	
	/**
	 * 按主键集合查询Mapper Statement id
	 */
	public static final String POSTFIX_SELECTBYIDS = ".selectByIds";
	/**
	 * 按Map集合条件统计总数 Mapper Statement id
	 */	
	public static final String POSTFIX_COUNT = ".count";
	
	/**
	 * 按条件查询记录 Mapper Statement id
	 */
	public static final String POSTFIX_LISTBYMAP = ".listByMap";
	/**
	 * 查询所有记录 Mapper Statement id
	 */
	public static final String POSTFIX_LISTALL = ".listAll";
	/**
	 * 新增记录Mapper Statement id
	 */
	public static final String POSTFIX_INSERT = ".insert";
	/**
	 * 新增记录返回Id Mapper Statement id
	 */
	public static final String POSTFIX_INSERTAFTERID = ".insertAfterId";
	/**
	 * 删除记录Mapper Statement id
	 */
	public static final String POSTFIX_DELETE = ".delete";
	/**
	 * 更新记录Mapper Statement id
	 */
	public static final String POSTFIX_UPDATE = ".update";
	public static final String POSTFIX_BATCHINSERT = ".batchinsert";
	public static final String POSTFIX_BATCHUPDATE = ".batchupdate";
	public static final String POSTFIX_BATCHDELETE = ".batchdelete";
	/**
	 * 按Map更新记录Mapper Statement id
	 */
	public static final String POSTFIX_UPDATEBYMAP = ".updateByMap";
	
	public static final String POSTFIX_UPDATEBYTicket = ".updateByTicket";

	protected Class<T> clazz;
	protected String clazzName;
	protected SqlSessionTemplate  sqlSessionTemplate = null;
	protected JdbcTemplate  jdbcTemplate = null;
	
	
	
	/**
	 * 子类需要重载这个方法来实现初始化，包括设置指定数据源 SqlSessionTemplate, JdbcTemplate
	 * 
	 */
	protected abstract void initDao();
	
	@Override
	public void afterPropertiesSet() throws Exception {
		// 通过范型反射，取得在子类中定义的class.
		clazz = GenericsUtils.getSuperClassGenricType(getClass());
		clazzName = clazz.getSimpleName();
		initDao();		
	}



	public GenericDaoImpl() {
		
	}
	
	/**
	 * 获取 Mapper Statement id 空间命名
	 * @param segment
	 * @return
	 */
	public String getFullMapperPath(String segment){
		return clazz.getName() + "."+segment;
	}
	
	/**
	 * 查询记录数
	 */
	@Override
	public Integer count(Map<String, Object> map) {
		return (Integer) sqlSessionTemplate.selectOne(clazz.getName() + POSTFIX_COUNT, map);
	}
	
	/**
	 * 查询记录数
	 */
	@Override
	public Integer selectCount(SearchCriteria<T> sc) {
		return (Integer) sqlSessionTemplate.selectOne(clazz.getName() + POSTFIX_SELECTCOUNT, sc);
	}
	
	/**
	 * 分页查询
	 */
	@Override
	public SearchCriteria<T> selectPage(SearchCriteria<T> sc) {
		List<T> list = sqlSessionTemplate.selectList(clazz.getName() + POSTFIX_SELECTPAGE, sc);
		sc.setResultList(list);
		if (list != null && !list.isEmpty()) {
			int count = this.selectCount(sc);
			sc.setTotalCount(count);
		}
		return sc;
	}

	/**
	 * 按条件查询记录
	 */
	@Override
	public List<T> listByMap(Map<String, Object> map) {
		return (List<T>) sqlSessionTemplate.selectList(clazz.getName() + POSTFIX_LISTBYMAP, map);
	}
	
	/**
	 * 获取所有记录
	 */
	@Override
	public List<T> listAll() {
		return (List<T>) sqlSessionTemplate.selectList(clazz.getName() + POSTFIX_LISTALL);
	}

	/**
	 * 通过id得到实体对象
	 */
	@Override
	public T findById(Serializable id) {
		return (T) sqlSessionTemplate.selectOne(clazz.getName() + POSTFIX_SELECTBYID, id);

	}
	/**
	 * 查询记录数
	 */
	@Override
	public  Integer selectSeq() {
		return (Integer) sqlSessionTemplate.selectOne(clazz.getName() + POSTFIX_SELECTBYSEQ);
	}
	/**
	 * 根据ids获取实体列表
	 * 
	 * @param ids
	 * @return
	 */
	@Override
	public List<T> findByIds(List<? extends Serializable> ids) {
		return (List<T>) sqlSessionTemplate.selectList(clazz.getName() + POSTFIX_SELECTBYIDS, ids);
	}

	/**
	 * 新增对象
	 */
	@Override
	public int insert(T o) throws Exception {
		int i = sqlSessionTemplate.insert(clazz.getName() + POSTFIX_INSERT, o);
		return i;
	}
	
	/**
	 * 新增对象 返回主键
	 */
	@Override
	public T addAndGetkey(T o) throws Exception {
		sqlSessionTemplate.insert(clazz.getName() + POSTFIX_INSERT, o);
		return o;
	}
	
	/**
	 * 更新对象
	 */
	@Override
	public int update(T o) throws Exception {
		return sqlSessionTemplate.update(clazz.getName() + POSTFIX_UPDATE, o);
	}

	/**
	 * 更新对象的部分属性
	 */
	@Override
	public int update(Map<String, Object> map) throws Exception {
		// 更新数据库
		return sqlSessionTemplate.update(clazz.getName() + POSTFIX_UPDATEBYMAP, map);
	}
	
	/**
	 * 更新对象的部分属性
	 */
	@Override
	public int updateByMap(String name,Map<String, Object> map) throws Exception {
		// 更新数据库
		return sqlSessionTemplate.update(clazz.getName() + "."+name, map);
	}
	
	/**
	 * 更新对象的部分属性
	 */
	@Override
	public int updateByTicket(Map<String, Object> map) throws Exception {
		// 更新数据库
		return sqlSessionTemplate.update(clazz.getName() + POSTFIX_UPDATEBYTicket, map);
	}

	/**
	 * 根据ID删除对象
	 */
	@Override
	public int delete(T o) throws Exception {
		return sqlSessionTemplate.delete(clazz.getName() + POSTFIX_DELETE, o);
	}
	
	@Override
	public int batchInsert(List<T> list) throws Exception {
		//批量增加
		/*SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
		int j=0;
		//session.getConnection().setAutoCommit(false);
		try{
		for (int i = 1; i <= list.size(); i++){
			 this.insert(list.get(i-1));
			 //session.insert(clazz.getName() + POSTFIX_INSERT, list.get(i));
             if (i % 1000 == 0 || i == list.size()){
                 session.commit();
                 //清理缓存，防止溢出
                 session.clearCache();
              }
              j++;
          }
		}catch(Exception e){
			e.printStackTrace();
			session.rollback();
		}
		session.close();
		return j;*/
		return sqlSessionTemplate.insert(clazz.getName() + POSTFIX_BATCHINSERT, list);
	}
	@Override
	public int batchUpdate(List<T> list) throws Exception {
		//批量更新
		/*SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
		int j=0;
		for (int i = 0; i < list.size(); i++){
			 list.get(i);
			 this.update(list.get(i));
             if (i % 1000 == 0 || i == list.size() - 1){
                 session.commit();
                 //清理缓存，防止溢出
                 session.clearCache();
              }
              j++;
        }
		session.close();
		return j;*/
		return sqlSessionTemplate.update(clazz.getName() + POSTFIX_BATCHUPDATE, list);
	}

	@Override
	public int batchDelete(List<T> list) throws Exception {
		//批量删除
		/*SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
		int j=0;
		for (int i = 0; i < list.size(); i++){
			 list.get(i);
			 this.delete(list.get(i));
             if (i % 1000 == 0 || i == list.size() - 1){
                 session.commit();
                 //清理缓存，防止溢出
                 session.clearCache();
              }
              j++;
          }
		session.close();
		return j;*/
		return sqlSessionTemplate.delete(clazz.getName() + POSTFIX_BATCHDELETE, list);
	}

	private JdbcTemplate getJdbcTemplate(){
		return this.jdbcTemplate;
	}
	
	private SqlSessionTemplate getSqlSession(){
		return this.sqlSessionTemplate;
	}
	
	public int insertByMap(String s, Object obj){
		return getSqlSession().insert(getFullMapperPath(s), obj);
	}
	
	public int deleteByMap(String s, Object obj){
		return getSqlSession().delete(getFullMapperPath(s), obj);
	}
	
	public Object selectOne(String s){
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
    }

}