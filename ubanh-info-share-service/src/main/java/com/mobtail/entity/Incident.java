package com.mobtail.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Incident {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private UUID id;

    @Column(name = "line")
    private String line;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "user_name")
    private String user;
}
