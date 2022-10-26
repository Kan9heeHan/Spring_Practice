package practice.spring_mysql.domain.post;

import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class PostResponse {
    private long id;
    private String title;
    private String content;
    private String writer;
    private int view_count;
    private Boolean notice_boolean;
    private Boolean delete_boolean;
    private LocalDateTime create_date;
    private LocalDateTime modified_date;
}
