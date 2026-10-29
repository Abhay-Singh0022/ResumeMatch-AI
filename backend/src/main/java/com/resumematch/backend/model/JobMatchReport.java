package com.resumematch.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "match_reports")
public class JobMatchReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String candidateName;
    private int matchScore;

    @ElementCollection
    private List<String> matchingSkills;

    @ElementCollection
    private List<String> missingSkills;

    @ElementCollection
    private List<String> filteredMeaninglessKeywords;

    @ElementCollection
    @Column(length = 1000)
    private List<String> tailoredBulletPoints;

    @Column(length = 2000)
    private String aiSummary;

    @ElementCollection
    private List<String> keyStrengths;

    @ElementCollection
    private List<String> areasOfImprovement;

    private LocalDateTime createdAt;

    public JobMatchReport() {
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCandidateName() { return candidateName; }
    public void setCandidateName(String candidateName) { this.candidateName = candidateName; }

    public int getMatchScore() { return matchScore; }
    public void setMatchScore(int matchScore) { this.matchScore = matchScore; }

    public List<String> getMatchingSkills() { return matchingSkills; }
    public void setMatchingSkills(List<String> matchingSkills) { this.matchingSkills = matchingSkills; }

    public List<String> getMissingSkills() { return missingSkills; }
    public void setMissingSkills(List<String> missingSkills) { this.missingSkills = missingSkills; }

    public List<String> getFilteredMeaninglessKeywords() { return filteredMeaninglessKeywords; }
    public void setFilteredMeaninglessKeywords(List<String> filteredMeaninglessKeywords) { this.filteredMeaninglessKeywords = filteredMeaninglessKeywords; }

    public List<String> getTailoredBulletPoints() { return tailoredBulletPoints; }
    public void setTailoredBulletPoints(List<String> tailoredBulletPoints) { this.tailoredBulletPoints = tailoredBulletPoints; }

    public String getAiSummary() { return aiSummary; }
    public void setAiSummary(String aiSummary) { this.aiSummary = aiSummary; }

    public List<String> getKeyStrengths() { return keyStrengths; }
    public void setKeyStrengths(List<String> keyStrengths) { this.keyStrengths = keyStrengths; }

    public List<String> getAreasOfImprovement() { return areasOfImprovement; }
    public void setAreasOfImprovement(List<String> areasOfImprovement) { this.areasOfImprovement = areasOfImprovement; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}