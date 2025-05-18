package com.idus.worklog.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.idus.worklog.enums.WorkLogStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_worklog")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkLog {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "work_log_selected", nullable = false)
    private LocalDateTime workLogSelected;

    @Column(name = "delayed_check_in", nullable = false)
    private Boolean delayedCheckIn;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @Column(name = "work_log_status")
    @Enumerated(EnumType.STRING)
    private WorkLogStatus workLogStatus;
    
    @CreationTimestamp
    private LocalDateTime createdAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getWorkLogSelected() {
		return workLogSelected;
	}

	public void setWorkLogSelected(LocalDateTime workLogSelected) {
		this.workLogSelected = workLogSelected;
	}

	public Boolean getDelayedCheckIn() {
		return delayedCheckIn;
	}

	public void setDelayedCheckIn(Boolean delayedCheckIn) {
		this.delayedCheckIn = delayedCheckIn;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public WorkLogStatus getWorkLogStatus() {
		return workLogStatus;
	}

	public void setWorkLogStatus(WorkLogStatus workLogStatus) {
		this.workLogStatus = workLogStatus;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
    
    
}