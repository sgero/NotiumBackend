package com.example.notiumb.repository;

import com.example.notiumb.model.Rpp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRppRepository extends JpaRepository <Rpp, Integer> {
}
