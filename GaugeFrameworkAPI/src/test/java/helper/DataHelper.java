package helper;

public class DataHelper {

    private static volatile DataHelper _instance = null;

    private DataHelper() {
    }

    public static DataHelper getInstance() {
        if (_instance == null) {
            synchronized (DataHelper.class) {
                if (_instance == null) {
                    _instance = new DataHelper();
                }
            }
        }
        return _instance;
    }
    private String response;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}

