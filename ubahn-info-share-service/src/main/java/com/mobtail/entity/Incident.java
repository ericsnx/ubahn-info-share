package com.mobtail.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "incidents")
public class Incident {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private UUID id;

    @Column(name = "line")
    private String line;

    @Column(name = "user_name")
    private String user;

    @Column(name = "reported_at")
    private Instant reportedAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "removed")
    private Boolean removed;
}
