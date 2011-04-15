package vooga.replay;

import java.io.*;

/**
 * Class that deals with saving/loadings StateTables from Serialized .ser files.
 * 
 * @author Josue, Chris
 */

public class StateTableFileManager {

	/**
	 * Serializes and saves StateTable object to .ser file.
	 * 
	 * If there is already a name.ser, it will overwrite that file with this new
	 * one.
	 * 
	 * @param st
	 *            - StateTable object being serialized
	 * @param name
	 *            - String name designated to the .ser file
	 */
	public void writeToNewSaveFile(StateTable st, String name) {

		FileOutputStream fout;
		try {
			fout = new FileOutputStream(name + ".ser");
			ObjectOutputStream out = new ObjectOutputStream(fout);
			out.writeObject(st);
			out.flush();
			out.close();
			// fout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Deserializes a .ser file into a StateTable object.
	 * 
	 * Currently this method does not handle the objects in a StateTable that
	 * are not serializeable; this method does not return a renderable
	 * StateTable object.
	 * 
	 * @param name
	 *            - Name of file you are deserializing (Must be in root
	 *            directory)
	 * @return StateTable object deserialized from name.ser file.
	 * @throws ClassNotFoundException
	 */
	public StateTable returnStateTableFromFile(String name)
			throws ClassNotFoundException {
		StateTable st = null;
		try {
			FileInputStream fileIn = new FileInputStream(name + ".ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			st = (StateTable) in.readObject();
			in.close();
			// fileIn.close();
		} catch (IOException e) {

		}
		return st;
	}

}
