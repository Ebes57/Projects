package edu.missouristate.edit;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

import static org.jsoup.helper.StringUtil.writeToFile;

public class NewPage {
    public static void main(String[] args) {
    }
    public static String createPage(String filename, String content){
        try { // Load the HTML file
            String src= "src/main/resources/static/";
            src += filename + ".html";

            File newFile = new File(src);
            File template = new File("src/main/resources/templates/template.html");
            Document doc = Jsoup.parse(template, "UTF-8");

            writeToFile(doc.outerHtml(), newFile);

            Element div = doc.getElementById("whitebox");
            if (div != null) { // Output the div content to the console
                System.out.println("Div content: " + div.html());
            } else {
                System.out.println("Div with the specified ID not found.");
            }
            //String testContent = "<p>TEST</p>";
            //div.html(testContent);
            div.html(content);

            org.jsoup.helper.StringUtil.writeToFile(doc.outerHtml(), newFile);
            System.out.println("Div content updated successfully.");

            return "Success!";

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
    public static void updateNavBar(String html){
        try { // Load the HTML file
            File newFile = new File("src/main/resources/static/navigation_bar.html");
            Document doc = Jsoup.parse(newFile, "UTF-8");


            Element div = doc.getElementById("nav");
            if (div != null) { // Output the div content to the console
                System.out.println("Div content: " + div.html());
            } else {
                System.out.println("Div with the specified ID not found.");
            }

            div.html(html);

            org.jsoup.helper.StringUtil.writeToFile(doc.outerHtml(), newFile);
            System.out.println("Div content updated successfully.");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

