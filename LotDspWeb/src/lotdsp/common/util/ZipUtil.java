package lotdsp.common.util;



import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;

import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

/**
 * Zip/Unzip Util Class
 * ※apache.commons.compress,apache.commons.ioが必須
 * @author Leang-Say
 */
public class ZipUtil {

	private final static String DEFAULT_ENCODING = "Windows-31J";

	public static void zip(String dirPath, String zipFilePath) throws IOException, ArchiveException {
		zip(new File(dirPath), new File(zipFilePath));
	}

	public static void zip(File dir, File zipFile) throws IOException, ArchiveException {
		zip(dir, zipFile, null);
	}

	public static void zip(File dir, File zipFile, String encoding) throws IOException, ArchiveException {
		mkdirs(zipFile.getPath());
		appendToZip(dir, zipFile, encoding);
	}

	private static void appendToZip(File source, File destination, String encoding)
			throws IOException, ArchiveException {
		OutputStream archiveStream = new FileOutputStream(destination);
		ZipArchiveOutputStream archive = new ZipArchiveOutputStream(archiveStream);

		if (encoding != null) {
			archive.setEncoding(encoding);
		} else {
			archive.setEncoding(DEFAULT_ENCODING);
		}

		Collection<File> fileList = FileUtils.listFiles(source, null, true);

		for (File file : fileList) {
			String entryName = getEntryName(source, file);
			ZipArchiveEntry entry = new ZipArchiveEntry(entryName);
			archive.putArchiveEntry(entry);

			BufferedInputStream input = new BufferedInputStream(new FileInputStream(file));

			IOUtils.copy(input, archive);
			input.close();
			archive.closeArchiveEntry();
		}

		archive.finish();
		archiveStream.close();
	}

	private static String getEntryName(File source, File file) throws IOException {
		int index = source.getAbsolutePath().length() + 1;
		String path = file.getCanonicalPath();
		return path.substring(index);
	}
	
	public static void unzip(String zipFilePath, String baseDirPath) throws IOException {
		unzip(new File(zipFilePath), new File(baseDirPath));
	}
	
	public static void unzip(File zipFile, File baseDir) throws IOException {
		unzip(zipFile, baseDir, DEFAULT_ENCODING);
	}

	public static void unzip(File zipFile, File baseDir, String encording) throws IOException {
		if (baseDir == null) {
			throw new NullPointerException("baseDir is null");
		}

		if (baseDir.exists() && !baseDir.isDirectory()) {
			throw new NullPointerException("[" + baseDir + "] is not directory");
		}

		String baseDirPath = FilenameUtils.concat(baseDir.getAbsolutePath(),
				FilenameUtils.getBaseName(zipFile.getName()));
		if (!baseDirPath.endsWith("/")) {
			baseDirPath = baseDirPath.concat("/");
		}
		FileUtils.forceMkdir(new File(baseDirPath));

		final InputStream is = new FileInputStream(zipFile);

		ZipArchiveInputStream archive;
		if (encording == null) {
			archive = new ZipArchiveInputStream(is);
		} else {
			archive = new ZipArchiveInputStream(is, encording, true);
		}

		ZipArchiveEntry entry;
		while ((entry = archive.getNextZipEntry()) != null) {
			File file = new File(baseDirPath + entry.getName());
			if (entry.isDirectory()) {
				file.mkdirs();
			} else {
				if (!file.getParentFile().exists()) {
					file.getParentFile().mkdirs();
				}
				OutputStream out = new FileOutputStream(file);
				IOUtils.copy(archive, out);
				out.close();
			}
		}

		archive.close();
	}

	private static boolean mkdirs(String filrOrDirPath) {
		if (filrOrDirPath == null) {
			return false;
		}
		File file = new File(filrOrDirPath);
		if (file.exists()) {
			return true;
		}
		if (filrOrDirPath.indexOf('.') > -1) {
			File dir = file.getParentFile();
			if (!dir.exists()) {
				return dir.mkdirs();
			}
		} else {
			return file.mkdirs();
		}
		return false;
	}
}
