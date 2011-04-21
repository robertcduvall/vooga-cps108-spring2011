package vooga.replay;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
 
 
@SuppressWarnings("serial")
public class SerialByteArrayObject<T> implements Serializable {
 
	private byte[] byteImage = null;
 
	public SerialByteArrayObject(T generic) {
		this.byteImage = toByteArray(generic);
	}
 
	public T getInstance() {
		return fromByteArray(byteImage);
	}
 
	@SuppressWarnings("unchecked")
	private T fromByteArray(byte[] byteArray) {
		try {
			if (byteArray != null && (byteArray.length > 0)) {
				ByteArrayInputStream byteArrayIn = new ByteArrayInputStream(byteArray);
				ObjectInputStream in = new ObjectInputStream(byteArrayIn);
				T instance = null;
				try {
					instance = (T) in.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				return instance;
			}
			return null;
		} catch (IOException e) {
			throw new IllegalArgumentException(e.toString());
		}
	}
 
	private byte[] toByteArray(T generic) {
		if (generic != null) {
			T genByte = generic;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try {
				ObjectOutputStream out = new ObjectOutputStream(baos);
				out.writeObject(genByte);
				//ImageIO.write(genByte, "png", baos);
			} catch (IOException e) {
				throw new IllegalStateException(e.toString());
			}
			byte[] b = baos.toByteArray();
			return b;
		}
		return new byte[0];
	}
}