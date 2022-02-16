package com.example.twintdockerdb.Repository;

import com.example.twintdockerdb.Models.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITweetRepository  extends JpaRepository<Tweet, Long> {
    List<Tweet> findAll();
}
