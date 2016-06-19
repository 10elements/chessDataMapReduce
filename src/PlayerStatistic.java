import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class PlayerStatistic {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		// TODO Auto-generated method stub
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "player statistic");
		job.setJarByClass(PlayerStatistic.class);
		job.setInputFormatClass(MyInputFormat.class);
		job.setMapperClass(PlayerMapper.class);
		job.setReducerClass(PlayerReducer.class);
		job.setCombinerClass(PlayerCombiner.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		MyInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}

}
