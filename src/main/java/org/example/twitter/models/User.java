package org.example.twitter.models;

import lombok.Data;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity(name = "user")
@Data
@NamedQueries({
        @NamedQuery(
                name = "User.findUser",
                query = "SELECT u FROM user u WHERE u.email = :email"
        )
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String name;

    public String email;

    private String password;

    private String contact;


}

