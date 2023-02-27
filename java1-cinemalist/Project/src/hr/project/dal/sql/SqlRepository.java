package hr.project.dal.sql;

import hr.project.dal.Repository;
import hr.project.model.Genre;
import hr.project.model.Movie;
import hr.project.model.Person;
import hr.project.model.Position;
import hr.project.model.Role;
import hr.project.model.User;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author Goran
 */
public class SqlRepository implements Repository {

    // Authentication
    private static final String ID_USER = "IDUser";
    private static final String USERNAME = "Username";
    private static final String PASSWORD = "Password";
    private static final String ROLE = "RoleID";
    private static final String USER_CREATE = "{ CALL Users_Create (?,?,?,?) }";
    private static final String USER_SELECT = "{ CALL Users_Select (?,?) }";

    // Movies
    private static final String MOVIE_ID = "IDMovie";
    private static final String MOVIE_TITLE = "Title";
    private static final String MOVIE_PUBLISHED_DATE = "PublishedDate";
    private static final String MOVIE_DESCRIPTION = "Description";
    private static final String MOVIE_ORIGINAL_TITLE = "OriginalTitle";
    private static final String MOVIE_DURATION = "Duration";
    private static final String MOVIE_POSTER_PATH = "PosterPath";
    private static final String MOVIE_LINK = "Link";
    private static final String MOVIE_SCREENING_STARTS_DATE = "ScreeningStartsDate";

    private static final String MOVIE_CREATE = "{ CALL Movies_Create (?,?,?,?,?,?,?,?,?) }";
    private static final String MOVIE_UPDATE = "{ CALL Movies_Update (?,?,?,?,?,?,?,?,?) }";
    private static final String MOVIE_DELETE = "{ CALL Movies_Delete (?) }";
    private static final String MOVIE_SELECT = "{ CALL Movies_Select (?) }";
    private static final String MOVIE_SELECT_ALL = "{ CALL Movies_SelectAll }";
    private static final String MOVIE_SELECT_ID = "{ CALL Movies_SelectId (?,?,?,?,?,?) }";

    private static final String MOVIE_SELECT_GENRES = "{ CALL Movies_SelectGenres (?) }";
    private static final String MOVIE_ADD_GENRE = "{ CALL Movies_AddGenre (?,?) }";
    private static final String MOVIE_REMOVE_GENRE = "{ CALL Movies_RemoveGenre (?,?) }";
    private static final String MOVIE_SELECT_PEOPLE = "{ CALL Movies_SelectPeople (?, ?) }";
    private static final String MOVIE_ADD_PERSON = "{ CALL Movies_AddPerson (?,?,?) }";
    private static final String MOVIE_REMOVE_PERSON = "{ CALL Movies_RemovePerson (?,?,?) }";

    private static final String MOVIE_DELETE_ALL = "{ CALL Clear_Database }";

    // People
    private static final String PERSON_ID = "IDPerson";
    private static final String PERSON_NAME = "Name";

    private static final String PERSON_CREATE = "{ CALL People_Create (?,?) }";
    private static final String PERSON_UPDATE = "{ CALL People_Update (?,?) }";
    private static final String PERSON_DELETE = "{ CALL People_Delete (?) }";
    private static final String PERSON_SELECT = "{ CALL People_Select (?) }";
    private static final String PERSON_SELECT_ALL = "{ CALL People_SelectAll }";
    private static final String PERSON_SELECT_ID = "{ CALL People_SelectId (?,?) }";

    // Genres
    private static final String GENRE_ID = "IDGenre";
    private static final String GENRE_NAME = "Name";

    private static final String GENRE_CREATE = "{ CALL Genres_Create (?,?) }";
    private static final String GENRE_UPDATE = "{ CALL Genres_Update (?,?) }";
    private static final String GENRE_DELETE = "{ CALL Genres_Delete (?) }";
    private static final String GENRE_SELECT = "{ CALL Genres_Select (?) }";
    private static final String GENRE_SELECT_ALL = "{ CALL Genres_SelectAll }";
    private static final String GENRE_SELECT_ID = "{ CALL Genres_SelectId (?,?) }";

    @Override
    public boolean userCreate(String username, String password) throws Exception {
        DataSource instance = DataSourceSingleton.getInstance();
        try (Connection conn = instance.getConnection();
                CallableStatement stmt = conn.prepareCall(USER_CREATE)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setInt(3, Role.User.getId());
            stmt.registerOutParameter(4, Types.INTEGER);
            stmt.executeUpdate();

            return stmt.getInt(4) > 0;
        }
    }

