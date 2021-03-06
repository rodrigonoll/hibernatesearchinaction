package com.manning.hsia.dvdstore.test;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.testng.annotations.Test;

import com.manning.hsia.dvdstore.action.Indexer;
import com.manning.hsia.dvdstore.model.Item;
import com.manning.hsia.dvdstore.util.SessionHolder;
import com.manning.hsia.dvdstore.util.TestCase;

public class IndexerTest extends TestCase {
	
	@Test(groups="ch02")
	public void testIndexer() throws Exception {
		SessionHolder.setSession(factory.openSession());
		
		Indexer indexing  = new Indexer();
		indexing.indexWithHibernate();
		
		SessionHolder.getSession().close();
		SessionHolder.setSession(null);
	}

	@Override
	public void postSetUp() throws Exception {
		Session s = factory.openSession();
		Transaction tx = s.beginTransaction();
	
		Item item = new Item();
		item.setTitle("Batman Begins");
		item.setEan("1234567890123");
		item.setDescription("Batman Begins explores the genese of the super hero...");
		
		s.persist(item);
		
		tx.commit();
		s.close();
	}
	
}
