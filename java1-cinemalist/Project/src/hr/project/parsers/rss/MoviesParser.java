package hr.project.parsers.rss;

import hr.algebra.factory.ParserFactory;
import hr.algebra.factory.UrlConnectionFactory;
import hr.algebra.utils.FileUtils;
import hr.project.model.Genre;
import hr.project.model.Movie;
import hr.project.model.Person;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author Goran
 */
public class MoviesParser {

    private static final int TIMEOUT = 10000;
    private static final String RSS_URL = "https://www.blitz-cinestar.hr/rss.aspx?najava=1";
    private static final String REQUEST_METHOD = "GET";
    private static final String ATTRIBUTE_URL = "url";
    private static final String DIR = "assets";

    private static final DateTimeFormatter PUBLISHED_DATE_FORMAT = DateTimeFormatter.RFC_1123_DATE_TIME;
    private static final String SCREENING_DATE_FORMAT = "d.M.yyyy";

    //  Remove space after comma
    private static final String SEPARATOR = "\\s*,\\s*";
    // Remove non breaking space
    private static final String NBSPACE = "\u00A0";

    private MoviesParser() {
    }

    public static List<Movie> parse() throws IOException, XMLStreamException {
        List<Movie> movies = new ArrayList<>();

        HttpURLConnection conn = UrlConnectionFactory.getHttpUrlConnection(
                RSS_URL, TIMEOUT, REQUEST_METHOD);

        try (InputStream is = conn.getInputStream()) {
            Movie movie = null;
            StartElement startElement = null;
            Optional<TagType> tagType = Optional.empty();

            XMLEventReader reader = ParserFactory.createStaxParser(is);

            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                switch (event.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT:
                        startElement = event.asStartElement();
                        tagType = TagType.from(startElement.getName().getLocalPart());
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = event.asCharacters();
                        String data = characters.getData().trim();
                        if (tagType.isPresent()) {
                            switch (tagType.get()) {
                                case ITEM:
                                    movie = new Movie();
                                    movies.add(movie);
                                    break;
                                case TITLE:
                                    if (movie != null && !data.isEmpty()) {
                                        movie.setTitle(data);
                                    }
                                    break;
                                case PUBLISHED_DATE:
                                    if (movie != null && !data.isEmpty()) {
                                        movie.setPublishedDate(LocalDateTime.parse(data, PUBLISHED_DATE_FORMAT));
                                    }
                                    break;
                                case DESCRIPTION:
                                    if (movie != null && !data.isEmpty()) {
                                        movie.setDescription(data);
                                    }
                                    break;
                                case ORIGINAL_TITLE:
                                    if (movie != null && !data.isEmpty()) {
                                        movie.setOriginalTitle(data);
                                    }
                                    break;
                                case DIRECTORS:
                                    if (movie != null && !data.isEmpty()) {
                                        Set<Person> directors = new HashSet<>();
                                        Arrays.asList(data.replaceAll(NBSPACE, "").trim().split(SEPARATOR))
                                                .forEach(e -> directors.add(new Person(e)));
                                        movie.setDirectors(directors);
                                    }
                                    break;
                                case ACTORS:
                                    if (movie != null && !data.isEmpty()) {
                                        Set<Person> actors = new HashSet<>();
                                        Arrays.asList(data.replaceAll(NBSPACE, "").trim().split(SEPARATOR))
                                                .forEach(e -> actors.add(new Person(e)));
                                        movie.setActors(actors);
                                    }
                                    break;
                                case DURATION:
                                    if (movie != null && !data.isEmpty()) {
                                        movie.setDuration(Integer.valueOf(data));
                                    }
                                    break;
                                case GENRES:
                                    if (movie != null && !data.isEmpty()) {
                                        Set<String> elements = new TreeSet<>();
                                        Set<Genre> genres = new TreeSet<>();
                                        elements.addAll(Arrays.asList(data.replaceAll(NBSPACE, "").trim().split(SEPARATOR)));
                                        elements.forEach(e -> genres.add(new Genre(e)));
                                        movie.setGenres(genres);
                                    }
                                    break;
                                case POSTER:
                                    if (movie != null && !data.isEmpty() && movie.getPosterPath() == null) {
                                        handlePicture(movie, data);
                                    }
                                    break;
                                case LINK:
                                    if (movie != null && !data.isEmpty()) {
                                        movie.setLink(data);
                                    }
                                    break;
                                case SCREENING_STARTS_DATE:
                                    if (movie != null && !data.isEmpty()) {
                                        movie.setScreeningStartsDate(LocalDate.parse(data, DateTimeFormatter.ofPattern(SCREENING_DATE_FORMAT)));
                                    }
                                    break;
                            }
                        }
                        break;
                }
            }
        }

        return movies;
    }

    public static void handlePicture(Movie movie, String url) throws IOException {
        String picturePath = generateRandomPictureName(url);
        FileUtils.copyFromUrl(url, picturePath);
        movie.setPosterPath(picturePath);
    }

    public static String generateRandomPictureName(String s) {
        String pictureName = UUID.randomUUID() + s.substring(s.lastIndexOf("."));
        return DIR + File.separator + pictureName;
    }

    private enum TagType {
        ITEM("item"),
        TITLE("title"),
        PUBLISHED_DATE("pubDate"),
        DESCRIPTION("description"),
        ORIGINAL_TITLE("orignaziv"),
        DIRECTORS("redatelj"),
        ACTORS("glumci"),
        DURATION("trajanje"),
        GENRES("zanr"),
        POSTER("plakat"),
        LINK("link"),
        SCREENING_STARTS_DATE("pocetak");

        private final String name;

        private TagType(String name) {
            this.name = name;
        }

        private static Optional<TagType> from(String name) {
            for (TagType value : values()) {
                if (value.name.equals(name)) {
                    return Optional.of(value);
                }
            }
            return Optional.empty();
        }
    }

}
