package at.technikumwien.rewardservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Attraction {

    private Long id;

    private String name;

    private String description;

    public Attraction(String name, String description)
    {
        this(null,name, description);
    }
}
