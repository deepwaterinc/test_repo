package com.education.repository;

import com.education.entity.Appeal;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * Связь таблицы Appeal с базой данных
 */
@Repository
public interface AppealRepository extends JpaRepository<Appeal, Long> {

    @EntityGraph(attributePaths = {"creator", "signer"})
    Optional<Appeal> findById(Long id);

    @EntityGraph(attributePaths = {"creator", "signer"})
    List<Appeal> findAllById(Iterable<Long> ids);

    @Modifying
    @Query(value = "UPDATE appeal SET archived_date = current_timestamp where id = :id",
            nativeQuery = true)
    void moveToArchive(@Param("id") Long id);

    @Query("select r from Appeal r where r.id = :id and r.archivedDate is null")
    @EntityGraph(attributePaths = {"creator", "signer"})
    Optional<Appeal> findByIdNotArchived(@Param("id") Long id);

    @Query(value = "select r from Appeal r where r.id in :ids and r.archivedDate is null ")
    @EntityGraph(attributePaths = {"creator", "signer"})
    List<Appeal> findAllByIdNotArchived(@Param("ids") Iterable<Long> ids);


    @Query(nativeQuery = true,
            value = "SELECT a.* FROM appeal a " +
            "LEFT JOIN employee e ON a.creator_id = e.id " +
            "WHERE e.id = :id ORDER BY a.id OFFSET :firstPoint LIMIT :amount")
    List<Appeal> findByIdEmployee(@Param(value = "id")Long id,
                                  @Param(value = "firstPoint")Long first,
                                  @Param(value = "amount")Long amount);


    @Modifying
    @Query(value = "UPDATE appeal SET appeal_status = 'UNDER_CONSIDERATION'" +
            "   from appeal a" +
            "   left join appeal_question aq on a.id = aq.appeal_id" +
            "   left join resolution r on aq.question_id = r.question_id" +
            "   where r.id = :resolutionId", nativeQuery = true)
    void moveToUnderConsideration(@Param("resolutionId") long resolutionId);
}

