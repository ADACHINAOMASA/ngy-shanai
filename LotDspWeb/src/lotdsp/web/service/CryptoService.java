package lotdsp.web.service;

import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import lotdsp.common.util.AesUtil;
import lotdsp.common.util.collection.Maps;
import nis.framework.properties.AppProperties;

/**
 * @author Leang-Say
 */
@Path("/crypto")
public class CryptoService {

	protected Log logger = LogFactory.getLog(getClass());

	private String passphrase = AppProperties.get("crypto.key.uri.passphrase");
	private String salt = AppProperties.get("crypto.key.uri.salt");
	private String iv = AppProperties.get("crypto.key.uri.iv");

	@POST
	@Path("/encrypt")
	@Consumes(MediaType.APPLICATION_JSON)
	public Map<String, String> encrypt(Map<String, String> map) {
		AesUtil aes = new AesUtil();
		Map<String, String> encryptedMap = Maps.newHashMap();
		for (Entry<String, String> entry : map.entrySet()) {
			encryptedMap.put(entry.getKey(), aes.encrypt(salt, iv, passphrase, entry.getValue()));
		}
		return encryptedMap;
	}

	
	@POST
	@Path("/decrypt")
	@Consumes(MediaType.APPLICATION_JSON)
	public Map<String, String> decrypt(Map<String, String> map) {
		AesUtil aes = new AesUtil();
		Map<String, String> decryptedMap = Maps.newHashMap();
		for (Entry<String, String> entry : map.entrySet()) {
			decryptedMap.put(entry.getKey(), aes.decrypt(salt, iv, passphrase, entry.getValue()));
		}
		return decryptedMap;
	}
	
	//-------------------------------------------------------------------------------------------------↓
	@GET
	@Path("/encrypt2/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	public String encrypt2(@PathParam("param") String param) {
		AesUtil aes = new AesUtil();
		String value=aes.encrypt(salt, iv, passphrase, param);
		return value;
	}
	
	@GET
	@Path("/encrypt3/{param1}/{param2}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, String> encrypt3(@PathParam("param1") String param1,@PathParam("param2") String param2) {
		AesUtil aes = new AesUtil();
		Map<String, String> map = Maps.newHashMap();
		map.put("param1", aes.encrypt(salt, iv, passphrase, param1));
		map.put("param2", aes.encrypt(salt, iv, passphrase, param2));
		return map;
	}

	@GET
	@Path("/decrypt2/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	public String decrypt2(@PathParam("param") String param) {
		AesUtil aes = new AesUtil();
		String value=aes.decrypt(salt, iv, passphrase, param);
		return value;
	}
	
	@GET
	@Path("/decrypt3/{param1}/{param2}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, String> decrypt3(@PathParam("param1") String param1,@PathParam("param2") String param2) {
		AesUtil aes = new AesUtil();
		Map<String, String> map = Maps.newHashMap();
		map.put("param1", aes.decrypt(salt, iv, passphrase, param1));
		map.put("param2", aes.decrypt(salt, iv, passphrase, param2));
		return map;
	}
	//-------------------------------------------------------------------------------------------------↑	
}
