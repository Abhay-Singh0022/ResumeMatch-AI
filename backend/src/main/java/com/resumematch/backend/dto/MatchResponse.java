package com.resumematch.backend.dto;

import java.util.List;

public class MatchResponse {
    private String candidateName;
    private int matchScore;
    private List<String> matchingSkills;
    private List<String> missingSkills;
    private List<String> filteredMeaninglessKeywords;
    private List<String> tailoredBulletPoints;

    // New Gemini-style Deep Insights Fields
    private String aiSummary;
    private List<String> keyStrengths;
    private List<String> areasOfImprovement;

    public MatchResponse(String candidateName, int matchScore, List<String> matchingSkills,
                         List<String> missingSkills, List<String> filteredMeaninglessKeywords,
                         List<String> tailoredBulletPoints, String aiSummary,
                         List<String> keyStrengths, List<String> areasOfImprovement) {
        this.candidateName = candidateName;
        this.matchScore = matchScore;
        this.matchingSkills = matchingSkills;
        this.missingSkills = missingSkills;
        this.filteredMeaninglessKeywords = filteredMeaninglessKeywords;
        this.tailoredBulletPoints = tailoredBulletPoints;
        this.aiSummary = aiSummary;
        this.keyStrengths = keyStrengths;
        this.areasOfImprovement = areasOfImprovement;
    }

    // Getters and Setters
    public String getCandidateName() { return candidateName; }
    public int getMatchScore() { return matchScore; }
    public List<String> getMatchingSkills() { return matchingSkills; }
    public List<String> getMissingSkills() { return missingSkills; }
    public List<String> getFilteredMeaninglessKeywords() { return filteredMeaninglessKeywords; }
    public List<String> getTailoredBulletPoints() { return tailoredBulletPoints; }
    public String getAiSummary() { return aiSummary; }
    public List<String> getKeyStrengths() { return keyStrengths; }
    public List<String> getAreasOfImprovement() { return areasOfImprovement; }
}