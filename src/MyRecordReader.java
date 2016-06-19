import java.io.IOException;
import java.util.HashMap;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

public class MyRecordReader extends RecordReader<Object, HashMap<String, String>> {
	FSDataInputStream stream = null;
	long start = 0;
	long length = 0;
	long current = 0;
	Parser p = null;
	Object key = null;
	HashMap<String, String> value = null;
	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
	}

	@Override
	public Object getCurrentKey() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		return key;
	}

	@Override
	public HashMap<String, String> getCurrentValue() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
			return value;
	}

	@Override
	public float getProgress() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void initialize(InputSplit arg0, TaskAttemptContext arg1) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		FileSplit fSplit = (FileSplit)arg0;
		this.start = (fSplit).getStart();
		this.length = (fSplit).getLength();
		Configuration cfg = arg1.getConfiguration();
		FileSystem fs = fSplit.getPath().getFileSystem(cfg);
		this.stream = fs.open(fSplit.getPath());
		p = new Parser(this.stream, this.start, this.length);
	}

	@Override
	public boolean nextKeyValue() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		value = p.next();
		key = new Object();
		if (value == null) {
			return false;
		}
		return true;
	}

}
