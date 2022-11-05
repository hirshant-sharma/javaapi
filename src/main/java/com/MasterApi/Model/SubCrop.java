package com.MasterApi.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sub_crop")
@Getter
@Setter
@NoArgsConstructor
public class SubCrop {
    @Id
    private int id;
    @Column(name = "sub_crop_grade")
    private String subCropGrade;
    private String title;

    @ManyToOne
    private Crop crop;
}
