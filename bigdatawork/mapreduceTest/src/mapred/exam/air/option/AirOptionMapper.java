package mapred.exam.air.option;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
//하둡 실행할 때 사용자가 입력(쉘 프롬프트에서 실행을 위해 사용자가 입력)하는 옵션을 Mapper내부에서
//사용할 수 있도록 처리
//=> 옵션이 어떤 값이 입력되었냐에 따라 다른 작업을 할 수 있도록 처리
public class AirOptionMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	Text outputKey = new Text();
	static final IntWritable outputval = new IntWritable(1);
	//사용자가 입력하는 옵션값을 저장하기 위한 변수
	String jobType;
	//Mapper가 실행될 때 한 번만 실해되는 메소드
	@Override
	protected void setup(Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		/*
		 * 사용자가 입력하는 옵션 정보를 환경설정 정보에서 가져와서 변수 jobType에 셋팅
		 * 옵션명 : get메소드의 매개변수는 사용자가 입력하는 옵션명 => jobType
		 */
		jobType = context.getConfiguration().get("jobType");
	}
	
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		if(key.get()>0) { //0번 라인 제외
			String[] line = value.toString().split(","); // 한 라인을 ,를 기준으로 단어 분리
			if(line!=null & line.length>0) { 
				//jobType의 값에 따라서(입력하는 jobType옵션의 값에 따라서)
				//다르게 동작할 수 있도록 구현
				if(jobType.equals("departure")) {
					//출발지연
					if(!line[15].equals("NA") && Integer.parseInt(line[15])>0){
						outputKey.set(line[1]+"월");	
						context.write(outputKey, outputval);
					}					
				}else if(jobType.equals("arrival")) {
					//도착지역
					if(!line[14].equals("NA") && Integer.parseInt(line[14])>0){
						outputKey.set(line[1]+"월");	
						context.write(outputKey, outputval);
					}
				}											
			}
		}		
	}	
}
