package com.example.s3asyncexamples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@SpringBootApplication
@RestController
@EnableAsync
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	S3Service service;

	public DemoApplication(S3Service service) {
		this.service = service;
	}

	@GetMapping("/download")
	public void download(@RequestParam("bucket") String bucketName, @RequestParam("prefix") String prefix, HttpServletResponse response) throws Exception {
		List<String> objectKeys = service.listObjectKeys(bucketName, prefix);

		List<S3Content> s3Contents = new ArrayList<>();
		List<CompletableFuture<S3Content>> processes = new ArrayList<>();
		for (String objectKey : objectKeys) {
			CompletableFuture<S3Content> process = service.fetchAsync(bucketName, objectKey).whenCompleteAsync((res, e) -> s3Contents.add(res));
			processes.add(process);
		}
		CompletableFuture.allOf(processes.toArray(new CompletableFuture[objectKeys.size()])).join();

		String basename = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + basename + ".zip");
		response.setStatus(HttpServletResponse.SC_OK);

		try (ZipOutputStream out = new ZipOutputStream(response.getOutputStream())) {
			for (S3Content content : s3Contents) {
				out.putNextEntry(new ZipEntry(Paths.get(basename, content.filename()).toString()));
				try (InputStream in = new ByteArrayInputStream(content.data())) {
					byte[] buf = new byte[4096];
					int len;
					while ((len = in.read(buf)) > 0) {
						out.write(buf, 0, len);
					}
				}
			}
		}
	}

}
