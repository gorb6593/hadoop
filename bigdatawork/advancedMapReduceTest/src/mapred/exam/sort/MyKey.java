package mapred.exam.sort;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableUtils;


public class MyKey implements WritableComparable<MyKey> {
	private String productId;
	private String userId;
	public MyKey() {
		
	}
	
	public MyKey(String productId, String userId) {
		super();
		this.productId = productId;
		this.userId = userId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	

	@Override
	public String toString() {
		return (new StringBuffer())
				.append(productId).append(",").append(userId).toString();
	}
	
	@Override
	public void readFields(DataInput in) throws IOException {
		productId = WritableUtils.readString(in);
		userId = WritableUtils.readString(in);
	}

	@Override
	public void write(DataOutput out) throws IOException {
		WritableUtils.writeString(out,productId);
		WritableUtils.writeString(out,userId);
	}

	@Override
	public int compareTo(MyKey anotherKey) {
		int result = productId.compareTo(anotherKey.productId);
		if(result==0) {
			result = userId.compareTo(anotherKey.userId);
		}
		return result;
	}

}
