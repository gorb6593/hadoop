package mapred.exam.stock.option;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class StockOptionReducer
	extends Reducer<Text, IntWritable, Text, IntWritable>{
	IntWritable resulVal = new IntWritable();

	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
		int sum = 0;
		for(IntWritable value:values) {
			sum = sum + value.get();
		}
		resulVal.set(sum);
		context.write(key, resulVal);
		
	}
	
}
