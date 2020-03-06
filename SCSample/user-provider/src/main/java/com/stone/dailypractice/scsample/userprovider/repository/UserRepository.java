package com.stone.dailypractice.scsample.userprovider.repository;

import com.stone.dailypractice.scsample.userprovider.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Long> {
}
