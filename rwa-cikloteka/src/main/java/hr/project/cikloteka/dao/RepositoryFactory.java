package hr.project.cikloteka.dao;

import hr.project.cikloteka.dao.sql.HibernateRepository;

public class RepositoryFactory {
    private static final Repository REPOSITORY = new HibernateRepository();

    private RepositoryFactory() {
    }

    public static Repository getRepository() {
        return REPOSITORY;
    }
}
