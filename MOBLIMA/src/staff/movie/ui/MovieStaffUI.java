package staff.movie.ui;

import base.AbstractUI;
import cache.Cache;
import staff.movie.crud.MovieCRUD;
import staff.movie.entity.Movie;

public class MovieStaffUI extends AbstractUI {

	@Override
	public void start() {
		// TODO Auto-generated method stub
		System.out.println();
		System.out.println("0 : List movie & View movie details");
		System.out.println("1 : Create/Update/Remove Movie Listing");
		System.out.println("2 : Back");
		
		int choice = this.getInputChoice(0, 2);
		
		this.run(choice);
	}
	
	public void run(int choice) {
		if (choice==2) {
			this.goBack();
			return;
		}
		MovieCRUD<Movie> crud = new MovieCRUD<>(Movie.class);
		Cache.setCurrentCRUD(crud);
		
		switch(choice) {
		case 0:
			this.intent(new MovieListUI());
			break;
		case 1:
			this.intent(new MovieCRUDUI());
			break;
		}
	}

}