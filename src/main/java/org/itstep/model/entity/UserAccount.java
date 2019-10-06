package org.itstep.model.entity;

import lombok.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAccount {

    private int id;

    private String password;

    private String firstName;

    private String lastName;

    private String email;

    private Set<TestRating> testRatings;

    private List<String> roles;

}