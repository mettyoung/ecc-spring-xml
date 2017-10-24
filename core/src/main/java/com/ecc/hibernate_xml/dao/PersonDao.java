package com.ecc.hibernate_xml.dao;

import java.util.List;
import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.apache.commons.lang3.StringUtils;

import com.ecc.hibernate_xml.model.Person;
import com.ecc.hibernate_xml.util.dao.TransactionScope;
import com.ecc.hibernate_xml.util.dao.HibernateUtility;

public class PersonDao extends AbstractDao<Person> {

	public PersonDao() {
		super(Person.class);
	}

	public List<Person> list(String lastName, Integer roleId, Date birthday, String orderBy, String order) {
		return TransactionScope.executeTransactionWithResult(session -> {
			String orderColumn = StringUtils.isEmpty(orderBy)? "id": orderBy;
			Criteria criteria = session.createCriteria(Person.class)
				.setCacheable(true)
				.addOrder("DESC".equals(order)? Order.desc(orderColumn): Order.asc(orderColumn));

			if (!StringUtils.isEmpty(lastName)) {
				criteria.add(Restrictions.ilike("name.lastName", "%" + lastName + "%"));
			}

			if (roleId != null) {
				criteria.createAlias("roles", "R").add(Restrictions.eq("R.id", roleId));
			}

			if (birthday != null) {
				criteria.add(Restrictions.eq("birthday", birthday));
			}

			return criteria.list();
		});
	}

	@Override
	public void delete(Person person) throws DaoException {
		super.delete(person);
		HibernateUtility.getSessionFactory().getCache().evictCollectionRegion(
			"com.ecc.hibernate_xml.model.Role.persons");
	}
}