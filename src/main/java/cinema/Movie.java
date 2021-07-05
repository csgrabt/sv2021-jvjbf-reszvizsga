package cinema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Movie {
    private Long id;
    private String title;
    private LocalDate date;
    private int totalSpace;
    private int freeSpace;


    public void reservation(int number) {
        if (0 <= freeSpace - number) {
            this.freeSpace = freeSpace - number;
        } else {
            throw new IllegalStateException("No enough free place");
        }
    }

}
