package web.dao.daoImpl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import web.dao.BaseDao;
import web.util.HibernateUtil;

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

	/** * ���� * * @param bean * */
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

	/** * ��ȡ������Ϣ * * @param c * * @return */

	public List getAllList(Class c) {
		return null;
		//

	}

	/** * ��ȡ������ * * @param c * @return */

	public Long getTotalCount(Class c) {
		//
		return null;
	}



	/** * ���� * * @param bean * */
	public void update(Object bean) {
		//

	}

	/** * ɾ�� * * @param bean * */
	public void delete(Object bean) {

		try {
		//	System.out.println("ready to getsession");	
			Session session =HibernateUtil.getSession() ;
			Transaction tx=session.beginTransaction();
			session.delete(bean);
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	/** * ����IDɾ�� * * @param c �� * * @param id ID * */
	@SuppressWarnings({ "rawtypes" })
	public void delete(Class c, String id) {
		//
	}

	/** * ����ɾ�� * * @param c �� * * @param ids ID ���� * */
	@SuppressWarnings({ "rawtypes" })
	public void delete(Class c, String[] ids) {
		//
	}
}
