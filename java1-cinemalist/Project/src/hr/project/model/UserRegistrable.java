package hr.project.model;

/**
 *
 * @author Goran
 */
public interface UserRegistrable {
     boolean register(String username, String password) throws Exception;
}
