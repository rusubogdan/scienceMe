package com.scncm.model;

import com.scncm.helpers.GenderType;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer userId;

    // same as email
    @Column(name = "username")
    private String username;

    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private Role role;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    // same
    @Column(name = "email")
    private String email;

    @Column(name = "gender")
    private GenderType gender;

    @Column(name = "register_date")
    private Timestamp registerDate;

    @Column(name = "deleted_date")
    private Timestamp deletedDate;

    @Column(name = "token")
    private String token;

    @Column(name = "secret_key")
    private String secretKey;

    @Column(name = "is_facebook", columnDefinition = "default '0'")
    private Boolean isFacebook;

    @OneToMany(mappedBy = "owner")
    private Set<Article> articles;

    @OneToMany(mappedBy = "user")
    private Set<UserArticleVote> userArticleVotesSet;

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "role")
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public GenderType getGender() {
        return gender;
    }

    public void setGender(GenderType gender) {
        this.gender = gender;
    }

    public Timestamp getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Timestamp registerDate) {
        this.registerDate = registerDate;
    }

    public Timestamp getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Timestamp deletedDate) {
        this.deletedDate = deletedDate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Boolean isFacebook() {
        return isFacebook;
    }

    public void setFacebook(Boolean isFacebook) {
        this.isFacebook = isFacebook;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    public Set<UserArticleVote> getUserArticleVotesSet() {
        return userArticleVotesSet;
    }

    public void setUserArticleVotesSet(Set<UserArticleVote> userArticleVotesSet) {
        this.userArticleVotesSet = userArticleVotesSet;
    }
}
