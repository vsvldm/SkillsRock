package com.example.SkillsRock.dto.user;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class UserResponse implements Serializable {

    private final UUID id;
    private final String firstName;
    private final String lastName;
    private final String patronymic;
    private final String phoneNumber;
    private final String avatar;
    private final Set<String> roles;

    private UserResponse(UUID id,
                        String firstName,
                        String lastName,
                        String patronymic,
                        String phoneNumber,
                        String avatar,
                        Set<String> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.phoneNumber = phoneNumber;
        this.avatar = avatar;
        this.roles = roles;
    }

    public static UserResponse createUserResponse(UUID id,
                                                  String firstName,
                                                  String lastName,
                                                  String patronymic,
                                                  String phoneNumber,
                                                  String avatar,
                                                  Set<String> roles) {
        return new UserResponse(id, firstName, lastName, patronymic, phoneNumber, avatar, roles);
    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAvatar() {
        return avatar;
    }

    public Set<String> getRoles() {
        return roles;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserResponse that = (UserResponse) o;
        return Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(patronymic, that.patronymic) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(avatar, that.avatar) && Objects.equals(roles, that.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, patronymic, phoneNumber, avatar, roles);
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", avatar='" + avatar + '\'' +
                ", roles=" + roles +
                '}';
    }
}