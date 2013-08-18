package com.lyaotian.example.repository;

import com.lyaotian.example.pojo.Email;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User: Yaotian Leung
 * Date: 7/25/13
 * Time: 5:48 PM
 */
public interface EmailRepository extends JpaRepository<Email, Long> {
}
