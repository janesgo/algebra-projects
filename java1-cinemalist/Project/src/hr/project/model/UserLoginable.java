package hr.project.model;

/**
 *
 * @author Goran
 */
public interface UserLoginable {
    boolean login(String username, String password) throws Exception;
}
