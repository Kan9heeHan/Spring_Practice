package practice.spring_mysql;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import practice.spring_mysql.domain.post.PostRequest;
import practice.spring_mysql.domain.post.PostResponse;
import practice.spring_mysql.domain.post.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PostServiceTest {
    @Autowired
    PostService postService;

    @Test
    void save(){
        PostRequest params=new PostRequest();
        params.setTitle("1번 게시글");
        params.setContent("1번 게시글 내용");
        params.setWriter("ㅇㅇ");
        params.setNotice_boolean(false);
        postService.savePost(params);

        List<PostResponse> posts = postService.findAllPost();
        System.out.println("전체 게시글 개수 : " + posts.size());
    }

    @Test
    void findPostById(){
        PostResponse post=postService.findPostById(3L);
        try{
            String postJson=new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(post);
            System.out.println(postJson);
        }catch(JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }

    @Test
    void updatePost() {
        PostRequest params=new PostRequest();
        params.setId(3L);
        params.setTitle("수정된 3번 게시글");
        params.setContent("수정된 3번 게시글 내용");
        params.setWriter("매니저");
        params.setNotice_boolean(true);
        postService.updatePost(params);

        PostResponse post=postService.findPostById(3L);
        try{
            String postJson=new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(post);
            System.out.println(postJson);
        }catch(JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }

    @Test
    void deletePost(){
        System.out.println("삭제 전 게시글의 수: "+postService.findAllPost().size());
        postService.deletePost(3L);  //실제로 데이터를 삭제하는 것은 아니다.
        System.out.println("삭제 후 게시글의 수: "+postService.findAllPost().size());
    }
}
