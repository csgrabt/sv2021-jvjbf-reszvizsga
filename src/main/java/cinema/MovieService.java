package cinema;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class MovieService {
    private AtomicLong idGenerator = new AtomicLong();
    private ModelMapper modelMapper;
    private List<Movie> movies = Collections.synchronizedList(new ArrayList<>());


    public MovieService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
