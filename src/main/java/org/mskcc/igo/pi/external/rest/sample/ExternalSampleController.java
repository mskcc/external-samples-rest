package org.mskcc.igo.pi.external.rest.sample;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mskcc.domain.external.ExternalSample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
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
    @RequestMapping(value = "/samples/patientCmo/{patientCmoId}", method = RequestMethod.GET)
    public ResponseEntity<Collection<ExternalSample>> getSampleByCmoPatientId(@PathVariable String patientCmoId) {
        LOGGER.info(String.format("Get samples for patient CMO id %s invoked", patientCmoId));

        try {
            Collection<ExternalSample> externalSamples = externalSampleGateway.getSamplesByCmoPatientId(patientCmoId);
            return ResponseEntity.ok().body(externalSamples);
        } catch (Exception e) {
            String message = String.format("Error occurred while retrieving sample for patient CMO id: %s",
                    patientCmoId);
            LOGGER.warn(message);
            throw new RuntimeException(message, e);
        }
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/samples/patientDmp/{patientDmpId}", method = RequestMethod.GET)
    public ResponseEntity<Collection<ExternalSample>> getSampleByDmpPatientId(@PathVariable String patientDmpId) {
        LOGGER.info(String.format("Get samples for patient DMP id %s invoked", patientDmpId));

        try {
            Collection<ExternalSample> externalSamples = externalSampleGateway.getSamplesByDmpPatientId(patientDmpId);
            return ResponseEntity.ok().body(externalSamples);
        } catch (Exception e) {
            String message = String.format("Error occurred while retrieving sample for patient DMP id: %s",
                    patientDmpId);
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

    @PreAuthorize("hasRole('USER')")
    @GetMapping(value = "/samples/get", params = {"page", "size"})
    public ResponseEntity<Page<ExternalSample>> getPaginated(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam(value = "sort", required = false) String sort,
            @RequestParam(value = "direction", required = false) String direction) {
        LOGGER.info("Get all samples invoked");

        try {
            Page<ExternalSample> externalSamples = externalSampleGateway.findPaginated(page, size, sort, direction);
            if (page > externalSamples.getTotalPages()) {
                throw new RuntimeException(String.format("Page %d exceeds total number of pages %s", page,
                        externalSamples.getTotalPages()));
            }

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

        try {
            externalSampleGateway.addSample(externalSample);
            if (externalSampleGateway.getSample(externalSample.getExternalId()).isPresent())
                return ResponseEntity.ok().build();

            throw new RuntimeException(String.format("External sample not found after attempt to save"));
        } catch (Exception e) {
            throw new RuntimeException(String.format("Error while saving external sample: %s", externalSample), e);
        }
    }

//    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Saving sample error")
//    @ExceptionHandler(Exception.class)
//    public void savingSampleException(Exception e) {
//        String message = String.format("Error occurred while saving sample");
//        LOGGER.warn(message, e);
//    }
//
//    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "Sample already exists")
//    @ExceptionHandler(SampleExistsException.class)
//    public void sampleExists(SampleExistsException e) {
//        String message = String.format("Sample already exists");
//        LOGGER.warn(message, e);
//    }

    static class SampleExistsException extends RuntimeException {
        public SampleExistsException(String message) {
            super(message);
        }
    }
}
