import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class PlayerCombiner extends Reducer<Text, Text, Text, Text> {

	public void reduce(Text _key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		// process values
		int whiteWin = 0;
		int whiteLose = 0;
		int whiteDraw = 0;
		int blackWin = 0;
		int blackLose = 0;
		int blackDraw = 0;
		for (Text val : values) {
			String []list = val.toString().split(",");
			if (list[0].equals("White")) {
				if (list[1].equals("1")) {
					whiteWin++;
				}else if (list[1].equals("0")) {
					whiteLose++;
				}else if (list[1].equals("1/2")) {
					whiteDraw++;
				}
			}else if (list[0].equals("Black")) {
				if (list[1].equals("1")) {
					blackWin++;
				}else if (list[1].equals("0")) {
					blackLose++;
				}else if (list[1].equals("1/2")) {
					blackDraw++;
				}
			}
		}
		context.write(_key, new Text("White," + String.valueOf(whiteWin) + "," + 
				String.valueOf(whiteLose) + "," + String.valueOf(whiteDraw)));
		context.write(_key, new Text("Black," + String.valueOf(blackWin) + "," + 
				String.valueOf(blackLose) + "," + String.valueOf(blackDraw)));
	}

}
