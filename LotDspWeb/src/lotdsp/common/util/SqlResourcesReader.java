package lotdsp.common.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * Sqlリソースを読み込むツール
 */
public class SqlResourcesReader {

	private final String SQL_EXT = ".sql";

	private String pkg;

	private List<String> params;

	private List<String> jokens;

	public SqlResourcesReader() {
	}

	public SqlResourcesReader(String pkg) {
		this.pkg = pkg;
	}

	public SqlResourcesReader(Class<?> clazz) {
		setPackage(clazz);
	}

	/**
	 * クラスからパッケージの設定
	 */
	public void setPackage(Class<?> clazz) {
		pkg = "/" + clazz.getPackage().getName().replace(".", "/") + "/";
	}

	/**
	 * パラメータの設定
	 */
	public void setParams(String... params) {
		this.params = Arrays.asList(params);
	}

	/**
	 * パラメータの設定
	 */
	public void setParams(List<String> params) {
		this.params = params;
	}

	/**
	 * パラメータの追加
	 */
	public void addParam(String param) {
		if (params == null) {
			params = new ArrayList<String>();
		}
		params.add(param);
	}

	/**
	 * 条件の設定
	 */
	public void setJokens(String... jokens) {
		this.jokens = Arrays.asList(jokens);
	}

	/**
	 * 条件の設定
	 */
	public void setJokens(List<String> jokens) {
		this.jokens = jokens;
	}

	/**
	 * 条件の追加
	 */
	public void addJoken(String joken) {
		if (jokens == null) {
			jokens = new ArrayList<String>();
		}
		jokens.add(joken);
	}

	private String readAsString(String sqlResources) throws IOException {
		InputStream is = null;
		
		if (sqlResources.contains(":")) {
			is = new FileInputStream(sqlResources);
		} else {
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream(sqlResources);
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		br.close();

		return sb.toString();
	}

	private boolean hasValue(List<?> obj) {
		if (obj == null || obj.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * リソースからSQLの取得
	 */
	public String getSql(String sqlResources) {
		String path = pkg + sqlResources + SQL_EXT;

		try {
			String sql = readAsString(path);

			if (hasValue(params)) {
				sql = paramChikan(sql);
			}
			if (hasValue(jokens)) {
				sql = jokenSettei(sql);
			}
			return sql;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * パラメータの置換
	 */
	private String paramChikan(String str) {
		for (int i = 0; i < params.size(); i++) {
			str = str.replaceAll("\\{" + i + "\\}", StringUtils.defaultString(params.get(i)));
		}
		return str;
	}

	/**
	 * 条件の設定
	 * 
	 * 指定条件に該当しない条件を省いてSQLを再生成するイメージ
	 * 
	 */
	private String jokenSettei(String str) {
		StringBuffer sb = new StringBuffer();
		Pattern pattern = Pattern.compile("--if.*?--end-if", Pattern.DOTALL);
		Matcher macher = pattern.matcher(str);
		int pos = 0;
		while (macher.find()) {
			sb.append(str.substring(pos, macher.start()));
			boolean hit = false;
			for (int i = 0; i < jokens.size(); i++) {
				if (macher.group().startsWith(jokens.get(i), 5)) {
					hit = true;
					break;
				}
			}
			if (hit) {
				sb.append(str.substring(macher.start(), macher.end()));
			}
			pos = macher.end();
		}
		sb.append(str.substring(pos));
		return sb.toString();
	}
}
