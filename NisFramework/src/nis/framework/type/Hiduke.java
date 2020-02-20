package nis.framework.type;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import nis.framework.util.DateUtil;
import nis.framework.web.json.HidukeDeserializer;
import nis.framework.web.json.HidukeSerializer;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 日付クラス
 * 年月日に対応し、時分秒を持たない
 * （内部的にはdateで持つがセット時に時間以下を消す）
 * Jsサイドでは普通にdateとして扱われる
 * @author Kengo-Nagase
 *
 */
@SuppressWarnings("serial")
@JsonSerialize(using = HidukeSerializer.class)
@JsonDeserialize(using = HidukeDeserializer.class)
public class Hiduke implements Serializable {

	private Date date;

	public Hiduke(){}

	public Hiduke(Date date) {
		set(date);
	}

	public Hiduke(String yyyyMMdd) {
		set(yyyyMMdd);
	}

	private void set(String yyyyMMdd) {
		if (StringUtils.isEmpty(yyyyMMdd)) {
			date = null;
			return;
		}
		try {
			date = DateUtils.parseDate(yyyyMMdd, new String[]{"yyyyMMdd", "yyyy/MM/dd", "yyyy-MM-dd"});
		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public void set(Date date) {
		if (date == null) {
			this.date = date;
			return;
		}
		this.date = DateUtil.clearHMS(date);
	}

	public Date date() {
		if (isNull()) {
			throw new NullPointerException();
		}
		return date;
	}

	public String string() {
		return string("yyyyMMdd");
	}

	public String string(String format) {
		if (isNull()) {
			throw new NullPointerException();
		}
		return DateUtil.format(date, format);
	}

	public boolean isNull() {
		return date == null;
	}

	@Override
	public String toString() {
		if (isNull()) {
			return "null";
		}
		return new SimpleDateFormat("yyyy/MM/dd").format(date);
	}

}
