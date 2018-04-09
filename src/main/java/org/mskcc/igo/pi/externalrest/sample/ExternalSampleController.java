package org.mskcc.igo.pi.externalrest.sample;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.mskcc.domain.sample.ExternalSample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
public class ExternalSampleController {
    private static final Logger LOGGER = LogManager.getLogger(ExternalSampleController.class);

    @Autowired
    private ExternalSampleService externalSampleService;

    @RequestMapping("/samples/{externalId}")
    public ResponseEntity<List<ExternalSample>> getSample(@PathVariable String externalId) {
        LOGGER.info(String.format("Get sample with id %s invoked", externalId));

        try {
            List<ExternalSample> externalSamples = externalSampleService.getSample(externalId);
            if (externalSamples.size() == 0) {
                String message = String.format("No samples found with id: %s", externalId);
                LOGGER.info(message);

                return ResponseEntity.ok().body(Collections.emptyList());
            }

            return ResponseEntity.ok().body(externalSamples);
        } catch (Exception e) {
            String message = String.format("Error occured while retrieving sample with id: %s", externalId);
            LOGGER.warn(message);
            throw new RuntimeException(message, e);
        }
    }

    @RequestMapping(value = "/samples", method = RequestMethod.GET)
    public ResponseEntity<List<ExternalSample>> getAllSamples() {
        LOGGER.info("Get all samples invoked");

        try {
            List<ExternalSample> externalSamples = externalSampleService.getAll();
            return ResponseEntity.ok(externalSamples);
        } catch (Exception e) {
            String message = "Error occured while retrieving all samples";
            LOGGER.warn(message);
            throw new RuntimeException(message, e);
        }
    }

    @RequestMapping(value = "/samples", method = RequestMethod.POST)
    public ResponseEntity<ExternalSample> addSample(@RequestBody ExternalSample externalSample) {
        LOGGER.info(String.format("Add sample with id %s invoked", externalSample.getExternalId()));

        try {
            return ResponseEntity.ok(externalSampleService.addSample(externalSample));
        } catch (ExternalSampleService.SampleExistsException e) {
            LOGGER.warn(e);
            return new ResponseEntity(externalSample, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch (Exception e) {
            String message = String.format("Error occurred while saving sample: %s", externalSample);
            LOGGER.warn(message);
            throw new RuntimeException(message, e);
        }
    }
}
