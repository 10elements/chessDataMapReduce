import java.io.IOException;
import java.util.HashMap;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.util.LineReader;

public class Parser {
	private LineReader lReader;
	private long start;
	private long length;
	private long end;
	private long pos;
	public Parser(FSDataInputStream stream, long start, long length) throws IOException {
		// TODO Auto-generated constructor stub
		this.start = start;
		this.length = length;
		this.end = this.start + this.length;
		this.pos = this.start;
		stream.seek(this.start);
		lReader = new LineReader(stream);
	}

	public HashMap<String, String> next() throws IOException{
		HashMap<String, String> record = new HashMap<String, String>();
		Text text = new Text();
		String temp = null;
		boolean recordStart = false;
		while(true){
			this.pos = this.pos + this.lReader.readLine(text);
			temp = text.toString();
			if (temp.startsWith("[Event")) {
				recordStart = true;
			}
			if (recordStart) {
				if (temp.startsWith("[")) {
					//********************************************
					String []list = temp.replace("[", "").replace("\"]", "").split(" \"");
					record.put(list[0], list[1]);
					if (list[0].equals("Result")) {
						recordStart = false;
						return record;
					}
				}	
			}
//			System.out.println(this.pos + ", " + this.end + ", " + recordStart);
			if (!recordStart && (this.pos >= this.end)) {
				return null;
			}
		}
	}
}
