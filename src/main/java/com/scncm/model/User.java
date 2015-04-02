package com.scncm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.scncm.helpers.GenderType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.scncm.helpers.GenderType;
import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    public User(String username, String password, Role role, String firstName, String lastName, String email, GenderType gender, Timestamp registerDate, Timestamp deletedDate, String token, String secretKey, Boolean isFacebook, Set<Article> articles, Set<UserArticleVote> userArticleVotesSet) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.registerDate = registerDate;
        this.deletedDate = deletedDate;
        this.token = token;
        this.secretKey = secretKey;
        this.isFacebook = isFacebook;
        this.articles = articles;
        this.userArticleVotesSet = userArticleVotesSet;
    }

    public User(){}

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", registerDate=" + registerDate +
                ", deletedDate=" + deletedDate +
                ", token='" + token + '\'' +
                ", secretKey='" + secretKey + '\'' +
                ", isFacebook=" + isFacebook +
                ", articles=" + articles +
                ", userArticleVotesSet=" + userArticleVotesSet +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (articles != null ? !articles.equals(user.articles) : user.articles != null) return false;
        if (deletedDate != null ? !deletedDate.equals(user.deletedDate) : user.deletedDate != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (gender != user.gender) return false;
        if (isFacebook != null ? !isFacebook.equals(user.isFacebook) : user.isFacebook != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (registerDate != null ? !registerDate.equals(user.registerDate) : user.registerDate != null) return false;
        if (role != null ? !role.equals(user.role) : user.role != null) return false;
        if (secretKey != null ? !secretKey.equals(user.secretKey) : user.secretKey != null) return false;
        if (token != null ? !token.equals(user.token) : user.token != null) return false;
        if (userArticleVotesSet != null ? !userArticleVotesSet.equals(user.userArticleVotesSet) : user.userArticleVotesSet != null)
            return false;
        if (userId != null ? !userId.equals(user.userId) : user.userId != null) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (registerDate != null ? registerDate.hashCode() : 0);
        result = 31 * result + (deletedDate != null ? deletedDate.hashCode() : 0);
        result = 31 * result + (token != null ? token.hashCode() : 0);
        result = 31 * result + (secretKey != null ? secretKey.hashCode() : 0);
        result = 31 * result + (isFacebook != null ? isFacebook.hashCode() : 0);
        result = 31 * result + (articles != null ? articles.hashCode() : 0);
        result = 31 * result + (userArticleVotesSet != null ? userArticleVotesSet.hashCode() : 0);
        return result;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer userId;

    // same as email
    @Column(name = "username")
    private String username;

    @JsonIgnore
    @Column(name = "password")
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
    @OneToMany(mappedBy = "user")
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
