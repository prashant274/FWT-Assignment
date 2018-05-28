package com.yash.moviebookingapp;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.yash.moviebookingapp.dao.ScreenDAO;
import com.yash.moviebookingapp.daoimpl.MovieServiceImpl;
import com.yash.moviebookingapp.daoimpl.ScreenDaoImpl;
import com.yash.moviebookingapp.exception.FileNotExistException;
import com.yash.moviebookingapp.exception.TimeSloteException;
import com.yash.moviebookingapp.model.Movie;
import com.yash.moviebookingapp.model.Screen;
import com.yash.moviebookingapp.service.MovieService;
import com.yash.moviebookingapp.service.ScreenService;
import com.yash.moviebookingapp.serviceimpl.ScreenServiceImpl;
import com.yash.moviebookingapp.util.FileUtil;
import com.yash.moviebookingapp.util.MenuUtil;

public class MovieBookingSystem {

	private MenuUtil menuUtil;
	private ScreenService screenService;
	private MovieService movieService;
	private ScreenDAO screenDao;
	private FileUtil fileUtil;
	private static int screeId = 100;

	public void launchAPP() {
		initializeSystem();
		Scanner scanner = new Scanner(System.in);
		Boolean isExit = false;
		while (!isExit) {
			menuUtil.showMainMenu();
			String userChoice = scanner.nextLine();
			int userInput=-1;
			try{
			 userInput=Integer.parseInt(userChoice);
			}catch (RuntimeException runtimeException) {
				System.out.println("invalid input");
				continue;
			}
			switch (userInput) {
			case 0:
				System.exit(0);
				isExit=true;
			case 1:
				System.out.println("enter screen name : ");
				String screenName = scanner.nextLine();
				Screen screenToAdd = new Screen(screeId++, screenName);
				addScreenUsingService(screenToAdd);
				break;
			case 2:
				addMovieToScreen(scanner);
				break;
			default:
				System.out.println("Invalid Input");
			}

		}
	}

	private void addMovieToScreen(Scanner scanner) {
		System.out.println("Enter Screen name to add movie: ");
		String screeNameToAddMovie = scanner.nextLine();
		Screen screeToAddMovie = new Screen(screeId++, screeNameToAddMovie);
		System.out.println("Enter Movie title : ");
		String movieTitle = scanner.nextLine();
		System.out.println("Enter Movie Duration in (HH:MM:SS):");
		String movieDuration = scanner.nextLine();
		Time totalMovieDuration =Time.valueOf(movieDuration);
		System.out.println("Enter Production name");
		String productionName = scanner.nextLine();
		System.out.println("Enter movies actors");
		boolean isMoreActorToAdd = true;
		List<String> actors = new ArrayList<String>();
		while (isMoreActorToAdd) {
			System.out.println("Enter actor/actress name");
			String actor = scanner.nextLine();
			System.out.println("More actors to add? (y/n)");
			String addActorChoice = scanner.next();
			if (addActorChoice.equalsIgnoreCase("n")) {
				isMoreActorToAdd = false;
			} else if (addActorChoice.equalsIgnoreCase("y")) {
				actors.add(actor);
			} else {
				System.out.println("invalid input");
			}
		}
		Movie movieToAddScreen=new Movie(movieTitle, productionName, actors, totalMovieDuration);
		try {
			movieService.addMovieToScreen(movieToAddScreen, screeToAddMovie);
		} catch (TimeSloteException timeSloteException) {
			System.out.println(timeSloteException.getMessage());
		}
	}

	private void addScreenUsingService(Screen screenToAdd) {
		try {
			int rowsAffected=screenService.addScreen(screenToAdd);
			if(rowsAffected==1){
				System.out.println("Screen Added SuccessFully");
			}
		} catch (FileNotExistException fileNotExistException) {
			System.out.println(fileNotExistException.getMessage());
		} catch (RuntimeException runtimeException) {
			System.out.println(runtimeException.getMessage());
		}
	}

	private void initializeSystem() {
		menuUtil = new MenuUtil();
		fileUtil = new FileUtil();
		screenDao = new ScreenDaoImpl(fileUtil);
		screenService = new ScreenServiceImpl(screenDao);
		movieService = new MovieServiceImpl(screenService);
	}

}
