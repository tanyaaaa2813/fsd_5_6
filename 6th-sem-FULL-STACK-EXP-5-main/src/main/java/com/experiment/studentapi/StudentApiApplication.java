package com.experiment.studentapi.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder  // <--- THIS IS THE MISSING PIECE
public class Student {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
