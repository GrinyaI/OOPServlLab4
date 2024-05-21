package com.example.oopservllab4;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "bookServlet", value = "/books")
public class BookServlet extends HttpServlet {
    private static final String FILE_PATH = "C:\\Users\\grine\\IdeaProjects\\OOPServlLab4\\src\\main\\java\\com\\example\\oopservllab4\\books.json";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Gson gson = new Gson();
        List<Book> books;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            Type listType = new TypeToken<ArrayList<Book>>() {}.getType();
            books = gson.fromJson(reader, listType);
            response.getWriter().write(gson.toJson(books));
        } catch (IOException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ошибка при чтении списка книг");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        StringBuilder jsonRequest = new StringBuilder();
        String line;

        try (BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                jsonRequest.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ошибка при чтении запроса");
            return;
        }

        Gson gson = new GsonBuilder().create();
        Book book = gson.fromJson(jsonRequest.toString(), Book.class);

        // Чтение текущего списка автомобилей из файла
        List<Book> books = new ArrayList<>();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(FILE_PATH))) {
            Type listType = new TypeToken<ArrayList<Book>>() {}.getType();
            books = gson.fromJson(fileReader, listType);
        } catch (IOException e) {
            e.printStackTrace();
        }

        books.add(book);

        try (FileWriter fileWriter = new FileWriter(FILE_PATH)) {
            gson.toJson(books, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ошибка при записи книги");
            return;
        }

        doGet(request, response);
    }
}