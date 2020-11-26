package edu.hanu.studentManagement.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.BooleanJunction;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.hanu.studentManagement.model.New;

@Service
public class SearchService {

	private final EntityManager entityManager;
	
	@Autowired
	public SearchService(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Transactional(readOnly = true)
	public List<New> search(final String keywords) {

		final FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

		// Search query builder
		final QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder()
				.forEntity(New.class).get();

		// Use a boolean junction and then add queries to it
		final BooleanJunction<BooleanJunction> outer = queryBuilder.bool();
		outer.must(queryBuilder.keyword().onFields("subject").matching(keywords).createQuery());

		@SuppressWarnings("unchecked")
		List<New> resultList = fullTextEntityManager.createFullTextQuery(outer.createQuery(), New.class)
				.getResultList();
		return resultList;
	}
}
