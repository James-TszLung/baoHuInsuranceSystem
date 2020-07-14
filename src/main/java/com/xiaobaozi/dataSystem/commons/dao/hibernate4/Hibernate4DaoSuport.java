package com.xiaobaozi.dataSystem.commons.dao.hibernate4;

// hibernate4 suport class ,don't remove plase
public class Hibernate4DaoSuport<T>
{
	
	public Hibernate4DaoSuport()
	{
	}
	/* hibernate4 suport class ,don't remove plase
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public Session getSession()
	{
	    return session != null ? session : getSessionFactory().getCurrentSession();
	}
	
	public void setSession(Session session)
	{
	    this.session = session;
	}
	
	public void save(T obj)
	{
	    getSession().save(obj);
	}
	
	public void saveOrUpdate(T obj){
		getSession().saveOrUpdate(obj);
	}
	
	public void saveOrUpdateAll(List <T> list){
		for (int i = 0; i < list.size(); i++){
			saveOrUpdate(list.get(i));
		}
	}
	
	public void saveAll(List <T> list){
		for (int i = 0; i < list.size(); i++){
			save(list.get(i));
		}
	}
	public void update(T obj)
	{
	    getSession().update(obj);
	}
	
	protected T searchForObject(String hql, Object params[])
	{
	    List list = find(hql, params);
	    return list.size() != 0 ? (T)list.get(0) : null;
	}
	
	protected T uniqueResult(String hql, Object params[])
	{
	    Query query = getSession().createQuery(hql);
	    for(int i = 0; params != null && i < params.length; i++)
	        query.setParameter(i, params[i]);
	
	    return (T)query.uniqueResult();
	}
	
	public SessionFactory getSessionFactory()
	{
	    return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory)
	{
	    this.sessionFactory = sessionFactory;
	}
	
	protected List<T> find(String queryString, Object value)
	    throws DataAccessException
	{
	    return find(queryString, new Object[] {value});
	}
	
	protected List<T> find(String queryString, Object... values)
	    throws DataAccessException
	{
	    Query queryObject = getSession().createQuery(queryString);
	    if(values != null)
	    {
	        for(int i = 0; i < values.length; i++)
	            queryObject.setParameter(i, values[i]);
	
	    }
	    return queryObject.list();
	}
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource
	private SessionFactory sessionFactory;
	private Session session;
	*/
}