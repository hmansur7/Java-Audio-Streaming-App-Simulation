import java.io.InvalidObjectException;
import java.rmi.server.SocketSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;


public class MyAudioUI
{
	public static void main(String[] args)
	{
		
		HashMap<String, Integer> titleToIndex = new HashMap<>();
		HashMap<String, ArrayList<Integer>> artistToIndex = new HashMap<>();
		HashMap<String, ArrayList<Integer>> genreToIndex = new HashMap<>();


		AudioContentStore store = new AudioContentStore(titleToIndex, artistToIndex, genreToIndex);
		
		
		Library library = new Library();

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		
		while (scanner.hasNextLine())
		{
			String action = scanner.nextLine();

			if (action == null || action.equals("")) 
			{
				System.out.print("\n>");
				continue;
			}
			else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
				return;
			
			else if (action.equalsIgnoreCase("STORE"))	
			{
				store.listAll(); 
			}
			else if (action.equalsIgnoreCase("SONGS"))	
			{
				library.listAllSongs(); 
			}
			else if (action.equalsIgnoreCase("BOOKS"))	
			{
				library.listAllAudioBooks(); 
			}
			else if (action.equalsIgnoreCase("PODCASTS"))	
			{
				library.listAllPodcasts(); 
			}
			else if (action.equalsIgnoreCase("ARTISTS"))	
			{
				library.listAllArtists(); 
			}
			else if (action.equalsIgnoreCase("PLAYLISTS"))	
			{
				library.listAllPlaylists(); 
			}
			else if (action.equalsIgnoreCase("DOWNLOAD")) 
			{
				
				try
				{
					
					int startIndex = 0, endIndex = 0;
					
					
					System.out.print("From Store Content #: ");
					if (scanner.hasNextInt())
					{
						startIndex = scanner.nextInt();
						scanner.nextLine(); 
					}

					System.out.print("To Store Content #: ");
					if (scanner.hasNextInt())
					{
						endIndex = scanner.nextInt();
						scanner.nextLine(); 
					}

					
					for(int i = startIndex; i <= endIndex; i++)
					{
						AudioContent content = store.getContent(i);
						library.download(content);
					}

				}
				catch(NullPointerException nullException)
				{
					System.out.println("Content Not Found in Store");
				}
			}
			else if (action.equalsIgnoreCase("PLAYSONG")) 
			{
				
				try
				{
					int index = 0;

					System.out.print("Song Number: ");
					if (scanner.hasNextInt())
					{
						index = scanner.nextInt();
					
						scanner.nextLine(); 
					}
					library.playSong(index);
				}
				catch(songNotInListException exception)
				{
					System.out.println(exception.getMessage());
				}
			}
			else if (action.equalsIgnoreCase("BOOKTOC")) 
			{
				
				try
				{
					int index = 0;

					System.out.print("Audio Book Number: ");
					if (scanner.hasNextInt())
					{
						index = scanner.nextInt();
						scanner.nextLine();
					}
					library.printAudioBookTOC(index);
				}
				catch(audioBookNotFoundException abException)
				{
					System.out.println(abException.getMessage());
				}
			}
			else if (action.equalsIgnoreCase("PLAYBOOK")) 
			{
				
				try
				{
					int index = 0;

					System.out.print("Audio Book Number: ");
					if (scanner.hasNextInt())
					{
						index = scanner.nextInt();
					}
					int chapter = 0;
					System.out.print("Chapter: ");
					if (scanner.hasNextInt())
					{
						chapter = scanner.nextInt();
						scanner.nextLine();
					}
					library.playAudioBook(index, chapter);
				}
				catch(audioBookNotFoundException abException)
				{
					System.out.println(abException.getMessage());
				}
				catch(chapterNotFoundException cException)
				{
					System.out.println(cException.getMessage());
				}
			}
			
			else if (action.equalsIgnoreCase("PODTOC")) 
			{
				
				try
				{
					int index = 0;
					int season = 0;
					
					System.out.print("Podcast Number: ");
					if (scanner.hasNextInt())
					{
						index = scanner.nextInt();
					}
					System.out.print("Season: ");
					if (scanner.hasNextInt())
					{
						season = scanner.nextInt();
						scanner.nextLine();
					}
					library.printPodcastEpisodes(index, season);
				}
				catch(podcastNotInListException pException)
				{
					System.out.println(pException.getMessage());
				}
				catch(seasonNotFoundException sException)
				{
					System.out.println(sException.getMessage());
				}
			}
			else if (action.equalsIgnoreCase("PLAYPOD")) 
			{
				
				try
				{
					int index = 0;

					System.out.print("Podcast Number: ");
					if (scanner.hasNextInt())
					{
						index = scanner.nextInt();
						scanner.nextLine();
					}
					int season = 0;
					System.out.print("Season: ");
					if (scanner.hasNextInt())
					{
						season = scanner.nextInt();
						scanner.nextLine();
					}
					int episode = 0;
					System.out.print("Episode: ");
					if (scanner.hasNextInt())
					{
						episode = scanner.nextInt();
						scanner.nextLine();
					}
					library.playPodcast(index, season, episode);
				}
				catch(podcastNotInListException pException)
				{
					System.out.println(pException.getMessage());
				}
				catch(seasonNotFoundException sException)
				{
					System.out.println(sException.getMessage());
				}
				catch(episodeNotFoundException eException)
				{
					System.out.println(eException.getMessage());
				}
			}
			else if (action.equalsIgnoreCase("PLAYALLPL")) 
			{
				
				try
				{
					String title = "";
					
					System.out.print("Playlist Title: ");
					if (scanner.hasNextLine())
					{
						title = scanner.nextLine();
					}
					library.playPlaylist(title);
				}	
				catch(playListNotFound plException)
				{
					System.out.println(plException.getMessage());
				}
			}
			else if (action.equalsIgnoreCase("PLAYPL")) 
			{
				
				try
				{
					String title = "";
					int index = 0;
			
					System.out.print("Playlist Title: ");
					if (scanner.hasNextLine())
					{
						title = scanner.nextLine();
					}
					System.out.print("Content Number: ");
					if (scanner.hasNextInt())
					{
						index = scanner.nextInt();
						scanner.nextLine();
					}
					library.playPlaylist(title, index);
				}
				catch(playListNotFound plException)
				{
					System.out.println(plException.getMessage());
				}
				catch(invalidPlaylistContent ivException)
				{
					System.out.println(ivException.getMessage());
				}
			}
			
			else if (action.equalsIgnoreCase("DELSONG")) 
			{
				
				try
				{
					int songNum = 0;

					System.out.print("Library Song #: ");
					if (scanner.hasNextInt())
					{
						songNum = scanner.nextInt();
						scanner.nextLine();
					}
					
					library.deleteSong(songNum);
				}
				catch(songNotInListException sException)
				{
					System.out.println(sException.getMessage());
				}
					
			}
			else if (action.equalsIgnoreCase("MAKEPL")) 
			{
				
				try
				{
					String title = "";

					System.out.print("Playlist Title: ");
					if (scanner.hasNextLine())
					{
						title = scanner.nextLine();
					}
					library.makePlaylist(title);
				}
				catch(playListExistsException existsException)
				{
					System.out.println(existsException.getMessage());
				}
			}
			else if (action.equalsIgnoreCase("PRINTPL"))	
			{
				
				try
				{
					String title = "";
					System.out.print("Playlist Title: ");
					if (scanner.hasNextLine())
					{
						title = scanner.nextLine();
					}
					library.printPlaylist(title);
				}	
				catch(playListNotFound plException)
				{
					System.out.println(plException.getMessage());
				}
			}
			
			else if (action.equalsIgnoreCase("ADDTOPL")) 
			{
				
				try
				{
					int contentIndex = 0;
					String contentType = "";
					String playlist = "";
			
					System.out.print("Playlist Title: ");
					if (scanner.hasNextLine())
						playlist = scanner.nextLine();
			
					System.out.print("Content Type [SONG, PODCAST, AUDIOBOOK]: ");
					if (scanner.hasNextLine())
						contentType = scanner.nextLine();
					
					System.out.print("Library Content #: ");
					if (scanner.hasNextInt())
					{
						contentIndex = scanner.nextInt();
						scanner.nextLine(); 
					}
					
					library.addContentToPlaylist(contentType, contentIndex, playlist);	
				}
				catch(songNotInListException sException)
				{
					System.out.println(sException.getMessage());
				}
				catch(audioBookNotFoundException abException)
				{
					System.out.println(abException.getMessage());
				}	
				catch(podcastNotInListException pException)
				{
					System.out.println(pException.getMessage());
				}
				catch(playListNotFound plException)
				{
					System.out.println(plException.getMessage());
				}		
			}
			
			else if (action.equalsIgnoreCase("DELFROMPL")) 
			{
				
				try
				{
					int contentIndex = 0;
					String playlist = "";

					System.out.print("Playlist Title: ");
					if (scanner.hasNextLine())
						playlist = scanner.nextLine();
					
					System.out.print("Playlist Content #: ");
					if (scanner.hasNextInt())
					{
						contentIndex = scanner.nextInt();
						scanner.nextLine(); 
					}
					library.delContentFromPlaylist(contentIndex, playlist);
				}
				catch(playListNotFound plException)
				{
					System.out.println(plException.getMessage());
				}
				catch(contentNotInList cException)
				{
					System.out.println(cException.getMessage());
				}
			}
			else if (action.equalsIgnoreCase("SORTBYYEAR")) 
			{
				library.sortSongsByYear();
			}
			else if (action.equalsIgnoreCase("SORTBYNAME"))
			{
				library.sortSongsByName();
			}
			else if (action.equalsIgnoreCase("SORTBYLENGTH")) 
			{
				library.sortSongsByLength();
			}
			
			else if(action.equalsIgnoreCase("SEARCH"))
			{
				
					String title = "";
					System.out.print("Title: ");
					if(scanner.hasNext())
					{
						 title = scanner.nextLine();
					}
					
					titleToIndex.put(title.toUpperCase(), store.getContentIndex(title));
					store.search(title.toUpperCase());
				
			}
			
			System.out.print("\n>");
		}
	}
}
