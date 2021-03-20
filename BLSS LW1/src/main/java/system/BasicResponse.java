package system;

public class BasicResponse {

    private String status;
    private Integer code;

    public BasicResponse(String status, Integer code) {
        this.status = status;
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public Integer getCode() {
        return code;
    }
}
