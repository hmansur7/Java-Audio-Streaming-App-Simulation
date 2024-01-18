public class Song extends AudioContent implements Comparable<Song>
{
	public static final String TYPENAME =	"SONG";
	
	public static enum Genre {POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL}; 
	private String artist; 		
	private String composer; 	
	private Genre  genre; 
	private String lyrics;
	
	
	public Song(String title, int year, String id, String type, String audioFile, int length, String artist,
			        String composer, Song.Genre genre, String lyrics)
	{
		super(title, year, id, type, audioFile, length);
		this.artist = artist;
		this.composer = composer;
		this.genre = genre;
		this.lyrics = lyrics;
	}
	
	public String getType()
	{
		return TYPENAME;
	}
	
	public void printInfo()
	{
		super.printInfo();
		System.out.println("Artist: " + artist.toString() + " Composer: " + composer + " Genre: " + genre);
	}
	
	public void play()
	{
		setAudioFile(lyrics);
		super.play();
	}
	
	public String getComposer()
	{
		return composer;
	}
	
	public void setComposer(String composer)
	{
		this.composer = composer;
	}
	
	public String getArtist()
	{
		return artist;
	}
	public void setArtist(String artist)
	{
		this.artist = artist;
	}
	
	public String getLyrics()
	{
		return lyrics;
	}
	public void setLyrics(String lyrics)
	{
		this.lyrics = lyrics;
	}

	public Genre getGenre()
	{
		return genre;
	}

	public void setGenre(Genre genre)
	{
		this.genre = genre;
	}	
	
	public boolean equals(Object other)
	{
		Song otherSong = (Song) other;
		return super.equals(other) && composer.equals(otherSong.composer) && artist.equals(otherSong.artist);
	}
	
	public int compareTo(Song other)
	{
		return this.getTitle().compareTo(other.getTitle());
	}
}
