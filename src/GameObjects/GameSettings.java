package GameObjects;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javafx.scene.paint.Color;
/**
 * class GameSettings is an object that is used to store and transfer
 * our configuration. this function is serializable so we can save and load
 * our stats conveniently.
 */
public class GameSettings implements Serializable{
	private static final long serialVersionUID = 1L;
	//members.
	private int size;
	//because javafx color is not serializable we need to construct it
	//we the double values.
	private double red1;
	private double blue1;
	private double green1;
	private double alpha1;
	private double red2;
	private double blue2;
	private double green2;
	private double alpha2;
	private String first;
	/**
	 * C'tor
	 */
	public GameSettings() {
		//initialized as default
        this.size = 8;
        Color player1 = Color.BLACK;
        Color player2 = Color.WHITE;
        this.red1 = player1.getRed();
        this.red2 = player2.getRed();
        this.blue1 = player1.getBlue();
        this.blue2 = player2.getBlue();
        this.green1 = player1.getGreen();
        this.green2 = player2.getGreen();
        this.alpha1 = player1.getOpacity();
        this.alpha2 = player2.getOpacity();
        //first player is saved as string for convenient use with choicebox
        this.first = "Black";
    }
	/**
	 * size getter
	 * @return
	 */
	public int getSize() {
		return this.size;
	}
	/**
	 * size setter
	 * @param size
	 */
	public void setSize(int size) {
		this.size = size;
	}
	/**
	 * first player getter.
	 * @return string first 
	 */
	public String getFirst() {
		return this.first;
	}
	/**
	 * first player setter.
	 * @param first
	 */
	public void setFirst(String first) {
		this.first = first;
	}
	/**
	 * player 1 color getter.
	 * @return color
	 */
	public javafx.scene.paint.Color getColor1() {
		return new Color(red1,green1,blue1,alpha1);
	}
	/**
	 * player 1 color setter.
	 * @param color to set
	 */
	public void setColor1(javafx.scene.paint.Color color) {
		 this.red1 = color.getRed();
		 this.blue1 = color.getBlue();
		 this.green1 = color.getGreen();
		 this.alpha1 = color.getOpacity();
	}
	/**
	 * player 2 color getter.
	 */
	public javafx.scene.paint.Color getColor2() {
		return new Color(red2,green2,blue2,alpha2);
	}
	/**
	 * player 2 color setter.
	 * @param color to set
	 */
	public void setColor2(javafx.scene.paint.Color color) {
		this.red2 = color.getRed();
		 this.blue2 = color.getBlue();
		 this.green2 = color.getGreen();
		 this.alpha2 = color.getOpacity();
	}
	/**
     * Load settings data from file Current table data is cleared.
     * @param filename , the file to load.
     * @throws IOException , exception.
     */
    public void load(File filename) throws IOException {
        ObjectInputStream objectinputstream = null;
        try {
            FileInputStream streamIn = new FileInputStream(filename);
            objectinputstream = new ObjectInputStream(streamIn);
            GameSettings loaded = (GameSettings) objectinputstream.readObject();
            this.size = loaded.getSize();
            this.first = loaded.getFirst();
            this.setColor1(loaded.getColor1());
            this.setColor2(loaded.getColor2());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (objectinputstream != null) {
                objectinputstream .close();
            }
        }
    }
    /**
     * Save settings data to the specified file.
     * @param filename file name.
     * @throws IOException in case of failure.
     */
    public void save(File filename) throws IOException {
        ObjectOutputStream oos = null;
        FileOutputStream fout = null;
        try {
            fout = new FileOutputStream(filename, false);
            oos = new ObjectOutputStream(fout);
            oos.writeObject(this);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                oos.close();
            }
        }
    }
    /**
     * Read gameSettings from file and return it.
     * If the file does not exist, or there is a problem with
     * reading it, an empty table is returned
     * @param filename , the file's name.
     * @return gameSettings or null.
     */
    public static GameSettings loadFromFile(File filename) {
    	GameSettings newlyLoaded = new GameSettings();
        try {
            newlyLoaded.load(filename);
            return newlyLoaded;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
