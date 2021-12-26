package com.dians.lab.dianslab.bootstrap;



import com.dians.lab.dianslab.model.Book;
import com.dians.lab.dianslab.model.Library;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Book> bookList = new ArrayList<>();
    public static List<Library> libraryList = new ArrayList<>();

    // String name, String description,
    // Integer counter, String authorName, List<Library> libraries

    //boolean isReadingRoom, Integer counter, String name, String lat, String lon 8615939373
    /*@PostConstruct
    public void init(){
        libraryList.add(new Library( "8615939373", true, 33,"БРАЌА МИЛАДИНОВЦИ", "42.0027424", "21.4120526"));
        libraryList.add(new Library("470029607",
                true, 33, "Gradska Biblioteka", "42.2055718", "22.3361557"));

        libraryList.add(new Library("386718936", false, 2, "Fetkin", "41.4340077", "22.0124363"));


        bookList.add(new Book("\"КАСНИ ПОРАСНИ\"",
                "Преку куси, интересни песнички, авторот ни покажува дека е многу важно да јадеме овошје и зеленчук бидејќи тие се многу важни за нашето здравје.",
                25, "ПЕТРЕ М. АНДРЕЕВСКИ", libraryList));
        bookList.add(new Book("\"ГЛУВЧЕТО МИЦЕ\"",
                "Лектира за Основно образование.",
                30, "ЈАСНА ИГЊАТОВИЌ", libraryList));
        bookList.add(new Book("\"АЛГОРИТМИ И ПОДАТОЧНИ СТРУКТУРИ\"",
                "Запознавање со основните податочни структури и алгоритми кои се понатаму неопходни за работа со бази на податоци како и за други апликации.",
                15, "", libraryList));
        bookList.add(new Book("\"БАЗИ НА ПОДАТОЦИ\"",
                "Запознавање на студентот со основните концепти за работа со бази на податоци, начините на нивно моделирање и имплементирање, како и работа со прашалните јазици.",
                22, "", libraryList));

    }*/
}