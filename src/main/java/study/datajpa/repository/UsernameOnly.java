package study.datajpa.repository;

import org.springframework.beans.factory.annotation.Value;

public interface UsernameOnly {


    /**
     * Spring EL을 사용해서 target 의 property 를 직접 가져올 수도 있음.<br/>
     * 이렇게 <strong>@Value Annotation</strong> 을 사용해서 target 의 property 를 직접 조회하는 것을<br/>
     * <strong>Open Projection</strong> 이라 한다. <br/>
     * 일단 Entity 의 모든 정보를 다 가져오고 (Select 절을 모두 날리고)<br/>
     * Application 상에서 계산을 해서 출력.<br/>
     * <strong>Close Projection</strong> 은 <strong>@Value Annotation</strong> 없이 정확하게 getter 를 통해 매칭되면 <br/>
     * Select 절이 최적화되어서 딱 그것만 가져온다.
     */
    @Value("#{target.username + ' ' + target.age + ' ' + target.createdDate.format(T(java.time.format.DateTimeFormatter).ofPattern('yyyy-MM-dd hh:mm:ss EEE'))}")
    String getUsername();
}
