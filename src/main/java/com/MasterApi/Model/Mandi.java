package com.MasterApi.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Mandi")
@Getter
@Setter
@NoArgsConstructor

public class Mandi {

    @Id
    private int id;
    @Column(name = "mandi_name")
    private String mandiName;
    private String tehsil;

    public Object startsWith(String string) {
        return null;
    }
}
