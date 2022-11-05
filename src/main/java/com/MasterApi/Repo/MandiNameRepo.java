package com.MasterApi.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MasterApi.Model.Mandi;

@Repository
public interface MandiNameRepo extends JpaRepository<Mandi, Integer> {
    List<Mandi> findByTehsilContaining(String tehsil);
}
