package hr.project.cikloteka.dao.sql;

import javax.persistence.EntityManagerFactory;
import javax.persistence.NamedQueries;
import javax.persistence.Persistence;

public class HibernateFactory {
    private static final String PERSISTENCE_UNIT = "default";

    private static final EntityManagerFactory EMF
            = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);

    public static EntityManagerWrapper getEntityManager() {
        return new EntityManagerWrapper(EMF.createEntityManager());
    }

    public static void release() {
        EMF.close();
    }

    public static String getSelectAll(Class c) {
        NamedQueries queries = (NamedQueries) c.getAnnotation(NamedQueries.class);
        return queries.value()[0].name();
    }
}
