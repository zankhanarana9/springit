package com.zrana.springit.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Vote extends Auditable {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private short direction;

    @NonNull
    @ManyToOne
    private Link link;

    //you can store information about the user
    //to know already voted


    //user
    //link
}
