package com.MasterApi.Repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.MasterApi.Model.Mandi;

@Repository
public interface MandiRepo extends JpaRepository<Mandi, Integer> {

    Page<Mandi> findByTehsilContaining(String tehsil, Pageable p);
}
