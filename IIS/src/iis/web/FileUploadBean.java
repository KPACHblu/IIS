package iis.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

//TODO SessionScoped>>>RequestScoped
@ManagedBean(name = "fileUploadBean")
@SessionScoped
public class FileUploadBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 8999146019489986906L;

	public static final String FILE_PATH = "/resources/uploadedFiles/";
	public static final String SITE_PREFIX = "/IIS";
	private UploadedFile file;
	private String selectedImage;

	private void handleFileUpload(UploadedFile uploadedFile) {
		try {
			ReadableByteChannel rbc = Channels.newChannel(uploadedFile
					.getInputstream());
			String targetPath = getRealPath(FILE_PATH
					+ uploadedFile.getFileName());
			FileOutputStream fos = new FileOutputStream(targetPath);
			FileChannel channel = fos.getChannel();
			channel.transferFrom(rbc, 0L, 0x1000000L);
			channel.close();
			addMessage(FacesMessage.SEVERITY_INFO, "Файл успешно загружен", "");
		} catch (Exception ex) {
			ex.printStackTrace();
			addMessage(FacesMessage.SEVERITY_ERROR,
					"Возникла ошибка при загрузке файла", "");
		}
	}

	public List<iis.web.util.File> getUploadedFiles() {
		File imageDir = new File(
				getRealPath(FILE_PATH));
		List<iis.web.util.File> fileList = new ArrayList<iis.web.util.File>();
		if (imageDir.listFiles() != null) {
			File arr$[] = imageDir.listFiles();
			int len$ = arr$.length;
			for (int i$ = 0; i$ < len$; i$++) {
				File imageFile = arr$[i$];
				if (!imageFile.isDirectory())
					fileList.add(new iis.web.util.File(imageFile.getName(), SITE_PREFIX + FILE_PATH + imageFile.getName()));
			}

		}
		return fileList;
	}

	public void handleFileUpload(FileUploadEvent event) {
		try {
			UploadedFile uploadedFile = event.getFile();
			java.nio.channels.ReadableByteChannel rbc = Channels
					.newChannel(uploadedFile.getInputstream());
			String targetPath = getRealPath(FILE_PATH+uploadedFile.getFileName());
			FileOutputStream fos = new FileOutputStream(targetPath);
			FileChannel channel = fos.getChannel();
			channel.transferFrom(rbc, 0L, 0x1000000L);
			channel.close();
			addMessage(FacesMessage.SEVERITY_INFO, "Файл успешно загружен", "");
		} catch (Exception ex) {
			addMessage(FacesMessage.SEVERITY_ERROR,
					"Возникла ошибка при загрузке файла", "");
		}
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
		handleFileUpload(file);
	}

	public String getSelectedImage() {
		return selectedImage;
	}

	public void setSelectedImage(String selectedImage) {
		this.selectedImage = selectedImage;
	}

}