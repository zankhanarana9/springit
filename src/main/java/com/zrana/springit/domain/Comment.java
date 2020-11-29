package com.zrana.springit.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
public class Comment extends Auditable {

    @Id
    @GeneratedValue
    private Long id;
    private String body;

    @ManyToOne
    private Link link;
}
