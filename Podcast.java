import java.util.ArrayList;


public class Podcast extends AudioContent
{
	public static final String TYPENAME =	"PODCAST";
	private String host; 
		
	private ArrayList<Season> seasons;
	private int currentSeason = 0;
	private int currentEpisode = 0;
	
	public Podcast(String title, int year, String id, String type, String host, ArrayList<Season> seasons)
	{
		super(title, year, id, type, "", 0);
		this.host = host;
		if (seasons == null)
		{
			seasons = new ArrayList<Season>();
			
		}
		else
			this.seasons = seasons;
	}
	
	public String getType()
	{
		return TYPENAME;
	}
	
	public void printInfo()
	{
		super.printInfo();
		System.out.println("Host: " + host);
		System.out.println("Seasons: " + seasons.size());
	}
	
	public void play()
	{
		setAudioFile(seasons.get(currentSeason).episodeTitles.get(currentEpisode) + ".\n" + seasons.get(currentSeason).episodeFiles.get(currentEpisode));
		super.play();
	}
	
	public void printSeasonEpisodes(int season)
	{
		for (int i = 0; i < seasons.get(season-1).episodeTitles.size(); i++)
		{
			String title = seasons.get(season-1).episodeTitles.get(i);
			int epi = i + 1;
			System.out.println("Episode " + epi + ". " + title + "\n");
		}
	}
	
	public String getHost()
	{
		return host;
	}
	public void setHost(String host)
	{
		this.host = host;
	}
	
	
	public void setSeason(int season)
	{
		if (season >= 1 && season <= seasons.size())
		{
			currentSeason = season - 1;
		}
	}

	public void setEpisode(int episode)
	{
		if (episode >= 1 && episode <= seasons.get(currentSeason).episodeTitles.size())
		{
			currentEpisode = episode - 1;
		}
	}

	public ArrayList<Season> getSeasons()
	{
		return seasons;
	}
	public void setSeasons(ArrayList<Season> seasons)
	{
		this.seasons = seasons;
	}
	
	public boolean equals(Object other)
	{
		Podcast otherPod = (Podcast) other;
		return super.equals(other) && host.equals(otherPod.host); 
	}
}
