package com.mycompany.signupservice.dto.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "phones")
@Data
@NoArgsConstructor
@ToString
public class Phone {

    @Id
    @SequenceGenerator(name = "phone_sequence", sequenceName = "phone_sequence_id",
            initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "phone_sequence")
    @Column(name = "id")
    private int id;

    @Column(name = "number")
    private long number;

    @Column(name = "cityCode")
    private int cityCode;

    @Column(name = "countryCode")
    private String countryCode;

    @Column(name = "userId")
    private String userId;

}
