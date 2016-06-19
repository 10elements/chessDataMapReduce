import java.io.IOException;
import java.util.HashMap;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class ChessMapper extends Mapper<Object, HashMap<String, String>, Text, Text> {
	private Text word = new Text("");
//	private final static IntWritable one = new IntWritable(1);
	public void map(Object key, HashMap<String, String> value, Context context) throws IOException, InterruptedException {
		String r = value.get("Result");
		if (r.equals("0-1")) {
			context.write(word, new Text("Black,1"));
		}else if (r.equals("1-0")) {
			context.write(word, new Text("White,1"));
		}else if (r.equals("1/2-1/2")) {
			context.write(word, new Text("Draw,1"));
		}
	}

}
