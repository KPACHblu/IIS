package iis.dao.impl;

import iis.dao.GenericDAO;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class GenericDAOImpl<T> implements GenericDAO<T> {

	private static final long serialVersionUID = 3093054157506175222L;

	@Autowired(required = true)
	private SessionFactory sessionFactory;

	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings({ "unchecked" })
	protected Class<T> getEntityClass() {
		ParameterizedType ptype = (ParameterizedType) getClass()
				.getGenericSuperclass();
		return (Class<T>) ptype.getActualTypeArguments()[0];
	}

	@Override
	@Transactional(readOnly = false)
	public T create(T entity) {
		sessionFactory.getCurrentSession().save(entity);
		return entity;
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false)
	public T update(T entity) {
		return (T) sessionFactory.getCurrentSession().merge(entity);
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(T entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteById(Long id) {
		delete(findById(id));
	}

	@Override
	@SuppressWarnings("unchecked")
	public T findById(Long id) {
		return (T) sessionFactory.getCurrentSession().get(getEntityClass(), id);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		Query query = sessionFactory.getCurrentSession().createQuery("SELECT e FROM " + getEntityClass().getCanonicalName() + " e");
        return query.list();
	}    

	@Override
	@SuppressWarnings("unchecked")
	public List<T> executeQueryList(String queryString, int startRow,
			int maxRows) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				queryString);
		if (startRow >= 0) {
			query.setFirstResult(startRow);
		}
		if (maxRows >= 0) {
			query.setMaxResults(maxRows);
		}
		return query.list();
	}

	@Override
	public Object executeQuerySingleResult(String queryString) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				queryString);
		return query.uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> executeNamedQueryList(String namedQuery, Object[] params) {
		Query query = sessionFactory.getCurrentSession().getNamedQuery(
				namedQuery);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter("p" + (i + 1), params[i]);
			}
		}
		return query.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public T executeNamedQuery(String namedQuery, Object[] params) {
		Query query = sessionFactory.getCurrentSession().getNamedQuery(
				namedQuery);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter("p" + (i + 1), params[i]);
			}
		}
		return (T) query.uniqueResult();
	}

	@Override
	@Transactional(readOnly = false)
	public void executeUpdateQuery(String namedQuery, Object[] params) {
		Query query = sessionFactory.getCurrentSession().getNamedQuery(
				namedQuery);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter("p" + (i + 1), params[i]);
			}
		}
		query.executeUpdate();
	}

	@Override
	@Transactional(readOnly = false)
	public void executeNativeUpdateQuery(String queryString, Object[] params) {
		Query query = sessionFactory.getCurrentSession().createSQLQuery(
				queryString);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter("p" + (i + 1), params[i]);
			}
		}
		query.executeUpdate();
	}

	@Override
	public void whereClause(String sortField, String sortOrder,
			Map<String, String> filters, boolean isWhere, StringBuilder query) {

		if (filters != null && !filters.isEmpty()) {
			boolean where = isWhere;
			for (Entry<String, String> entry : filters.entrySet()) {
				if (entry.getKey().length() != 0
						&& entry.getValue().length() != 0) {
					if (!where) {
						query.append(" WHERE ");
					}
					if (where) {
						query.append(" AND ");
					}
					where = true;
					String key = entry.getKey();
					if (key.contains(".id")) {
						query.append(key);
						query.append("=");
						query.append(entry.getValue());
					} else {
						query.append(key);
						query.append(" LIKE '%");
						query.append(entry.getValue());
						query.append("%'");
					}
				}
			}
		}
		if (sortField != null && !sortField.isEmpty()) {
			query.append(" ORDER BY ");
			query.append(sortField);
			query.append(" ");
			query.append(sortOrder);
		}
	}

	@Override
	public void whereClause(String sortField, String sortOrder,
			Map<String, String> filters, StringBuilder query) {
		whereClause(sortField, sortOrder, filters, false, query);
	}


}