package com.lyaotian.example.repository;

import com.lyaotian.example.pojo.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User: Yaotian Leung
 * Date: 7/29/13
 * Time: 8:58 PM
 */
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
}
