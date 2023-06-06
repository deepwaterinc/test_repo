package com.education.service.employee.impl;

import com.education.entity.Employee;
import com.education.repository.EmployeeRepository;
import com.education.service.employee.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * @author Степан Ритман
 * Сервис-класс для объекта Employee
 */
@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    /**
     * Объект класса-репозитория для сущности Employee
     */
    private final EmployeeRepository employeeRepository;

    /**
     * Метод для сохранения сущности Employee в таблицу
     * @param emp сохраняемый объект Employee
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(Employee emp) {
        emp.setCreationDate(ZonedDateTime.now(ZoneId.of("Europe/Moscow")));
        employeeRepository.save(emp);
    }

    /**
     * Метод для поиска объекта Employee по id среди всех записей
     * @param id id объекта
     * @return объект Employee
     */
    @Override
    @Transactional(readOnly = true)
    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElse(new Employee());
    }

    /**
     * Метод для поиска объектов Employee по коллекции id среди всех записей таблицы
     * @param ids коллекция id
     * @return Список объектов Employee
     */
    @Override
    @Transactional(readOnly = true)
    public List<Employee> findAllById(Iterable<Long> ids) {
        return employeeRepository.findAllById(ids);
    }

    /**
     * Метод для поиска объекта Employee по id среди не находящихся в архиве
     * @param id id объекта
     * @return объект Employee
     */
    @Override
    @Transactional(readOnly = true)
    public Employee findByIdNotArchived(Long id) {
        return employeeRepository.findByIdNotArchived(id);
    }

    /**
     * Метод для поиска объектов Employee по коллекции id среди не находящихся в архиве
     * @param ids коллекция id
     * @return Список объектов Employee
     */
    @Override
    @Transactional(readOnly = true)
    public List<Employee> findAllByIdNotArchived(Iterable<Long> ids) {
        return employeeRepository.findAllByIdNotArchived(ids);
    }

    /**
     * Метод для добавления объекта Employee в архив
     * @param id id объекта Employee
     */
    @Override
    @Transactional
    public void moveToArchive(Long id) {
        employeeRepository.moveToArchive(id);
    }

    /**
     * Метод, производящий поиск в таблице сущностей Employee по введенным символам
     * @param fio
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<Employee> findAllByLastNameLikeOrderByLastName(String fio) {
        return employeeRepository.findAllByLastNameLikeOrderByLastName(fio);
    }
}
