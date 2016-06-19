import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MyCombiner extends Reducer<Text, Text, Text, Text> {

	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		// process values
		int black = 0;
		int white = 0;
		int draw = 0;
		for (Text val : values) {
			String[] list = val.toString().split(",");
			if (list[0].equals("White")) {
				white++;
			}else if (list[0].equals("Black")) {
				black++;
			}else if (list[0].equals("Draw")) {
				draw++;
			}
		}
		context.write(key, new Text("White," + String.valueOf(white)));
		context.write(key, new Text("Black," + String.valueOf(black)) );
		context.write(key, new Text("Draw," + String.valueOf(draw)));
	}

}
