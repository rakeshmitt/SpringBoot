package org.rakesh.practice.blogapi.articles;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articles")
public class ArticlesController {

    @GetMapping("")
    public String getArticles(){
        return "all articles";
    }

    @GetMapping("/private")
    public String getPrivateArticles(@AuthenticationPrincipal Long userId){
        return "all private articles for "+ userId ;
    }
}
