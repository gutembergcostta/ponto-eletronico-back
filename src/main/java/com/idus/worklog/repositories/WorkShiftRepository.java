package com.idus.worklog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.idus.worklog.models.WorkShift;

public interface WorkShiftRepository extends JpaRepository<WorkShift, Long> {

}
