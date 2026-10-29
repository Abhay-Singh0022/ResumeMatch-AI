package com.resumematch.backend.controller;

import com.resumematch.backend.model.JobMatchReport;
import com.resumematch.backend.service.ResumeMatchService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/resume")
@CrossOrigin(origins = "*")
public class JobMatchController {

    @Autowired
    private ResumeMatchService resumeMatchService;

    @PostMapping("/match")
    public ResponseEntity<JobMatchReport> matchResume(
            @RequestParam("file") MultipartFile file,
            @RequestParam("jobDescription") String jobDescription) throws IOException {

        JobMatchReport report = resumeMatchService.analyzeAndSave(file, jobDescription);
        return ResponseEntity.ok(report);
    }

    @PostMapping("/batch-match")
    public ResponseEntity<List<JobMatchReport>> batchMatchResumes(
            @RequestParam("files") MultipartFile[] files,
            @RequestParam("jobDescription") String jobDescription) throws IOException {

        List<JobMatchReport> reports = new ArrayList<>();
        for (MultipartFile file : files) {
            reports.add(resumeMatchService.analyzeAndSave(file, jobDescription));
        }
        reports.sort((a, b) -> Integer.compare(b.getMatchScore(), a.getMatchScore()));
        return ResponseEntity.ok(reports);
    }

    @PostMapping("/export-pdf")
    public ResponseEntity<byte[]> exportPdfReport(@RequestBody JobMatchReport report) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 16);
                contentStream.newLineAtOffset(50, 750);
                contentStream.showText("ResumeMatch AI - Enterprise Audit Report");
                contentStream.endText();

                contentStream.beginText();
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);
                contentStream.newLineAtOffset(50, 710);
                contentStream.showText("Candidate File: " + (report.getCandidateName() != null ? report.getCandidateName() : "N/A"));
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Match Score: " + report.getMatchScore() + "%");
                contentStream.endText();
            }

            document.save(out);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "Analysis_Report.pdf");

        return ResponseEntity.ok().headers(headers).body(out.toByteArray());
    }
}