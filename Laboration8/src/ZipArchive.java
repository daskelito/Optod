import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class ZipArchive extends Thread {
	private File file;
	private String archive;
	
	public ZipArchive(String fileOrDir) {
		this(new File(fileOrDir));
	}
	
	public ZipArchive(File file) {
		this.file = file;
	}
	
	private String getFileName(String filename) {
		int index = filename.indexOf('.');
		if(index>=0)
			return filename.substring(0,index);
		else
			return filename;
	}
	
	private void zip(File file, ZipOutputStream zos, String directories) throws IOException {
		if(file.isFile()) {
			System.out.println("ZIP: " + file.getAbsolutePath());
			zos.putNextEntry(new ZipEntry(directories+file.getName()));
			try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
				int b = bis.read();
				while(b!=-1) {
					zos.write(b);
					b = bis.read();
				}
				zos.flush();
			}
			zos.closeEntry();
		} else if(file.isDirectory()) {
			for(File f : file.listFiles()) {
				zip(f,zos,directories+file.getName()+"/");
			}
		}
	}
	
	public void run() {
		System.out.println("TO ZIP: " + file.getAbsolutePath());
		if(file.isDirectory()) {
			archive = file.getAbsolutePath()+".zip";
		} else if(file.isFile()) {
			archive = getFileName(file.getAbsolutePath())+".zip";
		} else {
			System.out.println("NOT directory or file: " + file.getAbsolutePath());
			return;
		}
		
		try (ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(archive)))){
			zip(file, zos, "");
//			zip(file, zos, null);
		}catch(Exception e) {
			System.out.println("EXEPTION: " + e.getMessage());
			return;
		}
		System.out.println("ZIP-FILE: " + archive);
	}
}
