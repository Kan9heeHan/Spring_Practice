package practice.spring_mysql.domain.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    @GetMapping("/post/write.do")
    public String openPostWrite(@RequestParam(value="id", required = false) final Long id,Model model){
        if(id!=null){
            PostResponse post=postService.findPostById(id);
            model.addAttribute("post",post);
        }
        return "post/write";
    }

    @PostMapping("/post/save.do")
    window.onload = () => {
        renderPostInfo();
    }
    function renderPostInfo(){
        const post=[[${post}]];
        if(!post){return false;}
        const form = document.getElementById('saveForm');
        const fields = ['id', 'title', 'content', 'writer', 'noticeYn'];
        form.isNotice.checked = post.noticeYn;

        fields.forEach(field => {
                form[field].value = post[field];
        })
    }
    public String savePost(final PostRequest params){
        postService.savePost(params);
        return "redirect:/post/list.do";
    }

    @GetMapping("/post/list.do")
    public String openPostList(Model model){
        List<PostResponse> posts=postService.findAllPost();
        model.addAttribute("posts",posts);
        return "post/list";
    }

    @GetMapping("/post/view.do")
    public String openPostView(@RequestParam final Long id ,Model model){
        PostResponse post= postService.findPostById(id);
        model.addAttribute("post",post);
        return "post/view";
    }
}
