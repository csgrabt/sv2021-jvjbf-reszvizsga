package cinema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Movie {
    private Long id;
    private String title;
    private LocalDateTime date;
    private int totalSpace;
    private int freeSpaces;


    public void reservation(int number) {
        if (0 <= freeSpaces - number) {
            this.freeSpaces = freeSpaces - number;
        } else {
            throw new IllegalStateException("No enough free place");
        }
    }

}
