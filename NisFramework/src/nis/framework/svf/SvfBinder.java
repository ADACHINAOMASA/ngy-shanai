package nis.framework.svf;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import jp.co.fit.vfreport.Svf;



public class SvfBinder {

	private static Map<String, Map<String, PropertyDescriptor>> map = new HashMap<String, Map<String, PropertyDescriptor>>();
	private Svf svf;
	private SvfWriter writer;

	public SvfBinder(Svf svf, SvfWriter writer) {
		this.svf = svf;
		this.writer = writer;
	}

	public SvfBinder(Svf svf) {
		this.svf = svf;
		this.writer = new SvfWriterImpl();
	}

	public Svf getSvf() {
		return svf;
	}

	public void bind(Object bean, String[] names) throws PrintException, IOException {
		for (int i = 0; i < names.length; i++) {
			bind(bean, names[i]);
		}
	}

	public void bind(Object bean, String name) throws PrintException, IOException {
		bind(bean, name, getPropertyDescriptor(bean.getClass(), name));
	}

	public void bindAll(Object bean) throws PrintException, IOException {
		Map<String, PropertyDescriptor> map = getPropertyDescriptors(bean.getClass());
		Iterator<String> iter = map.keySet().iterator();
		while (iter.hasNext()) {
			String name = iter.next();
			bind(bean, name, map.get(name));
		}
	}

	private void bind(Object bean, String name, PropertyDescriptor prop) throws PrintException, IOException {
		Object value = null;
		try {
			value = prop.getReadMethod().invoke(bean, (Object[]) null);
		}
		catch (InvocationTargetException x) {
			throw new PrintException(x);
		}
		catch (IllegalAccessException x) {
			throw new PrintException(x);
		}

		if (String.class.equals(prop.getPropertyType())) {
			writer.write(svf, name, (String) value);
		}
		else if (BigDecimal.class.equals(prop.getPropertyType())) {
			writer.write(svf, name, (BigDecimal) value);
		}
		else if (Date.class.equals(prop.getPropertyType())) {
			writer.write(svf, name, (Date) value);
		}
		else {
			writer.write(svf, name, value);
		}
	}

	private Map<String, PropertyDescriptor> getPropertyDescriptors(Class<?> _class) throws PrintException {
		Map<String, PropertyDescriptor> propDescriptors = map.get(_class.getName());

		if (propDescriptors == null) {
			propDescriptors = new HashMap<String, PropertyDescriptor>();

			PropertyDescriptor[] props = null;
			try {
				props = Introspector.getBeanInfo(_class).getPropertyDescriptors();
			}
			catch (IntrospectionException x) {
				throw new PrintException(x);
			}
			for (int i = 0; i < props.length; i++) {
				String name = props[i].getName();

				if (!name.equals("class")) {
					propDescriptors.put(name, props[i]);
				}
			}
			map.put(_class.getName(), propDescriptors);
		}
		return propDescriptors;
	}

	private PropertyDescriptor getPropertyDescriptor(Class<?> _class, String name) throws PrintException {
		return getPropertyDescriptors(_class).get(name);
	}

	private class SvfWriterImpl implements SvfWriter {

		public void write(Svf svf, String name, String value) throws IOException {
			svf.VrsOut(name, nvl(value));
		}

		public void write(Svf svf, String name, BigDecimal value) throws IOException {
			if (value == null) {
				writeEmpty(svf, name);
			}
			else {
				svf.VrsOut(name, value.toString());
			}
		}

		public void write(Svf svf, String name, Date value) throws IOException {
			if (value == null) {
				writeEmpty(svf, name);
			}
			else {
				svf.VrsOut(name, format(value));
			}
		}

		public void write(Svf svf, String name, Object value) throws IOException {
			if (value == null) {
				writeEmpty(svf, name);
			}
			else {
				svf.VrsOut(name, value.toString());
			}
		}

		private void writeEmpty(Svf svf, String name) throws IOException {
			svf.VrsOut(name, "");
		}
		
		private String format(Date date) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			return sdf.format(date);
		}
		
		private String nvl(String val) {
			return (val == null || val.trim().equals("") ? "" : val);
		}

	}

}
