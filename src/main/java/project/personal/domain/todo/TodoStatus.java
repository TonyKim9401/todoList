package project.personal.domain.todo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TodoStatus {

    CREATE("생성"),
    START("시작"),
    CANCEL("취소"),
    COMPLETE("완료"),
    DELETE("삭제");

    private final String text;

}
