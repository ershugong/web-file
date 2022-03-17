package cn.mx.webfile.config;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JsonResult<T> {
    private int code;
    private String msg;
    private T data;

    public JsonResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T>JsonResult success(T data){
        return new JsonResult(200,"成功",data);
    }
}
