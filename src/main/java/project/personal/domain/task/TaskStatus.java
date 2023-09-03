package project.personal.domain.task;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TaskStatus {

    CREATE("생성"),
    START("시작"),
    UPDATE("수정"),
    CANCEL("취소"),
    COMPLETE("완료"),
    DELETE("삭제");

    private final String text;

}
