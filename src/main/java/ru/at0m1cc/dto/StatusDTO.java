package ru.at0m1cc.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StatusDTO {
    private String status;
    public StatusDTO(StatusCode status) {
        switch (status) {
            case OK:
                this.status = "OK";
                break;
            case ERROR:
                this.status = "ERROR";
                break;
            case ERROR_ENTITY_ALREADY_EXISTS:
                this.status = "ERROR_ENTITY_ALREADY_EXISTS";
                break;
        }

    }

}
