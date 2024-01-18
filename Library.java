import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Library
{
	private ArrayList<Song> 			songs; 
	private ArrayList<Podcast> 		podcasts;
	private ArrayList<AudioBook> 	audiobooks;
	private ArrayList<Playlist> 	playlists; 
		
	String errorMsg = "";
	
	public String getErrorMessage()
	{
		return errorMsg;
	}

	public Library()
	{
		songs 			= new ArrayList<Song>(); 
		podcasts		= new ArrayList<Podcast>(); ;
		audiobooks 	= new ArrayList<AudioBook>(); ;
		playlists   = new ArrayList<Playlist>(); 
	}
	
	public void download(AudioContent content)
	{
		
		if (content.getType().equals(Song.TYPENAME))
		{
			
			if (songs.contains(content))
			{
				System.out.println(Song.TYPENAME + " " + content.getTitle() + " already downloaded");
			}
			else
			{
				songs.add((Song)content);
				System.out.println(Song.TYPENAME + " " + content.getTitle() +" Added To Library");
			}
		}
		
		else if (content.getType().equals(AudioBook.TYPENAME))
		{
			
			if (audiobooks.contains(content))
			{
				System.out.println(AudioBook.TYPENAME + " " + content.getTitle() + " already downloaded");
			}
			else
			{
				
				audiobooks.add((AudioBook)content);
				System.out.println(AudioBook.TYPENAME + " " + content.getTitle() + " Added To Library");
			}
		}
		
		if (content.getType().equals(Podcast.TYPENAME))
		{
			
			if (podcasts.contains(content))
			{
				System.out.println(Podcast.TYPENAME + " " + content.getTitle() + " already downloaded");
			}
			else
			{
				
				podcasts.add((Podcast)content);
				System.out.println(Podcast.TYPENAME + " " + content.getTitle() + " Added To Library");
			}
		}
	}
	
	
	public void listAllSongs()
	{
		for (int i = 0; i < songs.size(); i++)
		{
			int index = i + 1;
			System.out.print(index + ". ");
			songs.get(i).printInfo();
			System.out.println();	
		}
	}
	
	
	public void listAllAudioBooks()
	{
		for (int i = 0; i < audiobooks.size(); i++)
		{
			int index = i + 1;
			System.out.print(index + ". ");
			audiobooks.get(i).printInfo();
			System.out.println();	
		}
	}
	
 
	public void listAllPodcasts()
	{
		for (int i = 0; i < podcasts.size(); i++)
		{
			int index = i+1;
			System.out.print(index + ". ");
			podcasts.get(i).printInfo();
		}
	}
	
 
	public void listAllPlaylists()
	{
		for (int i = 0; i < playlists.size(); i++)
		{
			int index = i+1;
			System.out.println(index + ". " +  playlists.get(i).getTitle());
		}
	}
	
 
	public void listAllArtists()
	{
		ArrayList<String> artists = new ArrayList<String>();
		
		for (Song song : songs)
		{
			if (!artists.contains(song.getArtist()))
				artists.add(song.getArtist());
		}
		for (int i = 0; i < artists.size(); i++)
		{
			int index = i+1;
			System.out.println(index + ". " + artists.get(i));
		}
	}

	
	public void deleteSong(int index)
	{
		
		if (index < 1 || index > songs.size())
		{
			throw new songNotInListException("Song not found");
		}
		Song song = songs.remove(index-1);
		
		
		for (int i = 0; i < playlists.size(); i++)
		{
			Playlist pl = playlists.get(i);
			if (pl.getContent().contains(song))
				pl.getContent().remove(song);
		}
	}
	
 
	public void sortSongsByYear()
	{
		Collections.sort(songs, new SongYearComparator());
	}

	private class SongYearComparator implements Comparator<Song>
	{
		public int compare(Song a, Song b)
		{
			if (a.getYear() > b.getYear()) return 1;
			if (a.getYear() < b.getYear()) return -1;	
			return 0;
		}
	}


	public void sortSongsByLength()
	{
		Collections.sort(songs, new SongLengthComparator());
	}

	private class SongLengthComparator implements Comparator<Song>
	{
		public int compare(Song a, Song b)
		{
			if (a.getLength() > b.getLength()) return 1;
			if (a.getLength() < b.getLength()) return -1;	
			return 0;
		}
	}

	
	public void sortSongsByName()
	{
		Collections.sort(songs);
	}

	
	
	
	public void playSong(int index) 
	{
		
		if(index < 1 || index > songs.size()){
			throw new songNotInListException("Song Not Found");
		}
		songs.get(index-1).play();
	}
	
	
	public void playPodcast(int index, int season, int episode)
	{
		
		if (index < 1 || index > podcasts.size())
		{
			throw new podcastNotInListException("Podcast Not Found");
		}
		Podcast podcast = podcasts.get(index-1);
		if (season < 1 || season > podcast.getSeasons().size())
		{
			throw new seasonNotFoundException("Season Not Found");
		}
		
		if (index < 1 || index > podcast.getSeasons().get(season-1).episodeTitles.size())
		{
			throw new episodeNotFoundException("Episode Not Found");
		}
		podcast.setSeason(season-1);
		podcast.setEpisode(episode-1);
		podcast.play();
	}
	
	public void printPodcastEpisodes(int index, int season)
	{
		
		if (index < 1 || index > podcasts.size())
		{
			throw new podcastNotInListException("Podcast Not Found");
		}	
		Podcast podcast = podcasts.get(index-1);
		if (season < 1 || season > podcast.getSeasons().size())
		{
			throw new seasonNotFoundException("Season Not Found");
		}
		podcast.printSeasonEpisodes(season);
	}
	
	
	public void playAudioBook(int index, int chapter)
	{
		
		if (index < 1 || index > audiobooks.size())
		{
			throw new audioBookNotFoundException("AudioBook Not Found");
		}
		AudioBook book = audiobooks.get(index-1);
		if (chapter < 1 || chapter > book.getNumberOfChapters())
		{
			throw new chapterNotFoundException("AudioBook Chapter Not Found");
		}
		book.selectChapter(chapter);
		book.play();
	}
	
	public void printAudioBookTOC(int index)
	{
		
		if (index < 1 || index > audiobooks.size())
		{
			throw new audioBookNotFoundException("AudioBook Not Found");
		}
		AudioBook book = audiobooks.get(index-1);
		book.printTOC();
	}
	
  
	public void makePlaylist(String title)
	{
		
		Playlist pl = new Playlist(title);
		if (playlists.contains(pl))
		{
				throw new playListExistsException("Playlist " + title + " Already Exists");
		}
		playlists.add(pl);
	}
	

	public void printPlaylist(String title)
	{
	
		int index = playlists.indexOf(new Playlist(title));
		
		if (index == -1)
		{
			throw new playListNotFound("Playlist Not Found");
		}
		playlists.get(index).printContents();
	}
	

	public void playPlaylist(String playlistTitle)
	{
		
		int index = playlists.indexOf(new Playlist(playlistTitle));
		
		if (index == -1)
		{
			throw new playListNotFound("Playlist Not Found");
		}
		playlists.get(index).playAll();
	}
	
	
	public void playPlaylist(String playlistTitle, int index)
	{
		
    	int plIndex = playlists.indexOf(new Playlist(playlistTitle));
		
		if (plIndex == -1)
		{
			throw new playListNotFound("Playlist Not Found");
		}
		Playlist pl = playlists.get(plIndex);
		
		if (index < 1 || index > pl.getContent().size())
		{
			throw new invalidPlaylistContent("Invalid Playlist AudioContent #");
		}
		System.out.println(pl.getTitle());
		
		
		if (pl.getContent(index).getType().equals(AudioBook.TYPENAME))
		{
			AudioBook book = (AudioBook) pl.getContent(index);
			book.selectChapter(1);
		}
		
		pl.play(index);
	}
	
	
	public void addContentToPlaylist(String type, int index, String playlistTitle)
	{
		
		AudioContent ac = null;
		Playlist playlist = new Playlist(playlistTitle);
		if (playlists.contains(playlist))
		{
			if (type.equalsIgnoreCase("SONG"))
			{
				if (index < 1 || index > songs.size())
				{
					throw new songNotInListException("Song Not Found");
				}
				ac = songs.get(index-1);
				playlists.get(playlists.indexOf(playlist)).addContent(ac);
			}
			else if (type.equalsIgnoreCase("AUDIOBOOK"))
			{
				if (index < 1 || index > audiobooks.size())
				{
					throw new audioBookNotFoundException("AudioBook Not Found");
				}
				ac = audiobooks.get(index-1);
				playlists.get(playlists.indexOf(playlist)).addContent(ac);
			}
			else if (type.equalsIgnoreCase("PODCAST"))
			{
				if (index < 1 || index > podcasts.size())
				{
					throw new podcastNotInListException("Podcast Not Found");
				}
				ac = podcasts.get(index-1);
				playlists.get(playlists.indexOf(playlist)).addContent(ac);
			}
		}
		else
		{
			throw new playListNotFound("Playlist not found");
		}
	}

 
	public void delContentFromPlaylist(int index, String playlistTitle)
	{
		
		int plIndex = playlists.indexOf(new Playlist(playlistTitle));
		
		if (plIndex == -1)
		{
			throw new playListNotFound("Playlist not found");
		}
		Playlist pl = playlists.get(plIndex);
		
		
		if (!pl.contains(index))
		{
			throw new contentNotInList("Content Not In Playlist");
		}
		pl.deleteContent(index);
	}
}


