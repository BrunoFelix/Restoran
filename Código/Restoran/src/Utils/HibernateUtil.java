package Utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
    private static EntityManagerFactory emf;
    private HibernateUtil() {
    }
    public static EntityManagerFactory geteEntityManagerFactory() {
        if (emf == null) {
            try {
            	Class.forName("com.mysql.jdbc.Driver");	
                emf = Persistence
                        .createEntityManagerFactory("projetorestoran");
            } catch (Exception ex) {
                System.err.println("Initial SessionFactory creation failed."
                        + ex);
            }
        }
        return emf;
    }
}