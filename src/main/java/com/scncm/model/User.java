package com.scncm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.scncm.helpers.GenderType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @JsonIgnore
    private String password;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private Role role;

    @JsonIgnore
    @Column(name = "first_name")
    private String firstName;

    @JsonIgnore
    @Column(name = "last_name")
    private String lastName;

    // same
    @JsonIgnore
    @Column(name = "email")
    private String email;

    @JsonIgnore
    @Column(name = "gender")
    private GenderType gender;

    @JsonIgnore
    @Column(name = "register_date")
    private Timestamp registerDate;

    @JsonIgnore
    @Column(name = "deleted_date")
    private Timestamp deletedDate;

    @JsonIgnore
    @Column(name = "token")
    private String token;

    @JsonIgnore
    @Column(name = "secret_key")
    private String secretKey;

    @JsonIgnore
    @Column(name = "is_facebook", columnDefinition = "default '0'")
    private Boolean isFacebook;

    @JsonIgnore
    @OneToMany(mappedBy = "owner")
    @JsonBackReference("Article-OwnerId")
    private Set<Article> articles;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference("UserArticleVote-UserId")
    private Set<UserArticleVote> userArticleVotesSet;

    @JsonIgnore
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @JsonIgnore
    public String getLastName() {
        return lastName;
    }

    @JsonProperty
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
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    @Column(name = "role")
    public Role getRole() {
        return role;
    }

    @JsonProperty
    public void setRole(Role role) {
        this.role = role;
    }

    @JsonProperty
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonIgnore
    public String getEmail() {
        return this.email;
    }

    @JsonIgnore
    public GenderType getGender() {
        return gender;
    }

    @JsonProperty
    public void setGender(GenderType gender) {
        this.gender = gender;
    }

    @JsonIgnore
    public Timestamp getRegisterDate() {
        return registerDate;
    }

    @JsonProperty
    public void setRegisterDate(Timestamp registerDate) {
        this.registerDate = registerDate;
    }

    @JsonIgnore
    public Timestamp getDeletedDate() {
        return deletedDate;
    }

    @JsonProperty
    public void setDeletedDate(Timestamp deletedDate) {
        this.deletedDate = deletedDate;
    }

    @JsonIgnore
    public String getToken() {
        return token;
    }

    @JsonProperty
    public void setToken(String token) {
        this.token = token;
    }

    @JsonIgnore
    public String getSecretKey() {
        return secretKey;
    }

    @JsonProperty
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    @JsonIgnore
    public Boolean isFacebook() {
        return isFacebook;
    }

    @JsonProperty
    public void setFacebook(Boolean isFacebook) {
        this.isFacebook = isFacebook;
    }

    @JsonIgnore
    public Set<Article> getArticles() {
        return articles;
    }

    @JsonProperty
    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    @JsonIgnore
    public Set<UserArticleVote> getUserArticleVotesSet() {
        return userArticleVotesSet;
    }

    @JsonProperty
    public void setUserArticleVotesSet(Set<UserArticleVote> userArticleVotesSet) {
        this.userArticleVotesSet = userArticleVotesSet;
    }
}
