package lotdsp.common.util.group;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class GroupUtil {
	
	public static synchronized Map<String, List<Object>> group(List<?> dataList)  {
		Map<String, List<Object>> map = new LinkedHashMap<String, List<Object>>();
		return group(dataList,map);
	}

	/**
	 * キー毎にデータをグループする。
	 *
	 * ※データは、必ずIGroupKeyを実装すること
	 * @param dataList
	 * @return
	 * @throws Exception
	 */
	public static synchronized Map<String, List<Object>> group(List<?> dataList,Map<String, List<Object>> map)  {

		if (dataList == null || dataList.isEmpty()) {
			return null;
		}

		//Map<String, List<Object>> map = new LinkedHashMap<String, List<Object>>();
		
		try {

			List<Object> tempList = new ArrayList<Object>();
			String key = null;
			int cnt = 0;

			for (Object info : dataList) {
				cnt++;

				if (!isEqual(key, getKey(info))) {

					if (cnt > 1) {
						map.put(key, new ArrayList<Object>(tempList));
						tempList.clear();
					}

					// 1件目 またはキーが変わった時
					key = getKey(info);
					tempList.add(info);

					// 1件しかないデータまたは最後のデータ
					if ((cnt == 1 && dataList.size() == 1) || (cnt == dataList.size())) {
						map.put(key, new ArrayList<Object>(tempList));
						tempList.clear();
					}

				} else {
					//同じキー
					tempList.add(info);

					//最後のデータ
					if (cnt == dataList.size()) {
						map.put(key, new ArrayList<Object>(tempList));
						tempList.clear();
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			map = null;
		}

		return map;
	}

	private static String getKey(Object info){
		return ((IGroupKey)info).getKey();
	}
	
	private static boolean isEqual(String s1, String s2) {
		if (s1 == null || s2 == null) {
			return (s1 == null && s2 == null);
		}
		return s1.equals(s2);
	}
}
