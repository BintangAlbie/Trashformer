package com.example.trashformer.dto.response;

public record ExportedReport(
        String fileName,
        String contentType,
        byte[] content
) {
}
