package hr.project.dal;

import hr.project.dal.sql.SqlRepository;

/**
 *
 * @author Goran
 */
public class RepositoryFactory {

    private RepositoryFactory() {
    }

    public static Repository getRepository() throws Exception {
        return new SqlRepository();
    }

}
