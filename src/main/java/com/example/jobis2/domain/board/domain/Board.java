package com.example.jobis2.domain.board.domain;

import com.example.jobis2.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Data
@Builder
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @OneToMany(mappedBy = "board", cascade = CascadeType.PERSIST, orphanRemoval = true)//부모 엔티티가 저장될 때, 연관된 자식 엔티티도 함께 저장되도록 함
    private List<Image> images;

    /*@OneToMany(mappedBy = "board", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Image> images;*/ // 나중에 넣을 사진

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createDate;

    @PrePersist
    public void onCreate() {
        this.createDate = LocalDate.now();
    }
}
