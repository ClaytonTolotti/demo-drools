package com.clayton.drools.service;

import com.clayton.drools.constants.Constants;
import com.clayton.drools.obj.AnalyzesObj;
import com.clayton.drools.obj.EmployeeObj;
import com.clayton.drools.obj.RoleObj;
import com.clayton.drools.dto.AnalyzesDTO;
import com.clayton.drools.dto.DemoRequest;
import com.clayton.drools.entity.AnalyzesEntity;
import com.clayton.drools.entity.EmployeeEntity;
import com.clayton.drools.entity.RoleEntity;
import com.clayton.drools.repository.EmployeeRepository;
import com.clayton.drools.repository.RoleRepository;
import com.clayton.drools.service.interfaces.AnalyzesService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.KieServices;
import org.kie.api.command.Command;
import org.kie.api.command.KieCommands;
import org.kie.api.runtime.ExecutionResults;
import org.kie.server.api.model.KieServiceResponse;
import org.kie.server.api.model.ServiceResponse;
import org.kie.server.client.KieServicesClient;
import org.kie.server.client.RuleServicesClient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Slf4j
@Service
public class AnalyzesServiceImpl implements AnalyzesService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private KieServicesClient kieServicesClient;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AnalyzesDTO analyzeProfile(DemoRequest body) {

        Optional<EmployeeEntity> employeeEntity = employeeRepository.findByNameIgnoreCase(body.getName().toUpperCase());

        if (employeeEntity.isPresent()) {

            Optional<RoleEntity> roleEntity = roleRepository.findByName(body.getRole());

            AnalyzesEntity analyzesEntity = new AnalyzesEntity();
            AnalyzesObj analyzesObj = getAnalyzesDTO(employeeEntity.get());
            if (roleEntity.isPresent()) {
                RoleObj roleTarget = getRoleObj(roleEntity.get());
                analyzesObj.setRoleTarget(roleTarget);
            } else {
                return analyzesDTOEmpty();
            }

            AnalyzesDTO analyzesDTO = AnalyzesDTO.builder().build();
            modelMapper.map(analyzesObj, analyzesEntity);

            RuleServicesClient rulesClient = kieServicesClient.getServicesClient(RuleServicesClient.class);
            KieCommands commandsFactory = KieServices.Factory.get().getCommands();

            Command<?> insert = commandsFactory.newInsert(analyzesObj, analyzesObj.getId().toString());
            Command<?> fireAllRules = commandsFactory.newFireAllRules();
            Command<?> batchCommand = commandsFactory.newBatchExecution(Arrays.asList(insert, fireAllRules), Constants.LOOKUP);

            ObjectMapper mapper = new ObjectMapper();
            try {
                String json = mapper.writeValueAsString(batchCommand);
                log.info("json request: {}", json);
            } catch (JsonProcessingException e) {
                log.error("convert object: {} to json failed: {}", batchCommand.getClass().getSimpleName(), e.getMessage(), e);
            }
            ServiceResponse<ExecutionResults> executeResults = rulesClient.executeCommandsWithResults(Constants.CONTAINER_ID, batchCommand);

            if (executeResults.getType() == KieServiceResponse.ResponseType.SUCCESS) {
                log.info("commands executed with success! response: {}", executeResults.getResult());
                ExecutionResults result = executeResults.getResult();
                modelMapper.map(result.getValue(analyzesObj.getId().toString()), analyzesObj);
            } else {
                log.error("error executing rules. stack trace: {}", executeResults.getMsg());
            }

            modelMapper.map(analyzesObj, analyzesDTO);

            return analyzesDTO;
        } else {
            return analyzesDTOEmpty();
        }
    }

    private AnalyzesDTO analyzesDTOEmpty(){
        return AnalyzesDTO.builder().build();
    }

    private AnalyzesObj getAnalyzesDTO(EmployeeEntity employeeEntity) {

        RoleObj roleObj = getRoleObj(employeeEntity.getRole());
        roleObj.setId(Long.sum(roleObj.getId(), 1));
        EmployeeObj employeeObj = getEmployeeObj(employeeEntity);

        employeeObj.setRole(roleObj);

        return AnalyzesObj.builder()
                .id(employeeObj.getId())
                .employee(employeeObj)
                .validate(Boolean.FALSE)
                .build();
    }

    private RoleObj getRoleObj(RoleEntity roleEntity) {

        return RoleObj.builder()
                .name(roleEntity.getName())
                .id(roleEntity.getId())
                .build();
    }

    private EmployeeObj getEmployeeObj(EmployeeEntity employeeEntity) {

        return EmployeeObj.builder()
                .id(employeeEntity.getId())
                .name(employeeEntity.getName())
                .companyTime(employeeEntity.getCompanyTime())
                .build();
    }
}
