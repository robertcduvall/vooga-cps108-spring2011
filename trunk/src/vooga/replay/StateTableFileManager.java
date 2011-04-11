package vooga.replay;
import java.io.*;

public class StateTableFileManager {

	public void writeToNewSaveFile(StateTable st, String name) {

		FileOutputStream fout;
		try {
			fout = new FileOutputStream(name + ".ser");
			ObjectOutputStream out = new ObjectOutputStream(fout);
			out.writeObject(st);
			out.flush();
			out.close();
			//fout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public StateTable returnStateTableFromFile(String name) throws ClassNotFoundException {
		StateTable st = null;
		try {
			FileInputStream fileIn = new FileInputStream(name+".ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			st = (StateTable) in.readObject();
			in.close();
			//fileIn.close();
		} catch (IOException e) {

		}
		return st;
	}

}
