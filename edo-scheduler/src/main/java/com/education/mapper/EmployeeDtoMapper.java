package com.education.mapper;

import com.education.DTO.FullEmployeeDto;
import com.education.model.dto.AddressDto;
import com.education.model.dto.DepartmentDto;
import com.education.model.dto.EmployeeDto;
import com.github.aleksandy.petrovich.Case;
import com.github.aleksandy.petrovich.Gender;
import com.github.aleksandy.petrovich.Petrovich;
import org.springframework.stereotype.Service;

import java.time.ZoneId;


@Service
public class EmployeeDtoMapper {

    public EmployeeDto toEmployeeDto(FullEmployeeDto fullEmployeeDto) {
        EmployeeDto employeeDto = new EmployeeDto();

        employeeDto.setFirstName(fullEmployeeDto.getName().getFirst());
        employeeDto.setLastName(fullEmployeeDto.getName().getLast());
        employeeDto.setMiddleName(fullEmployeeDto.getName().getMiddle());
        employeeDto.setAddress(formatAddress(fullEmployeeDto.getLocation()));
        employeeDto.setPhotoUrl(fullEmployeeDto.getPicture().getLarge());
        employeeDto.setPhone(fullEmployeeDto.getPhone());
        employeeDto.setUsername(fullEmployeeDto.getLogin().getUsername());
        employeeDto.setWorkPhone(fullEmployeeDto.getCell());
        employeeDto.setBirthDate(fullEmployeeDto.getDob().getDate().atStartOfDay(ZoneId.systemDefault()));
        employeeDto.setCreationDate(fullEmployeeDto.getRegistered().getDate());

        // External ID - assuming the UUID from the login as externalId
        employeeDto.setExternalId(fullEmployeeDto.getLogin().getUuid());

        employeeDto.setFioNominative(String.format("%s %s %s",
                fullEmployeeDto.getName().getFirst(),
                fullEmployeeDto.getName().getMiddle(),
                fullEmployeeDto.getName().getLast()));

        employeeDto.setFioGenitive(inflectToGenitiveCase(fullEmployeeDto.getName().getFirst(), fullEmployeeDto.getName().getMiddle(), fullEmployeeDto.getName().getLast(), fullEmployeeDto.getGender()));
        employeeDto.setFioDative(inflectToDativeCase(fullEmployeeDto.getName().getFirst(), fullEmployeeDto.getName().getMiddle(), fullEmployeeDto.getName().getLast(), fullEmployeeDto.getGender()));
        return employeeDto;
    }

    public DepartmentDto toDepartmentDto(FullEmployeeDto fullEmployeeDto) {
        DepartmentDto departmentDto = new DepartmentDto();

        departmentDto.setFullName(fullEmployeeDto.getDepartmentDto().getName());

        // Map address from FullEmployeeDto to AddressDto
        FullEmployeeDto.DepartmentDto.Location companyLocation = fullEmployeeDto.getDepartmentDto().getLocation();
        AddressDto companyAddress = new AddressDto();
        companyAddress.setFullAddress(String.format("%s %d, %s, %s, %s, %d",
                companyLocation.getStreet().getName(),
                companyLocation.getStreet().getNumber(),
                companyLocation.getCity(),
                companyLocation.getState(),
                companyLocation.getCountry(),
                companyLocation.getPostcode()));
        companyAddress.setStreet(companyLocation.getStreet().getName());
        companyAddress.setHouse(String.valueOf(companyLocation.getStreet().getNumber()));
        companyAddress.setCity(companyLocation.getCity());
        companyAddress.setRegion(companyLocation.getState());
        companyAddress.setCountry(companyLocation.getCountry());
        companyAddress.setIndex(String.valueOf(companyLocation.getPostcode()));
        departmentDto.setAddress(companyAddress);
        departmentDto.setPhone(fullEmployeeDto.getCell());
        departmentDto.setCreationDate((fullEmployeeDto.getRegistered().getDate()));
        departmentDto.setExternalId(fullEmployeeDto.getDepartmentDto().getId().getValue());


        return departmentDto;
    }


    public String inflectToGenitiveCase(String firstName, String middleName, String lastName, String gender) {
        Petrovich petrovich = new Petrovich();

        // Преобразование пола из JSON в Gender для Petrovich
        Gender genderPetrovich;
        if ("male".equalsIgnoreCase(gender)) {
            genderPetrovich = Gender.MALE;
        } else if ("female".equalsIgnoreCase(gender)) {
            genderPetrovich = Gender.FEMALE;
        } else {
            System.out.println("Unexpected gender: " + gender);
            genderPetrovich = Gender.ANDROGYNOUS;
        }

        Petrovich.Names names = new Petrovich.Names(lastName, firstName, middleName, genderPetrovich);
        Petrovich.Names namesInGenitive = petrovich.inflectTo(names, Case.GENITIVE);

        return namesInGenitive.toString();
    }

    public String inflectToDativeCase(String firstName, String middleName, String lastName, String gender) {
        Petrovich petrovich = new Petrovich();

        // Преобразование пола из JSON в Gender для Petrovich
        Gender genderPetrovich;
        if ("male".equalsIgnoreCase(gender)) {
            genderPetrovich = Gender.MALE;
        } else if ("female".equalsIgnoreCase(gender)) {
            genderPetrovich = Gender.FEMALE;
        } else {
            System.out.println("Unexpected gender: " + gender);
            genderPetrovich = Gender.ANDROGYNOUS;
        }

        Petrovich.Names names = new Petrovich.Names(lastName, firstName, middleName, genderPetrovich);
        Petrovich.Names namesInDative = petrovich.inflectTo(names, Case.DATIVE);

        return namesInDative.toString();
    }

    private String formatAddress(FullEmployeeDto.Location location) {
        return String.format("%s %s, %s, %s, %s",
                location.getStreet().getNumber(),
                location.getStreet().getName(),
                location.getCity(),
                location.getState(),
                location.getCountry());
    }


}
