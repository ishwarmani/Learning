package main;

import model.employee.Employee;
import model.student.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class App {
	public static void main(String[] args) {
		persistEmployeeInDb1();
		persistenceStudentInDb2();
	}

	private static void persistEmployeeInDb1(){
		//fetching second persistence unit
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		System.out.println("Starting Transaction");
		entityManager.getTransaction().begin();
		Employee employee = new Employee();
		employee.setName("Ram");
		System.out.println("Saving Employee to Database");

		entityManager.persist(employee);
		entityManager.getTransaction().commit();
		System.out.println("Generated Employee ID = " + employee.getEmployeeId());

		// get an object using primary key.
		Employee emp = entityManager.find(Employee.class, employee.getEmployeeId());
		System.out.println("got object " + emp.getName() + " " + emp.getEmployeeId());

		// get all the objects from Employee table
		@SuppressWarnings("unchecked")
		List<Employee> listEmployee = entityManager.createQuery("SELECT e FROM Employee e").getResultList();

		if (listEmployee == null) {
			System.out.println("No employee found . ");
		} else {
			for (Employee empl : listEmployee) {
				System.out.println("Employee name= " + empl.getName() + ", Employee id " + empl.getEmployeeId());
			}
		}
		// remove and entity
		/*
		entityManager.getTransaction().begin();
		System.out.println("Deleting Employee with ID = " + emp.getEmployeeId());
		entityManager.remove(emp);
		entityManager.getTransaction().commit();
		*/
		// close the entity manager
		entityManager.close();
		entityManagerFactory.close();
	}

	private static void persistenceStudentInDb2(){
		//fetching second persistence unit
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence2");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		System.out.println("Starting Transaction");
		entityManager.getTransaction().begin();
		Student student = new Student();
		student.setName("Raghav");
		System.out.println("Saving student in database2");

		entityManager.persist(student);
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
	}
}
