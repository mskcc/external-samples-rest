# external-samples-rest
Rest service to retrieve DMP samples for IGO

1. Samples endpoint<br/>
  usage: /samples/<EXTERNAL_SAMPLE_ID><br/>
  eg. for DMP Samples: /samples/P-0020725-N01<br/>
2. Get all samples for a patient: <br/>
  a) by Cmo patient id<br/>
      /samples/patientCmo/<CMO_PATIENT_ID><br/>
  b) by Dmp patient id<br/>
      /samples/patientDmp/<DMP_PATIENT_ID><br/>
3. Get samples using pagination <br/>
  a) usage: samples/get?page=<PAGE_NUMBER>&size=<SIZE_> <br/>
     page number is 0 based <br/><br/>
     result: content + information about pages:<br/>
       { <br/>
         "last": {<br/>
            "type": "boolean"<br/>
         },<br/>
         "totalPages":{<br/>
            "type": "integer"<br/>
         },<br/>
         "totalElements":{<br/>
            "type": "integer"<br/>
         },<br/>
         "size":{<br/>
            "type": "integer"<br/>
         },<br/>
         "number":{<br/>
            "type": "integer"<br/>
         },<br/>
         "sort":{<br/>
            "type": "string"<br/>
         },<br/>
         "first":{<br/>
            "type": "boolean"<br/>
         },<br/>
         "numberOfElements":{<br/>
            "type": "integer"<br/>
         }<br/>
       }<br/>
  b) sorting by any returned field: samples/get?page=1&size5&sort=runId&direction=desc<br/>
4. Returned fields:<br/>
{<br/>
“externalId”: {<br/>
“type” : “string”<br/>
},<br/>
“filePath”: {<br/>
“type” : “string”<br/>
},<br/>
“externalPatientId”: {<br/>
“type” : “string”<br/>
},<br/>
“tumorNormal”: {<br/>
“type” : “string”<br/>
},<br/>
“counter”: {<br/>
“type” : “integer”<br/>
},<br/>
“runId”: {<br/>
“type” : “string”<br/>
},<br/>
“sampleOrigin”: {<br/>
“type” : “string”<br/>
},<br/>
“sampleClass”: {<br/>
“type” : “string”<br/>
},<br/>
“cmoId”: {<br/>
“type” : “string”<br/>
},<br/>
“nucleidAcid”: {<br/>
“type” : “string”<br/>
},<br/>
“patientCmoId”: {<br/>
“type” : “string”<br/>
},<br/>
“specimenType”: {<br/>
“type” : “string”<br/>
},<br/>
“sex”: {<br/>
“type” : “string”<br/>
},<br/>
“oncotreeCode”: {<br/>
“type” : “string”<br/>
},<br/>
“baitVersion”: {<br/>
“type” : “string”<br/>
},<br/>
“tissueSite”: {<br/>
“type” : “string”<br/>
},<br/>
“preservationType”: {<br/>
“type” : “string”<br/>
},<br/>
“externalRunId”: {<br/>
“type” : “string”<br/>
}<br/>
}<br/>

