package study.datajpa.repository;

public class UsernameOnlyDto {

    private final String username;

    /**
     * 생성자의 파라미터 이름으로 매칭시켜서 Projection 하는 것도 가능하다.
     */
    public UsernameOnlyDto(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
