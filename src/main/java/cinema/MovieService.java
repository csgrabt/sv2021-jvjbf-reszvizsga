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
}
