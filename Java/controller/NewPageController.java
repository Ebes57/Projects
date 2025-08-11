package edu.missouristate.controller;

import edu.missouristate.edit.NewPage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/add")
public class NewPageController {

    @PostMapping("/page")
    public String getPage(@RequestBody GetNewPageData data) {
        String filename = data.getFileName();
        String content = data.getContent();
        if(content == null){
            content = "";
        }

        return NewPage.createPage(filename, content);
    }
    @PostMapping("/nav")
    public void updateNav(@RequestBody GetNewPageData data) {
        String html = data.getHtml();
        NewPage.updateNavBar(html);
    }
    public static class GetNewPageData{
        private String fileName;
        private String content;
        private String html;

        public String getHtml() {
            return html;
        }

        public void setHtml(String html) {
            this.html = html;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

}
