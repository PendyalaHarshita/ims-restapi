package com.wellsfargo.training.ims.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.Base64;

/*
     * Modeling with foreign key relationship in JPA.
     * Place @OneToOne on the primary class entity field Dealer.
     * Place @JoinColumn to configure foreign key column dealer_id in the Address class
     * that maps to the primary key column of Dealer class.
6
     */
@Entity
@Table(name = "dealers")
public class Dealer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "did")
    private Long id;
    @Column(unique = true)
    private String email;

    private String first_name;
    private String last_name;
    private String password;
    @JsonFormat(pattern = "YYYY-MM-DD")
    private Date dob;
    @Column(unique = true)
    private String phoneNo;
    @OneToOne(mappedBy = "dealer",cascade = CascadeType.ALL)
    private Address address;

    public Dealer() {
        super();
    }

    public Dealer(Long id, String email, String first_name, String last_name, String password, Date dob, String phoneNo, Address address) {
        this.id = id;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.password = password;
        this.dob = dob;
        this.phoneNo = phoneNo;
        this.address = address;
    }

    public Dealer(Long id, String email, String first_name, String last_name, String password, Date dob, String phoneNo) {
        this.id = id;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.password = password;
        this.dob = dob;
        this.phoneNo = phoneNo;
     }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        Base64.Encoder encoder = Base64.getEncoder();
        String normalString = password;
        String encodedString = encoder.encodeToString(   // encrypt password in database field
                normalString.getBytes(StandardCharsets.UTF_8) );
        this.password = encodedString;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
