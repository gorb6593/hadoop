package mapreduce.air.sort;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class AirSortDriver extends Configured implements Tool{
	
	@Override
	public int run(String[] optionlist) throws Exception {	
		GenericOptionsParser optionParser = 
				new GenericOptionsParser(getConf(), optionlist);

		//나머지 일반옵션(공통옵션)도 관리 - input/output경로가 설정되므로 반드시 필요
		String[] otherArgs = optionParser.getRemainingArgs();
		
		//Configuration conf = new Configuration();
		Job job = new Job(getConf(), "air_sort");
		
		job.setMapperClass(AirSortMapper.class);
		job.setReducerClass(AirSortReducer.class);
		job.setJarByClass(AirSortDriver.class);
		
		//shuffle할때 사용할 클래스를 사용자정의 클래스가 실행되도록 등록하기
		job.setPartitionerClass(AirSortPartitioner.class);
		job.setGroupingComparatorClass(GroupKeyComparator.class);
		job.setSortComparatorClass(CustomKeyComparator.class);
		job.setMapOutputKeyClass(CustomKey.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
		
		job.waitForCompletion(true);
		return 0;
	}	

	public static void main(String[] args) throws Exception {
		ToolRunner.run(new Configuration(), new AirSortDriver(), args);
	}

}
