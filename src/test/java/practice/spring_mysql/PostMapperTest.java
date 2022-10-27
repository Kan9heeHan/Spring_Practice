package practice.spring_mysql;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import practice.spring_mysql.domain.post.PostMapper;
import practice.spring_mysql.domain.post.PostResponse;
import practice.spring_mysql.domain.post.PostRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
public class PostMapperTest {
    @Autowired
    PostMapper postMapper;

    @Test
    void save(){
        PostRequest params=new PostRequest();
        params.setTitle("1번 게시글");
        params.setContent("1번 게시글 내용");
        params.setWriter("ㅇㅇ");
        params.setNotice_boolean(false);
        postMapper.save(params);

        List<PostResponse> posts = postMapper.findAll();
        System.out.println("전체 게시글 개수는 : " + posts.size() + "개입니다.");
    }

    @Test
    void findById(){
        PostResponse post=postMapper.findById(1L);
        try{
            String postJson=new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(post);
            System.out.println(postJson);
        }catch(JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }

    @Test
    void update() {
        PostRequest params=new PostRequest();
        params.setId(1L);
        params.setTitle("수정된 1번 게시글");
        params.setContent("수정된 1번 게시글 내용");
        params.setWriter("매니저");
        params.setNotice_boolean(true);
        postMapper.update(params);

        PostResponse post=postMapper.findById(1L);
        try{
            String postJson=new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(post);
            System.out.println(postJson);
        }catch(JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }

    @Test
    void delete(){
        System.out.println("삭제 전 게시글의 수: "+postMapper.findAll().size());
        postMapper.deleteById(1L);  //실제로 데이터를 삭제하는 것은 아니다.
        System.out.println("삭제 후 게시글의 수: "+postMapper.findAll().size());
    }
}
