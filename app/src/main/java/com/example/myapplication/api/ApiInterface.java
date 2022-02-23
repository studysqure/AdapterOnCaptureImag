package com.example.myapplication.api;

import com.example.myapplication.model.QuestionaryWrapper;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

        // getting same data in three different ways.
        @POST("get_question_list.php")
        Call<QuestionaryWrapper> getQuetinary(@Query("type") String type
                                              ,@Query("lang_type") String lang_type);

        @POST("get_question_list.php")
        Call<QuestionaryWrapper> sendAttandeceData(@Query("data") String data);

     /*   @GET("GetCompanyDetailByID")
        Call<CompanyResponse> getDetailOfComapanies(@Query("CompanyID") int companyID);


        @GET("GetCompanyDetailByID")
        Call<ResponseBody> getRawDetailOfCompanies(@Query("CompanyID") int companyID);

        @GET("{pathToAdd}")
        Call<CompanyResponse> getDetailOfComapaniesWithPath(@Path("pathToAdd") String pathToAppend, @Query("CompanyID") int companyID);
    */

}
