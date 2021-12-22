package at.technikumwien.statsservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Author {

    private Long id;

    private String firstname;

    private String lastname;

    public Author(String firstname, String lastname){
        this(null, firstname,lastname);
    }

}
