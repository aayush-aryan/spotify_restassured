package org.bridgelabz;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class SpotifyTest {

    public static String token="";
    public String User_ID="";
    public final String JSON= "application/json";


    @BeforeMethod
    public void get(){
         token="Authorization: Bearer BQBPErlPVs2VTKBpZEg_4UIFGVJ_joxpkCOmviyfjsMjVfQrhK4Y6N6O5lvohsfVNSiPetmTonnb8fD0qI5QKYNlFuE5pvhfLHJeTLFWXGmXqL9FmAKrCKQZ9pipAONzVI5Ez8nLC9AagCVs9GyShf70QVYefOPq0QwvmJsY_7NzgX_WgGi8pV7rvu6VfSrRFoX_FWcqbBT42tU8gLx3ZmM2_L3nW1int9vokgWC0FxcALLiVt5FmMfrvSQfa0E5G1dKY__Jg7XactH6JcBIg0NLKqiIjkzFBorMcb8";
    }
    @Test(priority = 1)
    public void UserIdGetRequest(){
        Response response = given().contentType(JSON).accept(JSON).header("Authorization",token)
                .when().get("https://api.spotify.com/v1/me");
        User_ID = response.path("id"); // this step fetch user id;
        String UserName=response.path("display_name");
        System.out.println(UserName);
          response.then().assertThat().statusCode(200);  // validation of status code;
        System.out.println(User_ID);
    }
     @Test(priority = 2)
    public void userProfileGetRequest(){
        Response response = given().accept(JSON).contentType(JSON).header("Authorization",token)
                .when().get("https://api.spotify.com/v1/users/" + User_ID+ '/');
        response.then().assertThat().statusCode(200);
        response.prettyPrint();

    }
    @Test
    public  void createPlaylist_Post_Request() {
        Response response = given()
                .accept(JSON)
                .contentType(JSON)
                .header("Authorization", token)
                .body("{\"name\": \"Party_Mix_New\",\"description\": \"New playlist description\",\"public\": true}")
                .when()
                .post(" https://api.spotify.com/v1/users/" + User_ID + "/playlists");
        String name = response.path("owner.display_name");
        String playListName = response.path("name");
        System.out.println("Name Of owner: " + name);
        System.out.println("Name Of playList: " + playListName);
        response.then().assertThat().statusCode(404); //Validation Of PlayList
        response.prettyPrint(); //optional
    }
    @Test
    public void ListOf_UserPlayList_Get_Request(){
        Response response = given().accept(JSON).contentType(JSON).header("Authorization" ,"token")
                .when().get("https://api.spotify.com/v1/me/playlists");
        response.then().assertThat().statusCode(400);
        response.prettyPrint();
    }





}
