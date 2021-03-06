package mapred.exam.air.option;

import java.io.IOException;

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

public class AirOptionDriver extends Configured implements Tool{
	
	@Override
	public int run(String[] optionlist) throws Exception {	
		//run메소드는 사용자가 입력한 모든 옵션에 대한 정보를 String[]으로 전달 받는다.
		//-D와 함께 사용자가 입력한 옵션과 기존에 사용되던 명령행매개변수를 분리해서 관리해야 한다.
		//이를 담당하는 객체가 GenericOptionParser
		//GenericOptionParser에 의해 모든 사용자가 입력한 모든 옵션이 관리된다.
		//-D와 입력한 옵션 정보를 GenericOptionParser가 관리하고 나머지 옵션(공통옵션)도 따로 관리한다.
		//getConf()메소드를 통해서 받은 환경설정정보에 -D와 함께 입력한 사용자 정의 옵션을 등록
		//=> 이렇게 하면 Mapper의 setup메소드에서 환경설정정보에서 사용자가 입력한 옵션을 꺼낼 수 있다.
		GenericOptionsParser optionParser = 
				new GenericOptionsParser(getConf(), optionlist);
		//나머지 일반옵션(공통옵션)도 관리 - input/output경로가 설정되므로 반드시 필요
		String[] otherArgs = optionParser.getRemainingArgs();
		
		//Configuration conf = new Configuration();
		Job job = new Job(getConf(), "airoption");
		
		job.setMapperClass(AirOptionMapper.class);
		job.setReducerClass(AirOptionReducer.class);
		job.setJarByClass(AirOptionDriver.class);
		
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
		ToolRunner.run(new Configuration(), new AirOptionDriver(), args);
	}

}
