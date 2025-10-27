package com.aparsh.api.pmay.repository;

import com.aparsh.api.pmay.model.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<StateEntity, String> {
}
