package ngyshanai.web.service;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ngyshanai.common.msg.PropertyMessageInfo;
import ngyshanai.common.msg.PropertyMessageReqInfo;
import ngyshanai.common.util.collection.Maps;
import nis.framework.properties.Messages;
import nis.framework.util.StringUtil;

/**
 * messages.propertiesにあるメッセージを返す。
 * 
 * @author Leang-Say
 */
@Path("/message")
public class MessageService {

	protected Log logger = LogFactory.getLog(getClass());

	private String nvl(String val) {
		if ("undefined".equals(val)) {
			return "";
		}
		return StringUtil.nvl(val);
	}

	@GET
	@Path("/{key}/{param1}/{param2}/{param3}")
	public String getMessage(@PathParam("key") String key, @PathParam("param1") String param1,
			@PathParam("param2") String param2, @PathParam("param3") String param3) {
		param1 = nvl(param1);
		param2 = nvl(param2);
		param3 = nvl(param3);
		if (StringUtil.hasValue(param1) && StringUtil.hasNoValue(param2) && StringUtil.hasNoValue(param3)) {
			return Messages.of(key, param1);
		} else if (StringUtil.hasValue(param1) && StringUtil.hasValue(param2) && StringUtil.hasNoValue(param3)) {
			return Messages.of(key, param1, param2);
		} else if (StringUtil.hasValue(param1) && StringUtil.hasValue(param2) && StringUtil.hasValue(param3)) {
			return Messages.of(key, param1, param2, param3);
		} else {
			return Messages.of(key);
		}
	}

	@GET
	@Path("/{key}")
	public String getMessage(@PathParam("key") String key) {
		return Messages.of(key);
	}

	@GET
	@Path("/getAll")
	public Map<String, String> getAll() {
		return Messages.getAll();
	}

	@POST
	@Path("/getMessages")
	@Consumes(MediaType.APPLICATION_JSON)
	public Map<String, String> getMessages(PropertyMessageReqInfo in) {
		Map<String, String> map = Maps.newHashMap();
		for (PropertyMessageInfo msg : in.getMessages()) {
			String key = msg.getKey();
			String[] params = (String[]) msg.getParams().toArray(new String[0]);
			if (params == null || params.length == 0) {
				map.put(key, Messages.of(key));
			} else {
				map.put(key, Messages.of(key, params));
			}
		}
		return map;
	}

}
