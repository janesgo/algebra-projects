package hr.project.dal;

import hr.project.model.Genre;
import hr.project.model.Movie;
import hr.project.model.Person;
import hr.project.model.User;
import java.util.Optional;
import java.util.Set;

/**
 *
 * @author Goran
 */
public interface Repository {

    // Authentication
    boolean userCreate(String username, String password) throws Exception;

    Optional<User> userLogin(String username, String password) throws Exception;

    // Movies
    int movieCreate(Movie movie) throws Exception;

    void moviesCreateAll(Set<Movie> movies) throws Exception;

    void movieUpdate(int id, Movie movie) throws Exception;

    void movieDelete(int id) throws Exception;

    Optional<Movie> movieSelect(int id) throws Exception;

    Set<Movie> moviesSelectAll() throws Exception;

    int movieSelectId(Movie movie) throws Exception;

    Set<Genre> movieSelectGenres(int id) throws Exception;

    void movieAddGenre(int movieId, int genreId) throws Exception;

    void movieAddAllGenres(int movieId, Set<Genre> genres) throws Exception;

    void movieRemoveGenre(int movieId, int genreId) throws Exception;

    void movieRemoveAllGenres(int movieId, Set<Genre> genres) throws Exception;

    void movieAddPerson(int movieId, int personId, int positionId) throws Exception;

    void movieAddAllPeople(int movieId, Set<Person> people, int positionId) throws Exception;

    void movieRemovePerson(int movieId, int personId, int positionId) throws Exception;

    void movieRemoveAllPeople(int movieId, Set<Person> people, int positionId) throws Exception;

    Set<Person> movieSelectPeople(int movieId, int positionId) throws Exception;

    // People
    int personCreate(Person person) throws Exception;

    void peopleCreateAll(Set<Person> people) throws Exception;

    void personUpdate(int id, Person person) throws Exception;

    void personDelete(int id) throws Exception;

    Optional<Person> personSelect(int id) throws Exception;

    Set<Person> peopleSelectAll() throws Exception;

    // Genres
    int genreCreate(Genre genre) throws Exception;

    void genresCreateAll(Set<Genre> genres) throws Exception;

    void genreUpdate(int id, Genre genre) throws Exception;

    void genreDelete(int id) throws Exception;

    Optional<Genre> genreSelect(int id) throws Exception;

    Set<Genre> genresSelectAll() throws Exception;

    void moviePersonCreateAll(Set<Movie> movies, Set<Person> people) throws Exception;

    void movieGenreCreateAll(Set<Movie> movies, Set<Genre> genres) throws Exception;
    
    void deleteAll() throws Exception;
    
    void moviesSelectGenres(Set<Movie> movies) throws Exception;
    
    void moviesSelectPeople(Set<Movie> movies) throws Exception;

}
