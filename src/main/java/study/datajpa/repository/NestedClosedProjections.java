package study.datajpa.repository;


/**
 * Root는 정확하게 가져오는데 비해, 연관된 것은 모두 조회한 후 계산한다.<br/>
 * <strong>최적화 불가능</strong>
 */
public interface NestedClosedProjections {

    String getUsername();
    TeamInfo getTeam();

    interface TeamInfo {
        String getName();
    }

}
