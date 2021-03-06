package mapred.exam.air.multiple;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
public class AirMultipleMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	Text outputKey = new Text();
	static final IntWritable outputval = new IntWritable(1);				
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		if(key.get()>0) { 
			String[] line = value.toString().split(","); 			
			if(line!=null & line.length>0) { 
				String year = line[0];
				String month = line[1];				
				int result = Integer.parseInt(line[0]);
				if(!line[15].equals("NA") && Integer.parseInt(line[15])>0){									
					if(result<1990) {
						outputKey.set("1980,"+year+"년"+month+"월");
						context.write(outputKey, outputval);
					}else if(result> 1990 && result <2000) {
						outputKey.set("1990,"+year+"년"+month+"월");
						context.write(outputKey, outputval);
					}else if(result>2000){
						outputKey.set("2000,"+year+"년"+month+"월");
						context.write(outputKey, outputval);													
					}		
				}
			}
		}		
	}	
}
