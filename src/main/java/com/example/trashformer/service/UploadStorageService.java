package com.example.trashformer.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class UploadStorageService {

    private final Path uploadRoot;

    public UploadStorageService(@Value("${app.upload.dir:uploads}") String uploadDir) {
        this.uploadRoot = Paths.get(uploadDir).toAbsolutePath().normalize();
    }

    public String store(MultipartFile file, String folder) {
        if (file == null || file.isEmpty()) {
            return null;
        }

        try {
            Path targetFolder = uploadRoot.resolve(folder).normalize();
            Files.createDirectories(targetFolder);

            String cleanName = StringUtils.cleanPath(file.getOriginalFilename());
            String extension = "";
            int extensionIndex = cleanName.lastIndexOf('.');
            if (extensionIndex >= 0) {
                extension = cleanName.substring(extensionIndex);
            }

            String storedName = UUID.randomUUID() + extension;
            Path storedPath = targetFolder.resolve(storedName);
            Files.copy(file.getInputStream(), storedPath, StandardCopyOption.REPLACE_EXISTING);

            return "/uploads/" + folder + "/" + storedName;
        } catch (IOException exception) {
            throw new UncheckedIOException("Gagal menyimpan file upload.", exception);
        }
    }
}
