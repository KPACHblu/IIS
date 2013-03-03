package iis.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;

public interface GenericDAO<T> extends Serializable {

	T create(T entity);

	T update(T entity);

	void delete(T entity);

	void deleteById(Long id);

	T findById(Long id);
	
	void executeNativeUpdateQuery(String queryString, Object[] params);

	void executeUpdateQuery(String namedQuery, Object[] params);

	T executeNamedQuery(String namedQuery, Object[] params);

	List<T> executeNamedQueryList(String namedQuery, Object[] params);

	Object executeQuerySingleResult(String queryString);

	List<T> executeQueryList(String queryString, int startRow, int maxRows);
	
	void whereClause(String sortField, String sortOrder,
			Map<String, String> filters, boolean isWhere, StringBuilder query);
	
	void whereClause(String sortField, String sortOrder,
			Map<String, String> filters, StringBuilder query);
	
	void setSessionFactory(SessionFactory sessionFactory);

}