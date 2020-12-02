package website.com.marcioheleno.aspect.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
public class ErrorDetails {

    public Date timestamp;
    private String message;
    private String details;

}
