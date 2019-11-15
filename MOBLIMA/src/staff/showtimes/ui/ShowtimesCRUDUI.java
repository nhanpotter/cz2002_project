package staff.showtimes.ui;

import base.AbstractUI;
import staff.movie.crud.MovieCRUD;
import staff.movie.entity.Movie;
import staff.showtimes.crud.CinemaCRUD;
import staff.showtimes.crud.CineplexCRUD;
import staff.showtimes.crud.ShowtimesCRUD;
import staff.showtimes.entity.Cinema;
import staff.showtimes.entity.Cineplex;
import staff.showtimes.entity.Showtimes;
import util.DateTimeHelper;
import util.TextDB;

public class ShowtimesCRUDUI extends AbstractUI {

	@Override
	public void start() {
		// TODO Auto-generated method stub
		System.out.println();
		System.out.println("Choose Cineplex:");
		CineplexCRUD<Cineplex> cineplexCRUD = new CineplexCRUD<>(Cineplex.class);
		int noCineplexChoice = cineplexCRUD.printChoices();
		int cineplexChoice = this.getInputChoice(0, noCineplexChoice-1);
		
		System.out.println("Choose Cinema");
		Cineplex cineplex = cineplexCRUD.getCineplex(cineplexChoice);
		CinemaCRUD<Cinema> cinemaCRUD = cineplex.getCinemaCRUD();
		int noCinemaChoice = cinemaCRUD.printChoices();
		int cinemaChoice = this.getInputChoice(0, noCinemaChoice-1);
		Cinema cinema = cinemaCRUD.getCinema(cinemaChoice);
		
		System.out.println("Choose Preview and Now Showing Movies");
		MovieCRUD<Movie> movieCRUD = new MovieCRUD<>(Movie.class);
		int noMovieChoice = movieCRUD.printChoicesForShowtimes();
		int movieChoice = this.getInputChoice(0, noMovieChoice-1);
		Movie movie = movieCRUD.getMovie(movieChoice);
		
		System.out.println("Enter Showing Date");
		String dateStr = this.getInputDate();
		
		System.out.println("Enter Movie Start Time:");
		String timeStr = this.getInputTime(dateStr, movie.getDuration());
		
		this.run(cineplex, dateStr, cinema, timeStr, movie);
	}

	// Need to check if pass next day
	public String getInputTime(String dateStr, int movieDuration) {
		String input;
		try {
			while (true) {
				System.out.println("Enter Time in " + DateTimeHelper.TIME_FORMAT);
				input = this.getInputString();
				if (DateTimeHelper.isToday(dateStr)) {
					if (!DateTimeHelper.checkAfterMinutesFromNow(input)) {
						System.out.println("Input time 15 minutes after current time");
						continue;
					}
				} 
				if (DateTimeHelper.getMinutesTillMidnight(input)>movieDuration)
					break;
				System.out.println("Cannot pass midnight");
			}
		} catch (Exception e) {
			System.out.println("Wrong Input! Try again");
			return this.getInputTime(dateStr, movieDuration);
		}
		return input;
	}
	
	public void run(Cineplex cineplex, String dateStr, Cinema cinema, String timeStr, Movie movie ) {
		ShowtimesCRUD<Showtimes> crud = new ShowtimesCRUD<>(Showtimes.class, cineplex.getId(), dateStr);
		Showtimes showtimes = new Showtimes(cinema, timeStr, movie);
		crud.create(showtimes);
		System.out.println("Create Showtimes Successfully");
		this.goBack();
	}
}
