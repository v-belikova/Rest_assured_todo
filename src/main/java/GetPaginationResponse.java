import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;

public class GetPaginationResponse {
    public int getStatusCode() {
        return statusCode;
    }

    public Date createdAt;
    public int id;
    public boolean status;
    public String text;
    public Date updatedAt;

    public boolean isSuccess() {
        return success;
    }

    public int notReady;
    public int numberOfElements;
    public int ready;

    public Data data;
    public int statusCode;
    public boolean success;


}
