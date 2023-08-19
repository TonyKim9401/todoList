package project.personal.api.service.todo.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class TodoCreateServiceRequest {

    private List<String> productNumbers;

    @Builder
    private TodoCreateServiceRequest(List<String> productNumbers) {
        this.productNumbers = productNumbers;
    }
}
