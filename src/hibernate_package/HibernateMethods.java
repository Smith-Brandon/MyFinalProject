package hibernate_package;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateMethods {
	// Declare Static Variables
    private static final SessionFactory sessionFactory = buildSessionFactory();
    public static int i=1;

    // Hibernate Build Session Factory
    private static SessionFactory buildSessionFactory() {
        try {
            // Create the ServiceRegistry from hibernate.cfg.xml
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()//
                    .configure("hibernate.cfg.xml").build();

            // Create a metadata sources using the specified service registry.
            Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();

            return metadata.getSessionFactoryBuilder().build();
        } catch (Throwable ex) {

            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
    
    
	/* @SuppressWarnings("unchecked")
	public static List<stocklist> getStocks() {
		 SessionFactory factory = null;
		    Session session = null;
		    factory = HibernateMethods.getSessionFactory();
	    

	        try {
	            session = factory.openSession();
	            session.getTransaction().begin();
	            String sql = "from hibernate_package.stocklist";
	            List<stocklist> cs = (List<stocklist>)session.createQuery(sql).getResultList();
	            session.getTransaction().commit();
	            return cs;

	        } catch (Exception e) {
	            e.printStackTrace();
	            // Rollback in case of an error occurred.
	            session.getTransaction().rollback();
	            return null;
	        } finally {
	            session.close();
	        }

	    }
	
	 @SuppressWarnings("unchecked")
	public static List<stocklist> getStocksById(int start) {
		 SessionFactory factory = null;
		    Session session = null;
		    factory = HibernateMethods.getSessionFactory();
		    i = start;
while (i<100) {
	        try {
	            session = factory.openSession();
	            session.getTransaction().begin();
	            String sql = "from hibernate_package.stocklist where id =" + i;
	            List<stocklist> cs = (List<stocklist>)session.createQuery(sql).getResultList();	            
	            session.getTransaction().commit();
	            
	            //Add 1 to counter
	            i = i + 1;
	            
	            return cs;	                       
	        } catch (Exception e) {
	            e.printStackTrace();
	            // Rollback in case of an error occurred.
	            session.getTransaction().rollback();
	            return null;
	        } finally {
	            session.close();
	        }
	       
	    }
return null;
	}
	*/
	public static boolean setStocks(String name, String symbol, Float price, Float avgYield, Float divYield, int shares) {
		stocklist stock = new stocklist();

		stock.setname(name);
		stock.setsymbol(symbol);
		stock.setprice(price);
		stock.setavgYield(avgYield);
		stock.setdivYield(divYield);
		stock.setshares(shares);

		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.buildSessionFactory();
		Session session = sessionFactory.openSession();

		session.beginTransaction();
		session.save(stock);
		session.getTransaction().commit();
		return true;
	}
}
