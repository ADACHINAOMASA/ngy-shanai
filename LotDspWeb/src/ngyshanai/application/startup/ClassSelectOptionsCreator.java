package ngyshanai.application.startup;

import java.util.List;

import ngyshanai.application.LogicExecutor;
import nis.framework.web.option.Option;
import nis.framework.web.option.SelectOptionsFactory;

public class ClassSelectOptionsCreator implements SelectOptionsCreator{

	public Class<SelectOptionsFactory> facotry;

	public ClassSelectOptionsCreator(Class<SelectOptionsFactory> facotry) {
		this.facotry = facotry;
	}

	@Override
	public List<Option> create(LogicExecutor ejb) {
		try {
			return facotry.newInstance().getSelectOptions();
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

}
