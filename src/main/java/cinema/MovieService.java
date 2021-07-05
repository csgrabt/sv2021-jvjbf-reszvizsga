package cinema;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class MovieService {
    private AtomicLong idGenerator = new AtomicLong();
    private ModelMapper modelMapper;
    private List<Movie> movies = Collections.synchronizedList(new ArrayList<>());


    public MovieService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<MovieDTO> movieList(Optional<String> title) {
        Type targetListType = new TypeToken<List<MovieDTO>>() {
        }.getType();
        List<Movie> filtered = movies
                .stream()
                .filter(n -> title.isEmpty() || n.getTitle().equalsIgnoreCase(title.get()))
                .collect(Collectors.toList());
        return modelMapper.map(filtered, targetListType);


    }

    public MovieDTO findMovieById(long id) {
        Movie movie = movieFinder(id);

        return modelMapper.map(movie, MovieDTO.class);
    }

    private Movie movieFinder(long id) {
        return movies
                .stream()
                .filter(n -> n.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Movie not found:  " + id));
    }

    public MovieDTO createMovie(CreateMovieCommand command) {
        Movie movie = new Movie(
                idGenerator.incrementAndGet(),
                command.getTitle(),
                command.getTime(),
                command.getTotalSpace(),
                command.getTotalSpace()
        );
        movies.add(movie);
        return modelMapper.map(movie, MovieDTO.class);

    }

    public MovieDTO reservationMovie(long id, CreateReservationCommand command) {
        Movie movie = movieFinder(id);
        movie.reservation(command.getRevers());
        return modelMapper.map(movie, MovieDTO.class);
    }

    public MovieDTO updateEmployee(long id, UpdateDateCommand command) {
        Movie movie = movieFinder(id);
        movie.setDate(command.getLocalDateTime());
        return modelMapper.map(movie, MovieDTO.class);


    }
}
