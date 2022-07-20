package com.hotel.villa.entity;

import com.hotel.villa.enums.Status;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@MappedSuperclass

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity<T extends Serializable>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated entity id")
    @OrderBy
    private T id;

    @Column(name = "UUID")
    private String uid;

    @CreatedDate
    @Column(name = "created")
    private LocalDateTime created;

    @LastModifiedDate
    @Column(name = "updated")
    @OrderColumn(name = "email")
    private LocalDateTime updated;

    @Column(name = "deleted")
    private LocalDateTime deleted;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "valid_date")
    private LocalDateTime validDate;


}
