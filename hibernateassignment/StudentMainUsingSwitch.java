package org.te.hibernateassignment;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class StudentMainUsingSwitch {
	public static void main(String[] args) {
		int ch;
		char choice = 'y';
		int flag=0,flag1=0;

		EntityManagerFactory factory=null;
		EntityManager manager=null;
		EntityTransaction transaction=null;
		Scanner scan=null;

		try {
			scan=new Scanner(System.in);
			factory=Persistence.createEntityManagerFactory("emp");
			manager=factory.createEntityManager();
			transaction=manager.getTransaction();

			while(true) {
				if(choice=='y') {
					System.out.println("press 1 to see all the data\n press 2 to see any particular data\n press 3 to update any particular data\n press 4 to see delete the data");
					System.out.println("enter your choice");
					ch=scan.nextInt();
					switch(ch) {
					case 1:String selectAll="from Student";
					Query query=manager.createQuery(selectAll);
					List<Student>list=query.getResultList();
					System.out.println(list);
					break;
					case 2:
						transaction.begin();
						int id;
						System.out.println("enter the student id");
						id=scan.nextInt();
						String quary2="from Student where id=:id";
						Query query3=manager.createQuery(quary2);
						query3.setParameter("id",id);
						try {
							List<Student>list1=query3.getResultList();
							if(list1.isEmpty()) {
								throw new InvalidIdException("invalid id");
							}
							else
								System.out.println(list1);
						}
						catch (Exception e) {
							System.out.println(e.getMessage());
						}

						transaction.commit();
					case 3:
						transaction.begin();
						String id1;
						System.out.println("enter the id");
						id1 = scan.next();
						String queryu1 = "from Student where id= :id1";
						Query queryu11 = manager.createQuery(queryu1);
						queryu11.setParameter("id1", Integer.parseInt(id1));

						try {
							List<Student> list1 = queryu11.getResultList();
							if(list1.isEmpty())
								throw new InvalidIdException("Invalid id");
							else
								flag=1;
						}																																												
						catch (InvalidIdException e) {
							System.out.println(e.getMessage());
						}
						transaction.commit();
						//System.out.println(stud);

						if(flag==1) {
							char cho,cho1;

							System.out.println("do you want to change the name");
							cho=scan.next().charAt(0);
							if(cho=='y') {
								System.out.println("enter the name");
								String name= scan.next();
								transaction.begin();
								String update="update Student set name=:name   where id=:id1";
								Query queryp= manager.createQuery(update);
								queryp.setParameter("name",name);
								queryp.setParameter("id1",Integer.parseInt(id1));
								int r = queryp.executeUpdate();
								transaction.commit();
							}



							System.out.println("do you want to change the marks");


							cho1=scan.next().charAt(0);
							if(cho1=='y') {
								System.out.println("enter the your marks");
								String marks= scan.next();
								transaction.begin();
								String update="update Student set marks=:marks  where id=:id1";
								Query queryp= manager.createQuery(update);
								queryp.setParameter("marks",Double.parseDouble(marks));
								queryp.setParameter("id1",Integer.parseInt(id1));
								int r = queryp.executeUpdate();
								transaction.commit();
							}




						}
						break; 
					case 4:
						transaction.begin();
						String id2;
						System.out.println("enter the id");
						id2= scan.next();
						String queryd = "from Student where id= :id2";
						Query queryd1 = manager.createQuery(queryd);
						queryd1.setParameter("id2", Integer.parseInt(id2));

						try {
							List<Student> list1 = queryd1.getResultList();
							if(list1.isEmpty())
								throw new InvalidIdException("Invalid id");
							else
								flag1=1;
						}																																												
						catch (InvalidIdException e) {
							System.out.println(e.getMessage());
						}
						transaction.commit();
						if(flag1==1) {

							transaction.begin();
							String update2="delete from Student  where id=:id";
							Query queryp= manager.createQuery(update2);
							queryp.setParameter("id2",Integer.parseInt(id2));
							int r = queryp.executeUpdate();
							transaction.commit();	
						}

						break;
					}
				}

				else {
					System.exit(0);
				}

				System.out.println("do you want to continue");
				choice = scan.next().charAt(0);
			}

		} catch (Exception e) {
         transaction.rollback();
		} finally {
			factory.close();
			manager.close();
		}

	}
}
