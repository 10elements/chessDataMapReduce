import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ChessReducer extends Reducer<Text, Text, Text, Text> {
	int white = 0;
	int black = 0;
	int draw = 0;
	int sum = 0;
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		// process values
		
		for (Text val : values) {
			String[] list = val.toString().split(",");
			if (list[0].equals("White")) {
				white += Integer.valueOf(list[1]);
				sum += Integer.valueOf(list[1]);
			}else if (list[0].equals("Black")) {
				black += Integer.valueOf(list[1]);
				sum += Integer.valueOf(list[1]);
			}else if (list[0].equals("Draw")) {
				draw += Integer.valueOf(list[1]);
				sum += Integer.valueOf(list[1]);
			}
		}
		float pw = (float)white / sum;
		float pb = (float)black / sum;
		float pd = (float)draw / sum;
 		context.write(new Text("White"), new Text(String.valueOf(white) + " " + new Text(String.valueOf(pw))));
		context.write(new Text("Black"), new Text(String.valueOf(black) + " " + new Text(String.valueOf(pb))));
		context.write(new Text("Draw"), new Text(String.valueOf(draw) + " " + new Text(String.valueOf(pd))));
	}

}
