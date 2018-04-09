package org.mskcc.igo.pi.external.rest.sample;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mskcc.domain.external.ExternalSample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class ExternalSampleController {
    private static final Logger LOGGER = LogManager.getLogger(ExternalSampleController.class);

    @Autowired
    private ExternalSampleGateway externalSampleGateway;

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/samples/{externalId}", method = RequestMethod.GET)
    public ResponseEntity<ExternalSample> getSample(@PathVariable String externalId) {
        LOGGER.info(String.format("Get sample with id %s invoked", externalId));

        try {
            Optional<ExternalSample> externalSample = externalSampleGateway.getSample(externalId);
            if (!externalSample.isPresent()) {
                String message = String.format("No samples found with id: %s", externalId);
                LOGGER.info(message);

                return ResponseEntity.ok().body(null);
            }

            return ResponseEntity.ok().body(externalSample.get());
        } catch (Exception e) {
            String message = String.format("Error occurred while retrieving sample with id: %s", externalId);
            LOGGER.warn(message);
            throw new RuntimeException(message, e);
        }
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/samples", method = RequestMethod.GET)
    public ResponseEntity<List<ExternalSample>> getAllSamples() {
        LOGGER.info("Get all samples invoked");

        try {
            List<ExternalSample> externalSamples = externalSampleGateway.getAll();
            return ResponseEntity.ok(externalSamples);
        } catch (Exception e) {
            String message = "Error occurred while retrieving all samples";
            LOGGER.warn(message);
            throw new RuntimeException(message, e);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/samples", method = RequestMethod.POST)
    public ResponseEntity<String> addSample(@RequestBody ExternalSample externalSample) {
        LOGGER.info(String.format("Add sample invoked: %s", externalSample));

        externalSampleGateway.addSample(externalSample);
        return ResponseEntity.ok().build();
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Saving sample error")
    @ExceptionHandler(Exception.class)
    public void savingSampleException(Exception e) {
        String message = String.format("Error occurred while saving sample");
        LOGGER.warn(message, e);
    }

    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "Sample already exists")
    @ExceptionHandler(SampleExistsException.class)
    public void sampleExists(SampleExistsException e) {
        String message = String.format("Sample already exists");
        LOGGER.warn(message, e);
    }

    static class SampleExistsException extends RuntimeException {
        public SampleExistsException(String message) {
            super(message);
        }
    }
}
