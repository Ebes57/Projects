package edu.missouristate.edit;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/write-page")
public class PageContent {

    public static void saveFile(String filename, String content) {
        String fileName = filename + ".txt";


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
            System.out.println("Data saved to file successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static String sendPageContent(String filename, String html) {
        try { // Load the HTML file
            filename = "src/main/resources/static/" + filename + ".html";
            System.out.println(filename);
            File newFile = new File(filename);
            System.out.println(html);
            Document doc = Jsoup.parse(newFile, "UTF-8");


            Element div = doc.getElementById("whitebox");
            div.html(html);

            org.jsoup.helper.StringUtil.writeToFile(doc.outerHtml(), newFile);
            System.out.println("Div content updated successfully.");

            return "Success!";


        } catch (IOException e) {
            e.printStackTrace();
            return "Error writing to file: " + e.getMessage();
        }
    }
}
