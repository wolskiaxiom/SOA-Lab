package pl.agh.kis.soa.entity;

import org.jboss.resteasy.links.RESTServiceDiscovery;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "movie")
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "movie")
public class Movie {

    @Id
    @Column(name = "movie_id")
    @GeneratedValue(strategy = IDENTITY)
    @XmlAttribute(name = "movieId")
    long movieId;

    @Column(name = "title")
    @XmlElement(name = "title")
    String title;

    @Column(name = "uri")
    @XmlElement(name = "uri")
    String uri;

    @XmlElement(name = "users")
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "movies")
    Set<User> users = new HashSet<>();

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Movie() {
    }

    public Movie(String title, String uri, Set<User> users) {
        this.title = title;
        this.uri = uri;
        this.users = users;
    }
    public Movie(long id, String title, String uri, Set<User> users) {
        this.movieId = id;
        this.title = title;
        this.uri = uri;
        this.users = users;
    }

    @Override
    public String toString() {
        return "Id: " + movieId+
                " Uri: " + uri+
                " title " + title +
                "users: " + users.toString();
    }
}
