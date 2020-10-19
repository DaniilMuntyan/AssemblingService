package kpi.trspo.restapp.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public final class ErrorResponse {
    private Date timestamp;
    private String message;
    private String details;
}
