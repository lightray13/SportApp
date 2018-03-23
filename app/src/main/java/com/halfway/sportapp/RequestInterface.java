package com.halfway.sportapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RequestInterface {

    @GET("/list.php")
    Call <CategoryItem> getCategory(@Query("category") String category);

    @GET("post.php")
    Call <ArticleItem> getArticle(@Query("article") String article);
}
