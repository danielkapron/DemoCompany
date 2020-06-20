package demo.company.zamkniecie;

import java.time.LocalDateTime;

public class ZamkniecieKontaResponse {
    private ZamkniecieKontaStatus status;
    private LocalDateTime processingDate;

    public ZamkniecieKontaResponse(ZamkniecieKontaStatus status, LocalDateTime processingDate) {
        this.status = status;
        this.processingDate = processingDate;
    }

    public ZamkniecieKontaStatus getStatus() {
        return status;
    }

    public void setStatus(ZamkniecieKontaStatus status) {
        this.status = status;
    }

    public LocalDateTime getProcessingDate() {
        return processingDate;
    }

    public void setProcessingDate(LocalDateTime processingDate) {
        this.processingDate = processingDate;
    }
}
