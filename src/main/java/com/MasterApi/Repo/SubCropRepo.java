package com.MasterApi.Repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MasterApi.Model.Crop;
import com.MasterApi.Model.SubCrop;

@Repository
public interface SubCropRepo extends JpaRepository<SubCrop, Integer> {

    List<SubCrop> findByCrop(Crop crop);

    Page<SubCrop> findByTitleContaining(String title, Pageable p);

}
