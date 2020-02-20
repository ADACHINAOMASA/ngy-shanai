package nis.framework.type;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import nis.framework.util.DateUtil;
import nis.framework.web.json.NengetsuDeserializer;
import nis.framework.web.json.NengetsuSerializer;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 年月クラス
 * 年月に対応し、日時分秒を持たない
 * （内部的にはdateで持つがセット時に時分秒を消し、日は1日で固定）
 * Jsサイドでは普通にdateとして扱われる
 * @author Kengo-Nagase
 *
 */
@SuppressWarnings("serial")
@JsonSerialize(using = NengetsuSerializer.class)
@JsonDeserialize(using = NengetsuDeserializer.class)
public class Nengetsu implements Serializable {

	private Date date;

	public Nengetsu(){}

	public Nengetsu(Date date) {
		set(date);
	}

	public Nengetsu(String yyyyMM) {
		set(yyyyMM);
	}

	private void set(String yyyyMM) {
		if (StringUtils.isEmpty(yyyyMM)) {
			date = null;
			return;
		}
		try {
			date = DateUtils.parseDate(yyyyMM, new String[]{"yyyyMM", "yyyy/MM", "yyyy-MM"});
		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public void set(Date date) {
		if (date == null) {
			this.date = date;
			return;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		this.date = cal.getTime();
	}

	public Date date() {
		if (isNull()) {
			throw new NullPointerException();
		}
		return date;
	}

	public String string() {
		return string("yyyyMM");
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
		return new SimpleDateFormat("yyyy/MM").format(date);
	}

}
