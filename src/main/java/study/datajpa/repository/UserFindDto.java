package study.datajpa.repository;

import lombok.Getter;
import lombok.ToString;

@Getter @ToString(of = {"username", "age"})
public class UserFindDto {

    private final String username;
    private final int age;

    public UserFindDto(String username, int age) {
        this.username = username;
        this.age = age;
    }


}
