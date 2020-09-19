package com.ek.signaling.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class Team {
    @Id
    @NonNull
    private String id;
}
