package co.edu.udes.activity.backend.demo.models;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Student")
@PrimaryKeyJoinColumn(name = "idUser")
public class Student extends User {

    @Column(name = "address")
    private String address;

    @Column(name = "statusStudent")
    private String statusStudent;

    @Column(name = "registrationDate")
    private String registrationDate;

    @Column(name = "phoneNumber")
    private String phoneNumber;


    public Student() {
    }


    public Student() {}
}
