package mapreduce.air.sort;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AirSortReducer 
	extends Reducer<CustomKey, IntWritable, CustomKey, IntWritable>{
	IntWritable resulVal = new IntWritable();
	CustomKey resultKey = new CustomKey();
	Text appenddata = new Text();//로그 파일로 출력할 값을 저장할 변수
	@Override
	protected void reduce(CustomKey key, Iterable<IntWritable> values,
			Reducer<CustomKey, IntWritable, CustomKey, IntWritable>.Context context)
					throws IOException, InterruptedException {
		int sum = 0;
		Integer beforeMonth = key.getMonth();
		int count = 0;
		//appenddata.set("reduce호출");
		//한 개씩 비교하면서 month가 같으면 집계하고 month가 다르면 집계 내용을 내보내기
		//다시 초기화
		for(IntWritable value:values) {
			if(count<=10) {
				System.out.println("reduce=>"+key);
				count++;
			}
			if(beforeMonth!=key.getMonth()) {//최초 month값과 값이 달라지는 시점
				//결과를 내보내기
				resultKey.setYear(key.getYear());
				resultKey.setMonth(beforeMonth);
				resulVal.set(sum);
				context.write(resultKey, resulVal);
				sum = 0;// 다시 집계해야 하므로 초기화
			}
			sum = sum + value.get();
			beforeMonth = key.getMonth();
		}
		if(key.getMonth()==beforeMonth) {
			resultKey.setYear(key.getYear());
			resultKey.setMonth(beforeMonth);
			resulVal.set(sum);
			context.write(resultKey, resulVal);
		}
	}	
}
