package mapred.exam.air.multiple;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

public class AirMultipleReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
	IntWritable resulVal = new IntWritable(); //출력값
	Text resultkey = new Text(); //출력키
	MultipleOutputs<Text, IntWritable> multiOut;
	@Override
	protected void setup(Reducer<Text, IntWritable, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		multiOut = new MultipleOutputs<Text, IntWritable>(context);
	}
	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Reducer<Text, IntWritable, Text, IntWritable>.Context context)
					throws IOException, InterruptedException {
		//1. mapper에서 받은 key에서 값을 분리
		String[] data = key.toString().split(",");
		resultkey.set(data[1]);
		
		//2. key에 추가됐던 구분자별로 output
		if(data[0].equals("1980")) {					
			int sum = 0;
			for(IntWritable value:values) {
				sum = sum + value.get();
			}		
			resulVal.set(sum);
			multiOut.write("1980", resultkey, resulVal);
		}else if(data[0].equals("1990")) {
			int sum = 0;
			for(IntWritable value:values) {
				sum = sum + value.get();
			}		
			resulVal.set(sum);
			multiOut.write("1990", resultkey, resulVal);
		}else {
			int sum = 0;
			for(IntWritable value:values) {
				sum = sum + value.get();
			}		
			resulVal.set(sum);
			multiOut.write("2000", resultkey, resulVal);
		}				
	}
	@Override
	protected void cleanup(Reducer<Text, IntWritable, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		multiOut.close();
	}

	
}
