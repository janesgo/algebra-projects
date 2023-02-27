package hr.project.cikloteka.dao.sql;

import javax.persistence.EntityManager;

public class EntityManagerWrapper implements AutoCloseable {

    private final EntityManager entityManager;

    public EntityManagerWrapper(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityManager get() {
        return entityManager;
    }

    @Override
    public void close() throws Exception {
        if (entityManager != null) {
            entityManager.close();
        }
    }

}
