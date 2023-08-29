package project.personal.domain;


import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 하위 Entity 들이 이 추상객체를 상속받을 것이기 때문에
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @CreatedDate
//    @Setter 테스트용
//    @Column(updatable = true) 테스트용
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    @LastModifiedDate
    @Column(updatable = true)
    private LocalDateTime modifiedDate;

    @LastModifiedBy
    @Column(updatable = true)
    private String modifiedBy;

}