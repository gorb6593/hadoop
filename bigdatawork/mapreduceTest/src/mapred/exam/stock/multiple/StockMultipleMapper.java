package mapred.exam.stock.multiple;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
public class StockMultipleMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	Text outputKey = new Text();
	static final IntWritable outputval = new IntWritable(1);				
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		if(key.get()>0) { //0번 라인 제외
			String[] line = value.toString().split(","); // 한 라인을 ,를 기준으로 단어 분리
			if(line!=null & line.length>0) { 
				//key => 구분값, 년도
				String year = line[2].substring(0,4);
				double result = Double.parseDouble(line[6]) -
						Double.parseDouble(line[3]);	
				if(result>0) {//상승마감
					outputKey.set("up,"+year);//키설정
					context.write(outputKey, outputval);
				}else if(result<0) {//하락마감
					outputKey.set("down,"+year);
					context.write(outputKey, outputval);
				}else {//동일
					outputKey.set("squal,"+year);
					context.write(outputKey, outputval);
				}
			}
		}		
	}	
}
