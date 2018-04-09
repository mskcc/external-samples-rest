package org.mskcc.igo.pi.external.rest.run;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mskcc.domain.external.ExternalRun;
import org.mskcc.util.rest.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class ExternalRunController {
    private static final Logger LOGGER = LogManager.getLogger(ExternalRunController.class);

    @Autowired
    private ExternalRunGateway externalRunGateway;

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/runs/{anonymizedRunId}", method = RequestMethod.GET)
    public ResponseEntity<ExternalRun> getRun(@PathVariable String anonymizedRunId) {
        try {
            LOGGER.info(String.format("Get run with anonymized id %s invoked", anonymizedRunId));

            Optional<ExternalRun> externalRun = externalRunGateway.getRunByAnonymizedId(anonymizedRunId);
            if (externalRun.isPresent())
                return ResponseEntity.ok().body(externalRun.get());

            String message = String.format("No runs found with anonymized id: %s", anonymizedRunId);
            LOGGER.info(message);

            ResponseEntity<ExternalRun> responseEntity = ResponseEntity.notFound().build();
            responseEntity.getHeaders().add(Header.ERRORS.name(), message);

            return responseEntity;
        } catch (Exception e) {
            String errorMsg = String.format("Error while trying to get run with anonymized run id: %s",
                    anonymizedRunId);
            LOGGER.warn(errorMsg, e);
            String message = String.format("%s. Cause: %s", errorMsg, e.getMessage());

            ResponseEntity<ExternalRun> responseEntity = new ResponseEntity<>(new EmptyExternalRun(), HttpStatus
                    .INTERNAL_SERVER_ERROR);
            responseEntity.getHeaders().add(Header.ERRORS.name(), message);

            return responseEntity;
        }
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/runs", method = RequestMethod.GET)
    public ResponseEntity<List<ExternalRun>> getAllRuns() {
        try {
            LOGGER.info("Get all runs invoked");
            List<ExternalRun> externalSamples = externalRunGateway.getAll();
            return ResponseEntity.ok(externalSamples);
        } catch (Exception e) {
            String errorMsg = "Error while trying to get all runs";
            LOGGER.warn(errorMsg, e);
            String message = String.format("%s. Cause: %s", errorMsg, e.getMessage());

            ResponseEntity<List<ExternalRun>> responseEntity = new ResponseEntity<>(Collections.emptyList(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
            responseEntity.getHeaders().add(Header.ERRORS.name(), message);

            return responseEntity;
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/runs", method = RequestMethod.POST)
    public ResponseEntity<String> addRun(@RequestBody ExternalRun externalRun) {
        try {
            LOGGER.info(String.format("Add run %s invoked", externalRun));
            validateInput(externalRun);
            externalRunGateway.addRun(externalRun);

            return ResponseEntity.ok("OK");
        } catch (Exception e) {
            String errorMsg = String.format("Error while trying to save external run: %s", externalRun);
            LOGGER.warn(errorMsg, e);
            String message = String.format("%s. Cause: %s", errorMsg, e.getMessage());

            ResponseEntity<String> responseEntity = new ResponseEntity<>("", HttpStatus.UNPROCESSABLE_ENTITY);
            responseEntity.getHeaders().add(Header.ERRORS.name(), message);

            return responseEntity;
        }
    }

    private void validateInput(ExternalRun externalRun) {
        if (externalRun == null || StringUtils.isEmpty(externalRun.getAnonymizedId()) || StringUtils.isEmpty
                (externalRun.getRealRunId()))
            throw new IncorrectExternalRunException(String.format("External run: %s is not correct. Both anonymized " +
                    "and read run id must be filled in.", externalRun));
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<String> serverExceptionHandler(Exception e) {
        LOGGER.warn("Error while trying to save external run", e);

        String message = String.format("Error while trying to invoke a rest call. Cause: %s", e.getMessage());

        ResponseEntity<String> responseEntity = new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
        responseEntity.getHeaders().add(Header.ERRORS.name(), message);

        return responseEntity;
    }

    private class IncorrectExternalRunException extends RuntimeException {
        public IncorrectExternalRunException(String message) {
            super(message);
        }
    }

    private class EmptyExternalRun extends ExternalRun {
    }
}
