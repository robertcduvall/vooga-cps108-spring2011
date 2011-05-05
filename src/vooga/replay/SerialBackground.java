package vooga.replay;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.golden.gamedev.object.Background;

@SuppressWarnings("serial")
public class SerialBackground implements Serializable{
	private byte[] myByte;
	public SerialBackground(Background background){
		try {
			myByte = toByteArray(background);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Background getBackground(){
		try {
			return fromByteArray(myByte);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return new Background();
	}
	public byte[] toByteArray(Background background) throws IOException{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream fos = new ObjectOutputStream(bos);
		fos.writeObject(background);
		return bos.toByteArray();
	}
	public Background fromByteArray(byte[] bytes) throws IOException, ClassNotFoundException{
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		ObjectInputStream ois = new ObjectInputStream(bis);
		return (Background)ois.readObject();
	}
}
