package pl.agh.kis.soa.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_id")
    long userId;

    @Column(name = "name")
    String name;

    @Column(name = "age")
    int age;

    @Column(name = "avatar")
    byte [] avatar;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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

    @Override
    public String toString() {
        return "User id: " + userId +
                "name: " + name +
                "age: " + age;
    }
}
