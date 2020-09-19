package com.ek.signaling.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class Member {

    @Id
    @NonNull
    private String id;

    @ManyToOne
    @JoinColumn
    @Setter
    private Team team;

    @NonNull
    private String userName;
}