class songNotInListException extends RuntimeException 
{
	public songNotInListException(){}
	
	public songNotInListException(String message)
	{
		super(message);
	}
}

class podcastNotInListException extends RuntimeException
{
	public podcastNotInListException(){}

	public podcastNotInListException(String message)
	{
		super(message);
	}
}

class seasonNotFoundException extends RuntimeException
{
	public seasonNotFoundException(){}

	public seasonNotFoundException(String message){
		super(message);
	}
}

class episodeNotFoundException extends RuntimeException
{
	public episodeNotFoundException(){}

	public episodeNotFoundException(String message)
	{
		super(message);
	}
}

class audioBookNotFoundException extends RuntimeException
{
	public audioBookNotFoundException(){}

	public audioBookNotFoundException(String message)
	{
		super(message);
	}
}

class chapterNotFoundException extends RuntimeException
{
	public chapterNotFoundException(){}

	public chapterNotFoundException(String message)
	{
		super(message);
	}
}

class playListExistsException extends RuntimeException
{
	public playListExistsException(){}

	public playListExistsException(String message)
	{
		super(message);
	}
}

class playListNotFound extends RuntimeException
{
	public playListNotFound(){}

	public playListNotFound(String message)
	{
		super(message);
	}
}

class invalidPlaylistContent extends RuntimeException
{
	public invalidPlaylistContent(){}

	public invalidPlaylistContent(String message)
	{
		super(message);
	}
}

class contentNotInList extends RuntimeException
{
	public contentNotInList(){}

	public contentNotInList(String message)
	{
		super(message);
	}
}

class songExistsException extends RuntimeException
{
	public songExistsException(){}

	public songExistsException(String message)
	{
		super(message);
	}
}

class audioBookExistsException extends RuntimeException
{
	public audioBookExistsException(){}

	public audioBookExistsException(String message)
	{
		super(message);
	}
}

class podCastExistsException extends RuntimeException
{
	public podCastExistsException(){}

	public podCastExistsException(String message)
	{
		super(message);
	}
}

class audioContentNotFound extends RuntimeException
{
	public audioContentNotFound(){}

	public audioContentNotFound(String message)
	{
		super(message);
	}
}