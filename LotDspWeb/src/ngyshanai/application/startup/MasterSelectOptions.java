package ngyshanai.application.startup;

import java.util.ArrayList;
import java.util.List;

import ngyshanai.application.LogicExecutor;
import nis.framework.web.option.Option;
import nis.framework.web.option.SelectOptions;

public class MasterSelectOptions {

	@SelectOptions("setsubiOptions")
	public List<Option> createShoninJokyo(LogicExecutor ejb) {
		
		List<Option> options = new ArrayList<>();
		options.add(new Option("L-1","L-1"));
		options.add(new Option("L-2","L-2"));
		options.add(new Option("L-3","L-3"));
		
		return options;
	}

}
