package net.javaguide.springboot.upload;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
	public static String saveFile(String fileName, MultipartFile multipartFile, String fileUploadPath)
			throws IOException {
		Path uploadPath = Paths.get(fileUploadPath);

		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		LocalDateTime LocalDt = LocalDateTime.now();
		String fileCode = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss").format(LocalDt);

		try (InputStream inputStream = multipartFile.getInputStream()) {
			Path filePath = uploadPath.resolve(fileCode + "-" + fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException ioe) {
			throw new IOException("Could not save file: " + fileName, ioe);
		}

		return fileCode;
	}
}
