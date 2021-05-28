package com.dj.webservices.myrestwebservice.repository;

import com.dj.webservices.myrestwebservice.bean.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
}