    @Override
    public Optional<User> userLogin(String username, String password) throws Exception {
        DataSource instance = DataSourceSingleton.getInstance();
        try (Connection conn = instance.getConnection();
                CallableStatement stmt = conn.prepareCall(USER_SELECT)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new User(
                            rs.getInt(ID_USER),
                            rs.getString(USERNAME),
                            rs.getString(PASSWORD),
                            Role.from(rs.getInt(ROLE))));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public int movieCreate(Movie movie) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn.prepareCall(MOVIE_CREATE)) {
            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getPublishedDate()
                    .format(Movie.DATE_TIME_FORMATTER));
            stmt.setString(3, movie.getDescription());
            stmt.setString(4, movie.getOriginalTitle());
            stmt.setInt(5, movie.getDuration());
            stmt.setString(6, movie.getPosterPath());
            stmt.setString(7, movie.getLink());
            stmt.setString(8, movie.getScreeningStartsDate()
                    .format(Movie.DATE_FORMATTER));
            stmt.registerOutParameter(9, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(9);
        }
    }

    @Override
    public void moviesCreateAll(Set<Movie> movies) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn.prepareCall(MOVIE_CREATE)) {
            for (Movie movie : movies) {
                stmt.setString(1, movie.getTitle());
                stmt.setString(2, movie.getPublishedDate()
                        .format(Movie.DATE_TIME_FORMATTER));
                stmt.setString(3, movie.getDescription());
                stmt.setString(4, movie.getOriginalTitle());
                stmt.setInt(5, movie.getDuration());
                stmt.setString(6, movie.getPosterPath());
                stmt.setString(7, movie.getLink());
                stmt.setString(8, movie.getScreeningStartsDate()
                        .format(Movie.DATE_FORMATTER));
                stmt.registerOutParameter(9, Types.INTEGER);
                stmt.executeUpdate();

                // Fetch id from database and set it to the model:
                movie.setId(stmt.getInt(9));
            }
        }
    }

    @Override
    public void movieUpdate(int id, Movie movie) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn.prepareCall(MOVIE_UPDATE)) {
            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getPublishedDate()
                    .format(Movie.DATE_TIME_FORMATTER));
            stmt.setString(3, movie.getDescription());
            stmt.setString(4, movie.getOriginalTitle());
            stmt.setInt(5, movie.getDuration());
            stmt.setString(6, movie.getPosterPath());
            stmt.setString(7, movie.getLink());
            stmt.setString(8, movie.getScreeningStartsDate().
                    format(Movie.DATE_FORMATTER));
            stmt.setInt(9, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void movieDelete(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn.prepareCall(MOVIE_DELETE)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<Movie> movieSelect(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn.prepareCall(MOVIE_SELECT)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Movie(
                            rs.getInt(MOVIE_ID),
                            rs.getString(MOVIE_TITLE),
                            LocalDateTime.parse(rs.getString(MOVIE_PUBLISHED_DATE),
                                    Movie.DATE_TIME_FORMATTER),
                            rs.getString(MOVIE_DESCRIPTION),
                            rs.getString(MOVIE_ORIGINAL_TITLE),
                            rs.getInt(MOVIE_DURATION),
                            rs.getString(MOVIE_POSTER_PATH),
                            rs.getString(MOVIE_LINK),
                            LocalDate.parse(rs.getString(MOVIE_SCREENING_STARTS_DATE),
                                    Movie.DATE_FORMATTER)));
                }
            }
        }

        return Optional.empty();
    }

    @Override
    public Set<Movie> moviesSelectAll() throws Exception {
        Set<Movie> movies = new HashSet<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn.prepareCall(MOVIE_SELECT_ALL);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                movies.add(new Movie(
                        rs.getInt(MOVIE_ID),
                        rs.getString(MOVIE_TITLE),
                        LocalDateTime.parse(rs.getString(MOVIE_PUBLISHED_DATE),
                                Movie.DATE_TIME_FORMATTER),
                        rs.getString(MOVIE_DESCRIPTION),
                        rs.getString(MOVIE_ORIGINAL_TITLE),
                        rs.getInt(MOVIE_DURATION),
                        rs.getString(MOVIE_POSTER_PATH),
                        rs.getString(MOVIE_LINK),
                        LocalDate.parse(rs.getString(MOVIE_SCREENING_STARTS_DATE),
                                Movie.DATE_FORMATTER)));
            }
        }

        return movies;
    }

    @Override
    public int movieSelectId(Movie movie) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn.prepareCall(MOVIE_SELECT_ID)) {
            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getPublishedDate()
                    .format(Movie.DATE_TIME_FORMATTER));
            stmt.setString(3, movie.getDescription());
            stmt.setString(4, movie.getOriginalTitle());
            stmt.setInt(5, movie.getDuration());
            stmt.registerOutParameter(6, Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt(6);
        }
    }

    @Override
    public void movieAddGenre(int movieId, int genreId) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn.prepareCall(MOVIE_ADD_GENRE)) {
            stmt.setInt(1, movieId);
            stmt.setInt(2, genreId);
            stmt.executeUpdate();
        }
    }

    @Override
    public void movieRemoveGenre(int movieId, int genreId) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn.prepareCall(MOVIE_REMOVE_GENRE)) {
            stmt.setInt(1, movieId);
            stmt.setInt(2, genreId);
            stmt.executeUpdate();
        }
    }

    @Override
    public void movieAddPerson(int movieId, int personId, int positionId) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn.prepareCall(MOVIE_ADD_PERSON)) {
            stmt.setInt(1, personId);
            stmt.setInt(2, movieId);
            stmt.setInt(3, positionId);
            stmt.executeUpdate();
        }
    }

    @Override
    public void movieRemovePerson(int movieId, int personId, int positionId) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn.prepareCall(MOVIE_REMOVE_PERSON)) {
            stmt.setInt(1, personId);
            stmt.setInt(2, movieId);
            stmt.setInt(3, positionId);
            stmt.executeUpdate();
        }
    }

    @Override
    public int personCreate(Person person) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn.prepareCall(PERSON_CREATE)) {
            stmt.setString(1, person.getName());
            stmt.registerOutParameter(2, Types.INTEGER);
            stmt.executeUpdate();

            return stmt.getInt(2);
        }
    }

    @Override
    public void peopleCreateAll(Set<Person> people) throws Exception {
        if (people == null) {
            return;
        }

        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn.prepareCall(PERSON_CREATE)) {
            for (Person person : people) {
                stmt.setString(1, person.getName());
                stmt.registerOutParameter(2, Types.INTEGER);
                stmt.executeUpdate();

                // Fetch id from database and set it to the model:
                person.setId(stmt.getInt(2));
            }
        }
    }

    @Override
    public void personUpdate(int id, Person person) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn.prepareCall(PERSON_UPDATE)) {
            stmt.setString(1, person.getName());
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void personDelete(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn.prepareCall(PERSON_DELETE)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<Person> personSelect(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn.prepareCall(PERSON_SELECT)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Person(
                            rs.getInt(PERSON_ID),
                            rs.getString(PERSON_NAME)));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public Set<Person> peopleSelectAll() throws Exception {
        Set<Person> people = new HashSet<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn.prepareCall(PERSON_SELECT_ALL);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                people.add(new Person(
                        rs.getInt(PERSON_ID),
                        rs.getString(PERSON_NAME)));
            }
        }
        return people;
    }

    @Override
    public int genreCreate(Genre genre) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn.prepareCall(GENRE_CREATE)) {
            stmt.setString(1, genre.getName());
            stmt.registerOutParameter(2, Types.INTEGER);
            stmt.executeUpdate();

            return stmt.getInt(2);
        }
    }

    @Override
    public void genresCreateAll(Set<Genre> genres) throws Exception {
        if (genres == null) {
            return;
        }

        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn.prepareCall(GENRE_CREATE)) {
            for (Genre genre : genres) {
                stmt.setString(1, genre.getName());
                stmt.registerOutParameter(2, Types.INTEGER);
                stmt.executeUpdate();

                // Fetch id from database and set it to the model:
                genre.setId(stmt.getInt(2));
            }
        }
    }

    @Override
    public void genreUpdate(int id, Genre genre) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn.prepareCall(GENRE_UPDATE)) {
            stmt.setString(1, genre.getName());
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void genreDelete(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn.prepareCall(GENRE_DELETE)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<Genre> genreSelect(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn.prepareCall(GENRE_SELECT)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Genre(
                            rs.getInt(GENRE_ID),
                            rs.getString(GENRE_NAME)));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public Set<Genre> genresSelectAll() throws Exception {
        Set<Genre> genres = new TreeSet<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn.prepareCall(GENRE_SELECT_ALL);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                genres.add(new Genre(
                        rs.getInt(GENRE_ID),
                        rs.getString(GENRE_NAME)));
            }
        }
        return genres;
    }

    @Override
    public void movieGenreCreateAll(Set<Movie> movies, Set<Genre> genres) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn.prepareCall(MOVIE_ADD_GENRE)) {
            movies.forEach((movie) -> {
                movie.getGenres().forEach(mg -> {
                    if (movie.getGenres() == null) {
                        return;
                    }
                    genres.forEach(g -> {
                        if (mg.getName().equals(g.getName())) {
                            try {
                                stmt.setInt(1, movie.getId());
                                stmt.setInt(2, g.getId());
                                stmt.executeUpdate();
                            } catch (SQLException ex) {
                                Logger.getLogger(SqlRepository.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                });
            });
        }
    }

    @Override
    public void moviePersonCreateAll(Set<Movie> movies, Set<Person> people) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn.prepareCall(MOVIE_ADD_PERSON)) {
            movies.forEach((movie) -> {
                if (movie.getActors() != null) {
                    movie.getActors().forEach(ma -> {
                        people.forEach(p -> {
                            if (ma.equals(p)) {
                                try {
                                    stmt.setInt(1, p.getId());
                                    stmt.setInt(2, movie.getId());
                                    stmt.setInt(3, Position.from(Position.Actor));
                                    stmt.executeUpdate();
                                } catch (SQLException ex) {
                                    Logger.getLogger(SqlRepository.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });
                    });
                }

                if (movie.getDirectors() != null) {
                    movie.getDirectors().forEach(md -> {
                        people.forEach(p -> {
                            if (md.equals(p)) {
                                try {
                                    stmt.setInt(1, p.getId());
                                    stmt.setInt(2, movie.getId());
                                    stmt.setInt(3, Position.from(Position.Director));
                                    stmt.executeUpdate();
                                } catch (SQLException ex) {
                                    Logger.getLogger(SqlRepository.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });
                    });
                }
            });
        }
    }

    @Override
    public Set<Genre> movieSelectGenres(int id) throws Exception {
        Set<Genre> genres = new TreeSet<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn.prepareCall(MOVIE_SELECT_GENRES)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    genres.add(new Genre(
                            rs.getInt(GENRE_ID),
                            rs.getString(GENRE_NAME)));
                }
            }
        }
        return genres;
    }

    @Override
    public Set<Person> movieSelectPeople(int movieId, int positionId) throws Exception {
        Set<Person> people = new TreeSet<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn.prepareCall(MOVIE_SELECT_PEOPLE)) {
            stmt.setInt(1, movieId);
            stmt.setInt(2, positionId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    people.add(new Person(
                            rs.getInt(PERSON_ID),
                            rs.getString(PERSON_NAME)));
                }
            }
        }
        return people;
    }

    @Override
    public void moviesSelectGenres(Set<Movie> movies) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn.prepareCall(MOVIE_SELECT_GENRES)) {
            for (Movie movie : movies) {
                Set<Genre> genres = new TreeSet<>();
                stmt.setInt(1, movie.getId());
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        genres.add(new Genre(
                                rs.getInt(GENRE_ID),
                                rs.getString(GENRE_NAME)));
                    }
                }
                movie.setGenres(genres);
            }
        }
    }

    @Override
    public void moviesSelectPeople(Set<Movie> movies) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn.prepareCall(MOVIE_SELECT_PEOPLE)) {
            for (Movie movie : movies) {
                Set<Person> actors = new TreeSet<>();
                Set<Person> directors = new TreeSet<>();

                stmt.setInt(1, movie.getId());
                stmt.setInt(2, Position.from(Position.Actor));
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        actors.add(new Person(
                                rs.getInt(PERSON_ID),
                                rs.getString(PERSON_NAME)));
                    }
                }

                stmt.setInt(2, Position.from(Position.Director));
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        directors.add(new Person(
                                rs.getInt(PERSON_ID),
                                rs.getString(PERSON_NAME)));
                    }
                }
                movie.setActors(actors);
                movie.setDirectors(directors);
            }
        }
    }

    @Override
    public void movieAddAllGenres(int movieId, Set<Genre> genres) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn.prepareCall(MOVIE_ADD_GENRE)) {
            for (Genre genre : genres) {
                stmt.setInt(1, movieId);
                stmt.setInt(2, genre.getId());
                stmt.executeUpdate();
            }
        }
    }

    @Override
    public void movieRemoveAllGenres(int movieId, Set<Genre> genres) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn.prepareCall(MOVIE_REMOVE_GENRE)) {
            for (Genre genre : genres) {
                stmt.setInt(1, movieId);
                stmt.setInt(2, genre.getId());
                stmt.executeUpdate();
            }
        }
    }

    @Override
    public void movieAddAllPeople(int movieId, Set<Person> people,
            int positionId) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn.prepareCall(MOVIE_ADD_PERSON)) {
            for (Person person : people) {
                stmt.setInt(1, person.getId());
                stmt.setInt(2, movieId);
                stmt.setInt(3, positionId);
                stmt.executeUpdate();
            }
        }
    }

    @Override
    public void movieRemoveAllPeople(int movieId, Set<Person> people,
            int positionId) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn.prepareCall(MOVIE_REMOVE_PERSON)) {
            for (Person person : people) {
                stmt.setInt(1, person.getId());
                stmt.setInt(2, movieId);
                stmt.setInt(3, positionId);
                stmt.executeUpdate();
            }
        }
    }

    @Override
    public void deleteAll() throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn.prepareCall(MOVIE_DELETE_ALL)) {
            stmt.executeUpdate();
        }
    }
}
