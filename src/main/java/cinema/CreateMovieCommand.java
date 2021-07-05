package cinema;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMovieCommand {
    @NotBlank(message = "Name cannot be blank")
    private String title;
    private LocalDateTime time;
    @Min(20)
    private int totalSpace;



}
