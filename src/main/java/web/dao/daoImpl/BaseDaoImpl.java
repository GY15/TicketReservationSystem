package web.dao.daoImpl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import web.dao.BaseDao;
import web.utilities.HibernateUtil;

import java.util.List;


public class BaseDaoImpl implements BaseDao {
	
	public BaseDaoImpl() {
	}

	public void flush() {
		HibernateUtil.getSession().flush();
	}

	public void clear() {
		HibernateUtil.getSession().clear();
	}

	public void save(Object bean) {
		try {
			System.out.println("ready to getsession");	
			Session session =HibernateUtil.getSession() ;
			Transaction tx=session.beginTransaction();
			session.merge(bean);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}  
	}
	
	public Object load(Class c, String id) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction tx=session.beginTransaction();
			Object o=session.get(c, id);
			tx.commit();
			return o;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}
	public Object load(Class c, int id) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction tx=session.beginTransaction();
			Object o=session.get(c, id);
			tx.commit();
			return o;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	public List getAllList(Class c) {
		return null;
	}


	public Long getTotalCount(Class c) {
		return null;
	}



	public void update(Object bean) {
		try {
			Session session =HibernateUtil.getSession() ;
			Transaction tx=session.beginTransaction();
			session.update(bean);
			tx.commit();

		} catch (Exception e) {
		}
	}

	public void delete(Object bean) {

		try {
			Session session =HibernateUtil.getSession() ;
			Transaction tx=session.beginTransaction();
			session.delete(bean);
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	public void delete(Class c, String id) {

	}

	public void delete(Class c, String[] ids) {
	}
}
