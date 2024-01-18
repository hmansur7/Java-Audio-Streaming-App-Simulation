abstract public class AudioContent
{
	public static enum Type {SONG, PODCAST, AUDIOBOOK};
	
	private String title;			
	private int year; 				
	private String id;				
	private String type;			 
	private String audioFile;     
	private int length; 			
	
	public AudioContent(String title, int year, String id, String type, String audioFile, int length)
	{
		this.title = title;
		this.year = year;
		this.id = id;
		this.type = type;
		this.audioFile = audioFile;
		this.length = length;
	}

	abstract public String getType();

  public void printInfo()
  {
    System.out.println("Title: " + title + " Id: " + id + " Year: " + year + " Type: " + type + " Length: " + length);	 
  }
	
	public void play()
	{
		this.printInfo();
		System.out.println("\n" + audioFile);
	}
	
	public boolean equals(Object other)
	{
		AudioContent otherCon = (AudioContent) other;
		return title.equals(otherCon.title) && id.equals(otherCon.id);
	}
  
	public String getAudioFile()
	{
		return this.audioFile;
	}

	public void setAudioFile(String file)
	{
		this.audioFile = file;
	}

	public int getLength()
	{
		return this.length;
	}

	public void setLength(int length)
	{
		this.length = length;
	}

	public String getTitle()
	{
		return this.title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public int getYear()
	{
		return this.year;
	}

	public void setYear(int year)
	{
		this.year = year;
	}

	public String getId()
	{
		return this.id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

}
