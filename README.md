# external-samples-rest
Rest service to retrieve DMP samples for IGO

1. Samples endpoint
  usage: /samples/<EXTERNAL_SAMPLE_ID>
  eg. for DMP Samples: /samples/P-0020725-N01
2. Get all samples for a patient:
  a) by Cmo patient id
      /samples/patientCmo/<CMO_PATIENT_ID>
  b) by Dmp patient id
      /samples/patientCmo/<DMP_PATIENT_ID>

Credentials:
in file /ifs/work/pi/external-samples-rest/prod/application.properties:

there are 2 users:
external.sample.rest.admin.username=
external.sample.rest.admin.password=

external.sample.rest.user.username=
external.sample.rest.user.password=
