import java.io.IOException;
import java.util.HashMap;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class PlycountMapper extends Mapper<Object, HashMap<String, String>, Text, Text> {
	private Text word = new Text("");
	public void map(Object key, HashMap<String, String> value, Context context) throws IOException, InterruptedException {
		String plycount = value.get("PlyCount");
		context.write(word, new Text(plycount));
	}

}
