package sportsstore.service;

import java.util.*;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sportsstore.*;
import sportsstore.domain.Product;

@Service("productService")
@Transactional
public class ProductService {
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public Map<String, Object> getAll(int pageNum, int pageSize) {
		Session session = sessionFactory.getCurrentSession();

		Query query = session.createQuery("FROM Product");

		int total = query.list().size();
		Pager pager = new Pager(total, pageNum, pageSize);

		Map<String, Object> dic = new HashMap<String, Object>();

		query.setFirstResult(pager.getLowerBound());
		query.setMaxResults(pager.getPageSize());

		List<Product> products = query.list();
		dic.put("list", products);
		dic.put("pager", pager);

		return dic;
	}

	public Map<String, Object> getByCategory(String category, int pageNum,
			int pageSize) {
		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(Product.class);

		Criterion qcategory = Restrictions.ilike("category", category,
				MatchMode.EXACT);
		criteria.add(qcategory);

		int total = criteria.list().size();
		Pager pager = new Pager(total, pageNum, pageSize);

		Map<String, Object> dic = new HashMap<String, Object>();

		criteria.setFirstResult(pager.getLowerBound());
		criteria.setMaxResults(pager.getPageSize());

		List<Product> products = criteria.list();
		dic.put("list", products);
		dic.put("pager", pager);

		return dic;
	}

	public List<String> getCategories() {
		Session session = sessionFactory.getCurrentSession();

		Query query = session
				.createSQLQuery("select distinct category from Product order by category");

		List<String> ls = query.list();

		return ls;
	}

	public Product getProduct(int productID) {
		Session session = sessionFactory.getCurrentSession();

		Product o = null;

		try {
			o = (Product) session.get(Product.class, productID);
		}

		catch (HibernateException e) {
			o = null;
		}

		return o;
	}

	public void addProduct(Product product) {
		Session session = sessionFactory.getCurrentSession();

		try {
			session.save(product);
		}

		catch (HibernateException e) {
		}
	}

	public void editProduct(Product product) {
		Session session = sessionFactory.getCurrentSession();

		Product o = null;

		try {
			o = (Product) session.get(Product.class, product.getProductID());

			o.setCategory(product.getCategory());
			o.setDescription(product.getDescription());
			o.setName(product.getName());
			o.setPrice(product.getPrice());
			o.setImageData(product.getImageData());
			o.setImageMimeType(product.getImageMimeType());

			session.save(o);
		}

		catch (HibernateException e) {
		}
	}

	public Product delete(int productID) {
		Session session = sessionFactory.getCurrentSession();

		Product p = (Product) session.get(Product.class, productID);

		session.delete(p);

		return p;
	}
}
