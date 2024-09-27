package org.example.data_response;

import lombok.Getter;
import lombok.Setter;

import java.text.DateFormat;
import java.time.LocalDate;
@Setter @Getter
public class CountYoungestEldestWorkers {
    private String type;
    private String name;
    private LocalDate birthday;
}
