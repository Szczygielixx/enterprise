package lab.dto;

// This is a copy of the DTO from the Server module.
// In a real project, this might be in a shared module.

import java.time.LocalDate;

public class ComplaintDTO {

    private Long id;
    private LocalDate complaintDate;
    private String complaintText;
    private String author;
    private String status;

    // Getters and setters are needed for JSON deserialization by Jackson/Moxy

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getComplaintDate() {
        return complaintDate;
    }

    public void setComplaintDate(LocalDate complaintDate) {
        this.complaintDate = complaintDate;
    }

    public String getComplaintText() {
        return complaintText;
    }

    public void setComplaintText(String complaintText) {
        this.complaintText = complaintText;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ComplaintDTO{" +
                "id=" + id +
                ", complaintDate=" + complaintDate +
                ", complaintText='" + complaintText + '\'' +
                ", author='" + author + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
} 