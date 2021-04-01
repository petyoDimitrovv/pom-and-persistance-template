package course.springdata.jpaintro;

import course.springdata.jpaintro.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaIntroMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("school_jpa");
        EntityManager em = emf.createEntityManager();
        Student student = new Student("Misho Shamana");
        em.getTransaction().begin();
        em.persist(student);
        em.getTransaction().commit();

        Student student2 = new Student("Gosho From Rest");
        em.getTransaction().begin();
        em.persist(student2);
        em.getTransaction().commit();

        em.getTransaction().begin();
        Student found = em.find(Student.class, 4L);
        System.out.printf("Found student: %s%n",found);
        em.getTransaction().commit();

        em.getTransaction().begin();
        em.createQuery("SELECT s FROM Student s WHERE s.name LIKE :name ")
        .setParameter("name","G%")
                .getResultList().forEach(System.out::println);
        em.getTransaction().commit();

        em.getTransaction().begin();
        em.remove(found);
        Student removed = em.find(Student.class, 3L);
        System.out.printf("Removed entity: %s",removed );
        em.getTransaction().commit();



        em.close();
    }
}
