package hr.project.model;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Goran
 */
@XmlRootElement(name = "movies")
@XmlAccessorType(XmlAccessType.FIELD)
public class Movies {

    @XmlElement(name = "movie")
    private List<Movie> movies;

    public Movies() {
    }

    public Movies(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Movie> getMovies() {
        return movies;
    }

}
