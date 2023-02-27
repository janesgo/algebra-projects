package hr.project.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Set;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Goran
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Movie {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    private int id;
    private String title;
    @XmlElement(name = "publisheddate")
    @XmlJavaTypeAdapter(PublishedDateAdapter.class)
    private LocalDateTime publishedDate;
    private String description;
    @XmlElement(name = "originaltitle")
    private String originalTitle;
    @XmlElementWrapper(name = "directors")
    @XmlElement(name = "director")
    private Set<Person> directors;
    @XmlElementWrapper
    @XmlElement(name = "actor")
    private Set<Person> actors;
    private int duration;
    @XmlElementWrapper(name = "genres")
    @XmlElement(name = "genre")
    private Set<Genre> genres;
    @XmlElement(name = "posterpath")
    private String posterPath;
    private String link;
    @XmlElement(name = "screeningstartsdate")
    @XmlJavaTypeAdapter(ScreeningStartsDateAdapter.class)
    private LocalDate screeningStartsDate;

    public Movie() {
    }

    public Movie(int id, String title, LocalDateTime publishedDate, String description, String originalTitle, int duration, String posterPath, String link, LocalDate screeningStartsDate) {
        this.id = id;
        this.title = title;
        this.publishedDate = publishedDate;
        this.description = description;
        this.originalTitle = originalTitle;
        this.duration = duration;
        this.posterPath = posterPath;
        this.link = link;
        this.screeningStartsDate = screeningStartsDate;
    }

    public Movie(String title, LocalDateTime publishedDate, String description, String originalTitle, int duration, String posterPath, String link, LocalDate screeningStartsDate) {
        this.title = title;
        this.publishedDate = publishedDate;
        this.description = description;
        this.originalTitle = originalTitle;
        this.duration = duration;
        this.posterPath = posterPath;
        this.link = link;
        this.screeningStartsDate = screeningStartsDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDateTime publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public Set<Person> getActors() {
        return actors;
    }

    public void setActors(Set<Person> actors) {
        this.actors = actors;
    }

    public Set<Person> getDirectors() {
        return directors;
    }

    public void setDirectors(Set<Person> directors) {
        this.directors = directors;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public LocalDate getScreeningStartsDate() {
        return screeningStartsDate;
    }

    public void setScreeningStartsDate(LocalDate screeningStartsDate) {
        this.screeningStartsDate = screeningStartsDate;
    }

    @Override
    public String toString() {
        return "Movie{" + "id=" + id + ", title=" + title + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.title);
        hash = 47 * hash + Objects.hashCode(this.originalTitle);
        hash = 47 * hash + this.duration;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Movie other = (Movie) obj;
        if (this.duration != other.duration) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.originalTitle, other.originalTitle)) {
            return false;
        }
        return true;
    }
}
