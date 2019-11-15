package staff.movie.ui;

import base.AbstractUI;
import staff.movie.entity.Movie;

public class MovieDetailUI extends AbstractUI {
	Movie movie;
	public MovieDetailUI(Movie movie) {
		this.movie = movie;
	}
	@Override
	public void start() {
		// TODO Auto-generated method stub
		System.out.println();
		System.out.println("0 : View movie details");
		System.out.println("1 : View movie reviews");
		System.out.println("2 : Add your own review");
		System.out.println("3 : Back to previous menu");
		
		int choice = this.getInputChoice(0, 3);
		this.run(choice);
	}
	
	public void run(int choice) {
		switch(choice) {
		case 0:
			this.movie.printMovieDetail();
			this.start();
			break;
		case 1:
			this.movie.printReview();
			this.start();
			break;
		case 2:
			if (this.movie.checkReviewExist()) {
				System.out.println("You have already given a review for this movie!");
			} else {
				System.out.println("Enter your review:");
				String review = this.getInputString();
				System.out.println("Enter your rating (out of 5):");
				int rating = this.getInputChoice(0,5);
				this.movie.addReview(review, rating);
			}
			this.start();
			break;
		case 3:
			this.goBack();
			break;
		}
	}

}
