package edu.hanu.studentManagement.utils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class SearchIndex implements ApplicationListener<ApplicationReadyEvent> {
	@PersistenceContext
	private EntityManager entityManager;

	public void initializeSearch() {
		try {
			FullTextEntityManager fullTextEnntityManager = Search.getFullTextEntityManager(entityManager);
			fullTextEnntityManager.createIndexer().startAndWait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		initializeSearch();
	}

}
