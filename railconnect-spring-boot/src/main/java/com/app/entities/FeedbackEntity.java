package com.app.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_feedback")
@AttributeOverride(name = "id", column = @Column(name = "feedback_id"))
public class FeedbackEntity extends BaseEntity{

    @Lob // Large object annotation if feedback can be lengthy
    @Column(name = "feedback", nullable = false)
    private String feedback;

    // ManyToOne relationship with User entity
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id_fk", referencedColumnName = "user_id")
    private UserEntity user;
}
