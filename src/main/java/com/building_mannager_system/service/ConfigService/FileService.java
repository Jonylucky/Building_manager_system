package com.building_mannager_system.service.ConfigService;


import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    // Method to save the file and handle conflicts
    public String saveFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Uploaded file is empty");
        }

        // Ensure the directory exists
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath); // Create directory if not exists
        }

        // Get the original file name and validate it's a PDF
        String originalFileName = file.getOriginalFilename();
        if (originalFileName == null) {
            throw new IllegalArgumentException("File name cannot be null");
        }

        // Validate file type (PDF only)
        if (!originalFileName.endsWith(".pdf")) {
            throw new IllegalArgumentException("Only PDF files are allowed.");
        }

        // Generate a unique file name to avoid overwriting existing files
        String uniqueFileName = generateUniqueFileName(originalFileName, uploadPath);

        // Save the file to the destination directory
        Path destinationPath = uploadPath.resolve(uniqueFileName);
        file.transferTo(destinationPath);

        return uniqueFileName; // Return the unique file name
    }

    // Helper method to handle file name conflicts by appending a number
    private String generateUniqueFileName(String originalFileName, Path directory) {
        String fileName = originalFileName;
        File destinationFile = directory.resolve(fileName).toFile();
        int counter = 1;

        // Check for file name conflicts and generate a unique file name
        while (destinationFile.exists()) {
            String nameWithoutExtension = fileName.substring(0, fileName.lastIndexOf('.'));
            String extension = fileName.substring(fileName.lastIndexOf('.'));
            fileName = nameWithoutExtension + "_" + counter++ + extension;
            destinationFile = directory.resolve(fileName).toFile();
        }

        return fileName;
    }
}

