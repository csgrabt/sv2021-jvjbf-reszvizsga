package cinema;


import org.springframework.web.bind.annotation.*;

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
}
