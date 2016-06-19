import java.io.IOException;
import java.util.HashMap;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class PlayerMapper extends Mapper<Object, HashMap<String, String>, Text, Text> {

	public void map(Object key, HashMap<String, String> value, Context context) throws IOException, InterruptedException {
			String white = value.get("White");
			String black = value.get("Black");
			String result = value.get("Result");
			String []winlose = result.split("-");
			context.write(new Text(white), new Text("White" + "," + winlose[0]));
			context.write(new Text(black), new Text("Black" + "," + winlose[1]));
			
	}

}
