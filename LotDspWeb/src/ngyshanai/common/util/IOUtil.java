package ngyshanai.common.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import jcifs.smb.SmbFileOutputStream;

import org.apache.commons.io.FileUtils;

/**
 * @author Leang-Say
 * Dependencies: jcifs-1.3.18.jar
 */
public class IOUtil {

	private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

	// Read
	// ------------------------------------------------------------------------------
	public static byte[] read(String filePath) {
		return toByte(filePath);
	}

	public static byte[] read(File file) {
		return toByte(file);
	}

	public static BufferedReader read(File file, String encoding) throws IOException {
		if (file == null) {
			throw new IOException("file is null.");
		}
		if (hasValue(encoding)) {
			return new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding));
		} else {
			return new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		}
	}
	
	public static String readAsString(String filePath) {
		return new String(toByte(filePath));
	}
	

	public static List<String> readAsStringList(File file, String encoding) throws IOException {
		List<String> dataList = new ArrayList<String>();
		try {
			BufferedReader br = read(file, encoding);
			String str = br.readLine();
			while (str != null) {
				str = br.readLine();
				dataList.add(str);
			}
			br.close();
		} catch (FileNotFoundException e) {
			throw new IOException(e);
		} catch (IOException e) {
			throw new IOException(e);
		}

		return dataList;
	}

	public static InputStream readAsInputStream(String filePath) throws FileNotFoundException {
		return readAsInputStream(new File(filePath));
	}

	public static InputStream readAsInputStream(File file) throws FileNotFoundException {
		return new FileInputStream(file);
	}

	public static byte[] readSmbFile(String smbUrl) throws MalformedURLException, SmbException, UnknownHostException {
		return toByte(readSmbFileAsInputStream(smbUrl));
	}

	public static InputStream readSmbFileAsInputStream(String smbUrl)
			throws MalformedURLException, SmbException, UnknownHostException {
		jcifs.Config.setProperty("jcifs.resolveOrder", "DNS");
		return new SmbFileInputStream(new SmbFile(smbUrl));
	}

	// Write
	// -------------------------------------------------------------------------------
	public static void write(byte[] bytes, String outputFilePath) throws IOException {
		writeFile(bytes, outputFilePath);
	}

	public static void writeFile(byte[] bytes, String outputFilePath) throws IOException {
		mkdirs(outputFilePath);

		OutputStream out = null;
		try {
			out = new FileOutputStream(outputFilePath);
			out.write(bytes);
			out.flush();
		} catch (IOException x) {
			throw x;
		} finally {
			closeQuietly(out);
		}
	}

	public static void writeSmbFile(byte[] bytes, String url) throws IOException {
		jcifs.Config.setProperty("jcifs.resolveOrder", "DNS");
		SmbFileOutputStream out = null;
		try {
			out = new SmbFileOutputStream(url);
			out.write(bytes);
			out.flush();
		} catch (IOException x) {
			throw x;
		} finally {
			closeQuietly(out);
		}
	}

	public static void writeMessage(File file, String message) throws IOException {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(file);
			out.write((message).getBytes());
			out.flush();
		} finally {
			closeQuietly(out);
		}
	}

	// Convert
	// ----------------------------------------------------------------------
	public static byte[] toByte(String filePath) {
		return toByte(new File(filePath));
	}

	public static byte[] toByte(File file) {
		FileInputStream fis = null;
		byte[] bytes = null;
		try {
			fis = new FileInputStream(file);

			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] buf = new byte[DEFAULT_BUFFER_SIZE];
			try {
				for (int readNum; (readNum = fis.read(buf)) != -1;) {
					bos.write(buf, 0, readNum);
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			bytes = bos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			closeQuietly(fis);
		}
		return bytes;
	}

	public static byte[] toByte(InputStream is) {
		byte[] bytes = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buf = new byte[DEFAULT_BUFFER_SIZE];
		try {
			for (int readNum; (readNum = is.read(buf)) != -1;) {
				bos.write(buf, 0, readNum);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		bytes = bos.toByteArray();
		return bytes;
	}

	public static boolean mkdirs(String filrOrDirPath) {
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

	public static void closeQuietly(OutputStream os) {
		try {
			if (os != null) {
				os.close();
				os = null;
			}
		} catch (IOException ioe) {
			// ignore
		}
	}

	public static void closeQuietly(InputStream is) {
		try {
			if (is != null) {
				is.close();
				is = null;
			}
		} catch (IOException ioe) {
			// ignore
		}
	}

	// Other
	// -------------------------------------------------------------------------------

	public static boolean existSmbFile(String smbUrl)
			throws MalformedURLException, SmbException, UnknownHostException {
		jcifs.Config.setProperty("jcifs.resolveOrder", "DNS");
		SmbFile smbFile = new SmbFile(smbUrl);
		return smbFile.exists();
	}


	public static void delete(String... fileOrDirs) {
		try {
			for (String fd : fileOrDirs) {
				File file = new File(fd);
				if (file.isFile()) {
					FileUtils.forceDelete(file);
				} else {
					FileUtils.deleteDirectory(file.getParentFile());
				}
			}
		} catch (IOException e) {
		}
	}

	// Private
	// -------------------------------------------------------------
	private static boolean hasValue(String val) {
		return (val != null && !val.trim().equals(""));
	}

}
