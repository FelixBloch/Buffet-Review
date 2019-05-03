package fi.haagahelia.buffetreview.web;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import fi.haagahelia.buffetreview.domain.FileModel;
import fi.haagahelia.buffetreview.domain.FileModelRepository;

@Controller
public class FileController {

	@Autowired
	private FileModelRepository frepository;

	@Value("${upload.path}")
	private String uploadFolder;

	@PostMapping("/upload")
	public String fileUpload(@RequestParam("file") MultipartFile file, Model model) {
		if (file.isEmpty()) {
			model.addAttribute("msg", "Upload failed");
			return "uploadstatus";
		}

		try {
			FileModel fileModel = new FileModel(file.getOriginalFilename(), file.getContentType(), file.getBytes());

			frepository.save(fileModel);

			model.addAttribute("msg", "File " + file.getOriginalFilename() + " uploaded");

		} catch (IOException e) {
			e.printStackTrace();
		}
		return "uploadstatus";
	}

	@PostMapping("/showreview")
	public String fileList(Model model) {
		model.addAttribute("files", frepository.findAll());
		return "filelist";
	}

	@GetMapping("/file/{id}")
	public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
		Optional<FileModel> fileOptional = frepository.findById(id);
		if (fileOptional.isPresent()) {
			FileModel file = fileOptional.get();
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
					.body(file.getFile());
		}
		return ResponseEntity.status(404).body(null);
	}

}
