package pl.agh.kis.soa.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "users")
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @XmlAttribute(name = "userId")
    long userId;

    @Column(name = "name")
    @XmlElement(name = "name")
    String name;

    @Column(name = "age")
    @XmlElement(name = "age")
    int age;

    @Column(name = "avatar")
    byte [] avatar;

    @XmlElement(name = "movies")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_movie",
            joinColumns = {
                    @JoinColumn(name = "user_id")
            }
            ,
            inverseJoinColumns = {
                    @JoinColumn(name = "movie_id")
            }
    )
    Set<Movie> movies = new HashSet<>();


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    public User() {
    }

    public User(long userId, String name, int age, byte[] avatar, Set<Movie> movies) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.avatar = avatar;
        this.movies = movies;
    }

    public User(String name, int age, byte[] avatar) {
        this.name = name;
        this.age = age;
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "User id: " + userId +
                "name: " + name +
                "age: " + age;
    }
}
