package mapred.exam.stock.option;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class StockOptionMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	Text outputKey = new Text();
	static final IntWritable outputval = new IntWritable(1);
	String stockType;
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		stockType = context.getConfiguration().get("stockType");
		if(key.get()>0) { 
			String[] line = value.toString().split(","); 
			if(line!=null & line.length>0) {
				if(stockType.equals("up")) {
					double result = Double.parseDouble(line[6]) - Double.parseDouble(line[3]);
					if(result>0) {
						outputKey.set(line[2].substring(0,4));
						context.write(outputKey, outputval);
					}				
				}else if(stockType.equals("down")) {
					double result = Double.parseDouble(line[6]) - Double.parseDouble(line[3]);
					if(result<0) {
						outputKey.set(line[2].substring(0,4));
						context.write(outputKey, outputval);
					}
				}else if(stockType.equals("same")) {
					double result = Double.parseDouble(line[6]) - Double.parseDouble(line[3]);
					if(result==0) {
						outputKey.set(line[2].substring(0,4));
						context.write(outputKey, outputval);
					}
				}																													
			}
		}													
	}	
}
