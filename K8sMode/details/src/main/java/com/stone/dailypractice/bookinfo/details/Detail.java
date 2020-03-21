package com.stone.dailypractice.bookinfo.details;


import lombok.Data;

/*
        'id' => id,
        'author': book['authors'][0],
        'year': book['publishedDate'],
        'type' => type,
        'pages' => book['pageCount'],
        'publisher' => book['publisher'],
        'language' => language,
        'ISBN-10' => isbn10,
        'ISBN-13' => isbn13
 */
@Data
public class Detail {
    private String id;
    private String author;
    private String type;
    private int pages;
    private String publisher;
    private String language;
    private String isbn10;
    private String isbn13;
    private String title;
    private String description;

    private String error="";

}
