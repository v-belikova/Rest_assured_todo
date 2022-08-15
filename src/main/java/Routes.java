public class Routes {
    // pages
    public String loginPage = "https://news-feed.dunice-testing.com/loign" + System.getenv("INTERNSHIP_NGROK");
    public String registrationPage = "https://news-feed.dunice-testing.com/registration";
    public String URL = "https://news-feed.dunice-testing.com/api";

    // requests
    public String postRegistration = "https://news-feed.dunice-testing.com/api/v1/auth/register";
    public String postLogin = "https://news-feed.dunice-testing.com/api/v1/auth/login";
    public String getUserInfo = "https://news-feed.dunice-testing.com/api/v1/user";
    public String getUserInfoId = "https://news-feed.dunice-testing.com/api/v1/user/";
    public String updateUserInfo = "https://news-feed.dunice-testing.com/api/v1/user";
    public String deleteUser = "https://news-feed.dunice-testing.com/api/v1/user";

    public String createNews = "https://news-feed.dunice-testing.com/api/v1/news";
    public String getNews = "https://news-feed.dunice-testing.com/api/v1/news?page=1&perPage=3";
    public String getOneNews = "https://news-feed.dunice-testing.com/api/v1/news/find";
    public String getOneNews1 = "https://news-feed.dunice-testing.com/api/v1/";
    public String updateNews = "https://news-feed.dunice-testing.com/api/v1/news/";
    public String deleteNews = "https://news-feed.dunice-testing.com/api/v1/news/";


    public String fileControl = "https://news-feed.dunice-testing.com/api/v1/file/uploadFile";
    public String getFileControl = "https://news-feed.dunice-testing.com/api/v1/file/";
    public String postLogin1 = "https://news-feed.dunice-testing.com/api/v1/auth/login";
    public String todoCreate = "https://news-feed.dunice-testing.com/api/v1/todo";

    public String getPaginatedTodo = "https://news-feed.dunice-testing.com/api/v1/todo";
    public String deleteTodo = "https://news-feed.dunice-testing.com/api/v1/todo/";

    public String patchTodo = "https://news-feed.dunice-testing.com/api/v1/todo/status/";

    public String patchText = "https://news-feed.dunice-testing.com/api/v1/todo/text/";

    public String getUserInfo1  = "https://news-feed.dunice-testing.com/api/v1/user/info";

}
