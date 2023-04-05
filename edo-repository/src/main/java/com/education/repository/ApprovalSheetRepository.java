package com.education.repository;

import com.education.entity.ApprovalSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Ivan Chursinov
 */

@Repository
public interface ApprovalSheetRepository extends JpaRepository<ApprovalSheet, Long> {

    /**
     * Метод для архивирования листа согласования
     * @param id
     */
    @Modifying
    @Query(value = "UPDATE approval_sheet set archived_date = current_timestamp where id = :id ", nativeQuery = true)
    void moveToArchive(@Param("id") Long id);

    /**
     * Метод предоставляет лист согласования по id, не находящийся в архиве
     *
     * @param id Long
     * @return Optional of ApprovalSheet
     */
    @Query(value = "SELECT n FROM ApprovalSheet n WHERE n.id = :id AND n.archivedDate is null")
    Optional<ApprovalSheet> findByIdNotArchived(@Param("id") Long id);

}
