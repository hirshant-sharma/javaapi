package com.MasterApi.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MasterApi.Model.Crop;

@Repository
public interface CropRepo extends JpaRepository<Crop, Integer> {

}
