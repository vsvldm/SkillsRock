package com.example.SkillsRock.dto.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Objects;
import java.util.UUID;

public class UserUpdate {
    @NotNull(message = "ID cannot be null")
    private final UUID id;

    @Size(max = 50, message = "First name cannot exceed 50 characters")
    private final String firstName;

    @Size(max = 50, message = "Last name cannot exceed 50 characters")
    private final String lastName;

    @Size(max = 50, message = "Patronymic cannot exceed 50 characters")
    private final String patronymic;

    @Pattern(
            regexp = "^\\+?[0-9]{10,15}$",
            message = "Phone number must be 10-15 digits and may start with '+'"
    )
    private final String phoneNumber;

    private final String avatar;

    public UserUpdate(UUID id, String firstName,
                      String lastName,
                      String patronymic,
                      String phoneNumber,
                      String avatar) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.phoneNumber = phoneNumber;
        this.avatar = avatar;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserUpdate that = (UserUpdate) o;
        return Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(patronymic, that.patronymic) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(avatar, that.avatar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, patronymic, phoneNumber, avatar);
    }

    @Override
    public String toString() {
        return "UserUpdate{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}