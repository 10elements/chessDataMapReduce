import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class PlyCombiner extends Reducer<Text, Text, Text, Text> {
	Map<String, Integer> dict = new HashMap<String, Integer>();
	
	public void reduce(Text _key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		// process values
		
		for (Text val : values) {
			if (!dict.containsKey(val.toString())) {
//				System.out.println(val.toString());
				dict.put(val.toString(), 1);
			}else {
				Integer nInt = dict.get(val.toString()) + 1;
//				System.out.println(val.toString()+ "," +nInt);
				dict.put(val.toString(), nInt);
			}
		}
		for(String mkey : dict.keySet()){
			context.write(new Text(""), new Text(mkey + "," + dict.get(mkey)));
		}
	}

}
