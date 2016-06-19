import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class PlayerReducer extends Reducer<Text, Text, Text, Text> {

	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		// process values
		int whiteWin = 0;
		int whiteLose = 0;
		int whiteDraw = 0;
		int whiteSum = 0;
		int blackWin = 0;
		int blackLose = 0;
		int blackDraw = 0;
		int blackSum = 0;
		for (Text val : values) {
			String []list = val.toString().split(",");
			if (list[0].equals("White")) {
				whiteWin += Integer.valueOf(list[1]);
				whiteLose += Integer.valueOf(list[2]);
				whiteDraw += Integer.valueOf(list[3]);
				
			}else if (list[0].equals("Black")) {
				blackWin += Integer.valueOf(list[1]);
				blackLose += Integer.valueOf(list[2]);
				blackDraw += Integer.valueOf(list[3]);
				
			}
		}
		whiteSum += (whiteWin + whiteLose + whiteDraw);
		blackSum += (blackWin + blackLose + blackDraw);
		float pww = (float)whiteWin / whiteSum;
		float pwl = (float)whiteLose / whiteSum;
		float pwd = (float)whiteDraw / whiteSum;
		float pbw = (float)blackWin / blackSum;
		float pbl = (float)blackLose / blackSum;
		float pbd = (float)blackDraw / blackSum;
		context.write(key, new Text("White " + String.valueOf(pww) + " " + String.valueOf(pwl) + " " + String.valueOf(pwd)));
		context.write(key, new Text("Black " + String.valueOf(pbw) + " " + String.valueOf(pbl) + " " + String.valueOf(pbd)));
	}

}
