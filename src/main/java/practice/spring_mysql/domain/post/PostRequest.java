package practice.spring_mysql.domain.post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequest {
    private long id;
    private String title;
    private String content;
    private String writer;
    private Boolean notice_boolean;
}
