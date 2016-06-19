import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class PlycountReducer extends Reducer<Text, Text, Text, Text> {
	Map<String, Integer> dict = new HashMap<String, Integer>();
	
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		// process values
		int sum = 0;
		for (Text val : values) {
			String[] list = val.toString().split(",");
			if (!dict.containsKey(list[0])) {
//				System.out.println(val.toString());
				dict.put(list[0], Integer.valueOf(list[1]));
				sum += Integer.valueOf(list[1]);
			}else {
				Integer nInt = dict.get(list[0]) + Integer.valueOf(list[1]);
				dict.put(list[0], nInt);
				sum += Integer.valueOf(list[1]);
			}
		}
		Map<String, Integer> sorteddict = sort(dict);
		for(String mKey : sorteddict.keySet()){
//			System.out.println(mKey);
			float percentage = (sorteddict.get(mKey)/(float)sum)*100;
			context.write(new Text(mKey), new Text(String.format("%s%%", percentage)));
		}
//		for (Text val : values) {
//			context.write(val, new Text("1"));
//		}
	}
	
	private Map<String, Integer> sort(Map<String, Integer> map){
		
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {

			@Override
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				// TODO Auto-generated method stub
				return -(o1.getValue().compareTo(o2.getValue()));
			}
		});
		Map<String, Integer> sorted = new LinkedHashMap<String, Integer>();
		Iterator<Map.Entry<String, Integer>> iterator = list.iterator();
		Map.Entry<String, Integer> temp = null;
		while(iterator.hasNext()){
			temp = iterator.next();
			sorted.put(temp.getKey(), temp.getValue());
		}
		return sorted;
		
	}
}
