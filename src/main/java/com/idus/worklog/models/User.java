package com.idus.worklog.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.idus.worklog.enums.Role;
import com.idus.worklog.enums.WorkShiftType;
import com.idus.worklog.strategy.EightHourShift;
import com.idus.worklog.strategy.SixHourShift;
import com.idus.worklog.strategy.WorkShift;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_user")
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String name;

	@Column
	private String email;

	@Column
	private String password;

	@Enumerated(EnumType.STRING)
	@Column
	private Role role;

	@Enumerated(EnumType.STRING)
	@Column(name = "work_shift_type", nullable = false)
	private WorkShiftType workShiftType;

	@Transient
	private WorkShift workShift;

	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<WorkLog> workLogs = new ArrayList<>();

	@CreationTimestamp
	private LocalDateTime createdAt;

	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	public User() {
		
	}
	
	public User(String name, String email, String password, Role role, WorkShiftType workShiftType){
		this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.workShiftType = workShiftType;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public WorkShift getWorkShift() {
		if (this.workShift == null) {
			this.workShift = switch (this.workShiftType) {
			case SIX_HOURS -> new SixHourShift();
			case EIGHT_HOURS -> new EightHourShift();
			};
		}
		return this.workShift;
	}

	public List<WorkLog> getWorkLogs() {
		return workLogs;
	}

	public WorkShiftType getWorkShiftType() {
		return workShiftType;
	}

	public void setWorkShiftType(WorkShiftType workShiftType) {
		this.workShiftType = workShiftType;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if(this.role == Role.ADMIN){
			return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
		}else {
			return List.of(new SimpleGrantedAuthority("ROLE_USER"));
		}
	}

	@Override
	public String getUsername() {
		return email;
	}

}
