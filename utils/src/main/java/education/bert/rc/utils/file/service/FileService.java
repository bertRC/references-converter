package education.bert.rc.utils.file.service;

import education.bert.rc.utils.file.exception.FileAccessException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileService {

    private final Path uploadPath;

    public FileService(String uploadPath) {
        this.uploadPath = Paths.get(uploadPath);
        try {
            Files.createDirectories(this.uploadPath);
        } catch (IOException e) {
            throw new FileAccessException(e);
        }
    }

    public List<String> readFile(String fileName) {
        final Path file = uploadPath.resolve(fileName);
        try (Stream<String> lines = Files.lines(file, StandardCharsets.UTF_8)) {
            return lines.collect(Collectors.toList());
        } catch (IOException e) {
            throw new FileAccessException(e);
        }
    }
}
