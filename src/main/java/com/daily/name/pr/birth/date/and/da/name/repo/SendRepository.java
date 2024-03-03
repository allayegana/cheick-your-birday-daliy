package com.daily.name.pr.birth.date.and.da.name.repo;

import com.daily.name.pr.birth.date.and.da.name.model.BirtEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SendRepository extends JpaRepository<BirtEntity, Integer> {
//
//    @Query(value = "SELECT * FROM  DAYOFWEEK('jour')", nativeQuery = true)
//    List<BirtEntity> findAll();
}
