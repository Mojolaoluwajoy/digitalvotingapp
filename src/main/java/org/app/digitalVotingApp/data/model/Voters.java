package org.app.digitalVotingApp.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "voters")
public class Voters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "voters_id",nullable = false,unique = true)
    private String votersId;
    @Column(name = "first_name",nullable = false)
    private String firstName;
    @Column(name = "last_name",nullable = false)
    private String lastName;
    @Column(name = "voters_nin",nullable = false,unique = true,length = 11)
    private String nin;
    @Column(name="password",nullable = false)
    private String password;

}
