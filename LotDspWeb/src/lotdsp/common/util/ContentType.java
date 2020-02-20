package lotdsp.common.util;

/**
 * @author Leang-Say
 *
 * @see http://www.tagindex.com/html5/basic/mimetype.html
 */
public enum ContentType {

	TEXT("text/plain",".txt","テキスト"),
	HTM("text/html",".htm","HTML文書"),
	HTML("text/html",".html","HTML文書"),
	HTML_SHIFT_JIS("text/html; charset=Shift_JIS",".html","HTML文書"),

	XHTML("application/xhtml+xml",".xhtml","XHTML"),
	XML("text/xml",".xml","XML文書"),
	RSS("application/rss+xml",".rss","RSS"),


	CSS("text/css",".css","CSS"),
	JS("text/javascript",".js","JavaScript"),
	VBS("text/vbscript",".vbs","VBScript"),
	CGI("application/x-httpd-cgi",".cgi","CGIスクリプト"),

	GIF("image/gif",".gif","GIF画像"),
	JPG("image/jpeg",".jpg","JPEG画像"),
	JPEG("image/jpeg",".jpeg","JPEG画像"),
	PNG("image/png",".png","PNG画像"),
	ICON("image/vnd.microsoft.icon",".ico","アイコン"),

	WORD("application/msword",".doc","Word文書"),
	EXCEL("application/msexcel",".xls","Excel"),
	ZIP("application/zip",".zip","ZIP"),
	PDF("application/pdf",".pdf","PDF文書"),

	ACC_VOICE("audio/aac",".aac","AAC音声"),
	MP4_VOICE("audio/mp4",".mp4","MP4音声"),
	M4A_VOICE("audio/mp4",".m4a","MP4音声"),
	MP1_VOICE("audio/mpeg",".mp1","MPEG音声"),
	MP2_VOICE("audio/mpeg",".mp2","MPEG音声"),
	MP3_VOICE("audio/mpeg",".mp3","MPEG音声"),
	MPG_VOICE("audio/mpeg",".mpg","MPEG音声"),
	MPEG_VOICE("audio/mpeg",".mpeg","MPEG音声"),
	OGG_VOICE("audio/ogg",".ogg","OGG音声"),
	OGA_VOICE("audio/ogg",".oga","OGG音声"),
	WAV_VOICE("audio/wav",".wav","WAV音声"),
	WEBM_VOICE("audio/webm",".webm","WebM音声"),


	MP4_VIDEO("video/mp4",".mp4","MP4ビデオ"),
	M4V_VIDEO("video/mp4",".m4v","MP4ビデオ"),
	OGG_VIDEO("video/ogg",".ogv","OGGビデオ"),
	WEBM_VIDEO("video/webm",".webm","WebMビデオ"),


	// クライアントコンピューターで、［プリンター選択］ダイアログからプリンターを選択して印刷する場合は、次のいずれかのContent-Typeを使用します。
	// WDD = Web Direct Dialog
	// SWDD = Svf Web Direct Dialog
	WDD("application/x-web-direct-dlg",".wdd","Web直接ダイアログ"),
	SWDD("x-svfweb-direct-dlg",".swdd","SvfWeb直接ダイアログ"),

	// 通常使うプリンターに印刷する場合は、次のいずれかのContent-Typeを使用します。
	// WDP = Web Direct Print
	// SWDP = Svf Web Direct Print
	WDP("application/x-web-direct-print",".wdp","Web直接印刷"),
	SWDP("application/x-svfweb-direct-print",".swdp","SvfWeb直接印刷")

	;

	private String mimeType;
	private String ext;
	private String fileFormat;

	ContentType(String mimeType,String ext,String fileFormat){
		this.mimeType=mimeType;
		this.ext=ext;
		this.fileFormat=fileFormat;
	}

	public String getMimeType() {
		return mimeType;
	}

	public String getExt() {
		return ext;
	}

	public String getFileFormat() {
		return fileFormat;
	}

	public String getContentType(){
		return mimeType;
	}

	public static String contentTypeOf(String fileName){
		if(fileName==null || "".equals(fileName)){
			return null;
		}

		String[] strArr = fileName.split("\\.");
		if(strArr==null || strArr.length!=2){
			return null;
		}

		for(ContentType v:values()){
			if(v.ext.endsWith(strArr[1].toLowerCase())){
				return v.mimeType;
			}
		}
		return null;
	}

}
