package org.te.hibernateassignment;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class StudentMain {
	public static void main(String[] args) {
		Student student=new Student();
		student.setId(12);
		student.setName("maruthi");
		student.setMarks(100.0);

		EntityManagerFactory factory=null;
		EntityManager manager=null;
		EntityTransaction transaction=null;

		try {
			factory=Persistence.createEntityManagerFactory("emp");
			manager=factory.createEntityManager();
			transaction=manager.getTransaction();
			transaction.begin();
			manager.persist(student);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(transaction!=null) {
				transaction.rollback();
			}
		}
		finally {
			if(factory!=null) {
				factory.close();
			}
			if(manager!=null) {
				manager.close();
			}
		}
	}
}
