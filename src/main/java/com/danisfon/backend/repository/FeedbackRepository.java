package com.danisfon.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danisfon.backend.model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

}
