// package APProject;

public class MyImages implements java.io.Serializable {
	String path;
	String caption;
	String album;
	
	public MyImages(String path,String caption,String album) {
		this.path = path;
		this.caption = caption;
		this.album = album;
	}
	public MyImages() {
		this(null,null,null);
	}
	
	public String getPath() {
		return path;
	}
	
	public String getCaption() {
		return caption;
	}
	
	public String getAlbum() {
		return album;
	}
}
