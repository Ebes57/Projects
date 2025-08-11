package edu.missouristate.controller;

import edu.missouristate.edit.PageContent;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/edit")
public class EditController {
    @PostMapping("/page")
    public void getData(@RequestBody GetPageData data){
        String filename = data.getFilename();
        String contents = data.getContents();

        PageContent.saveFile(filename, contents);
    }

    @PostMapping("/content")
    public String getPageContent(@RequestBody GetPageData data){
        String filename = data.getFilename();
        String html = data.getContents();
        System.out.println(filename);
        return PageContent.sendPageContent(filename, html);
    }

    public static class GetPageData{
        private String filename;
        private String contents;

        public String getFilename() {
            return filename;
        }

        public String getContents() {
            return contents;
        }
    }
}
