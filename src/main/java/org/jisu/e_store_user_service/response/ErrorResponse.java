package org.jisu.e_store_user_service.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ErrorResponse {
    private String error;
    private List<String> suberrors;
    @JsonIgnore
    private Integer status;
}
