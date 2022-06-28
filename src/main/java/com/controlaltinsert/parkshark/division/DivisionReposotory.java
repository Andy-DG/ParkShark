package com.controlaltinsert.parkshark.division;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DivisionReposotory extends JpaRepository<Division,Long> {
    Division createNewDivision(String name);
    }
}
