package fi.haagahelia.buffetreview.filehandling;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class FileModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long fileId;
	private String fileName, mimeType;

	@Lob
	private byte[] file;

	public FileModel(String fileName, String mimeType, byte[] file) {
		this.fileName = fileName;
		this.mimeType = mimeType;
		this.file = file;
	}

	public FileModel() {

	}

	public long getFileId() {
		return fileId;
	}

	public void setFileId(long fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

}
