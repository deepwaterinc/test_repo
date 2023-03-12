package com.education.repository;

import com.education.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ритман Степан
 * Интерфейс, наследующийся от JpaRepository для доступа к таблице Employee
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    /**
     * Метод, используемый для добавления сущности Employee в архив
     * @param id id сущности
     */
    @Modifying
    @Query(value = "UPDATE employee SET archived_date = current_timestamp WHERE id = :id", nativeQuery = true)
    void moveToArchive(@Param("id") Long id);

    /**
     * Метод, производящий поиск в таблице сущности Employee с заданным id,
     * не находящейся в архиве
     * @param id id сущности
     * @return объект сущности Employee
     */
    @Query("SELECT emp FROM Employee emp WHERE emp.id = :id AND emp.archivedDate IS NULL")
    Employee findByIdNotArchived(@Param("id") Long id);

    /**
     * Метод, производящий поиск в таблице сущностей Employee с заданными id,
     * не находящихся в архиве
     * @param ids коллекция id сущностей
     * @return список сущностей Employee с заданными id
     */
    @Query("SELECT emp FROM Employee emp WHERE emp.id IN :ids AND emp.archivedDate IS NULL")
    List<Employee> findAllByIdNotArchived(@Param("ids") Iterable<Long> ids);

    /**
     * Метод, производящий поиск в таблице сущностей Employee по введенным символам
     * @param fio
     * @return
     */
    @Query("SELECT emp FROM Employee emp WHERE concat(emp.lastName,' ', emp.firstName,' ', emp.middleName) LIKE %:fio% ORDER BY emp.lastName")
    List<Employee> findAllByLastNameLikeOrderByLastName(String fio);
}
