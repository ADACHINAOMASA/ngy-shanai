package nis.framework.svf;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import jp.co.fit.vfreport.Svf;

/**
 * svfライター
 */
public interface SvfWriter {

	public void write(Svf svf, String name, String value) throws IOException;
	public void write(Svf svf, String name, BigDecimal value) throws IOException;
	public void write(Svf svf, String name, Date value) throws IOException;
	public void write(Svf svf, String name, Object value) throws IOException;

}
