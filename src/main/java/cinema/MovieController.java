package cinema;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequestMapping("api/cinema")
@RestController
public class MovieController {
    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<MovieDTO> listMovies(@RequestParam Optional<String> title) {
        return movieService.movieList(title);
    }

    @GetMapping("/{id}")
    public MovieDTO findEmployeeById(@PathVariable("id") long id) {
        return movieService.findMovieById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MovieDTO createMovie(@Valid @RequestBody CreateMovieCommand command) {
        return movieService.createMovie(command);
    }

    @PostMapping("/{id}/reserve")
    public MovieDTO updateMovie(@PathVariable("id") long id, @RequestBody CreateReservationCommand command) {
        return movieService.reservationMovie(id, command);
    }

    @PutMapping("/{id}")
    public MovieDTO updateMovie(@PathVariable("id") long id, @RequestBody UpdateDateCommand command) {
        return movieService.updateEmployee(id, command);
    }

}
