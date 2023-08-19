package project.personal.domain.todo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TodoStatus {

    INIT("생성"),
    CANCEL("취소"),
    COMPLETED("완료"),
    FAILED("미완료");

    private final String text;

}
