import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

import java.util.HashMap;


public class AudioContentStore
{
		private ArrayList<AudioContent> contents; 
		private HashMap<String, Integer> titleToIndex = new HashMap<>();
		private HashMap<String, ArrayList<Integer>> artistToIndex = new HashMap<>();
		private HashMap<String, ArrayList<Integer>> genreToIndex = new HashMap<>();

		public AudioContentStore(HashMap<String, Integer> titleToIndex, HashMap<String, ArrayList<Integer>> artistToIndex, HashMap<String, ArrayList<Integer>> genreToIndex)
		{	
			try
			{
			 contents = makeAudioContents();
			 this.titleToIndex = titleToIndex;
			 this.artistToIndex = artistToIndex;
			 this.genreToIndex = genreToIndex;
			}
			catch(IOException e)
			{
				System.out.println(e.getMessage());
				System.exit(1);
			}
		}

		private ArrayList<AudioContent> makeAudioContents() throws IOException
		{
			
			ArrayList<AudioContent> audioContents = new ArrayList<>();
			
			
			File contents = new File("store.txt");
			
			Scanner in = new Scanner(contents);

			
			while(in.hasNextLine())
			{
				
				String type = in.nextLine();

				
				if(type.equals(Song.TYPENAME))
				{
					
					String id = in.nextLine();
					String title = in.nextLine();
					int year = Integer.parseInt(in.nextLine());
					int length = Integer.parseInt(in.nextLine());	
					String artist = in.nextLine();
					String composer = in.nextLine();
					String genre = in.nextLine();
					int lyriclines = Integer.parseInt(in.nextLine());
					String lyrics = "";

					
					for (int i = 0; i < lyriclines; i++)
					{
						lyrics += in.nextLine() + "\n";
					}

					audioContents.add(new Song(title, year, id, Song.TYPENAME, "", length, artist, composer, Song.Genre.valueOf(genre), lyrics));
				}

				
				else if(type.equals(AudioBook.TYPENAME))
				{
					
					String id = in.nextLine();
					String title = in.nextLine();
					int year = Integer.parseInt(in.nextLine());
					int length = Integer.parseInt(in.nextLine());	
					String author = in.nextLine();
					String narrator = in.nextLine();
					
					ArrayList<String> chapterTitles = new ArrayList<>();
					ArrayList<String> chapters = new ArrayList<>();
					int chapterTLines = Integer.parseInt(in.nextLine());

					
					for (int i = 0; i < chapterTLines; i++)
					{
						chapterTitles.add(in.nextLine());
					}


					
					for (int i = 0; i < chapterTitles.size(); i++)
					{
						String chapter = "";

						int chapterLines = Integer.parseInt(in.nextLine());

						for(int j = 0; j < chapterLines; j++)
						{
							chapter += in.nextLine() + "\n";
						}
						
						chapters.add(chapter);
					}

					
					audioContents.add(new AudioBook(title, year, id, AudioBook.TYPENAME, "", length, author, narrator, chapterTitles, chapters));
				}
			}
			
			in.close();
			return audioContents;
		}

		public AudioContent getContent(int index)
		{
			if (index < 1 || index > contents.size())
			{
				return null;
			}
			return contents.get(index-1);
		}
		
		public int getContentIndex(String title)
		{
			int index = 0;

			for(AudioContent content: contents)
			{
				if(title.equalsIgnoreCase(content.getTitle()))
				{
					index = contents.indexOf(content);
				}
			}
			
			return index;
		}

		public void listAll()
		{
			for (int i = 0; i < contents.size(); i++)
			{
				int index = i + 1;
				System.out.print(index + ". ");
				contents.get(i).printInfo();
				System.out.println();
			}
		}

		public void search(String title)
		{
			if(!titleToIndex.containsKey(title))
			{
				throw new audioContentNotFound("No matches for " + title);
			}
			else
			{
				System.out.print(titleToIndex.get(title) + 1 + ". ");
				contents.get(titleToIndex.get(title)).printInfo();
			}
		}

		

}
