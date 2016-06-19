import java.io.IOException;
import java.util.HashMap;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

public class MyInputFormat extends FileInputFormat<Object, HashMap<String, String>> {
	
	@Override
	public RecordReader<Object, HashMap<String, String>> createRecordReader(InputSplit arg0, TaskAttemptContext arg1)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		return new MyRecordReader();
	}
}