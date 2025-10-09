package main.utils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;

import main.cli.Logging;

import main.models.Day;
import main.models.Movie;
import main.models.Show;
import main.models.enums.Color;
import main.models.enums.Room;

import static main.utils.Constants.*;

public class Data {
    private static final Logger logger = new Logging(Data.class, LOG_FOLDER_ABS_PATH).getLogger();

    private static void populateDays(Map<String, Boolean> weekdays) {
        for (Map.Entry<String, Boolean> day : weekdays.entrySet()) {
            WEEKDAYS.add(new Day(day.getKey(), day.getValue()));

            logger.info(String.format("Added \"%s\" as \"%s\" to the list of weekdays.", day.getKey(), day.getValue() ? "OPEN" : "CLOSED"));
        }

        logger.info(String.format("Successfully added %d weekdays.", weekdays.size()));
    }

    private static void populateMovies(Map<String, Room> movieList) {
        int index = 1;

        for (Map.Entry<String, Room> movie : movieList.entrySet()) {
            String id = String.format("M%02d", ++index);
            MOVIES.put(id, new Movie(id, movie.getKey(), movie.getValue()));

            logger.info(String.format("Added \"%s\" to the list of movies.", movie.getKey()));
        }

        logger.info(String.format("Successfully added %d movies.", movieList.size()));
    }

    private static int generateRandomNumber(int bound) {
        return new Random().nextInt(bound);
    }

    private static int generateRandomNumber(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }

    private static void populateSchedule(Map<String, List<Show>> schedule) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        int index = 1;

        logger.info("Starting schedule population.");

        for (Map.Entry<String, Boolean> weekday : WEEKDAY_MAP.entrySet()) {
            if (!weekday.getValue()) {
                logger.info(String.format("Skipped \"%s\" since it was marked as \"CLOSED\".", weekday.getKey()));
                continue;
            }

            List<Show> showList = new ArrayList<>();
            int maxShows = generateRandomNumber(Constants.MIN_SHOWS_PER_DAY, Constants.MAX_SHOWS_PER_DAY);

            LocalTime newTime = Constants.OPENING_TIME;

            for (int showNum = 1; showNum <= maxShows; ++showNum) {
                List<Movie> movieList = new ArrayList<>(MOVIES.values());
                Movie randomMovie;

                boolean alreadyScheduled;

                do {
                    randomMovie = movieList.get(generateRandomNumber(movieList.size()));
                    alreadyScheduled = false;

                    for (Show show : showList) {
                        if (show.getMovie().equals(randomMovie)) {
                            alreadyScheduled = true;

                            logger.info(String.format("Skipped \"%s\" since it was already scheduled.", randomMovie));

                            break;
                        }
                    }
                } while (alreadyScheduled);

                String leadingZero = String.format(maxShows >= 10 ? "%02d" : "%d", showNum);
                String showId = String.format("D%dS%s", index, leadingZero);

                showList.add(new Show(showId, newTime.format(timeFormatter), randomMovie));

                newTime = newTime.plusMinutes(Constants.INTERVAL_BETWEEN_SHOWS);

                logger.info(String.format("Added show \"%s\" to the list of shows.", showId));
            }

            SCHEDULE.put(Day.getDayNameFromNumber(index), showList);

            logger.info(String.format("Added \"%s\" to the schedule as day %d.", weekday.getKey(), index));

            ++index;
        }

        logger.info(String.format("Successfully generated schedules for %d weekdays.", index));
    }

    public static void prettyPrintShows(Day day) {
        List<Show> shows = SCHEDULE.get(day.getName());

        if (shows == null || shows.isEmpty()) {
            System.out.println(Color.paint(Color.RED, "Ez daude saiorik egun honetan."));
            return;
        }

        for (Show show : shows) {
            String showId = Color.paint(Color.GREEN, show.getId());
            String showTime = Color.paint(Color.YELLOW, show.getTime());

            int tickets = show.getMovie().getTickets();

            System.out.println(String.format("    [ID: %s] @ %s (Sarrerak: %d/%d) - %s", showId, showTime, tickets, MAX_TICKETS_PER_MOVIE, show.getMovie()));
        }
    }

    public static void prettyPrintMovies() {
        System.out.println(String.format("%s: %s", Color.paint(Color.YELLOW, "Pelikula Kopurua"), MOVIES.size()));
        System.out.println();

        for (Movie movie : MOVIES.values()) {
            System.out.println(String.format("    (ID: %s) %s", Color.paint(Color.GREEN, movie.getId()), movie.getTitle()));
        }
    }

    public static void prettyPrintRooms() {
        System.out.println(String.format("%s: %s", Color.paint(Color.YELLOW, "Gela Kopurua"), Room.values().length));
        System.out.println();

        int index = 1;

        for (Room room : Room.values()) {
            System.out.println(String.format("    [R%s] %s", Color.paint(Color.GREEN, String.valueOf(index)), room.name));
            index++;
        }
    }

    public static void prettyPrintWorkingHours() {
        System.out.println(String.format("Irekiera: %s", Color.paint(Color.YELLOW, OPENING_TIME.toString())));
        System.out.println(String.format("Itxiera: %s", Color.paint(Color.YELLOW, CLOSING_TIME.toString())));
    }

    public void init() {
        logger.info("Starting data population.");

        Data.populateDays(WEEKDAY_MAP);
        Data.populateMovies(MOVIE_MAP);

        Data.populateSchedule(SCHEDULE);
    }
}
