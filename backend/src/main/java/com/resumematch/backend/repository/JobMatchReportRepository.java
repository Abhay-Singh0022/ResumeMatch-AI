package com.resumematch.backend.repository;

import com.resumematch.backend.model.JobMatchReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobMatchReportRepository extends JpaRepository<JobMatchReport, Long> {
}