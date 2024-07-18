package step;

import com.thoughtworks.gauge.Step;
import helper.DataHelper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import model.ApiResponse;
import org.junit.Assert;
import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.given;

@Slf4j
public class StepImplementation {

    @Step("<url> servisine istek atılır. Statusun <status>, kaydın <count> olduğu ve değeri <price> olan kaydın <who> olduğu doğrulanır.")
    public ApiResponse step(String url, int status, int coount, int price, String who) {
        var response = given().baseUri(url)
                .contentType(ContentType.JSON)
                .get()
                .then()
                .statusCode(status)
                .extract().as(ApiResponse.class);

        System.out.println("Toplam Kayit: " + response.getData().size());
        Assert.assertEquals(response.getData().size(),coount);

        for (int i=0; i<response.getData().size(); i++){
            if (response.getData().get(i).getEmployee_salary()==price){
                Assert.assertEquals(response.getData().get(i).getEmployee_name(),who);
                System.out.println("*******************************************");
                System.out.println("Id: "+ response.getData().get(i).getId() + " \nPrice: " + response.getData().get(i).getEmployee_salary() + " \nName: " + response.getData().get(i).getEmployee_name());

                break;
            }
        }
        return response;
    }

    DataHelper dataHelper = DataHelper.getInstance();
    @Step("<url> servisine istek atılır. Servisin https status <status> döndüğü kontrol edilir.")
    public void step1(String url,int status) {
        Response response = RestAssured.given().baseUri(url)
                .contentType(ContentType.JSON)
                .get()
                .then()
                .extract().response();

        response.prettyPrint();
        System.out.println("Status code: " + response.getStatusCode());
        Assert.assertEquals(response.statusCode(),status);
        dataHelper.setResponse(response.asPrettyString());
        System.out.println("**************************************************");

    }

    @Step("<count> adet kaydın geldiği kontrol edilir.")
    public void step2(int count) {

        String response = dataHelper.getResponse();
        List<Map<String, Object>> employeeList = JsonPath.from(response).getList("data");
        int lastIndex = employeeList.size() - 1;
        int id = (int) employeeList.get(lastIndex).get("id");
        System.out.println("Toplam Kayit: " + id);
        Assert.assertEquals(id,count);
        System.out.println("**************************************************");


    }

    @Step("employee_salary değeri <price> olan kaydın employee_name değerinin <who> olduğu kontrol edilir.")
    public void step3(int price, String who){


        String response = dataHelper.getResponse();
        List<Map<String, Object>> employeeList = JsonPath.from(response).getList("data");
        for (Map<String, Object> employee : employeeList) {
            String employeeName = (String) employee.get("employee_name");
            int employeeSalary = (int) employee.get("employee_salary");
            int id = (int) employee.get("id");
            if (employeeSalary == price){
                Assert.assertEquals(employeeName,who);
                System.out.println("Id: "+ id + " \nPrice: " + employeeSalary + " \nName: " + employeeName);
                break;
            }
        }
        System.out.println("**************************************************");

    }



}
